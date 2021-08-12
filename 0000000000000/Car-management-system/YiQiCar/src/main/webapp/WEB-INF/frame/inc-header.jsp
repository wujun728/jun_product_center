<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>好利来库存管理系统</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" media="all" />
</head>

<body>
<div id="header">
    <div class="logo-title">
       	<h1>好利来库存管理系统</h1>
    </div>
    <div class="logout user-icon">
      	欢迎登录，<span class="user-text">${user.loginname}</span> [<span class="signout-text"><a href="../quit" title="退出系统">退出系统</a></span>]
    </div>
</div>
</body>
</html>
