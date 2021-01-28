<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>
</head>
<body>
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
				<li class="active"><i class="glyphicon glyphicon-hand-right"></i> 我的主页</li>
			 </ol>
			</div>
			<!-- 主页内容 -->
			<h2>我的主页</h2>
        </div><!-- main -->
      </div>
    </div>
</body> 
</html>