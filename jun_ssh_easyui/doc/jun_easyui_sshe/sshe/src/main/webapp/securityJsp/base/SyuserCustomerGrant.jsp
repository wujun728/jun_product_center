<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
 	/**
 		关联用户的界面 一个用户可以关联多个客户
 	*/
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		var nodes = $('#tree').tree('getChecked', [ 'checked', 'indeterminate' ]);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
			ids.push(nodes[i].id);
			//console.log(nodes[i].id)
		}
		 
		$.post(sy.contextPath + '/base/syuser!grantCustomer.sy', {
			id : $(':input[name="data.id"]').val(),
			ids : ids.join(',')
		}, function(result) {
			if (result.success) {
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
			$pjq.messager.alert('提示', '修改成功！', 'info');
		}, 'json');
	};
	$(function() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$('#tree').tree({
			url : sy.contextPath + '/app/cust-info!doNotNeedSecurity_getCustomersTree.sy',
			parentField : 'pid',
			checkbox : true,
			formatter : function(node) {
				//console.log(node)
				return node.text;
			},
			onLoadSuccess : function(node, data) {
				$.post(sy.contextPath + '/app/cust-info!doNotNeedSecurity_getCustomerByUserId.sy', {
					id : $(':input[name="data.id"]').val()
				}, function(result) {
					 
					if (result) {
						for (var i = 0; i < result.length; i++) {
							console.log(result[i])
							var node = $('#tree').tree('find', result[i].customerId);
							//console.log(node)
							if (node) {
								//console.log(node)
								var isLeaf = $('#tree').tree('isLeaf', node.target);
								if (isLeaf) {
									$('#tree').tree('check', node.target);
								}
							}
						}
					}
					parent.$.messager.progress('close');
				}, 'json');
			}
		});
	});
</script>
</head>
<body>
	<input name="data.id" value="<%=id%>" readonly="readonly" type="hidden" />
	<fieldset>
		<legend>所属角色</legend>
		<ul id="tree"></ul>
	</fieldset>
</body>
</html>