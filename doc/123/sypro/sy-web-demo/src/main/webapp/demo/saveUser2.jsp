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
		$.post('${pageContext.request.contextPath}/demoCompanyController/find.do', {
			page : 1,
			pageSize : 10
		}, function(result) {
			if (result && result.rows) {
				var rows = result.rows;
				var options = [];
				for (var i = 0; i < rows.length; i++) {
					var option = '<option value="{id}">{name}</option>'.replace('{id}', rows[i].id).replace('{name}', rows[i].name);
					options.push(option);
				}
				$('#_select').html(options.join(''));
			}
		}, 'json');
	});
</script>

</head>
<body>


	<h1>示例2</h1>
	<h2>如果要在保存用户的时候，将公司也保存在用户的外键上</h2>
	<h2>这里使用了name="company.id"示例；company是用户类的一个属性</h2>
	<form action="${pageContext.request.contextPath}/demoUserController/save.do" method="post" target="_blank">
		公司：<select id="_select" name="company.id">
		</select>(这里如果没有公司，就说明你没有初始化数据)<br /> 用户名：<input name="name" value="新用户2" /> <br />生日：<input name="birthday" value="2000-10-05" /><br />
		<button type="submit">提交</button>
	</form>

</body>
</html>