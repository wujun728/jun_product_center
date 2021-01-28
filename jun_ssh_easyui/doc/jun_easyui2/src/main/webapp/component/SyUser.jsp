<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
var syUserId="i_sy_syUser_datagrid";
var syUserDt;
var linkp = '';
var organid = '';
var columns= [ [ {
	field : 'id',
	title : '编号',
	width : 150,
	checkbox : true,
	form:{
		hidden:true
	}
}, {
	field : 'username',
	title : '用户名',
	width : 150,
	form:{
		classname:'easyui-validatebox',
		dataoptions:"required:true",
		type:eType.ValidateBox
	}
}, {
	field : 'userid',
	title : '登录名',
	width : 150
}, {
	field : 'routename',
	title : '当前组织',
	width : 150,
	form:{
		classname:'easyui-combotree',
		dataoptions:"url:'"+'${ctx}'+actionUrl('/sys/','syOrgan','Get_Tree')+"'",
		type:eType.ComboTree
	}
},{
	field : 'routeid',
	title : '当前组织',
	width : 150,
	form:{
		hidden:true
	}
},{
	field : 'rolename',
	title : '用户角色',
	width : 150,
	form:{
		hidden:true
	}
}   ] ];
$(function(){
	linkp = (location.href.split('?')[1]).split('&');
	organid = linkp[0].split('=')[1];
	pageView(syUserId,columns);
});

//模式list （通过链接pageView=list访问）
function pageView_list(){
	var syUserDataGrid = {
			id:syUserId,
			url:"${ctx}"+actionUrl('/sys/','syUser','List')+"?syOrgan.organid="+organid,
			dId:'id',
			columns:columns
	};
	syUserDt=gGrid2(syUserDataGrid);	
}

function getSelUserInfo(){
	var rows=syUserDt.datagrid('getChecked');
	var userInfo = '';
	for ( var i = 0; i < rows.length; i++) {
		if((rows[i].id+'').substring(0,1)!='-'){
			userInfo+=trim(rows[i].username)+'<'+trim(rows[i].userid)+'></'+trim(rows[i].userid)+'>,';
		}
	}
	return userInfo;
}
</script>
</head>
<body>
<div id="i_sy_syUser_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syUser_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syUser_datagrid_searchbox"
					pdt="i_sy_syUser_datagrid"></input>
					<div id="i_sy_syUser_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syUser_datagrid"></table> 
</body>
</html>
