# inspinia_admin_java_ssm
inspinia_admin_Java(后台管理系统框架)
- 前端基于inspinia_admin-v2.7.1 及 VUE 框架;
- 后端为java基于SSM（Spring+SpringMVC+MyBatis）框架的maven项目
- 数据库使用MySql
## 项目预览截图
![项目截图](https://github.com/wangyushuai/inspinia_admin_java_ssm/raw/master/src/main/webapp/vendor/img/projectView.png)

## 技术亮点：
1. 使用了Mybatis逆向工程，自动生成mapper.xml 和 bean
2. 使用了Vue框架，做了母版页,提高视图代码复用率，每个页面专注于自己的业务逻辑

![项目截图](https://github.com/wangyushuai/inspinia_admin_java_ssm/raw/master/src/main/webapp/vendor/img/projectView_order.png)

```
<div id="_wrapper">
<!--主版页面组件-->
    <base-layout>
    <!--ibox组件-->
        <page-ibox title="订单管理" content="table content">
            <!--右侧自定义内容-->
        </page-ibox>
    </base-layout>
</div>
```

## 分支介绍
1. master 最新稳定版本
2. otherFun_wys 开发中版本

## 当前功能
*目前开发了4个Demo:*
- 分类管理
- 属性管理
- 产品管理
- 订单管理（使用VUE组件）

author： yushuai_w@163.com
