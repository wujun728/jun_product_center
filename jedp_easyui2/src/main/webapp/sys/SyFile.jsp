<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
var syFileId="i_sy_syFile_datagrid";
var syFileDt,syFileUploadDg,syFileUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		title:'编号', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'fileid',
            		title:'关系ID', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'filename',
            		title:'文件名', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'sysname',
            		title:'系统文件名', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'userid',
            		title:'上传用户ID', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'createtime',
            		title:'上传时间', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	var urls=window.location.href.split('?');
	var hasProcKey=$.getUrlParam('processDefinitionKey');
	if(hasProcKey){
	var p='';
	if(urls.length>1){p=urls[1]}{data(getUrl('syFlow','Get_Toobar')+'?'+p,'','json',wbuild);}
	}else{
		pageView(syFileId,columns);
	}
});

function wbuild(d){
	if(d){
		getWorkFlowBtn(d.commandList);
	}
	pageView(syFileId,columns);
}

function updateFun(d){
	
}

//模式add （通过链接pageView=add访问）
function pageView_add(){
	$('#'+syFileId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syFileId+'_add_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syFileId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syFile'+"."+field]=eGet('#'+syFileId+'_add_form_'+field);
		}
		data(getUrl('syFile','Add'),setData,'json',null);
	});
	$('#'+syFileId+'_add_dialog').dialog('open');
}

//模式edit （通过链接pageView=edit访问）
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syFile','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syFileId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syFileId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syFileId+'_edit_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syFileId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syFile'+"."+field]=eGet('#'+syFileId+'_edit_form_'+field);
		}
		data(getUrl('syFile','Update'),setData,'json',null);
	});
	$('#'+syFileId+'_edit_dialog').dialog('open');
}

//模式list （通过链接pageView=list访问）
function pageView_list(){
	//fileid
	var fileid=$.getUrlParam('fileid');
	var listUrl='${ctx}'+actionUrl('/sys/','syFile','List');
	var addUrl='${ctx}'+actionUrl('/sys/','syFile','Upload');
	if(fileid){
		addUrl+='?fileid='+fileid;
		listUrl+='?fileid='+fileid;
	}
	var syFileDataGrid = {
			id:syFileId,
			url:listUrl,
			dId:'id',
			columns:columns
	};
	
	syFileUploadDg = $('#i_sy_syFile_datagrid_upload_dialog');
	syFileUploadFm =$('#i_sy_syFile_datagrid_upload_dialog_form');
	syFileUploadFm.attr('action',addUrl);
	
	syFileDt=gGrid2(syFileDataGrid);	
	gDataGridToolbarBtn(syFileId,'icon-add','upLoadFun()',"新增");
	var strdelfun="dorow(syFileId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syFile','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syFileId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syFileId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syFile','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syFileId,'icon-page_white_excel',strexcelfun,"导出");
}

function upLoadFun(){
	syFileUploadDg.dialog('open');
}

function submitUploadForm(){
	syFileUploadFm.form('submit',{
		success:function(d){
			syFileDt.datagrid('reload');
			syFileUploadDg.dialog('close');
			d=$.parseJSON(d);
			log(d.info);
			}
	});
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syFile_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syFile_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syFile_datagrid_searchbox"
					pdt="i_sy_syFile_datagrid"></input>
					<div id="i_sy_syFile_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syFile_datagrid"></table> 
 
 <div id="i_sy_syFile_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syFile_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syFile_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syFile_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">编辑</div>
<!-- 请填入编辑表单html  start -->

<!-- 请填入编辑表单html  end -->
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syFile_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syFile_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syFile_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syFile_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
