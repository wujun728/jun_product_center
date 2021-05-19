    大平台基础架构

    技术选型
    1、后端使用技术
        springframework4.3.7.RELEASE
        EhCache2.10.2
        mybatis3.4.1
        shiro1.3.2
        servlet3.1.0
        druid1.0.28
        slf4j1.7.19
        fastjson1.2.30
        velocity1.7
        quartz2.2.3
        mysql5.1.39
        swagger2.4
        
    前端使用技术
        Vue2.5.1
        iview
        layer3.0.3
        jquery2.2.4
        bootstrap3.3.7
        jqgrid5.1.1
        ztreev3.5.26

    platform-admin 
    后台管理

    platform-common 
    公共模块
    
    platform-framework 
    系统WEB合并模块
    
    platform-gen 
    代码生成

    platform-restful 
    提供REST风格接口

    platform-schedule 
    定时任务
    
    实现功能

    一：综合管理系统
        管理员列表
        角色管理
        部门管理
        菜单管理
        应用域管理
        文件上传
        系统参数
        系统日志
        
        SQL监控
        代码生成器
        swagger接口列表
        
        定时任务
        
        短信服务平台
            安全起见，需配置有效IP地址。ApiSmsController.VALID_IP
            然后配置短信平台账户信息
		    向外提供发送短信接口：
		        http://域名:端口/api/sendSms?mobile=13000000000,15209831990&content=发送的短信内容
		
		开发环境配置：
			platform-admin/src/main/resources/dev/log4j.properties
			platform-admin/src/main/resources/dev/platform.properties
		开发环境打包：
			项目根目录>mvn package
		
		生产环境配置：
			platform-admin/src/main/resources/prod/log4j.properties
			platform-admin/src/main/resources/prod/platform.properties
		生产环境打包：
			项目根目录>mvn package -P prod
		
		打包路径：
			platform-framework\target\platform-framework.war
		
		
    后台管理项目演示
    演示地址：http://fly2you.cn/platform-framework
    账号密码：admin/admin
    
    如何交流、反馈、参与贡献？
    官方QQ群：66502035
    git：https://gitee.com/fuyang_lipengjun/platform-framework
    GitHub：https://github.com/lipengjun92/platform
    如需获取项目最新源码，请Watch、Star项目，同时也是对项目最好的支持

	
### 登录页面
![](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180618/13484892802ad2.png "登录")
### 首页
![](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180618/134920465c011b.png "首页")
### 菜单管理
![](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180618/135042481d1b4e.png "菜单管理")
### 多系统切换
![](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180618/134937754189a3.png "多系统切换")
### 角色授权
![](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180618/13495698447ff5.png "角色授权")
### 短信发送
![](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180618/1350145323d71d.png "短信发送")
### 捐赠
![](https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180618/13505517181d30.png "捐赠")