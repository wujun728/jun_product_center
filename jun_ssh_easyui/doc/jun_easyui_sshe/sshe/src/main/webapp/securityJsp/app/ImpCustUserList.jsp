<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	//病假管理
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
	var typeCombox;
	
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加病假信息',
			url : sy.contextPath + '/securityJsp/app/EmpSickLeaveRecordForm.jsp',
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
			title : '查看病假信息',
			url : sy.contextPath + '/securityJsp/app/EmpSickLeaveRecordForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑病假信息',
			url : sy.contextPath + '/securityJsp/app/EmpSickLeaveRecordForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/sick-user!delete.sy', {
					empSickLeaveRecordId : id
				}, function() {
					grid.treegrid('reload');
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
 	
		
		grid = $('#grid').datagrid({
			title : '历史导入数据',
			url : sy.contextPath + '/app/imp-cust-user-data!grid.sy',
			idField : 'id',
			rownumbers : true,
			pagination : false,
			sortName : 'createTime',
			sortOrder : 'asc',
			frozenColumns : [ [  {
				width : '50',
				title : '编号',
				field : 'id'
			} ] ],
			columns : [ [
			   {
				width : '100',
				title : '员工姓名',
				field : 'syuser',
				formatter : function(value, row) {
					var str = '';
					return value.name + "["+value.id+"]";
				}
			},
			{
				width : '120',
				title : '导入时间',
				field : 'impTime'
			},{
				width : '100',
				title : '类别',
				field : 'dataType',
				formatter : function(value, row) {
					 switch(value){
					 case 0: 
						 return '员工历史数据';
					 case 1:
						 return '员工历史数据';
					 }
					 return '';
				}
			}, 
			{
				width : '80',
				title : '数据条数',
				field : 'dataLine'
			}, {
				width : '200',
				title : '文件名称',
				field : 'srcFileName'
			},  {
				width : '200',
				title : '新文件名称',
				field : 'newFileName'
			}, {
				width : '150',
				title : '创建时间',
				field : 'createTime',
				hidden : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'updateTime',
				hidden : true
			}  ] ],
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
								<td>员工姓名</td>
								<td><input name="QUERY_t#syuser#name_S_LK" style="width: 180px;" /></td>
								<td>类别</td>
								<td>
								<select class="easyui-combobox"  name="QUERY_t#dataType_B_EQ"  data-options="panelHeight:'auto',editable:false" style="width: 155px;">
									<option value=0>员工导入数据</option>
									<option value=1>员工导入数据</option>
								</select>
								</td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				 
				<td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>