<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%@ page import="sy.util.base.ConfigUtil"%>
<%
	//考勤管理
	String contextPath = request.getContextPath();
	//SecurityUtil securityUtil = new SecurityUtil(session);

	SessionInfo sessionInfo = (SessionInfo) request.getSession()
			.getAttribute(ConfigUtil.getSessionInfoName());
	String userId = sessionInfo.getUserId();
	String userName = sessionInfo.getUser().getName();
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	
		
	var listMyRecord_CC = function() {
		var url = sy.contextPath + '/securityJsp/app/MedicalAttendList.jsp';
		window.location.href=url;
		
	};
	var bandIP = function(){
		var url = sy.contextPath + '/app/medical-attend!bandIP.sy';
		$.post(url,null, function(result) {
			if (result.success) {
				parent.$.messager.alert('提示', result.msg, 'info');
			} else {
				parent.$.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	
	var submitForm_CC = function($op) {
		//console.log('dddd') 
		
		if ($('form').form('validate')) {
			var url;

			$(':input[name="queryType"]').val($op);
			console.log($(':input[name="queryType"]').val())

			if ($(':input[name="data.id"]').val() > 0) {
				url = sy.contextPath + '/app/medical-attend!update.sy';
			} else {
				url = sy.contextPath + '/app/medical-attend!save.sy';
			}
			//console.log(url)
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					//$grid.treegrid('reload');
					//$dialog.dialog('destroy');
					//console.log('success 上班时间: ');
					// console.log(result.obj.sbTime);
					if (result.obj != null)
						$('#workTime').html(result.obj.sbTime=="undefined"? "" : result.obj.sbTime);
					if (result.obj != null)
						$('#afterWorkTime').html(result.obj.xbTime);
					/* if (result.obj != null)
						$('#isLeave').html(
								result.obj.isLeave == 0 ? '没有请假' : '今天请假了'); */
					if (result.obj != null)
						$(':input[name="data.id"]').val(result.obj.id);

					parent.$.messager.alert('提示', result.msg, 'info');
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('fail');
					$('#workTime').html('');
					$('#afterWorkTime').html('');
					/* $('#isLeave').html(''); */
				}
			}, 'json');
		}
	};

	$(function() {

		if ($(':input[name="data.syuser.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			
			$.post(sy.contextPath+ '/app/medical-attend!getCurrentUserInfo.sy',{
								queryType : 'me-today'
							},
							function(result) {
								//alert(result.iCONCLS)
								if (result.id != undefined) {
									$('form').form('load',{
														'data.id' : result.id,
														'data.syuser.id' : result.syuser ? result.syuser.id: '',
														'data.syuser.name' : result.syuser ? result.syuser.name: '',
														'data.yyyymmdd' : result.yyyymmdd,
														'data.ext1' : result.ext1
									});
									$('#workTime').html('' + result.workTime==undefined?"":result.workTime );
									//console.log(result.afterWorkTime);
									$('#afterWorkTime').html('' + result.afterWorkTime ==undefined?"":result.afterWorkTime);
									/* $('#isLeave').html(result.isLeave); */
									//$('#isLeave').html(result.isLeave == 0 ? '没有请假': '今天请假了');
									$('#iCONCLS').attr('class', result.iCONCLS);//设置背景图标
								}
								parent.$.messager.progress('close');
							}, 'json');
		}
		
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<div class="easyui-panel" title="考勤打卡" style="width: 800px;">
		<div style="padding: 10px 0 10px 60px">
			<table>
				<!-- <tr>
					<td><h2>员工考勤数据填写</h2></td>
					<td></td>
				</tr> -->
				<tr>
					<td><div class="easyui-calendar"
							style="width: 180px; height: 180px;"></div></td>
					<td>
						<div class="easyui-panel" title="考勤打卡" style="width: 400px;">
							<div style="padding: 10px 0 10px 60px">
								<form id="form" method="post">

									<input class="easyui-validatebox" type="text" name="queryType"
										value="" data-options="required:false"  hidden="true" readonly="readonly"></input>
									<table>
										<tr>
											<td></td>
										</tr>
										<tr>
											<td><!-- 编号: --></td>
											<td><input class="easyui-validatebox" type="text"
												name="data.id" hidden="true" data-options="required:false"
												readonly="readonly"></input></td>
										</tr>
										<tr>
											<td>用户名:</td>
											<td><input class="easyui-validatebox" hidden="true" type="text"
												name="data.syuser.id" value="<%=userId%>"
												data-options="required:false"></input> <input
												class="easyui-validatebox" type="text"
												name="data.syuser.name" value="<%=userName%>"
												disabled="disabled" data-options="required:false"></input></td>
										</tr>
										<tr>
											<td>日期:</td>
											<td><input class="easyui-validatebox" type="text"
												name="data.yyyymmdd" readonly="readonly" data-options="required:false"></input></td>
										</tr>
										<tr>
											<td>备注:</td>
											<td><textarea name="data.ext1" style="height: 60px;"></textarea></td>
										</tr>
									</table>
								</form>
								<div id="p" class="easyui-panel"
									style="width: 300px; height: 100px; padding: 10px;">
									<p style="font-size: 14px"></p>
									<ul>
										<li>上班时间:<label id="workTime" name="workTime" />
										</li>
										<li>下班时间:<label id="afterWorkTime" name="afterWorkTime" />
										</li>
										<!-- <li>是否请假:<label id="isLeave" name="isLeave" />
										</li>
										 -->
									</ul>
								</div>
							</div>
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-note_add',plain:true"
									onclick="listMyRecord_CC();">查询我的考勤</a> <a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_CC('sb')">上班打卡</a> <a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_CC('xb')">下班打卡</a>
									
									<a href="javascript:void(0)" class="easyui-linkbutton"
									onclick="bandIP()">绑定ip地址</a>
									
									<!--  <a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_CC('qj')">请假</a> -->
									 
								<!-- <a href="javascript:void(0)"
									class="easyui-linkbutton" onclick="clearForm()">Clear</a> -->
							</div>
						</div>
					</td>
				</tr>
			</table>

		</div>
	</div>


	<script>
		function submitForm() {
			$('#ff').form('submit');
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
	<%-- <div id="toolbar" style="display: block;">
		<table>
		<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>考勤名称</td>
								<td><input name="QUERY_t#deptName_S_LK" style="width: 180px;" /></td>
								<td>考勤地址</td>
								<td><input name="QUERY_t#deptAddr_S_LK" style="width: 180px;" /></td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.treegrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<%if (securityUtil.havePermission("/app/cust-dept!save")) {%>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<%}%>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	  --%>
	
	<!-- <div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div> -->
</body>
</html>