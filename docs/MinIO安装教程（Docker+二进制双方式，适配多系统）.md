# MinIO安装教程（Docker\+二进制双方式，适配多系统）

MinIO 是一款高性能、兼容 S3 协议的分布式对象存储系统，适用于私有云、容器、开发测试等多种场景，支持 Windows、Linux、macOS 及 Docker 部署。本教程涵盖最常用的 **Docker 部署（推荐，跨平台）** 和 **Linux 二进制部署（生产首选）**，步骤简洁可直接复制执行，同时包含启动验证、控制台访问及基础配置。

## 一、前置准备

### 1\. 环境要求

- Docker 方式：已安装 Docker（版本≥18\.09），确保 Docker 服务正常运行

- 二进制方式（Linux）：CentOS 7\+/Ubuntu 18\.04\+，内核≥3\.10，具备网络连接（用于下载安装包）

- 通用要求：开放 9000 端口（API 端口）和 9001 端口（控制台端口），避免端口占用

### 2\. 权限说明

Linux 系统需使用 root 权限或 sudo 权限执行命令；Windows 系统需以管理员身份打开 PowerShell/CMD；Docker 部署需确保当前用户拥有 Docker 操作权限。

## 二、Docker 部署（推荐，简单高效）

### 步骤1：拉取 MinIO 镜像

使用官方镜像，确保镜像完整性和安全性，避免第三方镜像风险：

```bash

docker pull minio/minio
# 验证镜像拉取成功
docker images | grep minio
    
```

### 步骤2：创建本地数据目录（持久化存储）

为避免容器删除后数据丢失，需将容器内数据目录挂载到本地，不同系统目录建议如下：

- Linux/MacOS：/data/minio

- Windows：D:\\MinIO\-Data（可自定义路径）

```bash

# 创建本地数据目录
mkdir -p /data/minio
# 赋予目录读写权限
chmod -R 755 /data/minio
   
```

```powershell

# 导航到目标盘并创建目录
cd D:
mkdir MinIO-Data
    
```

### 步骤3：启动 MinIO 容器

启动命令统一指定 API 端口（9000）和控制台端口（9001），设置管理员账号密码（密码长度≥8位），并挂载本地目录实现数据持久化：

```bash

docker run -d \
  --name minio \
  --restart=always \
  -p 9000:9000 \
  -p 9001:9001 \
  -e MINIO_ROOT_USER=admin \
  -e MINIO_ROOT_PASSWORD=Admin@2026 \
  -v /data/minio:/data \
  minio/minio server /data --console-address ":9001"
    
```

```powershell

docker run -d `
  --name minio `
  --restart=always `
  -p 9000:9000 `
  -p 9001:9001 `
  -e MINIO_ROOT_USER=admin `
  -e MINIO_ROOT_PASSWORD=Admin@2026 `
  -v D:\MinIO-Data:/data `
  minio/minio server /data --console-address ":9001"
    
```

命令参数说明：

- \-d：后台运行容器

- \-\-name minio：指定容器名称为 minio，便于后续管理

- \-\-restart=always：容器开机自启，异常退出时自动重启

- \-p 9000:9000：映射 API 端口，用于程序调用

- \-p 9001:9001：映射控制台端口，用于网页管理

- \-e MINIO\_ROOT\_USER：设置管理员账号

- \-e MINIO\_ROOT\_PASSWORD：设置管理员密码（生产环境建议使用复杂密码）

- \-v 本地目录:/data：挂载本地目录到容器内数据目录，实现数据持久化

### 步骤4：验证容器启动成功

```bash

# 查看容器是否正常运行
docker ps | grep minio
# 查看容器日志，确认启动无报错
docker logs minio
    
```

若日志中出现 “API: http://xxx:9000” 和 “Console: http://xxx:9001” 字样，说明启动成功。


firewall-cmd --zone=public --add-port=9000/tcp --permanent
firewall-cmd --zone=public --add-port=9001/tcp --permanent
firewall-cmd --reload

## 三、Linux 二进制部署（生产环境首选）

### 步骤1：下载 MinIO 二进制安装包

