<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<%String version = "20131003";%>

<%
java.util.Map<String, Cookie> cookieMap = new java.util.HashMap<String, Cookie>();
Cookie[] cookies = request.getCookies();
if (null != cookies) {
	for (Cookie cookie : cookies) {
		cookieMap.put(cookie.getName(), cookie);
	}
}
String easyuiTheme = "bootstrap";//指定如果用户未选择样式，那么初始化一个默认样式
if (cookieMap.containsKey("easyuiTheme")) {
	Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
	easyuiTheme = cookie.getValue();
}
%>

<script type="text/javascript">
var sy = sy || {};
sy.contextPath = '<%=contextPath%>';
sy.basePath = '<%=basePath%>';
sy.version = '<%=version%>';
sy.pixel_0 = '<%=contextPath%>/style/images/pixel_0.gif';//0像素的背景，一般用于占位
</script>

<%-- 引入my97日期时间控件 --%>
<script type="text/javascript" src="<%=contextPath%>/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<%-- 引入ueditor控件 --%>
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = '<%=contextPath%>/jslib/ueditor1_2_6_1-utf8-jsp/';</script>
<script src="<%=contextPath%>/jslib/ueditor1_2_6_1-utf8-jsp/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=contextPath%>/jslib/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js" type="text/javascript" charset="utf-8"></script>

<%-- 引入jQuery --%>
<%
String User_Agent = request.getHeader("User-Agent");
if (User_Agent.indexOf("MSIE") > -1 && (User_Agent.indexOf("MSIE 6") > -1 || User_Agent.indexOf("MSIE 7") > -1 || User_Agent.indexOf("MSIE 8") > -1)) {
	out.println("<script src='" + contextPath + "/jslib/jquery-1.9.1.js' type='text/javascript' charset='utf-8'></script>");
} else {
	out.println("<script src='" + contextPath + "/jslib/jquery-2.0.3.js' type='text/javascript' charset='utf-8'></script>");
}
%>
<%-- 引入jquery扩展 --%>
<script src="<%=contextPath%>/jslib/syExtJquery.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>

<%-- 引入Highcharts --%>
<script src="<%=contextPath%>/jslib/Highcharts-3.0.6/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=contextPath%>/jslib/Highcharts-3.0.6/js/modules/exporting.js" type="text/javascript" charset="utf-8"></script>
<%-- 引入Highcharts扩展 --%>
<script src="<%=contextPath%>/jslib/syExtHighcharts.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>

<%-- 引入plupload --%>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>

<%-- 引入EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" href="<%=contextPath%>/jslib/jquery-easyui-1.3.4/themes/<%=easyuiTheme%>/easyui.css" type="text/css">
<!-- <link rel="stylesheet" href="<%=contextPath%>/jslib/jquery-easyui-1.3.4/themes/icon.css" type="text/css"> -->
<script type="text/javascript" src="<%=contextPath%>/jslib/jquery-easyui-1.3.4/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=contextPath%>/jslib/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<%-- 引入EasyUI Portal插件 --%>
<link rel="stylesheet" href="<%=contextPath%>/jslib/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<%-- 引入easyui扩展 --%>
<script src="<%=contextPath%>/jslib/syExtEasyUI.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>

<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="<%=contextPath%>/style/syExtIcon.css?version=<%=version%>" type="text/css">

<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=contextPath%>/style/syExtCss.css?version=<%=version%>" type="text/css">

<%-- 引入javascript扩展 --%>
<script src="<%=contextPath%>/jslib/syExtJavascript.js?version=<%=version%>" type="text/javascript" charset="utf-8"></script>