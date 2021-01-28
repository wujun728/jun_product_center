<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
/* =====================================
	系统功能模块首页
	@author : zhouxj
	@Date: 2013-12-18
===================================== */
%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var grid;
var addFun = function() {
	var dialog = parent.sy.modalDialog({
		title : '添加资源信息',
		url : sy.contextPath + '/securityJsp/base/SyresourceForm.jsp',
		buttons : [ {
			text : '添加',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
			}
		} ]
	});
};
var showFun = function(id) {
	var dialog = parent.sy.modalDialog({
		title : '查看资源信息',
		url : sy.contextPath + '/securityJsp/base/SysFuncDefForm.jsp?id=' + id
	});
};
var openFun = function(id) {
	var dialog = parent.sy.modalDialog({
		title : '打开资源信息',
		url : sy.contextPath + '/securityJsp/base/SysFuncDefForm.jsp?id=' + id,
		buttons : [ {
			text : '打开',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu,'open');
			}
		} ]
	});
};
var closeFun = function(id) {
	var dialog = parent.sy.modalDialog({
		title : '关闭功能',
		url : sy.contextPath + '/securityJsp/base/SysFuncDefForm.jsp?id=' + id,
		buttons : [ {
			text : '关闭',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu,'close');
			}
		} ]
	});
};
var redoFun = function() {
	var node = grid.treegrid('getSelected');
	if (node) {
		grid.treegrid('expandAll', node.id);
	} else {
		grid.treegrid('expandAll');
	}
};
var undoFun = function() {
	var node = grid.treegrid('getSelected');
	if (node) {
		grid.treegrid('collapseAll', node.id);
	} else {
		grid.treegrid('collapseAll');
	}
};
var reloadData = function() {
	if(grid == null){
		return;
	}
	grid.treegrid('load',sy.serializeObject($('#searchForm')));
}
$(function() {
	grid = $('#grid').treegrid({
		title : '',
		url : sy.contextPath + '/base/syresource!treeGridMenu.sy',
		idField : 'id',
		treeField : 'name',
		parentField : 'pid',
		rownumbers : true,
		pagination : false,
		sortName : 'seq',
		sortOrder : 'asc',
		frozenColumns : [ [ {
			width : '200',
			title : '资源名称',
			field : 'name'
		} ] ],
		columns : [ [ {
			width : '200',
			title : '图标名称',
			field : 'iconCls'
		}, {
			width : '200',
			title : '资源路径',
			field : 'url',
			formatter : function(value, row) {
				if(value){
					return sy.formatString('<span title="{0}">{1}</span>', value, value);
				}
			}
		}, {
			width : '60',
			title : '资源类型',
			field : 'syresourcetype',
			formatter : function(value, row) {
				return value.name;
			}
		}, {
			width : '150',
			title : '创建时间',
			field : 'createdatetime',
			hidden : true
		}, {
			width : '150',
			title : '修改时间',
			field : 'updatedatetime',
			hidden : true
		}, {
			width : '200',
			title : '资源描述',
			field : 'description',
			formatter : function(value, row) {
				if(value){
					return sy.formatString('<span title="{0}">{1}</span>', value, value);
				}
			}
		}, {
			width : '80',
			title : '排序',
			field : 'seq',
			hidden : true
		}, {
			width : '80',
			title : '目标',
			field : 'target'
		},  {
			width : '80',
			title : '是否开启',
			field : 'isOpen',
			formatter : function(value, row) {
				switch (value) {
				case 0:
					return '开启';
				case 1:
					return '关闭';
				}
				//alert(value)
				//return '未知';
			}
		},{
			title : '操作',
			field : 'action',
			width : '180',
			formatter : function(value, row) {
				var str = '';
				<%if (securityUtil.havePermission("/base/syresource!getById")) {%>
					var bt = systool.createFuncButton('ext-icon-note','查看','查看菜单资料','showFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
				<%}%>
				<%if (securityUtil.havePermission("/base/syresource!updateOpenFlag")) {%>
					var bt = systool.createFuncButton('ext-icon-note_edit','开启','开启菜单','openFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
				<%}%>
				<%if (securityUtil.havePermission("/base/syresource!updateOpenFlag")) {%>
					var bt = systool.createFuncButton('ext-icon-note_delete','关闭','关闭菜单','closeFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
				<%}%>
				return str;
			}
		} ] ],
		toolbar : '#toolbar',
		onBeforeLoad : function(row, param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
		},
		onLoadSuccess : function(row, data) {
			$('.iconImg').attr('src', sy.pixel_0);
			parent.$.messager.progress('close');
		}
	});
});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>菜单名称</td>
								<td><input name="QUERY_t#name_S_LK" style="width: 80px;" /></td>
							 
								<td>创建时间</td>
								<td><input name="QUERY_t#createdatetime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createdatetime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="reloadData()">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<%-- <table>
						<tr>
							<%if (securityUtil.havePermission("/base/syuser!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
						</tr>
					</table> --%>
				</td>
			</tr>
		</table>
	</div>
	
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>