<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>dict page</title>
    <c:import url="/admin/pages/common/headsource.jsp"/>
	<script language="javascript">
		function loaddict(){
			var row = $('#codeclass-dg').datagrid('getSelected');
			if(row){
				$('#codeitem-dg').datagrid("load",{classCode:row.code});
			}
				
		}
	</script>
  </head>
  
  <body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" title="字典分类" style="width:45%;">
    	<c:import url="dictclass.jsp"/> 
		</div>
		<div data-options="region:'center'" titile="字典项">
    	<c:import url="dictitem.jsp"/>
		</div>
	</div>
    
    <div style="display: none;">


	</div>
	
  </body>
</html>
