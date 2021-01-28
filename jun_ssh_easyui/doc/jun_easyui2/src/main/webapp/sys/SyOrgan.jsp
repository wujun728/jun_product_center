<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
var syOrganId="i_sy_syOrgan_datagrid";
var syOrganDt,UploadDg,UploadFm;
var columns= [ [ {
	field : 'id',
	title : '编号',
	width : 150,
	checkbox : true,
	form:{
		hidden:true
	}
}, {
	field : 'organname',
	title : '组织名称',
	width : 150
}, {
	field : 'organid',
	title : '组织id',
	hidden:true,
	width : 150,
	form:{
		hidden:true
	}
}, {
	field : 'routename',
	title : '从属组织',
	width : 150,
	form:{
		classname:'easyui-combotree',
		dataoptions:"url:'${ctx}"+actionUrl('/sys/','syOrgan','Get_Tree')+"'",
		type:eType.ComboTree
	},
	formatter: function(value,row,index){
		value=value.substring(0,value.lastIndexOf('/'));
		row['routename']=value;
		return value;
	}
},{
	field : 'routeid',
	title : '从属组织id',
	width : 150,
	hidden:true,
	form:{
		hidden:true
	}
}  ] ];
	var organid,routeid;
$(function(){
	$('#'+syOrganId+'_add_dialog').dialog({
		onOpen:function(){
			reshOrganData('add');
			eSet('#i_sy_syOrgan_datagrid_add_form_routename',routeid);
		}
	});
	
	$('#'+syOrganId+'_edit_dialog').dialog({
		onOpen:function(){
			reshOrganData('edit');
		}
	});
	var urls=window.location.href.split('?');
	organid = $.getUrlParam('id');
	routeid= $.getUrlParam('routeid');
	pageView(syOrganId,columns);
});

function updateFun(d){
	window.parent.window.sy_og_tree.tree('reload');
}

//模式add （通过链接pageView=add访问）
function pageView_add(){
	$('#'+syOrganId+'_add_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syOrganId+'_add_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syOrganId+'_add_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syOrgan'+"."+field]=eGet('#'+syOrganId+'_add_form_'+field);
		}
		data(getUrl('syOrgan','Add'),setData,'json',updateFun);
	});
	$('#'+syOrganId+'_add_dialog').dialog('open');
}

//模式edit （通过链接pageView=edit访问）
function pageView_edit(){
	data_={id:$.getUrlParam('id')};
	$.ajax({
		url : getUrl('syOrgan','Get_ById'),
		data : data_,
		dataType : 'json',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			setForm(r,syOrganId);
			if(r){if('info' in r){log(r.info);}};
		}
	});
	$('#'+syOrganId+'_edit_dialog').dialog({
		noheader:true,
		fit:true,
		border:false,
		title:null,
		modal: true  
	});
	$('#'+syOrganId+'_edit_btn a').off().click(function(){
		var setData={};
		for(var i=0; i<columns[0].length; i++){
			var field=columns[0][i].field;
			var title=columns[0][i].title;
			if(!checkFormField('#'+syOrganId+'_edit_form_'+field)){log('['+title+']不能为空，请填写!'); return;}
			setData['syOrgan'+"."+field]=eGet('#'+syOrganId+'_edit_form_'+field);
		}
		data(getUrl('syOrgan','Update'),setData,'json',updateFun);
	});
	$('#'+syOrganId+'_edit_dialog').dialog('open');
}

//模式list （通过链接pageView=list访问）
function pageView_list(){
	var syOrganDataGrid = {
			id:syOrganId,
			url:"${ctx}"+actionUrl('/sys/','syOrgan','List')+"?syOrgan.organid="+organid,
			dId:'id',
			columns:columns
	};
	
	UploadDg = $('#i_sy_syOrgan_datagrid_upload_dialog');
	UploadFm =$('#i_sy_syOrgan_datagrid_upload_dialog_form');
	UploadFm.attr('action',"${ctx}"+actionUrl('/sys/','syOrgan','Upload'));
	
	syOrganDt=gGrid2(syOrganDataGrid);	
	var straddfun="dorow(syOrganId,'','${ctx}"+actionUrl('/sys/','syOrgan','Add')+"',updateFun,'c')";
	gDataGridToolbarBtn(syOrganId,'icon-add',straddfun,"新增组织");
	var stredit="dorow(syOrganId,'','${ctx}"+actionUrl('/sys/','syOrgan','Update')+"',updateFun,'u')";
	gDataGridToolbarBtn(syOrganId,'icon-edit',stredit,"修改组织");
	var strdelfun="dorow(syOrganId,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syOrgan','Delete')+"',updateFun,'d')";
	gDataGridToolbarBtn(syOrganId,'icon-remove',strdelfun,"删除");
	var strexcelfun="dorow(syOrganId,'您确定要导出数据','${ctx}'+actionUrl('/sys/','syOrgan','Excel')+'?syOrgan.organid='+organid,'','e')";
	gDataGridToolbarBtn(syOrganId,'icon-page_white_excel',strexcelfun,"导出");
	gDataGridToolbarBtn(syOrganId,'icon-page_white_excel','upLoadFun()',"导入");
}

