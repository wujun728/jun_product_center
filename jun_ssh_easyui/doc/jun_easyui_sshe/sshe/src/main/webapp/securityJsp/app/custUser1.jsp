<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
    //学生信息管理
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
			title : '添加学生信息',
			url : sy.contextPath + '/securityJsp/app/custUserForm1.jsp',
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
			title : '查看学生信息',
			url : sy.contextPath + '/securityJsp/app/custUserForm1.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑学生信息',
			url : sy.contextPath + '/securityJsp/app/custUserForm1.jsp?id=' + id,
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
	
	var gotoEmpHis = function(id,name) {
		var url = sy.contextPath + '/securityJsp/app/EmpHis.jsp?id='+id+"&name="+name;
		console.log(url)
		window.location.href=url;
	};
	
	var gotoEmpIllHis = function(id,name) {
		var url = sy.contextPath + '/securityJsp/app/EmpIllHis.jsp?id='+id+"&name="+name;
		console.log(url)
		window.location.href=url;
	};
	
	/* var grantOrganizationFun = function(id,name) {
		var dialog = parent.sy.modalDialog({
			title : '修改学生部门',
			url : sy.contextPath + '/securityJsp/app/SyuserOrganizationGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	}; */
	$(function() {
		 //学校
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/cust-user!grid.sy?custType=1',
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
				title : '学生编号',
				field : 'userId',
				sortable : true
			}, {
				width : '100',
				title : '学生名称',
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
				sortable : true
			}, {
				width : '100',
				title : '学校',
				field : 'customerInfo',	
				sortable : true,
				formatter : function(value, row, index) {
					// alert(value.deptName + '  ' + row +'  ' + index)
					 return value.customerName ;
				}
			},  {
				width : '100',
				title : '年级班级',
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
				title : '学生状态',
				field : 'status',
				hidden : false,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '在校';
					case 2:
						return '毕业';
					case 3:
						return '待业';
					}
				}
			}, {
				width : '250',
				title : '学生地址',
				field : 'address' 
			}, {
				width : '100',
				title : '电话',
				field : 'phone'
			}, {
				title : '操作',
				field : 'action',
				width : '400',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/cust-user!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看学生资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-user!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑学生资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%-- <%if (securityUtil.havePermission("/app/cust-user!grantCustDept")) {%>
						var bt = systool.createFuncButton('ext-icon-group','分配部门','给学生分配部门','grantOrganizationFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%> --%>						
					<%if (securityUtil.havePermission("/app/cust-user!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除学生','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-user!gotoEmpIllHis")) {%>
						var bt = systool.createFuncButton('ext-icon-user','学生病史','学生病史管理','gotoEmpIllHis(\'{0}\',\'{1}\');');
						str +=  sy.formatString(bt,row.userId,row.userName);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-user!gotoEmpHis")) {%>
						var bt = systool.createFuncButton('ext-icon-user','学生职业史','学生职业史管理','gotoEmpHis(\'{0}\',\'{1}\');');
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
	
	var gotoImpPage = function() {
		var url = sy.contextPath + '/securityJsp/app/ImpCustUserForm.jsp';
		window.location.href=url;
	};
	
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
								<td><input id="customerIdCombogrid"
									name="QUERY_t#customerInfo#customerId_I_EQ" type="text"
									value="" style="width: 214px;"></td>
								<th>部门</th>
								<td><input id="deptIdCombogrid"
									name="QUERY_t#custDept#deptId_I_EQ" type="text" value=""
									style="width: 214px;"></td>

							</tr>


							<tr>
								<td>学生名称</td>
								<td><input name="QUERY_t#userName_S_LK"
									style="width: 180px;" /></td>
								<td>学生地址</td>
								<td><input name="QUERY_t#address_S_LK"
									style="width: 180px;" /></td>
								<td>学生状态</td>
								<td><select name="QUERY_t#status_B_EQ"
									class="easyui-combobox"
									data-options="panelHeight:'auto',editable:false"><option
											value="">请选择</option>
										<option value="1">在校</option>
										<option value="2">毕业</option>
										<!-- <option value="3">待业</option> -->
								</select></td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									readonly="readonly" style="width: 120px;" />-<input
									name="QUERY_t#createTime_D_LE" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom',plain:true"
									onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%
								if (securityUtil.havePermission("/app/cust-user!save")) {
							%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="addFun();">添加</a></td>
							<%
								}
							%>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-table_add',plain:true"
								onclick="gotoImpPage();">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
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