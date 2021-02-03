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

	<h1>这里返回的数据结构是Record结构</h1>
	<h2>这里的查询可以使用QueryFilter，请右键源码，查看input的name部分</h2>
	<form action="${pageContext.request.contextPath}/demoUserController/getBySql2.do" target="_blank" method="post">
		查询id：<input name="Q_t.id_=_Long" value="1" />
		<button type="submit">查找</button>
	</form>

	<hr />
	<form action="${pageContext.request.contextPath}/demoUserController/getBySql2.do" target="_blank" method="post">
		查询name：<input name="Q_t.name_=_String" value="孙1宇" />
		<button type="submit">查找</button>
	</form>

	<hr />
	<form action="${pageContext.request.contextPath}/demoUserController/countBySql.do" target="_blank" method="post">
		name：<input name="Q_t.name_like" value="孙" />
		<button type="submit">统计个数</button>
	</form>

</body>
</html>