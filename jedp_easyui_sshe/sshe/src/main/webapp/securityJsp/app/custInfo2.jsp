<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<% 

	/**
	 *  学校信息管理
	 */
	
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	
	String custType = request.getParameter("custType");
	
	if(custType == null || (custType != null && custType.isEmpty())){
		custType = "0";
	}
	
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
			title : '添加学校信息',
			url : sy.contextPath + '/securityJsp/app/custInfoForm2.jsp',
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
			title : '查看学校信息',
			url : sy.contextPath + '/securityJsp/app/custInfoForm2.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑学校信息',
			url : sy.contextPath + '/securityJsp/app/custInfoForm2.jsp?id=' + id,
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
				$.post(sy.contextPath + '/app/cust-info!delete.sy', {
					customerInfoId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var grantRoleFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改角色',
			url : sy.contextPath + '/securityJsp/app/SyuserRoleGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var grantOrganizationFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改机构',
			url : sy.contextPath + '/securityJsp/app/SyuserOrganizationGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		var custType = <%=custType%>;
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/cust-info!grid.sy?custType='+custType,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'customerId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '学校编号',
				field : 'customerId',
				sortable : true
			}, {
				width : '100',
				title : '学校名称',
				field : 'customerName',
				sortable : true
			}  ] ],
			columns : [ [ {
				width : '120',
				title : '创建时间',
				field : 'createTime',
				sortable : true
			}, {
				width : '120',
				title : '修改时间',
				field : 'updateTime',
				sortable : true
			}, {
				width : '50',
				title : '学校类别',
				field : 'customerType',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '企业';
					case 1:
						return '学校';
					}
				}
			}, {
				width : '50',
				title : '学校状态',
				field : 'customerStatus',
				hidden : false,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '新建';
					case 1:
						return '合作有效';
					case 2:
						return '合作无效';
					}
				}
			}, {
				width : '250',
				title : '学校地址',
				field : 'customerAddr' 
			}, {
				width : '100',
				title : '学校联系方式',
				field : 'customerContact'
			}, {
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/cust-info!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看学校资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.customerId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-info!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑学校资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.customerId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-info!grantRole")) {%>
						var bt = systool.createFuncButton('ext-icon-user','学校角色','给学校授予角色','grantRoleFun(\'{0}\');');
						str +=  sy.formatString(bt,row.customerId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-info!grantOrganization")) {%>
						var bt = systool.createFuncButton('ext-icon-group','学校机构','给学校授予学校机构','grantOrganizationFun(\'{0}\');');
						str +=  sy.formatString(bt,row.customerId);
					<%}%>						
					<%if (securityUtil.havePermission("/app/cust-info!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除学校','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.customerId);
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
								<td>学校名称</td>
								<td><input name="QUERY_t#customerName_S_LK" style="width: 180px;" /></td>
								<td>学校地址</td>
								<td><input name="QUERY_t#customerAddr_S_LK" style="width: 180px;" /></td>
								<td>学校状态</td>
								<td><select name="QUERY_t#customerStatus_B_EQ" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="0">新建</option>
										<option value="1">合作有效</option>
										<option value="2">合作无效</option>
										</select></td>
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
							<!-- <td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
							 -->
							 <td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
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