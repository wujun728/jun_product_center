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
			console.log($(':input[name="data.drugInfo.drugCode"]').val()) 
			if ($(':input[name="data.drugInfo.drugCode"]').val() > 0) {
				if($op == 'rk'){
					url = sy.contextPath + '/app/drug-record!valid.sy?opMod=IN';
				}else if($op == 'pd'){
					url = sy.contextPath + '/app/drug-record!valid.sy?opMod=PANDIAN';
				}else if($op == 'JZ'){
					url = sy.contextPath + '/app/drug-record!valid.sy?opMod=JIUZHG';
				}
				
			} else {
				parent.$.messager.alert('提示', '请选择入库药品..如果没有找到你要添加的药品，请去药品管理界面中增加!!', 'info');
				return false;
			}
			
			console.log(sy.serializeObject($('form')))
			
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					
					parent.$.messager.alert('提示', result.msg, 'info');
					
					if (result.obj != null){
						console.log(result.obj)
						if (result.obj.id != undefined) {
									$('form').form('load',{
														'data.id' : result.obj.id,
														'data.drugInfo.drugCode' : result.obj.drugInfo.drugCode,
														'data.num' : result.obj.num,
														'data.price' : result.obj.price,
														'data.drugInfo.specification' : result.obj.drugInfo.specification
														
									});
									
								}
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
	 
	 
    
   
  	
	$(function() {
		 
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/drug-store!getById.sy', {
				DrugStoreId : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.storeId != undefined) {
					$('form').form('load', {
						'data.drugInfo.drugCode' : result.drugInfo.drugCode,
						'data.drugTimes' : result.drugTimes,
						'data.num' : result.num,
						'data.drugInfo.specification' : result.specification,
						'data.num2' : result.num2
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
			url : sy.contextPath + '/app/drug-info!doNotNeedSecurity_getMainMenu.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'drugCode',
			textField : 'drugName',
			pagination : true,
			fitColumns : true,
			required : false,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'drugCode',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'drugCode',
				title : '编号',
				width : 100,
				sortable : true
			}, {
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
				field : 'expireTime',
				title : '过期时间',
				width : 150,
				sortable : true
			}, {
				field : 'specification',
				title : '规格',
				width : 150,
				sortable : true
			} ] ],
			onSelect:function(record){
				//console.log(record);
				var g = $('#drugCodeCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId )
				if(r.drugCode != null){
					$(':input[name="data.drugInfo.specification"]').val(r.specification);
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
												name="data.id" readonly="readonly"></input></td>
											<th>药品选择</th>
											<td><input id="drugCodeCombogrid" 
												name="data.drugInfo.drugCode" class="easyui-validatebox"
												data-options="required:false" type="text"
												style="width: 130px;"></td>
											</td>
										</tr>
										<tr>
											<th>数量</th>
											<td><input name="data.num" class="easyui-numberbox"
												max="99999.99" type="text" style="width: 130px;"
												data-options="required:true"> 最大 99999.99</td>
											</td>
											<th>规格</th>
											<td><input name="data.drugInfo.specification"
												class="easyui-validatebox" type="text" style="width: 130px;"
												readonly="readonly"></td>
											</td>
										</tr>
										<tr>
											<th>盘点数量</th>
											<td><input name="data.num2" class="easyui-numberbox"
												type="text" precision="2" max="99999.99" size="8"
												maxlength="8" style="text-align: right;"
												style="width: 130px;" data-options="required:true">元
											</td>
											<th>批次</th>
											<td><input name="data.drugTimes" class="easyui-validatebox"
												type="text"  max="99999" size="10"
												maxlength="10" style="text-align: right;"
												style="width: 130px;" data-options="required:true">
											</td>
										</tr>
									</table>
								</form>
								 
							</div>
							<div style="text-align: center; padding: 5px">
								 
								 
							</div>
						</div>
					</td>
				</tr>  
			</table>

		</div>
	</div>


	 
	 
</body>
</html>