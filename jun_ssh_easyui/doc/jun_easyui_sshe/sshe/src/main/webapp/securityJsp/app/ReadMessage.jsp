<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* 读取消息
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	SecurityUtil securityUtil = new SecurityUtil(session);
	System.out.println(">>>>>>" + id);
	id = (id == null) ? "" : id; */
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;

	
	var submitForm = function($dialog, $pjq) {

		var rows = $('#grid').datagrid("getRows");
		console.log(rows);
		if (rows.length > 0) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			//alert(ids.join(','));
			var readIds = {
				'ids' : ids.join(',')
			};
			var url = sy.contextPath
					+ '/base/sys-message!doNotNeedSecurity_read.sy';
			$.post(url, readIds, function(result) {
				if (result.success) {
					parent.$.messager.alert('提示', result.msg, 'info');
					if (result.obj != null) {
						
					}
					$dialog.dialog('destroy');
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('fail');
					$dialog.dialog('destroy');
				}
			}, 'json');
		}else{
			$dialog.dialog('destroy');
		}
	};
	
	var listType = function() {
		grid = $('#grid').datagrid(
				{
					title : '',
					url : sy.contextPath
							+ '/base/sys-message!grid.sy',
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
						width : '50',
						title : '编号',
						field : 'id',
						sortable : true
					}, {
						width : '100',
						title : '发送者',
						field : 'messageFrom',
						sortable : true
					} ] ],
					columns : [ [ {
						width : '150',
						title : '接收者',
						field : 'messageTo',
						sortable : true
					}, {
						width : '350',
						title : '消息内容',
						field : 'messageBody',
						sortable : true
					}, {
						width : '150',
						title : '创建时间',
						field : 'createdatetime',
						sortable : true
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
					},
					onDblClickRow : function(rowIndex, rowData) {
						//console.log(rowData)
						//console.log(parent.$)
					}
				});
	};

	$(function() {
		listType();
		//~~~~~~~~~~~~~~~~~~~~~~~~~
	});
</script>



</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<div class="easyui-panel" title="">

		<div data-options="region:'center',fit:true,border:false"
			style="height: 500px">
			<table id="grid" data-options="fit:true,border:false"></table>
		</div>
		</div>
</body>
</html>