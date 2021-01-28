<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp" %>
<%@ include file="/include/meta.jsp" %>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/VcxSbBalanceData.js"></script>
<script>
var vcxSbBalanceDataId="i_sy_vcxSbBalanceData_datagrid";
var vcxSbBalanceDataDt,vcxSbBalanceDataUploadDg,vcxSbBalanceDataUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		search : false,
     				checkbox : true,
     				title : '#',
            		width : '120'
            	  }, 
            	 {
            		field : 'locationLine',
     				title : '管控线路',
            		search : false,
     				checkbox : false,
     				hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'standardLine',
            		search : false,
     				checkbox : false,
     				hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'factory',
            		search : false,
     				checkbox : false,
     				hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'postion',
            		title:'控制位置',
            		width : '120'
            	  }, 
            	 {
            		field : 'note',
            		search : false,
     				hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'address',
            		search : false,
     				hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'sim',
            		search : false,
     				hidden : true,
     				title:'通信sim卡',
            		width : '120'
            	  }, 
            	 {
            		field : 'name',
            		 title:'设备名',
            		width : '120'
            	  }, 
            	 {
            		field : 'type',
            		title:'设备型号',
            		hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'locationLineName',
            		 title:'监控线路',
            		width : '120'
            	  }, 
            	 {
            		field : 'org',
            		 title:'组织机构',
            		 hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'factoryName',
            		 title:'厂家',
            		 hidden : true,
            		width : '120'
            	  }, 
            	 {
            		field : 'standardLineName',
            		 title:'参考线路',
            		width : '120'
            	  }, 
            	 {
            		field : 'standard',
            		 title:'参考值',
            		width : '120'
            	  }, 
            	 {
            		field : 'a',
            		 title:'A相电流',
            		width : '120',
            		formatter : function(value, row, index) {
            			
            			if(parseFloat(row.standard)*1.15<=parseFloat(value))
              			{
            				return "<div style='background:red;width:99%;height:99%;text-align: center;color:yellow'>"+value+"</div>";
              			}
            			else if(parseFloat(row.standard)*0.85>=parseFloat(value)){
            				return "<div style='background:green;width:99%;height:99%;text-align: center;color:white'>"+value+"</div>";
            			}else{
            				return value;
            			}
    				}
            	  }, 
            	 {
            		field : 'b',
            		 title:'B相电流',
            		width : '120',
            		formatter : function(value, row, index) {
            			if(parseFloat(row.standard)*1.15<=parseFloat(value))
              			{
            				return "<div style='background:red;width:99%;height:99%;text-align: center;color:yellow'>"+value+"</div>";
              			}
            			else if(parseFloat(row.standard)*0.85>=parseFloat(value)){
            				return "<div style='background:green;width:99%;height:99%;text-align: center;color:white'>"+value+"</div>";
            			}else{
            				return value;
            			}
    				}
            	  }, 
            	 {
            		field : 'c',
            		 title:'C相电流',
            		width : '120',
            		formatter : function(value, row, index) {
            			if(parseFloat(row.standard)*1.15<=parseFloat(value))
              			{
            				return "<div style='background:red;width:99%;height:99%;text-align: center;color:yellow'>"+value+"</div>";
              			}
            			else if(parseFloat(row.standard)*0.85>=parseFloat(value)){
            				return "<div style='background:green;width:99%;height:99%;text-align: center;color:white'>"+value+"</div>";
            			}else{
            				return value;
            			}
    				}
            	  }, 
            	 {
            		field : 'saveDate',
            		 title:'数据采集时间',
            		 hidden : true,
            		 search : false,
            		width : '120'
            	  } , 
             	 {
              		field : 'dd',
              		 title:'操作',
              		search : false,
              		width : '120',
              		formatter : function(value, row, index) {
              			return "<div style='width:99%;text-algin:center;'><input type='button' style='width:90%' value='自动平衡' onclick='autoBalance("
						+ row['id']
						+ ",\""
						+ row.postion
						+ "\")'></input></div>";

    				}
              	  }
            	] ];
            	
