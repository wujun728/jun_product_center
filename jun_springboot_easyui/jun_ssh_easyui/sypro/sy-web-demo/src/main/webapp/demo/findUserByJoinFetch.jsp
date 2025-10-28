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
	<h2>这个例子演示了，查询用户列表，并且用户的公司是”公司2“的所有用户</h2>
	<h3>具体怎样设置的，请右键-查看网页源码</h3>
	<h3>主要看input的name的使用</h3>

	<form action="${pageContext.request.contextPath}/demoUserController/find.do" method="post" target="_blank">
		<div>
			级联查询前要设置级联属性<input name="joinFetch" value="t.company" /><br />
			 过滤条件<input name="Q_company.name_EQ_String" value="公司2" />
		</div>
		<button type="submit">查找</button>
	</form>



</body>
</html>