<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<table id="codeitem-dg" class="easyui-datagrid"  
			data-options="
			idField			: 'id',
			rownumbers		:true,
			singleSelect	:true,
			url				:adminActionPath+'/codeitem/datalist',
			queryParams		:{classCode:'00'},
			toolbar			:'#codeitem-toolbar',
			fit				:true,
			fitColumns		:true,
			showFooter		:true">
		<thead>
			<tr>
				<th data-options="field:'code',width:120,formatter:complexCol">代码</th>
				<th data-options="field:'name',width:80,formatter:complexCol">名称</th>
				<th data-options="field:'value',width:120,formatter:complexCol">值</th>
				<th data-options="field:'remark',width:120,formatter:complexCol">备注</th>
				<th data-options="field:'orderNum',width:60,formatter:complexCol">排序</th>
			</tr>
		</thead>
	</table>
	
    <div id="codeitem-toolbar">

        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="codeitem_table.add()">添加</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="codeitem_table.edit()">修改</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="codeitem_table.remove()">删除</a>
	</div>
    
	<div id="itemdlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"  
            closed="true" buttons="#itemdlg-buttons" modal="true">  
        <form id="itemfm" class="data-form" method="post">  
			<input name="id" style="display: none" />
           	<input name="classCode" type="hidden">  
           	
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
					<td><label>扩展信息</label></td>
					<td	colspan="3">
						<textarea rows="3" name="extension" class="textarea easyui-validatebox"
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
	<div id="itemdlg-buttons">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="codeitem_table.save()">保存</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#itemdlg').dialog('close')">取消</a>  
    </div>  
	
	<script type="text/javascript">	
		
		var codeitem_table = new DataTable({
			$datagrid_table :$("#codeitem-dg"),
			$data_form_dialog : $("#itemdlg"),
			$data_form : $("#itemfm"),
			data_form_name : "字典数据",
			
			addOpt : {
				alertValidation : function(){
					var row = $('#codeclass-dg').datagrid('getSelected');
					if(!row){
						$.messager.alert("提示","请选择分类！");
						return false;
					}
					return true;
				},
				url : adminActionPath+"/codeitem/add",
				afterOpenDlg : function($data_form){
					var row = $('#codeclass-dg').datagrid('getSelected');
					$data_form.find("input[name='classCode']").val(row.code);
				}
			},
			editOpt : {
				url : adminActionPath+"/codeitem/edit"
			},
			removeOpt : {
				url : adminActionPath+"/codeitem/delete"
			},
			saveOpt : {},
			searchOpt : {
				$searchForm : $("#codeitem-search-form"),
			}
		});
		
	</script>
