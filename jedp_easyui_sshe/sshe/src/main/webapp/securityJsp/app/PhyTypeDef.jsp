<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	/* 系统体格类型表*/
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
			title : '添加体格类型数据',
			url : sy.contextPath + '/securityJsp/app/PhyTypeDefForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$, parent.mainMenu,'add');
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看体格类型信息',
			url : sy.contextPath + '/securityJsp/app/PhyTypeDefForm.jsp?id='
					+ id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑体格类型信息',
			url : sy.contextPath + '/securityJsp/app/PhyTypeDefForm.jsp?id='
					+ id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$, parent.mainMenu,'update');
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/phy-def!delete.sy', {
					id : id
				}, function() {
					grid.treegrid('reload');
					parent.mainMenu.tree('reload');
				}, 'json');
			}
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
	$(function() {
		grid = $('#grid').treegrid(
				{
					title : '',
					url : sy.contextPath + '/app/phy-def!treeGrid.sy',
					idField : 'phyId',
					treeField : 'phyName',
					parentField : 'pid',
					rownumbers : true,
					pagination : false,
					sortName : 'phyId',
					sortOrder : 'asc',
					frozenColumns : [ [ {
						width : '100',
						title : '编码',
						field : 'phyId',
						hidden : true
					} ,
					{
						width : '200',
						title : '名称',
						field : 'phyName'
					}] ],
					columns : [ [
							
							{
								width : '100',
								title : '体格类型类型',
								field : 'phyType',
								formatter : function(value, row, index) {
									switch (value) {
									case 0:
										return '系统默认';
									case 1:
										return '用户添加';
									}
								}
							},
							{
								width : '100',
								title : '级别',
								field : 'level',
								hidden : false,
								formatter : function(value, row, index) {
									switch (value) {
									case 0:
										return '类型定义';
									default:
										return '子类型列举';
									}
								}
							},
							{
								/* 1 叶子， 0不是叶子 */
								width : '100',
								title : '是否叶子节点',
								field : 'isLeaf',
								hidden : false,
								formatter : function(value, row, index) {
									switch (value) {
									case 0:
										return '不是叶子';
									case 1:
										return '叶子';
									}
								}
							},
							{
								width : '150',
								title : '操作人',
								field : 'uid',
								hidden : true
							},
							{
								width : '150',
								title : '创建时间',
								field : 'createTime',
								hidden : false
							},
							{
								width : '150',
								title : '修改时间',
								field : 'updateTime',
								hidden : false
							},
							  
							 
							{
								title : '操作',
								field : 'action',
								width : '180',
								formatter : function(value, row) {
									var str = '';
<%if (securityUtil.havePermission("/base/sys-def!getById")) {%>
	var bt = systool.createFuncButton(
											'ext-icon-note', '查看', '查看菜单资料',
											'showFun(\'{0}\');');
									str += sy.formatString(bt, row.phyId);
<%}%>
	
<%if (securityUtil.havePermission("/base/sys-def!update")) {%>
	var bt = systool.createFuncButton(
											'ext-icon-note_edit', '编辑',
											'编辑菜单资料', 'editFun(\'{0}\');');
									str += sy.formatString(bt, row.phyId);
<%}%>
	
<%if (securityUtil.havePermission("/base/sys-def!delete")) {%>
	var bt = systool.createFuncButton(
											'ext-icon-note_delete', '删除',
											'删除菜单', 'removeFun(\'{0}\');');
									str += sy.formatString(bt, row.phyId);
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
				<%
					if (securityUtil.havePermission("/base/sys-def!save")) {
				%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'ext-icon-note_add',plain:true"
					onclick="addFun();">添加</a></td>
				<%
					}
				%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a
					onclick="undoFun();" href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');"
					href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>