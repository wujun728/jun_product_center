<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	String isGenerate = request.getParameter("isGenerate");
	
	String pid = request.getParameter("pid");
	if (pid == null) {
		pid = "undefined";
	}
	String psysCode = request.getParameter("psysCode");
	if (psysCode == null) {
		psysCode = "undefined";
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
				  if ($(':input[name="data.sysCode"]').val().length > 0) {
						url = sy.contextPath + '/base/sys-def!update.sy';
					} else   
					{
						url = sy.contextPath + '/base/sys-def!save.sy';
					}
			}else if($op='add'){
				  if ($(':input[name="data.sysCode"]').val().length > 0) {
						url = sy.contextPath + '/base/sys-def!save.sy';
					} else   
					{
						$pjq.messager.alert('提示', '编码不能空！！', 'error');
						
						return;
					}
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
		<%if(isGenerate!=null && isGenerate.equalsIgnoreCase("true")){%>
			
		<%}else{%>
		
		 if ($(':input[name="data.sysCode"]').val().length > 0) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
				$.post(sy.contextPath + '/base/sys-def!getById.sy', {
					id : $(':input[name="data.sysCode"]').val(),
				}, function(result) {
					  //alert(result.sysDef.sysCode)
					if (result.sysCode != undefined) {
						$('form').form('load', {
							'data.sysCode' : result.sysCode,
							'data.sysName' : result.sysName,
							'data.sysDef.sysCode' : result.sysDef ? result.sysDef.sysCode : '',
							'data.ext1' : result.ext1,
							'data.level' : result.level,
							'data.type' : result.type,
							'data.isLeaf' : result.isLeaf
						});	
					}
					parent.$.messager.progress('close');
				}, 'json');
			}   
		 <%}%>
		  var pid = <%=pid%>;
		  
		  if(pid != null && pid != undefined){
			  $(':input[name="data.sysDef.sysCode"]').val(pid);
		  }
		  var psysCode = <%=psysCode%>;
		  if(psysCode != null && psysCode != undefined){
			  $(':input[name="data.sysCode"]').val(psysCode);
		  }
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>字典基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编码</th>
					<td><input name="data.sysCode" value="<%=id%>"  data-options="required:true" /></td>
					<th>名称</th>
					<td><input name="data.sysName" class="easyui-validatebox" data-options="required:true" /></td>
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
					<td><select name="data.type" class="easyui-combobox"   style="width: 155px;">
							<option value="0">系统默认</option>
							<option value="1">用户添加</option>
						</select></td>
				</tr>
				<tr>
					<th>上级字典</th>
					<td><select id="data_sysDef_sysCode"  name="data.sysDef.sysCode" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getMainMenu.sy'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data_sysDef_sysCode').combotree('clear');" title="清空" /></td>
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
	<div style="text-align: left; padding: 5px">
		<input id="iconCls" class="ext-icon-bell" readonly="readonly"
			style="padding-left: 18px; width: 13px; border: none" />
			自动生成规则：<br/> 添加大类: 增加一个大类的，自动生成编号，<br/> 添加小类: 自动生成该类型下面的小类，编码自动 +1
		<br/>添加小类的时候， 上级类型如果被改动，则有可能增加到其他的类型下面去了，操作的时候请注意。默认是当前大类下面增加子类型。
		<br />  
	</div>
</body>
</html>