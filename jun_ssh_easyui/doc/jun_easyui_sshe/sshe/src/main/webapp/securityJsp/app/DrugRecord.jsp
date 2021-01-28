<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%  
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加药品信息',
			url : sy.contextPath + '/securityJsp/app/DrugInfoForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看药品信息',
			url : sy.contextPath + '/securityJsp/app/DrugInfoForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑药品信息',
			url : sy.contextPath + '/securityJsp/app/DrugInfoForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/drug-record!delete.sy', {
					drugInfoId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var filtData = function() {
		var opType = $('#QUERY_t_opType_B_EQ').combobox('getValue');
		
		//console.log(opType);
		if($('#QUERY_t_opType_B_EQ').combobox('getValue') == 10){
			listInRecord();
		}else if($('#QUERY_t_opType_B_EQ').combobox('getValue') == 20){
			listOutRecord();
		}else if($('#QUERY_t_opType_B_EQ').combobox('getValue') == 30){
			listPdRecord();
		}else if($('#QUERY_t_opType_B_EQ').combobox('getValue') == 40){
			listOutRecord();
		}else if($('#QUERY_t_opType_B_EQ').combobox('getValue') == 50){
			listJiuzhengRecord();
		}
		grid.datagrid('load',sy.serializeObject($('#searchForm')));
	};
	
	var listInRecord = function(){
		var url = sy.contextPath + '/app/drug-in-record!grid.sy?opMod=IN';
		grid = $('#grid').datagrid({
			title : '',
			url : url,
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
				title : '编号',
				field : 'id',
				sortable : true,
				hidden: true
			}, {
				width : '100',
				title : '药品名称',
				field : 'drugSpecInfo.drugInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.drugName ;
				}
			}  ] ],
			columns : [ [ {
				width : '100',
				title : '客户',
				field : 'drugSpecInfo.drugInfo.customerInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.customerInfo.customerName ;
				}
			},{
				width : '80',
				title : '操作类型',
				field : 'opType',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 10:
						return '入库';
					case 20:
						return '消耗';
					case 30:
						return '盘点';
					case 40:
						return '出库';
					case 50:
						return '纠偏';
					}
					return '';
				}
			},{
				width : '80',
				title : '批次',
				field : 'drugLotNo',
				sortable : true
			},{
				width : '80',
				title : '数量',
				field : 'num',
				sortable : true
			}, {
				width : '80',
				title : '规格',
				field : 'drugSpecInfo.specification',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.specification;
				}
			}  ,
			 {
				width : '80',
				title : '单价',
				field : 'price',
				sortable : true
			}  ,{
				width : '120',
				title : '操作时间',
				field : 'createTime',
				sortable : true
			}  ,{
				width : '100',
				title : '操作人',
				field : 'syuser',
				sortable : true,
				formatter : function(value, row, index) {
					 return value.name ;
				}
			}  ,
			{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/drug-in-record!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看药品资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugSpecInfo.drugInfo.drugCode);
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
	};
	
	var listJiuzhengRecord = function(){
		var url = sy.contextPath + '/app/drug-in-record!grid.sy?opMod=JIUZHG';
		grid = $('#grid').datagrid({
			title : '',
			url : url,
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
				title : '编号',
				field : 'id',
				sortable : true,
				hidden: true
			}, {
				width : '100',
				title : '药品名称',
				field : 'drugSpecInfo.drugInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.drugName ;
				}
			}  ] ],
			columns : [ [ {
				width : '100',
				title : '客户',
				field : 'drugSpecInfo.drugInfo.customerInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.customerInfo.customerName ;
				}
			},{
				width : '80',
				title : '操作类型',
				field : 'opType',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 10:
						return '入库';
					case 20:
						return '消耗';
					case 30:
						return '盘点';
					case 40:
						return '出库';
					case 50:
						return '纠偏';
					}
					return '';
				}
			},{
				width : '80',
				title : '批次',
				field : 'drugLotNo',
				sortable : true
			},{
				width : '80',
				title : '数量',
				field : 'num',
				sortable : true
			}, {
				width : '80',
				title : '规格',
				field : 'drugSpecInfo.specification',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.specification;
				}
			}  ,
			 {
				width : '80',
				title : '单价',
				field : 'price',
				sortable : true
			}  ,{
				width : '120',
				title : '操作时间',
				field : 'createTime',
				sortable : true
			}  ,{
				width : '100',
				title : '操作人',
				field : 'syuser',
				sortable : true,
				formatter : function(value, row, index) {
					 return value.name ;
				}
			}  ,
			{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/drug-in-record!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看药品资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugSpecInfo.drugInfo.drugCode);
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
	};
	
	var listOutRecord = function(){
		var url = sy.contextPath + '/app/drug-out-record!grid.sy';
		grid = $('#grid').datagrid({
			title : '',
			url : url,
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
				title : '编号',
				field : 'id',
				sortable : true,
				hidden: true
			}, {
				width : '100',
				title : '药品名称',
				field : 'drugSpecInfo.drugInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.drugName ;
				}
			}  ] ],
			columns : [ [ {
				width : '100',
				title : '客户',
				field : 'drugSpecInfo.drugInfo.customerInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.customerInfo.customerName ;
				}
			},{
				width : '80',
				title : '操作类型',
				field : 'opType',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 10:
						return '入库';
					case 20:
						return '消耗';
					case 30:
						return '盘点';
					case 40:
						return '出库';
					case 50:
						return '纠偏';
					}
					return '';
				}
			},{
				width : '80',
				title : '批次',
				field : 'drugLotNo',
				sortable : true
			},{
				width : '80',
				title : '数量',
				field : 'num',
				sortable : true
			}, {
				width : '80',
				title : '规格',
				field : 'drugSpecInfo.specification',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.specification;
				}
			}  ,
			 {
				width : '80',
				title : '单价',
				field : 'price',
				sortable : true
			}  ,{
				width : '120',
				title : '操作时间',
				field : 'createTime',
				sortable : true
			}  ,{
				width : '100',
				title : '操作人',
				field : 'syuser',
				sortable : true,
				formatter : function(value, row, index) {
					 return value.name ;
				}
			}  ,
			{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/drug-record!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看药品资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugSpecInfo.drugInfo.drugCode);
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
	};
	
	
	var listPdRecord = function(){
		var url = sy.contextPath + '/app/drug-store-check!grid.sy';
		grid = $('#grid').datagrid({
			title : '',
			url : url,
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
				title : '编号',
				field : 'id',
				sortable : true,
				hidden: true
			}, {
				width : '100',
				title : '药品名称',
				field : 'drugSpecInfo.drugInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.drugName ;
				}
			}  ] ],
			columns : [ [ {
				width : '100',
				title : '客户',
				field : 'drugSpecInfo.drugInfo.customerInfo',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.drugInfo.customerInfo.customerName ;
				}
			},{
				width : '80',
				title : '操作类型',
				field : 'opType',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 10:
						return '入库';
					case 20:
						return '消耗';
					case 30:
						return '盘点';
					case 40:
						return '出库';
					case 50:
						return '纠偏';
					}
					return '';
				}
			},{
				width : '80',
				title : '批次',
				field : 'drugLotNo',
				sortable : true
			},{
				width : '80',
				title : '数量',
				field : 'num2',
				sortable : true
			}, {
				width : '80',
				title : '规格',
				field : 'drugSpecInfo.specification',
				sortable : true,
				formatter : function(value, row, index) {
					 return row.drugSpecInfo.specification;
				}
			}  ,
			 {
				width : '80',
				title : '单价',
				field : 'price',
				sortable : true
			}  ,{
				width : '120',
				title : '操作时间',
				field : 'createTime',
				sortable : true
			}  ,{
				width : '100',
				title : '操作人',
				field : 'syuser',
				sortable : true,
				formatter : function(value, row, index) {
					 return value.name ;
				}
			}  ,
			{
				title : '操作',
				field : 'action',
				width : '100',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/drug-record!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看药品资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugSpecInfo.drugInfo.drugCode);
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
	};
	
	$(function() {
		listInRecord();
		
		 $('#QUERY_t_opType_B_EQ').combobox({
		       onChange:function(data){
			 			filtData();
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
				//console.log(record);
				//刷新数据，重新读取省份下的城市，并清空当前输入的值  
				//alert(record);
				var g = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				
				filtData();
				
				//alert(r.customerId);
		       /*  $('#deptIdCombogrid').combogrid({
		            disabled:false,  
		            url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId='+r.customerId,
		            valueField:'deptId',  
		            textField:'deptName'  
		        }).combogrid('clear'); */
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
								<td>操作类型</td>
								<td>
									<select class="easyui-combobox" id="QUERY_t_opType_B_EQ" name="QUERY_t#opType_B_EQ" data-options="panelHeight:'auto',editable:false" onclick="filtData();" style="width: 155px;">
										<option value="10">入库操作</option>
										<option value="20">消耗操作</option>
										<option value="30">盘点操作</option>
										<option value="40">出库操作</option>
										<option value="50">纠正操作</option>
									</select>
								</td>
								<td>药品名称</td>
								<td><input name="QUERY_t#drugSpecInfo#drugInfo#drugName_S_LK" style="width: 180px;" /></td>
								<!-- <td>生产厂商</td>
								<td><input name="QUERY_t#drugSpecInfo#drugInfo#produceComp_S_LK" style="width: 180px;" /></td> -->
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="filtData();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%-- <%if (securityUtil.havePermission("/app/drug-info!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<%}%> --%>
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