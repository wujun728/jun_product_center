<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   		
	<table id="datagrid-table" class="easyui-datagrid"
		data-options="
			rownumbers		: true,
			singleSelect	: true,
			checkOnSelect	: false,
			selectOnCheck	: false,
			fitColumns		: true, 
			toolbar			: '#toolbar',
			fit				: true,
			showFooter		: true,
			idField			: 'id',
			loadFilter		: function(json){return json.data;},
			onDblClickRow   : function(){dataTable.edit();}">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				
				<th data-options="field:'tableName',width:80,align:'left',formatter:complexCol">表名</th>
				<th data-options="field:'entityName',width:80,align:'left',formatter:complexCol">实体名</th>
				<th data-options="field:'businessName',width:80,align:'left',formatter:complexCol">业务名</th>
				<th data-options="field:'simpleName',width:50,align:'left',formatter:complexCol">简称</th>
				<th data-options="field:'moduleName',width:50,align:'left',formatter:complexCol">所属模块</th>
				<th data-options="field:'canDelete',width:50,align:'left',formatter:codeCol,codeClass:'yes_or_no'">删除功能</th>
				<th data-options="field:'canEdit',width:50,align:'left',formatter:codeCol,codeClass:'yes_or_no'">编辑功能</th>
				<th data-options="field:'entitySuperClass',width:150,align:'left',
					formatter:codeCol,codeClass:'entity_super_class'">实体基类</th>
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
					style="color: red"  onclick="clearForm('search-form')">条件清空</a>	
		
				<label>名称</label>
				<input name="name" style="width:125px;" type="text">
				<span class="inline-clear"></span>
				
				<label>所属模块</label>
				<input id="search-moduleId" name="moduleId" class="easyui-combobox"
					data-options="valueField:'id',textField:'moduleName',editable:false,panelHeight:'auto',defaultFirst:true,
							url:adminActionPath +'/module/defalut_project',dataField : 'data',onLoadSuccess:loadTables">
				<span class="inline-clear"></span>
				
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="dataTable.search()">查询</a>
		     </form>
		</div>
	
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="dataTable.add()">添加</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="dataTable.edit()">修改</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="dataTable.remove()">删除</a>
        
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-database_table" plain="true" onclick="javascript:openTableList();">数据库导入</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-database_copy" plain="true" onclick="javascript:cloneTablemeta()">克隆元数据</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page_code" plain="true" onclick="javascript:codegen()">生成代码</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-folder_go" plain="true" onclick="javascript:deployToTestProject()">部署到测试工程</a>
         
	</div>
	
	<div id="data-form-dlg" class="easyui-dialog" style="width: 600px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons" modal="true">
		<form id="data-form" class="data-form" method="post">
			<input name="id" style="display: none" />
			<table style="margin-left:-20px;">
				<tr class="tr_padding">
					<td><label>表名</label></td>
					<td>
						<input onChange="tableNameOnChange(this.value)" name="tableName" class="easyui-validatebox" data-options="required:true,validType:'word'">
					</td>
					<td><label>实体名</label></td>
					<td>
						<input id="input-entityName" name="entityName" class="easyui-validatebox" data-options="required:true,validType:'word'">
					</td>		
				</tr>
				<tr class="tr_padding">
					<td><label>简称</label></td>
					<td>
						<input name="simpleName" class="easyui-validatebox" data-options="required:true">
					</td>
					<td><label>业务名</label></td>
					<td>
						<input id="input-businessName" name="businessName" class="easyui-validatebox" data-options="required:true">
					</td>		
				</tr>
				
				<tr class="tr_padding">
					<td><label>实体基类</label></td>
					<td>
						<input name="entitySuperClass" class="easyui-combobox" 
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							defaultFirst:true,codeClass:'entity_super_class',defaultFirst:true">
					</td>
					<td><label>所属模块</label></td>
					<td>
						<input id="form-moduleName" name="moduleName" style="display: none" />
						<input name="moduleId" class="easyui-combobox"
					data-options="valueField:'id',textField:'moduleName',defaultFirst:true,editable:false,panelHeight:'auto',
							url:adminActionPath +'/module/defalut_project',dataField : 'data',
							onSelect:function(record){$('#form-moduleName').val(record.moduleName)}">
					</td>		
				</tr>

				<tr class="tr_padding">
					<td><label>删除功能</label></td>
					<td>
						<input name="canDelete" class="easyui-combobox" value="yes"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							required:true,defaultFirst:true,codeClass:'yes_or_no'">
					</td>	
					<td><label>编辑功能</label></td>
					<td>
						<input name="canEdit" class="easyui-combobox" value="yes"
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
	<div id="dlg-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="dataTable.save()">保存</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#data-form-dlg').dialog('close')">取消</a>
	</div>
	<c:import url="/admin/pages/fieldmeta/_tableImport.jsp"></c:import>
	
	<script type="text/javascript">
	function tableNameOnChange(newValue){
        	$("#input-entityName").val(NamingStrategy.tableNameToClassName(newValue));
        	$("#input-businessName").val(NamingStrategy.tableNameToBusinessName(newValue));
        	$("#data-form").form('validate');
	}
	
	function clearForm(id){
		$("#"+id).form("clear");	
	}
	
	function deployToTestProject(){
		var tablemetaIds = getTablemetaIds();
		if(tablemetaIds.length){
			var moduleId = $("#search-moduleId").combobox("getValue");
			$.messager.alert({
				title : "提示",
				msg : "正则生成代码，请勿重复点击！"
			})
			$.post(adminActionPath + "/gen/deployToTestProject",{tablemetaIds:tablemetaIds, moduleId:moduleId},function(json){
				if(json.type == success){
					$.messager.show({
						title : "提示",
						msg : "成功生成代码并部署！"
					})
				}
			},"json"); 
		}
	}
	
	function getTablemetaIds(){
		var checked_rows = $("#datagrid-table").datagrid("getChecked");
		var tablemetaIds = [];
		for(var i=0; i<checked_rows.length; i++){
			tablemetaIds.push(checked_rows[i].id);
		}
		if(!checked_rows.length){
			$.messager.alert({
				title : "提示",
				msg : "请勾选实体元数据！"
			})
			return tablemetaIds;
		}
		return tablemetaIds;
	}
	
	function codegen(){
		var tablemetaIds = getTablemetaIds();
		if(tablemetaIds.length){
			var moduleId = $("#search-moduleId").combobox("getValue");
			$.messager.alert({
				title : "提示",
				msg : "正则生成代码，请勿重复点击！"
			})
			postParams(adminActionPath + "/gen/genCodeByZip",{tablemetaIds:tablemetaIds, moduleId:moduleId}); 
		}
		 
	}
	
	function cloneTablemeta(){
		var tablemetaIds = getTablemetaIds();
		if(tablemetaIds.length){
			$.post(adminActionPath + "/tablemeta/clone",{tablemetaIds:tablemetaIds},function(json){
				if(json.type == "success"){
					$.messager.show({
						title : "提示",
						msg : "clone元数据成功！"
					});
					loadTables();
				}
			},"json")
		}
	}
	
	
	var dataTable = new DataTable({
		$datagrid_table :$("#datagrid-table"),
		$data_form_dialog : $("#data-form-dlg"),
		$data_form : $("#data-form"),
		data_form_name : "实体元数据",
		
		addOpt : {
			url : adminActionPath+"/tablemeta/add"
		},
		editOpt : {
			url : adminActionPath+"/tablemeta/edit"
		},
		removeOpt : {
			url : adminActionPath+"/tablemeta/delete"
		},
		saveOpt : {},
		searchOpt : {
			$searchForm : $("#search-form"),
		}
	});
	
	</script>
	