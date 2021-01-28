<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<%
//设置返回码200，避免浏览器自带的错误页面
response.setStatus(200);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>
<body>
<!-- header begin -->
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!-- header end -->
<!-- content begin -->
<div class="jumbotron">
 <h1>403，权限不足!</h1>
 <div>
	<p>您访问的页面没有权限...</p>
	<a href="javascript:" onclick="history.go(-1);"class="btn btn-default">返回上一页</a>
 </div>
</div>
</div>
</body> 
</html>