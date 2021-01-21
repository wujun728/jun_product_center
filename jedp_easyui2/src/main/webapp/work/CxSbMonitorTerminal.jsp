<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/CxSbMonitorTerminal.js"></script>
<script>
var cxSbMonitorTerminalId="i_sy_cxSbMonitorTerminal_datagrid";
var cxSbMonitorTerminalDt,cxSbMonitorTerminalUploadDg,cxSbMonitorTerminalUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', dataoptions:"required:false" , 
            		hidden:false}, 
            		title:'序号', 
            		hidden:false, 
            		width : '50'
            	  }, 
            	 {
            		field : 'pointName',
            		title:'监控点名称', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'voltageLevel',
            		title:'监控点电压等级', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '100'
            	  }, 
            	 {
            		field : 'relatedLineArea',
            		title:'监控终端所属线路及台区', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'location',
            		title:'监控终端装设具体位置', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'manufacturers',
            		title:'监控终端厂家', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '80'
            	  }, 
            	 {
            		field : 'mteminalType',
            		title:'监控终端型号', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '80'
            	  }, 
            	 {
            		field : 'mterminalCapacity',
            		title:'监控终端容量', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '80'
            	  }, 
            	 {
            		field : 'simCard',
            		title:'SIM 卡通讯', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '80'
            	  }, 
            	 {
            		field : 'ip',
            		title:'IP 地址', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '50'
            	  }, 
            	 {
            		field : 'communication',
            		title:'通讯方式', 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		hidden:false, 
            		width : '50'
            	  } 
            	] ];
$(function(){
	$('#i_sy_cxSbMonitorTerminal_datagrid_add_dialog').dialog({
		onOpen:function(){
			cxSbMonitorTerminalAddOnOpen();
		}
	});
	
	$('#i_sy_cxSbMonitorTerminal_datagrid_edit_dialog').dialog({
		onOpen:function(){
			cxSbMonitorTerminalEditOnOpen();
		}
	});
	pageView(cxSbMonitorTerminalId,columns);
	cxSbMonitorTerminalonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+cxSbMonitorTerminalId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbMonitorTerminalId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbMonitorTerminalId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbMonitorTerminal'+"."+field]=eGet('#'+cxSbMonitorTerminalId+'_add_form_'+field);
		}
		data(getUrl('cxSbMonitorTerminal','Add'),setData,'json',null);
	});
	$('#'+cxSbMonitorTerminalId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('cxSbMonitorTerminal','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,cxSbMonitorTerminalId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+cxSbMonitorTerminalId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbMonitorTerminalId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbMonitorTerminalId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbMonitorTerminal'+"."+field]=eGet('#'+cxSbMonitorTerminalId+'_edit_form_'+field);
		}
		data(getUrl('cxSbMonitorTerminal','Update'),setData,'json',null);
	});
	$('#'+cxSbMonitorTerminalId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var cxSbMonitorTerminalDataGrid = {
			id:cxSbMonitorTerminalId,
			url:'${ctx}'+actionUrl('/sys/','cxSbMonitorTerminal','List'),
			dId:'id',
			columns:columns
	};
	
	cxSbMonitorTerminalUploadDg = $('#i_sy_cxSbMonitorTerminal_datagrid_upload_dialog');
	cxSbMonitorTerminalUploadFm =$('#i_sy_cxSbMonitorTerminal_datagrid_upload_dialog_form');
	cxSbMonitorTerminalUploadFm.attr('action','${ctx}'+actionUrl('/sys/','cxSbMonitorTerminal','Upload'));
	
	cxSbMonitorTerminalDt=gGrid2(cxSbMonitorTerminalDataGrid);	
	var straddfun="dorow(cxSbMonitorTerminalId,'','${ctx}"+actionUrl('/sys/','cxSbMonitorTerminal','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(cxSbMonitorTerminalId,'icon-add',straddfun,"新增");
	var stredit="dorow(cxSbMonitorTerminalId,'','${ctx}"+actionUrl('/sys/','cxSbMonitorTerminal','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(cxSbMonitorTerminalId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(cxSbMonitorTerminalId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','cxSbMonitorTerminal','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(cxSbMonitorTerminalId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(cxSbMonitorTerminalId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','cxSbMonitorTerminal','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(cxSbMonitorTerminalId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(cxSbMonitorTerminalId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(cxSbMonitorTerminalId,'icon-page_find','doroodo_search()',"复合查询");
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
				cxSbMonitorTerminalDt.datagrid('load', obj.getSearchs('cxSbMonitorTerminal'));
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
	cxSbMonitorTerminalUploadDg.dialog('open');
}

function submitUploadForm(){
	cxSbMonitorTerminalUploadFm.form('submit',{
		success:function(d){
			cxSbMonitorTerminalDt.datagrid('reload');
			cxSbMonitorTerminalUploadDg.dialog('close');
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
		data(getUrl('cxSbMonitorTerminal', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_cxSbMonitorTerminal_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_cxSbMonitorTerminal_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_cxSbMonitorTerminal_datagrid_searchbox"
					pdt="i_sy_cxSbMonitorTerminal_datagrid"></input>
					<div id="i_sy_cxSbMonitorTerminal_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_cxSbMonitorTerminal_datagrid"></table> 
 
 <div id="i_sy_cxSbMonitorTerminal_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_cxSbMonitorTerminal_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_cxSbMonitorTerminal_datagrid_add_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td style="width: 15%;" class="label" align="right">监控点名称</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_pointName" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">监控终端型号</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_mteminalType" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控点电压等级</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_voltageLevel" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">监控终端容量</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_mterminalCapacity" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控终端所属线路及台区</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_relatedLineArea" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">SIM 卡通讯</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_simCard" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控终端装设具体位置</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_location" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">IP 地址</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_ip" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控终端厂家</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_manufacturers" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">通讯方式</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_communication" value="" type="text" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_cxSbMonitorTerminal_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbMonitorTerminal_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_cxSbMonitorTerminal_datagrid_add_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td style="width: 15%;" class="label" align="right">监控点名称</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_pointName" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">监控终端型号</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_mteminalType" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控点电压等级</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_voltageLevel" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">监控终端容量</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_mterminalCapacity" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控终端所属线路及台区</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_relatedLineArea" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">SIM 卡通讯</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_simCard" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控终端装设具体位置</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_location" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">IP 地址</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_ip" value="" type="text" reftype="-1"></td></tr><tr><td style="width: 15%;" class="label" align="right">监控终端厂家</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_manufacturers" value="" type="text" reftype="-1"></td><td style="width: 15%;" class="label" align="right">通讯方式</td><td style="width: 35%;" align="left"><input id="i_sy_cxSbMonitorTerminal_datagrid_add_form_communication" value="" type="text" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_cxSbMonitorTerminal_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_cxSbMonitorTerminal_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_cxSbMonitorTerminal_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_cxSbMonitorTerminal_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
