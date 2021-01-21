<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
</head>
<body>
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

<div id="i_sy_syFile_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:200px;" 
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,toolbar:'#i_sy_syFile_datagrid_upload_dialog_form_upload_toolbar',buttons:'#i_sy_syFile_datagrid_upload_dialog_form_upload_btn'"> 
          <form id="i_sy_syFile_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    		<div><input type="file" name="fileGroup" /></div>
        	</form>
        	<div id="i_sy_syFile_datagrid_upload_dialog_form_upload_toolbar">
	    		<a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" onclick="addFileInput()">增加</a>
			</div>
        	<div id="i_sy_syFile_datagrid_upload_dialog_form_upload_btn">
        	<strong style="display:none;color:green" id="uploadstate"><span style="padding:3px;background:url(/doroodo/images/loading.gif) no-repeat;">&nbsp&nbsp&nbsp&nbsp</span>上传中……</strong>
				<a href="#" class="easyui-linkbutton" onClick="submitUploadForm();"  id="i_sy_syFile_datagrid_upload_dialog_form_upload_btn_"
					data-options="iconCls:'icon-edit',plain:true">上传</a>
			</div>
</div>  
<script>
function addFileInput(){
	$('div:last',$('#i_sy_syFile_datagrid_upload_dialog_form')).after('<div><input type="file" name="fileGroup"/><a href="#" class="easyui-linkbutton" data-options="iconCls:\'icon-cancel\',plain:true" onclick="$(this).parent().remove();">删除</a></div>');
	$.parser.parse($('div:last',$('#i_sy_syFile_datagrid_upload_dialog_form')));
}

