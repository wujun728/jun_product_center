<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyChart.js"></script>
<script>
var syChartId="i_sy_syChart_datagrid";
var syChartDt,syChartUploadDg,syChartUploadFm;
var eChart_url='/plug/ichartjs-designer/index.html';
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
            		title:'主题', 
            		addform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'createuser',
            		title:'创建人', 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'createtime',
            		title:'创建时间', 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'url',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		title:'系统链接', 
            		width : '250',
            			formatter: function(value, row, index) {
            			return '<input  type="text" style="color: red;" value="'+value+'" readonly></input>';
            			}
            	  },
            	  {
              		field : 'sysname',
              		title:'操作', 
              		addform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
              		editform:{type:'eType.ValidateBox', dataoptions:"required:true"}, 
              		tooltip:'如:mychart_1', 
              		width : '50',
            			formatter: function(value, row, index) {
            				var charturl_="/chart/"+value+".html";
                 			return '<a style="font-weight:bold;color:#666666;text-decoration: underline;margin:0px;padding:0px;" href="javascript:void(0)" onclick="editChart(\''+value+'\',\''+row.title+'\',\''+eChart_url+'\',\''+charturl_+'\')" >编辑|预览</a>';
                 		}
            	  }
            	] ];
$(function(){
	$('#i_sy_syChart_datagrid_add_dialog').dialog({
		onOpen:function(){
			syChartAddOnOpen();
		}
	});
	
	$('#i_sy_syChart_datagrid_edit_dialog').dialog({
		onOpen:function(){
			syChartEditOnOpen();
		}
	});
	pageView(syChartId,columns);
	syChartonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+syChartId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syChartId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syChartId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syChart'+"."+field]=eGet('#'+syChartId+'_add_form_'+field);
		}
		data(getUrl('syChart','Add'),setData,'json',null);
	});
	$('#'+syChartId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syChart','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syChartId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syChartId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syChartId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syChartId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syChart'+"."+field]=eGet('#'+syChartId+'_edit_form_'+field);
		}
		data(getUrl('syChart','Update'),setData,'json',null);
	});
	$('#'+syChartId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var syChartDataGrid = {
			id:syChartId,
			url:'${ctx}'+actionUrl('/sys/','syChart','List'),
			dId:'id',
			columns:columns
	};
	
	syChartUploadDg = $('#i_sy_syChart_datagrid_upload_dialog');
	syChartUploadFm =$('#i_sy_syChart_datagrid_upload_dialog_form');
	syChartUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syChart','Upload'));
	
	syChartDt=gGrid2(syChartDataGrid);	
	var straddfun="dorow(syChartId,'','${ctx}"+actionUrl('/sys/','syChart','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syChartId,'icon-add',straddfun,"新增");
	var stredit="dorow(syChartId,'','${ctx}"+actionUrl('/sys/','syChart','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syChartId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(syChartId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syChart','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syChartId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syChartId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syChart','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syChartId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syChartId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	syChartUploadDg.dialog('open');
}

function submitUploadForm(){
	syChartUploadFm.form('submit',{
		success:function(d){
			syChartDt.datagrid('reload');
			syChartUploadDg.dialog('close');
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
		data(getUrl('syChart', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syChart_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syChart_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syChart_datagrid_searchbox"
					pdt="i_sy_syChart_datagrid"></input>
					<div id="i_sy_syChart_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syChart_datagrid"></table> 
 
 <div id="i_sy_syChart_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syChart_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syChart_datagrid_add_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">主题</td><td align="left" style="width:35%;"><input id="i_sy_syChart_datagrid_add_form_title" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td><td class="label" align="right" style="width:15%;">文件名（英文）</td><td align="left" style="width:35%;"><input id="i_sy_syChart_datagrid_add_form_sysname" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/><a href="#" title="如:mychart_1" class="easyui-tooltip">填写说明</a></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_add_form_id" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">创建人</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_add_form_createuser" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">创建时间</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_add_form_createtime" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">系统链接</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_add_form_url" type="text" reftype="-1"/></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syChart_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syChart_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_syChart_datagrid_edit_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">主题</td><td align="left" style="width:35%;"><input id="i_sy_syChart_datagrid_edit_form_title" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td><td class="label" align="right" style="width:15%;">文件名（英文）</td><td align="left" style="width:35%;"><input id="i_sy_syChart_datagrid_edit_form_sysname" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/><a href="#" title="如:mychart_1" class="easyui-tooltip">填写说明</a></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_edit_form_id" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">创建人</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_edit_form_createuser" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">创建时间</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_edit_form_createtime" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">系统链接</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syChart_datagrid_edit_form_url" type="text" reftype="-1"/></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syChart_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syChart_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syChart_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syChart_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  
<div style="display:none" id="chart_data"></div>
</body>
</html>
