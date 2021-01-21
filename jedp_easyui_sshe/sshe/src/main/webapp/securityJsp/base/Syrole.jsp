<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>

<%-- 引入javascript--%>
<script src="<%=contextPath%>/jslib/module/sys/role.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	var grid;
	var userGrid;
	/* 添加角色函数 */
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加角色信息',
			url : sy.contextPath + '/securityJsp/base/SyroleForm.jsp',
			buttons : [ {
				text : '添加角色',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看角色信息',
			url : sy.contextPath + '/securityJsp/base/SyroleForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑角色信息',
			url : sy.contextPath + '/securityJsp/base/SyroleForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/syrole!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var grantFun = function(id) {
		var dialog = parent.sy
				.modalDialog({
					title : '角色授权',
					url : sy.contextPath
							+ '/securityJsp/base/SyroleGrant.jsp?id=' + id,
					buttons : [ {
						text : '授权',
						handler : function() {
							dialog.find('iframe').get(0).contentWindow
									.submitForm(dialog, grid, parent.$);
						}
					} ]
				});
	};
	
	 
	
	/* 设置角色和用户关联 */
	var setRoleUsersFun = function() {
		var roleId = 	sys.getSelectedId (grid);
		 
		if (roleId>0) {
			var ids = sys.getSelections(grid); 
			$.post(sy.contextPath + '/base/syrole!relationRoleUser.sy', {
				id : roleId,
				userids: ids.join(',')
			}, function(result) {
				if (result.success) {
					parent.$.messager.alert('提示', '关联成功！', 'info');
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
				}
				//parent.$.messager.alert('提示', '关联成功！', 'info');
			}, 'json');
		}
		 
	};
	
	var checkRoleUsersHasPriv = function() {
		//alert('checkRoleUsersHasPriv  ');
		<%if (securityUtil.havePermission("/base/syuser!getById")) {%>
			return true;
		<%}%>
		return false;
	};
	
	/* 角色数据 */
	$(function() {
		grid = $('#grid').datagrid(
				{
					title : '',
					url : sy.contextPath + '/base/syrole!grid.sy',
					striped : true,
					rownumbers : true,
					pagination : true,
					singleSelect : true,
					checkbox: true,
					idField : 'id',
					sortName : 'seq',
					sortOrder : 'asc',
					frozenColumns : [ [ 
					                    {
					                    	checkbox:true,
						width : '100',
						title : '选择',
						field : 'ID',
						sortable : true
					} 
					                    ,{
						width : '100',
						title : '角色名称',
						field : 'name',
						sortable : true
					} ] ],
					columns : [ [
							{
								width : '150',
								title : '创建时间',
								field : 'createdatetime',
								sortable : true
							},
							{
								width : '150',
								title : '修改时间',
								field : 'updatedatetime',
								sortable : true
							},
							{
								width : '300',
								title : '资源描述',
								field : 'description'
							},
							{
								width : '60',
								title : '排序',
								field : 'seq',
								hidden : true,
								sortable : true
							},
							{
								title : '操作',
								field : 'action',
								width : '250',
							
									formatter : function(value, row) {
																	var str = '';
								<%if (securityUtil.havePermission("/base/syrole!getById")) {%>
									var bt = systool.createFuncButton('ext-icon-note', '查看', '查看角色资料','showFun(\'{0}\');');
									str += sy.formatString(bt, row.id);
								<%}%>
								<%if (securityUtil.havePermission("/base/syrole!update")) {%>
									var bt = systool.createFuncButton('ext-icon-note_edit', '编辑','编辑角色资料', 'editFun(\'{0}\');');
									str += sy.formatString(bt, row.id);
								<%}%>
								<%if (securityUtil.havePermission("/base/syrole!grant")) {%>
									var bt = systool.createFuncButton('ext-icon-key', '授权', '角色授权','grantFun(\'{0}\');');
									str += sy.formatString(bt, row.id);
								<%}%>
								<%if (securityUtil.havePermission("/base/syrole!delete")) {%>
									var bt = systool.createFuncButton('ext-icon-note_delete', '删除','删除角色', 'removeFun(\'{0}\');');
									str += sy.formatString(bt, row.id);
								<%}%>
									return str;
								}
							} ] ],
					toolbar : '#toolbar',
					onBeforeLoad : function(param) {
						parent.$.messager.progress({
							text : '数据加载中....'
						});
					},
					onLoadSuccess : function(data) {
						$('.iconImg').attr('src', sy.pixel_0);
						parent.$.messager.progress('close');
					},
					onClickRow: function(rowIndex, rowData) {
						 //alert('rowIndex, rowData' + rowIndex + "   "+ rowData);
						 sys.getSelected($(this));
					}
					
				});
		
		
		 
		
		
		//~~~~~
	});
	
	
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<%
					if (securityUtil.havePermission("/base/syrole!save")) {
				%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'ext-icon-note_add',plain:true"
					onclick="addFun();">添加</a></td>
				<%
					}
				%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><input id="searchBox" class="easyui-searchbox"
					style="width: 150px"
					data-options="searcher:function(value,name){grid.datagrid('load',{'QUERY_t#name_S_LK':value});},prompt:'搜索角色名称'"></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'ext-icon-zoom_out',plain:true"
					onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空查询</a></td>
			</tr>
		</table>
	</div>
	
	<div id="usertoolbar" style="display: none;">
		<table>
			<tr>
				<%
					if (securityUtil.havePermission("/base/syrole!save")) {
				%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'ext-icon-note_add',plain:true"
					onclick="setRoleUsersFun();" title="将所选择的角色与以下的用户关联">关联</a></td>
				<%
					}
				%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><input id="user_searchBox" class="easyui-searchbox"
					style="width: 150px"
					data-options="searcher:function(value,name){userGrid.datagrid('load',{'QUERY_t#name_S_LK':value});},prompt:'搜索用户名称'"></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'ext-icon-zoom_out',plain:true"
					onclick="sys.searchbox()">清空查询</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<div id="main-layout" class="easyui-layout"
			style="height: 595px; margin-top: 1px">
			<div
				data-options="region:'north',split:true ,fit:false,iconCls:'icon-ok'"
				title="角色数据" style="width: 300px;height: 300px">
				<table id="grid" data-options="fit:true,border:false" ></table>
			</div>
			<div data-options="region:'center',title:'用户数据',split:true ,fit:false,iconCls:'icon-ok' "
			 id="_content_00" style="width: 300px;height: 300px">
					<table id="userGrid" data-options="fit:true,border:false" ></table>
			</div>
		</div>
	</div>
	
	

<!-- 	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div> -->
	 
</body>
</html>