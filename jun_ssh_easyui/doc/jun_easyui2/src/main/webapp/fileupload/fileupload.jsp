<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
$(function(){
	$('#fileuploadform').attr('action','${ctx}'+actionUrl('/sys/','syFile','Upload'));
	var inputHtml='上传文件：<input type="file" name="fileGroup"><br/> ';
	$('#addFileInput').click(function(){
		$('#fileInputs').html($('#fileInputs').html()+inputHtml);
	})
});
</script>
</head>

<body>
<form id="fileuploadform"  enctype="multipart/form-data" method="post" > 
<input type="button" id="addFileInput" value="添加附件"/>  
<div id="fileInputs">
</div>
</form>  
</body>
</html>
