<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
	<table id="entityfield-table" class="easyui-datagrid"
		data-options="
			rownumbers		: true,
			singleSelect	: true,
			fitColumns		: true, 
			fit				: true,
			onLoadSuccess	: editAllCol_entity,
			showFooter		: true,
			idField			: 'id',
			loadFilter		: function(json){return json.data;},
			onClickRow		: function (rowIndex, rowData) {
				ag_$entityfield_table.datagrid('unselectRow', rowIndex);
				ag_checked_index = -1;
			},
			onCheck			: set_ag_checked_index">
		<thead>			
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',title:'ID',width:40"></th>
				<th data-options="field:'columnName',width:80,align:'left',formatter:complexCol" 
					editor="{type:'textbox',options:{validType:'word',required:true,onChange:columnNameOnChange}}">列名</th>
					
				<th data-options="field:'columnType',width:50,align:'left',formatter:complexCol"
					editor="{type:'combobox',options:{valueField:'sqlType',textField:'sqlType',editable:false,panelHeight:'auto',
							dataFn:getSqlTypeJson,onSelect:sqlTypeOnSelect}}">物理类型</th>
				<th data-options="field:'attrType',width:50,align:'left',formatter:complexCol"
					editor="{type:'combobox',options:{valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							codeClass:'java_type'}}">javaType</th>
							
				<th data-options="field:'attrName',width:80,align:'left',formatter:complexCol" 
					editor="{type:'textbox',options:{validType:'word'}}">java字段</th>
					
				<th data-options="field:'length',width:50,align:'left',formatter:complexCol" 
					editor="{type:'numberbox'}">长度</th>
				<th data-options="field:'decimalPlaces',width:50,align:'left',formatter:complexCol" 
					editor="{type:'numberbox'}">小数点</th>
				<th data-options="field:'pkRestrict',width:30,align:'left',
					formatter:checkboxCol,editor:ag_checkbox_editor">主键约束</th>
				<th data-options="field:'notNullRestrict',width:30,align:'left',
					formatter:checkboxCol,editor:ag_checkbox_editor">非空约束</th>
					
				<th data-options="field:'uniqueRestrict',width:30,align:'left',
					formatter:checkboxCol,editor:ag_checkbox_editor">唯一约束</th>
				<th data-options="field:'notInsertRestrict',width:30,align:'left',
					formatter:checkboxCol,editor:ag_checkbox_editor">不可插入</th>
				<th data-options="field:'notUpdateRestrict',width:30,align:'left',
					formatter:checkboxCol,editor:ag_checkbox_editor">不可更新</th>	
				<th data-options="field:'comments',width:100,align:'left',formatter:complexCol" 
					editor="{type:'textbox'}">列注释</th>
					
				<th data-options="field:'fieldValidCode',width:50,align:'left',formatter:complexCol"
					editor="{type:'combobox',options:{enableNull:true,nullText:'无',valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							dataFn:getFieldValidJson}}">字段校验</th>			
			</tr>
		</thead>
	</table>
	<div id="toolbar">	
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" 
        	onclick="javascript:$('#entityfield-table').edatagrid('addRow')">添加</a>  

        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" 
        	onclick="javascript:$('#entityfield-table').edatagrid('destroyRow')">删除</a>

        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" 
        	onclick="javascript:saveSort();">保存</a> 
        	
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-arrow_up" plain="true" 
        	onclick="moveUp()">上移</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-arrow_down" plain="true" 
        	onclick="moveDown()">下移</a>
	</div>
	<script type="text/javascript">
		//全局变量
		var ag_sqlTypeJson=[];
		var ag_$entityfield_table=$("#entityfield-table");

		function editAllCol_entity(data){
			var rows=data.rows;
			for(var i = 0; i < rows.length; i++){
				ag_$entityfield_table.datagrid('beginEdit',i);
			}
		}
		
		$.get(adminActionPath +'/typemapping/findlist',function(json){
			ag_sqlTypeJson=json.data;
		},'json')
		
		function getSqlTypeJson(){
			return ag_sqlTypeJson;
		}
		function sqlTypeOnSelect(record){
            //var row = ag_$entityfield_table.datagrid('getSelected'); 
            //var rindex = ag_$entityfield_table.datagrid('getRowIndex', row);  
            
            //closest 获得匹配选择器的第一个祖先元素，从当前元素开始沿 DOM 树向上。
            var rindex =$(this).closest(".datagrid-row").attr("datagrid-row-index");
            var editor = ag_$entityfield_table.datagrid('getEditor', {  
                    index : rindex,  
                    field : 'attrType'  
                }); 
            if(editor && editor.target)
            	$(editor.target).combobox("setValue",record.javaType);
            
            
		}
		
		function columnNameOnChange(newValue, oldValue){
            //closest 获得匹配选择器的第一个祖先元素，从当前元素开始沿 DOM 树向上。
            var rindex =$(this).closest(".datagrid-row").attr("datagrid-row-index");
            var editor = ag_$entityfield_table.datagrid('getEditor', {  
                index : rindex,  
                field : 'attrName'  
            }); 
            if(editor && editor.target)
            	$(editor.target).textbox("setValue",NamingStrategy.columnNameToAttrName(newValue));
		}
		/** 名称策略*/
		var NamingStrategy={
				tableNameToBusinessName : function(str){
					if(!str)
						return '';
				    var re=/_(\w)/g;
				    str = str.replace(re,function ($0,$1){
				        return $1.toUpperCase();
				    });
				    return str.replace(str[0],str[0].toLowerCase());
				},
				tableNameToClassName : function(str){
					if(!str)
						return '';
				    var re=/_(\w)/g;
				    str =  str.replace(re,function ($0,$1){
				        return $1.toUpperCase();
				    });
				   	return str.replace(str[0],str[0].toUpperCase());
				},
				columnNameToAttrName : function(str){
					if(!str)
						return '';
				    var re=/_(\w)/g;
				    return str.replace(re,function ($0,$1){
				        return $1.toUpperCase();
				    });
				}
		}

	</script>