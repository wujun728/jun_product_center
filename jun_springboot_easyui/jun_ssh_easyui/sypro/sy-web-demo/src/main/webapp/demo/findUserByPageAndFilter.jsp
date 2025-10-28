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
	function findByPage() {
		$.post('${pageContext.request.contextPath}/demoUserController/find.do', $('#_form').serialize(), function(result) {
			if (result && result.msg) {
				alert(result.msg);
				return;
			}
			var rows = result.rows;
			var tbody = [];
			for (var i = 0; i < rows.length; i++) {
				var tr = '<tr><td>{name}</td><td>{age}</td></tr>'.replace('{name}', rows[i].name).replace('{age}', rows[i].age);
				tbody.push(tr);
			}
			$('#_table tbody').html(tbody.join(''));
			$('#_form [name="totalPage"]').html(result.totalPage);
			$('#_form [name="totalRows"]').html(result.totalRows);
		}, 'json');
	}
</script>

</head>
<body>

	<h1>查找后会返回JSON</h1>
	<h2>分页查询所有用户，带排序功能，可以多字段排序</h2>

	<table border="1" id="_table">
		<thead>
			<tr>
				<th>用户名称</th>
				<th>年龄</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>

	<br />
	<br />

	<form id="_form" method="post">
		<div>
			<div>动态查询条件，请右键看源码，主要看input的name部分</div>
			姓名模糊：<input name="Q_name_like" value="孙" />
		</div>
		<div>
			当前页<input name="page" value="1" /> 每页显示<input name="pageSize" value="10" />排序<input name="sort" value="name desc,age asc" />
		</div>
		<div>
			总页数[<span name="totalPage"></span>]总记录数[<span name="totalRows"></span>]
		</div>
		<button type="button" onclick="findByPage();">查找</button>
	</form>



</body>
</html>