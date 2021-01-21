<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	String id = request.getParameter("id");
	String name = request.getParameter("name");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function(id,name) {
		var dialog = parent.sy.modalDialog({
			title : '添加员工病史信息',
			url : sy.contextPath + '/securityJsp/app/EmpIllHisForm.jsp?id=' + id + "&name=" +name,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看员工病史信息',
			url : sy.contextPath + '/securityJsp/app/EmpIllHisForm.jsp?hisId=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑员工病史信息',
			url : sy.contextPath + '/securityJsp/app/EmpIllHisForm.jsp?hisId=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？' +id, function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/emp-ill-his!delete.sy', {
					EmpIllHisId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/emp-ill-his!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '员工病史编号',
				field : 'id',
				sortable : true
			}, {
				width : '100',
				title : '员工姓名',
				field : 'custUser',
				sortable : true,
				formatter : function(value, row, index) {
					return row.custUser.userName;
				}
			}  ] ],
			columns : [ [  {
				width : '50',
				title : '既往病史',
				field : 'jwbsContent',
				sortable : true
			}, {
				width : '150',
				title : '生育史',
				field : 'sysContent',
				sortable : true
			},  {
				width : '150',
				title : '家族史',
				field : 'jzContent',
				sortable : true
			},  {
				width : '150',
				title : '创建时间',
				field : 'createTime',
				sortable : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'updateTime',
				sortable : true
			}, {
				title : '操作',
				field : 'action',
				width : '350',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/emp-ill-his!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看员工病史资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-ill-his!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑员工病史资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-ill-his!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除员工病史','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
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
								<td>员工姓名</td>
								<td><input name="QUERY_t#custUser#userName_S_LK" style="width: 180px;" /></td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%if (securityUtil.havePermission("/base/syuser!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun(<%=id%>,'<%=name%>');">添加</a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	 
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>