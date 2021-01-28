<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<link rel="stylesheet" type="text/css" href="${root}/static/easyui/easyui-extend/IconsExtension/icon_fm.css">    
    
	<div class="easyui-panel" style="width:100%">
		<div id="fieldmeta-toolbar">	
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
	        	onclick="addField()">添加</a>  
	
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
	        	onclick="removeField()">删除</a>

			<a id="save-button" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" 
	    		onclick="javascript:saveFields()">保存</a> 

			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-arrow_up" plain="true" 
	        	onclick="moveUp()">上移</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-arrow_down" plain="true" 
	        	onclick="moveDown()">下移</a>
		</div>		
	</div>
	<div class="easyui-tabs" id="tt" data-options="fit:true,border:false,plain:true,onSelect:keepSame">
		<div title="实体字段">
			<c:import url="${root}/admin/pages/fieldmeta/_entityfield.jsp"></c:import>
		</div>
		<div title="页面字段" >
			<c:import url="${root}/admin/pages/fieldmeta/_pagefield.jsp"></c:import>
		</div>
	</div>

<script type="text/javascript">

//初始未被勾选
var ag_checked_index = -1;
var ag_table = {
		selectTable	: null,
		// 防止重复加载 字段元信息
		loadTable  		: null,
		saveFieldsUrl	: null
	};

var ag_entity_field_default=	{
		"columnType": "varchar",
		"attrType": "String",
		"length": 255,
	};
var ag_page_field_default={
		"canList": "yes",
		"queryType": "CONTAIN",
		"formType":"",
		"fieldFormatter":"",
		"canEdit": "yes",
		"gridRowCol": "6/4/8",
	}


function copyComments(){
	var page_fields = ag_$pagefield_table.datagrid("getRows");
	for(var i= 0; i < page_fields.length;i++){
		var entity_editor = ag_$entityfield_table.datagrid("getEditor",{
			index : i,
			field : "comments"
		});		
		var columnName = $(entity_editor.target).textbox("getValue");
		
		var page_editor = ag_$pagefield_table.datagrid("getEditor",{
			index : i,
			field : "fieldTitle"
		});
		var fieldTitle = $(page_editor.target).textbox("getValue");
		if(!fieldTitle){
			$(page_editor.target).textbox("setValue",columnName);				
		}
	}
}



function laodWithTableName(){
	var tableName = ag_table.selectTable;
	if(ag_table.loadTable != tableName){
		ag_$entityfield_table.datagrid({url:adminActionPath + '/entityfield/findlist?tableName=' + tableName});
		ag_$pagefield_table.datagrid({url:adminActionPath + '/pagefield/findlist?tableName=' + tableName});	
		ag_table.loadTable = tableName;
	}
}

function laodWithTableId(){
	var tableId = ag_table.selectTable;
	if(ag_table.loadTable != tableId){
		ag_$entityfield_table.datagrid({url:adminActionPath + '/entityfield/findlist?tableId=' + tableId});
		ag_$pagefield_table.datagrid({url:adminActionPath + '/pagefield/findlist?tableId=' + tableId});	
		ag_table.loadTable = tableId;
	}
}

function set_ag_checked_index(index, row){
		ag_checked_index = index;
}

function keepSame(title,index){
	keepFieldInfoSame(title,index);
	keepCheckedSame();
}


function keepCheckedSame(){	
	var entity_checked_row=ag_$entityfield_table.datagrid('getSelected');
	var page_checked_row=ag_$pagefield_table.datagrid('getSelected');
	if(ag_checked_index !=-1){
		ag_$entityfield_table.datagrid('checkRow', ag_checked_index);
		ag_$pagefield_table.datagrid('checkRow', ag_checked_index);
	}else{
		ag_$entityfield_table.datagrid('uncheckAll');
		ag_$pagefield_table.datagrid('uncheckAll');
	}
}

function keepFieldInfoSame(title,index){

	if(title != "页面字段"){
		return ;
	}
	
	var entity_fields = ag_$entityfield_table.datagrid("getRows");	
	var page_fields = ag_$pagefield_table.datagrid("getRows");
	//datagrid view 操作
	var panel = ag_$pagefield_table.datagrid('getPanel');   
	for(var i= 0; i < page_fields.length;i++){
		var editors = ag_$entityfield_table.datagrid("getEditors",i);
		var tr = $("tr[datagrid-row-index=" + i + "]",panel);
		
		if(!page_fields[i].entityField){
			page_fields[i].entityField = {};
		}
		
		var columnName = getEditorValue(editors,"columnName");
		page_fields[i].entityField.columnName = columnName;
		tr.find("td[field='entityField.columnName']").children("div").html(columnName);
		
		var attrName = getEditorValue(editors,"attrName");
		page_fields[i].entityField.attrName = attrName;
		tr.find("td[field='entityField.attrName']").children("div").html(attrName);
	}
}	

function getEditorValue(editors,field){
	for(var i=0; i<editors.length; i++){
		if (editors[i].field == field){
			return $(editors[i].target).textbox("getValue");
		}
	}
	return null;
}

