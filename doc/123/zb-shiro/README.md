# zb-shiro

#### 项目介绍
Springboot + shiro权限管理。最精简的shiro上手项目。<br/>
基于Spring Boot、Shiro、MyBatis、Redis、thymeleaf等框架，前端使用adminlte模板。<br/>
可以加[QQ群130512958](http://shang.qq.com/wpa/qunwpa?idkey=dad3420aea2111ee98653f703f61bc504bfcd6dd85d1766a344523d7e353ad43)交流技术！

项目预览：[admin.nbclass.com](http://admin.nbclass.com)
    音乐：[music.nbclass.com](http://music.nbclass.com)

**如果喜欢，请多多分享！！多多Star！！**

![JDK](https://img.shields.io/badge/JDK-1.8-green.svg)
![Maven](https://img.shields.io/badge/Maven-3.3.9-green.svg)
![MySQL](https://img.shields.io/badge/MySQL-5.6.24-green.svg)
![Redis](https://img.shields.io/badge/Redis-3.0.503-green.svg)
[![license](https://img.shields.io/badge/license-MIT-yellow.svg)](https://gitee.com/supperzh/zb-shiro/blob/master/LICENSE)

#### 使用说明

1. 使用IDE导入本项目
2. 新建数据库`CREATE DATABASE zb-shiro;`
3. 导入数据库`docs/db/shiro.sql`
4. 修改(`resources/application.yml`)配置文件
   1. 数据库链接属性(可搜索`datasource`或定位到L.15) 
   2. redis配置(可搜索`redis`或定位到L.28)
5. 运行项目(三种方式)
   1. 项目根目录下执行`mvn -X clean package -Dmaven.test.skip=true`编译打包，然后执行`java -jar zb-shiro/target/zb-shiro.jar`
   2. 项目根目录下执行`mvn springboot:run`
   3. 直接运行`ShiroBootApplication.java`
6. 浏览器访问`http://localhost:8081`

**用户密码**

_演示账号_： 账号：admin 密码：123456<br/>




#### 其他开源项目

[zplayer音乐播放器](https://gitee.com/supperzh/zplayer)

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 图片预览

![首页](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/workdest.png?v=1.0)
什么？你没看错，前端模板有很棒的主题风格切换功能！<br/>
![主题切换](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/control.png?v=1.0)
![用户管理](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/userlist.png?v=1.0)
![角色管理](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/rolelist.png?v=1.0)
![角色分配资源](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/assignpermission.png?v=1.0)
![资源管理](https://gitee.com/supperzh/zb-shiro/raw/master/docs/img/permissionlist.png?v=1.0)


