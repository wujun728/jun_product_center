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

	<h1>这个项目，90%的功能，都由Base类实现了，不用写任何sql语句和hql语句，但是还有一些例如报表、统计等，用hql不好实现的怎么办？</h1>
	<h2>项目提供了sql语句的接口，这里演示，利用sql语句，查找用户信息</h2>
	<h3>并且演示了，自定义service接口和自定义dao接口的编写例子</h3>
	<h4>注意，sql接口调用后，会返回Map&lt;String,Object&gt;格式的数据或者List&lt;Map&lt;String,Object&gt;&gt;的数据，但是key的值都是大写的</h4>

	<form action="${pageContext.request.contextPath}/demoUserController/getBySql.do" target="_blank" method="post">
		id：<input name="id" value="1" /><br />
		<button type="submit">查找</button>
	</form>



</body>
</html>