<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
/* =====================================
	系统反馈首页
	@author : zhouxj
	@Date: 2013-12-18
===================================== */
%>
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
var phyKeshiCombox;

var addFun = function() {
	var dialog = parent.sy.modalDialog({
		title : '添加系统反馈信息',
		url : sy.contextPath + '/securityJsp/base/SysFeedbackDefForm.jsp',
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
		title : '查看系统反馈信息',
		url : sy.contextPath + '/securityJsp/base/SysFeedbackDefForm.jsp?id=' + id
	});
};
var editFun = function(id) {
	var dialog = parent.sy.modalDialog({
		title : '编辑系统反馈信息',
		url : sy.contextPath + '/securityJsp/base/SysFeedbackDefForm.jsp?id=' + id,
		buttons : [ {
			text : '编辑',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$ );
			}
		} ]
	});
};
var removeFun = function(id) {
	parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
		if (r) {
			$.post(sy.contextPath + '/base/sys-feedback!doNotNeedSecurity_delete.sy', {
				SysFeedbackId : id
			}, function() {
				grid.datagrid('reload');
			}, 'json');
		}
	});
};
var reloadData = function() {
	if(grid == null){
		return;
	}
	grid.datagrid('load',sy.serializeObject($('#searchForm')));
}

var getPhyKeshiComboxById = function(id) {
	var data = $("#phyKeshiCombox").combobox('getData');
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
	
	phyKeshiCombox = $('#phyKeshiCombox').combobox({
	    url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=SYS_MODULE_TYPE',
	    idField:'id',
	    valueField:'id',
	    textField:'text',
	    parentField:'pid'
    });
	
	
	grid = $('#grid').datagrid({
		title : '',
		url : sy.contextPath + '/base/sys-feedback!doNotNeedSecurity_grid.sy',
		idField : 'id',
		pageSize : 50,
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
		rownumbers : true,
		pagination : false,
		sortName : 'id',
		sortOrder : 'asc',
		frozenColumns : [ [ {
			width : '200',
			title : '系统反馈内容',
			field : 'feedbackContent'
		} ] ],
		columns : [ [  {
			width : '90',
			title : '系统反馈类型',
			field : 'type',
			formatter : function(value, row, index) {
				return getPhyKeshiComboxById(value);
			}
		}, {
			width : '150',
			title : '创建时间',
			field : 'createdatetime',
			hidden : false
		}, {
			width : '150',
			title : '修改时间',
			field : 'updatedatetime',
			hidden : false
		}, {
			width : '200',
			title : '系统反馈描述',
			field : 'ext1'
		}, {
			width : '100',
			title : '反馈者Email',
			field : 'feedbackEmail'
		},{
			width : '100',
			title : '反馈者',
			field : 'syuser',
			formatter : function(value, row, index) {
				 
				if(value != undefined ){
					return value.name;
				}
				return '';
			}
		},{
			width : '50',
			title : '状态',
			field : 'status',
			formatter : function(value, row, index) {
				switch (value) {
				<%-- 0 提交 1 已经解决 2 待解决 3 未知 99 删除 --%>
				case 0:
					return '提交';
				case 1:
					return '已经解决';
				case 2:
					return '待解决';
				case 3:
					return '未知';
				case 99:
					return '删除';
				default:
					return '未知';
				}
			}
		}, {
			title : '操作',
			field : 'action',
			width : '180',
			formatter : function(value, row) {
				var str = '';
				<%-- <%if (securityUtil.havePermission("/base/syresource!getById")) {%> --%>
					var bt = systool.createFuncButton('ext-icon-note','查看','查看菜单资料','showFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
				<%-- <%}%> --%>
				<%-- <%if (securityUtil.havePermission("/base/sys-feedback!doNotNeedSecurity_update")) {%> --%>
					var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑','editFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
				<%-- <%}%> --%>
				<%-- <%if (securityUtil.havePermission("/base/sys-feedback!doNotNeedSecurity_delete")) {%> --%>
					var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除','closeFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
				<%-- <%}%> --%>
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
								<td>反馈内容</td>
								<td><input name="QUERY_t#feedbackContent_S_LK" style="width: 80px;" /></td>
							 	<th>类型</th>
								<td>
									<select id="phyKeshiCombox" name="QUERY_t#type_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#phyKeshiCombox').combotree('clear');" title="清空" />
								</td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createdatetime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createdatetime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="reloadData()">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
				 <table>
						<tr>
							<%-- <%if (securityUtil.havePermission("/base/sys-feedback!doNotNeedSecurity_save")) {%> --%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<%-- <%}%> --%>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="reloadData()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
							<!-- <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td> -->
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