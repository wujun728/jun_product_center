<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	//考勤记录
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
	
	var requireFun = function(id,type,workTime,afterWorkTime) {
		
		var title = '申请加班信息';
		var url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?workTime='+workTime+"&afterWorkTime="+afterWorkTime;
		if(type == 1){
			title = '申请加班信息';
			url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?workTime='+workTime+"&afterWorkTime="+afterWorkTime;
		}else if(type == 2){
			title = '申请请假信息';
			url = sy.contextPath + '/securityJsp/app/EmpQingjiaRecordForm.jsp?workTime='+workTime+"&afterWorkTime="+afterWorkTime;
		}
		
		var dialog = parent.sy.modalDialog({
			title : title,
			url : url,
			buttons : [ {
				text : '申请加班',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '考勤数据',
			url : sy.contextPath + '/app/medical-attend!grid.sy?queryType=NODEPT',
			idField : 'id',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'userId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [[{
				width : '50',
				title : '编号',
				field : 'id'
			  },{
				width : '80',
				title : '用户',
				field : 'syuser',
				formatter : function(value, row) {
					return value.name;
				}
		    }]],
			columns : [ [
			    {
					width : '60',
					title : '时间',
					field : 'yyyymmdd'
				},
			 {
				width : '60',
				title : '是否加班',
				field : 'isLeave',
				formatter : function(value, row) {
					switch(value){
					case 1:
						return '加班';
					case 2:
						return '请假';
					}
					return '';
				}
			}, {
				width : '60',
				title : '时间',
				field : 'opTime',
				formatter : function(value, row) {
					return value + "(小时)";
				}
			},{
				width : '120',
				title : '上班时间',
				field : 'workTime'
			}, {
				width : '120',
				title : '下班时间',
				field : 'afterWorkTime'
			}, {
				width : '120',
				title : '创建时间',
				field : 'createTime',
				hidden : false
			}, {
				width : '120',
				title : '修改时间',
				field : 'fireTime',
				hidden : true
			}, {
				width : '60',
				title : '排序',
				field : 'seq',
				hidden : true
			}, {
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/emp-jiaban!update")) {%>
					var bt = systool.createFuncButton('ext-icon-note_edit','申请加班','申请加班','requireFun(\'{0}\',\'{1}\',\'{2}\',\'{3}\');');
					str +=  sy.formatString(bt,row.id,row.isLeave,row.workTime,row.afterWorkTime);
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
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<%-- <td>部门</td>
								<td><select id="syorganizationId" name="syorganizationId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'name',parentField:'pid',url:'<%=contextPath%>/base/syorganization!doNotNeedSecurity_comboTree.sy'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#syorganizationId').combotree('clear');" title="清空" /></td> --%>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
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