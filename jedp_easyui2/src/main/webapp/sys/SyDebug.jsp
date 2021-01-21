<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyDebug.js"></script>
<script>
var syDebugId="i_sy_syDebug_datagrid";
var syDebugDt,syDebugUploadDg,syDebugUploadFm;
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
            		field : 'bugContent',
            		title:'bug内容', 
            		addform:{type:'eType.HtmlEdit'}, 
            		editform:{type:'eType.HtmlEdit'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'findUser',
            		title:'发现人', 
            		addform:{type:'eType.Input', readonly:true}, 
            		editform:{type:'eType.Input', readonly:true}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'editResult',
            		title:'修改结果', 
            		addform:{type:'eType.HtmlEdit'}, 
            		editform:{type:'eType.HtmlEdit'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'updateUser',
            		title:'修改人', 
            		addform:{type:'eType.Input', readonly:true}, 
            		editform:{type:'eType.Input', readonly:true}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'updateTime',
            		title:'修改时间', 
            		addform:{type:'eType.DateTimeBox'}, 
            		editform:{type:'eType.DateTimeBox'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'state',
            		title:'状态', 
            		addform:{type:'eType.ComboBox', dataoptions:"valueField:'text', url:'/doroodo/sys/syDebugState_ComboBox'"}, 
            		editform:{type:'eType.ComboBox', dataoptions:"valueField:'text', url:'/doroodo/sys/syDebugState_ComboBox'"}, 
            		width : '150',
            		formatter: function(value,row,index){
             			if(value=='新发现'){
             				return "<div style='font-weight:700;color:red;'>新发现</div>";
             			}else if(value=='已接受'){
             				return "<div style='font-weight:700;color:yellow;'>已接受</div>";
             			}else if(value=="正在处理"){
             				return "<div style='font-weight:700;color:purple;'>正在处理</div>";
             			}else{
             				return "<div style='font-weight:700;color:green;'>已完成</div>";
             			}
             		}
            	  } 
            	] ];
