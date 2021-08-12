<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>一汽轿车核心业务平台</title>
<style>
html,body { overflow:hidden;}
</style>
<link href="css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="js/jquery.onlyforindex.js" type="text/javascript"></script>
</head>

<body>
<script color="255,0,0" pointColor="255,0,0" opacity='0.7' zIndex="-2" count="200" src="${pageContext.request.contextPath }/js/canvas-nest.min.js" type="text/javascript"></script>

<div id="header-wrap">
	<iframe allowtransparency="true" frameborder="0" id="header-box" scrolling="no" src="frame/inc-header.html"></iframe>
</div>
<div id="main-wrap" style="opacity:0.8">
	<div id="main-nav">
    	<iframe frameborder="0" id="siderbar-box" scrolling="no" src="frame/inc-nav"></iframe>
    </div>
    <div id="main-content">
        <table border="0" cellpadding="0" cellspacing="0" id="main-content-box">
            <tr>
                <td class="toggle"></td>
                <td class="content-wrap"><iframe frameborder="0" id="content-box" src="frame/welcome.html" scrolling="auto"></iframe></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>