var syFileId="i_sy_syFile_datagrid";
var syFileDt,syFileUploadDg,syFileUploadFm;
var syFileColumns= [ [
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
            		hidden:true, 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'filename',
            		title:'文件名', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150',
            		formatter: function(value,row,index){
            			//var url=top.sysPath+'/fileupload/'+row.sysname;
            			//return '<a href="javascript:void(0)" onclick="artPicShow(\''+row.filename+'\',\''+ url+'\')" >' + row.filename + '</a>';
            			return '<a href="'+top.sysPath+'/fileupload/'+row.sysname+'" name="'+row.id+'" target="view_frame" >'+row.filename+'</a>';
            		}
            	  }, 
            	 {
            		field : 'sysname',
            		title:'系统文件名', 
            		hidden:true, 
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
	syFileUploadDg = $('#i_sy_syFile_datagrid_upload_dialog');
	syFileUploadFm =$('#i_sy_syFile_datagrid_upload_dialog_form');
	if(g_viewModal){
		syFileColumns=[ [
		            	 {
		             		field : 'id',
		             		hidden:true, 
		             		title:'编号', 
		             		addform:{type:'eType.Input'}, 
		             		editform:{type:'eType.Input'}, 
		             		width : '150'
		             	  }, 
		             	 {
		             		field : 'fileid',
		             		title:'关系ID', 
		             		hidden:true, 
		             		addform:{type:'eType.Input'}, 
		             		editform:{type:'eType.Input'}, 
		             		width : '150'
		             	  }, 
		             	 {
		             		field : 'filename',
		             		title:'文件名', 
		             		addform:{type:'eType.Input'}, 
		             		editform:{type:'eType.Input'}, 
		             		width : '150',
		             		formatter: function(value,row,index){
		             			return '<a href="'+top.sysPath+'/fileupload/'+row.sysname+'" name="'+row.id+'" target="view_frame" >'+row.filename+'</a>';
		            			//var url=top.sysPath+'/fileupload/'+row.sysname;
		            			//return '<a href="javascript:void(0)" onclick="artPicShow(\''+row.filename+'\',\''+ url+'\')" >' + row.filename + '</a>';
		            		}
		             	  }, 
		             	 {
		             		field : 'sysname',
		             		title:'系统文件名', 
		             		hidden:true, 
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
	}
	pageView_list();
	if(g_viewModal){
		viewModal();
	}
});


function artPicShow(title,destUrl){
	alert(destUrl);
	top.art.dialog({
	    padding: 0,
	    title: title,
	    content: '<img src='+destUrl+' width="379" height="500" />',
	    lock: true
	});
}

//pdf显示
function pdfView(title,destUrl){
	var swfname =destUrl;
	var pdfObj=$.window({
		title :title,
		url : top.sysPath+'/plug/flexPaper/index.html',
		isIframe : true,
		height : 400,
		width : 800,
		winId : 'pdfview'+new Date().getTime(),
		target : 'self',
		maximizable : false,
		onComplete: function() {
			var obj = pdfObj.find('iframe')[0].contentWindow;
			obj.showPdf(swfname);
		}
	});
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
		for(var i=0; i<syFileColumns[0].length; i++){
			var field=syFileColumns[0][i].field;
			var title=syFileColumns[0][i].title;
			if(!checkFormField('#'+syFileId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syFile'+"."+field]=eGet('#'+syFileId+'_add_form_'+field);
		}
		data(getUrl('syFile','Add'),setData,'json',flowCall);
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
		for(var i=0; i<syFileColumns[0].length; i++){
			var field=syFileColumns[0][i].field;
			var title=syFileColumns[0][i].title;
			if(!checkFormField('#'+syFileId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syFile'+"."+field]=eGet('#'+syFileId+'_edit_form_'+field);
		}
		data(getUrl('syFile','Update'),setData,'json',flowCall);
	});
	$('#'+syFileId+'_edit_dialog').dialog('open');
}

var g_Ids='';
//模式list （通过链接pageView=list访问）
function pageView_list(){
	var listUrl='';
	if(g_fileid==''){
		listUrl='${ctx}'+actionUrl('/sys/','syFile','List_Null');
	}else{
		listUrl='${ctx}'+actionUrl('/sys/','syFile','List')+'?fileid='+g_fileid;
	}

	var syFileDataGrid = {
			id:syFileId,
			url:listUrl,
			dId:'id',
			columns:syFileColumns
	};
	
	
	syFileDt=gGrid2(syFileDataGrid);	
	gDataGridToolbarBtn(syFileId,'icon-add','upLoadFun()',"新增");
	var strdelfun="dorow(syFileId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syFile','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syFileId,'icon-remove',strdelfun,"删除");
	//var strexcelfun="dorow(syFileId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syFile','Excel')+"',updateFun,'e')";
	//gDataGridToolbarBtn(syFileId,'icon-page_white_excel',strexcelfun,"导出");
}

function upLoadFun(){
	syFileUploadDg.dialog('open');
}

function arryyToStrs(obj){
	var strs='';
	for(var i=0;i<obj.length;i++){
		strs+=obj[i].id+',';
	}
	return strs;
}

function submitUploadForm(){
	var listUrl;
	syFileUploadFm.form('submit',{
		url:'${ctx}'+actionUrl('/sys/','syFile','Upload')+'?fileid='+g_fileid,
		onSubmit:function(){
			$('#i_sy_syFile_datagrid_upload_dialog_form_upload_btn_').linkbutton('disable');
			$('#uploadstate').show();
		},
		success:function(d){
			$('#uploadstate').hide();
			$('#i_sy_syFile_datagrid_upload_dialog_form_upload_btn_').linkbutton('enable');
			d=$.parseJSON(d);
			if(d.error){
				return 普通窗体('是','上传文件失败',d.error);
			}
			g_Ids+=arryyToStrs(d.syFiles);
			if(g_fileid==''){
				listUrl='${ctx}'+actionUrl('/sys/','syFile','List')+"?ids="+g_Ids;
			}else{
				listUrl='${ctx}'+actionUrl('/sys/','syFile','List')+'?fileid='+g_fileid;
			}
			syFileDt.datagrid({url:listUrl,
				columns:syFileColumns});
			syFileUploadDg.dialog('close');
			log(d.info);
		}
	});
}

function viewModal(){
	$(syFileDt.datagrid('options').toolbar).html('');//隐藏工具栏
}
</script>
</body>
</html>
