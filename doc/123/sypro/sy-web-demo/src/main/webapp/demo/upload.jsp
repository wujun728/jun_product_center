<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title></title>

<script>
	$(function() {
		$('#formUpload').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
			},
			success : function(result, statusText) {
				alert(result.msg);
			},
			url : '${pageContext.request.contextPath}/demoUploadController/upload.do',
			type : 'post',
			dataType : 'json'
		});
	});
</script>

</head>
<body>

	<h1>多文件上传示例，总上传文件大小限制在spring-mvc.xml中设置</h1>
	<h1>可以上传多文件，并且附带表单项信息</h1>

	<form id="formUpload" method="post" enctype="multipart/form-data">
		<div>
			<input placeholder="这个地方你可以输入其他表单信息" name="otherString" style="width: 500px;" />
		</div>
		<div>
			<input type="file" name="file" />
		</div>
		<div>
			<input type="file" name="file" />
		</div>
		<button type="submit">上传</button>
	</form>

</body>
</html>