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

	<h1>这个功能最后进行测试吧，否则后面的示例可能会找不到数据</h1>

	<h1>删除后会返回JSON</h1>
	<h2>演示了前台传递id参数，后台自动封装成Long</h2>
	<h3>如果删除不成功，说明没有id为1的用户</h3>

	<h1>请注意：这个删除会级联删除，用户里面有个角色的集合(多对多)，代表用户可以有多个角色，在删除用户的时候，就会删除掉中间表的信息</h1>

	<form action="${pageContext.request.contextPath}/demoUserController/delete.do" target="_blank" method="post">
		id：<input name="id" value="1" /><br />
		<button type="submit">删除</button>
	</form>



</body>
</html>