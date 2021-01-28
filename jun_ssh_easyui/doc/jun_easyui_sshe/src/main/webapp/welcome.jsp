<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎页面</title>
<jsp:include page="inc.jsp"></jsp:include>
</head>
<body>
	SSHE示例项目是一个由Eclipse Kepler创建，Struts2.3.x+Spring3.2.x+Hibernate4.2.x+CXF2.7.x+EasyUI1.3.4+Maven架构的示例程序
	<br /> 当前示例演示了包括：权限控制、超大附件文件上传、EasyUI基本组件使用等等功能，具体请自行看本示例演示功能
	<br />SSHE框架环境需求：JAVA环境：JDK7+；数据库环境：oracle10g+/sqlserver2000+/mysql5+；WEB容器环境：jetty6+/tomcat6+；编译环境：maven：3.x+
	<hr />
	<h1>
		<a href="http://pan.baidu.com/share/home?uk=1526873401#category/type=0" target="_blank">源码下载(里面有很多分享，请进入SSHE示例项目文件夹进行下载)</a>
	</h1>
	<a href="https://me.alipay.com/sypro" target="_blank"><img alt="捐助SSHE/SYPRO" src="<%=contextPath%>/style/images/alipay.jpg" /></a>如果您觉得我的源码对您有帮助的话，请给我更有利的支持吧^_^
	<hr />
	如果发现系统有BUG，请给我发Email:89333367@qq.com
	<hr />
	v20131023
	<ul>
		<li>增加Struts2 Action映射监控查看功能</li>
	</ul>
	<hr />
	v20131021
	<ul>
		<li>在sy.test包下，添加了POI读取excel和写入excel的小例子</li>
		<li>在sy.test包下，添加了apache的commons包下的lang3的一些常用工具示例</li>
		<li>在sy.test包下，添加了apache的commons包下的dbutils工具的一些常用示例</li>
	</ul>
	<hr />
	v20131018
	<ul>
		<li>升级Highcharts到3.0.6版本</li>
		<li>升级plupload到2.0.0版本</li>
		<li>升级jquery到2.0.3版本</li>
	</ul>
	<hr />
	v20131015
	<ul>
		<li>更换了oschina的maven仓库</li>
	</ul>
	<hr />
	v20131011
	<ul>
		<li>修正“我的信息”某些情况下会报空指针的问题</li>
	</ul>
	<hr />
	v20131010
	<ul>
		<li>添加了一个WebService(CXF)的示例，请点击<a target="_blank" href="<%=contextPath%>/ws">这里来查看已发布的服务</a></li>
		<li>更换了batik的JAR包为org.apache.xmlgraphics下的，解决了tomcat容器中，导出图表后台报错的BUG</li>
	</ul>
	<hr />
	v20131009
	<ul>
		<li>更新了jetty插件的JAR包为最新版本</li>
	</ul>
	<hr />
	v20131003
	<ul>
		<li>改变了所有编辑功能页面的等待提示z-index级别，避免页面没有加载完毕，就点击了编辑按钮会出错的BUG</li>
	</ul>
	<hr />
	v20131001
	<ul>
		<li>新增了combobox/combogrid的一个扩展，用于检验用户autocomplete后的值是否存在与下拉列表中，如果不存在则认为用户输入无效</li>
		<li>修正初始化数据库时，如果改动了资源或者机构的父节点，会初始化不成功的BUG</li>
	</ul>
	<hr />
	v20130930
	<ul>
		<li>新增了combobox的autocomplete功能，在首页登录页面演示</li>
		<li>新增了combogrid的自动补全功能，在首页登录页面演示</li>
		<li>其他细节修改</li>
		<li>一些BUG的修正</li>
		<li>升级了所有能升级的JAR包</li>
	</ul>
	<hr />
	v20130927
	<ul>
		<li>修正了角色管理，排序报错的问题</li>
		<li>修正角色分布图表中，导出PDF格式显示不正确的问题</li>
		<li>修正Highcharts导出JPEG图片时，后台报错的问题</li>
	</ul>
	<hr />
	v20130926
	<ul>
		<li>修改了File404Filter的判断方式，尽量提升响应速度</li>
		<li>修正了谷歌浏览器下，不能点击删除按钮的BUG</li>
		<li>修正了某些情况下init数据库失效的问题</li>
		<li>修正某些情况下机构删除出错的问题</li>
	</ul>
	<hr />
	v20130925
	<ul>
		<li>修改了inc.jsp，引入版本号概念，避免IE缓存导致样式或JS不更新导致错误</li>
		<li>tabs的tools添加文字提示</li>
		<li>添加了官方演示示例和API等链接</li>
		<li>更新了syExtCss.css样式</li>
		<li>其他小细节修改</li>
		<li>修改了上传文件的路径，便于部署项目后，上传的文件依然存在</li>
		<li>修正了west没有滚动条的BUG</li>
	</ul>
	<hr />
	v20130924
	<ul>
		<li>为图表功能增加了导出文件功能，可以导出为PNG/JPEG/PDF类型的文件</li>
		<li>初始化数据库添加synchronized修饰符</li>
		<li>修改默认日志级别</li>
		<li>增加了tabs的演示，tabs可以选择上下左右显示</li>
		<li>修正了几个页面的editable:false问题</li>
	</ul>
	<hr />
	v20130923
	<ul>
		<li>修正了syExtEasyUI.js扩展中，idField/textField/parentField的命名错误问题</li>
		<li>新增用户登录注销历史记录功能</li>
		<li>演示了用户增加session属性时的监听示例，记录了用户登录、注销的相关信息</li>
		<li>修正一些grid的显示问题</li>
		<li>升级了struts2的版本到2.3.15.2</li>
	</ul>
	<hr />
	v20130922
	<ul>
		<li>统计报表中，增加等待提示功能，避免用户看到白板</li>
		<li>修正初始化数据库的时候，超级管理员没有赋予所有机构，导致他不能给他人分配机构的BUG</li>
		<li>调节了一下grid中按钮显示的顺序</li>
		<li>修正了用户管理中，给用户选择机构的时候，一个显示问题的BUG</li>
	</ul>
	<hr />
	v20130921
	<ul>
		<li>修正，当添加资源时，url为空的时候，删除资源会发生删不掉的情况</li>
		<li>修正，当执行初始化数据库功能时，以前添加的资源、角色会看不到的情况</li>
		<li>默认配置了缓存静态内容、关闭了开发模式配置，如需改动请自行修改struts.xml的配置</li>
	</ul>
	<hr />
	v20130920
	<ul>
		<li>增加了一个过滤器，用于用户上传的文件丢失时，返回一个默认的图片</li>
		<li>修正了用户编辑和显示时，如果photo字段是空，会报错的问题</li>
		<li>新增一个用户登录、注销监听器，二次开发时可以利用这个监听器做某些事情</li>
		<li>优化了treegrid/datagrid的显示速度，增加了展开搜索刷新功能</li>
	</ul>
	<hr />
	v20130918
	<ul>
		<li>添加了一个饼图的示例</li>
		<li>更新了log4j的配置信息</li>
		<li>更新了pom.xml，目前最新JAR包依赖</li>
		<li>文件上传添加可配置上传路径参数</li>
	</ul>
	<hr />
	v20130916
	<ul>
		<li>修正用户管理中，新增用户可以重复登录名的BUG</li>
		<li>修正用户管理中，新增用户没有默认密码的BUG</li>
		<li>修正用户管理中，编辑用户可以重复登录名的BUG</li>
	</ul>
	<hr />
	v20130915
	<ul>
		<li>修正了文件上传后，回显路径不对的问题</li>
		<li>修正了IE浏览器下，选择上传文件后，看不到删除按钮的问题</li>
		<li>用户管理，增加了过滤条件功能</li>
		<li>更新了一些预设图标显示</li>
		<li>增加了一个图表示例</li>
	</ul>
	<hr />
	v20130914
	<ul>
		<li>重构了2012年SSHE示例的所有代码，使类和文件更加清晰，便于管理</li>
		<li>配置文件分离，不同功能放到不同的配置文件中</li>
		<li>新增了文件上传的功能，演示在用户添加/编辑中，真实上传文件进度条</li>
		<li>支持超大附件上传(理论上无限大附件)，演示系统有限制，请自行下载源码，修改其中配置；上传原理，利用plupload插件，将要上传的附件进行分块(分块大小取决于配置)；例如要上传一个1GB的附件，那么在上传的时候，plupload会自动进行文件切割，将1GB的文件分割成N小块，然后分批上传，避开了浏览器上传大附件的限制；最后全部上传成功后，在后台进行文件合并，将N块文件合并成正确的文件</li>
		<li>升级了easyui版本到1.3.4，支持IE6/7/8/9/10/火狐/谷歌等浏览器</li>
		<li>升级所有JAR包到最新</li>
		<li>使用了jetty插件的SSHE项目可单独运行，不需要发布到WEB容器</li>
		<li>尽可能的标注了更详细的注释</li>
		<li>新增了项目监控功能</li>
		<li>新增了机构管理</li>
		<li>权限架构：一个用户可以属于多个角色，一个用户也可以属于多个机构；一个角色可以访问多个资源，一个机构也可以访问多个资源；</li>
		<li>更新部署说明</li>
		<li>还有一些其他细节更新</li>
	</ul>
</body>
</html>