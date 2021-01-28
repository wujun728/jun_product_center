<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyDemoTreeiframe.js"></script>
<script>
var syDemoTreeiframeId="i_sy_syDemoTreeiframe_datagrid";
var syDemoTreeiframeDt,syDemoTreeiframeUploadDg,syDemoTreeiframeUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		title:'编号', 
            		width : '150'
            	  }, 
            	 {
            		field : 'routeId',
            		title:'组织id', 
            		addform:{type:'eType.ValidateBox', hidden:true}, 
            		editform:{type:'eType.ValidateBox', hidden:true}, 
            		hidden:true, 
            		width : '150'
            	  }, 
            	 {
            		field : 'routeName',
            		title:'关系路由', 
            		addform:{type:'eType.ValidateBox', hidden:true}, 
            		editform:{type:'eType.ValidateBox', hidden:true}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'name',
            		title:'姓名', 
            		addform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'pid',
            		title:'上级分类', 
            		addform:{type:'eType.ComboTree', dataoptions:"valueField:'id', url:'/doroodo/sys/syDemoTreeiframe_Get_Tree'"}, 
            		editform:{type:'eType.ComboTree', dataoptions:"valueField:'id', url:'/doroodo/sys/syDemoTreeiframe_Get_Tree'"}, 
            		width : '150'
            	  } 
            	] ];
var pid=null;
$(function(){
	$('#i_sy_syDemoTreeiframe_datagrid_add_dialog').dialog({
		onOpen:function(){
			syDemoTreeiframeAddOnOpen();
		}
	});
	
	$('#i_sy_syDemoTreeiframe_datagrid_edit_dialog').dialog({
		onOpen:function(){
			syDemoTreeiframeEditOnOpen();
		}
	});
	pid=$.getUrlParam('id');
	pageView(syDemoTreeiframeId,columns);
	syDemoTreeiframeonload();
});

function updateFun(d){
	 window.parent.window.reloadTree();
}

