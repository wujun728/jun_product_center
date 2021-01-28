<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/CxSbFault.js"></script>
<script>
var cxSbFaultId="i_sy_cxSbFault_datagrid";
var cxSbFaultDt,cxSbFaultUploadDg,cxSbFaultUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		title:'序号', 
            		width : '150'
            	  }, 
            	 {
            		field : 'lineNumber',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'线路编号', 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'lineName',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'线路名称', 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'head',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'负责人', 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'phone',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'电话号码', 
            		hidden:false, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_cxSbFault_datagrid_add_dialog').dialog({
		onOpen:function(){
			cxSbFaultAddOnOpen();
		}
	});
	
	$('#i_sy_cxSbFault_datagrid_edit_dialog').dialog({
		onOpen:function(){
			cxSbFaultEditOnOpen();
		}
	});
	pageView(cxSbFaultId,columns);
	cxSbFaultonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+cxSbFaultId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbFaultId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbFaultId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbFault'+"."+field]=eGet('#'+cxSbFaultId+'_add_form_'+field);
		}
		data(getUrl('cxSbFault','Add'),setData,'json',null);
	});
	$('#'+cxSbFaultId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('cxSbFault','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,cxSbFaultId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+cxSbFaultId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbFaultId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbFaultId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbFault'+"."+field]=eGet('#'+cxSbFaultId+'_edit_form_'+field);
		}
		data(getUrl('cxSbFault','Update'),setData,'json',null);
	});
	$('#'+cxSbFaultId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var cxSbFaultDataGrid = {
			id:cxSbFaultId,
			url:'${ctx}'+actionUrl('/sys/','cxSbFault','List'),
			dId:'id',
			columns:columns
	};
	
	cxSbFaultUploadDg = $('#i_sy_cxSbFault_datagrid_upload_dialog');
	cxSbFaultUploadFm =$('#i_sy_cxSbFault_datagrid_upload_dialog_form');
	cxSbFaultUploadFm.attr('action','${ctx}'+actionUrl('/sys/','cxSbFault','Upload'));
	
	cxSbFaultDt=gGrid2(cxSbFaultDataGrid);	
	var straddfun="dorow(cxSbFaultId,'','${ctx}"+actionUrl('/sys/','cxSbFault','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(cxSbFaultId,'icon-add',straddfun,"新增");
	var stredit="dorow(cxSbFaultId,'','${ctx}"+actionUrl('/sys/','cxSbFault','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(cxSbFaultId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(cxSbFaultId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','cxSbFault','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(cxSbFaultId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(cxSbFaultId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','cxSbFault','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(cxSbFaultId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(cxSbFaultId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(cxSbFaultId,'icon-page_find','doroodo_search()',"复合查询");
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
				cxSbFaultDt.datagrid('load', obj.getSearchs('cxSbFault'));
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
	cxSbFaultUploadDg.dialog('open');
}

function submitUploadForm(){
	cxSbFaultUploadFm.form('submit',{
		success:function(d){
			cxSbFaultDt.datagrid('reload');
			cxSbFaultUploadDg.dialog('close');
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
		data(getUrl('cxSbFault', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_cxSbFault_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_cxSbFault_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_cxSbFault_datagrid_searchbox"
					pdt="i_sy_cxSbFault_datagrid"></input>
					<div id="i_sy_cxSbFault_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_cxSbFault_datagrid"></table> 
 
 <div id="i_sy_cxSbFault_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_cxSbFault_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_cxSbFault_datagrid_add_form"><table width="99%" border="1" class="formtable" ><tbody><tr style="display: none;"><td style="width: 15%; display: none;" class="label" align="right">序号</td><td style="width: 85%; display: none;" align="left"><input id="i_sy_cxSbFault_datagrid_add_form_id" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">线路编号</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_add_form_lineNumber" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">线路名称</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_add_form_lineName" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">负责人</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_add_form_head" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">电话号码</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_add_form_phone" value="" type="text" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_cxSbFault_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbFault_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_cxSbFault_datagrid_edit_form"><table width="99%" border="1" class="formtable" ><tbody><tr style="display: none;"><td style="width: 15%; display: none;" class="label" align="right">序号</td><td style="width: 85%; display: none;" align="left"><input id="i_sy_cxSbFault_datagrid_edit_form_id" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">线路编号</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_edit_form_lineNumber" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">线路名称</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_edit_form_lineName" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">负责人</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_edit_form_head" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">电话号码</td><td style="width: 85%;" align="left"><input id="i_sy_cxSbFault_datagrid_edit_form_phone" value="" type="text" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_cxSbFault_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_cxSbFault_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_cxSbFault_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_cxSbFault_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
