<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%@ page import="sy.util.base.ConfigUtil"%>
<%
	//药品入库
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	SecurityUtil securityUtil = new SecurityUtil(session);
	System.out.println(">>>>>>" + id);
	id = ( id == null ) ? "" : id; 
	String drugCode = request.getParameter("drugCode");
	drugCode = ( drugCode == null ) ? "" : drugCode; 
	
	
	String specification = request.getParameter("specification");
	specification = ( specification == null ) ? "" : specification; 
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var userIdCombogrid;
	var datagrid; //定义全局变量datagrid
    var editRow = undefined; //定义全局变量：当前编辑的行
    var drugCodeCombogrid; //药品信息
    
	var listMyRecord_CC = function() {
		var url = sy.contextPath + '/securityJsp/app/MyDrugRecordList.jsp';
		window.location.href=url;
	};
 	
	var submitForm_BASE = function($op) {
		if ($('form').form('validate')) 
		{
			var url;
			console.log($(':input[name="data.drugSpecInfo.specId"]').val()) 
			if ($(':input[name="data.drugSpecInfo.specId"]').val() > 0) {
				if($op == 'rk'){
					url = sy.contextPath + '/app/drug-in-record!save.sy?opMod=IN';
				}else if($op == 'pd'){
					url = sy.contextPath + '/app/drug-in-record!save.sy?opMod=PANDIAN';
				}
				
			} else {
				parent.$.messager.alert('提示', '请选择入库药品..如果没有找到你要添加的药品，请去药品管理界面中增加!!', 'info');
				return false;
			}
			
			if ($(':input[name="data.expireTime"]').val().length <= 0) {
				parent.$.messager.alert('提示', '过期时间不能为空!', 'info');
				return false;
			}
			
			
			if ($(':input[name="data.drugLotNo"]').val().length <= 0) {
				parent.$.messager.alert('提示', '过期时间不能为空!', 'info');
				$(':input[name="data.drugLotNo"]').val(0);
			}
			
			console.log(sy.serializeObject($('form')))
			//alert(sy.serializeObject($('form')))
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					
					parent.$.messager.alert('提示', result.msg, 'info');
					
					if (result.obj != null){
						console.log(result.obj)
						if (result.obj.id != undefined) {
									$('form').form('load',{
														'data.id' : result.obj.id,
														'data.drugSpecInfo.specId' : result.obj.specId,
														'data.num' : result.obj.num,
														'data.price' : result.obj.price,
														'data.drugSpecInfo.specification' : result.obj.drugInfo.specification
														
									});
									
								}
						listType();
					}
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('fail');
					//$('#workTime').html('');
					//$('#afterWorkTime').html('');
					//$('#isLeave').html('');
				}
			}, 'json');
		} 
	};
	var listDrugCombogrid = function($customerId) {
		 drugCodeCombogrid = $('#drugCodeCombogrid').combogrid({
			url : sy.contextPath + '/app/drug-spec-info!doNotNeedSecurity_getMainMenu.sy?customerId='+$customerId,
			panelWidth : 500,
			panelHeight : 200,
			idField : 'specId',
			textField : 'drugName',
			pagination : true,
			fitColumns : true,
			required : false,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'specId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'specId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'drugCode',
				title : '药品编号',
				width : 100,
				sortable : true
			},{
				field : 'customerId',
				title : '客户编号',
				width : 100,
				sortable : true
			}, {
				field : 'customerName',
				title : '客户名称',
				width : 100,
				sortable : true
			},   {
				field : 'drugName',
				title : '药品名称',
				width : 100,
				sortable : true
			}, {
				field : 'drugDesc',
				title : '药品描述',
				width : 150,
				sortable : true
			}, {
				field : 'specification',
				title : '规格',
				width : 150,
				sortable : true
			},{
				field : 'unit',
				title : '单位',
				width : 150,
				sortable : true
			}  ] ],
			onSelect:function(record){
				//console.log(record);
				var g = $('#drugCodeCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId )
				if(r.drugCode != null){
					$(':input[name="data.drugSpecInfo.specification"]').val(r.specification);
					$(':input[name="data.drugSpecInfo.drugInfo.drugCode"]').val(r.drugCode);
					$(':input[name="data.unit"]').val(r.unit);
				}
			}
			
		});
	}
	$(function() {
		//listType();
		////-----------------------------------------------------
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	
<%--     var attrUnitCombox = $('#attrUnitCombox').combobox({
		url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=TEST',
        idField:'id',
        valueField:'id',
        textField:'text',
        parentField:'pid'
      });
	   --%>
	   
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
				var g = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId )
				if(r.customerId != null){
					listDrugCombogrid(r.customerId);
				}
			}
		});
    //~~~~~~~~~~~~~~~~~~~~~~~~~
	});
