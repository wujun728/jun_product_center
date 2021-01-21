<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%@ page import="sy.util.base.ConfigUtil"%>
<%
	//知识
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
var sshe_chat_webroot = '<%=ConfigUtil.get("sshe_chat_webroot")%>';
var addFun = function(id,name) {
 		
	var dialog = parent.sy.modalDialog({
		title : '咨询',
		url : sshe_chat_webroot 
	});
};
	 
</script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false" title="知识管理">
		 <iframe width="99%" height="97%" name="actionframe"   src="http://www.discuz.net/thread-3154987-1-1.html"></iframe>
	</div>
	 
</body>
</html>