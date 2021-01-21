<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
function gId(field,ero){return '#'+syNoticeId+'_'+ero+'_form_'+field;}
var syNoticeId="i_sy_syNotice_datagrid";
var syNoticeDt,syNoticeUploadDg,syNoticeUploadFm;
var g_viewModal=false;
var columns= [ [
            	 {
            		field : 'id',
            		checkbox:true, 
            		title:'编号', 
            		addform:{type:'eType.Input', hidden:true}, 
            		editform:{type:'eType.Input', hidden:true}, 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'thetext',
            		title:'正文', 
            		addform:{hidden:false, type:'eType.HtmlEdit'}, 
            		editform:{hidden:false, type:'eType.HtmlEdit'}, 
            		hidden:true, 
            		width : '150'
            	  }, 
            	 {
            		field : 'createtime',
            		title:'创建时间', 
            		hidden:false, 
            		addform:{hidden:true, type:'eType.DateTimeBox'}, 
            		editform:{hidden:true, type:'eType.DateTimeBox'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'ceateuserid',
            		title:'创建人',
            		addform:{setvalue:"getCurrentUserId()", readonly:true, hidden:false, type:'eType.Input'}, 
            		editform:{readonly:true, hidden:false, type:'eType.Input'}, 
            		hidden:false, 
            		width : '150'
            	  }, 
            	 {
            		field : 'title',
            		checkbox:false, 
            		title:'标题', 
            		hidden:false, 
            		addform:{hidden:false, type:'eType.Input'}, 
            		editform:{hidden:false, type:'eType.Input'}, 
            		width : '150'
            	  }, 
            	 {
            		field : 'readusernames',
            		title:'已阅用户',
            		addform:{hidden:true, type:'eType.Input'}, 
            		editform:{hidden:true, type:'eType.Input'}, 
            		width : '150'
            	  } 
            	] ];
$(function(){
	pageView(syNoticeId,columns);
	
	$('#i_sy_syNotice_datagrid_add_dialog').dialog({
		onOpen:function(){
			initAddFileDt();
		}
	});
	
	$('#i_sy_syNotice_datagrid_edit_dialog').dialog({
		onOpen:function(){
			initEditFileDt();
		}
	});
});

function updateFun(d){
	if(d.info=='新增成功!'){
		var setData={'ids':g_Ids,'fileid':d.fileid};
		data(getUrl('syFile','Update'),setData,'json',null);
	}else if(d.info=='删除成功!'){
		var setData={'fileids':d.fileids};
		data(getUrl('syFile','Delete_ByFileIds'),setData,'json',null);
	}
}

//模式add （通过链接pageView=add访问）
function pageView_add(){
	$('#'+syNoticeId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syNoticeId+'_add_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syNoticeId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syNotice'+"."+field]=eGet('#'+syNoticeId+'_add_form_'+field);
		}
		data(getUrl('syNotice','Add'),setData,'json',null);
	});
	$('#'+syNoticeId+'_add_dialog').dialog('open');
}

//模式edit （通过链接pageView=edit访问）
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syNotice','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syNoticeId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syNoticeId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syNoticeId+'_edit_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syNoticeId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syNotice'+"."+field]=eGet('#'+syNoticeId+'_edit_form_'+field);
		}
		data(getUrl('syNotice','Update'),setData,'json',null);
	});
	$('#'+syNoticeId+'_edit_dialog').dialog('open');
}

