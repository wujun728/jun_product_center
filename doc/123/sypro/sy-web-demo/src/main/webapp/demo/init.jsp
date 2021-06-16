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

	<h1>初始化一些测试数据，演示了service里引入多service的例子，成功后会返回JSON</h1>
	<h1>密码sha加密演示</h1>
	<h3>初始化会添加一批用户、角色、资源、公司信息，便于后面的测试演示</h3>

	<a target="_blank" href="${pageContext.request.contextPath}/demoInitController/init.do" target="_blank">开始初始化</a>



</body>
</html>