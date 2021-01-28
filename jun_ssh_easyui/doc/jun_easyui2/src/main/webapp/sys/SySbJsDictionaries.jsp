<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp" %>
<%@ include file="/include/meta.jsp" %>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SySbJsDictionaries.js"></script>
<script>
var sySbJsDictionariesId="i_sy_sySbJsDictionaries_datagrid";
var sySbJsDictionariesDt,sySbJsDictionariesUploadDg,sySbJsDictionariesUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'jsEvent',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'eventName',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'jsEventContent',
            		 
            		width : '150'
            	  }, 
            	 {
            		field : 'updateTime',
            		addform:{type:'eType.DateTimeBox'}, 
            		editform:{type:'eType.DateTimeBox'}, 
            		title:'修改时间', 
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_sySbJsDictionaries_datagrid_add_dialog').dialog({
		onOpen:function(){
			sySbJsDictionariesAddOnOpen();
		}
	});
	
	$('#i_sy_sySbJsDictionaries_datagrid_edit_dialog').dialog({
		onOpen:function(){
			sySbJsDictionariesEditOnOpen();
		}
	});
	pageView(sySbJsDictionariesId,columns);
	sySbJsDictionariesonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+sySbJsDictionariesId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+sySbJsDictionariesId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+sySbJsDictionariesId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['sySbJsDictionaries'+"."+field]=eGet('#'+sySbJsDictionariesId+'_add_form_'+field);
		}
		data(getUrl('sySbJsDictionaries','Add'),setData,'json',null);
	});
	$('#'+sySbJsDictionariesId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('sySbJsDictionaries','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,sySbJsDictionariesId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+sySbJsDictionariesId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+sySbJsDictionariesId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+sySbJsDictionariesId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['sySbJsDictionaries'+"."+field]=eGet('#'+sySbJsDictionariesId+'_edit_form_'+field);
		}
		data(getUrl('sySbJsDictionaries','Update'),setData,'json',null);
	});
	$('#'+sySbJsDictionariesId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var sySbJsDictionariesDataGrid = {
			id:sySbJsDictionariesId,
			url:'${ctx}'+actionUrl('/sys/','sySbJsDictionaries','List'),
			dId:'id',
			columns:columns
	};
	
	sySbJsDictionariesUploadDg = $('#i_sy_sySbJsDictionaries_datagrid_upload_dialog');
	sySbJsDictionariesUploadFm =$('#i_sy_sySbJsDictionaries_datagrid_upload_dialog_form');
	sySbJsDictionariesUploadFm.attr('action','${ctx}'+actionUrl('/sys/','sySbJsDictionaries','Upload'));
	
	sySbJsDictionariesDt=gGrid2(sySbJsDictionariesDataGrid);	
	var straddfun="dorow(sySbJsDictionariesId,'','${ctx}"+actionUrl('/sys/','sySbJsDictionaries','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(sySbJsDictionariesId,'icon-add',straddfun,"新增");
	var stredit="dorow(sySbJsDictionariesId,'','${ctx}"+actionUrl('/sys/','sySbJsDictionaries','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(sySbJsDictionariesId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(sySbJsDictionariesId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','sySbJsDictionaries','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(sySbJsDictionariesId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(sySbJsDictionariesId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','sySbJsDictionaries','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(sySbJsDictionariesId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(sySbJsDictionariesId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(sySbJsDictionariesId,'icon-page_find','doroodo_search()',"复合查询");
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
				sySbJsDictionariesDt.datagrid('load', obj.getSearchs('sySbJsDictionaries'));
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
	sySbJsDictionariesUploadDg.dialog('open');
}

function submitUploadForm(){
	sySbJsDictionariesUploadFm.form('submit',{
		success:function(d){
			sySbJsDictionariesDt.datagrid('reload');
			sySbJsDictionariesUploadDg.dialog('close');
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
		data(getUrl('sySbJsDictionaries', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_sySbJsDictionaries_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_sySbJsDictionaries_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_sySbJsDictionaries_datagrid_searchbox"
					pdt="i_sy_sySbJsDictionaries_datagrid"></input>
					<div id="i_sy_sySbJsDictionaries_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_sySbJsDictionaries_datagrid"></table> 
 
 <div id="i_sy_sySbJsDictionaries_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_sySbJsDictionaries_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_sySbJsDictionaries_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_sySbJsDictionaries_datagrid_edit_btn',toolbar:[{
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
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_sySbJsDictionaries_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_sySbJsDictionaries_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_sySbJsDictionaries_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_sySbJsDictionaries_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
