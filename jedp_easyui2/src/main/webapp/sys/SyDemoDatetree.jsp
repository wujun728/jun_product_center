<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyDemoDatetree.js"></script>
<script>
var syDemoDatetreeId="i_sy_syDemoDatetree_datagrid";
var syDemoDatetreeDt,syDemoDatetreeUploadDg,syDemoDatetreeUploadFm;
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
					field : 'doroodoDate',
						title:'时间', 
						addform:{type:'eType.DateBox'}, 
						editform:{type:'eType.DateBox'}, 
						width : '150',
						formatter: function(value,row,index){
	               			if(value){
	               				var p="";
	    	            				var ys=value.split('年');
	    	            				var year=ys[0];
	    	            				p+=year;
	    	            				if(ys[1]){
	    		            				var yss=ys[1].split('月');
	    		            				var month=yss[0];
	    		            				p+=":"+month;
	    		            				if(yss[1]){
	    		            					var week=yss[1].split('周')[0].replaceAll('第','');
	    		            					p+=":"+week;
	    		            				}
	    	            				}
	    	            				row['doroodoDate']=value+'!'+p;
	               			}
	               			return value;
	               		} 
				 }, 
				{
					field : 'explainv',
						title:'使用说明', 
						addform:{type:'eType.HtmlEdit'}, 
						editform:{type:'eType.HtmlEdit'}, 
						hidden:true, 
						width : '150'
				 } 
            	] ];
$(function(){
	$('#i_sy_syDemoDatetree_datagrid_add_dialog').dialog({
		onOpen:function(){
			syDemoDatetreeAddOnOpen();
		}
	});
	
	$('#i_sy_syDemoDatetree_datagrid_edit_dialog').dialog({
		onOpen:function(){
			syDemoDatetreeEditOnOpen();
		}
	});
	pageView(syDemoDatetreeId,columns);
	syDemoDatetreeonload();
	$("#distance").tabs({
		onSelect:function(title,index){
			if(title=="说明"){
				getThext();
			}
		}
	});
});

//模式add
function pageView_add(){
	$('#'+syDemoDatetreeId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDemoDatetreeId+'_add_btn a').off().click(function(){
		if(!AddBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDemoDatetreeId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDemoDatetree'+"."+field]=eGet('#'+syDemoDatetreeId+'_add_form_'+field);
		}
		data(getUrl('syDemoDatetree','Add'),setData,'json',null);
	});
	$('#'+syDemoDatetreeId+'_add_dialog').dialog('open');
}

//模式edit
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syDemoDatetree','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syDemoDatetreeId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syDemoDatetreeId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syDemoDatetreeId+'_edit_btn a').off().click(function(){
		if(!EditBtnClick()) return;
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syDemoDatetreeId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syDemoDatetree'+"."+field]=eGet('#'+syDemoDatetreeId+'_edit_form_'+field);
		}
		data(getUrl('syDemoDatetree','Update'),setData,'json',null);
	});
	$('#'+syDemoDatetreeId+'_edit_dialog').dialog('open');
}

