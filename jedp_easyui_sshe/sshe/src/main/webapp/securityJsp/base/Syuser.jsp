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
<%-- 引入javascript工具--%>

<script src="<%=contextPath%>/jslib/jquery-easyui-1.3.4/plugins/jquery.form.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加用户信息',
			url : sy.contextPath + '/securityJsp/base/SyuserForm.jsp',
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
			title : '查看用户信息',
			url : sy.contextPath + '/securityJsp/base/SyuserForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : sy.contextPath + '/securityJsp/base/SyuserForm.jsp?id=' + id,
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
				$.post(sy.contextPath + '/base/syuser!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var gotoChatFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要登陆到聊天服务器吗？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/base/syuser!gotoChatIM.sy', {
					id : id
				}, function(result) {
					if (result.success && result.obj != null) {
						// 取得要提交的参数
					    // 取得要提交页面的URL
					    var action = "http://localhost:8081/sshe_chat/adapter/login";
					    // 创建Form
					   // var form = $('<form></form>');
					    var form =  $('#showDataForm');
				        
					    // 设置属性
					    form.attr('action', action);
					    form.attr('method', 'post');
					    // form的target属性决定form在哪个页面提交
					    // _self -> 当前页面 _blank -> 新页面
					    form.attr('target', '_blank');
					    // 创建Input
					    var my_input_name =$(':input[name="name"]');
					    my_input_name.attr('value', result.obj.name);
					    // 附加到Form
					    //form.append(my_input_name); 
					    
					    var my_input_server =$(':input[name="server"]');
					    my_input_server.attr('value', result.obj.server);
					    // 附加到Form
					    //form.append(my_input_server); 
					    
					    var my_input_port = $(':input[name="port"]');
					    my_input_port.attr('value', result.obj.port);
					    // 附加到Form
					    //form.append(my_input_port); 
					    
					    var my_input_password = $(':input[name="password"]');
					    my_input_password.attr('value', result.obj.pass);
					    // 附加到Form
					    //form.append(my_input_password); 
					   // console.log(form)
					    // 提交表单
					   // form.submit(); 
					   //console.log(form)
					   form.submit(); 
					    
					    window.open("http://localhost:8081/sshe_chat");
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							 
						});
					}
				   }, 'json');
			}
		});
	};
	var grantRoleFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改角色',
			url : sy.contextPath + '/securityJsp/base/SyuserRoleGrant.jsp?id=' + id,
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
			url : sy.contextPath + '/securityJsp/base/SyuserOrganizationGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var grantCustomerFun =  function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改关联客户',
			url : sy.contextPath + '/securityJsp/base/SyuserCustomerGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	function saveReport() { 
		// jquery 表单提交 
		$("#showDataForm").submit();
		
		return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
		} 
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/base/syuser!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'createdatetime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : '登录名',
				field : 'loginname',
				sortable : true
			}, {
				width : '80',
				title : '姓名',
				field : 'name',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '150',
				title : '创建时间',
				field : 'createdatetime',
				sortable : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'updatedatetime',
				sortable : true
			}, {
				width : '50',
				title : '性别',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case '0':
						return '女';
					case '1':
						return '男';
					}
				}
			}, {
				width : '50',
				title : '年龄',
				field : 'age',
				hidden : true
			}, {
				width : '250',
				title : '照片',
				field : 'photo',
				formatter : function(value, row) {
					if(value){
						return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			},{
				width : '150',
				title : '物理IP',
				field : 'phyAddr',
				hidden : false
			},  {
				title : '操作',
				field : 'action',
				width : '400',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/base/syuser!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看用户资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/base/syuser!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑用户资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/base/syuser!grantRole")) {%>
						var bt = systool.createFuncButton('ext-icon-user','用户角色','给用户授予角色','grantRoleFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/base/syuser!grantOrganization")) {%>
						var bt = systool.createFuncButton('ext-icon-group','用户机构','给用户授予用户机构','grantOrganizationFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/base/syuser!grantCustomer")) {%>
					var bt = systool.createFuncButton('ext-icon-group','相关客户','给用户关联给客户，拥有该客户的权限','grantCustomerFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
				<%}%>	
					<%if (securityUtil.havePermission("/base/syuser!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除用户','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/base/syuser!gotoChatIM")) {%>
					var bt = systool.createFuncButton('ext-icon-note_delete','进入聊天','进入聊天','gotoChatFun(\'{0}\');');
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
								
								<td>登录名</td>
								<td><input name="QUERY_t#loginname_S_LK" style="width: 80px;" /></td>
								<td>姓名</td>
								<td><input name="QUERY_t#name_S_LK" style="width: 80px;" /></td>
								<td>性别</td>
								<td><select name="QUERY_t#sex_S_EQ" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">男</option>
										<option value="0">女</option></select></td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createdatetime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createdatetime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
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
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	 
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
	<div data-options="region:'center',fit:true,border:false" style="visibility: hidden">
		<form id="showDataForm" action="http://localhost:8080/sshe/desktop/" method="post"  >
			<input type="text" name="name" />
			<input type="text" name="server" />
			<input type="text" name="port" />
			<input type="text" name="password" />
		</form> 
	</div>
</body>
</html>