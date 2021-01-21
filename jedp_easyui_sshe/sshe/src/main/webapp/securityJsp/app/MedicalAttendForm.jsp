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
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.deptId"]').val().length > 0) {
				url = sy.contextPath + '/app/cust-dept!update.sy';
			} else {
				url = sy.contextPath + '/app/cust-dept!save.sy';
			}
			 
			   $.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					$grid.treegrid('reload');
					$dialog.dialog('destroy');
				} else {
					$pjq.messager.alert('提示', result.msg, 'error');
				}
			}, 'json');    
		}
	};
	var showIcons = function() {
		var dialog = parent.sy.modalDialog({
			title : '浏览小图标',
			url : sy.contextPath + '/style/icons.jsp',
			buttons : [ {
				text : '确定',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.selectIcon(dialog, $('#iCONCLS'));
				}
			} ]
		});
	};
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/medical-attend!getById.sy', {
				empAttendRecordId : $(':input[name="data.id"]').val()
			}, function(result) {
				//alert(result.iCONCLS)
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.syuser.name' : result.syuser.name,
						'data.yyyymmdd' : result.yyyymmdd,
						'data.workTime' : result.workTime,
						'data.afterWorkTime' : result.afterWorkTime,
						'data.isLeave' : result.isLeave
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
			<legend>考勤基本信息</legend>
			<table class="table" style="width: 100%;">
			     
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th>考勤名称</th>
					<td><input name="data.syuser.name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>考勤日期</th>
					<td><input name="data.yyyymmdd" /></td>
					<th>上班时间</th>
					<td><input name="data.workTime"    style="width: 155px;" value="100" /></td>
				</tr>
				<tr>
					<th>下班时间</th>
					<td>
						<input  name="data.afterWorkTime"      " style="width: 155px;" /><img class="iconImg ext-icon-cross" onclick="$('#custDept_deptId').combotree('clear');" title="清空" />
					</td>
					<th>是否请假</th>
					<td>
						<select class="easyui-combobox" name="data.isLeave" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="0">没有请假</option>
							<option value="1">今天请假了</option>
						</select>
					</td>
				</tr>
				 
			</table>
		</fieldset>
	</form>
</body>
</html>