<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div id="tableinfo-dlg" title="导入选项 注意：相同模块的表不能重复" class="easyui-dialog"  style="width: 800px; height: 600px; padding: 5px 0px" closed="true"
		buttons="#tableinfo-dlg-buttons" modal="true">
		

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',collapsible:false,border:false" style="height: 30px">
			<div class="easyui-panel" class="search-form"
			    data-options="collapsible:true,minimizable:true,border:false">
				<form id="table-option-form" class="search-form" enctype="multipart/form-data">    
					<span class="inline-clear"></span>
					<label>删除表前缀</label>
					<input name="removeTablePrefix" class="easyui-validatebox" data-options="validType:'word'">
					<span class="inline-clear"></span>
					
					<label>实体基类</label>
					<input name="entitySuperClass" class="easyui-combobox" 
					data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
						defaultFirst:true,codeClass:'entity_super_class',defaultFirst:true">
					<span class="inline-clear"></span>
					
					<label>所属模块</label>
					<input id="moduleId-it" name="moduleId" class="easyui-combobox"
						data-options="valueField:'id',textField:'moduleName',defaultFirst:true,editable:false,panelHeight:'auto',
						url:adminActionPath +'/module/defalut_project',dataField : 'data'">
				</form>
			</div>
		</div>
		<div data-options="region:'west',collapsible:false" title="未导入的表" style="width:50%;">
    		<table id="un-import-tables" class="easyui-datagrid" 
    			data-options="fitColumns:true,fit:true,checkbox: true,selectOnCheck: true,rownumbers: true">
    			<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'tableName',width:80,align:'left',formatter:complexCol">表名</th>
					</tr>
    		</table>
		</div>
		<div data-options="region:'center'" title="已导入的表">
		
    		<table id="imported-tables" class="easyui-datagrid" 
    			data-options="fitColumns:true,fit:true,checkbox: true,selectOnCheck: true,rownumbers: true">
    			<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'tableName',width:80,align:'left',formatter:complexCol">表名</th>
					</tr>
    		</table>
		</div>
	</div>
	</div>
	<div id="tableinfo-dlg-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="confirmImport()">确认导入</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#tableinfo-dlg').dialog('close')">取消</a>
	</div>	
	
	<script>
		function openTableList(){
			$("#loadingData").show();
			$.get(adminActionPath + "/dbImport/getTableInfo",function(json){
				if(json.type="success"){
					$('#tableinfo-dlg').dialog('open');
					$("#un-import-tables").datagrid('loadData',wrapperTableNames(json.data.tableNames));
					$("#imported-tables").datagrid('loadData',json.data.importedTables);
				}
				$("#loadingData").hide();
			},'json');
		}
		
		function wrapperTableNames(tableNames){
			var rows = [];
			for(var i=0;i < tableNames.length; i++){
				var row = {tableName:tableNames[i]};
				rows.push(row);
			}
			return rows;
		}
		
		function confirmImport(){
			var selectTables1 = $("#un-import-tables").datagrid("getChecked");
			var selectTables2 = $("#imported-tables").datagrid("getChecked");
		
			var tableNames = [];
			for(var i=0; i<selectTables1.length;i++){
				tableNames.push(selectTables1[i].tableName);
			}
			for(var i=0; i<selectTables2.length;i++){
				tableNames.push(selectTables2[i].tableName);
			}

			if(!tableNames.length){
				$.messager.alert({
					title : "提示",
					msg : "请勾选表！"
				})
				return false;
			}
			
			var tableOption = $("#table-option-form").serializeJson();
			var moduleName = $("#moduleId-it").combobox("getText");
			tableOption.moduleName = moduleName;
			var params = {tableNames:tableNames,tableOption:tableOption};
			
			$.ajax({
				    url:adminActionPath + "/dbImport/importTables",
				    /**必须是POST方法**/
				    type:'post',
				    dataType:'json',
				    /**必须制定请求的类型**/
				    contentType:'application/json;charset=utf-8',
				    data:JSON.stringify(params),
				    success:function(json){
						if(json.type == "success"){
							loadTables();
							$('#tableinfo-dlg').dialog('close');
							$.messager.show({
								title : "提示",
								msg : "导入成功！"
							});
						}else{
							$.messager.show({
								title : "提示",
								msg : json.message
							})
						}
				    }
				});			
		}
		
	</script>
	
	