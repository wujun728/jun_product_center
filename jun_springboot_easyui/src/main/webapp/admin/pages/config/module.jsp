<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    	<title>模块配置</title>
   		<c:import url="/admin/pages/common/headsource.jsp"/>		
  	</head>
<body>
	<table id="datagrid-table" class="easyui-datagrid" title="模块配置表"
		data-options="
			rownumbers		: true,
			singleSelect	: true,
			fitColumns		: true, 
			url				: adminActionPath + '/module/findpage',
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
				<th data-options="field:'moduleName',width:80,align:'left',formatter:complexCol">模块代码</th>
				<th data-options="field:'project.name',width:80,align:'left',formatter:complexCol">所属项目</th>
				<th data-options="field:'description',width:100,align:'left',formatter:complexCol">描述</th>
				<th data-options="field:'packageName',width:150,align:'left',formatter:complexCol">包名</th>
				<th data-options="field:'author',width:100,align:'left',formatter:complexCol">作者</th>
				<th data-options="field:'copyRight',width:100,align:'left',formatter:complexCol">版权信息</th>
				<th data-options="field:'remark',width:150,formatter:complexCol">备注</th>
				<th data-options="field:'modifyTime',width:120,align:'left',formatter:EasyUiDateTime">更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<div class="easyui-panel"
		    data-options="collapsible:true,minimizable:true">
			<form id="search-form" class="search-form" enctype="multipart/form-data">    
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true"
					style="color: red"  onclick="resetForm('search-form')">条件重置</a>	
		
				<label>模块代码</label>
				<input name="moduleName" style="width:125px;" type="text">
				<span class="inline-clear"></span>
				
				<label>所属项目</label>
				<input name="project.id" class="easyui-combobox"
					data-options="valueField:'id',textField:'name',enableNull:true,editable:false,panelHeight:'auto',
							url:adminActionPath +'/project/findlist',dataField : 'data'">
				<span class="inline-clear"></span>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="dataTable.search()">查询</a>
		     </form>
		</div>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dataTable.add()">添加</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dataTable.edit()">修改</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="dataTable.remove()">删除</a>

	</div>
	
	<div id="data-form-dlg" class="easyui-dialog" style="width: 600px; height: 500px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="data-form" class="data-form" method="post">
			<input name="id" style="display: none" />
			<table style="margin-left:-20px;">
				<tr class="tr_padding">
					<td><label>模块代码</label></td>
					<td>
						<input name="moduleName" class="easyui-validatebox" data-options="required:true,validType:'word'">
					</td>
					<td><label>所属项目</label></td>
					<td>
				<input name="project.id" class="easyui-combobox"
					data-options="valueField:'id',textField:'name',defaultFirst:true,editable:false,panelHeight:'auto',
							url:adminActionPath +'/project/findlist',dataField : 'data'">
					</td>		
				</tr>
				<tr class="tr_padding">
					<td><label>包名</label></td>
					<td	colspan="3">
						<input name="packageName" class="easyui-validatebox" style="width: 375px" data-options="required:true">
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>模块描述</label></td>
					<td	colspan="3">
						<input name="description" class="easyui-validatebox" style="width: 375px" data-options="validType:'maxLength[255]'">
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>作者</label></td>
					<td	colspan="3">
						<input name="author" class="easyui-validatebox" style="width: 375px" data-options="validType:'maxLength[255]'">
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>版权信息</label></td>
					<td	colspan="3">
						<textarea rows="3" name="copyRight" class="textarea easyui-validatebox"
							style="width: 375px"></textarea>
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
			data_form_name : "模块配置",
			
			addOpt : {
				url : adminActionPath+"/module/add"
			},
			editOpt : {
				url : adminActionPath+"/module/edit"
			},
			removeOpt : {
				url : adminActionPath+"/module/delete"
			},
			saveOpt : {},
			searchOpt : {
				$searchForm : $("#search-form"),
			}
		});
		
	</script>
</body>
</html>
			