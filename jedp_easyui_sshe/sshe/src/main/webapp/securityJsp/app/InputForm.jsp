<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* //自定义入库
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
	 
	var submitForm = function($dialog, $grid, $pjq) {
		var sel = grid.datagrid('getSelected');
		//console.log(sel)
		if(sel != undefined){
			$grid.val(sel.defDesc);
			$dialog.dialog('destroy');
		}
	};
	var listType = function() {
		grid = $('#grid').datagrid(
				{
					title : '',
					url : sy.contextPath + '/app/self-medical-def!grid.sy',
					striped : true,
					rownumbers : true,
					pagination : true,
					singleSelect : true,
					idField : 'defId',
					sortName : 'createTime',
					sortOrder : 'desc',
					pageSize : 50,
					pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
					frozenColumns : [ [ {
						width : '50',
						title : '自定义编号',
						field : 'defId',
						sortable : true
					}, {
						width : '100',
						title : '自定义名称',
						field : 'defName',
						sortable : true
					} ] ],
					columns : [ [
							{
								width : '150',
								title : '自定义描述',
								field : 'defDesc',
								sortable : true
							} 
							  ] ],
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
					onDblClickRow : function(rowIndex, rowData){
						console.log(rowData)
						console.log(parent.$)
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
			style="height: 400px">
				<table id="grid" data-options="fit:true,border:false"></table>
		</div>
</body>
</html>