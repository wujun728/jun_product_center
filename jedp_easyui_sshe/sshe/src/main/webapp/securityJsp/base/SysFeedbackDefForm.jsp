<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/* =====================================
	系统系统反馈模块-表单
	@author : zhouxj
	@Date: 2013-12-18
===================================== */
%>

<%
String contextPath = request.getContextPath();
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
		if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/base/sys-feedback!doNotNeedSecurity_update.sy';
		} else {
				url = sy.contextPath + '/base/sys-feedback!doNotNeedSecurity_save.sy';
		}
		console.log(sy.serializeObject($('form')));
		 
		$.post(url, sy.serializeObject($('form')), function(result) {
			if (result.success) {
				$grid.datagrid('reload');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	}
};

var submitForm2 = function($dialog, $pjq) {
	if ($('form').form('validate')) {
		
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/base/sys-feedback!doNotNeedSecurity_update.sy';
		} else {
				url = sy.contextPath + '/base/sys-feedback!doNotNeedSecurity_save.sy';
		}
		//console.log(sy.serializeObject($('form')));
		 
		$.post(url, sy.serializeObject($('form')), function(result) {
			if (result.success) {
				$pjq.messager.alert('提示', '提交成功 !!!', 'info');
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
				dialog.find('iframe').get(0).contentWindow.selectIcon(dialog, $('#iconCls'));
			}
		} ]
	});
};
$(function() {
	if ($(':input[name="data.id"]').val().length > 0) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(sy.contextPath + '/base/sys-feedback!doNotNeedSecurity_getById.sy', {
			SysFeedbackId : $(':input[name="data.id"]').val(),
		}, function(result) {
			if (result.id != undefined) {
				$('form').form('load', {
					'data.id' : result.id,
					'data.feedbackContent' : result.feedbackContent,
					'data.ext1' : result.ext1,
					'data.status' : result.status ,
					'data.type' : result.type
				});
				$('#iconCls').attr('class', result.iconCls);//设置背景图标
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
			<legend>系统反馈基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>系统反馈内容</th>
					<td colspan="3">
						<textarea name="data.feedbackContent" cols="80" style="width: 350px;height: 200px" class="easyui-validatebox" data-options="required:true" ></textarea></td>
					</td>
				</tr>
				<tr>
					<th>系统反馈Email</th>
					<td><input name="data.feedbackEmail" /></td>
					<th>系统反馈类型</th>
					<td>
					<select id="data.type" name="data.type" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=SYS_MODULE_TYPE',required:true" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" />
					</td>
				</tr>
				<tr>
					<th>提交者</th>
					<td><input name="data.syuser.name" style="display: none" /></td>
					<th>状态</th>
					<%-- 0 提交 1 已经解决 2 待解决 3 未知 99 删除 --%>
					<td>
					<select name="data.status" class="easyui-combobox"   style="width: 155px;">
							<option value="0">提交</option>
							<option value="1">已经解决</option>
							<option value="2">待解决</option>
							<option value="3">未知</option>
							<option value="99">删除</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1"></textarea></td>
				 	
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>