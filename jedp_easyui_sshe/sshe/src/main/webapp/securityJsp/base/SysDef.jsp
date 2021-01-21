<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	/* 系统字典表*/
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
	
	var generateNextSysCode = function() {
		var data = {};
	
		var t = $('#grid');
		var rowss = t.treegrid('getData');
		 
		data['total']=rowss.length;
		//console.log('generateNextSysCode=====')
		//console.log(rowss.length)
		
		var rows = [];
		$.each(rowss, function(i,val){      
			var o = {};
		     //console.log(val);
		     o.sysCode = val.sysCode;
		      
			 rows[i] = o;
		  });
		 data['rows']=rows;
		 
		 var rowsdata = [];
		 
		 for(var i = 0; i < data['total'];  i++ ){
			 var pre = data['rows'][i].sysCode + "";
			 var pre2 = pre.substring(0,5);
			 var next = Number(pre2) + 1;
			 //console.log(next)
			 rowsdata[i] = next;
		 }
		// console.log(rowsdata)
		 rowsdata = rowsdata.sort(function(a,b){
			 return (a<b)?1:-1;
		 });
		 //console.log(rowsdata[0])
		 
		return rowsdata[0];
	};
	
	var addFun = function() {
		//推荐生成一个 syscode
		var nextSysCode = generateNextSysCode();
		nextSysCode = nextSysCode + "100";
		//return;
		
		var dialog = parent.sy.modalDialog({
			title : '添加字典信息',
			url : sy.contextPath + '/securityJsp/base/SysDefForm.jsp?id='+nextSysCode+'&isGenerate=true',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$, parent.mainMenu,'add');
				}
			} ]
		});
	};
	var addSubFun = function(pid,psysCode)
	{
		psysCode = Number(psysCode) + 1;
		var dialog = parent.sy.modalDialog({
			title : '添加字典信息',
			url : sy.contextPath + '/securityJsp/base/SysDefForm.jsp?pid='+pid+'&psysCode='+psysCode,
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
			title : '查看字典信息',
			url : sy.contextPath + '/securityJsp/base/SysDefForm.jsp?id='
					+ id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑字典信息',
			url : sy.contextPath + '/securityJsp/base/SysDefForm.jsp?id='
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
				$.post(sy.contextPath + '/base/sys-def!delete.sy', {
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
					url : sy.contextPath + '/base/sys-def!treeGrid.sy',
					idField : 'sysCode',
					treeField : 'sysName',
					parentField : 'pid',
					rownumbers : true,
					pagination : false,
					sortName : 'sysCode',
					sortOrder : 'asc',
					frozenColumns : [ [ {
						width : '100',
						title : '编码',
						field : 'sysCode'
					} ] ],
					columns : [ [
							{
								width : '200',
								title : '名称',
								field : 'sysName'
							},
							{
								width : '200',
								title : '简称',
								field : 'sName'
							},
							
							{
								width : '100',
								title : '字典类型',
								field : 'type',
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
								field : 'createdatetime',
								hidden : false
							},
							{
								width : '150',
								title : '修改时间',
								field : 'updatedatetime',
								hidden : false
							},
							  
							 
							{
								title : '操作',
								field : 'action',
								width : '230',
								formatter : function(value, row) {
									var str = '';
								<%if (securityUtil.havePermission("/base/sys-def!getById")) {%>
									var bt = systool.createFuncButton(
																			'ext-icon-note', '查看', '查看菜单资料',
																			'showFun(\'{0}\');');
																	str += sy.formatString(bt, row.sysCode);
								<%}%>
									
								<%if (securityUtil.havePermission("/base/sys-def!update")) {%>
									var bt = systool.createFuncButton(
																			'ext-icon-note_edit', '编辑',
																			'编辑菜单资料', 'editFun(\'{0}\');');
																	str += sy.formatString(bt, row.sysCode);
								<%}%>
									
								<%if (securityUtil.havePermission("/base/sys-def!delete")) {%>
									var bt = systool.createFuncButton(
																			'ext-icon-note_delete', '删除',
																			'删除菜单', 'removeFun(\'{0}\');');
																	str += sy.formatString(bt, row.sysCode);
								<%}%>
								<%if (securityUtil.havePermission("/base/sys-def!save")) {%>
								var bt = systool.createFuncButton(
																		'ext-icon-note_add', '添加',
																		'添加下级定义', 'addSubFun(\'{0}\',\'{1}\');');
																str += sy.formatString(bt, row.sysCode,row.sysCode);
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
					onclick="addFun();">添加大类</a></td>
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