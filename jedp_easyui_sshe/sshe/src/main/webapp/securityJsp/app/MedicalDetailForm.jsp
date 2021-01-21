<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
//一般体检
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	boolean isZhiye = false;
	String oType = request.getParameter("oType");
	if (oType == null) {
		oType = "TJ_REPORT_TYPE_YEAR";
	}
	String medicalProject = "";
	if(oType.equalsIgnoreCase("TJ_REPORT_TYPE_YEAR")){
		medicalProject ="年度体检";
		isZhiye = false;
	}else if(oType.equalsIgnoreCase("TJ_REPORT_TYPE_INDUTY")){
		medicalProject ="入职体检";
		isZhiye = false;
	}else if(oType.equalsIgnoreCase("TJ_REPORT_TYPE_ZY_LZ")){
		medicalProject ="离职体检(职业病)";
		isZhiye = true;
	}else if(oType.equalsIgnoreCase("TJ_REPORT_TYPE_ZY_RZ")){
		medicalProject ="入职体检(职业病)";
		isZhiye = true;
	}else if(oType.equalsIgnoreCase("TJ_REPORT_TYPE_ZY_YEAR")){
		medicalProject ="年度体检(职业病)";
		isZhiye = true;
	}
		
	String data_empMedicalDetail_id = request.getParameter("data_empMedicalDetail_id");
	if (data_empMedicalDetail_id == null) {
		data_empMedicalDetail_id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	 var medicalTypeCombox;
	 var medicalBaseTypeCombox;
	 
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/app/medical-detail!update.sy';
		} else {
			url = sy.contextPath + '/app/medical-detail!save.sy';
		}
		 
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.treegrid('load');
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
	
	var getMedicalTypeOptionById = function(id) {
		var data = $("#medicalTypeCombox").combobox('getData');
		var name = '';
		var count = data.length;
		//alert('count:'+ count)
		for ( var i = 0; i < count; i++) {
			//alert(data[i].text)
			if (data[i].id == id) {
				name = data[i].text;
				break;
			}
		}
		return name;
	}
	var getMedicalBaseTypeOptionById = function(id) {
		var data = $("#medicalBaseTypeCombox").combobox('getData');
		var name = '';
		var count = data.length;
		//alert('count:'+ count)
		for ( var i = 0; i < count; i++) {
			//alert(data[i].text)
			if (data[i].id == id) {
				name = data[i].text;
				break;
			}
		}
		return name;
	}
	$(function() {

		medicalTypeCombox= $('#medicalTypeCombox').combobox({
	        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getSubListByType.sy?type=<%=oType%>',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid',
	        onLoadSuccess : function(){
	        	 var data = $('#medicalTypeCombox').combobox('getData');
                 if (data.length > 0) {
                	 $('#medicalTypeCombox').combobox('select',data[0].id);
                 }
	        }
		});
		
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/medical-detail!getById.sy', {
				EmpMedicalDetailId : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.opOrder' : result.opOrder,
						'data.custUser.userName' : result.custUser.userName,
						'data.custUser.userId' : result.custUser.userId,
						'data.medicalTime' : result.medicalTime,
						'data.medicalType' : result.medicalType,
						'data.medicalBaseType' : result.medicalBaseType,
						'data.medicalProject' : result.medicalProject,
						'data.expectReportTime' : result.expectReportTime,
						'data.realReportTime' : result.realReportTime,
						'data.reportFax' : result.reportFax,
						'data.medicalResult' : result.medicalResult,
						'data.isInduction' : result.isInduction,
						'data.isInductionExt' : result.isInductionExt,
						'data.medocaConclusion' : result.medocaConclusion,
						'data.medocaConclusionOp' : result.medocaConclusionOp,
						'data.hasCheck' : result.hasCheck,
						'data.offTime' : result.offTime,
						'data.whetherOff' : result.whetherOff,
						'data.medicalCheckTime' : result.medicalCheckTime,
						'data.acceptReportTime' : result.acceptReportTime,
						'data.reviewResult' : result.reviewResult,
						'data.reviewConclusion' : result.reviewConclusion,
						'data.reviewConclusionOp' : result.reviewConclusionOp,
						'data.appointment' : result.appointment,
						'data.ext1' : result.ext1,
						'data.medicalReportDef.templateId' : result.medicalReportDef != undefined ?result.medicalReportDef.templateId :''
						
					});
					 
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		medicalBaseTypeCombox= $('#medicalBaseTypeCombox').combobox({
	        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE_BASE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid',
	        onLoadSuccess : function(){
	        	 var data = $('#medicalTypeCombox').combobox('getData');
                 if (data.length > 0) {
                	 $('#medicalTypeCombox').combobox('select',data[0].id);
                 }
	        }
		});
		<%-- data_empMedicalDetail_id = $('#data_empMedicalDetail_id').combotree({
	        url:'<%=contextPath%>/app/medical-detail!doNotNeedSessionAndSecurity_treeGrid.sy?data.medicalType=10104101',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid',
	        onLoadSuccess : function(){
	        	 /* var data = $('#medicalTypeCombox').combobox('getData');
                 if (data.length > 0) {
                	 $('#medicalTypeCombox').combobox('select',data[0].id);
                 } */
	        }
		}); --%>
		$('#userIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-user!doNotNeedSessionAndSecurity_userIdCombogrid.sy',
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
			} ] ]
		});
		;
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>体检基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>体检报告</th>
					<td>
						<select id="data.medicalReportDef.templateId" name="data.medicalReportDef.templateId" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/app/report-def!doNotNeedSecurity_getMainMenu.sy'" style="width: 155px;"></select>
					</td>
					<!-- <th>体检编号</th> -->
					<th>体检流水号</th>
					<td>
						<input name="data.id" value="<%=id%>" hidden="true"  readonly="readonly" />
						<input name="data.opOrder"  class="easyui-validatebox" readonly="readonly" />
					</td>
				</tr>
				<tr>
				
					<th>体检人</th>
					<td colspan="3">
					<%if(id != null && !id.isEmpty()){%> 
					<input name="data.custUser.userName" class="easyui-validatebox"  disabled="disabled" size="20"  maxlength="20" width="300px" data-options="required:false" />
					<input name="data.custUser.userId" class="easyui-validatebox"  hidden="true" size="20"  maxlength="20" width="300px" data-options="required:true" />
					<%}else{ %>
					<input id="userIdCombogrid"  name="data.custUser.userId" type="text" value="" style="width: 214px;">
					<%} %>
					</td>
					
				</tr>
				<tr>
				<th>上级</th>
					<td colspan="3">
						
						<!-- <select id="data_empMedicalDetail_id" name="data.empMedicalDetail.id" class="easyui-combotree"  style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data_empMedicalDetail_id').combotree('clear');" title="清空" /> -->
						<input id="data_empMedicalDetail_id"  readonly="readonly" name="data.empMedicalDetail.id" type="text" value="<%=data_empMedicalDetail_id%>" style="width: 214px;">
						
					</td>
				</tr>
				<tr>
					<th>体检日期</th>
					<td colspan="1">
					<input name="data.medicalTime" class="easyui-datebox"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									 style="width: 120px;" />
				
					<th>基本类型</th>
						<td>
							<select id="medicalBaseTypeCombox" name="data.medicalBaseType" class="easyui-combotree"  style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#medicalBaseTypeCombox').combotree('clear');" title="清空" />
						</td>
				</tr>
				<tr>
					<th>体检性质</th>
					<td>
						<select id="medicalTypeCombox" name="data.medicalType" class="easyui-combotree"  style="width: 155px;"></select><!-- <img class="iconImg ext-icon-cross" onclick="$('#medicalTypeCombox').combotree('clear');" title="清空" /> -->
						<%-- <select id="data.medicalType" name="data.medicalType" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" /> --%>
					 </td>
					<th><%if(isZhiye){ %>特殊体检项目<%} else {%>体检项目<%} %></th>
					<td><input name="data.medicalProject" class="easyui-validatebox"  maxlength="80"  value="<%=medicalProject%>" data-options="required:<%if(isZhiye){ %>true<%} else {%>false<%} %>" /></td>
				</tr>
			 	
				<tr>
					<th>预计出报告时间</th>
					<td><input name="data.expectReportTime" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									readonly="readonly" style="width: 120px;" /></td>
					<th>实际出报告时间</th>
					<td><input name="data.realReportTime" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									readonly="readonly" style="width: 120px;" /></td>
				</tr>
				<tr>
					<th>报告传真签收情况</th>
					<td>
					<select class="easyui-combobox" name="data.reportFax" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>1已经签收</option>
							<option value=2>未签收</option>
					</select>
					</td>
					<th>体检结果</th>
					<td>
					<input name="data.medicalResult" class="easyui-validatebox" data-options="required:false" />
					 </td>
				</tr>
				<tr>
					<th>是否可以入职</th>
					<td>
					<select class="easyui-combobox" name="data.isInduction" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>可以入职</option>
							<option value=2>不可以入职</option>
					</select>
					</td>
					<th>建议(不可以则填写)</th>
					<td>
					<input name="data.isInductionExt" class="easyui-validatebox" data-options="required:false" />
					 </td>
				</tr>
				
				<tr>
					<th>检查结论</th>
					<td>
					<input name="data.medocaConclusion" class="easyui-validatebox" data-options="required:false" />
					</td>
				</tr>
				
				<tr>
					<th>结论确定者</th>
					<td>
						<input name="data.medocaConclusionOp" class="easyui-validatebox" data-options="required:false" />
					</td>
					<th>是否已参检</th>
					<td>
					<select class="customerType" name="data.hasCheck" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">参检</option>
							<option value="2">没有参检</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>离职时间</th>
					<td><input name="data.offTime" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									readonly="readonly" style="width: 120px;" /></td>
					<th>是否至医务室办离职手续</th>
					<td>
					<select class="customerType" name="data.whetherOff" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">可以办理手续</option>
							<option value="2">不能办理离职手续</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>复查体检开始日期</th>
					<td><input name="data.medicalCheckTime" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									readonly="readonly" style="width: 120px;" /></td>
					<th>复查接收报告时间</th>
					<td>
					<input name="data.acceptReportTime" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"
									readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				
				<tr>
					<th>复查结果</th>
					<td><input name="data.reviewResult" class="easyui-validatebox" data-options="required:false" /></td>
					<th>复查结论</th>
					<td>
						<input name="data.reviewConclusion" class="easyui-validatebox" data-options="required:false" />
					</td>
				</tr>
				<tr>
					<th>复查结论确定者</th>
					<td>
						<input name="data.reviewConclusionOp" class="easyui-validatebox" data-options="required:false" />
					</td>
					<th>体检预约人</th>
					<td>
						<input name="data.appointment" class="easyui-validatebox" data-options="required:false" />
					</td>
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