//模式list （通过链接pageView=list访问）
function pageView_list(){
	var syNoticeDataGrid = {
			id:syNoticeId,
			url:'${ctx}'+actionUrl('/sys/','syNotice','List'),
			dId:'id',
			columns:columns
	};
	
	syNoticeUploadDg = $('#i_sy_syNotice_datagrid_upload_dialog');
	syNoticeUploadFm =$('#i_sy_syNotice_datagrid_upload_dialog_form');
	syNoticeUploadFm.attr('action','${ctx}'+actionUrl('/sys/','syNotice','Upload'));
	
	syNoticeDt=gGrid2(syNoticeDataGrid);	
	var straddfun="dorow(syNoticeId,'','${ctx}"+actionUrl('/sys/','syNotice','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syNoticeId,'icon-add',straddfun,"新增");
	var stredit="dorow(syNoticeId,'','${ctx}"+actionUrl('/sys/','syNotice','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syNoticeId,'icon-edit',stredit,"修改");
	var strdelfun="dorow(syNoticeId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syNotice','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syNoticeId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syNoticeId,'您确定要导出数据','${ctx}"+actionUrl('/sys/','syNotice','Excel')+"',updateFun,'e')";
	gDataGridToolbarBtn(syNoticeId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syNoticeId,'icon-page_white_excel','upLoadFun()',"导入");
	gDataGridToolbarBtn(syNoticeId,'icon-page_find','doroodo_search()',"复合查询");
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
				syNoticeDt.datagrid('load', obj.getSearchs('syNotice'));
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
	syNoticeUploadDg.dialog('open');
}

var g_fileid='';
function initAddFileDt(){
	g_fileid='';
	$('#i_sy_syNotice_datagrid_edit_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/empty.jsp');
	$('#i_sy_syNotice_datagrid_add_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/SyFile_.jsp');
}

function initEditFileDt(){
	g_fileid='syNotice-'+eGet('#'+syNoticeId+'_edit_form_id');
	$('#i_sy_syNotice_datagrid_add_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/empty.jsp');
	$('#i_sy_syNotice_datagrid_edit_form_file_form').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/SyFile_.jsp');
}

function submitUploadForm(){
	syNoticeUploadFm.form('submit',{
		success:function(d){
			syNoticeDt.datagrid('reload');
			syNoticeUploadDg.dialog('close');
			d=$.parseJSON(d);
			log(d.info);
			}
	});
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syNotice_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syNotice_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syNotice_datagrid_searchbox"
					pdt="i_sy_syNotice_datagrid"></input>
					<div id="i_sy_syNotice_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syNotice_datagrid"></table> 
 
 <div id="i_sy_syNotice_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syNotice_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syNotice_datagrid_add_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="center">标题</td><td align="center"><input id="i_sy_syNotice_datagrid_add_form_title" type="text" reftype="-1"/></td><td class="label" align="center" style="display:none;">编号</td><td align="center" style="display:none;"><input id="i_sy_syNotice_datagrid_add_form_id" type="text" reftype="-1"/></td></tr><tr><td class="label" align="center">正文</td><td align="center"><div id="i_sy_syNotice_datagrid_add_form_thetext" reftype="13"></div></td><td class="label" align="center" style="display:none;">创建时间</td><td align="center" style="display:none;"><input id="i_sy_syNotice_datagrid_add_form_createtime" type="text" class="easyui-datetimebox" reftype="7"/></td></tr><tr><td class="label" align="center">创建人</td><td align="center"><input id="i_sy_syNotice_datagrid_add_form_ceateuserid" type="text" reftype="-1"/></td><td class="label" align="center" style="display:none;">已阅用户</td><td align="center" style="display:none;"><input id="i_sy_syNotice_datagrid_add_form_readusernames" type="text" reftype="-1"/></td></tr></tbody></table></form><script>var uedit = new UE.ui.Editor(); uedit.render("i_sy_syNotice_datagrid_add_form_thetext");top.formfieldmap.put("i_sy_syNotice_datagrid_add_form_thetext",uedit);</script><script>$(function(){eSet("#i_sy_syNotice_datagrid_add_form_ceateuserid",getCurrentUserId());});</script>
<!-- 请填入新建表单html  end -->
<div class="titlep">附件</div>
<div id="i_sy_syNotice_datagrid_add_form_file_form"></div>
</div>
</div>
<div id="i_sy_syNotice_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syNotice_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">编辑</div>
<!-- 请填入编辑表单html  start -->
<form id="i_sy_syNotice_datagrid_edit_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="center">标题</td><td align="center"><input id="i_sy_syNotice_datagrid_edit_form_title" type="text" reftype="-1"/></td><td class="label" align="center" style="display:none;">编号</td><td align="center" style="display:none;"><input id="i_sy_syNotice_datagrid_edit_form_id" type="text" reftype="-1"/></td></tr><tr><td class="label" align="center">正文</td><td align="center"><div id="i_sy_syNotice_datagrid_edit_form_thetext" reftype="13"></div></td><td class="label" align="center" style="display:none;">创建时间</td><td align="center" style="display:none;"><input id="i_sy_syNotice_datagrid_edit_form_createtime" type="text" class="easyui-datetimebox" reftype="7"/></td></tr><tr><td class="label" align="center">创建人</td><td align="center"><input id="i_sy_syNotice_datagrid_edit_form_ceateuserid" type="text" reftype="-1"/></td><td class="label" align="center" style="display:none;">已阅用户</td><td align="center" style="display:none;"><input id="i_sy_syNotice_datagrid_edit_form_readusernames" type="text" reftype="-1"/></td></tr></tbody></table></form>
<script>var uedit = new UE.ui.Editor();uedit.render("i_sy_syNotice_datagrid_edit_form_thetext");top.formfieldmap.put("i_sy_syNotice_datagrid_edit_form_thetext",uedit);</script>
<!-- 请填入编辑表单html  end -->
<div class="titlep">附件</div>
<div id="i_sy_syNotice_datagrid_edit_form_file_form"></div>
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syNotice_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syNotice_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syNotice_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syNotice_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
