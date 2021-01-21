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
	
	var illTypeCombox;
	
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加流行病信息',
			url : sy.contextPath + '/securityJsp/app/EmpEpidemicRecordForm.jsp',
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
			title : '查看流行病信息',
			url : sy.contextPath + '/securityJsp/app/EmpEpidemicRecordForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑流行病信息',
			url : sy.contextPath + '/securityJsp/app/EmpEpidemicRecordForm.jsp?id=' + id,
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
				$.post(sy.contextPath + '/app/emp-epidemic-record!delete.sy', {
					EmpEpidemicRecordId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var getIllTypeById = function(id) {
		var data = $("#illTypeCombox").combobox('getData');
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
	
	$(function() {
		
	 illTypeCombox = $('#illTypeCombox').combobox(
		{
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=LIUXINGBING_TYPE',
		    idField:'id',
		    valueField:'id',
		    textField:'text',
		    parentField:'pid'
	    });
		
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/emp-epidemic-record!grid.sy',
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
				title : '流行病编号',
				field : 'id',
				sortable : true
			}, {
				width : '100',
				title : '姓名',
				field : 'custUser',
				sortable : true,
				formatter : function(value, row, index) {
					if(value != undefined){
						return value.userName;
					}
					return '';
				}
			}  ] ],
			columns : [ [  {
				width : '80',
				title : '流行病类别',
				field : 'illType',
				sortable : true, 
				formatter : function(value, row, index) {
					return getIllTypeById(value);
				}
			}, {
				width : '150',
				title : '疑似',
				field : 'suspected',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
						case 1:
							return '疑似';					
						case 2:
							return '确诊';
						}
					return "";
				}
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
					<%-- <%if (securityUtil.havePermission("/app/emp-epidemic-record!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看流行病资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-epidemic-record!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑流行病资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-epidemic-record!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除流行病','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					<%}%> --%>
					
					var bt = systool.createFuncButton('ext-icon-note','查看','查看流行病资料','showFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
					  bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑流行病资料','editFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
					  bt = systool.createFuncButton('ext-icon-note_delete','删除','删除流行病','removeFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
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
								<td>流行病名称</td>
								<td><select id="illTypeCombox" name="QUERY_t#illType_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#illTypeCombox').combotree('clear');" title="清空" /></td>
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