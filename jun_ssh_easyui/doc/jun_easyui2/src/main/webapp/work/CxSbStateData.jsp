<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/CxSbStateData.js"></script>
<script>
var cxSbStateDataId="i_sy_cxSbStateData_datagrid";
var cxSbStateDataDt,cxSbStateDataUploadDg,cxSbStateDataUploadFm;
var g_viewModal=false;
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
            		field : 'name',
            		title:'终端状态', 
            		addform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_cxSbStateData_datagrid_add_dialog').dialog({
		onOpen:function(){
			initAddFileDt();
			cxSbStateDataAddOnOpen();
		}
	});
	
	$('#i_sy_cxSbStateData_datagrid_edit_dialog').dialog({
		onOpen:function(){
			initEditFileDt();
			cxSbStateDataEditOnOpen();
		}
	});
	pageView(cxSbStateDataId,columns);
	cxSbStateDataonload();
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
	$('#'+cxSbStateDataId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbStateDataId+'_add_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbStateDataId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbStateData'+"."+field]=eGet('#'+cxSbStateDataId+'_add_form_'+field);
		}
		data(getUrl('cxSbStateData','Add_HasFiles'),setData,'json',updateFun);
	});
	$('#'+cxSbStateDataId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('cxSbStateData','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,cxSbStateDataId);
			initEditFileDt();
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+cxSbStateDataId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbStateDataId+'_edit_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbStateDataId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbStateData'+"."+field]=eGet('#'+cxSbStateDataId+'_edit_form_'+field);
		}
		data(getUrl('cxSbStateData','Update'),setData,'json',updateFun);
	});
	$('#'+cxSbStateDataId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var cxSbStateDataDataGrid = {
			id:cxSbStateDataId,
			url:'${ctx}'+actionUrl('/sys/','cxSbStateData','List'),
			dId:'id',
			columns:columns
	};
	
	cxSbStateDataUploadDg = $('#i_sy_cxSbStateData_datagrid_upload_dialog');
	cxSbStateDataUploadFm =$('#i_sy_cxSbStateData_datagrid_upload_dialog_form');
	cxSbStateDataUploadFm.attr('action','${ctx}'+actionUrl('/sys/','cxSbStateData','Upload'));
	
	cxSbStateDataDt=gGrid2(cxSbStateDataDataGrid);	
	var straddfun="dorow(cxSbStateDataId,'','${ctx}"+actionUrl('/sys/','cxSbStateData','Add_HasFiles')+"',updateFun,'c')";
	gDataGridToolbarBtn(cxSbStateDataId,'icon-add',straddfun,"新增");
	var stredit="dorow(cxSbStateDataId,'','${ctx}"+actionUrl('/sys/','cxSbStateData','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(cxSbStateDataId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(cxSbStateDataId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','cxSbStateData','Delete_HasFiles')+"',updateFun,'d')";
	gDataGridToolbarBtn(cxSbStateDataId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(cxSbStateDataId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','cxSbStateData','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(cxSbStateDataId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(cxSbStateDataId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(cxSbStateDataId,'icon-page_find','doroodo_search()',"复合查询");
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
				cxSbStateDataDt.datagrid('load', obj.getSearchs('cxSbStateData'));
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
	cxSbStateDataUploadDg.dialog('open');
}

var g_fileid='';
function initAddFileDt(){
	g_fileid='';
	$('#i_sy_cxSbStateData_datagrid_edit_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/empty.jsp');
	$('#i_sy_cxSbStateData_datagrid_add_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/SyFile_.jsp');
}

function initEditFileDt(){
	if(eGet('#'+cxSbStateDataId+'_edit_form_id')=='')return;
	g_fileid='cxSbStateData-'+eGet('#'+cxSbStateDataId+'_edit_form_id');
	$('#i_sy_cxSbStateData_datagrid_add_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/empty.jsp');
	$('#i_sy_cxSbStateData_datagrid_edit_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/SyFile_.jsp');
}

function submitUploadForm(){
	cxSbStateDataUploadFm.form('submit',{
		success:function(d){
			cxSbStateDataDt.datagrid('reload');
			cxSbStateDataUploadDg.dialog('close');
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
		data(getUrl('cxSbStateData', 'FormFile'),setData,
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
<div id="i_sy_cxSbStateData_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_cxSbStateData_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_cxSbStateData_datagrid_searchbox"
					pdt="i_sy_cxSbStateData_datagrid"></input>
					<div id="i_sy_cxSbStateData_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_cxSbStateData_datagrid"></table> 
 
 <div id="i_sy_cxSbStateData_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_cxSbStateData_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_cxSbStateData_datagrid_add_form"><table class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" style="width:15%;" align="right">终端状态</td><td style="width:85%;" align="left"><input id="i_sy_cxSbStateData_datagrid_add_form_name" class="easyui-validatebox" data-options="required:true" reftype="0" type="text"/></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:85%;display:none;" align="left"><input id="i_sy_cxSbStateData_datagrid_add_form_id" reftype="-1" type="text"/></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
<div class="titlep">附件</div>
<div id="i_sy_cxSbStateData_datagrid_add_form_file_form"></div>
</div>
</div>
<div id="i_sy_cxSbStateData_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbStateData_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_cxSbStateData_datagrid_edit_form"><table class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" style="width:15%;" align="right">终端状态</td><td style="width:85%;" align="left"><input id="i_sy_cxSbStateData_datagrid_edit_form_name" class="easyui-validatebox" data-options="required:true" reftype="0" type="text"/></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:85%;display:none;" align="left"><input id="i_sy_cxSbStateData_datagrid_edit_form_id" reftype="-1" type="text"/></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
<div class="titlep">附件</div>
<div id="i_sy_cxSbStateData_datagrid_edit_form_file_form"></div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_cxSbStateData_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_cxSbStateData_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_cxSbStateData_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_cxSbStateData_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
