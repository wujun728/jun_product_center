<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table id="codeclass-dg" class="easyui-datagrid"  
			data-options="
			idField			: 'id',
			rownumbers		:true,
			singleSelect	:true,
			url				:adminActionPath + '/codeclass/datalist',
			toolbar			:'#codeclass-toolbar',
			fit				:true,
			fitColumns		:true,
			showFooter		:false,
			onSelect		:loaddict">
		<thead>
			<tr>
				<th data-options="field:'code',width:120,formatter:complexCol">编码</th>
				<th data-options="field:'name',width:80,formatter:complexCol">名称</th>
				<th data-options="field:'moduleCode',width:80,formatter:codeCol,codeClass:'module_code'">所属模块</th>
				<th data-options="field:'isSys',width:50,formatter:codeCol,codeClass:'yes_or_no'">系统字典</th>
				<th data-options="field:'remark',width:120,formatter:complexCol">备注</th>				
			</tr>
		</thead>
	</table>
	<div id="codeclass-toolbar">
        <div class="easyui-panel"
		    data-options="collapsible:true,minimizable:true">
			<form id="codeclass-search-form" class="search-form" enctype="multipart/form-data">    
			    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true"
					style="color: red"  onclick="resetForm('codeclass-search-form')">条件重置</a>	
		
				<label>名称</label>
				<input name="name" style="width:125px;" type="text">
				<span class="inline-clear"></span>
				
				<label>所属模块</label>
				<input name="moduleCode" class="easyui-combobox"
					data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							enableNull:true,codeClass:'module_code'">
				<span class="inline-clear"></span>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="codeclass_dataTable.search()">查询</a>
		     </form>
		</div>


        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="codeclass_dataTable.add()">添加</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="codeclass_dataTable.edit()">修改</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="codeclass_dataTable.remove()">删除</a>


    </div> 

	<div id="classdlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"  
            closed="true" buttons="#classdlg-buttons" modal="true">
        <form id="classfm" class="data-form" method="post"> 
			<input name="id" style="display: none" />
        	<table style="margin-left:-20px;"> 
				<tr class="tr_padding">
					<td><label>编<span class="letter-space-2"></span>码</label></td>
					<td>
						<input name="code" class="easyui-validatebox" data-options="required:true,validType:'maxLength[255]'">
					</td>
					<td><label>名<span class="letter-space-2"></span>称</label></td>
					<td>
						<input name="name" class="easyui-validatebox" data-options="required:true,validType:'maxLength[255]'">
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>值</label></td>
					<td>
						<input name="value" class="easyui-validatebox" data-options="validType:'maxLength[255]'">
					</td>
					<td><label>排<span class="letter-space-2"></span>序</label></td>
					<td>
						<input name="orderNum" class="easyui-validatebox" data-options="validType:'positiveNumber'">
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>所属模块</label></td>
					<td>
						<input name="moduleCode" class="easyui-combobox"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							required:true,defaultFirst:true,codeClass:'module_code'">
					</td>
					<td><label>系统内置</label></td>
					<td>
						<input name="isSys" class="easyui-combobox"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							required:true,defaultFirst:true,codeClass:'yes_or_no'">
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
	<div id="classdlg-buttons">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="codeclass_dataTable.save()">保存</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#classdlg').dialog('close')">取消</a>  
    </div>  
	
	<script type="text/javascript">  
		var codeclass_dataTable = new DataTable({
			$datagrid_table :$("#codeclass-dg"),
			$data_form_dialog : $("#classdlg"),
			$data_form : $("#classfm"),
			data_form_name : "字典分类",
			
			addOpt : {
				url : adminActionPath+"/codeclass/add"
			},
			editOpt : {
				url : adminActionPath+"/codeclass/edit"
			},
			removeOpt : {
				url : adminActionPath+"/codeclass/delete"
			},
			saveOpt : {},
			searchOpt : {
				$searchForm : $("#codeclass-search-form"),
			}
		});
        
	</script>
