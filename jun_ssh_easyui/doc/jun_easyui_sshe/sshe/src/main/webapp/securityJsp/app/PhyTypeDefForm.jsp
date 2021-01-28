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
	var submitForm = function($dialog, $grid, $pjq, $mainMenu,$op) {
		if ($('form').form('validate')) {
			var url;
			if($op=='update'){
				  if ($(':input[name="data.phyId"]').val().length > 0) {
						url = sy.contextPath + '/app/phy-def!update.sy';
					} else   
					{
						url = sy.contextPath + '/app/phy-def!save.sy';
					}
			}else if($op='add'){
				 // if ($(':input[name="data.phyId"]').val().length > 0) 
				  {
						url = sy.contextPath + '/app/phy-def!save.sy';
					} 
				 /* else   
					{
						$pjq.messager.alert('提示', '编码不能空！！', 'error');
						
						return;
					} */
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
		  if ($(':input[name="data.phyId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/phy-def!getById.sy', {
				id : $(':input[name="data.phyId"]').val(),
			}, function(result) {
				  //alert(result.physicalTypeDef.phyId)
				if (result.phyId != undefined) {
					$('form').form('load', {
						'data.phyId' : result.phyId,
						'data.phyName' : result.phyName,
						'data.physicalTypeDef.phyId' : result.physicalTypeDef ? result.physicalTypeDef.phyId : '',
						'data.ext1' : result.ext1,
						'data.level' : result.level,
						'data.phyType' : result.phyType,
						'data.isLeaf' : result.isLeaf
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
			<legend>体格类型数据</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编码</th>
					<td><input name="data.phyId" value="<%=id%>"  readonly="readonly" /></td>
					<th>名称</th>
					<td><input name="data.phyName" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>级别</th>
					<td>
					<select name="data.level" class="easyui-combobox"   style="width: 155px;">
							<option value="0">类型定义</option>
							<option value="1">子类型列举</option>
						</select>
					</td>
					<th>字典类型</th>
					<td><select name="data.phyType" class="easyui-combobox"   style="width: 155px;">
							<option value="0">系统默认</option>
							<option value="1">用户添加</option>
						</select></td>
				</tr>
				<tr>
					<th>上级字典</th>
					<td><select id="data.physicalTypeDef.phyId" name="data.physicalTypeDef.phyId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/app/phy-def!doNotNeedSecurity_getMainMenu.sy'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.physicalTypeDef.phyId').combotree('clear');" title="清空" /></td>
					<th>是否叶子</th>
					<td><select name="data.isLeaf" class="easyui-combobox"   style="width: 155px;">
							<option value="0">不是叶子</option>
							<option value="1">叶子</option>
						</select></td>
				</tr>
				 
				<tr>
					<th>描述</th>
					<td><textarea name="data.ext1"></textarea></td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>