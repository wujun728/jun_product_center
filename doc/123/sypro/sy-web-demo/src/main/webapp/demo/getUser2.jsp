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
	<h1>只返回格式为{name:'',age:xxx}的JSON</h1>
	<h3>如果返回为null说明你没有进行初始化数据操作，或者id为1的用户被删掉了</h3>

	<form action="${pageContext.request.contextPath}/demoUserController/get2.do" target="_blank" method="post">
		id：<input name="id" value="1" /><br />
		<button type="submit">查找</button>
	</form>



</body>
</html>