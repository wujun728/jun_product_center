<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="sy.util.base.SecurityUtil"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%@ page import="sy.util.base.ConfigUtil"%>
<%
//加班信息
	String contextPath = request.getContextPath();
%>
<%	
	String opType = request.getParameter("opType");

	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
 	
	SessionInfo sessionInfo = (SessionInfo) request.getSession()
			.getAttribute(ConfigUtil.getSessionInfoName());
	String userId = sessionInfo.getUserId();
	String userName = sessionInfo.getUser().getName();
	 
	String workTime = request.getParameter("workTime");
	String afterWorkTime = request.getParameter("afterWorkTime");
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	
	var checkIsJiabanValid = function($dialog, $grid, $pjq){
		
		if ($(':input[name="data.startTime"]').val().length <= 0) {
			$pjq.messager.alert('提示', '加班开始时间不能为空!', 'info');
			return false;
		}
		if ($(':input[name="data.endTime"]').val().length <= 0) {
			$pjq.messager.alert('提示', '加班结束时间不能为空!', 'info');
			return false;
		}
		
		var startTime = $(':input[name="data.startTime"]').val();
		var endTime = $(':input[name="data.endTime"]').val();
		//开始日期
		var startDate = startTime.substring(0,10);
		var endDate = endTime.substring(0,10);
		if(startDate != endDate){
			$pjq.messager.alert('提示', '开始时间和结束的时间必须在一天之内 !', 'info');
			return false;
		}
		
		if(!checkValidTime(startDate,endDate)){
			$pjq.messager.alert('提示', '开始时间不能大于结束的时间 !', 'info');
			return false;
		}
		return true;
		
	};
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = sy.contextPath + '/app/emp-jiaban!update.sy';
		} else {
			url = sy.contextPath + '/app/emp-jiaban!save.sy';
		}
		
		var unit= $(':input[name="data.unit"]').val();
		if(unit == '10108101'){
			//天 取日期 ， 小时 取时间 
			var startTime= $(':input[name="data.startTime"]').val();
			var newStartTime = Date.CreateDateTime(startTime);
			$(':input[name="data.startTime"]').val(newStartTime);
			
			var endTime= $(':input[name="data.endTime"]').val();
			var newEndTime = Date.CreateDateTime(endTime);
			$(':input[name="data.endTime"]').val(newEndTime);
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
			if(!checkIsJiabanValid($dialog, $grid, $pjq)){
				return ;
			}
			submitNow($dialog, $grid, $pjq);
		}
	};
	var backFlowForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/app/emp-jiaban!back.sy';
			} else {
				$pjq.messager.alert('提示', '找不到提交的数据!!', 'info');
				return;
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
		}
	};
	var checkedFlowForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/app/emp-jiaban!checked.sy';
			} else {
				$pjq.messager.alert('提示', '找不到提交的数据!!', 'info');
				return;
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
		}
	};
	var opresultFlowForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/app/emp-jiaban!opresult.sy';
			} else {
				$pjq.messager.alert('提示', '找不到提交的数据!!', 'info');
				return;
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
		}
	};
	var cancelFlowForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/app/emp-jiaban!cancel.sy';
			} else {
				$pjq.messager.alert('提示', '找不到取消的数据!!', 'info');
				return;
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
		}
	};
	var submitFlowForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = sy.contextPath + '/app/emp-jiaban!submit.sy';
			} else {
				$pjq.messager.alert('提示', '找不到提交的数据!!', 'info');
				return;
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
		}
	};
	 
	function clearForm() {
		$('#ff').form('clear');
	}
	var loadMeJiaBanDataToday = function(){
		{
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath+ '/app/medical-attend!getCurrentUserInfo.sy',{
				queryType : 'me-today'
			},
			function(result) {
				 
				if (result.id != undefined) {
					$('form').form('load',{
										 
										'data.syuser.id' : result.syuser ? result.syuser.id: '',
										'data.syuser.name' : result.syuser ? result.syuser.name: '',
										'data.yyyymmdd' : result.yyyymmdd,
										'data.ext1' : result.ext1,
										'data.startTime' : result.workTime,
										'data.endTime' : result.afterWorkTime,
										'data.reason' : result.reason,
										'data.opResult': result.opResult,
										'data.unit' : result.unit
					});
				 	
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	}
	
	$(function() {
		
		if ($(':input[name="data.id"]').val().length > 0) {
			 
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/emp-jiaban!getById.sy', {
				empJiabanId : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result != undefined && result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.opOrder' : result.opOrder,
						'data.syuser.id' : result.syuser.id,
						'data.syuser.name' : result.syuser.name,
						'data.type' : result.type,
						'data.subType' : result.subType,
						'data.startTime' : result.startTime,
						'data.endTime' : result.endTime,
						'data.requiredTime' : result.requiredTime,
						'data.yyyymmdd' : result.yyyymmdd,
						'data.ext1' : result.ext1,
						'data.reason' : result.reason,
						'data.opResult': result.opResult,
						'data.unit' : result.unit,
						'data.status' : result.status
					});
				 	
					if(result.status == '2'){
						//审批通过
						$('#table_result').css('display','block'); 
					}else{
						$('#table_result').css('display','none'); 
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}

	});
</script>
 
</head>
<body>
	<form id="ff" method="post" class="form">
		<fieldset>
			<legend>基本信息</legend>
			<table class="table" style="width: 100%;">

				<tr>
					<td colspan="4"><a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom',plain:true"
						onclick="loadMeJiaBanDataToday()">今日</a><a
						href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'ext-icon-zoom_out',plain:true"
						onclick="clearForm()">重置</a></td>
				</tr>
				<tr>

					<th>加班编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<td>流水号</td>
					<td><input name="data.opOrder" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>用户名:</td>
					<td><input class="easyui-validatebox" hidden="true"
						type="text" name="data.syuser.id" value="<%=userId%>"
						data-options="required:false"></input> <input
						class="easyui-validatebox" type="text" name="data.syuser.name"
						value="<%=userName%>" disabled="disabled"
						data-options="required:false"></input></td>
				</tr>
				
				<tr>
					<th>加班类型</th>
					<td><select id="data.subType" name="data.subType"
						class="easyui-combotree"
						data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=JIABAN_TYPE'"
						style="width: 155px;"></select></td>
					<th><!-- 类型 --></th>
					<td>
					<input class="easyui-validatebox" hidden="true"
						type="text" name="data.type" value="1"
						data-options="required:false"></input>
					<!-- <select class="easyui-combobox" name="data.type"
						data-options="panelHeight:'auto',editable:false"  
						data-options="required:true" style="width: 155px;">
							<option value="1">加班</option>
							<option value="2">请假</option>
					</select> -->
					</td>
				</tr>
				<tr>
					<th>开始时间</th>
					<td colspan="3"><input name="data.startTime" class="easyui-validatebox" value="<%=workTime!=null?workTime:""%>"
						data-options="required:true"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly" style="width: 150px;" /></td>
				</tr>
				<tr>
					<th>结束时间</th>
					<td colspan="3"><input name="data.endTime" class="easyui-validatebox"  value="<%=workTime!=null?afterWorkTime:""%>"
						data-options="required:true"
						onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						readonly="readonly" style="width: 150px;" /></td>
				</tr>
				<tr>
					<th>申请加班时间</th>
					<td><input name="data.requiredTime" class="easyui-numberbox" max="99999.99"
						data-options="required:true"   /></td>
					<th>单位</th>
					<td>
						<select id="data_unit" name="data.unit" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=SICK_UNIT'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data_unit').combotree('clear');" title="清空" />	
					</td>
				</tr>
				<tr>
					<th>日期:</th>
					<td colspan="3"><input class="easyui-validatebox" type="text"
						name="data.yyyymmdd" readonly="readonly"
						data-options="required:false"></input></td>
				</tr>
				<tr>
					 
					<th>备注</th>
					<td colspan="1"><textarea name="data.ext1" cols="100"></textarea></td>
					<th>状态</th>
					<td colspan="1">
						<select class="easyui-combobox" name="data.status" 
							data-options="panelHeight:'auto',editable:false"  
							data-options="required:true" style="width: 155px;">
								<option value="-1" >取消</option>
								<option value="0" >新建</option>
								<option value="1" >等待审批</option>
								<option value="2" >审批通过</option>
								<option value="3" >驳回</option>
								<option value="99" >删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>退回原因</th>
					<td colspan="3"><textarea name="data.reason" cols="100"></textarea></td>
				</tr>
			</table>
			<table id="table_result" class="table" style="width: 100%;display: none">
				<tr>
						<th>定性</th>
						<td><select class="easyui-combobox" name="data.opResult" 
							data-options="panelHeight:'auto',editable:false"  
							data-options="required:true" style="width: 155px;">
								<option value="0" >未使用</option>
								<option value="1" >已调休</option>
								<option value="2" >费用结算</option>
						</select></td>
					 	
					</tr>
			</table>
		</fieldset>
		<div style="text-align: left; padding: 5px">
			<input id="iconCls" class="ext-icon-bell" readonly="readonly"
				style="padding-left: 18px; width: 13px; border: none" />注意：加班只支持一天时间内
		</div> 
	</form>
</body>
</html>