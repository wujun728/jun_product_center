# 在京东云服务器上部署 RabbitMQ 的完整步骤（含启动报错排查）

在京东云服务器上用 Docker 部署 RabbitMQ，核心是**镜像选择、端口映射、安全组放行、数据持久化**。结合你之前 IDEA 一键部署后报 MQ 错误、以及当前 Docker 启动报错的场景，下面给你**京东云专属、可直接复制**的完整步骤\+报错排查方案。

---

## 一、京东云服务器准备（先做这 3 步）

### 1\. 安装 Docker（已装跳过）

```bash
# 安装依赖
yum install -y yum-utils device-mapper-persistent-data lvm2
# 添加 Docker 源（若提示网页解析失败，可替换为国内源：http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo）
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# 安装并启动
yum install -y docker-ce docker-ce-cli containerd.io
systemctl start docker && systemctl enable docker
```

注意：若执行添加 Docker 源命令时，报错“网页解析失败，可能是不支持的网页类型”，直接将命令中的源地址替换为国内阿里云源：http://mirrors\.aliyun\.com/docker\-ce/linux/centos/docker\-ce\.repo，可解决解析失败问题。

### 2\. 京东云安全组开放端口（**最关键！否则连不上**）

登录 **京东云控制台 → 云主机 CVM → 安全组 → 管理实例**：新增 **入方向规则**，放行 2 个端口：

- **5672**：AMQP 客户端通信（SpringBoot 连接用）

- **15672**：Web 管理界面

- 协议：TCP

- 源地址：0\.0\.0\.0/0（或仅放你的项目服务器 IP）

### 3\. 服务器防火墙放行（CentOS 7\+）

```bash

firewall-cmd --zone=public --add-port=5672/tcp --permanent
firewall-cmd --zone=public --add-port=15672/tcp --permanent
firewall-cmd --reload
```

---

## 二、Docker 一键启动 RabbitMQ（京东云推荐，修正密码）

### 1\. 拉取带管理界面的镜像

```bash

# 3.13 稳定版（management 自带 Web 控制台）
docker pull rabbitmq:3.13-management
```

### 2\. 创建数据目录（持久化，防止删容器丢数据）

```bash

mkdir -p /data/rabbitmq/data
mkdir -p /data/rabbitmq/log
chmod -R 777 /data/rabbitmq
```

### 3\. 启动命令（直接复制运行，已替换为你提供的密码）

```bash
docker run -d \
  --name rabbitmq \
  --hostname rabbitmq-host \
  -p 5672:5672 \
  -p 15672:15672 \
  -v /data/rabbitmq/data:/var/lib/rabbitmq \
  -v /data/rabbitmq/log:/var/log/rabbitmq \
  -e RABBITMQ_DEFAULT_USER=admin \
  -e RABBITMQ_DEFAULT_PASS=admin_123 \
  --restart=always \
  rabbitmq:3.13-management
```

- **\-\-hostname**：必须固定，否则重启后节点名变、数据丢失

- **5672**：项目连接端口

- **15672**：Web 管理端口

- **RABBITMQ\_DEFAULT\_USER/PASS**：管理员账号（密码已改为你提供的 admin\_123，生产务必改强密码）

### 4\. 验证启动

```bash

# 看容器状态（Up 表示正常）
docker ps | grep rabbitmq

# 看日志（无 ERROR 即可，重点排查启动报错）
docker logs rabbitmq

# 本地测试端口连通
telnet 127.0.0.1 5672
```

---

## 三、Docker 启动报错专项排查（核心解决你的问题）

执行上述启动命令后，若容器未启动（docker ps 看不到 rabbitmq）或启动后报错，按以下步骤排查，覆盖 99% 常见启动错误：

### 1\. 先查日志（最关键，直接定位错误）

```bash

# 查看完整启动日志，重点找 ERROR 关键字
docker logs rabbitmq
# 若容器未启动，查看历史日志
docker logs -f rabbitmq
```

