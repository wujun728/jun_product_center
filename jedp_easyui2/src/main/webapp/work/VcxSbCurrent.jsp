<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp" %>
<%@ include file="/include/meta.jsp" %>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/VcxSbCurrent.js"></script>
<script>
var vcxSbCurrentId="i_sy_vcxSbCurrent_datagrid";
var vcxSbCurrentDt,vcxSbCurrentUploadDg,vcxSbCurrentUploadFm;
var columns= [ [
           	 {
           		field : 'id',
           		hidden:true,
              		search : false,
              		title:'',
           		width : '150'
           	  }, 
           	 {
           		field : 'acrrent',
           		title:'A相电流(A)',
           		width : '150'
           	  }, 
           	 {
           		field : 'bcrrent',
           		title:'B相电流(A)',
           		width : '150'
           	  }, 
           	 {
           		field : 'ccrrent',
           		title:'C相电流(A)',
           		width : '150'
           	  }, 
           	 {
           		field : 'terminalId',
           		title:'终端编号',
           		width : '150'
           	  }, 
           	 {
           		field : 'saveDate',
           		title:'采集时间',
           		width : '150'
           	  }, 
           	 {
           		field : 'line',
           		title:'线路',
           		width : '150'
           	  }, 
           	 {
           		field : 'factory',
           		title:'设备厂家',
           		width : '150'
           	  }, 
           	 {
           		field : 'protocol',
           		hidden:true,
              		search : false,
              		title:'',
           		width : '150'
           	  }, 
           	 {
           		field : 'position',
              		title:'安装位置',
           		width : '150'
           	  }, 
           	 {
           		field : 'protocolName',
           		hidden:true,
              		search : false,
              		title:'',
           		width : '150'
           	  }, 
           	 {
           		field : 'simCard',
           		hidden:true,
              		search : false,
              		title:'',
           		width : '150'
           	  } 
           	] ];
$(function(){
	$('#i_sy_vcxSbCurrent_datagrid_add_dialog').dialog({
		onOpen:function(){
			vcxSbCurrentAddOnOpen();
		}
	});
	
	$('#i_sy_vcxSbCurrent_datagrid_edit_dialog').dialog({
		onOpen:function(){
			vcxSbCurrentEditOnOpen();
		}
	});
	pageView(vcxSbCurrentId,columns);
	vcxSbCurrentonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+vcxSbCurrentId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+vcxSbCurrentId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+vcxSbCurrentId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['vcxSbCurrent'+"."+field]=eGet('#'+vcxSbCurrentId+'_add_form_'+field);
		}
		data(getUrl('vcxSbCurrent','Add'),setData,'json',null);
	});
	$('#'+vcxSbCurrentId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('vcxSbCurrent','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,vcxSbCurrentId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+vcxSbCurrentId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+vcxSbCurrentId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+vcxSbCurrentId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['vcxSbCurrent'+"."+field]=eGet('#'+vcxSbCurrentId+'_edit_form_'+field);
		}
		data(getUrl('vcxSbCurrent','Update'),setData,'json',null);
	});
	$('#'+vcxSbCurrentId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var vcxSbCurrentDataGrid = {
			id:vcxSbCurrentId,
			url:'${ctx}'+actionUrl('/sys/','vcxSbCurrent','List'),
			dId:'id',
			columns:columns
	};
	
	vcxSbCurrentUploadDg = $('#i_sy_vcxSbCurrent_datagrid_upload_dialog');
	vcxSbCurrentUploadFm =$('#i_sy_vcxSbCurrent_datagrid_upload_dialog_form');
	vcxSbCurrentUploadFm.attr('action','${ctx}'+actionUrl('/sys/','vcxSbCurrent','Upload'));
	
	vcxSbCurrentDt=gGrid2(vcxSbCurrentDataGrid);	
	var straddfun="dorow(vcxSbCurrentId,'','${ctx}"+actionUrl('/sys/','vcxSbCurrent','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(vcxSbCurrentId,'icon-add',straddfun,"新增");
	var stredit="dorow(vcxSbCurrentId,'','${ctx}"+actionUrl('/sys/','vcxSbCurrent','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(vcxSbCurrentId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(vcxSbCurrentId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','vcxSbCurrent','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(vcxSbCurrentId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(vcxSbCurrentId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','vcxSbCurrent','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(vcxSbCurrentId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(vcxSbCurrentId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(vcxSbCurrentId,'icon-page_find','doroodo_search()',"复合查询");
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
				vcxSbCurrentDt.datagrid('load', obj.getSearchs('vcxSbCurrent'));
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
	vcxSbCurrentUploadDg.dialog('open');
}

function submitUploadForm(){
	vcxSbCurrentUploadFm.form('submit',{
		success:function(d){
			vcxSbCurrentDt.datagrid('reload');
			vcxSbCurrentUploadDg.dialog('close');
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
		data(getUrl('vcxSbCurrent', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_vcxSbCurrent_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_vcxSbCurrent_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_vcxSbCurrent_datagrid_searchbox"
					pdt="i_sy_vcxSbCurrent_datagrid"></input>
					<div id="i_sy_vcxSbCurrent_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_vcxSbCurrent_datagrid"></table> 
 
 <div id="i_sy_vcxSbCurrent_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_vcxSbCurrent_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_vcxSbCurrent_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_vcxSbCurrent_datagrid_edit_btn',toolbar:[{
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
<div id="i_sy_vcxSbCurrent_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_vcxSbCurrent_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_vcxSbCurrent_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_vcxSbCurrent_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
