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
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/app/emp-epidemic-record!update.sy';
		} else {
			url = sy.contextPath + '/app/emp-epidemic-record!save.sy';
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

		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/emp-epidemic-record!getById.sy', {
				EmpEpidemicRecordId : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.custUser.userId' : result.custUser.userId,
						'data.illType' : result.illType,
						'data.startTime' : result.startTime,
						'data.endTime' : result.endTime,
						'data.confirmTime' : result.confirmTime,
						'data.backTime' : result.backTime,
						'data.ext1' : result.ext1,
						'data.suspected' : result.suspected,
						'data.customerInfo.customerId' : result.custUser.customerInfo.customerId,
						'data.custDept.deptId' : result.custUser.custDept.deptId
					});
				 	
				}
				parent.$.messager.progress('close');
			}, 'json');
		}

		

		var customerIdCombogrid = $('#customerIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-info!doNotNeedSessionAndSecurity_customerInfoIdComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'customerId',
			textField : 'customerName',
			pagination : true,
			fitColumns : true,
			required : false,
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
			} ] ],
			onSelect:function(record){
				//console.log(record);
				//刷新数据，重新读取省份下的城市，并清空当前输入的值  
				//alert(record);
				var g = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId);
		        $('#deptIdCombogrid').combogrid({
		            disabled:false,  
		            url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId='+r.customerId,
		            valueField:'deptId',  
		            textField:'deptName'  
		        }).combogrid('clear');
			}
			
		});
		 
		var deptIdCombogrid = $('#deptIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId=' +customerIdCombogrid.val(),
			panelWidth : 500,
			panelHeight : 200,
			idField : 'deptId',
			textField : 'deptName',
			pagination : true,
			fitColumns : true,
			required : false,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'deptId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'deptId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'deptName',
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
			} ] ],
			onSelect:function(record){
				//console.log(record);
				//刷新数据，重新读取省份下的城市，并清空当前输入的值  
				var deptIdCombogrid = $('#deptIdCombogrid').combogrid('grid');	// get datagrid object
				var deptIdCombogridrow = deptIdCombogrid.datagrid('getSelected');	// get the selected row
				
				var customerIdCombogrid = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var customerIdCombogridrow = customerIdCombogrid.datagrid('getSelected');	// get the selected row
				
				//alert(r.customerId);
		        $('#userIdCombogrid').combogrid({
		            disabled:false,  
		            url : sy.contextPath + '/app/cust-user!doNotNeedSessionAndSecurity_userIdCombogridById.sy?customerInfoId='+customerIdCombogridrow.customerId+'&deptId=' + deptIdCombogridrow.deptId,
		            valueField:'userId',  
		            textField:'userName'  
		        }).combogrid('clear');
			}
		});
		
		$('#userIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-user!doNotNeedSessionAndSecurity_userIdCombogridById.sy?customerInfoId='+customerIdCombogrid.val()+'&deptId=' + deptIdCombogrid.val(),
			panelWidth : 500,
			panelHeight : 200,
			idField : 'userId',
			textField : 'userName',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'userId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'userId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'userName',
				title : '用户',
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
			<legend>流行病基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>流行病编号</th>
					<td><input name="data.id" value="<%=id%>"
						readonly="readonly" /></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
				 <th>学校</th>
					<td>
						<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 130px;">
					</td>
					<th>年级班级</th>
					<td>
						<input id="deptIdCombogrid" name="data.custDept.deptId" type="text" value="" style="width: 130px;">
					</td>
				 </tr>
				<tr>
					<th>就诊员工</th>
					<td> 
					     <input id="userIdCombogrid" name="data.custUser.userId" data-options="required:true"  type="text" value="" style="width: 130px;">
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
				     <th>流行病类型</th>
					<td>
						<select id="data.illType" name="data.illType" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=LIUXINGBING_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.type').combotree('clear');" title="清空" />
					</td>	
					<th></th>
					<td></td>	 
				</tr>
				<tr>
					<th>开始时间</th>
					<td colspan="3"><input name="data.startTime" class="Wdate"  
						data-options="required:true"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly" style="width: 150px;" /></td>
				</tr>
				<tr>
					<th>结束时间</th>
					<td colspan="3"><input name="data.endTime" class="Wdate" 
						data-options="required:true"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly" style="width: 150px;" /></td>
				</tr>
				<tr>
					<th>是否疑似</th>
					<td><select class="easyui-combobox" name="data.suspected" 
						data-options="panelHeight:'auto',editable:false"   
						data-options="required:true" style="width: 155px;">
							<option value="1"   >疑似</option>
								<option value="2"   >确诊</option>
					</select></td>
					<th>确诊时间</th>
					<td colspan="3"><input name="data.confirmTime" class="Wdate"  
						data-options="required:true"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly" style="width: 150px;" /></td>
				</tr>
				<tr>
					<th>返回时间</th>
					<td colspan="3"><input name="data.backTime" class="Wdate" 
						data-options="required:true"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly" style="width: 150px;" /></td>
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