function autoBalance(id,postion){
	$.messager.confirm('三相自动平衡确认', '确认对['+postion+']执行自动三相平衡命令！', function(r){
		if(!r)return;
		var cmd={};
		cmd["id"]=id;
		return;
		data(getUrl('vcxSbBalanceData', 'Control_ByCmd'), cmd, 'json', function(d){
			if(d=="0"){
				log('操作成功');
				cxSbTerminalDt.datagrid('reload');
			}else{
				log('操作失败');
			}
		});
      });
}
$(function(){
	$('#i_sy_vcxSbBalanceData_datagrid_add_dialog').dialog({
		onOpen:function(){
			vcxSbBalanceDataAddOnOpen();
		}
	});
	
	$('#i_sy_vcxSbBalanceData_datagrid_edit_dialog').dialog({
		onOpen:function(){
			vcxSbBalanceDataEditOnOpen();
		}
	});
	pageView(vcxSbBalanceDataId,columns);
	vcxSbBalanceDataonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+vcxSbBalanceDataId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+vcxSbBalanceDataId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+vcxSbBalanceDataId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['vcxSbBalanceData'+"."+field]=eGet('#'+vcxSbBalanceDataId+'_add_form_'+field);
		}
		data(getUrl('vcxSbBalanceData','Add'),setData,'json',null);
	});
	$('#'+vcxSbBalanceDataId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('vcxSbBalanceData','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,vcxSbBalanceDataId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+vcxSbBalanceDataId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+vcxSbBalanceDataId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+vcxSbBalanceDataId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['vcxSbBalanceData'+"."+field]=eGet('#'+vcxSbBalanceDataId+'_edit_form_'+field);
		}
		data(getUrl('vcxSbBalanceData','Update'),setData,'json',null);
	});
	$('#'+vcxSbBalanceDataId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var vcxSbBalanceDataDataGrid = {
			id:vcxSbBalanceDataId,
			url:'${ctx}'+actionUrl('/sys/','vcxSbBalanceData','List'),
			dId:'id',
			columns:columns
	};
	
	vcxSbBalanceDataUploadDg = $('#i_sy_vcxSbBalanceData_datagrid_upload_dialog');
	vcxSbBalanceDataUploadFm =$('#i_sy_vcxSbBalanceData_datagrid_upload_dialog_form');
	vcxSbBalanceDataUploadFm.attr('action','${ctx}'+actionUrl('/sys/','vcxSbBalanceData','Upload'));
	
	vcxSbBalanceDataDt=gGrid2(vcxSbBalanceDataDataGrid);	
	var strexcelfun="dorow(vcxSbBalanceDataId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','vcxSbBalanceData','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(vcxSbBalanceDataId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(vcxSbBalanceDataId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(vcxSbBalanceDataId,'icon-page_find','doroodo_search()',"复合查询");
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
				vcxSbBalanceDataDt.datagrid('load', obj.getSearchs('vcxSbBalanceData'));
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
	vcxSbBalanceDataUploadDg.dialog('open');
}

function submitUploadForm(){
	vcxSbBalanceDataUploadFm.form('submit',{
		success:function(d){
			vcxSbBalanceDataDt.datagrid('reload');
			vcxSbBalanceDataUploadDg.dialog('close');
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
		data(getUrl('vcxSbBalanceData', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_vcxSbBalanceData_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_vcxSbBalanceData_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_vcxSbBalanceData_datagrid_searchbox"
					pdt="i_sy_vcxSbBalanceData_datagrid"></input>
					<div id="i_sy_vcxSbBalanceData_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_vcxSbBalanceData_datagrid"></table> 
 
 <div id="i_sy_vcxSbBalanceData_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_vcxSbBalanceData_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_vcxSbBalanceData_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_vcxSbBalanceData_datagrid_edit_btn',toolbar:[{
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
<div id="i_sy_vcxSbBalanceData_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_vcxSbBalanceData_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_vcxSbBalanceData_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_vcxSbBalanceData_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
