<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>项目管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var dataGrid;
	var treeGrid;
	$(function() {

		treeGrid = $('#treeGrid').treegrid({
			//url : '${pageContext.request.contextPath}/telphoneController/ggtreeGrid',
			data : [ {
				"name" : "财务部",
				"telid" : "1"
			}, {
				"mid" : "1",
				"name" : "出纳部",
				"telid" : "2"
			}, {
				"mid" : "1",
				"name" : "会计",
				"telid" : "3"
			}, {
				"name" : "技术部",
				"telid" : "7"
			}, {
				"name" : "综合维护事业部",
				"telid" : "12"
			}, {
				"mid" : "12",
				"name" : "数据机房",
				"telid" : "14"
			}, {
				"mid" : "12",
				"name" : "交换机房",
				"telid" : "15"
			}, {
				"mid" : "12",
				"name" : "系统平台机房",
				"telid" : "16"
			} ],
			idField : 'telid',
			treeField : 'name',
			parentField : 'mid',
			fit : true,
			fitColumns : false,
			border : false,
			frozenColumns : [ [ {
				field : 'telid',
				title : '编号',
				width : 100
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '姓名',
				width : 100

			}, {
				field : 'mid',
				title : 'mid',
				width : 100
			}, {
				field : 'telphone',
				title : '座机',
				width : 80
			}, {
				field : 'mobile',
				title : '手机',
				width : 70

			}, {
				field : 'emali',
				title : '邮箱',
				width : 70

			}, {
				field : 'bz',
				title : '备注',
				width : 70

			}, {
				field : 'action',
				title : '操作',
				width : 150,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_edit.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前项目？', function(b) {
			if (b) {
				$.post('${pageContext.request.contextPath}/cunchuController/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
				}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 790,
			height : 350,
			href : '${pageContext.request.contextPath}/cunchuController/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 790,
			height : 350,
			href : '${pageContext.request.contextPath}/cunchuController/addPage?xmid=${xmid}',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px; overflow: hidden;">
			<form id="searchForm">
				<table width="573" style="display: none;">
					<tr>

						<td width="109" align="right">服务器名称：</td>
						<td width="464"><input name="name" placeholder="" class="span2" /></td>
					</tr>
					<tr>
						<td width="109" align="right">型号：</td>
						<td><input name="xinghao" placeholder="" class="span2" /></td>
					</tr>


				</table>
			</form>
		</div>

		<div data-options="region:'center',border:false">
			<table id="treeGrid" fit="true"></table>
		</div>

	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a> <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
</body>
</html>