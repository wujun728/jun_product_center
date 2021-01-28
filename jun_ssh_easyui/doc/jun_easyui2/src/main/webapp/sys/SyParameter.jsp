<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
var syParameterId="i_sy_syParameter_datagrid";
var syParameterDt;
var columns= [ [ {
	field : 'id',
	title : '编号',
	width : 150,
	checkbox : true,
	form : {
		hidden : true
	}
}, {
	field : 'title',
	title : '平台名称',
	width : 150
}, {
	field : 'navtitle',
	title : '导航名称',
	width : 150,
	form:{
		type:eType.HtmlEdit
	}
}, {
	field : 'footer',
	title : '页脚显示',
	width : 150,
	form:{
		type:eType.HtmlEdit
	}
} ] ];
$(function(){
	pageView(syParameterId,columns);
});

function updateFun(d){
	
}

//模式add （通过链接pageView=add访问）
function pageView_add(){
	$('#'+syParameterId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syParameterId+'_add_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syParameterId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData[syParameter+"."+field]=eGet('#'+syParameterId+'_add_form_'+field);
		}
		data(getUrl('syParameter','Add'),setData,'json',null);
	});
	$('#'+syParameterId+'_add_dialog').dialog('open');
}

//模式edit （通过链接pageView=edit访问）
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syParameter','GetById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syParameterId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syParameterId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syParameterId+'_edit_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syParameterId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syParameter'+"."+field]=eGet('#'+syParameterId+'_edit_form_'+field);
		}
		data(getUrl('syParameter','Update'),setData,'json',null);
	});
	$('#'+syParameterId+'_edit_dialog').dialog('open');
}

//模式list （通过链接pageView=list访问）
function pageView_list(){
	var syParameterDataGrid = {
			id:syParameterId,
			url:'${ctx}'+actionUrl('/sys/','syParameter','List'),
			dId:'id',
			columns:columns
	};
	
	syParameterDt=gGrid2(syParameterDataGrid);	
	var straddfun="dorow(syParameterId,'','${ctx}"+actionUrl('/sys/','syParameter','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syParameterId,'icon-add',straddfun,"新增");
	var stredit="dorow(syParameterId,'','${ctx}"+actionUrl('/sys/','syParameter','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syParameterId,'icon-edit',stredit,"修改");
	var strsetdefultfun="dorow(syParameterId,'您是否确定要设置当前选中配置为默认','${ctx}"+actionUrl('/sys/','syParameter','Set_Defult')+"',setDefultFun,'s')";
	gDataGridToolbarBtn(syParameterId,'icon-tip',strsetdefultfun,"设置为默认");
	var strdelfun="dorow(syParameterId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syParameter','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syParameterId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syParameterId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syParameter','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syParameterId,'icon-page_white_excel',strexcelfun,"导出");
}

function setDefultFun(d){
	top.setWeb();
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syParameter_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syParameter_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syParameter_datagrid_searchbox"
					pdt="i_sy_syParameter_datagrid"></input>
					<div id="i_sy_syParameter_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syParameter_datagrid"></table> 
 
 <div id="i_sy_syParameter_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syParameter_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syParameter_datagrid_add_form"><table class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" align="center">平台名称</td><td align="center"><input id="i_sy_syParameter_datagrid_add_form_title" reftype="-1" type="text"/></td></tr><tr><td class="label" align="center">导航名称</td><td align="center"><div id="i_sy_syParameter_datagrid_add_form_navtitle" reftype="13"></div></td></tr><tr><td class="label" align="center">页脚显示</td><td align="center"><div id="i_sy_syParameter_datagrid_add_form_footer" reftype="13"></div></td></tr><tr><td class="label" style="display:none;" align="center">编号</td><td style="display:none;" align="center"><input id="i_sy_syParameter_datagrid_add_form_id" reftype="-1" type="text"/></td></tr></tbody></table></form><script>var uedit = new UE.ui.Editor(); uedit.render("i_sy_syParameter_datagrid_add_form_navtitle");top.formfieldmap.put("i_sy_syParameter_datagrid_add_form_navtitle",uedit);</script><script>var uedit = new UE.ui.Editor(); uedit.render("i_sy_syParameter_datagrid_add_form_footer");top.formfieldmap.put("i_sy_syParameter_datagrid_add_form_footer",uedit);</script>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syParameter_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syParameter_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">编辑</div>
<!-- 请填入编辑表单html  start -->
<form id="i_sy_syParameter_datagrid_edit_form"><table class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" align="center">平台名称</td><td align="center"><input id="i_sy_syParameter_datagrid_edit_form_title" reftype="-1" type="text"/></td></tr><tr><td class="label" align="center">导航名称</td><td align="center"><div id="i_sy_syParameter_datagrid_edit_form_navtitle" reftype="13"></div></td></tr><tr><td class="label" align="center">页脚显示</td><td align="center"><div id="i_sy_syParameter_datagrid_edit_form_footer" reftype="13"></div></td></tr><tr><td class="label" style="display:none;" align="center">编号</td><td style="display:none;" align="center"><input id="i_sy_syParameter_datagrid_edit_form_id" reftype="-1" type="text"/></td></tr></tbody></table></form><script>var uedit = new UE.ui.Editor(); uedit.render("i_sy_syParameter_datagrid_edit_form_navtitle");top.formfieldmap.put("i_sy_syParameter_datagrid_edit_form_navtitle",uedit);</script><script>var uedit = new UE.ui.Editor(); uedit.render("i_sy_syParameter_datagrid_edit_form_footer");top.formfieldmap.put("i_sy_syParameter_datagrid_edit_form_footer",uedit);</script>
<!-- 请填入编辑表单html  end -->
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syParameter_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syParameter_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

</body>
</html>
