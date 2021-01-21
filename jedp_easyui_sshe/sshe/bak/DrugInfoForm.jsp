<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
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
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.drugCode"]').val().length > 0) {
			url = sy.contextPath + '/app/drug-info!update.sy';
		} else {
			url = sy.contextPath + '/app/drug-info!save.sy';
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
		$('#customerIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-info!doNotNeedSessionAndSecurity_customerInfoIdComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'customerId',
			textField : 'customerName',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'customerId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'customerId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'customerName',
				title : '客户',
				width : 100,
				sortable : true
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'updateTime',
				title : '修改时间',
				width : 150,
				sortable : true
			} ] ]
		});
		
		if ($(':input[name="data.drugCode"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/drug-info!getById.sy', {
				DrugInfoId : $(':input[name="data.drugCode"]').val()
			}, function(result) {
				if (result.drugCode != undefined) {
					$('form').form('load', {
						'data.customerInfo.customerId' : result.customerInfo != undefined ? result.customerInfo.customerId : '',
						'data.drugCode' : result.drugCode,
						'data.drugName' : result.drugName,
						'data.drugDesc' : result.drugDesc,
						'data.produceComp' : result.produceComp,
						'data.expireTime' : result.expireTime,
						'data.produceTime' : result.produceTime,
						'data.effect' : result.effect,
						'data.brand' : result.brand,
						'data.specification' : result.specification,
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
				<tr>
					<th>客户</th>
					<td>
						<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 214px;">
					</td>
						<th></th>
					<td></td>
				</tr>
				<tr>
					<th>药品名称</th>
					<td colspan="3">
					<!-- 药品编号 --><input name="data.drugCode" value="<%=id%>"  hidden="hidden"  readonly="readonly" />
					<input name="data.drugName" class="easyui-validatebox"  size="20"  maxlength="20" width="300px" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>药品描述</th>
					<td colspan="3"><textarea name="data.drugDesc" cols="100"></textarea></td>
				</tr>
				<tr>
					<th>生产厂商</th>
					<td>
						<input name="data.produceComp" class="easyui-validatebox" data-options="required:false" />
					</td>
					<th>过期时间</th>
					<td>
						<input name="data.expireTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<th>生产时间</th>
					<td>
						<input name="data.produceTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" data-options="required:true"/>
					</td>
					<th>规格</th>
					<td><input name="data.specification" class="easyui-validatebox" data-options="required:true" /></td>
					
				</tr>
				<tr>
					<th><!-- 品牌 --></th>
					<td><input name="data.brand" class="easyui-validatebox" hidden="true" data-options="required:false" /></td>
					<th><!-- 药效 --></th>
					<td><input name="data.effect" class="easyui-validatebox" hidden="true"  data-options="required:false" /></td>
				</tr>
				<tr>
					<th>生产厂商联系地址</th>
					<td colspan="3"><input name="data.contact" class="easyui-validatebox" data-options="required:true"  maxlength="50" style="width: 320px;"/></td>
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