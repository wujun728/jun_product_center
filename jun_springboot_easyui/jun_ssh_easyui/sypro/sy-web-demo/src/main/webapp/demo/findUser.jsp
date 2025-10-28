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
	<h2>查找name like %张% 的用户</h2>
	<h3>input的name是name="Q_t.name_like"写法，后台会自动QueryFilter，动态添加条件，不用编写任何hql或者sql，不需要编写任何代码</h3>

	<form action="${pageContext.request.contextPath}/demoUserController/find.do" target="_blank" method="post">
		模糊查询姓名为：<input name="Q_t.name_like" value="张" /><br />
		<button type="submit">查找</button>
	</form>

	<hr />
	<h1>查找后会返回JSON</h1>
	<h2>查找id in 1,2,3,4的用户</h2>
	<h3>利用了QueryFilter，参数传递的是name="Q_t.id_in_Long"</h3>
	<form action="${pageContext.request.contextPath}/demoUserController/find.do" target="_blank" method="post">
		id为：<input name="Q_t.id_in_Long" value="1,2,3,4" /><br />
		<button type="submit">查找</button>
	</form>

	<hr />
	<h1>查找后会返回JSON</h1>
	<h2>查找company is null的用户，也就是没有公司的用户</h2>
	<h3>利用了QueryFilter，参数传递的是name="Q_t.company_isNull"</h3>
	<form action="${pageContext.request.contextPath}/demoUserController/find.do" target="_blank" method="post">
		<input name="Q_t.company_isNull" type="hidden" value="null" /><br />
		<button type="submit">查找</button>
	</form>

	<hr />
	<h1>查找后会返回JSON</h1>
	<h2>查找company is not null的用户</h2>
	<h3>利用了QueryFilter，参数传递的是name="Q_t.company_isNotNull"</h3>
	<form action="${pageContext.request.contextPath}/demoUserController/find.do" target="_blank" method="post">
		<input name="Q_t.company_isNotNull" type="hidden" value="null" /><br />
		<button type="submit">查找</button>
	</form>

	<hr />
	<h1>查找后会返回JSON</h1>
	<h2>查找birthday是2000-11-01的用户</h2>
	<h3>利用了QueryFilter，参数传递的是name="Q_birthday_=_Date"</h3>
	<form action="${pageContext.request.contextPath}/demoUserController/find.do" target="_blank" method="post">
		生日<input name="Q_birthday_=_Date" type="text" value="2000-11-01" /><br />
		<button type="submit">查找</button>
	</form>

	<hr />
	<h1>查找后会返回JSON</h1>
	<h2>查找birthday是2000-11-01 00:00:00的用户</h2>
	<h3>利用了QueryFilter，参数传递的是name="Q_birthday_EQ_DateTime"</h3>
	<form action="${pageContext.request.contextPath}/demoUserController/find.do" target="_blank" method="post">
		生日<input name="Q_birthday_EQ_DateTime" type="text" value="2000-11-01 00:00:00" /><br />
		<button type="submit">查找</button>
	</form>

</body>
</html>