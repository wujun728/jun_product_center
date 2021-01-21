<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp" %>
<%@ include file="/include/meta.jsp" %>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/CxSbTerminalConfig.js"></script>
<script>
var cxSbTerminalConfigId="i_sy_cxSbTerminalConfig_datagrid";
var cxSbTerminalConfigDt,cxSbTerminalConfigUploadDg,cxSbTerminalConfigUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		title:'终端编号', 
            		width : '150'
            	  }, 
             	 {
              		field : 'address',
              		title:'终端地址', 
              		addform:{type:'eType.Input'}, 
              		editform:{type:'eType.Input', hidden:true}, 
              		map:'v_cx_sb_terminal|id|terminalNumber', 
              		width : '150'
              	  } , 
             	 {
              		field : 'line',
              		title:'所在线路', 
              		addform:{type:'eType.Input'}, 
              		editform:{type:'eType.Input', hidden:true}, 
              		map:'v_cx_sb_terminal|id|lineName', 
              		width : '150'
              	  },  {
            		field : 'postion',
            		title:'安装位置', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		map:'v_cx_sb_terminal|id|postion', 
            		width : '150'
            	  }, 
            	 
            	 {
            		field : 'leakageCurrent',
            		checkbox:false, 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		title:'漏电电流阀值', 
            		width : '100'
            	  }, 
            	 {
            		field : 'overCurrent',
            		checkbox:false, 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		title:'过流电流阀值', 
            		width : '100'
            	  }, 
            	 {
            		field : 'handleTime',
            		title:'分合时间', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input'}, 
            		width : '100'
            	  }, 
            	  {
              		field : 'configDate',
              		checkbox:false, 
              		addform:{type:'eType.Input'}, 
              		editform:{type:'eType.Input', hidden:true}, 
              		title:'配置时间', 
              		width : '150'
              	  }, 
            	 {
            		field : 'factory',
            		title:'设备厂家', 
            		addform:{type:'eType.Input'}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		map:'v_cx_sb_terminal|id|factoryName', 
            		width : '150'
            	  }
            	] ];
