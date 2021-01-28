<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>jsonp测试</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : "post",
			async : true,
			url : "http://localhost:9999/sshe/test/5.js",
			dataType : "jsonp",
			jsonp : "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
			jsonpCallback : "flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名，也可以写"?"，jQuery会自动为你处理数据
			success : function(json) {
				alert('您查询到航班信息：票价： ' + json.price + ' 元，余票： ' + json.tickets + ' 张。');
			},
			error : function() {
				alert('fail');
			}
		});
	});
</script>
</head>
<body>
</body>
</html>