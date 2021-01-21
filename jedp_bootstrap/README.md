此项目为java后台管理系统脚手架
基于lanyuan 3.0版做了精简
主要包括：
1、数据库表名
2、mybatis源码精简
3、命名修改
4、界面精简


lanyuan系统框架：
前端：bootstrap
后端：springmvc+spring+mybatis+shiro
登陆账号: admin/123456
技术要点：
1：此版本采用ajax+js分页,表格lyGrid分页插件是自己写的,有点模仿ligerui的分页实现 
2：列表的表头固定,兼容IE,firefox,google,360的浏览器,其他暂没有测试.
3：表格排序功能
4：弹窗采用贤心的插件，网址：http://sentsin.com/jquery/layer/
5：加入druid技术,对sql语句的监控.
6：自定义注解导出excel
7：使用了ehcache缓存机制
8：新增支持oracle分页实现
9：新增支持SQLserver2008分页实现
10：解决分页参数没法传到后台的问题
11：异常统一处理
12：采用了shiro权限机制
13：封装好baseSerive,baseSeriveImpl,baseMapper..服务层，持久层统一调用


ui:http://themeforest.net/item/notebook-web-app-and-admin-template/6228450