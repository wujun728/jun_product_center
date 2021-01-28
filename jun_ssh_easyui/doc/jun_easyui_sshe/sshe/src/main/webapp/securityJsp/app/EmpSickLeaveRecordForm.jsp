<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//病假管理
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
	String templateId = request.getParameter("templateId");
	String templateName = request.getParameter("templateName");
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	//alert("<%=templateId%>");
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/app/sick-user!update.sy';
		} else {
			url = sy.contextPath + '/app/sick-user!save.sy';
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
			$.post(sy.contextPath + '/app/sick-user!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.custUser.userId' : result.custUser.userId,
						'data.sickYm' : result.sickYm,
						'data.type' : result.type,
						'data.sickStartDate' : result.sickStartDate,
						'data.sickEndDate' : result.sickEndDate,
						'data.reason' : result.reason,
						'data.hospital' : result.hospital,
						'data.total' : result.total,
						'data.number' : result.number,
						'data.unit' : result.unit,
						'data.ext1' : result.ext1
					});
					if (result.customerLogo) {
						$('#customerLogo').attr('src', sy.contextPath + result.customerLogo);
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		};
		
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
			<legend>病假数据</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>病假编号</th>
					<td><input name="data.id" value="<%=id%>"  hidden="true"  readonly="readonly" /></td>
					<th></th>
					<td>
					</td>
				</tr>
				<tr>
				 <th>客户</th>
					<td>
						<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 130px;">
					</td>
					<th>部门</th>
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
					<th>病假年月</th>
					<td>
						<input name="data.sickYm" class="easyui-datebox" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" data-options="required:true" style="width: 120px;" />
					</td>
					<th>病假类型</th>
					<td>
						<select id="data_type" name="data.type" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=SICK_LEAVE_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data_type').combotree('clear');" title="清空" />
					</td>
				</tr>
				<tr>
					<th>病假开始时间</th>
					<td>
						<input name="data.sickStartDate" class="easyui-datebox" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"   style="width: 120px;" data-options="required:true"/>
					</td>
					<th>病假结束时间</th>
					<td>
						<input name="data.sickEndDate" class="easyui-datebox" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 120px;" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>病因</th>
					<td colspan="3"><textarea name="data.reason" cols="100" style="width: 300px" maxlength="30" class="easyui-validatebox"  data-options="required:true" ></textarea></td>
				</tr>
				<tr>
					<th>就诊医院</th>
					<td>
					
						<select id="data_hospital" name="data.hospital" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=HOSPITAL',required:true" style="width: 135px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data_hospital').combotree('clear');" title="清空" />	
					</td>
					<th>汇总</th>
					<td><input name="data.total" class="easyui-validatebox"  maxlength="80"  data-options="required:true" /></td>
				</tr>
				<tr>
					<th>数量</th>
					<td>
						<input name="data.number" title="最大9999" class="easyui-numberbox" precision="0" max="9999" size="5" size="20"  maxlength="4" width="300px" data-options="required:true" />
					</td>
					<th>单位</th>
					<td>
						<select id="data_unit" name="data.unit" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=SICK_UNIT'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data_unit').combotree('clear');" title="清空" />	
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100" style="width: 300px"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>