### 2\. 常见启动报错及解决方案（对应你的场景）

#### 报错1：端口被占用（最常见）

日志提示：`Bind for 0\.0\.0\.0:5672 failed: port is already allocated` 或 `port 15672: bind: address already in use`

原因：服务器上已有进程占用 5672 或 15672 端口（可能是之前启动的 RabbitMQ 容器、其他服务）

解决：

```bash

# 1. 查找占用 5672 端口的进程
netstat -tulpn | grep 5672
# 2. 杀死占用进程（替换 PID 为上一步查到的进程号）
kill -9 PID
# 3. 删除之前可能启动失败的 RabbitMQ 容器（若有）
docker rm -f rabbitmq
# 4. 重新执行启动命令
```

#### 报错2：数据目录权限不足

日志提示：`Permission denied` 或 `unable to write to /var/lib/rabbitmq`

原因：创建的 /data/rabbitmq 目录权限不够，Docker 容器无法写入数据

解决：重新执行权限赋值命令，再启动

```bash

chmod -R 777 /data/rabbitmq
# 删除失败容器，重新启动
docker rm -f rabbitmq
docker run -d \
  --name rabbitmq \
  --hostname rabbitmq-host \
  -p 5672:5672 \
  -p 15672:15672 \
  -v /data/rabbitmq/data:/var/lib/rabbitmq \
  -v /data/rabbitmq/log:/var/log/rabbitmq \
  -e RABBITMQ_DEFAULT_USER=admin \
  -e RABBITMQ_DEFAULT_PASS=admin_123 \
  --restart=always \
  rabbitmq:3.13-management
```

#### 报错3：镜像拉取不完整/损坏

日志提示：`no such image` 或 `image is corrupted`

原因：docker pull 时网络中断，导致镜像拉取不完整

解决：删除损坏镜像，重新拉取

```bash

# 删除损坏的镜像
docker rmi rabbitmq:3.13-management
# 重新拉取（若网络慢，可配置阿里云镜像加速器）
docker pull rabbitmq:3.13-management
# 重新启动容器
```

#### 报错4：Docker 服务未启动

提示：`Cannot connect to the Docker daemon at unix:///var/run/docker\.sock\. Is the docker daemon running?`

原因：Docker 服务未启动或启动失败

解决：重启 Docker 服务

```bash

systemctl restart docker
# 查看 Docker 状态，确保是 active（running）
systemctl status docker
# 重新启动 RabbitMQ 容器
```

#### 报错5：hostname 异常（导致启动后无法访问）

日志无明显 ERROR，但容器启动后，telnet 127\.0\.0\.1 5672 不通

日志无明显 ERROR，但容器启动后，telnet 127\.0\.0\.1 5672 不通

原因：\-\-hostname 未固定，或与服务器 hostname 冲突

原因：\-\-hostname 未固定，或与服务器 hostname 冲突

解决：严格使用启动命令中的 `\-\-hostname rabbitmq\-host`，删除旧容器重新启动（命令同上）

解决：严格使用启动命令中的 `\-\-hostname rabbitmq\-host`，删除旧容器重新启动（命令同上）

#### 报错6：Cookie 文件权限异常（当前核心报错）

---

日志提示：`Cookie file /var/lib/rabbitmq/\.erlang\.cookie must be accessible by owner only`

## 四、Web 管理界面访问（修正密码）

原因：RabbitMQ 核心 Cookie 文件（\.erlang\.cookie）权限过高，要求仅文件所有者可访问，当前权限不符合要求（多为之前赋值 777 权限导致）

浏览器打开：

解决：进入容器修改 Cookie 文件权限，或删除旧数据目录重新启动（推荐后者，更简洁），命令可直接复制执行：

```Plain Text

http://京东云服务器公网IP:15672
```

