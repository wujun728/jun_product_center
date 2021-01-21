<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyDebugState.js"></script>
<script>
var syDebugStateId="i_sy_syDebugState_datagrid";
var syDebugStateDt,syDebugStateUploadDg,syDebugStateUploadFm;
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
            		field : 'stateName',
            		checkbox:false, 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		title:'状态名称', 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_syDebugState_datagrid_add_dialog').dialog({
		onOpen:function(){
			syDebugStateAddOnOpen();
		}
	});
	
	$('#i_sy_syDebugState_datagrid_edit_dialog').dialog({
		onOpen:function(){
			syDebugStateEditOnOpen();
		}
	});
	pageView(syDebugStateId,columns);
	syDebugStateonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+syDebugStateId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDebugStateId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDebugStateId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDebugState'+"."+field]=eGet('#'+syDebugStateId+'_add_form_'+field);
		}
		data(getUrl('syDebugState','Add'),setData,'json',null);
	});
	$('#'+syDebugStateId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syDebugState','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syDebugStateId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syDebugStateId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDebugStateId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDebugStateId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDebugState'+"."+field]=eGet('#'+syDebugStateId+'_edit_form_'+field);
		}
		data(getUrl('syDebugState','Update'),setData,'json',null);
	});
	$('#'+syDebugStateId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var syDebugStateDataGrid = {
			id:syDebugStateId,
			url:'${ctx}'+actionUrl('/sys/','syDebugState','List'),
			dId:'id',
			columns:columns
	};
	
	syDebugStateUploadDg = $('#i_sy_syDebugState_datagrid_upload_dialog');
	syDebugStateUploadFm =$('#i_sy_syDebugState_datagrid_upload_dialog_form');
	syDebugStateUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syDebugState','Upload'));
	
	syDebugStateDt=gGrid2(syDebugStateDataGrid);	
	var straddfun="dorow(syDebugStateId,'','${ctx}"+actionUrl('/sys/','syDebugState','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syDebugStateId,'icon-add',straddfun,"新增");
	var stredit="dorow(syDebugStateId,'','${ctx}"+actionUrl('/sys/','syDebugState','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syDebugStateId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(syDebugStateId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syDebugState','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syDebugStateId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syDebugStateId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syDebugState','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syDebugStateId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syDebugStateId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	syDebugStateUploadDg.dialog('open');
}

function submitUploadForm(){
	syDebugStateUploadFm.form('submit',{
		success:function(d){
			syDebugStateDt.datagrid('reload');
			syDebugStateUploadDg.dialog('close');
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
		data(getUrl('syDebugState', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syDebugState_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syDebugState_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syDebugState_datagrid_searchbox"
					pdt="i_sy_syDebugState_datagrid"></input>
					<div id="i_sy_syDebugState_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syDebugState_datagrid"></table> 
 
 <div id="i_sy_syDebugState_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syDebugState_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syDebugState_datagrid_add_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td class="label" style="width:15%;" align="right">状态名称</td><td style="width:85%;" align="left"><input id="i_sy_syDebugState_datagrid_add_form_stateName" reftype="-1" type="text"></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:85%;display:none;" align="left"><input id="i_sy_syDebugState_datagrid_add_form_id" reftype="-1" type="text"></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syDebugState_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syDebugState_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_syDebugState_datagrid_edit_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td class="label" style="width:15%;" align="right">状态名称</td><td style="width:85%;" align="left"><input id="i_sy_syDebugState_datagrid_edit_form_stateName" reftype="-1" type="text"></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:85%;display:none;" align="left"><input id="i_sy_syDebugState_datagrid_edit_form_id" reftype="-1" type="text"></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syDebugState_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syDebugState_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syDebugState_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syDebugState_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
