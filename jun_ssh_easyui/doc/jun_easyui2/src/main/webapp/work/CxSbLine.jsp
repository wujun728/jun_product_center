<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp" %>
<%@ include file="/include/meta.jsp" %>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/CxSbLine.js"></script>
<script>
var cxSbLineId="i_sy_cxSbLine_datagrid";
var cxSbLineDt,cxSbLineUploadDg,cxSbLineUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		hidden:false, 
            		title:'编号', 
            		width : '150'
            	  }, 
            	 {
            		field : 'lineNumber',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', dataoptions:"required:false" , 
            		hidden:false, readonly:false}, 
            		title:'线路编号', 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'lineName',
            		title:'线路名称', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'institutions',
            		title:'所属机构', 
            		addform:{type:'eType.ComboTree', dataoptions:"valueField:'text', url:'/doroodo/sys/syOrgan_Get_Tree'"}, 
            		editform:{type:'eType.ComboTree', dataoptions:"valueField:'text', url:'/doroodo/sys/syOrgan_Get_Tree'"}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'voltageGrade',
            		title:'电压等级【kv】', 
            		addform:{type:'eType.Input', hidden:false, dataoptions:"required:false"}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_cxSbLine_datagrid_add_dialog').dialog({
		onOpen:function(){
			cxSbLineAddOnOpen();
		}
	});
	
	$('#i_sy_cxSbLine_datagrid_edit_dialog').dialog({
		onOpen:function(){
			cxSbLineEditOnOpen();
		}
	});
	pageView(cxSbLineId,columns);
	cxSbLineonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+cxSbLineId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbLineId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbLineId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbLine'+"."+field]=eGet('#'+cxSbLineId+'_add_form_'+field);
		}
		data(getUrl('cxSbLine','Add'),setData,'json',null);
	});
	$('#'+cxSbLineId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('cxSbLine','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,cxSbLineId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+cxSbLineId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbLineId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbLineId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbLine'+"."+field]=eGet('#'+cxSbLineId+'_edit_form_'+field);
		}
		data(getUrl('cxSbLine','Update'),setData,'json',null);
	});
	$('#'+cxSbLineId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var cxSbLineDataGrid = {
			id:cxSbLineId,
			url:'${ctx}'+actionUrl('/sys/','cxSbLine','List'),
			dId:'id',
			columns:columns
	};
	
	cxSbLineUploadDg = $('#i_sy_cxSbLine_datagrid_upload_dialog');
	cxSbLineUploadFm =$('#i_sy_cxSbLine_datagrid_upload_dialog_form');
	cxSbLineUploadFm.attr('action','${ctx}'+actionUrl('/sys/','cxSbLine','Upload'));
	
	cxSbLineDt=gGrid2(cxSbLineDataGrid);	
	var straddfun="dorow(cxSbLineId,'','${ctx}"+actionUrl('/sys/','cxSbLine','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(cxSbLineId,'icon-add',straddfun,"新增");
	var stredit="dorow(cxSbLineId,'','${ctx}"+actionUrl('/sys/','cxSbLine','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(cxSbLineId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(cxSbLineId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','cxSbLine','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(cxSbLineId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(cxSbLineId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','cxSbLine','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(cxSbLineId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(cxSbLineId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(cxSbLineId,'icon-page_find','doroodo_search()',"复合查询");
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
				cxSbLineDt.datagrid('load', obj.getSearchs('cxSbLine'));
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
	cxSbLineUploadDg.dialog('open');
}

function submitUploadForm(){
	cxSbLineUploadFm.form('submit',{
		success:function(d){
			cxSbLineDt.datagrid('reload');
			cxSbLineUploadDg.dialog('close');
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
		data(getUrl('cxSbLine', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_cxSbLine_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_cxSbLine_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_cxSbLine_datagrid_searchbox"
					pdt="i_sy_cxSbLine_datagrid"></input>
					<div id="i_sy_cxSbLine_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_cxSbLine_datagrid"></table> 
 
 <div id="i_sy_cxSbLine_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_cxSbLine_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_cxSbLine_datagrid_add_form"><table width="99%" border="1" class="formtable" ><tbody><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbLine_datagrid_add_form_id" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">线路编号</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_add_form_lineNumber" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">线路名称</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_add_form_lineName" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">所属机构</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_add_form_institutions" type="text" class="easyui-combotree" data-options="valueField:'text', url:'/doroodo/sys/syOrgan_Get_Tree'" reftype="3"></td></tr><tr><td class="label" align="right" style="width:15%;">电压等级【kv】</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_add_form_voltageGrade" type="text" data-options="required:false" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_cxSbLine_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbLine_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_cxSbLine_datagrid_edit_form"><table width="99%" border="1" class="formtable" ><tbody><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbLine_datagrid_edit_form_id" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">线路编号</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_edit_form_lineNumber" type="text" data-options="required:false" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">线路名称</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_edit_form_lineName" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">所属机构</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_edit_form_institutions" type="text" class="easyui-combotree" data-options="valueField:'text', url:'/doroodo/sys/syOrgan_Get_Tree'" reftype="3"></td></tr><tr><td class="label" align="right" style="width:15%;">电压等级【kv】</td><td align="left" style="width:85%;"><input id="i_sy_cxSbLine_datagrid_edit_form_voltageGrade" type="text" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_cxSbLine_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_cxSbLine_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_cxSbLine_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_cxSbLine_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
