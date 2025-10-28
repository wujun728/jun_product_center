<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>可选字段</title>
   		<c:import url="/admin/pages/common/headsource.jsp"/>	  		
  	</head>
<body>
<c:import url="${root}/admin/pages/fieldmeta/_fieldmeta.jsp"></c:import>
			 
<script type="text/javascript">
//全局变量
var ag_option_field_table="codefun_option_field";

$(function(){
	ag_table.selectTable =  ag_option_field_table;
	ag_table.saveFieldsUrl = adminActionPath + '/tablemeta/save_fields_tbname?tableName=';
	laodWithTableName();
});

</script>
			 			 
</body>
</html>
			