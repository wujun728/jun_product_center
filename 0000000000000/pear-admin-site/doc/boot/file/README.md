## 项目结构  :id=config


```
Pear Admin Boot
│
├─annex  项目SQL文件
│
├─pear-common 公共模块
│  │
│  ├─config 框架集成配置
│  │
│  ├─constant 通用常量
│  │
│  ├─exception 异常处理
│  │
│  ├─plugins 封装组件
│  │
│  ├─tools 工具类
│  │
│  └─web WEB 处理封装
│
├─pear-entrance 启动模块
│  │
│  ├─api 通用接口
│  │
│  ├─secure 安全框架
│  │
│  └─EntranceApplication 启动类
│
├─pear-entrance 启动模块
│  │
│  ├─static 静态资源
│  │
│  ├─templates 页面文件
│  │
│  ├─logback-spring.xml 日志配置
│  │
│  ├─application-dev.yml 开发环境配置
│  │
│  ├─application-prod.yml 线上环境配置
│  │
│  ├─application-test.yml 测试环境配置
│  │
│  └─application.yml 配置文件
│
├─pear-modules 业务模块
│  │
│  ├─pear-generator 代码生成
│  │
│  ├─pear-system 系统业务
│  │
│  ├─pear-schedule 定时任务
│  │
│  └─pear-process 工作流程
│  
└─pom.xml  Maven 配置

```