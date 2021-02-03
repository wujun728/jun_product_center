<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<title></title>
</head>
<body>

	<div>
		<h1>
			示例列表(<strong>请按顺序查看</strong>，以免后面的功能没有数据体现)
		</h1>
	</div>

	<ol>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/security.jsp"><h1>演示权限拦截(先执行这个啊，否则没权限看下面所有例子)</h1></a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/init.jsp"><h1>初始化一些测试数据</h1></a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/generator.jsp"><h2>代码生成工具</h2></a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/saveUser.jsp">增加一个用户(name=xxx方式传递参数)</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/saveUser2.jsp">级联插入用户的其他对象类型属性(company.id=xxx方式传递参数)</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/saveUser3.jsp">增加一个用户(级联插入用户的角色信息)</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/getUser.jsp">查找一个用户</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findUser.jsp">查找一批用户</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findUserByPage.jsp">查找一批用户，带分页，带排序</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findUserByPageAndFilter.jsp">查找一批用户，带分页，带排序，带动态查询条件(string类型)</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findUserByPageAndFilter2.jsp">查找一批用户，带分页，带排序，带动态查询条件(short类型)</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findUserByPageAndFilter3.jsp">查找一批用户，带分页，带排序，带动态查询条件(多字段过滤)</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findCByJoinFetch.jsp">多对一、多级预先抓取查询,解决N+1问题</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findCByJoinFetch2.jsp">多对多、预先抓取查询,解决N+1问题</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findUserByJoinFetch.jsp">预先抓取，并且增加查询条件</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/upload.jsp">文件上传</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/sql.jsp">sql语句的使用示例</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/sql2.jsp">sql语句的使用示例2(可以使用QueryFilter)</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/getUser2.jsp">查找一个用户(返回自定义格式的JSON)</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/getUser3.jsp">查找一个用户，并且返回级联的公司信息(返回自定义格式的JSON)</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/findCByJoinFetch3.jsp">查找一个对象，并且级联多级属性(返回自定义格式的JSON)</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/updateUser.jsp">修改一个用户</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/helloTag.jsp">新版jsp tag演示</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/permission.jsp">权限控制标签演示</a></li>

		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/deleteUser.jsp"><i>删除一个用户(没有给你这个权限，会有提示的)</i></a></li>
	</ol>

	<hr />
	<h1>jqGrid示例</h1>
	<ol>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/jqGridDemo1.jsp">用户管理jqGrid</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/jqGridDemo2.jsp">资源管理jqGrid treeGrid</a></li>
	</ol>

	<hr />
	<h1>zTree示例</h1>
	<ol>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/zTreeDemo1.jsp">下拉树</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/zTreeDemo2.jsp">下拉树(后台数据)</a></li>
	</ol>

	<hr />
	<h1>系统日志</h1>
	<ol>
		<li><a target="_blank" href="${pageContext.request.contextPath}/demo/aopController.jsp">Controller操作日志</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/monitoring">JavaMelody日志</a></li>
		<li><a target="_blank" href="${pageContext.request.contextPath}/druid/index.html">Druid监控</a></li>
	</ol>

</body>
</html>