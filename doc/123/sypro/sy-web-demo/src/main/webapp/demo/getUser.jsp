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

	<h1>查找后会返回JSON</h1>
	<h2>查找id为1的用户</h2>

	<form action="${pageContext.request.contextPath}/demoUserController/get.do" target="_blank" method="post">
		id：<input name="id" value="1" /><br />
		<button type="submit">查找</button>
	</form>

	<hr />

	<h1>查找后会返回JSON</h1>
	<h2>查找id!=1的所有用户</h2>

	<form action="${pageContext.request.contextPath}/demoUserController/find.do" target="_blank" method="post">
		id != 1的所有用户：<input name="Q_id_!=_Long" value="1" /><br />
		<button type="submit">查找</button>
	</form>



</body>
</html>