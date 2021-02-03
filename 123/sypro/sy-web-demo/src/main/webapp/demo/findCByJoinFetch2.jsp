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

	<h1>前提，要初始化一下数据，否则演示不了</h1>
	<h1>用户和角色是多对多的关系，在查询用户的时候，想级联查出角色对象的信息，那么就在预先抓取属性中，填写，用户类里面的角色属性名称</h1>
	<h2></h2>

	<form action="${pageContext.request.contextPath}/demoUserController/find.do" method="post" target="_blank">
		用户id：<input name="Q_t.id_=_long" value="1" /><br /> 预先抓取属性：<input name="joinFetch" value="t.roles" />
		<button type="submit">查询</button>
	</form>



	<hr />
	<h1>如果没有传递joinFetch参数，这不抓取角色信息，进行用户单表查询</h1>
	<form action="${pageContext.request.contextPath}/demoUserController/find.do" method="post" target="_blank">
		用户id：<input name="Q_t.id_=_long" value="1" /><br /> 预先抓取属性：<input name="joinFetch" value="" />
		<button type="submit">查询</button>
	</form>


	<hr />
	<h1>您现在可能有疑问，说我想预先抓取，用户里面的角色，并且角色里面的资源列表我也要，可不可以 joinFetch = t.roles,roles.resources ？？</h1>
	<h2>可以，前提是，model类中@ManyToMany或@OneToMany的Many方此时用Set容器来存放，而不用List集合</h2>
	<form action="${pageContext.request.contextPath}/demoUserController/find.do" method="post" target="_blank">
		用户id：<input name="Q_t.id_=_long" value="1" /><br /> 预先抓取属性：<input name="joinFetch" value="t.roles,roles.resources" />
		<button type="submit">查询</button>
	</form>


</body>
</html>