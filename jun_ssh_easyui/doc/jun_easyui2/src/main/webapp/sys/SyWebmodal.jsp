<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyWebmodal.js"></script>
<script>
var syWebmodalId="i_sy_syWebmodal_datagrid";
var syWebmodalDt,syWebmodalUploadDg,syWebmodalUploadFm;
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
            		field : 'title',
            		title:'模块名称', 
            		addform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'codeName',
            		title:'系统名称', 
            		addform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		tooltip:'必须为英文，同模版名称一致', 
            		width : '150'
            	  }, 
            	 {
            		field : 'imgPath',
            		title:'示例模块', 
            		addform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		width : '150',
            		formatter: function(value,row,index){
         				return '<a href="javascript:void(0)" onClick="打开模块(\''+value+'\')">'+value+'</a>';
         			}
            	  }, 
            	 {
            		field : 'info',
            		title:'说明', 
            		addform:{type:'eType.HtmlEdit', style:'width:800px;height:200px'}, 
            		editform:{type:'eType.HtmlEdit', style:'width:800px;height:200px'}, 
            		hidden:true, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_syWebmodal_datagrid_add_dialog').dialog({
		onOpen:function(){
			syWebmodalAddOnOpen();
		}
	});
	
	$('#i_sy_syWebmodal_datagrid_edit_dialog').dialog({
		onOpen:function(){
			syWebmodalEditOnOpen();
		}
	});
	pageView(syWebmodalId,columns);
	syWebmodalonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+syWebmodalId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syWebmodalId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syWebmodalId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syWebmodal'+"."+field]=eGet('#'+syWebmodalId+'_add_form_'+field);
		}
		data(getUrl('syWebmodal','Add'),setData,'json',null);
	});
	$('#'+syWebmodalId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syWebmodal','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syWebmodalId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syWebmodalId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syWebmodalId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syWebmodalId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syWebmodal'+"."+field]=eGet('#'+syWebmodalId+'_edit_form_'+field);
		}
		data(getUrl('syWebmodal','Update'),setData,'json',null);
	});
	$('#'+syWebmodalId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var syWebmodalDataGrid = {
			id:syWebmodalId,
			url:'${ctx}'+actionUrl('/sys/','syWebmodal','List'),
			dId:'id',
			columns:columns
	};
	
	syWebmodalUploadDg = $('#i_sy_syWebmodal_datagrid_upload_dialog');
	syWebmodalUploadFm =$('#i_sy_syWebmodal_datagrid_upload_dialog_form');
	syWebmodalUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syWebmodal','Upload'));
	
	syWebmodalDt=gGrid2(syWebmodalDataGrid);	
	var straddfun="dorow(syWebmodalId,'','${ctx}"+actionUrl('/sys/','syWebmodal','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syWebmodalId,'icon-add',straddfun,"新增");
	var stredit="dorow(syWebmodalId,'','${ctx}"+actionUrl('/sys/','syWebmodal','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syWebmodalId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(syWebmodalId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syWebmodal','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syWebmodalId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syWebmodalId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syWebmodal','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syWebmodalId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syWebmodalId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	syWebmodalUploadDg.dialog('open');
}

function submitUploadForm(){
	syWebmodalUploadFm.form('submit',{
		success:function(d){
			syWebmodalDt.datagrid('reload');
			syWebmodalUploadDg.dialog('close');
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
		data(getUrl('syWebmodal', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syWebmodal_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syWebmodal_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syWebmodal_datagrid_searchbox"
					pdt="i_sy_syWebmodal_datagrid"></input>
					<div id="i_sy_syWebmodal_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syWebmodal_datagrid"></table> 
 
 <div id="i_sy_syWebmodal_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syWebmodal_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syWebmodal_datagrid_add_form"><table width="99%" border="1" class="formtable" data-sort="sortDisabled"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">模块名称</td><td align="left" style="width:35%;"><input id="i_sy_syWebmodal_datagrid_add_form_title" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td><td class="label" align="right" style="width:15%;">系统名称</td><td align="left" style="width:35%;"><input id="i_sy_syWebmodal_datagrid_add_form_codeName" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/><a href="#" title="必须为英文，同模版名称一致" class="easyui-tooltip">填写说明</a></td></tr><tr><td class="label" align="right" style="width:15%;">示例模块</td><td align="left" style="width:35%;" rowspan="1" colspan="3"><input id="i_sy_syWebmodal_datagrid_add_form_imgPath" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td></tr><tr><td class="label" align="right" style="width:15%;">说明</td><td align="left" style="width:35%;" rowspan="1" colspan="3"><div id="i_sy_syWebmodal_datagrid_add_form_info" style="width:800px;height:200px" reftype="13"></div></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syWebmodal_datagrid_add_form_id" type="text" reftype="-1"/></td><td><br/></td><td><br/></td></tr></tbody></table></form><script>function i_sy_syWebmodal_datagrid_add_form_infouei(){var str="";var style=$("#i_sy_syWebmodal_datagrid_add_form_info").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syWebmodal_datagrid_add_form_infouedit = new UE.ui.Editor(i_sy_syWebmodal_datagrid_add_form_infouei()); i_sy_syWebmodal_datagrid_add_form_infouedit.render("i_sy_syWebmodal_datagrid_add_form_info");top.formfieldmap.put("i_sy_syWebmodal_datagrid_add_form_info",i_sy_syWebmodal_datagrid_add_form_infouedit);</script>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syWebmodal_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syWebmodal_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_syWebmodal_datagrid_edit_form"><table width="99%" border="1" class="formtable" data-sort="sortDisabled"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">模块名称</td><td align="left" style="width:35%;"><input id="i_sy_syWebmodal_datagrid_edit_form_title" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td><td class="label" align="right" style="width:15%;">系统名称</td><td align="left" style="width:35%;"><input id="i_sy_syWebmodal_datagrid_edit_form_codeName" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/><a href="#" title="必须为英文，同模版名称一致" class="easyui-tooltip">填写说明</a></td></tr><tr><td class="label" align="right" style="width:15%;">示例模块</td><td align="left" style="width:35%;" rowspan="1" colspan="3"><input id="i_sy_syWebmodal_datagrid_edit_form_imgPath" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td></tr><tr><td class="label" align="right" style="width:15%;">说明</td><td align="left" style="width:35%;" rowspan="1" colspan="3"><div id="i_sy_syWebmodal_datagrid_edit_form_info" style="width:800px;height:200px" reftype="13"></div></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syWebmodal_datagrid_edit_form_id" type="text" reftype="-1"/></td><td><br/></td><td><br/></td></tr></tbody></table></form><script>function i_sy_syWebmodal_datagrid_edit_form_infouei(){var str="";var style=$("#i_sy_syWebmodal_datagrid_edit_form_info").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syWebmodal_datagrid_edit_form_infouedit = new UE.ui.Editor(i_sy_syWebmodal_datagrid_edit_form_infouei()); i_sy_syWebmodal_datagrid_edit_form_infouedit.render("i_sy_syWebmodal_datagrid_edit_form_info");top.formfieldmap.put("i_sy_syWebmodal_datagrid_edit_form_info",i_sy_syWebmodal_datagrid_edit_form_infouedit);</script>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syWebmodal_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syWebmodal_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syWebmodal_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syWebmodal_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
