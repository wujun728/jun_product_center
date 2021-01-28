<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	//加班申请
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	String id = request.getParameter("id");
	String opType = request.getParameter("opType");
	//System.out.print("opType:" + opType);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function(opType) {
		var title = <%=opType%> == 1 ? "添加加班信息" : "添加请假信息" ; 
		var url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?opType='+opType;
		if(<%=opType%> == 2){
			url = sy.contextPath + '/securityJsp/app/EmpQingjiaRecordForm.jsp?opType='+opType;
		}
		
		var dialog = parent.sy.modalDialog({
			title : title,
			url : url,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id,type) {
		var title = '查看加班信息';
		var url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		if(type == 1){
			title = '查看加班信息';
			url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		}else if(type == 2){
			title = '查看请假信息';
			url = sy.contextPath + '/securityJsp/app/EmpQingjiaRecordForm.jsp?id=' + id;
		}
		 
		var dialog = parent.sy.modalDialog({
			title : title,
			url : url,
			
		});
	};
	var submitFun = function(id,type) {
		var title = '查看加班信息';
		var url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		if(type == 1){
			title = '查看加班信息';
			url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		}else if(type == 2){
			title = '查看请假信息';
			url = sy.contextPath + '/securityJsp/app/EmpQingjiaRecordForm.jsp?id=' + id;
		}
		 
		var dialog = parent.sy.modalDialog({
			title : title,
			url : url,
			buttons : [ {
				text : '提交',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitFlowForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var cancelFun =  function(id,type) {
		var title = '取消加班信息';
		var url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		if(type == 1){
			title = '取消加班信息';
			url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		}else if(type == 2){
			title = '取消请假信息';
			url = sy.contextPath + '/securityJsp/app/EmpQingjiaRecordForm.jsp?id=' + id;
		}
		
		var dialog = parent.sy.modalDialog({
			title : title,
			url : url,
			buttons : [ {
				text : '提交',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.cancelFlowForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var editFun = function(id,type) {
		
		var title = '编辑加班信息';
		var url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		if(type == 1){
			title = '编辑加班信息';
			url = sy.contextPath + '/securityJsp/app/EmpJiabanRecordForm.jsp?id=' + id;
		}else if(type == 2){
			title = '编辑请假信息';
			url = sy.contextPath + '/securityJsp/app/EmpQingjiaRecordForm.jsp?id=' + id;
		}
		
		var dialog = parent.sy.modalDialog({
			title : title,
			url : url,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？' +id, function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/emp-jiaban!delete.sy', {
					empJiabanId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/emp-jiaban!grid.sy?opType='+<%=opType%>,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '加班编号',
				field : 'id',
				sortable : true,
				hidden:true
			}, {
				width : '130',
				title : '流水号',
				field : 'opOrder',
				sortable : true
			}  ] ],
			columns : [ [  {
				width : '50',
				title : '子类别',
				field : 'subType',
				sortable : true, 
				formatter : function(value, row, index) {
					if(row.type == 1){
						switch (value) {
						case 10109101:
							return '延时加班';
						case 10109102:
							return '周末加班';
						case 10109103:
							return '节假日加班';
						}
					}else if(row.type == 2){
						switch (value) {
						case 10110101:
							return '病假';
						case 10110102:
							return '事假';
						case 10110103:
							return '调休';
						case 10110104:
							return '公休';
						case 10110105:
							return '产假';
						}
					}
				}
			},  {
				width : '50',
				title : '类型',
				field : 'type',
				sortable : true, 
				hidden :true,
				formatter : function(value, row, index) {
					switch (value) {
					case 1:
						return '加班';
					case 2:
						return '请假';
					}
				}
			},{
				width : '80',
				title : '用户名',
				field : 'syuser',
				sortable : true,
				formatter : function(value, row, index) {
					return row.syuser.name;
				}
			},  {
				width : '130',
				title : '开始时间',
				field : 'startTime',
				sortable : true,
				formatter : function(value, row, index) {
					var unit = row.unit;
					if(unit == '10108101'){
						//天
						var newStartTime = Date.CreateDateTime(row.startTime,'yyyy年MM月dd日');
						return newStartTime;
					}else {
						//小时
						var newStartTime = Date.CreateDateTime(row.startTime,'yyyy年MM月dd日 hh:mm:ss');
						return newStartTime;
					}
					return row.startTime;
				}
			}, {
				width : '130',
				title : '结束时间',
				field : 'endTime',
				sortable : true,
				formatter : function(value, row, index) {
					var unit = row.unit;
					if(unit == '10108101'){
						//天
						var newEndTime = Date.CreateDateTime(row.endTime,'yyyy年MM月dd日');
						return newEndTime;
					}else{
						var newEndTime = Date.CreateDateTime(row.endTime,'yyyy年MM月dd日 hh:mm:ss');
						return newEndTime;
					}
					return row.endTime;
				}
			}, {
				width : '80',
				title : '申请时间',
				field : 'requiredTime',
				sortable : true,
				formatter : function(value, row, index) {
					return row.requiredTime;
				}
			},  {
				width : '80',
				title : '时间单位',
				field : 'unit',
				sortable : true,
				formatter : function(value, row, index) {
					console.log(value)
					switch(value){
					case '10108101' : 
						return '天';
					case '10108102':
						return '小时';
					}
					return '小时';
				}
			},{
				width : '150',
				title : '创建时间',
				field : 'createTime',
				sortable : true,
				hidden :true
			}, {
				width : '150',
				title : '修改时间',
				field : 'updateTime',
				sortable : true,
				hidden :true
			}, {
				width : '100',
				title : '日期',
				field : 'yyyymmdd',
				sortable : true
			}, {
				width : '50',
				title : '状态',
				field : 'status',
				sortable : true, 
				formatter : function(value, row, index) {
					//-1 取消  0 保存(新建) 1 提交(等待审批) 2 审批通过(syuser不能再修改了) 3 驳回 99删除状态
					switch (value) {
						case -1:
							return '取消';					
						case 0:
							return '新建';
						case 1:
							return '等待审批';
						case 2:
							return '审批通过';
						case 3:
							return '驳回';
						}
					 
					return "";
				}
			},{
				width : '50',
				title : '定性',
				field : 'opResult',
				sortable : true, 
				formatter : function(value, row, index) {
					 
					if(row.type == 1){
						switch (value) {
						case 0:
							return '未使用';
						case 1:
							return '已调休';
						case 2:
							return '费用结算';
						}
					}else if(row.type == 2){
						switch (value) {
						case 0:
							return '未销毁';
						case 1:
							return '已销毁';
						}
					}
					 
					return "";
				}
			}, {
				title : '操作',
				field : 'action',
				width : '285',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/emp-jiaban!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看加班资料','showFun(\'{0}\',\'{1}\');');
						str +=  sy.formatString(bt,row.id,row.type);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-jiaban!update")) {%>
						if(row.status != 2 && row.status != 1 ){
							var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑加班资料','editFun(\'{0}\',\'{1}\');');
							str +=  sy.formatString(bt,row.id,row.type);	
						}
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-jiaban!delete")) {%>
					if(row.status != 2&& row.status != 1 ){
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除加班','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					}
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-jiaban!submit")) {%>
					if(row.status != 2&& row.status != 1 ){	
						var bt = systool.createFuncButton('ext-icon-note_delete','提交','提交','submitFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					}
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-jiaban!cancel")) {%>
					if(row.status != 2&& row.status != 1 ){
						var bt = systool.createFuncButton('ext-icon-note_delete','取消','取消','cancelFun(\'{0}\');');
						str +=  sy.formatString(bt,row.id);
					}
					<%}%>					
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			}
		});
		
		var customerIdCombogrid= $('#customerIdCombogrid').combogrid({
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
			} ] ],
			onSelect:function(record){
				 
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<th>客户</th>
								<td>
									<input id="customerIdCombogrid" name="QUERY_t#customerInfo#customerId_I_EQ" type="text" value="" style="width: 214px;">
								</td>
							</tr>
							<tr>
								<td>用户姓名</td>
								<td><input name="QUERY_t#syuser#name_S_LK" style="width: 180px;" /></td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
							<%if(opType.equalsIgnoreCase("1")){%>
				<tr>
					<th>定性</th>
					<td><select class="easyui-combobox" name="QUERY_t#opResult_B_EQ" 
						data-options="panelHeight:'auto',editable:false"  
						data-options="required:true" style="width: 155px;">
							<option value="0" >未使用</option>
							<option value="1" >已调休</option>
							<option value="2" >费用结算</option>
					</select></td>
					<th></th>
					<td></td>
				</tr>
			<%}else{ %>
				<tr>
					<th>定性</th>
					<td><select class="easyui-combobox" name="QUERY_t#opResult_B_EQ" 
						data-options="panelHeight:'auto',editable:false"  
						data-options="required:true" style="width: 155px;">
								<option value="0" >未销假</option>
								<option value="1" >已销假</option>
					</select></td>
				</tr>
			<%} %>
						</table>
			
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%if (securityUtil.havePermission("/app/emp-jiaban!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun(<%=opType%>);"><%if(opType!=null && opType.equalsIgnoreCase("2")){%>请假申请<%}else{%>添加<%}%></a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td>
							 <td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	 
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>