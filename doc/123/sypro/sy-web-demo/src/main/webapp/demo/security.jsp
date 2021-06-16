<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title></title>

<script type="text/javascript">
	function login() {
		$.getJSON('${pageContext.request.contextPath}/demoSecurityController/login.do', function(result) {
			alert(result.msg);
		});
	}
	function logout() {
		$.getJSON('${pageContext.request.contextPath}/demoSecurityController/logout.do', function(result) {
			alert(result.msg);
		});
	}
</script>

</head>
<body>

	<h1>如果你不进行下面的模拟登陆，直接访问功能的话，就没有权限，会拦截住</h1>
	<h2>其实下面那个登陆就是一次模拟，没有进行实际操作，只是将sessionInfo放到session里面，模拟用户已经登陆成功</h2>

	<form method="post" id="f">
		姓名<input name="name" value="模拟名字" /> 密码<input name="pwd" value="123456" /> <br />
		<button type="button" onclick="login();">登陆</button>
		<button type="button" onclick="logout();">注销登陆，重新测试</button>
	</form>

</body>
</html>