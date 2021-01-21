<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	//身份识别
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
	
	var gotoHisDiagnose = function(userId) {
		var url = sy.contextPath + '/securityJsp/app/EmpDiagnoseList.jsp?userId='+userId;
		window.location.href=url;
	}
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加员工信息',
			url : sy.contextPath + '/securityJsp/app/custUserForm.jsp',
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
			title : '查看员工信息',
			url : sy.contextPath + '/securityJsp/app/custUserForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑员工信息',
			url : sy.contextPath + '/securityJsp/app/custUserForm.jsp?id=' + id,
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
				$.post(sy.contextPath + '/app/cust-user!delete.sy', {
					customerInfoId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	var diagnoseFun = function(id,name) {
		parent.$.messager.confirm('询问', '您确定要就诊吗？' + name, function(r) {
			if (r) {
				var url = sy.contextPath + '/securityJsp/app/EmpDiagnose.jsp?userId=' + id+"&userName=" +name;
				window.location.href=url;
			/* 	$.post(sy.contextPath + '/app/cust-user!delete.sy', {
					customerInfoId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json'); */
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
		 
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/cust-user!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'userId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '员工编号',
				field : 'userId',
				sortable : true
			}, {
				width : '100',
				title : '员工名称',
				field : 'userName',
				sortable : true
			}  ] ],
			columns : [ [ {
				width : '80',
				title : '创建时间',
				field : 'createTime',
				sortable : true
			}, {
				width : '80',
				title : '修改时间',
				field : 'updateTime',
				sortable : true,
				hidden :true
			}, {
				width : '100',
				title : '客户',
				field : 'customerInfo',	
				sortable : true,
				formatter : function(value, row, index) {
					// alert(value.deptName + '  ' + row +'  ' + index)
					 return value.customerName ;
				}
			},  {
				width : '100',
				title : '部门',
				field : 'custDept',
				sortable : true,
				formatter : function(value, row, index) {
					// alert(value.deptName + '  ' + row +'  ' + index)
					 return value.deptName ;
				}
			}, {
				width : '50',
				title : 'email',
				field : 'email',
				sortable : true 
			}, {
				width : '50',
				title : '员工状态',
				field : 'status',
				hidden : false,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '在职';
					case 2:
						return '离职';
					case 3:
						return '待业';
					}
				}
			}, {
				width : '150',
				title : '员工地址',
				field : 'address' 
			}, {
				width : '100',
				title : '电话',
				field : 'phone'
			}, {
				title : '操作',
				field : 'action',
				width : '350',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/cust-user!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看员工资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-user!get")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','基础疾病','查看员工基础疾病','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-diagnose-record!grid")) {%>
						var bt = systool.createFuncButton('ext-icon-user','历史就诊','查看员工历史就诊信息','gotoHisDiagnose(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%-- <%if (securityUtil.havePermission("/app/cust-user!update")) {%>
						var bt = systool.createFuncButton('ext-icon-group','体检信息','查看员工的历史就诊信息','grantOrganizationFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%> --%>
				 	
					<%if (securityUtil.havePermission("/app/cust-user!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-group','进入就诊','进入就诊','diagnoseFun(\'{0}\',\'{1}\');');
						str +=  sy.formatString(bt,row.userId,row.userName);
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
		
		$('#deptIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'deptId',
			textField : 'deptName',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'deptId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'deptId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'deptName',
				title : '客户',
				width : 100,
				sortable : true
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'updateTime',
				title : '修改时间',
				width : 150,
				sortable : true
			} ] ]
		});
		
		var customerIdCombogrid= $('#customerIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-info!doNotNeedSessionAndSecurity_customerInfoIdComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'customerId',
			textField : 'customerName',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'customerId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'customerId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'customerName',
				title : '客户',
				width : 100,
				sortable : true
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'updateTime',
				title : '修改时间',
				width : 150,
				sortable : true
			} ] ],
			onSelect:function(record){
				//console.log(record);
				//刷新数据，重新读取省份下的城市，并清空当前输入的值  
				//alert(record);
				var g = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId);
		        $('#deptIdCombogrid').combogrid({
		            disabled:false,  
		            url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId='+r.customerId,
		            valueField:'deptId',  
		            textField:'deptName'  
		        }).combogrid('clear');
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
					 <th>客户</th>
					<td>
						<input id="customerIdCombogrid" name="QUERY_t#customerInfo#customerId_I_EQ" type="text" value="" style="width: 214px;">
					</td>
					<th>部门</th>
					<td>
						<input id="deptIdCombogrid" name="QUERY_t#custDept#deptId_I_EQ" type="text" value="" style="width: 214px;">
					</td>
					
				 </tr>
				
				
							<tr>
								<td>员工名称</td>
								<td><input name="QUERY_t#userName_S_LK" style="width: 180px;" /></td>
								<td>员工地址</td>
								<td><input name="QUERY_t#address_S_LK" style="width: 180px;" /></td>
								<td>员工状态</td>
								<td><select name="QUERY_t#status_B_EQ" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">在职</option>
										<option value="2">离职</option>
										<option value="3">待业</option>
										</select></td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<%-- <tr>
				<td>
					<table>
						<tr>
							<%if (securityUtil.havePermission("/base/syuser!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
						</tr>
					</table>
				</td>
			</tr> --%>
		</table>
	</div>
	 
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>