$(function(){
	$('#i_sy_cxSbTerminalConfig_datagrid_add_dialog').dialog({
		onOpen:function(){
			cxSbTerminalConfigAddOnOpen();
		}
	});
	
	$('#i_sy_cxSbTerminalConfig_datagrid_edit_dialog').dialog({
		onOpen:function(){
			cxSbTerminalConfigEditOnOpen();
		}
	});
	pageView(cxSbTerminalConfigId,columns);
	cxSbTerminalConfigonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+cxSbTerminalConfigId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbTerminalConfigId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbTerminalConfigId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbTerminalConfig'+"."+field]=eGet('#'+cxSbTerminalConfigId+'_add_form_'+field);
		}
		data(getUrl('cxSbTerminalConfig','Add'),setData,'json',null);
	});
	$('#'+cxSbTerminalConfigId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('cxSbTerminalConfig','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,cxSbTerminalConfigId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+cxSbTerminalConfigId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbTerminalConfigId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbTerminalConfigId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbTerminalConfig'+"."+field]=eGet('#'+cxSbTerminalConfigId+'_edit_form_'+field);
		}
		data(getUrl('cxSbTerminalConfig','Update'),setData,'json',null);
	});
	$('#'+cxSbTerminalConfigId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var cxSbTerminalConfigDataGrid = {
			id:cxSbTerminalConfigId,
			url:'${ctx}'+actionUrl('/sys/','cxSbTerminalConfig','List'),
			dId:'id',
			columns:columns
	};
	
	cxSbTerminalConfigUploadDg = $('#i_sy_cxSbTerminalConfig_datagrid_upload_dialog');
	cxSbTerminalConfigUploadFm =$('#i_sy_cxSbTerminalConfig_datagrid_upload_dialog_form');
	cxSbTerminalConfigUploadFm.attr('action','${ctx}'+actionUrl('/sys/','cxSbTerminalConfig','Upload'));
	
	cxSbTerminalConfigDt=gGrid2(cxSbTerminalConfigDataGrid);

	var stredit="dorow(cxSbTerminalConfigId,'','${ctx}"+actionUrl('/sys/','cxSbTerminalConfig','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(cxSbTerminalConfigId,'icon-edit',stredit,"修改配置");
	var strconfig="batchConfig(cxSbTerminalConfigId,'','${ctx}"+actionUrl('/sys/','cxSbTerminalConfig','BatchConfig')+"')";
	gBatchDataGridToolbarBtn(cxSbTerminalConfigId,'icon-edit',strconfig,"批量配置");
	
	var strexcelfun="dorow(cxSbTerminalConfigId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','cxSbTerminalConfig','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(cxSbTerminalConfigId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(cxSbTerminalConfigId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(cxSbTerminalConfigId,'icon-page_find','doroodo_search()',"复合查询");
}

function gBatchDataGridToolbarBtn(pid,iconCls,callback,text){
	var id=pid+'_toolbtn_batch_'+iconCls;
	var html='<a href="javascript:void(0)" id="'+id+'" class="easyui-linkbutton" data-options="iconCls:\''+iconCls+'\',plain:true" onclick="'+callback+';">'+text+'</a>';
	var obj = $('#'+pid+'_toolbtn');
	obj.append(html);
	$.parser.parse(obj);
	return id;
}

function batchConfig(pid,msg,url){
	var eobj=$('#'+pid);
	var eReftype=parseInt(eobj.attr('reftype'));
	var rows=[];var rows_=[];var ids = [];//选择的的行
	var addrs=[];
	rows_=eobj.datagrid('getChecked');
	for ( var i = 0; i < rows_.length; i++) {
		if((rows_[i].id+'').substring(0,1)!='-'){
			ids.push(rows_[i].id);
			addrs.push(rows_[i].address.replaceAll(",",";"));
			rows.push(rows_[i]);
		}
	}
	 if (rows.length>0) {
				//if(ids.length!=1) return log("请选择一行!");
				var columns=eobj.datagrid('options').columns;
				var arg={}
				//arg["id"]=ids.join(',');
				arg["id"]=rows_[0].id;
				data(url.replaceAll('_BatchConfig','_Get_ById'),arg,'json',function(d){
					if(d){
						for (var i in d){
							eSet('#'+pid+'_batchedit_form_'+i,d[i]);
						}
					}
				});
				
				
				$('#'+pid+'_batchedit_dialog').dialog({width:$(self).width(), 
					height:($(self).height()*2)/3,
					top:($(self).height()*1)/3,
					modal:false});
				var editDlg=$('#'+pid+'_batchedit_dialog');
				$('#'+pid+'_batchedit_btn a').off().click(function(){
					if(typeof(EditBtnClick)!='undefined'){if(!EditBtnClick()) return;}
					var columns,modelname;
					switch(eReftype){
						case eType.TreeGrid:
							columns =eobj.treegrid('options').columns;
							modelname=eobj.treegrid('options').dmodel;
							break;
						default:
							columns =eobj.datagrid('options').columns;
							modelname=eobj.datagrid('options').dmodel;
					}
					var setData={};
					setData["ids"]=ids.join(',');
					setData["addrs"]=addrs.join(',');
					for(var i=0; i<columns[0].length; i++){
						var field=columns[0][i].field;
						if(!checkFormField('#'+pid+'_batchedit_form_'+field)) return;
						setData[modelname+"."+field]=eGet('#'+pid+'_batchedit_form_'+field);
					}
					data(url,setData,'json',function(d){
						log(d.msg)
					});
					editDlg.dialog('close');
				});
				editDlg.dialog({selrows:rows});
				editDlg.dialog('open');
			
	}else {
		log("请选择要操作的数据!");
	}
	return ids;
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
				cxSbTerminalConfigDt.datagrid('load', obj.getSearchs('cxSbTerminalConfig'));
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
	cxSbTerminalConfigUploadDg.dialog('open');
}

function submitUploadForm(){
	cxSbTerminalConfigUploadFm.form('submit',{
		success:function(d){
			cxSbTerminalConfigDt.datagrid('reload');
			cxSbTerminalConfigUploadDg.dialog('close');
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
		data(getUrl('cxSbTerminalConfig', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_cxSbTerminalConfig_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_cxSbTerminalConfig_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_cxSbTerminalConfig_datagrid_searchbox"
					pdt="i_sy_cxSbTerminalConfig_datagrid"></input>
					<div id="i_sy_cxSbTerminalConfig_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_cxSbTerminalConfig_datagrid"></table> 
 
 <div id="i_sy_cxSbTerminalConfig_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_cxSbTerminalConfig_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_cxSbTerminalConfig_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbTerminalConfig_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_cxSbTerminalConfig_datagrid_edit_form"><table width="99%" border="1" class="formtable" ><tbody><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">所在线路</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_line" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">设备厂家</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_factory" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">终端地址</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_address" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">漏电电流</td><td align="left" style="width:85%;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_leakageCurrent" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">过流电流阀值</td><td align="left" style="width:85%;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_overCurrent" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">分合时间</td><td align="left" style="width:85%;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_handleTime" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">终端编号</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_id" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">安装位置</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_postion" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">配置时间</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_edit_form_configDate" type="text" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>



<div id="i_sy_cxSbTerminalConfig_datagrid_batchedit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbTerminalConfig_datagrid_batchedit_btn',toolbar:[{
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
<div id="report_batch">
<!-- 请填入编辑表单html  start -->
<form id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form"><table width="99%" border="1" class="formtable" ><tbody><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">所在线路</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_line" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">设备厂家</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_factory" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">终端地址</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_address" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">漏电电流</td><td align="left" style="width:85%;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_leakageCurrent" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">过流电流阀值</td><td align="left" style="width:85%;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_overCurrent" type="text" reftype="-1"></td></tr><tr><td class="label" align="right" style="width:15%;">分合时间</td><td align="left" style="width:85%;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_handleTime" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">终端编号</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_id" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">安装位置</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_postion" type="text" reftype="-1"></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">配置时间</td><td align="left" style="width:85%;display:none;"><input id="i_sy_cxSbTerminalConfig_datagrid_batchedit_form_configDate" type="text" reftype="-1"></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps_batch"> 
</div>
</div>
</div>
</div>


<!-- 按钮 start -->
<div id="i_sy_cxSbTerminalConfig_datagrid_batchedit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">批量配置</a>
	</div>
<div id="i_sy_cxSbTerminalConfig_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_cxSbTerminalConfig_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_cxSbTerminalConfig_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_cxSbTerminalConfig_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
