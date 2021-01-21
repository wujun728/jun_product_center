<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//学校的年级班级
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
		if ($(':input[name="data.deptId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/cust-dept!getById.sy', {
				id : $(':input[name="data.deptId"]').val()
			}, function(result) {
				//alert(result.iCONCLS)
				if (result.deptId != undefined) {
					$('form').form('load', {
						'data.deptId' : result.deptId,
						'data.customerInfo.customerId' : result.customerInfo.customerId,
						'data.deptName' : result.deptName,
						'data.deptAddr' : result.deptAddr,
						'data.custDept.deptId' : result.custDept  ? result.custDept.deptId : '',
						'data.iCONCLS' : result.iCONCLS,
						'data.seq' : result.seq,
						'data.deptCode' : result.deptCode
					});
					$('#iCONCLS').attr('class', result.iCONCLS);//设置背景图标
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		
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
				title : '年级班级',
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
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>年级班级基本信息</legend>
			<table class="table" style="width: 100%;">
			    <tr>
					<th>年级班级</th>
					<td>
					<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 214px;">
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>编号</th>
					<td><input name="data.deptId" value="<%=id%>" readonly="readonly" /></td>
					<th>年级班级名称</th>
					<td><input name="data.deptName" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>年级班级编码</th>
					<td><input name="data.deptCode" /></td>
					<th>顺序</th>
					<td><input name="data.seq" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 155px;" value="100" /></td>
				</tr>
				<tr>
					<th>上级年级班级</th>
					<td>
					<select id="custDept_deptId" name="data.custDept.deptId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/app/cust-dept!doNotNeedSecurity_comboTree.sy'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#custDept_deptId').combotree('clear');" title="清空" />
					</td>
					<th>年级班级图标</th>
					<td><input id="iCONCLS" name="data.iCONCLS"  readonly="readonly" style="padding-left: 18px; width: 134px;" /><img class="iconImg ext-icon-zoom" onclick="showIcons();" title="浏览图标" />&nbsp;<img class="iconImg ext-icon-cross" onclick="$('#iCONCLS').val('');$('#iCONCLS').attr('class','');" title="清空" /></td>
				</tr>
				<tr>
					<th>年级班级地址</th>
					<td><input name="data.deptAddr" /></td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>