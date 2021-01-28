<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	/* 系统字典表*/
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title>项目管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
var lastIndex;  
$('#detailTable').datagrid({  
toolbar:[{  
text:'新增',  
iconCls:'icon-add',  
handler:function(){  
$('#detailTable').datagrid('endEdit', lastIndex);  
$('#detailTable').datagrid('appendRow',{  
categoryId:'',  
brandId:'',  
productionId:'',  
storeId:'',  
price:'',  
count:'',  
total:'',  
prePay:''  
});  
  
lastIndex = $('#detailTable').datagrid('getRows').length-1;  
$('#detailTable').datagrid('selectRow', lastIndex);  
$('#detailTable').datagrid('beginEdit', lastIndex);  
  
synchCategory(getEditRow(lastIndex,'categoryId'),lastIndex);  
  
synchStore(getEditRow(lastIndex,'storeId'));  
}  
},'-',{  
text:'编辑',  
iconCls:'icon-remove',  
handler:function(){  
var row = $('#detailTable').datagrid('getSelected');  
if (row){  
var index = $('#detailTable').datagrid('getRowIndex', row);  
$('#detailTable').datagrid('deleteRow', index);  
}  
}  
},'-',{  
text:'删除',  
iconCls:'icon-remove',  
handler:function(){  
var row = $('#detailTable').datagrid('getSelected');  
if (row){  
var index = $('#detailTable').datagrid('getRowIndex', row);  
$('#detailTable').datagrid('deleteRow', index);  
}  
}  
}],  
onClickRow:function(rowIndex){  
if (lastIndex != rowIndex){  
$('#detailTable').datagrid('endEdit', lastIndex);  
$('#detailTable').datagrid('beginEdit', rowIndex);  
}  
lastIndex = rowIndex;  
}  
});  
  
// 异步加载类别，并在选中后，级联加载品牌（Brand）的combobox  
function synchCategory(editRow,index){  
$(editRow.target).combobox('reload','<%=contextPath%>/base/sys-def!doNotNeedSecurity_getMainMenu.sy?date='  
+ new Date().getTime());  
$(editRow.target).combobox({onSelect:function(){  
synchBrand(getEditRow(index,'brandId'),index,$(this).combobox('getValue'));  
}});  
}  
  
// 异步加载品牌，并在选中后，级联加载商品（Production）的combobox   
function synchBrand(editRow,index,categoryId){  
jQuery(editRow.target).combobox('reload','brand/findSuperBrandByAjax.action?categoryId=' + categoryId + '&date='  
+ new Date().getTime());  
  
$(editRow.target).combobox({onSelect:function(){  
synchProduction(getEditRow(index,'productionId'),index,$(this).combobox('getValue'));  
}});  
}
  
// 加载品牌（production）  
function synchProduction(editRow,index,brandId){  
jQuery(editRow.target).combobox('reload','production/findProductionByAjax.action?brandId=' + brandId + '&date=' + new Date().getTime());  
}  
  
// 加载仓库   
function synchStore(editRow){  
jQuery(editRow.target).combobox('reload','store/findSuperStoreByAjax.action?date='  
+ new Date().getTime());  
}
  
// 获取需要编辑的控件  
function getEditRow(lastIndex,field){  
return category = jQuery('#detailTable').datagrid('getEditor', {    
        index : lastIndex,    
        field : field  
});  
}  
</script>
</head>
<body>
	<table id="detailTable" style="width:auto;height:auto"  
title="采购明细">  
<thead>  
<tr>  
<th data-options="field:'categoryId',width:120,align:'center',  
editor:{  
type:'combobox',  
options:{  
valueField:'superId',  
textField:'superName',  
required:true  
}  
}">类别  
</th>  
<th data-options="field:'brandId',width:120,align:'center',  
editor:{  
type:'combobox',  
options:{  
valueField:'superId',  
textField:'superName',  
required:true  
}  
}">品牌  
</th>  
<th data-options="field:'productionId',width:120,align:'center',  
editor:{  
type:'combobox',  
options:{  
valueField:'id',  
textField:'name',  
required:true  
}  
}">产品  
</th>  
<th data-options="field:'storeId',width:120,align:'center',  
editor:{  
type:'combobox',  
options:{  
valueField:'superId',  
textField:'superName',  
required:true  
}  
}">仓库  
</th>  
<th data-options="field:'price',width:70,align:'center',editor:'numberbox'">单价</th>  
<th data-options="field:'count',width:70,align:'center',editor:'numberbox'">数量</th>  
<th data-options="field:'total',width:70,align:'center',editor:'numberbox'">总价</th>  
<th data-options="field:'prePay',width:70,align:'center',editor:'numberbox'">预付</th>  
</tr>  
</thead>  
</table>
</body>
</html>