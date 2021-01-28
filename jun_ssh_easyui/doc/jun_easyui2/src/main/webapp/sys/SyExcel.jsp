<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<title>doroodo平台Excel文件导出工具</title>
<script>
var g='hj';
</script>
</head>
<body>
<form action="" id="excel" method="post">
<input name="searchName" id="searchName" type="hidden">
<input name="searchKey" id="searchKey" type="hidden">
<input name="page" id="page" type="hidden">
<input name="rows" id="rows" type="hidden">
<input name="hearders" id="hearders" type="hidden">
<input name="fields" id="fields" type="hidden">
<input name="excelExportAll" id="excelExportAll" type="hidden">
</form>
</body>
</html>
