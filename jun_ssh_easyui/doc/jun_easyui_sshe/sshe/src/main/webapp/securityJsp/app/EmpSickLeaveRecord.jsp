<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	//病假管理
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
	var hospitalCombox;
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
	
	var getHospitalComboxById = function(id) {
		var data = $("#hospitalCombox").combobox('getData');
		var name = '';
		var count = data.length;
		//alert('count:'+ count)
		for ( var i = 0; i < count; i++) {
			//alert(data[i].text)
			if (data[i].id == id) {
				name = data[i].text;
				break;
			}
		}
		return name;
	}
	
	var getTypeComboxById = function(id) {
		var data = $("#typeCombox").combobox('getData');
		var name = '';
		var count = data.length;
		//alert('count:'+ count)
		for ( var i = 0; i < count; i++) {
			//alert(data[i].text)
			if (data[i].id == id) {
				name = data[i].text;
				break;
			}
		}
		return name;
	}
	
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
		hospitalCombox = $('#hospitalCombox').combobox({
					url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=HOSPITAL',
				    idField:'id',
				    valueField:'id',
				    textField:'text',
				    parentField:'pid'
			    });
		
		typeCombox = $('#typeCombox').combobox({
			url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=SICK_LEAVE_TYPE',
		    idField:'id',
		    valueField:'id',
		    textField:'text',
		    parentField:'pid'
	    });
		
		var custType = <%=custType%>;
		
		grid = $('#grid').datagrid({
			title : '病假数据',
			url : sy.contextPath + '/app/sick-user!grid.sy?custType='+custType,
			idField : 'id',
			rownumbers : true,
			pagination : false,
			sortName : 'createTime',
			sortOrder : 'asc',
			frozenColumns : [ [  {
				width : '50',
				title : '病假ID',
				field : 'id'
			} ] ],
			columns : [ [
			   {
				width : '100',
				title : '员工姓名',
				field : 'custUser',
				formatter : function(value, row) {
					var str = '';
					return value.userName;
				}
			},
			{
				width : '70',
				title : '日期',
				field : 'sickYm'
			},{
				width : '70',
				title : '类别',
				field : 'type',
				formatter : function(value, row) {
					return getTypeComboxById(value);
				}
			}, 
			{
				width : '120',
				title : '医院',
				field : 'hospital',
				formatter : function(value, row, index) {
					//alert(value);
					return getHospitalComboxById(value);
				}
			}, {
				width : '200',
				title : '病因',
				field : 'reason'
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
			}, {
				title : '操作',
				field : 'action',
				width : '250',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/sick-user!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看病假资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/app/sick-user!update")) {%>
					var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑病假资料','editFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);	
					<%}%>
					 
					<%if (securityUtil.havePermission("/app/sick-user!delete")) {%>
					var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除病假','removeFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);		
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
								<td>员工姓名</td>
								<td><input name="QUERY_t#custUser#userName_S_LK" style="width: 180px;" /></td>
								<td>就诊医院</td>
								<td>
									<select id="hospitalCombox" name="QUERY_t#hospital_S_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#hospitalCombox').combotree('clear');" title="清空" />
								</td>
								<td>类别</td>
								<td>
									<select id="typeCombox" name="QUERY_t#type_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#typeCombox').combotree('clear');" title="清空" />
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
			<table>
				<%if (securityUtil.havePermission("/app/sick-user!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
				<td></td>
				</table>
			</tr>
		</table>
	</div>
	
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>