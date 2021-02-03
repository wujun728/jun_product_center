### SYpro Demo

=====================================================

演示了Spring4 + Spring mvc + Hibernate4 + Maven 的整合使用<br/>
这是一个基础框架，内部已经实现了泛型的增删改查、预先抓取(可以抓取多级对象)、动态条件查询(QueryFilter)等功能<br/>
具体请运行起来，并查看index.jsp示例索引<br/>
使用这个框架做后台，会大大降低你的代码量<br/>
此项目有代码生成工具类，可以直接生成dao、doaImpl、service、ServiceImpl、controller类，基本上整个项目你只需要自己写一下model和JSP就可以了<br/>
代码自动生成使用[Beetl](http://ibeetl.com/community/?/explore/)<br/>
没有特殊需求，后台不需要你编写一行代码！！<br/>
如果你会使用hibernate tools或者myeclipse的话，model也可以通过数据库反向生成，整个项目你只需要让美工写页面就行了<br/>
项目包含了通用的Controller方法日志，使用AOP拦截，拦截了所有方法，能知道谁(IP)在什么时候，访问了什么类的什么方法，传递的参数是什么，返回了什么值<br/>
项目使用拦截器做权限控制，可以控制到任何url、任何按钮、菜单，保障你的项目安全<br/>
项目使用javamelody监控dao、service、controller和系统的状况<br/>
项目使用druid监控数据源信息<br/>

------------------------------------------------------
  
### 部署说明

> [下载部署说明](http://git.oschina.net/sphsyv/sypro/blob/master/DeploymentInstructions.docx)<br/>

--------------------------------------------------------
### 二次开发手册

> 由于项目进行了模块划分，分多个module了，所以二次开发手册得重新编写，下面这个仅供参考，目前有很多截图都不对，我有空再重新写一份。。

>> 待完善<br/>
  

