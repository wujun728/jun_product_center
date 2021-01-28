<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyProject.js"></script>
<script>
var syProjectId="i_sy_syProject_datagrid";
var syProjectDt,syProjectUploadDg,syProjectUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		title:'编号', 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'projectName',
            		title:'项目名称', 
            		addform:{type:'eType.ValidateBox', hidden:false, dataoptions:"required:true"}, 
            		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'projectInfo',
            		title:'项目简介', 
            		addform:{type:'eType.HtmlEdit', style:'width:600px;height:200px'}, 
            		editform:{type:'eType.HtmlEdit', style:'width:600px;height:200px'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'projectModule',
            		title:'模块', 
            		addform:{type:'eType.TextArea', style:'width:200px;height:50px', attribute:'row="4" cols="10"'}, 
            		editform:{type:'eType.TextArea', style:'width:200px;height:50px', attribute:'row="4" cols="10"'}, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_syProject_datagrid_add_dialog').dialog({
		onOpen:function(){
			syProjectAddOnOpen();
		}
	});
	
	$('#i_sy_syProject_datagrid_edit_dialog').dialog({
		onOpen:function(){
			syProjectEditOnOpen();
		}
	});
	pageView(syProjectId,columns);
	syProjectonload();
});


function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+syProjectId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syProjectId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syProjectId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syProject'+"."+field]=eGet('#'+syProjectId+'_add_form_'+field);
		}
		data(getUrl('syProject','Add'),setData,'json',null);
	});
	$('#'+syProjectId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syProject','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syProjectId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syProjectId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syProjectId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syProjectId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syProject'+"."+field]=eGet('#'+syProjectId+'_edit_form_'+field);
		}
		data(getUrl('syProject','Update'),setData,'json',null);
	});
	$('#'+syProjectId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var syProjectDataGrid = {
			id:syProjectId,
			url:'${ctx}'+actionUrl('/sys/','syProject','List'),
			dId:'id',
			columns:columns
	};
	
	syProjectUploadDg = $('#i_sy_syProject_datagrid_upload_dialog');
	syProjectUploadFm =$('#i_sy_syProject_datagrid_upload_dialog_form');
	syProjectUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syProject','Upload'));
	
	syProjectDt=gGrid2(syProjectDataGrid);	
	var straddfun="dorow(syProjectId,'','${ctx}"+actionUrl('/sys/','syProject','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syProjectId,'icon-add',straddfun,"新增");
	var stredit="dorow(syProjectId,'','${ctx}"+actionUrl('/sys/','syProject','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syProjectId,'icon-edit',stredit,"修改");
	var strsetdefultfun="dorow(syProjectId,'您是否确定要设置当前选中配置为默认','${ctx}"+actionUrl('/sys/','syProject','Set_Defult')+"',updateFun,'s')";
	gDataGridToolbarBtn(syProjectId,'icon-tip',strsetdefultfun,"设置为默认");
	var strdelfun="dorow(syProjectId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syProject','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syProjectId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syProjectId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syProject','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syProjectId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syProjectId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	syProjectUploadDg.dialog('open');
}

function submitUploadForm(){
	syProjectUploadFm.form('submit',{
		success:function(d){
			syProjectDt.datagrid('reload');
			syProjectUploadDg.dialog('close');
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
		data(getUrl('syProject', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout">
<div id="i_sy_syProject_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syProject_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syProject_datagrid_searchbox"
					pdt="i_sy_syProject_datagrid"></input>
					<div id="i_sy_syProject_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syProject_datagrid"></table> 
 
 <div id="i_sy_syProject_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syProject_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syProject_datagrid_add_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">项目名称</td><td align="left" style="width:85%;"><input id="i_sy_syProject_datagrid_add_form_projectName" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td></tr><tr><td class="label" align="right" style="width:15%;">项目简介</td><td align="left" style="width:85%;"><div id="i_sy_syProject_datagrid_add_form_projectInfo" style="width:600px;height:200px" reftype="13"></div></td></tr><tr><td class="label" align="right" style="width:15%;">模块</td><td align="left" style="width:85%;"><textarea id="i_sy_syProject_datagrid_add_form_projectModule" class="easyui-validatebox" style="width:200px;height:50px" row="4" cols="10" reftype="16"></textarea>
<a id="i_sy_syProject_datagrid_add_form_projectModule_c_clear" href="#" class="easyui-linkbutton" >清空</a>
<input id="i_sy_syProject_datagrid_add_form_projectModule_c" type="text" class="easyui-combobox" data-options="url:'/doroodo/sys/syModule_Get_ComboBox', valueField:'text'" reftype="2"/>
</td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:85%;display:none;"><input id="i_sy_syProject_datagrid_add_form_id" type="text" reftype="-1"/></td></tr></tbody></table></form><script>function i_sy_syProject_datagrid_add_form_projectInfouei(){var str="";var style=$("#i_sy_syProject_datagrid_add_form_projectInfo").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syProject_datagrid_add_form_projectInfouedit = new UE.ui.Editor(i_sy_syProject_datagrid_add_form_projectInfouei()); i_sy_syProject_datagrid_add_form_projectInfouedit.render("i_sy_syProject_datagrid_add_form_projectInfo");top.formfieldmap.put("i_sy_syProject_datagrid_add_form_projectInfo",i_sy_syProject_datagrid_add_form_projectInfouedit);</script>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syProject_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syProject_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_syProject_datagrid_edit_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">项目名称</td><td align="left" style="width:85%;"><input id="i_sy_syProject_datagrid_edit_form_projectName" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td></tr><tr><td class="label" align="right" style="width:15%;">项目简介</td><td align="left" style="width:85%;"><div id="i_sy_syProject_datagrid_edit_form_projectInfo" style="width:600px;height:200px" reftype="13"></div></td></tr><tr><td class="label" align="right" style="width:15%;">模块</td><td align="left" style="width:85%;"><textarea id="i_sy_syProject_datagrid_edit_form_projectModule" class="easyui-validatebox" style="width:200px;height:50px" row="4" cols="10" reftype="16"></textarea>
<a id="i_sy_syProject_datagrid_edit_form_projectModule_c_clear" href="#" class="easyui-linkbutton" >清空</a>
<input id="i_sy_syProject_datagrid_edit_form_projectModule_c" type="text" class="easyui-combobox" data-options="url:'/doroodo/sys/syModule_Get_ComboBox', valueField:'text'" reftype="2"/>
</td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:85%;display:none;"><input id="i_sy_syProject_datagrid_edit_form_id" type="text" reftype="-1"/></td></tr></tbody></table></form><script>function i_sy_syProject_datagrid_edit_form_projectInfouei(){var str="";var style=$("#i_sy_syProject_datagrid_edit_form_projectInfo").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syProject_datagrid_edit_form_projectInfouedit = new UE.ui.Editor(i_sy_syProject_datagrid_edit_form_projectInfouei()); i_sy_syProject_datagrid_edit_form_projectInfouedit.render("i_sy_syProject_datagrid_edit_form_projectInfo");top.formfieldmap.put("i_sy_syProject_datagrid_edit_form_projectInfo",i_sy_syProject_datagrid_edit_form_projectInfouedit);</script>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syProject_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syProject_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syProject_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syProject_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
