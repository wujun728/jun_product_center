<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<div id="optionfield-dlg" class="easyui-dialog" style="width:800px;height: 400px;" closed="true"  modal="true">
	<table id="optionfield-table" class="easyui-datagrid"
		data-options="
			rownumbers		: true,
			singleSelect	: true,
			fitColumns		: true, 
			url				: adminActionPath + '/entityfield/findlist?tableName=codefun_option_field',
			fit				: true,
			showFooter		: true,
			idField			: 'id',
			onDblClickRow   : selectOptionfieldCall,
			loadFilter		: function(json){return json.data;}">
		<thead>			
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>								
				<th data-options="field:'columnName',width:80,align:'left',formatter:complexCol">列名</th>

				<th data-options="field:'columnType',width:50,align:'left',formatter:complexCol">物理类型</th>
				<th data-options="field:'attrType',width:50,align:'left',formatter:codeCol,codeClass:'java_type'">javaType</th>							
					
				<th data-options="field:'length',width:50,align:'left',formatter:complexCol">长度</th>
					
				<th data-options="field:'decimalPlaces',width:50,align:'left',formatter:complexCol">小数点</th>
				<th data-options="field:'comments',width:100,align:'left',formatter:complexCol">列注释</th>				
			</tr>
		</thead>
	</table>
	
<!-- 
	<div id="optionfield-toolbar">
		<div class="easyui-panel"
		    data-options="collapsible:true,minimizable:true" style="height: 25px">
			<form id="optionfield-search-form" class="search-form" enctype="multipart/form-data">    		

				<label>名称</label>
				<input name="name" style="width:125px;" type="text">

				<span class="inline-clear"></span>
				<label style="color: green">提示：双击即可添加</label>
		     </form>
		</div>
	</div>
 -->
 
 </div>	
<script>
function closeOptionfieldDlg(){
	$('#optionfield-dlg').dialog('close'); 
}

function openOptionfieldDlg(){
	$('#optionfield-dlg').dialog('open').dialog('setTitle', '可选字段'); 
}
</script>