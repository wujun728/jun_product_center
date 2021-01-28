<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String hisId = request.getParameter("hisId");
	if (hisId == null) {
		hisId = "";
	}
	
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	String name = request.getParameter("name");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/app/emp-ill-his!update.sy';
		} else {
			url = sy.contextPath + '/app/emp-ill-his!save.sy';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
	$(function() {

		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/emp-ill-his!getById.sy', {
				EmpIllHisId : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.custUser.userId' : result.custUser.userId,
						'data.custUser.userName' : result.custUser.userName,
						'data.jwbsContent' : result.jwbsContent,
						'data.sysContent' : result.sysContent,
						'data.jzContent' : result.jzContent,
						'data.ext1' : result.ext1
					});
				 	
				}
				parent.$.messager.progress('close');
			}, 'json');
		}

	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>员工病史基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>员工病史编号</th>
					<td><input name="data.id" value="<%=hisId%>"
						readonly="readonly" /></td>
					<th>员工姓名</th>
					<td >
					<input name="data.custUser.userId" value="<%=id%>" hidden="true"
						class="easyui-validatebox" size="20" maxlength="20" width="300px"
						data-options="required:true" />
					<input name="data.custUser.userName" value="<%=name%>"
						class="easyui-validatebox" size="20" maxlength="20" width="300px"
						data-options="required:true" /></td>
						 <td></td>
				</tr>
 				
				<tr>
					<th>既往病史</th>
					<td colspan="3"><textarea name="data.jwbsContent" cols="100"></textarea></td>
				</tr>
				<tr>
					<th>生育史</th>
					<td colspan="3"><textarea name="data.sysContent" cols="100"></textarea></td>
				</tr>
				<tr>
					<th>家族史</th>
					<td colspan="3"><textarea name="data.jzContent" cols="100"></textarea></td>
				</tr>				
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>