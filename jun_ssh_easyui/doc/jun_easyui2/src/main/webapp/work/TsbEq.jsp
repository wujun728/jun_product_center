<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/TsbEq.js"></script>
<script>
var tsbEqId="i_sy_tsbEq_datagrid";
var tsbEqDt,tsbEqUploadDg,tsbEqUploadFm;
var columns= [ [
           	 {
         		field : 'id',
         		addform:{type:'eType.Input', hidden:true}, 
         		editform:{type:'eType.Input', hidden:true}, 
         		checkbox:true, 
         		title:'编号', 
         		width : '50'
         	  }, 
         	 {
         		field : 'terminalNumber',
         		addform:{type:'eType.Input', dataoptions:"missingMessage:'不允许为空！', required:true" , 
         		hidden:false}, 
         		editform:{type:'eType.Input', dataoptions:"required:true" , 
         		hidden:false}, 
         		checkbox:false, 
         		title:'终端编号', 
         		width : '150'
         	  }, 
         	 {
         		field : 'position',
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		checkbox:false, 
         		title:'安装位置', 
         		width : '150'
         	  }, 
         	 {
         		field : 'circuit',
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		checkbox:false, 
         		title:'监测线路', 
         		width : '150'
         	  }, 
         	 {
         		field : 'voltage',
         		title:'电压', 
         		addform:{type:'eType.Input',hidden:false}, 
         		editform:{type:'eType.Input',hidden:false}, 
         		hidden:false, 
         		width : '60'
         	  }, 
         	 {
         		field : 'current',
         		title:'电流', 
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		hidden:false, 
         		width : '60'
         	  }, 
         	 {
         		field : 'activePower',
         		title:'有功', 
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		hidden:false, 
         		width : '60'
         	  }, 
         	 {
         		field : 'reactivePower',
         		title:'无功', 
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		hidden:false, 
         		width : '60'
         	  }, 
         	 {
         		field : 'powerFactor',
         		title:'功率因素', 
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		width : '80'
         	  }, 
         	 {
         		field : 'factory',
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		title:'厂家', 
         		checkbox:false, 
         		width : '80'
         	  }, 
         	 {
         		field : 'state',
         		title:'终端状态', 
         		addform:{type:'eType.ComboBox', dataoptions:"valueField:'text', url:'/doroodo/sys/cxSbStateData_ComboBox'"}, 
         		editform:{type:'eType.ComboBox', dataoptions:"url:'/doroodo/sys/cxSbStateData_ComboBox', valueField:'text'"}, 
         		width : '80'
         	  }, 
         	 {
         		field : 'simCard',
         		addform:{type:'eType.Input', hidden:false}, 
         		editform:{type:'eType.Input', hidden:false}, 
         		checkbox:false, 
         		title:'SIM卡号', 
         		width : '80'
         	  } ,
              {
              		field : 'd',
              		checkbox:false, 
              		addform:{type:'eType.Input', hidden:false}, 
              		editform:{type:'eType.Input', hidden:false}, 
              		title:'操作', 
              		width : '150',
              		formatter : function(value, row, index) {
    						return "<input type='button' value='分'></input><input type='button' value='合'></input>";
    				}
              	  } 
            	] ];