使用 wget 或 curl 下载官方二进制包，确保文件完整性，可选择最新稳定版或指定历史版本：

```bash

# 下载最新版 MinIO 二进制包（Linux amd64）
wget https://dl.min.io/server/minio/release/linux-amd64/minio
# 若 wget 不可用，使用 curl 下载
# curl -O https://dl.min.io/server/minio/release/linux-amd64/minio

# 赋予执行权限
chmod +x minio

# 移动到系统全局可执行目录，便于任意路径调用
sudo mv minio /usr/local/bin/

# 验证安装（查看版本）
minio --version
```

可选：下载历史版本（解决最新版控制台无法设置桶权限问题）：

```bash

wget https://dl.min.io/server/minio/release/linux-amd64/archive/minio.RELEASE.2024-06-15T19-57-03Z -O minio
    
```

### 步骤2：创建专用用户及数据目录

为提升安全性，创建专用系统用户运行 MinIO，避免使用 root 用户直接启动：

```bash

# 创建不可登录的系统用户 minio（专门用于运行 MinIO 服务）
sudo useradd -r -s /bin/false minio

# 创建数据存储目录（推荐挂载外部存储，此处以 /mnt/minio_data 为例）
sudo mkdir -p /mnt/minio_data

# 赋予目录所有权给 minio 用户，确保权限正常
sudo chown -R minio:minio /mnt/minio_data
    
```

### 步骤3：配置系统服务（开机自启）

创建 systemd 服务文件，实现 MinIO 开机自启、异常重启，配置管理员账号密码及启动参数：

```bash

sudo vi /etc/systemd/system/minio.service
```

粘贴以下内容（可根据实际路径和账号密码修改）：

```ini

[Unit]
Description=MinIO Object Storage
After=network.target
Documentation=https://min.io/docs/minio/linux/index.html

[Service]
Type=simple
User=minio
Group=minio
Environment="MINIO_ROOT_USER=admin"
Environment="MINIO_ROOT_PASSWORD=Admin@2026"
Environment="MINIO_MEMORY_LIMIT=128M"
ExecStart=/usr/local/bin/minio server /mnt/minio_data \
  --address ":9000" \
  --console-address ":9001" \
  --quiet
Restart=always
LimitNOFILE=65536
MemoryLimit=128M

[Install]
WantedBy=multi-user.target
    
```

### 步骤4：启动 MinIO 服务并设置开机自启

```bash

# 重新加载 systemd 服务配置
sudo systemctl daemon-reload

# 启动 MinIO 服务
sudo systemctl start minio.service

# 设置开机自启
sudo systemctl enable minio.service

# 查看服务运行状态（确认无报错）
sudo systemctl status minio.service
    
```

若状态显示 “active \(running\)”，说明服务启动成功；若启动失败，可通过 `journalctl \-f \-u minio\.service` 查看详细报错日志。

## 四、Windows 二进制部署

### 步骤1：下载 MinIO 安装包

