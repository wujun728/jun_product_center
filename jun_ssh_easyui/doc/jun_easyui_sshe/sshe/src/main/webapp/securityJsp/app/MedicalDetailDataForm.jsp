<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
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
	var isIndicatorsCombox;
	var medicalTypeCombox;
	var IsAbnormalCombox;
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.attrId"]').val().length > 0) {
			url = sy.contextPath + '/app/medical-detail-data!update.sy';
		} else {
			url = sy.contextPath + '/app/medical-detail-data!save.sy';
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
	var getIsAbnormalComboxById = function(id) {
		var data = $("#IsAbnormalCombox").combobox('getData');
		var name ='';
		var count = data.length;
		//alert('count:'+ count)
		for(var i =0; i < count; i++){
			//alert(data[i].text)
			 if(data[i].id == id)  
		        {  
		            name = data[i].text;
		            break;  
		        }
		}
		return name;
	}
	
	$(function() {
		isIndicatorsCombox = $('#isIndicatorsCombox').combobox({
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_VALUE_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		IsAbnormalCombox =$('#IsAbnormalCombox').combobox({
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_ISABNORMAL_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		
		if ($(':input[name="data.attrId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/medical-detail-data!getById.sy', {
				id : $(':input[name="data.dataId"]').val()
			}, function(result) {
				if (result.attrId != undefined) {
					$('form').form('load', {
						'data.attrId' : result.attrId,
						'data.attrName' : result.attrName,
						'data.medicalReportDef.templateId' : result.medicalReportDef.templateId,
						'data.medicalReportDef.templateName' : result.medicalReportDef.templateName,
						'data.attrCankao' : result.attrCankao,
						'data.attrKeshi' : result.attrKeshi,
						'data.attrCheck' : result.attrCheck,
						'data.attrUnit' : result.attrUnit,
						'data.isIndicators' : result.isIndicators,
						'data.attrDesc' : result.attrDesc,
						'data.ext1' : result.ext1,
						'data.medicalType': result.medicalType,
						'data.opOrder': result.opOrder,
						'data.custUser.userName':result.custUser.userName,
						'data.custUser.userId' : result.custUser.userId,
						'data.isAbnormal':result.isAbnormal,
						'data.medicalTime' : result.medicalTime,
						'data.attrValue': result.attrValue
					});
					if (result.customerLogo) {
						$('#customerLogo').attr('src', sy.contextPath + result.customerLogo);
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		 
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>属性数据</legend>
			<table class="table" style="width: 100%;">
			<tr>
					<th>体检人</th>
					<td>
						<input name="data.custUser.userName" readonly="readonly" /> 
						<input name="data.custUser.userId"  hidden="true" readonly="readonly" /></td>
					<th>体检日期</th>
					<td>
						<input name="data.medicalTime"   readonly="readonly" /></td>
					</td>
				</tr>
				
				<tr>
					<th>体检性质</th>
					<td>
						<input name="data.medicalType"  readonly="readonly" /></td>
					<th>操作流水号</th>
					<td>
						<input name="data.opOrder"  readonly="readonly" /></td>
					</td>
				</tr>
				
				<tr>
					<th>属性编号</th>
					<td>
						<input name="data.dataId" value="<%=id%>" hidden="true"  readonly="readonly" />
						<input name="data.attrId" value="<%=id%>"  readonly="readonly" /></td>
					<th>体检报告</th>
					<td> 
					    <input name="data.medicalReportDef.templateId" value="<%=templateId%>"  style="width: 10px;display: none" readonly="readonly" />
					     <input name="data.medicalReportDef.templateName" value="<%=templateName%>"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>属性名称</th>
					<td><input name="data.attrName" class="easyui-validatebox"  size="20"  maxlength="20" width="300px" data-options="required:true" /></td>
					<th>属性参考值</th>
					<td><input name="data.attrCankao" class="easyui-validatebox"  maxlength="80"  data-options="required:false" /></td>
				</tr>
				<tr>
					<th>所属科室</th>
					<td>
					<select id="data.attrKeshi" name="data.attrKeshi" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_KESHI'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.attrKeshi').combotree('clear');" title="清空" />
					</td>
					<th>属性检查类型</th>
					<td>
						<select id="data.attrCheck" name="data.attrCheck" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_CHECK'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.attrCheck').combotree('clear');" title="清空" />
					</td>
				</tr>
				<tr>
					<th>属性单位</th>
					<td>
						<select id="data.attrUnit" name="data.attrUnit" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_UNIT'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.attrUnit').combotree('clear');" title="清空" />
					</td>
					<th>属性数值</th>
					<td><input name="data.attrValue" class="easyui-validatebox"  maxlength="80"  data-options="required:false" /></td>
				</tr>
				<tr>
					<th>数值类型</th>
					<td>
						<select id="isIndicatorsCombox" name="data.isIndicators" class="easyui-combotree"  data-options="required:true" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" />
					</td>
					<th>是否异常</th>
					<td>
						<select id="IsAbnormalCombox" name="data.isAbnormal" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#IsAbnormalCombox').combotree('clear');" title="清空" />
					</td>
				</tr>
				
				<tr>
					<th>属性描述</th>
					<td colspan="3"><textarea name="data.attrDesc" cols="100"  style="width: 300px"></textarea></td>
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