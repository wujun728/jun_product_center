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
	$(function() {
		$.getJSON('${pageContext.request.contextPath}/demoRoleController/find.do', {
			page : 1,
			pageSize : 10
		}, function(result) {
			var checkboxs = [];
			if (result && result.rows.length > 0) {
				for (var i = 0; i < result.rows.length; i++) {
					var cb = '<input type="checkbox" value="{id}" name="roles"/>{name}<br/>'.replace('{id}', result.rows[i].id).replace('{name}', result.rows[i].name);
					checkboxs.push(cb);
				}
				$('#_roleDiv').html(checkboxs.join(''));
			} else {
				$('#_roleDiv').html('数据库里没有角色信息,请先初始化数据库，再来测试');
			}

		});
	});
</script>
</head>
<body>

	<h1>用户和角色的关系是多对多，所以用户和角色直接有一个中间表，来存放对应关系</h1>
	<h1>在保存用户的时候，将角色的关系也保存在中间表中，相当于保存了User表和User_Role中间表两张。</h1>

	<form action="${pageContext.request.contextPath}/demoUserController/save.do" method="post" target="_blank">
		用户名：<input name="name" value="带角色的用户" /> <br />
		<div>请选择用户的角色</div>
		<div id="_roleDiv"></div>
		<button type="submit">提交</button>
	</form>

</body>
</html>