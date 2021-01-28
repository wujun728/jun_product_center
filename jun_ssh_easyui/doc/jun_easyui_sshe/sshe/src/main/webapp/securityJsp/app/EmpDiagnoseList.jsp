<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	//历史就诊记录
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	String userId = (String)request.getParameter("userId");
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	/* var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加部门信息',
			url : sy.contextPath + '/securityJsp/app/custDeptForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	}; */
	var gotoDiagnose = function(id) {
		var url = sy.contextPath + '/securityJsp/app/EmpDiagnose.jsp?id='+id;
		window.location.href=url;
	};
	 
	$(function() {
		grid = $('#grid').datagrid({
			title : '历史就诊数据',
			url : sy.contextPath + '/app/emp-diagnose-record!grid.sy?userId='+<%=userId%>,
			idField : 'medicalId',
			rownumbers : true,
			pagination : true,
			sortName : 'medicalId',
			sortOrder : 'asc',
			frozenColumns : [[{
				width : '100',
				title : '编号',
				field : 'medicalId'
			  },{
				width : '100',
				title : '用户',
				field : 'custUser',
				formatter : function(value, row) {
					return value.userName;
				}
		    }]],
			columns : [ [
			    {
					width : '100',
					title : '时间',
					field : 'diagTime'
				},
				{
					width : '100',
					title : 'SGI编号',
					field : 'sgiNo',
					hidden : true
				},
			   {
				width : '150',
				title : '创建时间',
				field : 'createTime',
				hidden : false
			}, {
				width : '150',
				title : '修改时间',
				field : 'fireTime',
				hidden : true
			}, {
				title : '操作',
				field : 'action',
				width : '150',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/emp-diagnose-record!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note','就诊','就诊','gotoDiagnose(\'{0}\');');
						str +=  sy.formatString(bt,row.medicalId);
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
	
	//员工职业史，后退返回到员工管理列表
	function returnUrl(){
		window.location.href=document.referrer;
	}
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
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="returnUrl();">返回</a>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<%-- <%if (securityUtil.havePermission("/app/cust-dept!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%> --%>
				<td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	 
	
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>