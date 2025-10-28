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

	<h1>添加后会返回JSON</h1>
	<h2>演示了后台自动封装成用户对象，直接保存数据库</h2>
	<h3>姓名是字符串格式、年龄是数字型、生日是日期类型，全自动转型，不需要人工操作</h3>

	<form action="${pageContext.request.contextPath}/demoUserController/save.do" target="_blank" method="post">
		用户名：<input name="name" value="张三" /><br /> 
		年龄：<input name="age" value="31" /><br /> 
		生日：<input name="birthday" value="1983-01-11" /><br />
		<button type="submit">添加</button>
	</form>



</body>
</html>