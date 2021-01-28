<%@page import="org.zhanghua.ssm.common.utils.Exceptions"%>
<%@page import="org.slf4j.LoggerFactory"%>
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
<%
//获取异常类
Throwable ex = Exceptions.getThrowable(request);
if (ex != null){
	LoggerFactory.getLogger("500.jsp").error(ex.getMessage(), ex);
}
//编译错误信息
StringBuilder sb = new StringBuilder("错误信息：\n");
if (ex != null) {
	sb.append(Exceptions.getStackTraceAsString(ex));
} else {
	sb.append("未知错误.\n\n");
}
//out.print(sb.toString());
%>
<!-- header begin -->
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!-- header end -->
<!-- content begin -->
<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <!-- 菜单栏 -->
          <%@ include file="/WEB-INF/views/common/menu.jsp"%>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	<!-- 路径导航 -->
        	<div class="breadcrumbs">
			 <ol class="breadcrumb">
				<li class="active"><i class="glyphicon glyphicon-hand-right"></i> 500</li>
			 </ol>
			</div>
        	<div class="alert alert-danger alert-dismissible" role="alert">
			  <!-- <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
			  <h1>系统错误!</h1>
			  <p>请点击“查看详细信息”按钮，将详细错误信息发送给系统管理员，谢谢！</p>
			  <p>
				  <a href="javascript:" onclick="history.go(-1);"class="btn btn-default">返回上一页</a>
				  <a class="btn btn-danger" role="button" data-toggle="collapse" href="#detail" aria-expanded="false" aria-controls="detail">
				         查看详细信息
				  </a>
			  </p>
			  <p></p>
			  <div class="collapse" id="detail">
			  	<pre><% out.print(sb.toString()); %></pre>
			  </div>
			</div>
        </div><!-- main -->
      </div>
    </div>
</body> 
</html>