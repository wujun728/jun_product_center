# Docker image for boot-mongo-admin run
# VERSION 0.0.1
# Author: shuogesha
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER shuogesha <2596310607@qq.com>
# VOLUME 指定了临时文件目录为/data。
ARG workdir=/data
VOLUME ${workdir}
WORKDIR ${workdir}
# 将jar包添加到容器中并更名为app.jar
ADD target/boot-mongo-admin-0.0.1-SNAPSHOT.jar /app.jar
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone 

# 指定8080端口
EXPOSE 8080 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#docker build -t boot-mongo-admin . 