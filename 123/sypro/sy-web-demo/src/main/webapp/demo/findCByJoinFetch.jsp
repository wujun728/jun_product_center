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
	<h1>数据库里有三个对象，分别是A/B/C，他们的关系是C里面有一个B的对象，B里面有一个A的对象</h1>
	<h1>现在我要在查询C对象的时候，级联查询出B和A的信息，但是不是N+1这种效率低下的查询，使用的是预先抓取策略</h1>
	<h3>解释一下多级预先抓取的格式，我们想要从C里面抓取B信息，所以joinFetch的参数应该是t.demob，为什么是t.？因为是我规定的....，为什么是t.demob，因为DemoC类里面，有个demob的属性，使用的是这个名字</h3>
	<h3>那为什么是demob.demoa？而不是t.demoa？因为demoa的属性是在DemoB的中，t.代表的是DemoC的类，所以获取不到</h3>
	<h3>所以这个级联预先抓取是有允许的，从C里面抓取B，使用t.demob，这个demob就会生成一个别名，当DemoB来使用</h3>
	<h3>再级联A的时候，就要使用上面生成的demob的别名来做前缀，所以是demob.demoa，这样就能查询出DemoB类里面的DemoA对象了</h3>
	<h3>多个抓取属性之间使用英文半角逗号分隔</h3>

	<form action="${pageContext.request.contextPath}/demoCController/find.do" method="post" target="_blank">
		预先抓取属性：<input name="joinFetch" value="t.demob,demob.demoa" />
		<button type="submit">查询</button>
	</form>


	<hr />
	<form action="${pageContext.request.contextPath}/demoCController/find.do" method="post" target="_blank">
		预先抓取属性：<input name="joinFetch" value="t.demob" />
		<button type="submit">查询</button>
	</form>

	<hr />
	<form action="${pageContext.request.contextPath}/demoCController/find.do" method="post" target="_blank">
		预先抓取属性：<input name="joinFetch" value="" />
		<button type="submit">查询</button>
	</form>


</body>
</html>