<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/TsbProjectPage.js"></script>
<script>
var tsbProjectPageId="i_sy_tsbProjectPage_datagrid";
var tsbProjectPageDt,tsbProjectPageUploadDg,tsbProjectPageUploadFm;
var g_viewModal=false;
var columns= [ [
            	 {
            		field : 'id',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'pid',
            		title:'父节点编号', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'parentId',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'pageName',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'pageContent',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'nodeId',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'updateTime',
            		title:'更新时间', 
            		addform:{type:'eType.DateTimeBox'}, 
            		editform:{type:'eType.DateTimeBox'}, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_tsbProjectPage_datagrid_add_dialog').dialog({
		onOpen:function(){
			initAddFileDt();
			tsbProjectPageAddOnOpen();
		}
	});
	
	$('#i_sy_tsbProjectPage_datagrid_edit_dialog').dialog({
		onOpen:function(){
			initEditFileDt();
			tsbProjectPageEditOnOpen();
		}
	});
	pageView(tsbProjectPageId,columns);
	tsbProjectPageonload();
});

function updateFun(d){
	if(d.info=='新增成功!'){
		var setData={'ids':g_Ids,'fileid':d.fileid};
		data(getUrl('syFile','Update'),setData,'json',null);
	}else if(d.info=='删除成功!'){
		var setData={'fileids':d.fileids};
		data(getUrl('syFile','Delete_ByFileIds'),setData,'json',null);
	}
}

//模式add
function pageView_add(){
	$('#'+tsbProjectPageId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+tsbProjectPageId+'_add_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+tsbProjectPageId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['tsbProjectPage'+"."+field]=eGet('#'+tsbProjectPageId+'_add_form_'+field);
		}
		data(getUrl('tsbProjectPage','Add_HasFiles'),setData,'json',updateFun);
	});
	$('#'+tsbProjectPageId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('tsbProjectPage','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,tsbProjectPageId);
			initEditFileDt();
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+tsbProjectPageId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+tsbProjectPageId+'_edit_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+tsbProjectPageId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['tsbProjectPage'+"."+field]=eGet('#'+tsbProjectPageId+'_edit_form_'+field);
		}
		data(getUrl('tsbProjectPage','Update'),setData,'json',updateFun);
	});
	$('#'+tsbProjectPageId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var tsbProjectPageDataGrid = {
			id:tsbProjectPageId,
			url:'${ctx}'+actionUrl('/sys/','tsbProjectPage','List'),
			dId:'id',
			columns:columns
	};
	
	tsbProjectPageUploadDg = $('#i_sy_tsbProjectPage_datagrid_upload_dialog');
	tsbProjectPageUploadFm =$('#i_sy_tsbProjectPage_datagrid_upload_dialog_form');
	tsbProjectPageUploadFm.attr('action','${ctx}'+actionUrl('/sys/','tsbProjectPage','Upload'));
	
	tsbProjectPageDt=gGrid2(tsbProjectPageDataGrid);	
	var straddfun="dorow(tsbProjectPageId,'','${ctx}"+actionUrl('/sys/','tsbProjectPage','Add_HasFiles')+"',updateFun,'c')";
	gDataGridToolbarBtn(tsbProjectPageId,'icon-add',straddfun,"新增");
	var stredit="dorow(tsbProjectPageId,'','${ctx}"+actionUrl('/sys/','tsbProjectPage','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(tsbProjectPageId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(tsbProjectPageId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','tsbProjectPage','Delete_HasFiles')+"',updateFun,'d')";
	gDataGridToolbarBtn(tsbProjectPageId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(tsbProjectPageId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','tsbProjectPage','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(tsbProjectPageId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(tsbProjectPageId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(tsbProjectPageId,'icon-page_find','doroodo_search()',"复合查询");
}

function doroodo_search(){
	var searchObj=$.window({
		title :'查询构造器',
		url : top.sysPath+'/component/search.jsp?topthemeName='+top.themeName,
		isIframe : true,
		height : 260,
		width : 800,
		winId : 'searchdig'+new Date().getTime(),
		target : 'self',
		maximizable : true,
		buttons : [ {
			text : '查询',
			handler : function() {
				var obj = searchObj.find('iframe')[0].contentWindow;
				tsbProjectPageDt.datagrid('load', obj.getSearchs('tsbProjectPage'));
				searchObj.window('destroy');
			}
		},{
			text : '取消',
			handler : function() {
				searchObj.window('destroy');
			}
		}],
		onComplete: function() {
			var obj = searchObj.find('iframe')[0].contentWindow;
			obj.setSearchColumns(columns);
		}
    });
}

function upLoadFun(){
	tsbProjectPageUploadDg.dialog('open');
}

var g_fileid='';
function initAddFileDt(){
	g_fileid='';
	$('#i_sy_tsbProjectPage_datagrid_edit_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/empty.jsp');
	$('#i_sy_tsbProjectPage_datagrid_add_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/SyFile_.jsp');
}

function initEditFileDt(){
	if(eGet('#'+tsbProjectPageId+'_edit_form_id')=='')return;
	g_fileid='tsbProjectPage-'+eGet('#'+tsbProjectPageId+'_edit_form_id');
	$('#i_sy_tsbProjectPage_datagrid_add_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/empty.jsp');
	$('#i_sy_tsbProjectPage_datagrid_edit_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/SyFile_.jsp');
}

function submitUploadForm(){
	tsbProjectPageUploadFm.form('submit',{
		success:function(d){
			tsbProjectPageDt.datagrid('reload');
			tsbProjectPageUploadDg.dialog('close');
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
			 var obj = $(n);
		     if(!obj.is('a')){
		    	var id=obj.attr('id');
		    	if(id){
		    		gobj.html(eGet('#'+id));
		    	}
		     }
		    });
	});
	form.children().each(function(i,n){
   	 $('*',$(n)).each(function(ii,nn){
   		 if($(nn).css("display")=='none'){
   			 $(nn).remove();
   		 }
   	 });
	    });
	$('script',form).remove();
	var setData={'tableHtml':'<div class="titlep">'+title+'</div>'+form.html(),'tableTitle':title};
		data(getUrl('tsbProjectPage', 'FormFile'),setData,
		'json',  function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}
		});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_tsbProjectPage_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_tsbProjectPage_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_tsbProjectPage_datagrid_searchbox"
					pdt="i_sy_tsbProjectPage_datagrid"></input>
					<div id="i_sy_tsbProjectPage_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_tsbProjectPage_datagrid"></table> 
 
 <div id="i_sy_tsbProjectPage_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_tsbProjectPage_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
<div class="titlep">附件</div>
<div id="i_sy_tsbProjectPage_datagrid_add_form_file_form"></div>
</div>
</div>
<div id="i_sy_tsbProjectPage_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_tsbProjectPage_datagrid_edit_btn',toolbar:[{
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

<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
<div class="titlep">附件</div>
<div id="i_sy_tsbProjectPage_datagrid_edit_form_file_form"></div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_tsbProjectPage_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_tsbProjectPage_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_tsbProjectPage_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_tsbProjectPage_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
