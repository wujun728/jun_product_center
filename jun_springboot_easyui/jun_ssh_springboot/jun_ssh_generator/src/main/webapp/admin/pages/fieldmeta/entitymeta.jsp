<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>实体元数据</title>

   		<c:import url="/admin/pages/common/headsource.jsp"/>
   				
  	</head>
<body class="easyui-layout" fit="true">
<c:import url="/admin/pages/common/loading.jsp"/>

<div data-options="region:'center'" title="实体元数据">
	<c:import url="${root}/admin/pages/fieldmeta/_entitymeta.jsp"></c:import>
</div>	
<div data-options="region:'south',split:true,hideCollapsedContent:false" title="字段元数据" style="height:60%;" >
	<c:import url="${root}/admin/pages/fieldmeta/_fieldmeta.jsp"></c:import>
</div>	
<div style="display: none">
        <a href="javascript:void(0)" id="optionfield-button" class="easyui-linkbutton" iconCls="icon-text_list_bullets" plain="true" 
       	onclick="openOptionfieldDlg()">可选字段</a>  
</div>	

<c:import url="${root}/admin/pages/fieldmeta/_optionfield-dlg.jsp"></c:import>
	<script type="text/javascript">	
		$(function(){
			$("#fieldmeta-toolbar").append($("#optionfield-button"));
		});
	

		
		function loadTables(){
			var moduleId = $("#search-moduleId").combobox("getValue");
			$("#datagrid-table").datagrid({
				url				: adminActionPath + '/tablemeta/findlist',
				queryParams		: {moduleId : moduleId},
				onSelect		: function(index, row){
					ag_table.selectTable = row.id;
					ag_table.saveFieldsUrl = adminActionPath + '/tablemeta/save_fields?tableId=';
					laodWithTableId();
				}
			});
			
			
			
		};
		function selectOptionfieldCall(index, row){	
			$.get(adminActionPath + '/pagefield/findlist',"entityField.id="+row.id,function(json){
				if(json.type == "success"){
					var newEntityField = $.extend(true, {}, row);
					var newPageField =json.data[0];
					newEntityField.id = null;
					newPageField.id = null;
					newPageField.entityField={};
					appendRow(newEntityField,newPageField );	
				}
			},"json")
			
		}	
	</script>
</body>
</html>
			