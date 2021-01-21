<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>用户注册</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		var regFun = function() {
			if ($('form').form('validate')) {
				$('#regBtn').linkbutton('disable');
				$.post(sy.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_reg.sy', $('form').serialize(), function(result) {
					if (result.success) {
						location.replace(sy.contextPath + '/index.jsp');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('form :input:eq(1)').focus();
						});
						$('#regBtn').linkbutton('enable');
					}
				}, 'json');
			}
		};

		$('#regDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-user_add',
			buttons : [ {
				text : '登录',
				handler : function() {
					location.replace(sy.contextPath + '/login.jsp');
				}
			}, {
				id : 'regBtn',
				text : '注册',
				handler : function() {
					regFun();
				}
			} ],
			onOpen : function() {
				$('form :input:first').focus();
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						regFun();
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<div id="regDialog" title="用户注册" style="display: none;">
		<form method="post" class="form">
			<table class="table">
				<tr>
					<th width="60">登录名</th>
					<td><input name="data.loginname" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input id="pwd" name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>