function upLoadFun(){
	UploadDg.dialog('open');
}

function submitUploadForm(){
	UploadFm.form('submit',{
		success:function(d){
			syOrganDt.datagrid('reload');
			UploadDg.dialog('close');
			d=$.parseJSON(d);
			log(d.info);
			}
	});
}

function reshOrganData(eora){
	$('#i_sy_syOrgan_datagrid_'+eora+'_form_routename').combotree('reload','${ctx}/sys/syOrgan_Get_Tree');
}
</script>
</head>
<body class="easyui-layout" >
<div id="i_sy_syOrgan_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syOrgan_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syOrgan_datagrid_searchbox"
					pdt="i_sy_syOrgan_datagrid"></input>
					<div id="i_sy_syOrgan_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syOrgan_datagrid"></table> 
 
 <div id="i_sy_syOrgan_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syOrgan_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syOrgan_datagrid_add_form"><table class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" align="center">组织名称</td><td align="center"><input id="i_sy_syOrgan_datagrid_add_form_organname" reftype="-1" type="text"/></td></tr><tr><td class="label" align="center">从属组织</td><td align="center"><input id="i_sy_syOrgan_datagrid_add_form_routename" class="easyui-combotree" data-options="url:'${ctx}/sys/syOrgan_Get_Tree'" reftype="3" type="text"/></td></tr><tr><td class="label" style="display:none;" align="center">编号</td><td style="display:none;" align="center"><input id="i_sy_syOrgan_datagrid_add_form_id" reftype="-1" type="text"/></td></tr><tr><td class="label" style="display:none;" align="center">组织id</td><td style="display:none;" align="center"><input id="i_sy_syOrgan_datagrid_add_form_organid" reftype="-1" type="text"/></td></tr><tr><td class="label" style="display:none;" align="center">从属组织id</td><td style="display:none;" align="center"><input id="i_sy_syOrgan_datagrid_add_form_routeid" reftype="-1" type="text"/></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syOrgan_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syOrgan_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">编辑</div>
<!-- 请填入编辑表单html  start -->
<form id="i_sy_syOrgan_datagrid_edit_form"><table class="formtable" border="1" width="99%"><tbody><tr class="firstRow"><td class="label" align="center">组织名称</td><td align="center"><input id="i_sy_syOrgan_datagrid_edit_form_organname" reftype="-1" type="text"/></td></tr><tr><td class="label" align="center">从属组织</td><td align="center"><input id="i_sy_syOrgan_datagrid_edit_form_routename" class="easyui-combotree" data-options="url:'${ctx}/sys/syOrgan_Get_Tree'" reftype="3" type="text"/></td></tr><tr><td class="label" style="display:none;" align="center">编号</td><td style="display:none;" align="center"><input id="i_sy_syOrgan_datagrid_edit_form_id" reftype="-1" type="text"/></td></tr><tr><td class="label" style="display:none;" align="center">组织id</td><td style="display:none;" align="center"><input id="i_sy_syOrgan_datagrid_edit_form_organid" reftype="-1" type="text"/></td></tr><tr><td class="label" style="display:none;" align="center">从属组织id</td><td style="display:none;" align="center"><input id="i_sy_syOrgan_datagrid_edit_form_routeid" reftype="-1" type="text"/></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syOrgan_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syOrgan_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
<!-- 按钮 end -->

<div id="i_sy_syOrgan_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syOrgan_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"><a href="${ctx}/excelmodel/组织表.xls" target="_blank">导入模版下载</a></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  

</body>
</html>
