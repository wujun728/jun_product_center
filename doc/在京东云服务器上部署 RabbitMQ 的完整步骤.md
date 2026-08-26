# 在京东云服务器上部署 RabbitMQ 的完整步骤

在京东云服务器上用 Docker 部署 RabbitMQ，核心是**镜像选择、端口映射、安全组放行、数据持久化**。结合你之前 IDEA 一键部署后报 MQ 错误的场景，下面给你**京东云专属、可直接复制**的完整步骤。

---

## 一、京东云服务器准备（先做这 3 步）

### 1\. 安装 Docker（已装跳过）

```bash
# 安装依赖
yum install -y yum-utils device-mapper-persistent-data lvm2
# 添加 Docker 源
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# 安装并启动
yum install -y docker-ce docker-ce-cli containerd.io
systemctl start docker && systemctl enable docker
```

### 2\. 京东云安全组开放端口（**最关键！否则连不上**）

登录 **京东云控制台 → 云主机 CVM → 安全组 → 管理实例**：
新增 **入方向规则**，放行 2 个端口：

- **5672**：AMQP 客户端通信（SpringBoot 连接用）

- **15672**：Web 管理界面

- 协议：TCP

- 源地址：\[0\.0\.0\.0/0\]\(0\.0\.0\.0/0\)（或仅放你的项目服务器 IP）

### 3\. 服务器防火墙放行（CentOS 7\+）

```bash
firewall-cmd --zone=public --add-port=5672/tcp --permanent
firewall-cmd --zone=public --add-port=15672/tcp --permanent
firewall-cmd --reload
```

---

## 二、Docker 一键启动 RabbitMQ（京东云推荐）

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

### 3\. 启动命令（直接复制运行）

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
  
  
  # latest RabbitMQ 3.13
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management


# 拉取镜像
docker pull rabbitmq:3.13-management

# -d 参数：后台运行 Docker 容器
# --name 参数：设置容器名称
# -p 参数：映射端口号，格式是“宿主机端口号:容器内端口号”。5672供客户端程序访问，15672供后台管理界面访问
# -v 参数：卷映射目录
# -e 参数：设置容器内的环境变量，这里我们设置了登录RabbitMQ管理后台的默认用户和密码
docker run -d \
--name rabbitmq \
-p 5672:5672 \
-p 15672:15672 \
-v rabbitmq-plugin:/plugins \
-e RABBITMQ_DEFAULT_USER=admin \
-e RABBITMQ_DEFAULT_PASS=123456 \
rabbitmq:3.13-management
```

- **\-\-hostname**：必须固定，否则重启后节点名变、数据丢失

- **5672**：项目连接端口

- **15672**：Web 管理端口

- **RABBITMQ\_DEFAULT\_USER/PASS**：管理员账号（生产务必改强密码）

### 4\. 验证启动

```bash
# 看容器状态（Up 表示正常）
docker ps | grep rabbitmq

# 看日志（无 ERROR 即可）
docker logs rabbitmq

# 本地测试端口连通
telnet 127.0.0.1 5672
```

---

## 三、Web 管理界面访问

浏览器打开：

```Plain Text
http://京东云服务器公网IP:15672
```

- 账号：`admin`

- 密码：`jdcloud\_123`（你启动时设置的）

---

## 四、SpringBoot 连接配置（解决你之前的 MQ 报错）

### application\-prod\.yml（服务器环境）

```yaml
spring:
  rabbitmq:
    host: 京东云服务器内网IP/公网IP
    port: 5672
    username: admin
    password: jdcloud_123
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

## 五、常见错误（你之前遇到的）

### 1\. 连接超时 /connection refused

- 京东云**安全组没开 5672**

- 服务器**防火墙没放行 5672**

- 项目配置的 IP / 端口写错

### 2\. 403/PERMISSION\_DENIED

- 账号密码错误

- 没加 `\-\-spring\.profiles\.active=prod`，用了本地配置

### 3\. 启动后 Web 打不开

- 安全组 / 防火墙**没开 15672**

- 容器没启动成功：`docker logs rabbitmq` 看日志

---

## 六、终极排查命令（京东云服务器执行）

```bash
# 1. 测试自身端口通不通
telnet 127.0.0.1 5672
telnet 127.0.0.1 15672

# 2. 测试公网端口（本地电脑 cmd 执行）
telnet 京东云公网IP 5672

# 3. 看容器网络
docker exec rabbitmq ping 京东云内网IP
```

---

## 七、生产优化建议

1. **密码强度**：`jdcloud\_123` 换成复杂密码

2. **内网访问**：项目和 RabbitMQ 同 VPC，用**内网 IP**连接（更快、更安全）

3. **版本**：用 `3\.13\-management` 稳定版，避免最新版兼容性问题

4. **资源限制**：启动加 `\-\-memory 2G \-\-cpus 1` 防止占满服务器

---

需要我根据你的**京东云服务器 IP、账号密码**，直接生成一份可一键复制的 **docker run 命令 \+ SpringBoot yml 配置**吗？

> （注：文档部分内容可能由 AI 生成）
