<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title></title>
</head>
<body>

	<h1>在这个页面您应该能看到一个按钮</h1>
	<h2>如果看不到，说明你没有登录，请按示例第一个链接，模拟登陆一次</h2>
	<h3>具体用法，请查看permission.jsp，右键源码是看不到的</h3>

	<hr />
	<br />
	<c:if test="${fn:contains(sessionInfo.permissionUrls,'/demoUserController/save.do')}">
		<button>您能看到这个按钮，说明有权限</button>
	</c:if>

</body>
</html>