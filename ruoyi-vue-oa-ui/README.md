## 说明
* 本项目前、后端代码全开源，没有任何保留，没有区分免费版、商业版或其他付费版；
* 遵循MIT开源协议，100%免费使用；
* 后端代码：[RuoYi-Vue-OA](https://gitee.com/OpenJJ/ruoyi-vue-oa)。
* github仓库：[github](https://github.com/OnlyJJ/RuoYi-Vue-OA-UI)

如果本项目能对你有所帮助，请记得 ⭐️ **Star** ⭐️ 一下，这是对作者最好的鼓励和支持。

## 系统演示
* 账号：test/test123
* 地址：[在线演示](http://175.178.78.162)

通过test账号登录后，在系统管理 -> 用户管理下，可查看默认创建的、用于演示的账号，密码都是test123（为了保持良好的演示环境，演示系统每天会重置数据）。

## 平台简介

本项目是一款比较完善的OA系统，页面美观、实用，非常适合作为企业级的协同办公系统使用。演示系统中的所有功能，代码完全涵盖，开箱即用。

* 后端使用多模块方式，采用Spring Boot、Spring Security、MyBatis、Redis、Redisson、Minio、RabbitMQ、Netty;
* 权限认证使用 Spring Security & Token & Redis，支持多终端认证系统；
* 使用 Netty 实现Socket Server，用于实时刷新待办、消息通知；
* 使用 Minio 做文件服务，统一处理文件；
* 使用 RabbitMQ 做异步任务消费，提升性能；
* 数据库支持MySQL、Oracle、PostgreSQL、SQL Server、MariaDB、国产达梦 DM 等；
* 集成腾讯云、阿里云短信，可自由配置流程审批时，触发短信通知；
* 流程引擎使用Flowable，去除多余表的同时，还做了较多优化，使用上更丝滑；
* 支持动态表单自定义，实现了基本常用的表单组件，极大满足表单使用；
* 支持流程在线设计，提供通用的参数配置，以满足复杂场景的流程定制；
* 支持加载动态权限菜单，按钮级别权限控制；
* 支持动态配置正文、附件，并且支持offic正文在线盖章；
* 当前官方版本：[RuoYi-Vue 3.9.0](https://gitee.com/y_project/RuoYi-Vue)，后续保持同步更新。

## 功能简介

1.  业务模块：个人事项（待办、已办）、流程管理、资讯公告、日程管理、知识库、通讯录、编号管理、运维管理；
2.  系统配置：个人设置、基础设置；
3.  内置功能：系统管理、系统监控、系统工具。

具体功能可查看下方图片或 [ _在线体验_ ](http://175.178.78.162) 。



## 演示图

<table>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/login.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/shouye.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/template.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/newstart.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo1.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo3.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/form2.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo2.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo4.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo5.png"/></td>
    </tr>	 
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo6.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo7.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo6.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo7.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/todo8.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/folw-complate.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/seal.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/flow2.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/topic.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/topic2.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/notic.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/news.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/schedule.png"/></td>
        <td><img src="https://gitee.com/OpenJJ/imgs/raw/master/images/contact.png"/></td>
    </tr>
</table>


## 启动说明

* 默认的快速启动仅系统基础功能；
* 完整启动请加入星球后获取启动文档、使用帮助。
（加入星球后，除了获得保姆级的超详细文档外，还可以获得作者提供的其他帮助）
<img src="https://gitee.com/OpenJJ/imgs/raw/master/images/zsxq.jpg"/>


## 其他

* 为了长久、稳定的维护项目的发展，欢迎加入星球，给我们动力；
* 本项目不存在任何其他费用，请勿相信打着任何名义的付费“帮助”；
* 如果你还需要我们提供其他帮助，欢迎加入下面的QQ群，联系群主。


QQ群：  **463654560** 
