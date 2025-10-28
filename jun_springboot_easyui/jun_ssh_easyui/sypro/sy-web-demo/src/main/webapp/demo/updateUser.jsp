<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title></title>
</head>
<body>

	<h1>如果更新失败，说明数据库中没有id是1的用户</h1>
	<h1>请注意：这个更新会级联删除用户里面的角色列表，用户里面有个角色的集合(多对多)，在这里更新的时候，并没有给角色信息，所以会级联删除中间表。</h1>
	<h1>请对照着级联添加功能示例，查看不同之处</h1>


	<form id="_form" action="${pageContext.request.contextPath}/demoUserController/update.do" target="_blank" method="post">
		用户id：<input name="id" value="1" /><br /> 用户名：<input name="name" value="张三2" /><br /> 年龄：<input name="age" value="20" /><br />
		<button type="submit">修改</button>
	</form>



</body>
</html>