//模式list
function pageView_list(){
	var syDemoDatetreeDataGrid = {
			id:syDemoDatetreeId,
			url:'${ctx}'+actionUrl('/sys/','syDemoDatetree','List')+'?year='+new Date().getFullYear(),
			dId:'id',
			columns:columns,
			dTreeId:'doroodoDate',//需要修改
			etype:eType.TreeGrid,
			dExpandUrl:'${ctx}'+actionUrl('/sys/','syDemoDatetree','List_tree')+'?year=',
			dExpandId:'doroodoDate'//需要修改
			
	};
	
	syDemoDatetreeUploadDg = $('#i_sy_syDemoDatetree_datagrid_upload_dialog');
	syDemoDatetreeUploadFm =$('#i_sy_syDemoDatetree_datagrid_upload_dialog_form');
	syDemoDatetreeUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syDemoDatetree','Upload'));
	
	syDemoDatetreeDt=gGrid2(syDemoDatetreeDataGrid);	
	var straddfun="dorow(syDemoDatetreeId,'','${ctx}"+actionUrl('/sys/','syDemoDatetree','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syDemoDatetreeId,'icon-add',straddfun,"新增");
	var stredit="dorow(syDemoDatetreeId,'','${ctx}"+actionUrl('/sys/','syDemoDatetree','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syDemoDatetreeId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(syDemoDatetreeId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syDemoDatetree','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syDemoDatetreeId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syDemoDatetreeId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syDemoDatetree','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syDemoDatetreeId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syDemoDatetreeId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	syDemoDatetreeUploadDg.dialog('open');
}

function submitUploadForm(){
	syDemoDatetreeUploadFm.form('submit',{
		success:function(d){
			syDemoDatetreeDt.datagrid('reload');
			syDemoDatetreeUploadDg.dialog('close');
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
		data(getUrl('syDemoDatetree', 'FormFile'),setData,
		'json', function(d){if(type=='word'){
			window.open(top.sysPath+'/report/word.jsp',new Date().getTime());
		}else if(type=='excel'){
			window.open(top.sysPath+'/report/excel.jsp',new Date().getTime());
		}});
	
}

function getThext(){
	 var setData = {};
	 setData.name = "日期树表格";//需要修改
	data(getUrl('syWebmodal','Get_ByName'),setData,'json',updateFun);
} 

function updateFun(d){
	$('#explains').html(d.info);
}
</script>
</head>
<body>
<!-- 修改开始 -->
<div id="distance" class="easyui-tabs" data-options="tabPosition:'left'" headerWidth="50" fit="true" > 
	<div title="演示" >
	<!-- 演示开始 -->
	<div class="easyui-layout" fit="true">
<!-- 修改结束-->
<div id="i_sy_syDemoDatetree_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syDemoDatetree_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syDemoDatetree_datagrid_searchbox"
					pdt="i_sy_syDemoDatetree_datagrid"></input>
					<div id="i_sy_syDemoDatetree_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syDemoDatetree_datagrid"></table> 
 
 <div id="i_sy_syDemoDatetree_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syDemoDatetree_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syDemoDatetree_datagrid_add_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td class="label" style="width:15%;" align="right">时间</td><td style="width:85%;" align="left"><input id="i_sy_syDemoDatetree_datagrid_add_form_doroodoDate" class="easyui-datebox" reftype="6" type="text"></td></tr><tr><td class="label" style="width:15%;" align="right">使用说明</td><td style="width:85%;" align="left"><div id="i_sy_syDemoDatetree_datagrid_add_form_explainv" reftype="13"></div></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:85%;display:none;" align="left"><input id="i_sy_syDemoDatetree_datagrid_add_form_id" reftype="-1" type="text"></td></tr></tbody></table></form><script>function i_sy_syDemoDatetree_datagrid_add_form_explainvuei(){var str="";var style=$("#i_sy_syDemoDatetree_datagrid_add_form_explainv").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syDemoDatetree_datagrid_add_form_explainvuedit = new UE.ui.Editor(i_sy_syDemoDatetree_datagrid_add_form_explainvuei()); i_sy_syDemoDatetree_datagrid_add_form_explainvuedit.render("i_sy_syDemoDatetree_datagrid_add_form_explainv");top.formfieldmap.put("i_sy_syDemoDatetree_datagrid_add_form_explainv",i_sy_syDemoDatetree_datagrid_add_form_explainvuedit);</script>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syDemoDatetree_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syDemoDatetree_datagrid_edit_btn',toolbar:[{
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
<form id="i_sy_syDemoDatetree_datagrid_edit_form"><table width="99%" border="1" class="formtable" ><tbody><tr><td class="label" style="width:15%;" align="right">时间</td><td style="width:85%;" align="left"><input id="i_sy_syDemoDatetree_datagrid_edit_form_doroodoDate" class="easyui-datebox" reftype="6" type="text"></td></tr><tr><td class="label" style="width:15%;" align="right">使用说明</td><td style="width:85%;" align="left"><div id="i_sy_syDemoDatetree_datagrid_edit_form_explainv" reftype="13"></div></td></tr><tr style="display:none;"><td class="label" style="width:15%;display:none;" align="right">编号</td><td style="width:85%;display:none;" align="left"><input id="i_sy_syDemoDatetree_datagrid_edit_form_id" reftype="-1" type="text"></td></tr></tbody></table></form><script>function i_sy_syDemoDatetree_datagrid_edit_form_explainvuei(){var str="";var style=$("#i_sy_syDemoDatetree_datagrid_edit_form_explainv").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var i_sy_syDemoDatetree_datagrid_edit_form_explainvuedit = new UE.ui.Editor(i_sy_syDemoDatetree_datagrid_edit_form_explainvuei()); i_sy_syDemoDatetree_datagrid_edit_form_explainvuedit.render("i_sy_syDemoDatetree_datagrid_edit_form_explainv");top.formfieldmap.put("i_sy_syDemoDatetree_datagrid_edit_form_explainv",i_sy_syDemoDatetree_datagrid_edit_form_explainvuedit);</script>
<!-- 请填入编辑表单html  end -->
<div id="report_ps"> 
</div>
</div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syDemoDatetree_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syDemoDatetree_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syDemoDatetree_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syDemoDatetree_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  
</div>
</div>
<div id="explains" title="说明"" style="overflow:auto;padding:20px;">  
</div>
</div>
</body>
</html>