$(function(){
	$('#i_sy_syDebug_datagrid_add_dialog').dialog({
		onOpen:function(){
			syDebugAddOnOpen();
		}
	});
	
	$('#i_sy_syDebug_datagrid_edit_dialog').dialog({
		onOpen:function(){
			syDebugEditOnOpen();
		}
	});
	pageView(syDebugId,columns);
	syDebugonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+syDebugId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDebugId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDebugId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDebug'+"."+field]=eGet('#'+syDebugId+'_add_form_'+field);
		}
		data(getUrl('syDebug','Add'),setData,'json',null);
	});
	$('#'+syDebugId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syDebug','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syDebugId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syDebugId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDebugId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDebugId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDebug'+"."+field]=eGet('#'+syDebugId+'_edit_form_'+field);
		}
		data(getUrl('syDebug','Update'),setData,'json',null);
	});
	$('#'+syDebugId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var syDebugDataGrid = {
			id:syDebugId,
			url:'${ctx}'+actionUrl('/sys/','syDebug','List'),
			dId:'id',
			columns:columns
	};
	
	syDebugUploadDg = $('#i_sy_syDebug_datagrid_upload_dialog');
	syDebugUploadFm =$('#i_sy_syDebug_datagrid_upload_dialog_form');
	syDebugUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syDebug','Upload'));
	
	syDebugDt=gGrid2(syDebugDataGrid);	
	var straddfun="dorow(syDebugId,'','${ctx}"+actionUrl('/sys/','syDebug','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syDebugId,'icon-add',straddfun,"新增");
	var stredit="dorow(syDebugId,'','${ctx}"+actionUrl('/sys/','syDebug','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syDebugId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(syDebugId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syDebug','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syDebugId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syDebugId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syDebug','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syDebugId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syDebugId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	syDebugUploadDg.dialog('open');
}

function submitUploadForm(){
	syDebugUploadFm.form('submit',{
		success:function(d){
			syDebugDt.datagrid('reload');
			syDebugUploadDg.dialog('close');
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
		data(getUrl('syDebug', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syDebug_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syDebug_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syDebug_datagrid_searchbox"
					pdt="i_sy_syDebug_datagrid"></input>
					<div id="i_sy_syDebug_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syDebug_datagrid"></table> 
 
 <div id="i_sy_syDebug_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syDebug_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syDebug_datagrid_add_form"><table data-sort="sortDisabled" class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" style="width:15%;" align="right">发现人</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_add_form_findUser" readonly="readonly" reftype="-1" type="text"/></td><td class="label" style="width:15%;" align="right">状态</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_add_form_state" class="easyui-combobox" data-options="valueField:'text', url:'/doroodo/sys/syDebugState_ComboBox'" reftype="2" type="text"/></td></tr><tr><td class="label" style="width:15%;" align="right">bug内容</td><td colspan="3" rowspan="1" style="width:35%;" align="left"><div id="i_sy_syDebug_datagrid_add_form_bugContent" reftype="13"></div></td></tr><tr><td class="label" style="width:15%;" align="right">修改人</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_add_form_updateUser" readonly="readonly" reftype="-1" type="text"/></td><td class="label" style="width:15%;" align="right">修改时间</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_add_form_updateTime" class="easyui-datetimebox" reftype="7" type="text"/></td></tr><tr><td class="label" style="width:15%;" align="right">修改结果</td><td colspan="3" rowspan="1" style="width:35%;" align="left"><div id="i_sy_syDebug_datagrid_add_form_editResult" reftype="13"></div></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDebug_datagrid_add_form_id" reftype="-1" type="text"/></td><td><br/></td><td><br/></td></tr></tbody></table></form><script>function i_sy_syDebug_datagrid_add_form_bugContentuei(){var str="";var style=$("#i_sy_syDebug_datagrid_add_form_bugContent").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syDebug_datagrid_add_form_bugContentuedit = new UE.ui.Editor(i_sy_syDebug_datagrid_add_form_bugContentuei()); i_sy_syDebug_datagrid_add_form_bugContentuedit.render("i_sy_syDebug_datagrid_add_form_bugContent");top.formfieldmap.put("i_sy_syDebug_datagrid_add_form_bugContent",i_sy_syDebug_datagrid_add_form_bugContentuedit);</script><script>function i_sy_syDebug_datagrid_add_form_editResultuei(){var str="";var style=$("#i_sy_syDebug_datagrid_add_form_editResult").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syDebug_datagrid_add_form_editResultuedit = new UE.ui.Editor(i_sy_syDebug_datagrid_add_form_editResultuei()); i_sy_syDebug_datagrid_add_form_editResultuedit.render("i_sy_syDebug_datagrid_add_form_editResult");top.formfieldmap.put("i_sy_syDebug_datagrid_add_form_editResult",i_sy_syDebug_datagrid_add_form_editResultuedit);</script>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syDebug_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syDebug_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_syDebug_datagrid_edit_form"><table data-sort="sortDisabled" class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" style="width:15%;" align="right">发现人</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_edit_form_findUser" readonly="readonly" reftype="-1" type="text"/></td><td class="label" style="width:15%;" align="right">状态</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_edit_form_state" class="easyui-combobox" data-options="valueField:'text', url:'/doroodo/sys/syDebugState_ComboBox'" reftype="2" type="text"/></td></tr><tr><td class="label" style="width:15%;" align="right">bug内容</td><td colspan="3" rowspan="1" style="width:35%;" align="left"><div id="i_sy_syDebug_datagrid_edit_form_bugContent" reftype="13"></div></td></tr><tr><td class="label" style="width:15%;" align="right">修改人</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_edit_form_updateUser" readonly="readonly" reftype="-1" type="text"/></td><td class="label" style="width:15%;" align="right">修改时间</td><td style="width:35%;" align="left"><input id="i_sy_syDebug_datagrid_edit_form_updateTime" class="easyui-datetimebox" reftype="7" type="text"/></td></tr><tr><td class="label" style="width:15%;" align="right">修改结果</td><td colspan="3" rowspan="1" style="width:35%;" align="left"><div id="i_sy_syDebug_datagrid_edit_form_editResult" reftype="13"></div></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:35%;display:none;" align="left"><input id="i_sy_syDebug_datagrid_edit_form_id" reftype="-1" type="text"/></td><td><br/></td><td><br/></td></tr></tbody></table></form><script>function i_sy_syDebug_datagrid_edit_form_bugContentuei(){var str="";var style=$("#i_sy_syDebug_datagrid_edit_form_bugContent").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syDebug_datagrid_edit_form_bugContentuedit = new UE.ui.Editor(i_sy_syDebug_datagrid_edit_form_bugContentuei()); i_sy_syDebug_datagrid_edit_form_bugContentuedit.render("i_sy_syDebug_datagrid_edit_form_bugContent");top.formfieldmap.put("i_sy_syDebug_datagrid_edit_form_bugContent",i_sy_syDebug_datagrid_edit_form_bugContentuedit);</script><script>function i_sy_syDebug_datagrid_edit_form_editResultuei(){var str="";var style=$("#i_sy_syDebug_datagrid_edit_form_editResult").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syDebug_datagrid_edit_form_editResultuedit = new UE.ui.Editor(i_sy_syDebug_datagrid_edit_form_editResultuei()); i_sy_syDebug_datagrid_edit_form_editResultuedit.render("i_sy_syDebug_datagrid_edit_form_editResult");top.formfieldmap.put("i_sy_syDebug_datagrid_edit_form_editResult",i_sy_syDebug_datagrid_edit_form_editResultuedit);</script>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syDebug_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syDebug_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syDebug_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syDebug_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
