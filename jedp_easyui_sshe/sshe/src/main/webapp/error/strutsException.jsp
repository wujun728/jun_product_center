<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>错误
</head>
<body>${exception.message}
	
<!--   异常堆栈信息(开发人员用)  -->
    <div style="display:block;">
        
 	</div>
    
</body>
</html>