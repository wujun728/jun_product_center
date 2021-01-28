<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>实体基类</title>
   		<c:import url="/admin/pages/common/headsource.jsp"/>	  		
  	</head>
<body>


<div style="display: none">
	<!-- 
	<div id="baseEntity-panel" class="easyui-panel"
	    data-options="collapsible:true,minimizable:true">
		<form class="search-form" enctype="multipart/form-data">  		  					

	     </form>
	</div>
	 -->
	<div id="baseEntity-panel" class="search-form" style="display: inline-block;">
			<label style="margin-left: 10px ">实体基类</label>
				<input class="easyui-combobox"
					data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							defaultFirst:true,codeClass:'entity_super_class',onSelect:selectBaseEntity">
			<span class="inline-clear"></span>		
	</div>
</div>

<c:import url="${root}/admin/pages/fieldmeta/_fieldmeta.jsp"></c:import>
			 
<script type="text/javascript">
$.parser.auto=false;

$(function(){
	$("#fieldmeta-toolbar").append($("#baseEntity-panel"));
	$.parser.parse(); 
	
});

function selectBaseEntity(record){
	ag_table.selectTable =  record.value;
	ag_table.saveFieldsUrl = adminActionPath + '/tablemeta/save_fields_tbname?tableName=';
	laodWithTableName();
}

</script>
			 			 
</body>
</html>
