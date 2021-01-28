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
	var workTypeCombox;
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.hisId"]').val().length > 0) {
			url = sy.contextPath + '/app/emp-his!update.sy';
		} else {
			url = sy.contextPath + '/app/emp-his!save.sy';
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
		 workTypeCombox = $('#workTypeCombox').combobox({
				url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=WORK_TYPE',
		        idField:'id',
		        valueField:'id',
		        textField:'text',
		        parentField:'pid'
	        });
		
		if ($(':input[name="data.hisId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/emp-his!getById.sy', {
				EmpHisId : $(':input[name="data.hisId"]').val()
			}, function(result) {
				if (result.hisId != undefined) {
					$('form').form('load', {
						'data.hisId' : result.hisId,
						'data.custUser.userId' : result.custUser.userId,
						'data.custUser.userName' : result.custUser.userName,
						'data.workComp' : result.workComp,
						'data.startTime' : result.startTime,
						'data.endTime' : result.endTime,
						'data.workShop' : result.workShop,
						'data.harmful' : result.harmful,
						'data.protect' : result.protect,
						'data.workType' : result.workType,
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
			<legend>员工职业史基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>员工职业史编号</th>
					<td><input name="data.hisId"  value="<%=hisId%>"
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
					<th>工作单位</th>
					<td>
						<input name="data.workComp" class="easyui-validatebox" size="20" maxlength="20" width="300px"
						data-options="required:true" />
					</td>
					<th></th>
					<td ></td>
				</tr>
				<tr>
					<th>开始时间</th>
					<td>
						<input name="data.startTime" class="Wdate" data-options="required:true" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
					<th>结束时间</th>
					<td >
					<input name="data.endTime" class="Wdate" data-options="required:true" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				<tr>
					<th>车间</th>
					<td>
						<input name="data.workShop"  class="easyui-validatebox" size="20" maxlength="20" width="300px"
						data-options="required:true" />
					</td>
					<th>工种</th>
					<td >
						<select id="workTypeCombox"   name="data.workType" class="easyui-combotree"  style="width: 105px; display: none"></select><img class="iconImg ext-icon-cross" onclick="$('#workTypeCombox').combotree('clear');" title="清空" />
					</td>
				</tr>
				<tr>
					<th>有害因素</th>
					<td>
						<input name="data.harmful"  class="easyui-validatebox" size="20" maxlength="20" width="300px"
						data-options="required:true" />
					</td>
					<th>防护措施</th>
					<td >
						<input name="data.protect"   class="easyui-validatebox" size="20" maxlength="20" width="300px"
						data-options="required:true" />
					</td>
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