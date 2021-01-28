<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>字段映射配置</title>
   		<c:import url="/admin/pages/common/headsource.jsp"/>		
  	</head>
<body>
	<table id="datagrid-table" class="easyui-datagrid" title="字段映射配置表"
		data-options="
			rownumbers		: true,
			singleSelect	: true,
			fitColumns		: true, 
			url				: adminActionPath + '/typemapping/findpage',
			toolbar			: '#toolbar',
			fit				: true,
			pagination		: true,
			pageSize		: 15,
			pageList        : [10,15,20,25,30],
			showFooter		: true,
			idField			: 'id',
			onDblClickRow   : function(){dataTable.edit();}">
		<thead>
			<tr>
				<th data-options="field:'sqlType',width:80,align:'left',formatter:complexCol">sqlType</th>
				<th data-options="field:'javaType',width:80,align:'left',formatter:complexCol">javaType</th>
				<th data-options="field:'fullJavaType',width:200,align:'left',formatter:complexCol">fullJavaType</th>
				

				<th data-options="field:'remark',width:150,formatter:complexCol">备注</th>
				<th data-options="field:'modifyTime',width:120,align:'left',formatter:EasyUiDateTime">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">	
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dataTable.add()">添加</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dataTable.edit()">修改</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="dataTable.remove()">删除</a>

	</div>
	
	<div id="data-form-dlg" class="easyui-dialog" style="width: 600px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="data-form" class="data-form" method="post">
			<input name="id" style="display: none" />
			<table style="margin-left:-20px;">				
				<tr class="tr_padding">
					<td><label>sqlType</label></td>
					<td>
						<input name="sqlType" class="easyui-validatebox" data-options="required:true,validType:'word'">
					</td>
					<td><label>javaType</label></td>
					<td>
						<input name="javaType" class="easyui-combobox"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							required:true,codeClass:'java_type',defaultFirst:true,
							onSelect:function(record){
								$('input[name=fullJavaType]').val(record.value)
							}">
					</td>
				</tr>
				
				<tr class="tr_padding">
					<td><label>fullJavaType</label></td>
					<td	colspan="3">
						<input name="fullJavaType" readonly="readonly" class="easyui-validatebox" style="width: 400px">
					</td>
				</tr>
								
				<tr class="tr_padding">
					<td><label>备<span class="letter-space-2"></span>注</label></td>
					<td	colspan="3">
						<textarea rows="3" name="remark" class="textarea easyui-validatebox"
							style="width: 400px"></textarea>
					</td>
				</tr>
			</table> 
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="dataTable.save()">保存</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#data-form-dlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">	
		
		var dataTable = new DataTable({
			$datagrid_table :$("#datagrid-table"),
			$data_form_dialog : $("#data-form-dlg"),
			$data_form : $("#data-form"),
			data_form_name : "字段映射配置",
			
			addOpt : {
				url : adminActionPath+"/typemapping/add",
				notReset : true,
				afterOpenDlg : function($data_form){
					$data_form.find("input[name='sqlType']").val("");
				}
			},
			editOpt : {
				url : adminActionPath+"/typemapping/edit"
			},
			removeOpt : {
				url : adminActionPath+"/typemapping/delete"
			},
			saveOpt : {}
		});
		
	</script>
</body>
</html>
			