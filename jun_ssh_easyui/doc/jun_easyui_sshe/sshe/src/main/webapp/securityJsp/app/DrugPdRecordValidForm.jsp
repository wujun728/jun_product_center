<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%@ page import="sy.util.base.ConfigUtil"%>
<%
	//药品纠正入库
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	SecurityUtil securityUtil = new SecurityUtil(session);
	System.out.println(">>>>>>" + id);
	id = ( id == null ) ? "" : id; 
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
    
	
	var submitForm  = function($op,$dialog, $grid, $pjq) {
		if ($('form').form('validate')) 
		{
			var url;
			console.log($(':input[name="data.drugSpecInfo.specId"]').val()) 
			if ($(':input[name="data.drugSpecInfo.specId"]').val() > 0) {
				if($op == 'rk'){
					url = sy.contextPath + '/app/drug-store-check!save.sy?opMod=IN';
				}else if($op == 'PD'){
					url = sy.contextPath + '/app/drug-store-check!save.sy?opMod=PANDIAN';
				}else if($op == 'JZ'){
					url = sy.contextPath + '/app/drug-store-check!save.sy?opMod=JIUZHG';
				}
				
			} else {
				parent.$.messager.alert('提示', '请选择入库药品..如果没有找到你要添加的药品，请去药品管理界面中增加!!', 'info');
				return false;
			}
			
			console.log(sy.serializeObject($('form')))
			console.log(url)
			$.post(url, sy.serializeObject($('form')), function(result) {
				
				
				parent.sy.progressBar('close');//关闭上传进度条
				if (result.success) {
					
					parent.$.messager.alert('提示', result.msg, 'info');
					
					if (result.obj != null){
						console.log(result.obj)
						if (result.obj.id != undefined) {
									$('form').form('load',{
														'data.storeId' : result.obj.id,
														'data.drugInfo.drugCode' : result.obj.drugInfo.drugCode,
														'data.num' : result.obj.num,
														'data.price' : result.obj.price,
														'data.num2' : result.obj.num2,
														'data.drugInfo.specification' : result.obj.drugInfo.specification
														
									});
									
								}
					}
					
					
						$grid.datagrid('load');
						$dialog.dialog('destroy');
				 			
					
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
	 
	
	$(function() {
		 
		if ($(':input[name="data.storeId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/drug-store!getById.sy', {
				DrugStoreId : $(':input[name="data.storeId"]').val()
			}, function(result) {
				if (result.storeId != undefined) {
					$('form').form('load', {
						'data.drugSpecInfo.specId' : result.drugSpecInfo.specId,
						'data.drugSpecInfo.drugInfo.drugCode' : result.drugSpecInfo.drugInfo.drugCode,
						'data.drugLotNo' : result.drugLotNo,
						'data.num' : result.num,
						'data.specification' : result.specification,
						'data.num2' : result.num2,
						'data.unit' : result.unit
					});
					if (result.customerLogo) {
						$('#customerLogo').attr('src', sy.contextPath + result.customerLogo);
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		
		////-----------------------------------------------------
		var drugCodeCombogrid = $('#drugCodeCombogrid').combogrid({
			url : sy.contextPath + '/app/drug-spec-info!doNotNeedSecurity_getMainMenu.sy',
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
					//$(':input[name="data.drugSpecInfo.specification"]').val(r.specification);
					//$(':input[name="data.drugSpecInfo.drugInfo.drugCode"]').val(r.drugCode);
					//$(':input[name="data.unit"]').val(r.unit);
				}
			}
			
		});
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 	
	});	
</script>

 

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	
	<div class="easyui-panel" title=""  style="height: 526px" data-options="fit:true,border:false" >
		<div style="padding: 10px 0 10px 10px;"   >
			<table>
			     <tr>
					<td>
						<div class="easyui-panel" title="" style="height: 140px;width: 500px">
							<div style="padding: 10px 0 10px 10px">
								<form id="form" method="post">
									<table>
										<tr>
											<td>编号:</td>
											<td><input class="easyui-validatebox" type="text"  value="<%=id%>"
												name="data.storeId" readonly="readonly"></input></td>
											<th>药品选择</th>
											<td><input id="drugCodeCombogrid"   readonly="readonly"
												name="data.drugSpecInfo.specId" class="easyui-validatebox"
												data-options="required:false" type="text"
												style="width: 130px;">
												<input   required="required"
												name="data.drugSpecInfo.drugInfo.drugCode" readonly="readonly"  hidden="true" class="easyui-validatebox"
												data-options="required:false" type="text"
												style="width: 130px;">
												</td>
											</td>
										</tr>
										<tr>
											<th>数量</th>
											<td><input name="data.num" class="easyui-numberbox" readonly="readonly"
												max="99999.99" type="text" style="width: 130px;"   
												data-options="required:true"> 最大 99999.99</td>
											</td>
											<th>规格</th>
											<td><input name="data.specification" required="required"
												class="easyui-validatebox" type="text" style="width: 130px;"
												readonly="readonly"></td>
											</td>
										</tr>
										<tr>
											<th><font color="red">盘点数量</font></th>
											<td><input name="data.num2" class="easyui-numberbox"
												type="text" max="99999.99" size="8"
												maxlength="8" style="text-align: right;"
												style="width: 130px;" data-options="required:true">
											</td>
											<th>批次</th>
											<td><input name="data.drugLotNo" class="easyui-validatebox" readonly="readonly"
												type="text"  max="99999" size="10"
												maxlength="10" style="text-align: right;"
												style="width: 130px;" data-options="required:true">
											</td>
										</tr>
										<tr>
											<th>单位</th>
											<td><input name="data.unit" class="easyui-validatebox" readonly="readonly"
												type="text" max="99999.99" size="10"
												maxlength="8" style="text-align: right;"
												style="width: 130px;" data-options="required:true">
											</td>
											<th></th>
											<td>
											</td>
										</tr>
									</table>
								</form>
								 
							</div>
							
						</div>
					</td>
				</tr>
			</table>
			<div style="text-align: left; padding: 5px">
				<input id="iconCls"  class="ext-icon-bell" readonly="readonly" style="padding-left: 18px; width: 13px;border: none" />盘点纠正：把库存的数量改成盘点的数量
				<br/>
				<input id="iconCls"  class="ext-icon-bell" readonly="readonly" style="padding-left: 18px; width: 13px;border: none" />盘点纠正：盘点的情况下，只能输入盘点的数量
				<br/>
				<input id="iconCls"  class="ext-icon-bell" readonly="readonly" style="padding-left: 18px; width: 13px;border: none" />盘点纠正：如果找不到对应的药品和规格的盘点数据，请检查原因，并且到药品入库模块中增加
				<br/>
				<input id="iconCls"  class="ext-icon-bell" readonly="readonly" style="padding-left: 18px; width: 13px;border: none" />盘点纠正：为了保证盘点数据的输入准确性，当前版本只支持单个数据的盘点！！
				
			</div>
		</div>
	</div>


	 
	 
</body>
</html>
