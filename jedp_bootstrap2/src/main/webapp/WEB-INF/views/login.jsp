<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/js/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/login.css" /> 
<script type="text/javascript" src="${ctx}/js/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/1.13.1/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/1.13.1/additional-methods.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/1.13.1/messages_zh.min.js"></script>
<script type="text/javascript">
$(function(){
	//改变验证码
	chgCaptcha = function(){
		$("#jcaptchaImage").attr('src', '${ctx}/images/jcaptcha.jpg?' + Math.floor(Math.random()*100) );
	};
	
	$("#loginForm").submit(function(e){
	    if($("#username").val() == 0){
	    	$("#loginError").html("请输入用户名");
	    	return false;
	    }
	    if($("#password").val() == 0){
	    	$("#loginError").html("请输入密码");
	    	return false;
	    }
	    if($("#captcha").val() == 0){
	    	$("#loginError").html("请输入验证码");
	    	return false;
	    }
	    loading();
	    
	});
	 
	
	/* $("#loginForm").validate({
		errorLabelContainer: "#messageBox",
		errorPlacement: function(error,element) {
			error.appendTo($("#loginError").parent());
		},
		rules: {
			username: {
				required: true
			},
			password: {
				required: true
			},
			captcha: {
				required: true
			}
			
		},
		messages: {
			username: {
				required: "请输入用户名"
			},
			password: {
				required: "请输入密码"
			},
			captcha: {
				required: "请输入验证码"
			}
		}
	}); */
});
</script>
</head>
<body>
<div class="logo">
  <div class="container">
      <a href="#">
      	<img alt="" src="${ctx}/images/logo.png">
      </a>
      <%-- <img alt="" src="${ctx}/images/l-icon.png"> --%>
  </div>
</div>
 <div class="login-wrap">
 	<div class="container">
 		<div class="login-box">
 			<form id="loginForm" class="form-horizontal" role="form" action="${ctx}/login" method="post">
				<!-- <div class="form-group">
					
				</div> -->
				<h3><i class="fa fa-sign-in fa-fw"></i>用户登录</h3>
				<div class="form-group">
				   	<div id="messageBox">
					   	<label id="loginError" class="error">${error}</label>
				   	</div>
				</div>
				<div class="form-group">
					<label class="sr-only">用户名</label>
				    <div class="input-group">
				      <div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>
				      <input type="text" class="form-control" id="username" name="username" value="${username}" placeholder="用户名/邮箱/手机号码" />
				    </div>
				</div>
				<div class="form-group">
					<label class="sr-only">密码</label>
				    <div class="input-group">
				      <div class="input-group-addon"><i class="fa fa-lock fa-fw"></i></div>
				      <input type="password" class="form-control" id="password" name="password" value="${password}" placeholder="密码" />
				    </div>
				</div>
				<div class="form-group">
					<label class="sr-only">验证码</label>
					<div class="row">
					<div class="col-xs-7">
						<div class="input-group">
				      	<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>
				      	<input type="text" class="form-control" id="captcha" name="captcha" value="${captcha}" placeholder="验证码" />
				    	</div>
					</div>
			     	<div class="col-xs-5">
			      		<a href="javascript:chgCaptcha();"><img id="jcaptchaImage" src="${ctx}/images/jcaptcha.jpg" onclick="chgCaptcha();"></a>
			        </div>
			        </div>
				</div>
				<div class="form-group">
					<div class="checkbox">
				      <label><input type="checkbox" id="rememberMe" name="rememberMe"> 7天内自动登录</label>
				    </div>
			    </div>
			    <div class="form-group">
			    	<button class="btn btn-primary btn-block" type="submit"><strong>登 录</strong></button>
			    </div>
			</form>
 		</div>
 	</div>
</div><!-- /login-wrap -->
<div class="footer">
 <div class="container">
	<!-- <div class="row footer-top">
        <div class="col-sm-6 col-lg-6">
          <h4>
            <img src="http://static.bootcss.com/www/assets/img/logo.png">
          </h4>
          <p>本网站所列开源项目的中文版文档全部由<a href="http://www.bootcss.com/">Bootstrap中文网</a>成员翻译整理，并全部遵循 <a href="http://creativecommons.org/licenses/by/3.0/" target="_blank">CC BY 3.0</a>协议发布。</p>
        </div>
        <div class="col-sm-6  col-lg-5 col-lg-offset-1">
          <div class="row about">
            <div class="col-xs-3">
              <h4>关于</h4>
              <ul class="list-unstyled">
                <li><a href="/about/">关于我们</a></li>
                <li><a href="/ad/">广告合作</a></li>
                <li><a href="/links/">友情链接</a></li>
                <li><a href="/hr/">招聘</a></li>
              </ul>
            </div>
            <div class="col-xs-3">
              <h4>联系方式</h4>
              <ul class="list-unstyled">
                <li><a href="http://weibo.com/bootcss" title="Bootstrap中文网官方微博" target="_blank">新浪微博</a></li>
                <li><a href="mailto:admin@bootcss.com">电子邮件</a></li>
              </ul>
            </div>
            <div class="col-xs-3">
              <h4>旗下网站</h4>
              <ul class="list-unstyled">
                <li><a href="http://www.golaravel.com/" target="_blank">Laravel中文网</a></li>
                <li><a href="http://www.ghostchina.com/" target="_blank">Ghost中国</a></li>
              </ul>
            </div>
            <div class="col-xs-3">
              <h4>赞助商</h4>
              <ul class="list-unstyled">
                <li><a href="http://www.ucloud.cn/" target="_blank">UCloud</a></li>
                <li><a href="https://www.upyun.com" target="_blank">又拍云</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div> -->
      <hr/>
	  <div class="row footer-bottom">
	        <ul class="list-inline text-center">
	          <li><a href="http://www.miibeian.gov.cn/" target="_blank">粤ICP备88888888号</a></li>
	          <li>&copy; 2015 Edward Copyright</li>
	        </ul>
	  </div> 
  </div>
</div>
</body>
</html>