</script>

 

</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<div class="easyui-panel" title="药品入库" style="height: 526px"
		data-options="fit:true,border:false">
		<div style="padding: 10px 0 10px 10px;">
			<table>
				<tr>
					<td>
						<div class="easyui-panel" title="入库操作"
							style="height: 340px; width: 800px">
							<div style="padding: 10px 0 10px 10px">
								<form id="form" method="post">
									<table>
										<tr>
											<th>客户</th>
											<td><input id="customerIdCombogrid"
												name="data.customerInfo.customerId" type="text" value=""
												style="width: 214px;"></td>
											<th></th>
											<td></td>
										</tr>
										<tr>

											<th>药品选择</th>
											<td><input id="drugCodeCombogrid" value="<%=id%>"
												name="data.drugSpecInfo.specId" class="easyui-validatebox"
												data-options="required:false" type="text"
												style="width: 130px;"> <input
												name="data.drugSpecInfo.drugInfo.drugCode"
												value="<%=drugCode%>" readonly="readonly" hidden="true"
												class="easyui-validatebox" data-options="required:false"
												type="text" style="width: 130px;"></td>
											</td>
											<td>
												<!-- 编号: -->
											</td>
											<td><input class="easyui-validatebox" type="text"
												hidden="true" name="data.id" readonly="readonly"></input></td>
										</tr>
										<tr>
											<th>规格</th>
											<td><input name="data.drugSpecInfo.specification"
												value="<%=specification%>" class="easyui-validatebox"
												type="text" style="width: 130px;" readonly="readonly"></td>
											</td>
											<th>数量</th>
											<td><input name="data.num" class="easyui-numberbox"
												max="99999.99" type="text" style="width: 130px;"
												data-options="required:true"> 最大 99999.99</td>
											</td>
										</tr>
										<tr>
											<th>单价</th>
											<td><input name="data.price" class="easyui-numberbox"
												type="text" precision="2" max="99999.99" size="8"
												maxlength="8" style="text-align: right;"
												style="width: 130px;" data-options="required:true">元
											</td>
											<th>批次</th>
											<td><input name="data.drugLotNo"
												class="easyui-validatebox" type="text" max="99999" size="10"
												maxlength="10" style="text-align: right;"
												style="width: 130px;" data-options="required:false">
											</td>
										</tr>

										<tr>
											<th>单位</th>
											<td><input name="data.unit" class="easyui-validatebox"
												readonly="readonly" type="text" max="99999" size="10"
												maxlength="10" style="text-align: right;"
												style="width: 130px;" data-options="required:true"></td>
											<th>生产厂商</th>
											<td><input name="data.produceComp"
												class="easyui-validatebox" data-options="required:false" />
											</td>

										</tr>
										<tr>
											<th>生产时间</th>
											<td><input name="data.produceTime"
												class="easyui-datebox"
												onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
												style="width: 120px;" data-options="required:false" /></td>
											<th>过期时间</th>
											<td><input name="data.expireTime" class="easyui-datebox"
												onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
												style="width: 120px;" data-options="required:true" /></td>
										</tr>
										<tr>
											<th colspan="2">生产厂商联系地址</th>
											<td colspan="2"><input name="data.contact"
												class="easyui-validatebox" data-options="required:false"
												maxlength="50" style="width: 320px;" /></td>
										</tr>
										<tr>
											<th>备注</th>
											<td colspan="3"><textarea name="data.ext1" cols="50"></textarea></td>
										</tr>

									</table>
								</form>

							</div>
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-note_add',plain:true"
									onclick="listMyRecord_CC();">查询历史入库</a> <a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_BASE('rk')">入库</a>
								<!--  <a href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_BASE('pd')">盘点</a>  -->
							</div>
						</div>
					</td>
				</tr>

				<!-- 				<tr>
					<td>
						<div class="easyui-panel" title="历史入库信息"   >
							 
							<div data-options="region:'center',fit:true,border:false" style="height: 330px">
								  <form id="dataform" style="height:100%">
									<table id="datagrid" data-options="fit:true,border:false"></table>
								</form>  
							</div>
						</div>
					</td>
				</tr> -->



			</table>

		</div>
	</div>




</body>
</html>