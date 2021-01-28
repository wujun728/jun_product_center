<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
	String userId = request.getParameter("userId");
	String userName = request.getParameter("userName");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.defId"]').val().length > 0) {
			url = sy.contextPath + '/app/self-medical-def!update.sy';
		} else {
			url = sy.contextPath + '/app/self-medical-def!save.sy';
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

		if ($(':input[name="data.defId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/self-medical-def!getById.sy', {
				id : $(':input[name="data.defId"]').val()
			}, function(result) {
				if (result.defId != undefined) {
					$('form').form('load', {
						'data.defId' : result.defId,
						'data.defName' : result.defName,
						'data.defType' : result.defType,
						'data.defDesc' : result.defDesc,
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
			<legend>加班基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>加班编号</th>
					<td><input name="data.defId" value="<%=id%>"
						readonly="readonly" /></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th>加班名称</th>
					<td colspan="3"><input name="data.defName"
						class="easyui-validatebox" size="20" maxlength="20" width="300px"
						data-options="required:true" /></td>
				</tr>
				<tr>
					<th>描述</th>
					<td colspan="3"><textarea name="data.defDesc" cols="100"></textarea></td>
				</tr>
				<tr>
					<th>类型</th>
					<td><select class="easyui-combobox"
						name="data.defType"
						data-options="panelHeight:'auto',editable:false"
						style="width: 155px;">
							<option value="10">体检结果定义</option>
							<option value="11">加班建议</option>
					</select></td>
					<td></td>
					<td></td>
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