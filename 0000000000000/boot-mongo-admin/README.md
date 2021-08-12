# SpringBoot2+Vue后台管理系统+mongodb4.0

#### 介绍
基于SpringBoot的后台管理系统，后台框架采用ElementUi实现了系统权限、动态菜单，用户权限，数据字典等基础功能。
[QQ群号：
671994939 ](https://qm.qq.com/cgi-bin/qm/qr?k=JIn9_m9wpia-5w5m-IzAVyMMWsvKsLQE&jump_from=webapi)

#### 软件架构
1. ---java目录为java接口的核心代码，需要maven+jdk8以上环境
2. ---vue-admin目录为后台管理界面的核心代码，需要node14和yarn环境

#### 相关开发工具

springtoolSute4、visual studio code
![输入图片说明](https://images.gitee.com/uploads/images/2021/0609/150659_62a381d2_862431.png "截屏2021-06-09 下午2.53.11.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0609/150737_36c98fe6_862431.png "截屏2021-06-09 下午3.07.28.png")
#### 安装教程

1.  拉取mongo的官方镜像
###### docker pull mongo:4
2.  还原mongo数据库
###### mongorestore -d boot-mongo-admin 数据库目录地址
3.  java项目打包docker项目
项目工程选择pom.xml运行，然后在docker目录执行docker打包如：docker build -t boot-mongo-admin . 

#### Docker运行环境
1.  Mongo4.0的镜像

docker run -d --name mongo -p 27017:27017 shuogesha/boot-mongo

2.  java接口镜像

docker run -d -p 8080:8080 --name  boot-mongo-admin shuogesha/boot-mongo-admin

3.  后台界面

docker run -d -p 18080:80 --name  boot-mongo-admin-nginx shuogesha/boot-mongo-admin-nginx


#### 使用说明

1.  常规的菜单权限和定时任务
2.  各终端项目打包image镜像
3.  

#### 参与贡献


 