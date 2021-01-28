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
var userId = '<%=sessionInfo.getUserId()%>';

	var gotoChatFun = function(id) {
	//parent.$.messager.confirm('询问', '您确定要登陆到聊天服务器吗？'  , function(r) {
		//if (r)
		{
			$.post(sy.contextPath + '/base/syuser!gotoChatIM.sy', {
				id : id
			}, function(result) {
				if (result.success && result.obj != null) {
					// 取得要提交的参数
				    // 取得要提交页面的URL
				    var action = "http://localhost:8081/sshe_chat/adapter/login";
				    // 创建Form
				   // var form = $('<form></form>');
				    var form =  $('#showDataForm');
			        
				    // 设置属性
				    form.attr('action', action);
				    form.attr('method', 'post');
				    // form的target属性决定form在哪个页面提交
				    // _self -> 当前页面 _blank -> 新页面
				    form.attr('target', "actionframe");
				    // 创建Input
				    var my_input_name =$(':input[name="name"]');
				    my_input_name.attr('value', result.obj.name);
				    // 附加到Form
				    //form.append(my_input_name); 
				    
				    var my_input_server =$(':input[name="server"]');
				    my_input_server.attr('value', result.obj.server);
				    // 附加到Form
				    //form.append(my_input_server); 
				    
				    var my_input_port = $(':input[name="port"]');
				    my_input_port.attr('value', result.obj.port);
				    // 附加到Form
				    //form.append(my_input_port); 
				    
				    var my_input_password = $(':input[name="password"]');
				    my_input_password.attr('value', result.obj.pass);
				    
				    // 附加到Form
				    //form.append(my_input_password); 
				   // console.log(form)
				    // 提交表单
				   // form.submit(); 
				    console.log('-------------------')
				    console.log(form);
				   form.submit(); 
				    
				  // window.open("http://localhost:8081/sshe_chat/");
				} else {
					$.messager.alert('提示', result.msg, 'error', function() {
					});
				}
			   }, 'json');
		}
	//});
};


var addFun = function(id,name) {
	gotoChatFun(userId);
	
	var dialog = parent.sy.modalDialog({
		title : '咨询',
		url : 'http://localhost:8081/sshe_chat/' 
	});
};
	 
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<div class="easyui-panel" title="" style="width: 800px;">
		<div style="padding: 10px 0 10px 60px">
			<table>
				<tr>
						<div class="easyui-panel" title="在线咨询" style="width: 400px;">
							<div style="padding: 1px 0 10px 60px">
								<div style="text-align: center; padding: 15px">
							 	 <a href="javascript:void(0)" class="easyui-linkbutton"
									onclick="addFun()">进入咨询</a> 
							</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<iframe width="0" height="0" name="actionframe" hidden="true"></iframe>
		<div data-options="region:'center',fit:true,border:false"
		style="visibility: hidden">
		<form id="showDataForm" action=""
			method="post" target="actionframe">
			<input type="text" name="name" /> <input type="text" name="server" />
			<input type="text" name="port" /> <input type="text" name="password" />
		</form>
	</div>
	</div>

</body>
</html>