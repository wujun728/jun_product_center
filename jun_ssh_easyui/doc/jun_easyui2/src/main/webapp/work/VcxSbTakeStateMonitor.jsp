<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp" %>
<%@ include file="/include/meta.jsp" %>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/VcxSbTakeStateMonitor.js"></script>
<script>
var vcxSbTakeStateMonitorId="i_sy_vcxSbTakeStateMonitor_datagrid";
var vcxSbTakeStateMonitorDt,vcxSbTakeStateMonitorUploadDg,vcxSbTakeStateMonitorUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		title:'序号',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'proState',
            		title:'智能断路器状态',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'proStateDesc',
            		title:'断路器保护状态',
            		formatter : function(value, row, index) {
    					if (row.note == "1") {
    						return "<div style='background:red;width:99%;height:99%;text-align: center;color:yellow'>"+row.proStateDesc+"</div>";
    					} else {
    						return "<div style='background:blue;width:99%;height:99%;text-align: center;color:white'>"+row.proStateDesc+"</div>";
    					}

    				},
            		width : '150'
            	  }, 
            	 {
            		field : 'faultCurrent',
            		title:'故障电流',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'leakageValue',
            		title:'漏电状态值',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'overValue',
            		title:'过流动作值',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'sign',
            		title:'标志',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'saveDate',
            		title:'采集时间',
            		width : '150'
            	  }, 
            	 {
            		field : 'terminalNumber',
            		title:'终端地址',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'position',
            		title:'安装位置',
            		width : '150'
            	  }, 
            	 {
            		field : 'circuit',
            		title:'安装线路',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'name',
            		title:'厂家',
            		width : '150'
            	  }, 
            	 {
            		field : 'state',
            		title:'状态',
      				search : false,
      				hidden : true,
            		width : '150'
            	  } , 
            	 {
            		field : 'lineId',
            		title:'安装线路',
      				search : false,
      				hidden : true,
            		width : '150'
            	  }, 
            	 {
            		field : 'lineName',
            		title:'安装线路',
            		width : '150'
            	  }, 
            	 {
            		field : 'org',
            		title:'所属机构',
            		width : '150'
            	  } 
            	] ];
$(function(){
	$('#i_sy_vcxSbTakeStateMonitor_datagrid_add_dialog').dialog({
		onOpen:function(){
			vcxSbTakeStateMonitorAddOnOpen();
		}
	});
	
	$('#i_sy_vcxSbTakeStateMonitor_datagrid_edit_dialog').dialog({
		onOpen:function(){
			vcxSbTakeStateMonitorEditOnOpen();
		}
	});
	pageView(vcxSbTakeStateMonitorId,columns);
	vcxSbTakeStateMonitoronload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+vcxSbTakeStateMonitorId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+vcxSbTakeStateMonitorId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+vcxSbTakeStateMonitorId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['vcxSbTakeStateMonitor'+"."+field]=eGet('#'+vcxSbTakeStateMonitorId+'_add_form_'+field);
		}
		data(getUrl('vcxSbTakeStateMonitor','Add'),setData,'json',null);
	});
	$('#'+vcxSbTakeStateMonitorId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('vcxSbTakeStateMonitor','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,vcxSbTakeStateMonitorId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+vcxSbTakeStateMonitorId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+vcxSbTakeStateMonitorId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+vcxSbTakeStateMonitorId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['vcxSbTakeStateMonitor'+"."+field]=eGet('#'+vcxSbTakeStateMonitorId+'_edit_form_'+field);
		}
		data(getUrl('vcxSbTakeStateMonitor','Update'),setData,'json',null);
	});
	$('#'+vcxSbTakeStateMonitorId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var vcxSbTakeStateMonitorDataGrid = {
			id:vcxSbTakeStateMonitorId,
			url:'${ctx}'+actionUrl('/sys/','vcxSbTakeStateMonitor','List'),
			dId:'id',
			columns:columns
	};
	
	vcxSbTakeStateMonitorUploadDg = $('#i_sy_vcxSbTakeStateMonitor_datagrid_upload_dialog');
	vcxSbTakeStateMonitorUploadFm =$('#i_sy_vcxSbTakeStateMonitor_datagrid_upload_dialog_form');
	vcxSbTakeStateMonitorUploadFm.attr('action','${ctx}'+actionUrl('/sys/','vcxSbTakeStateMonitor','Upload'));
	
	vcxSbTakeStateMonitorDt=gGrid2(vcxSbTakeStateMonitorDataGrid);	
	var strexcelfun="dorow(vcxSbTakeStateMonitorId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','vcxSbTakeStateMonitor','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(vcxSbTakeStateMonitorId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(vcxSbTakeStateMonitorId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(vcxSbTakeStateMonitorId,'icon-page_find','doroodo_search()',"复合查询");
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
				vcxSbTakeStateMonitorDt.datagrid('load', obj.getSearchs('vcxSbTakeStateMonitor'));
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
	vcxSbTakeStateMonitorUploadDg.dialog('open');
}

function submitUploadForm(){
	vcxSbTakeStateMonitorUploadFm.form('submit',{
		success:function(d){
			vcxSbTakeStateMonitorDt.datagrid('reload');
			vcxSbTakeStateMonitorUploadDg.dialog('close');
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
		data(getUrl('vcxSbTakeStateMonitor', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_vcxSbTakeStateMonitor_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_vcxSbTakeStateMonitor_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_vcxSbTakeStateMonitor_datagrid_searchbox"
					pdt="i_sy_vcxSbTakeStateMonitor_datagrid"></input>
					<div id="i_sy_vcxSbTakeStateMonitor_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_vcxSbTakeStateMonitor_datagrid"></table> 
 
 <div id="i_sy_vcxSbTakeStateMonitor_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_vcxSbTakeStateMonitor_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_vcxSbTakeStateMonitor_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_vcxSbTakeStateMonitor_datagrid_edit_btn',toolbar:[{
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
<div id="i_sy_vcxSbTakeStateMonitor_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_vcxSbTakeStateMonitor_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_vcxSbTakeStateMonitor_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_vcxSbTakeStateMonitor_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
