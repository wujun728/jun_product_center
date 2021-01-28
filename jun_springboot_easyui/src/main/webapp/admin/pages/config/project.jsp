<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>项目配置</title>
   		<c:import url="/admin/pages/common/headsource.jsp"/>		
  	</head>
<body>
	<table id="datagrid-table" class="easyui-datagrid" title="项目配置表"
		data-options="
			rownumbers		: true,
			singleSelect	: true,
			fitColumns		: true, 
			url				: adminActionPath + '/project/findpage',
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
				<th data-options="field:'name',width:100,align:'left',formatter:complexCol">项目代码</th>
				<th data-options="field:'isDefaultCode',width:50,align:'left',formatter:codeCol,codeClass:'yes_or_no'">是否默认</th>
				<th data-options="field:'dbUrl',width:200,align:'left',formatter:complexCol">JDBC地址</th>
				<th data-options="field:'dbUsername',width:100,align:'left',formatter:complexCol">用户名</th>
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
	
	<div id="data-form-dlg" class="easyui-dialog" style="width: 600px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="data-form" class="data-form" method="post">
			<input name="id" style="display: none" />
			<table style="margin-left:-20px;">
				<tr class="tr_padding">
					<td><label>项目代码</label></td>
					<td>
						<input name="name" class="easyui-validatebox" data-options="required:true,validType:'word'">
					</td>
					<td><label>是否默认</label></td>
					<td>
						<input name="isDefaultCode" class="easyui-combobox"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							required:true,codeClass:'yes_or_no'">
					</td>		
				</tr>
				
				<!-- 
				<tr class="tr_padding">
					<td><label>java模板</label></td>
					<td>
						<input name="javaTemplateCode" class="easyui-combobox"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							required:true,defaultFirst:true,codeClass:'java_template_code'">
					</td>
					<td><label>页面模板</label></td>
					<td>
						<input name="pageTemplateCode" class="easyui-combobox"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							required:true,defaultFirst:true,codeClass:'page_template_code'">
					</td>
				</tr>
				 -->
				 
				
				<tr class="tr_padding">
					<td><label>JDBC地址</label></td>
					<td	colspan="3">
						<input name="dbUrl" class="easyui-validatebox" style="width: 375px" data-options="validType:'maxLength[255]'">
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>用户名</label></td>
					<td>
						<input name="dbUsername" class="easyui-validatebox" data-options="validType:'maxLength[255]'">
					</td>
					<td><label>密码</label></td>
					<td>
						<input name="dbPassword" type="password" class="easyui-validatebox" data-options="validType:'maxLength[255]'">
					</td>		
				</tr>
				<tr class="tr_padding">
					<td><label>备<span class="letter-space-2"></span>注</label></td>
					<td	colspan="3">
						<textarea rows="3" name="remark" class="textarea easyui-validatebox"
							style="width: 375px"></textarea>
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
			data_form_name : "项目配置",
			
			addOpt : {
				url : adminActionPath+"/project/add"
			},
			editOpt : {
				url : adminActionPath+"/project/edit"
			},
			removeOpt : {
				url : adminActionPath+"/project/delete"
			},
			saveOpt : {},
			searchOpt : {
				$searchForm : $("#search-form"),
			}
		});
		
	</script>
</body>
</html>
			