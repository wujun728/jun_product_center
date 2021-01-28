<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加自定义信息',
			url : sy.contextPath + '/securityJsp/app/EmpJiabanRecordDetailForm.jsp',
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
			title : '查看自定义信息',
			url : sy.contextPath + '/securityJsp/app/EmpJiabanRecordDetailForm.jsp?id=' + id
		});
	};
	
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑自定义信息',
			url : sy.contextPath + '/securityJsp/app/EmpJiabanRecordDetailForm.jsp?id=' + id,
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
				$.post(sy.contextPath + '/app/emp-jiaban-detail!delete.sy', {
					selfMedicalDefId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	 
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/emp-jiaban-detail!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'defId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '自定义编号',
				field : 'defId',
				sortable : true
			}, {
				width : '100',
				title : '自定义名称',
				field : 'defName',
				sortable : true
			}  ] ],
			columns : [ [  {
				width : '50',
				title : '自定义类别',
				field : 'defType',
				sortable : true, 
				formatter : function(value, row, index) {
					switch (value) {
					case 10:
						return '体检结果定义';
					case 11:
						return '自定义建议';
					}
				}
			}, {
				width : '150',
				title : '自定义描述',
				field : 'defDesc',
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
					<%if (securityUtil.havePermission("/app/emp-jiaban-detail!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看自定义资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.defId);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-jiaban-detail!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑自定义资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.defId);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-jiaban-detail!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除自定义','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.defId);
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
								<td>自定义名称</td>
								<td><input name="QUERY_t#defName_S_LK" style="width: 180px;" /></td>
								<td>自定义描述</td>
								<td><input name="QUERY_t#defDesc_S_LK" style="width: 180px;" /></td>
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
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
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