// ------------get fields data and save

function getAllFields(){
	refresTable(ag_$entityfield_table);
	refresTable(ag_$pagefield_table);
	var entity_fields = ag_$entityfield_table.datagrid("getRows");	
	var page_fields = ag_$pagefield_table.datagrid("getRows");
	var data={
			'entityFields' : entity_fields,
			'pageFields' : page_fields
	};	
	return data;
}

function refresTable($dg){
	var rows=$dg.datagrid("getRows");
	for(var i=0; i < rows.length;i++){
		refreshRow($dg,i);
	}
}

function refreshRow($dg,i){
	$dg.datagrid("endEdit",i);
	$dg.datagrid("refreshRow",i);
	$dg.datagrid("beginEdit",i);
}

function saveFields(){
	if(!checkSelectTable())
		return false;
	
	ajaxRequestBody({
		url : ag_table.saveFieldsUrl + ag_table.selectTable,
		data : getAllFields()
	})
}

function checkSelectTable(){
	
	if(!ag_table.selectTable){
		$.messager.alert({
			title : '提示',
			msg : "请勾选一行实体元数据！"
		});
		return false;
	}
	return true;
}

// add and remove field

function addField(){
	appendRow($.extend(true, {}, ag_entity_field_default),$.extend(true, {}, ag_page_field_default));
}

function appendRow(entity_field,page_field ){
	if(!checkSelectTable())
		return false;
	ag_$entityfield_table.datagrid("appendRow",entity_field);
	ag_$pagefield_table.datagrid("appendRow",page_field);
	
	var addIndex=ag_$entityfield_table.datagrid('getRows').length - 1;
	
	ag_$entityfield_table.datagrid("beginEdit",addIndex);
	ag_$pagefield_table.datagrid("beginEdit",addIndex);	
	//refreshRow(ag_$entityfield_table,addIndex);
	//refreshRow(ag_$pagefield_table,addIndex);	
}


function removeField(){
	if(isChecked()){
		
		$.messager.confirm('确认', '确定要删除这条信息吗?', function(r) {
			if (r) {
				var row = ag_$entityfield_table.datagrid("getSelected");
				if(row.id){
					$.post(adminActionPath + '/tablemeta/deleteFiledPair' , {id : row.id},function(json){
						if(json.type == "success"){
							ag_$entityfield_table.datagrid("deleteRow",ag_checked_index);
							ag_$pagefield_table.datagrid("deleteRow",ag_checked_index);
						}else{
							$.messager.show({ // show error message
								title : '删除失败',
								msg : json.message
							});
						}
					},"json")
				}else{
					ag_$entityfield_table.datagrid("deleteRow",ag_checked_index);
					ag_$pagefield_table.datagrid("deleteRow",ag_checked_index);
				}
			}
		});
		

	}
}

//----------move up and down----begin

function isChecked(){
	console.log("ag_checked_index:" +ag_checked_index)
	if(ag_checked_index == -1){
		$.messager.show({
			title : '提示',
			msg : "请勾选一行记录！"
		});
		return false;
	}
	return true;
}

function moveUp(){
	if(isChecked()){
		var index = ag_checked_index;
		mysort(index, 'up', ag_$pagefield_table);  
		mysort(index, 'up', ag_$entityfield_table);  
	}
}

function moveDown(){
	if(isChecked()){
		//变量 index 是来保持同步的
		var index = ag_checked_index;
		 mysort(index, 'down', ag_$pagefield_table);
		 mysort(index, 'down', ag_$entityfield_table); 
	}
}

function mysort(index, type, $dg) {		
	//结束行编辑，否则处于编辑状态的行，移动后丢失数据
    if ("up" == type) {
        if (index != 0) {
        	$dg.datagrid("endEdit",index);
        	$dg.datagrid("endEdit",index -1);
            var toup = $dg.datagrid('getData').rows[index];
            var todown = $dg.datagrid('getData').rows[index - 1];
            $dg.datagrid('getData').rows[index] = todown;
            $dg.datagrid('getData').rows[index - 1] = toup;
            $dg.datagrid('refreshRow', index);
            $dg.datagrid('refreshRow', index - 1);
            $dg.datagrid('selectRow', index - 1);
            $dg.datagrid("beginEdit",index);
            $dg.datagrid("beginEdit",index - 1);
        }
    } else if ("down" == type) {
        var rows = $dg.datagrid('getRows').length;
        if (index != rows - 1) {
        	$dg.datagrid("endEdit",index);
        	$dg.datagrid("endEdit",index + 1);
            var todown = $dg.datagrid('getData').rows[index];
            var toup = $dg.datagrid('getData').rows[index + 1];
            $dg.datagrid('getData').rows[index + 1] = todown;
            $dg.datagrid('getData').rows[index] = toup;
            $dg.datagrid('refreshRow', index);
            $dg.datagrid('refreshRow', index + 1);
            $dg.datagrid('selectRow', index + 1);
            $dg.datagrid("beginEdit",index);
            $dg.datagrid("beginEdit",index + 1);
        }
    }
}

//----------move up and down----end

</script>