//模式add
function pageView_add(){
	$('#'+syDemoTreeiframeId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDemoTreeiframeId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDemoTreeiframeId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDemoTreeiframe'+"."+field]=eGet('#'+syDemoTreeiframeId+'_add_form_'+field);
		}
		data(getUrl('syDemoTreeiframe','Add'),setData,'json',null);
	});
	$('#'+syDemoTreeiframeId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syDemoTreeiframe','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syDemoTreeiframeId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syDemoTreeiframeId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDemoTreeiframeId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDemoTreeiframeId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDemoTreeiframe'+"."+field]=eGet('#'+syDemoTreeiframeId+'_edit_form_'+field);
		}
		data(getUrl('syDemoTreeiframe','Update'),setData,'json',null);
	});
	$('#'+syDemoTreeiframeId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var syDemoTreeiframeDataGrid = {
			id:syDemoTreeiframeId,
			url:'${ctx}'+actionUrl('/sys/','syDemoTreeiframe','List_ByPid')+"?syDemoTreeiframe.id="+pid,
			dId:'id',
			columns:columns
	};
	
	syDemoTreeiframeUploadDg = $('#i_sy_syDemoTreeiframe_datagrid_upload_dialog');
	syDemoTreeiframeUploadFm =$('#i_sy_syDemoTreeiframe_datagrid_upload_dialog_form');
	syDemoTreeiframeUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syDemoTreeiframe','Upload'));
	
	syDemoTreeiframeDt=gGrid2(syDemoTreeiframeDataGrid);	
	var straddfun="dorow(syDemoTreeiframeId,'','${ctx}"+actionUrl('/sys/','syDemoTreeiframe','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syDemoTreeiframeId,'icon-add',straddfun,"新增");
	var stredit="dorow(syDemoTreeiframeId,'','${ctx}"+actionUrl('/sys/','syDemoTreeiframe','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syDemoTreeiframeId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(syDemoTreeiframeId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syDemoTreeiframe','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syDemoTreeiframeId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syDemoTreeiframeId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syDemoTreeiframe','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syDemoTreeiframeId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syDemoTreeiframeId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	syDemoTreeiframeUploadDg.dialog('open');
}

function submitUploadForm(){
	syDemoTreeiframeUploadFm.form('submit',{
		success:function(d){
			syDemoTreeiframeDt.datagrid('reload');
			syDemoTreeiframeUploadDg.dialog('close');
			d=$.parseJSON(d);
			log(d.info);
			}
	});
}

function getEditFormHtml(title,type){
	var form=$('#report').clone();
	var word=$('table',form);
	title=title+"详细资料";
	$('td', word).each(function() {
		var gobj = $(this);
		gobj.children().each(function(i,n){
		     var obj = $(n)
		    	var id=obj.attr('id');
		    	if(id){
		    		gobj.html(eGet('#'+id));
		    	}
		    });
	});
	form.children().each(function(i,n){
    	 $('tr',$(n)).each(function(ii,nn){
    		 if($(nn).attr('style')=='display:none;'){
    			 $(nn).remove();
    		 }
    	 });
	    });
	var setData={'tableHtml':'<div class="titlep">'+title+'</div>'+form.html(),'tableTitle':title};
		data(getUrl('syDemoTreeiframe', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body>
<div id="i_sy_syDemoTreeiframe_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syDemoTreeiframe_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syDemoTreeiframe_datagrid_searchbox"
					pdt="i_sy_syDemoTreeiframe_datagrid"></input>
					<div id="i_sy_syDemoTreeiframe_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syDemoTreeiframe_datagrid"></table> 
 
 <div id="i_sy_syDemoTreeiframe_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syDemoTreeiframe_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syDemoTreeiframe_datagrid_add_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td class="label" style="width:15%;" align="right">姓名</td><td style="width:35%;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_add_form_name" class="easyui-validatebox" data-options="required:true" reftype="0" type="text"></td><td class="label" style="width:15%;" align="right">上级分类</td><td style="width:35%;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_add_form_pid" class="easyui-combotree" data-options="valueField:'id', url:'/doroodo/sys/syDemoTreeiframe_Get_Tree'" reftype="3" type="text"></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_add_form_id" reftype="-1" type="text"></td><td class="label" style="width:15%;display:none;" align="right">组织id</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_add_form_routeId" class="easyui-validatebox" reftype="0" type="text"></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">关系路由</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_add_form_routeName" class="easyui-validatebox" reftype="0" type="text"></td><td></td><td></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syDemoTreeiframe_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syDemoTreeiframe_datagrid_edit_btn',toolbar:[{
				text:'导出',
				iconCls:'icon-page_white_excel',
				handler:function(){
					getEditFormHtml('编辑','excel');//请修改
				}
			},
			{
				text:'导出',
				iconCls:'icon-page_white_word',
				handler:function(){
					getEditFormHtml('编辑','word');//请修改
				}
			}]"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">编辑</div>
<div id="report">
<!-- 请填入编辑表单html  start -->
<form id="i_sy_syDemoTreeiframe_datagrid_edit_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td class="label" style="width:15%;" align="right">姓名</td><td style="width:35%;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_edit_form_name" class="easyui-validatebox" data-options="required:true" reftype="0" type="text"></td><td class="label" style="width:15%;" align="right">上级分类</td><td style="width:35%;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_edit_form_pid" class="easyui-combotree" data-options="valueField:'id', url:'/doroodo/sys/syDemoTreeiframe_Get_Tree'" reftype="3" type="text"></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_edit_form_id" reftype="-1" type="text"></td><td class="label" style="width:15%;display:none;" align="right">组织id</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_edit_form_routeId" class="easyui-validatebox" reftype="0" type="text"></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">关系路由</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDemoTreeiframe_datagrid_edit_form_routeName" class="easyui-validatebox" reftype="0" type="text"></td><td></td><td></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syDemoTreeiframe_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syDemoTreeiframe_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syDemoTreeiframe_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syDemoTreeiframe_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
