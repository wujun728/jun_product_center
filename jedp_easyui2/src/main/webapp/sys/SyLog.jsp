<%@page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
var syLogId="i_sy_syLog_datagrid";
var syLogDt;
var columns= [ [
            	 {
            		field : 'id',
            		title : '编号',
            		width : 150,
            		checkbox : true
            	  }, 
            	 {
            		field : 'logDate',
            		title : '时间',
            		width : '150'
            	  }, 
            	 {
            		field : 'logLevel',
            		hidden:true,
            		width : '150'
            	  }, 
            	 {
            		field : 'location',
            		hidden:true,
            		width : '150'
            	  }, 
            	 {
            		field : 'message',
            		title : '日志内容',
            		width : '150'
            	  } 
            	] ];
$(function(){
	pageView(syLogId,columns);
});

function reshDt(){
	//window.setInterval(reshDt, 60000);
	syLogDt.datagrid('load');
}

function updateFun(d){
	
}

//模式add （通过链接pageView=add访问）
function pageView_add(){
	$('#'+syLogId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syLogId+'_add_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syLogId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData[syLog+"."+field]=eGet('#'+syLogId+'_add_form_'+field);
		}
		data(getUrl('syLog','Add'),setData,'json',null);
	});
	$('#'+syLogId+'_add_dialog').dialog('open');
}

//模式edit （通过链接pageView=edit访问）
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syLog','GetById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syLogId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syLogId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syLogId+'_edit_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syLogId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syLog'+"."+field]=eGet('#'+syLogId+'_edit_form_'+field);
		}
		data(getUrl('syLog','Update'),setData,'json',null);
	});
	$('#'+syLogId+'_edit_dialog').dialog('open');
}

//模式list （通过链接pageView=list访问）
function pageView_list(){
	var syLogDataGrid = {
			id:syLogId,
			url:'${ctx}'+actionUrl('/sys/','syLog','List'),
			dId:'id',
			pagination:true,
			columns:columns
	};
	
	syLogDt=gGrid2(syLogDataGrid);	
	/*
	var straddfun="dorow(syLogId,'','${ctx}"+actionUrl('/sys/','syLog','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syLogId,'icon-add',straddfun,"新增");
	var stredit="dorow(syLogId,'','${ctx}"+actionUrl('/sys/','syLog','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syLogId,'icon-edit',stredit,"修改");
	*/
	var strdelfun="dorow(syLogId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syLog','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syLogId,'icon-remove',strdelfun,"删除");
	gDataGridToolbarBtn(syLogId,'icon-arrow_refresh_small','reshDt()',"刷新");
	var strexcelfun="dorow(syLogId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syLog','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syLogId,'icon-page_white_excel',strexcelfun,"导出");
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syLog_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syLog_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syLog_datagrid_searchbox"
					pdt="i_sy_syLog_datagrid"></input>
					<div id="i_sy_syLog_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syLog_datagrid"></table> 
 
 <div id="i_sy_syLog_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syLog_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syLog_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syLog_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">编辑</div>
<!-- 请填入编辑表单html  start -->

<!-- 请填入编辑表单html  end -->
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syLog_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syLog_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

</body>
</html>
