# Pear Admin Plugin

官方网站 : [官 网](http://www.pearadmin.com "在这里输入图片标题")

交流社区 : [交 流](https://jq.qq.com/?_wv=1027&k=5OdSmve)

#### 介绍

Pear Admin Boot / Pear Admin Cloud 插件服务包 ，以 Spring Boot Starter 形式，提供基础服务，遵循 Spring Boot 源码包的方式管理和扩展代码，目前已内置 Sequence ID 发号器，Document Swagger 文档 ，Dynamic DataSource 组件


#### 项目结构

1. pear-plugin-project  组件代码

      1. pear-plugin-common    公用代码
      2. pear-plugin-configure 自动配置
      3. pear-plugin-framework 基础实现
             
             1. pear-plugin-framework-sequence   发号器实现
             2. pear-plugin-framework-document   接口文档实现
             3. pear-plugin-framework-datasource 多数据源实现
      
      4. pear-plugin-starters  启动器包 
             
             1. pear-plugin-starter-sequence   发号器启动器
             2. pear-plugin-starter-datasource 数据源启动器
             3. pear-plugin-starter-document   接口文档启动器

2. pear-plugin-sample   组件实例

      1. pear-plugin-sample-sequence   自动发号器实例
      2. pear-plugin-sample-datasource 动态数据源实例
      3. pear-plugin-sample-document   接口文档实例


#### 下载

使用 Git 服务拉取代码

```
git clone https://gitee.com/Jmysy/Pear-Admin-Plugin.git

```
使用 Maven Install 命令安装代码到本地 Maven 库

```
maven install 

```

#### 使用

POM 文件 依赖引入


```
        <dependency>
            <groupId>com.pearadmin</groupId>
            <artifactId>pear-plugin-starter-sequence</artifactId>
            <version>0.0.2-SNAPSHOT</version>
        </dependency>

```

application.yml 相关配置

```
pear:
  # 组 件 列 表
  plugin:
    # 分 布 式 ID 组件
    sequence:
      # 机 器 ID
      worker-id: 1
      # 数 据 中 心
      data-center-id: 1
      # 最 小 闲 置
      min-idle: 100
      # 初 始 化 数 量
      init-size: 70000

```

业务使用

```


    @Resource
    private SequencePool sequencePool;

    sequencePool.getId();  // 生产 ID 

    sequencePool.getId(1000) // 生产 100 个ID

```

在 Sequence Id 组件中，效仿数据库连接池，使用资源池设计模式来提高 Sequence Id 的 生产 和 预生产 能力，提高在并发环境下的效率

更多组件的使用方式，参考 Pear-Plugin-Sample 目录