```bash

# 1. 停止并删除当前启动失败的容器
docker rm -f rabbitmq
# 2. （关键）删除之前的持久化数据目录（避免旧Cookie文件残留）
rm -rf /data/rabbitmq
# 3. 重新创建数据目录（无需赋值777，避免权限过高）
mkdir -p /data/rabbitmq/data
mkdir -p /data/rabbitmq/log
# 4. 重新启动容器（自动生成符合权限的Cookie文件）
docker run -d \
  --name rabbitmq \
  --hostname rabbitmq-host \
  -p 5672:5672 \
  -p 15672:15672 \
  -v /data/rabbitmq/data:/var/lib/rabbitmq \
  -v /data/rabbitmq/log:/var/log/rabbitmq \
  -e RABBITMQ_DEFAULT_USER=admin \
  -e RABBITMQ_DEFAULT_PASS=admin_123 \
  --restart=always \
  rabbitmq:3.13-management
# 5. 验证启动（查看是否正常运行）
docker ps | grep rabbitmq
docker logs rabbitmq
```

- 账号：`admin`

注意：无需给 /data/rabbitmq 目录赋值 777 权限，默认权限即可满足需求，过高权限会导致 Cookie 文件权限异常，这是本次报错的核心原因。

- 密码：`admin\_123`（已同步你提供的启动命令密码）

---

## 五、SpringBoot 连接配置（同步密码，解决 MQ 报错）

### application\-prod\.yml（服务器环境，同步密码）

```yaml

spring:
  rabbitmq:
    host: 京东云服务器内网IP/公网IP
    port: 5672
    username: admin
    password: admin_123  # 同步Docker启动时的密码，避免密码错误
    virtual-host: /  # 默认
    listener:
      simple:
        acknowledge-mode: auto  # 自动确认
    template:
      retry:
        enabled: true  # 连接失败重试
```

### IDEA 一键部署启动参数（必须加）

`Run → Edit Configurations → Remote SSH → Program arguments`

```Plain Text

--spring.profiles.active=prod
```

**不加这个会读本地 dev 配置，连本地 MQ → 必然超时报错**

---

## 六、常见错误（含启动报错\+连接报错）

### 1\. 连接超时 /connection refused

- 京东云**安全组没开 5672**

- 服务器**防火墙没放行 5672**

- 项目配置的 IP / 端口写错

- RabbitMQ 容器未正常启动（按第三步排查）

### 2\. 403/PERMISSION\_DENIED

- 账号密码错误（确保与 Docker 启动命令、SpringBoot 配置一致）

- 没加 `\-\-spring\.profiles\.active=prod`，用了本地配置

### 3\. 启动后 Web 打不开

- 安全组 / 防火墙**没开 15672**

- 容器没启动成功：`docker logs rabbitmq` 看日志

---

## 七、终极排查命令（京东云服务器执行）

```bash

# 1. 测试自身端口通不通
telnet 127.0.0.1 5672
telnet 127.0.0.1 15672

# 2. 测试公网端口（本地电脑 cmd 执行）
telnet 京东云公网IP 5672

# 3. 看容器网络
docker exec rabbitmq ping 京东云内网IP

# 4. 查看 Docker 服务状态
systemctl status docker

# 5. 查看端口占用
netstat -tulpn | grep -E "5672|15672"
```

---

## 八、生产优化建议

1. **密码强度**：`admin\_123` 换成复杂密码（如 Admin@2026），避免弱密码泄露

2. **内网访问**：项目和 RabbitMQ 同 VPC，用**内网 IP**连接（更快、更安全）

3. **版本**：用 `3\.13\-management` 稳定版，避免最新版兼容性问题

4. **资源限制**：启动加 `\-\-memory 2G \-\-cpus 1` 防止占满服务器

---

需要我根据你的**京东云服务器 IP、实际报错日志**，直接生成一份可一键复制的 **报错修复命令 \+ 启动命令**吗？

> （注：文档部分内容可能由 AI 生成）
