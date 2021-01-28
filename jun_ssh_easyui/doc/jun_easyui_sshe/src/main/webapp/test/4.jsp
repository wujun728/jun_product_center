<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ueditor测试</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var ueditor;
	$(function() {
		ueditor = new UE.ui.Editor();
		ueditor.render('ueditor');
	});
</script>
</head>
<body>
	<script type="text/plain" id="ueditor"></script>
</body>
</html>