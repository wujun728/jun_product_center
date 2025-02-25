# jun_springboot_admin_layui

## 简介
jun_springboot_admin_layui 是一个基于Layui + Spring Boot & MyBatis的API接口服务项目，
项目使用Spring boot2.0+shiro+redis+mybatis架构，可以适用 前后端分离项目后台或者APP接口后台，使用了Shiro-Redis实现分布式Session共享
基于当前项目，可快速构建中小型API、RESTful API接口，项目含有代码生成器，单表标接口一键生成
该项目已经有过多个真实项目的实践，稳定、简单、快速，让码农脱离重复劳动，专注于业务代码的编写，减少加班。


- Swagger地址：http://localhost:8080/swagger-ui.html
- 登录地址：localhost:8080/api/user/login?username=admin&password=admin
- 接口地址：localhost:8080/api/public/company/list
- session超时，30分；



### 使用框架如下：
* 核心框架：spring boot 2.1.6
* 持久层框架：mybatis
* 数据库连接池：alibaba druid
* 安全框架：apache shiro
* 缓存框架：redis(自定义 RedisTemplate 序列化)
* 日志框架：logback
* 接口文档：swagger 2.9.2
* 接口文档：Fastjson 
* MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
* MyBatisb通用Mapper插件（[查看官方中文文档](https://mapperhelper.github.io/docs/)）
* MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）
* 其他略

### 功能截图：
- 一键生成表接口测试
![](https://github.com/wujun728/jun_springboot_admin_layui/blob/main/jun_springboot_api/doc/images/1.png) 
- 登录接口测试
![](https://github.com/wujun728/jun_springboot_admin_layui/blob/main/jun_springboot_api/doc/images/2.png) 
- swagger接口清单
![](https://github.com/wujun728/jun_springboot_admin_layui/blob/main/jun_springboot_api/doc/images/3.png) 


C:\Windows\System32\drivers\etc\hosts文件中添加域名：   
* qixing.fly666.cn qixing.hbqxcpa.cn
* api.qixing.fly666.cn api.qixing.hbqxcpa.cn
127.0.0.1           qixing.fly666.cn
127.0.0.1           qixing.hbqxcpa.cn
127.0.0.1           api.qixing.fly666.cn
127.0.0.1           api.qixing.hbqxcpa.cn


 ### 特征&提供
- 最佳实践的项目结构、配置文件、精简的POM
- 统一响应结果封装及生成工具
- 统一异常处理
- 简单的接口签名认证
- 常用基础方法抽象封装
- 使用Druid Spring Boot Starter 集成Druid数据库连接池与监控
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度
- 集成MyBatis、通用Mapper插件、PageHelper分页插件，实现单表业务零SQL
- 使用SpringFox-Swagger2管理API文档
- 使用lombok简化POJO
- 提供代码生成器根据表名生成对应的Model、Mapper、MapperXML、Service、ServiceImpl、Controller等基础代码，
- 其中Controller模板默认提供POST和RESTful两套，根据需求在```CodeGenerator.genController(tableName)```方法中自己选择，默认使用POST模板。
- 代码模板可根据实际项目的需求来扩展，由于每个公司业务都不太一样，所以只提供了一些比较基础、通用的模板，**主要是提供一个思路**来减少重复代码的编写，我在实际项目的使用中，其实根据公司业务的抽象编写了大量的模板。另外，使用模板也有助于保持团队代码风格的统一

### 快速开始
1. 下载项目
2. 对```test```包内的代码生成器```CodeGenerator```进行配置，主要是JDBC，因为要根据表名来生成代码
3. 如果只是想根据上面的演示来亲自试试的话可以使用```test resources```目录下的```sys_user.sql```，否则忽略该步
3. 输入表名，运行```CodeGenerator.main()```方法，生成基础代码（可能需要刷新项目目录才会出来）
4. 根据业务在基础代码上进行扩展
5. 对开发环境配置文件```application-dev.properties```进行配置，启动项目！

### 开发建议
- 表名，建议使用小写，多个单词使用下划线拼接
- Model内成员变量建议与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加```@Transient```注解，详情见[通用Mapper插件文档说明](https://mapperhelper.github.io/docs/2.use/)
- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出
- 需要工具类的话建议先从```apache-commons-*```和```guava```中找，实在没有再造轮子或引入类库，尽量精简项目
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/alibaba/p3c))
- 建议在公司内部使用[SpringFox-Swagger2](https://github.com/springfox/springfox) 、[RAP](https://github.com/thx/RAP)等开源项目来编写、管理API文档

反射兼容问题
这是由于 JDK 8 中有关反射相关的功能自从 JDK 9 开始就已经被限制了，为了兼容原先的版本，需要在运行项目时添加 --add-opens java.base/java.lang=ALL-UNNAMED