访问 MinIO 官方下载地址：[dl\.min\.io/server/minio/release/windows\-amd64/minio\.exe](https://dl.min.io/server/minio/release/windows-amd64/minio.exe)，下载 minio\.exe 文件。

将下载的 minio\.exe 放入专门目录（如 C:\\MinIO），便于管理。

### 步骤2：启动 MinIO 服务

以管理员身份打开 PowerShell，导航到 minio\.exe 所在目录，执行启动命令（指定数据存储目录）：

```powershell

# 导航到 MinIO 目录
cd C:\MinIO

# 启动 MinIO，指定数据目录为 D:\MinIO-Data，控制台端口 9001
.\minio.exe server D:\MinIO-Data --console-address ":9001"
    
```

可选：设置 Windows 自启动（使用 NSSM 工具），具体步骤如下：

1. 下载 NSSM 工具：[nssm\.cc/download](https://nssm.cc/download)，解压后进入 win64 目录

2. 以管理员身份打开 PowerShell，导航到 NSSM 目录，执行 `\.\\nssm\.exe install MinIO\-Server`

3. 在弹出的图形界面中，配置 Path 为 minio\.exe 完整路径，Startup directory 为 minio\.exe 所在目录，点击确定即可完成自启动配置

## 五、控制台访问与基础配置

### 1\. 控制台访问

启动成功后，打开浏览器，访问控制台地址：`http://服务器IP:9001`（本地部署可使用 `http://localhost:9001`）。

使用设置的管理员账号密码登录（默认账号：minioadmin，默认密码：minioadmin；自定义账号密码则使用配置的账号密码）。

### 2\. 基础配置（必做）

1. 创建 Bucket（存储桶）：登录控制台后，点击左侧「Buckets」→「Create Bucket」，输入桶名（必须小写，不能含下划线），点击创建即可。

2. 创建访问密钥：点击左侧「Access Keys」→「Create Access Key」，生成新的 Access Key 和 Secret Key，用于程序调用 MinIO API，建议保存好密钥信息。

3. 设置桶权限（可选）：最新版控制台不支持直接设置桶权限，可通过 MinIO 客户端（mc）设置，具体操作见下文。

## 六、MinIO 客户端（mc）安装与常用操作

### 1\. 客户端安装（Linux）

```bash

# 下载 mc 客户端二进制包
wget https://dl.min.io/client/mc/release/linux-amd64/mc

# 赋予执行权限
chmod +x mc

# 移动到全局可执行目录
sudo mv mc /usr/local/bin/

# 验证安装
mc --version

```

### 2\. 常用客户端命令

以下命令需先添加 MinIO 服务别名，再执行相关操作（以本地部署为例）：

```bash

# 添加 MinIO 服务别名（别名：myminio，地址：本地9000端口，账号密码对应配置）
mc alias set myminio http://localhost:9000 admin Admin@2026

# 创建存储桶（例：创建名为 my-bucket 的桶）
mc mb myminio/my-bucket

# 列出所有存储桶
mc ls myminio

# 上传本地文件到存储桶
mc cp ./test.txt myminio/my-bucket/

# 下载存储桶文件到本地
mc cp myminio/my-bucket/test.txt ./

# 设置桶为公共只读（解决最新版控制台无法设置权限问题）
mc policy set download myminio/my-bucket

# 查看桶权限
mc policy get myminio/my-bucket

# 查看 MinIO 服务信息
mc admin info myminio
    
```

## 七、常见问题排查

### 1\. 端口占用报错

提示 “address already in use”，解决方法：查看 9000/9001 端口占用情况，停止占用端口的进程，或修改 MinIO 端口（启动时指定 \-\-address 和 \-\-console\-address）。

```bash

# Linux 查看端口占用
netstat -tulnp | grep -E "9000|9001"
# 停止占用进程（根据进程ID，替换 xxx 为实际PID）
kill -9 xxx
    
```

### 2\. 数据目录权限不足

提示 “Permission denied”，解决方法：修改数据目录所有权和权限，确保 MinIO 运行用户有读写权限。

```bash

# Linux 系统
sudo chown -R minio:minio /mnt/minio_data
sudo chmod -R 755 /mnt/minio_data
    
```

### 3\. 控制台无法访问

排查要点：① 确认 MinIO 服务正常运行；② 检查服务器防火墙/安全组是否开放 9001 端口；③ 本地部署使用 localhost，远程部署使用服务器公网IP；④ 检查启动命令中是否指定 \-\-console\-address \&\#34;:9001\&\#34;。

### 4\. 最新版控制台无法设置桶权限

解决方法：① 安装历史版本 MinIO；② 使用 mc 客户端执行 `mc policy set download myminio/my\-bucket` 设置桶权限（公共只读）。

## 八、生产环境优化建议

- 密码安全：修改默认管理员密码，使用复杂密码（含大小写、数字、特殊符号），定期更换访问密钥。

- 数据备份：定期使用 mc mirror 命令同步存储桶数据，实现增量备份。

- 资源限制：生产环境中配置 MemoryLimit 和 CPU 限制，避免 MinIO 占用过多服务器资源。

- 安全防护：配置 HTTPS 加密访问，结合 Nginx 反向代理，限制控制台访问IP。

- 版本选择：使用稳定版 MinIO，避免使用最新测试版，减少兼容性问题。

> （注：文档部分内容可能由 AI 生成）
