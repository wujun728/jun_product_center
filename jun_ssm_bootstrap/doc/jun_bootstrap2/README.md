## SSM 基础技术架构平台





## 平台简介
   SpringMVC/Spring/Mybatis 基础技术架构平台，简称ssm。
   ssm是参考了多个优秀的开源项目，选用开源主流的技术框架整合封装成高性能、强安全性、易开发的开源基础框架平台。
   

## 技术选型

1、后端

* 核心框架：Spring Framework 4.0
* 持久层框架：MyBatis 3.2
* 权限框架：Apache Shiro 1.2
* 视图框架：Spring MVC 4.0
* 服务端验证：Hibernate Validator 5.1
* 数据库连接池：Alibaba Druid 1.0
* 缓存框架：Ehcache 2.6
* 日志管理：SLF4J 1.7、Log4j
* 验证码组件：jcaptcha
* 工具类：Apache Commons、Jackson 2.2、Dozer 5.3、POI 3.9

2、前端

* JS框架：jQuery 1.9。
* CSS框架：Twitter Bootstrap 2.3.1。
* 客户端验证：JQuery Validation Plugin 1.11。
* 富文本：UEditor
* 下拉选择框：jQuery Select2
* 美化下拉选择框： select2-bootstrap (github.com/select2/select2-bootstrap-theme)
* 树结构控件：jQuery zTree
* 日期控件： My97DatePicker


## 环境配置
    开发环境：JDK1.6+、Maven3.0+、MySql5+
    数据库：doc/db/脚本
    修改src\main\resources\application.properties文件中的数据库设置参数
    运行命令：mvn jetty:run 或者 mvn tomcat:run
    访问地址：http://localhost:9080/ssm
    登录帐号:超级管理员 admin/admin

## 更新日志

   v1.0
    # 加入EHcache缓存框架
    # 优化shiro登录认证用户携带更多信息
    # 优化springmvc访问url类REST风格
        /sys/user/list
        /sys/user/save
        /sys/user/update /sys/user/update/1
        /sys/user/delete/1
        /sys/user/view/1
    # 加入druid数据库连接池的监控
    # 实现全局异常日志控制
   	  
   v0.7
    # 加入开源验证码组件Jcaptcha组件，支持可配置化	
   	
   v0.6
    # 加入ztree
    # 实现权限管理功能，基于资源的权限管理(Resource-Based Access Control) 可以控制到按钮级别
    # 加入主流的权限框架shiro
        登录集成shiro
        实现session不依赖容器，支持可配置化
        实现记住我的登录，支持可配置化
   	 
   v0.5
    # 加入select2插件/select-bootstrap插件
   	
   v0.4
    # 加入jquery validation 校验框架   
   	
   v0.3
    # 加入前端UI框架bootstrap
         
   v0.2
    # 优化整合配置
    # 加入jquery等插件 
      
   v0.1
    # springmvc/spirng/mybatis 基础技术骨架

 