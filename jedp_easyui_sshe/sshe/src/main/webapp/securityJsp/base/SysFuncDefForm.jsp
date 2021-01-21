<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
/* =====================================
	系统功能模块-表单
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
var submitForm = function($dialog, $grid, $pjq, $mainMenu, $flag) {
	if ($('form').form('validate')) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			if($flag=='open'){
				url = sy.contextPath + '/base/syresource!updateOpenFlag.sy';
			}else if($flag =='close'){
				url = sy.contextPath + '/base/syresource!updateCloseFlag.sy';
			}
		} else {
			$pjq.messager.alert('提示', '找不到对应的数据 !!', 'error');
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			if (result.success) {
				$grid.treegrid('reload');
				$dialog.dialog('destroy');
				$mainMenu.tree('reload');
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
		$.post(sy.contextPath + '/base/syresource!getById.sy', {
			id : $(':input[name="data.id"]').val(),
		}, function(result) {
			if (result.id != undefined) {
				$('form').form('load', {
					'data.id' : result.id,
					'data.name' : result.name,
					'data.url' : result.url,
					'data.syresourcetype.id' : result.syresourcetype.id,
					'data.description' : result.description,
					'data.syresource.id' : result.syresource ? result.syresource.id : '',
					'data.iconCls' : result.iconCls,
					'data.seq' : result.seq,
					'data.target' : result.target,
					'data.isOpen' : result.isOpen ,
					'data.nodeType' : result.nodeType
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
			<legend>资源基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th>资源名称</th>
					<td><input name="data.name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>资源路径</th>
					<td><input name="data.url" /></td>
					<th>资源类型</th>
					<td><select name="data.syresourcetype.id" class="easyui-combobox" data-options="required:true,editable:false,valueField:'id',textField:'name',url:'<%=contextPath%>/base/syresourcetype!doNotNeedSecurity_combobox.sy',panelHeight:'auto'" style="width: 155px;"></select></td>
				</tr>
				<tr>
					<th>上级资源</th>
					<td><select id="syresource_id" name="data.syresource.id" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/syresource!doNotNeedSecurity_getMainMenu.sy'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#syresource_id').combotree('clear');" title="清空" /></td>
					<th>资源图标</th>
					<td><input id="iconCls" name="data.iconCls" readonly="readonly" style="padding-left: 18px; width: 134px;" /><img class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img class="iconImg ext-icon-cross" onclick="$('#iconCls').val('');$('#iconCls').attr('class','');" title="清空" /></td>
				</tr>
				<tr>
					<th>顺序</th>
					<td><input name="data.seq" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 155px;" value="100" /></td>
					<th>目标</th>
					<td><input name="data.target" /></td>
				</tr>
				<tr>
					<th>资源描述</th>
					<td><textarea name="data.description"></textarea></td>
					<th><input id="iconCls"  hidden="true" name="data.nodeType" readonly="readonly" style="padding-left: 18px; width: 134px;" /></th>
					<td>
					<select class="easyui-combobox" name="data.isOpen" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">关闭</option>
							<option value="0">开启</option>
					</select>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>