$(function(){
	$('#i_sy_tsbEq_datagrid_add_dialog').dialog({
		onOpen:function(){
			tsbEqAddOnOpen();
		}
	});
	
	$('#i_sy_tsbEq_datagrid_edit_dialog').dialog({
		onOpen:function(){
			tsbEqEditOnOpen();
		}
	});
	pageView(tsbEqId,columns);
	tsbEqonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+tsbEqId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+tsbEqId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+tsbEqId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['tsbEq'+"."+field]=eGet('#'+tsbEqId+'_add_form_'+field);
		}
		data(getUrl('tsbEq','Add'),setData,'json',null);
	});
	$('#'+tsbEqId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('tsbEq','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,tsbEqId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+tsbEqId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+tsbEqId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+tsbEqId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['tsbEq'+"."+field]=eGet('#'+tsbEqId+'_edit_form_'+field);
		}
		data(getUrl('tsbEq','Update'),setData,'json',null);
	});
	$('#'+tsbEqId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var tsbEqDataGrid = {
			id:tsbEqId,
			url:'${ctx}'+actionUrl('/sys/','tsbEq','List'),
			dId:'id',
			columns:columns
	};
	
	tsbEqUploadDg = $('#i_sy_tsbEq_datagrid_upload_dialog');
	tsbEqUploadFm =$('#i_sy_tsbEq_datagrid_upload_dialog_form');
	tsbEqUploadFm.attr('action','${ctx}'+actionUrl('/sys/','tsbEq','Upload'));
	
	tsbEqDt=gGrid2(tsbEqDataGrid);	
	var straddfun="dorow(tsbEqId,'','${ctx}"+actionUrl('/sys/','tsbEq','Add')+"',updateFun,'c')";
	//gDataGridToolbarBtn(tsbEqId,'icon-add',straddfun,"新增");
	var stredit="dorow(tsbEqId,'','${ctx}"+actionUrl('/sys/','tsbEq','Update')+"',updateFun,'u')";
	//gDataGridToolbarBtn(tsbEqId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(tsbEqId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','tsbEq','Delete')+"',updateFun,'d')";
	//gDataGridToolbarBtn(tsbEqId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(tsbEqId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','tsbEq','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(tsbEqId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(tsbEqId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(tsbEqId,'icon-page_find','doroodo_search()',"复合查询");
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
				tsbEqDt.datagrid('load', obj.getSearchs('tsbEq'));
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
	tsbEqUploadDg.dialog('open');
}

function submitUploadForm(){
	tsbEqUploadFm.form('submit',{
		success:function(d){
			tsbEqDt.datagrid('reload');
			tsbEqUploadDg.dialog('close');
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
		data(getUrl('tsbEq', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_tsbEq_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_tsbEq_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_tsbEq_datagrid_searchbox"
					pdt="i_sy_tsbEq_datagrid"></input>
					<div id="i_sy_tsbEq_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_tsbEq_datagrid"></table> 
 
 <div id="i_sy_tsbEq_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_tsbEq_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_tsbEq_datagrid_add_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;"><span style="color: rgb(255, 0, 0);">终端编号</span></td><td align="left" style="width:85%;"><input id="i_sy_tsbEq_datagrid_add_form_num" type="text" class="easyui-validatebox" data-options="missingMessage:'终端编号不能为空', required:true" reftype="0"/></td></tr><tr><td class="label" align="right" style="width:15%;">终端状态</td><td align="left" style="width:85%;"><input id="i_sy_tsbEq_datagrid_add_form_state" type="text" reftype="-1"/></td></tr><tr><td class="label" align="right" style="width:15%;">手机卡号</td><td align="left" style="width:85%;"><input id="i_sy_tsbEq_datagrid_add_form_sim" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td></tr><tr><td class="label" align="right" style="width:15%;">位置</td><td align="left" style="width:85%;"><input id="i_sy_tsbEq_datagrid_add_form_postion" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:85%;display:none;"><input id="i_sy_tsbEq_datagrid_add_form_id" type="text" reftype="-1"/></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_tsbEq_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_tsbEq_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_tsbEq_datagrid_edit_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">终端编号</td><td align="left" style="width:35%;"><input id="i_sy_tsbEq_datagrid_edit_form_num" type="text" class="easyui-validatebox" data-options="required:true, missingMessage:'这是编辑表单'" reftype="0"/></td><td class="label" align="right" style="width:15%;">终端状态</td><td align="left" style="width:35%;"><input id="i_sy_tsbEq_datagrid_edit_form_state" type="text" reftype="-1"/></td></tr><tr><td class="label" align="right" style="width:15%;">手机卡号</td><td align="left" style="width:35%;"><input id="i_sy_tsbEq_datagrid_edit_form_sim" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td><td class="label" align="right" style="width:15%;">位置</td><td align="left" style="width:35%;"><input id="i_sy_tsbEq_datagrid_edit_form_postion" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:35%;display:none;"><input id="i_sy_tsbEq_datagrid_edit_form_id" type="text" reftype="-1"/></td><td><br/></td><td><br/></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_tsbEq_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_tsbEq_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_tsbEq_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_tsbEq_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
