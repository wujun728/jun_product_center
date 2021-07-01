# mk-teamwork-server

#### 介绍
mk-teamwork是任务协同项目管理系统，是在pear project 基础上的JAVA 实现 ，目前项目完成了主要流程，后续会继续完善 ，打造一个实用的协同工作平台。

#### 软件架构
JAVA 1.8
MYSQL 8
基于前后端分离架构，服务端主要技术：springboot 、jwt  前端主要是vue;

#### 安装教程

1.  下载代码、编译打包，部署后端服务
2.  部署前端服务
3.  安装数据库

前端地址：https://gitee.com/wulon/mk-teamwork-ui

#### 使用说明

1.演示地址：http://teamwork.mokingsoft.com

demo/123456

2.如果库运行时如果报 ONLY_FULL_GROUP_BY 相关错误，是由于mysql8的兼容配置引起，请做以下操作：

修改mysql配置文件，通过手动添加sql_mode的方式强制指定不需要ONLY_FULL_GROUP_BY属性，

my.cnf位于etc文件夹下，vim下光标移到最后，添加如下：

sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION

3.提供数据库teamwork0309_init.sql 无相关数据 默认账号admin/123456

#### 系统截图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0714/142642_ba78ff2a_132459.png "11.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0714/142654_6a430b66_132459.png "2.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0714/142743_59e7e773_132459.png "1.png")

#### 特此鸣谢

1.  感谢vilson，本项目是pearProject的JAVA版本，后续会保持与pearProject同步，继续完善
2.  pearProject 地址：https://gitee.com/vilson/vue-projectManage


#### 问题反馈

1、欢迎大家使用，目前版本，可学习，可商用，欢迎大家共同开发，共同完善。

2、技术交流群（仅技术交流）：855354961


#### 鼓励一下

![输入图片说明](https://images.gitee.com/uploads/images/2020/0714/144051_01ee1fb3_132459.png "22.png")
