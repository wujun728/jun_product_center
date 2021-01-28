<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.*"%>
<%
	String contextPath = request.getContextPath();
%>
<% 
	
// 规格 
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	System.out.println(id);
	String drugCode = request.getParameter("drugCode");
	if (drugCode == null) {
		drugCode = "";
	}
	String drugName =  request.getParameter("drugName") ;
	
	// 用默认字符编码解码字符串。
 	byte[] bs = drugName.getBytes();
	// 用新的字符编码生成字符串
	drugName = new String(bs, "UTF-8");
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.specId"]').val().length > 0) {
			url = sy.contextPath + '/app/drug-spec-info!update.sy';
		} else {
			url = sy.contextPath + '/app/drug-spec-info!save.sy';
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
		 
		if ($(':input[name="data.specId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/drug-spec-info!getById.sy', {
				DrugSpecInfoId : $(':input[name="data.specId"]').val()
			}, function(result) {
				if (result.specId != undefined) {
					$('form').form('load', {
						'data.specId' : result.specId,
						'data.specification' : result.specification,
						'data.unit' : result.unit,
						'data.drugInfo.drugCode' : result.drugInfo.drugCode,
						'data.drugInfo.drugName' : result.drugInfo.drugName,
						'data.contact' : result.contact,
						'data.ext1' : result.ext1
					});
					if (result.customerLogo) {
						$('#customerLogo').attr('src', sy.contextPath + result.customerLogo);
					}
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
			<legend>药品基本信息</legend>
			<table class="table" style="width: 100%;">
				<!-- <tr>
					<th>客户</th>
					<td>
						<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 214px;">
					</td>
						<th></th>
					<td></td>
				</tr> -->
				<tr>
					<th></th>
					<td colspan="3">
					<!-- 药品编号 --><input name="data.specId" value="<%=id%>"  readonly="readonly" />
				</tr>
				<tr>
					<th>药品名称</th>
					<td colspan="3">
					<!-- 药品编号 --><input name="data.drugInfo.drugCode" value="<%=drugCode%>"  hidden="hidden"   readonly="readonly" />
					<input name="data.drugInfo.drugName" disabled="disabled" class="easyui-validatebox" value="<%=drugName%>" size="20"  readonly="readonly" maxlength="20" width="300px" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>规格</th>
					<td><input name="data.specification" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>单位</th>
					<td><input name="data.unit" class="easyui-validatebox" data-options="required:true" /></td>
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