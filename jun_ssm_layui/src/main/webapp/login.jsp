<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>登录界面</title>
	<base href="<%=basePath%>"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="favicon.ico">
	<link rel="stylesheet" href="resource/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="resource/css/public.css" media="all" />
</head>
<body class="loginBody">
	<form class="layui-form" action="sys/login.page">
		<div class="login_face"><img src="resource/images/face.jpg" class="userAvatar"></div>
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label>
			<input type="text" placeholder="请输入用户名" value="Admin" autocomplete="off" name="userName" id="userName" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input type="password" placeholder="请输入密码" value="12345678" autocomplete="off" name="password" id="password" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item" id="imgCode">
			<label for="code">验证码</label>
			<input type="text" placeholder="请输入验证码" autocomplete="off" name="code" id="code" class="layui-input">
			<img src="sys/captcha.jpg" />
		</div>
		<div class="layui-form-item">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
		</div>
	</form>
	<script type="text/javascript" src="resource/layui/layui.js"></script>
	<script type="text/javascript" src="resource/js/login.js"></script>
	<script type="text/javascript" src="resource/js/cache.js"></script>
</body>
</html>