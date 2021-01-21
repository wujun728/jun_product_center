<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp" %>


<script id="pjs" type="text/javascript" src="${ctx}/js/template/gridToChart/gridToChart.js"></script>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/CxSbTest.js"></script>

<script>
var g_chart_url="/chart/HIDOROODO.html";//请在此处填写您所指定的chart的url
var cxSbTestId="i_sy_cxSbTest_datagrid";
var cxSbTestDt,cxSbTestUploadDg,cxSbTestUploadFm;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'编号', 
            		width : '150'
            	  }, 
            	 {
            		field : 'comp',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'厂家', 
            		map:'cx_sb_company|id|name', 
            		width : '150'
            	  }, 
            	 {
            		field : 'name',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'名字', 
            		width : '150'
            	  }, 
            	 {
            		field : 'note',
            		checkbox:false, 
            		addform:{type:'eType.Input', hidden:false}, 
            		editform:{type:'eType.Input', hidden:false}, 
            		title:'备注', 
            		width : '150'
            	  } 
            	] ];
            	
            	
         	
            	
$(function(){
	$('#i_sy_cxSbTest_datagrid_add_dialog').dialog({
		onOpen:function(){
			cxSbTestAddOnOpen();
		}
	});
	
	$('#i_sy_cxSbTest_datagrid_edit_dialog').dialog({
		onOpen:function(){
			cxSbTestEditOnOpen();
		}
	});
	pageView(cxSbTestId,columns);
	cxSbTestonload();
});

function updateFun(d){
	
}

//模式add
function pageView_add(){
	$('#'+cxSbTestId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbTestId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbTestId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbTest'+"."+field]=eGet('#'+cxSbTestId+'_add_form_'+field);
		}
		data(getUrl('cxSbTest','Add'),setData,'json',null);
	});
	$('#'+cxSbTestId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('cxSbTest','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,cxSbTestId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+cxSbTestId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+cxSbTestId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+cxSbTestId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['cxSbTest'+"."+field]=eGet('#'+cxSbTestId+'_edit_form_'+field);
		}
		data(getUrl('cxSbTest','Update'),setData,'json',null);
	});
	$('#'+cxSbTestId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var cxSbTestDataGrid = {
			id:cxSbTestId,
			url:'${ctx}'+actionUrl('/sys/','cxSbTest','List'),
			dId:'id',
			columns:columns
	};
	
	cxSbTestUploadDg = $('#i_sy_cxSbTest_datagrid_upload_dialog');
	cxSbTestUploadFm =$('#i_sy_cxSbTest_datagrid_upload_dialog_form');
	cxSbTestUploadFm.attr('action','${ctx}'+actionUrl('/sys/','cxSbTest','Upload'));
	
	cxSbTestDt=gGrid2(cxSbTestDataGrid);
	//以下为图表连接处
	cxSbTestDt.datagrid({
		onClickRow:function(rowIndex, rowData){
			$('#monitor_tabs').tabs('close','电流');
			$('#monitor_tabs').tabs('close','电压');

			$('#monitor_tabs').tabs('close','故障');
			$('#monitor_tabs').tabs('add',{  
	            title:'电流',  
	            content:'<div class="easyui-panel" id="chartPanel" fit=true><iframe id="chart-iframe" src="/doroodo/chart/highchart.html?id=123&height='+$('#chartPanel').height()+'&width='+$('#chartPanel').height()+'" scrolling="no" frameborder="0"  style="width:100%;height:100%;"></iframe></div>',  
	            iconCls:'icon-default'
	            //,
	            //uiurl:'/chart/HIDOROODO.html'
	        }); 
			$('#monitor_tabs').tabs('add',{  
	            title:'故障',  
	            content:'<iframe scrolling="no" frameborder="0"  src="/doroodo/sys/syParameter_Go_VcxSbTakeState" style="width:100%;height:100%;"></iframe>',  
	            iconCls:'icon-default'
	            //,
	            //uiurl:'/chart/HIDOROODO.html'
	        });
			//alert(rowData.name);
			//alert($('#chartPanel').height());
			//alert($('#chartPanel').width());
			$('#chart-iframe').attr('src','/doroodo/chart/highchart.html?id='+rowData.id+'&height='+$('#chartPanel').height()+'&width='+$('#chartPanel').width());
			//gridToChart('mainlayout_center_chart',rowData.name,g_chart_url+'?id='+rowData.id,0,0,200,400);
		}
	});
	var straddfun="dorow(cxSbTestId,'','${ctx}"+actionUrl('/sys/','cxSbTest','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(cxSbTestId,'icon-add',straddfun,"新增");
	var stredit="dorow(cxSbTestId,'','${ctx}"+actionUrl('/sys/','cxSbTest','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(cxSbTestId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(cxSbTestId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','cxSbTest','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(cxSbTestId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(cxSbTestId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','cxSbTest','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(cxSbTestId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(cxSbTestId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(cxSbTestId,'icon-page_find','doroodo_search()',"复合查询");
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
				cxSbTestDt.datagrid('load', obj.getSearchs('cxSbTest'));
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
	cxSbTestUploadDg.dialog('open');
}

function submitUploadForm(){
	cxSbTestUploadFm.form('submit',{
		success:function(d){
			cxSbTestDt.datagrid('reload');
			cxSbTestUploadDg.dialog('close');
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
		data(getUrl('cxSbTest', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:260px;">
<div id="i_sy_cxSbTest_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_cxSbTest_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_cxSbTest_datagrid_searchbox"
					pdt="i_sy_cxSbTest_datagrid"></input>
					<div id="i_sy_cxSbTest_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_cxSbTest_datagrid"></table> 
 </div>
 
 <div data-options="region:'center'">
	<div id="mainlayout_center_chart" style="width:100%;height:100%;" reftype="20">
		<div class="easyui-tabs" fit="true" id="monitor_tabs">
		<div title="电流" style="padding:10px" data-options="url:'syParameter_Go_CxSbTest',method:'get',animate:true">
			电流曲线
		</div>
		<div title="电压" style="padding:10px">
			电压曲线
		</div>
		<div title="故障" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
			故障统计
		</div>
	</div>
	
	</div>
 </div>
 
 <div id="i_sy_cxSbTest_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_cxSbTest_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->

<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_cxSbTest_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbTest_datagrid_edit_btn',toolbar:[{
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
<div id="i_sy_cxSbTest_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_cxSbTest_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_cxSbTest_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_cxSbTest_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
