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
<hr />
	 <%-- 项目是一个由Eclipse Kepler创建，Struts2.3.x+Spring3.2.x+Hibernate4.2.x+CXF2.7.x+EasyUI1.3.4+Maven架构的示例程序
	<br />DEMO框架环境需求：JAVA环境：JDK7+；数据库环境 /mysql5+；WEB容器环境：jetty6+/tomcat6+；编译环境：maven：3.x+ --%>
	复医凯泽信息管理系统 
	
	<hr />
	如果发现系统有BUG，请给我发Email:xiaojunzhou@allianture.com
	<hr> 版本研发历史 </hr>
	<hr />
	v20131213
	<ul>
		<li>调整系统框架</li>
		<li>调整系统权限结构</li>
	</ul>
	v20140220
	<ul>
		<li>系统功能基本开发完成</li>
	</ul>
	v20140225
	<ul>
		<li>第一轮bug测试修改后结果<a href="bugs/bug-20140220.mht">复医凯泽-系统测试bug-20140220.mht</a></li>
	</ul>
	 v20140227
	<ul>
		<li>第二轮bug测试修改后结果<a href="bugs/bug-20140227.mht">复医凯泽-系统测试bug-20140227.mht</a></li>
	</ul>
	 v20140312
	<ul>
		<li>第三轮bug测试修改后结果<a href="bugs/bug-20140227-3.mht">复医凯泽-系统测试bug-20140227-3.mht</a></li>
	</ul>
</body>
</html>