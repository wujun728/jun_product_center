# 设置基础镜像
FROM nginx
# 定义作者
MAINTAINER zhy
# 将dist文件中的内容复制到 /usr/share/nginx/html/ 这个目录下面
COPY dist/  /usr/share/nginx/html/
COPY default.conf /etc/nginx/conf.d/default.conf
RUN echo 'echo init ok!!'

#docker build -t boot-mongo-admin-nginx .
