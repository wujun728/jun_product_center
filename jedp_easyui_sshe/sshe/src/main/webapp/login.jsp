<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>复医凯泽管理系统</title>
<jsp:include page="inc.jsp"></jsp:include>

<style type="text/css">
	body{margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #1B3142;}
	.header{width:100%;height:41px;background: url(<%=path%>/images/login-top-bg.gif) repeat-x;}
	.center{width:100%;height:532px;background: url(<%=path%>/images/login_bg.jpg) repeat-x;}
	.login_right{float:right;width:50%;height:100%;background: url(<%=path%>/images/login-wel.gif) bottom no-repeat;}
	.login_left{float:right;width:295px;height:100%;background: url(<%=path%>/images/login-content-bg.gif) no-repeat;}
	.login_title{margin-left:35px;font-family: Arial, Helvetica, sans-serif;font-size: 14px;height:36px;line-height: 36px;color: #666666;font-weight: bold;}
	.login_info{margin-left:35px;font-family: Arial, Helvetica, sans-serif;font-size: 12px;height:36px;line-height: 36px;color: #333333;}
	.login_input{width:150px;height:20px;margin-left:30px;border:1px solid #7F9DB9;vertical-align: middle;}
	.login_code{width:70px;height:20px;margin-left:30px;border:1px solid #7F9DB9;vertical-align: middle;}
	.btn{width:60px;height:25px;border-width:0px;background-image: url(<%=path%>/images/btn-bg2.gif);letter-spacing: 3px;margin-right:70px;cursor: pointer;}
	.login_info img{vertical-align: middle;cursor: pointer;}
	
	.errInfo{color:red;}
	
	.logo{width:100%;height:68px;background: url(<%=path%>/images/logo2.png) no-repeat;_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/logo2.png';)}
	.left_txt{font-family: Arial, Helvetica, sans-serif;font-size: 12px;line-height: 25px;color: #666666;}
	
	.bottom{width:100%;height:auto;text-align:center;font-family: Arial, Helvetica, sans-serif;font-size: 10px;color: #ABCAD3;text-decoration: none;line-height: 20px;}
</style>
<script language="JavaScript"> 
if (window != top) 
	top.location.href = location.href;
	
var loginFun = function() {
	var $form = $('#loginForm');//选中的tab里面的form
	if(!check()){
		return ;
	}
	console.log('开始登陆 !!!'  +  $form.length );
	if ($form.length == 1 && $form.form('validate')) {
	     
		//console.log($form.serialize())
		//$('#	').linkbutton('disable');
		var url = sy.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_login.sy';
		console.log("url:" + url);
		$.post(url , $form.serialize(), function(result) {
		if (result.success) {
			location.replace(sy.contextPath + '/index.jsp');
		} else {
			$.messager.alert('提示', result.msg, 'error', function() {
				$('#loginBtn').linkbutton('enable');
			});
		}
	   }, 'json');
	}
};


</script>
</head>

<body>
	<div
		style="width: 100%; height: 645px; position: absolute; top: 50%; left: 50%; margin-top: -320px; margin-left: -50%;">
		<div class="header"></div>
		<div class="center">
			<div class="login_right">
				<div style="width: 100%; height: auto; margin-top: 150px;">
					<form  method="post" id="loginForm"  >
						<!-- <input name="is_check" value="1" type="hidden" /> -->
						<div class="login_title">系统登陆区域</div>
						<div class="login_info">
							<label>用&nbsp&nbsp&nbsp户：</label><input type="text" name="data.loginname" value="zz"
								id="data.loginname" class="login_input" maxlength="20" /> &nbsp;<span
								id="nameerr" class="errInfo"></span>
						</div>
						<div class="login_info">
							<label>密&nbsp&nbsp&nbsp码：</label><input type="password" name="data.pwd" value="123456"
								id="data.pwd" class="login_input" maxlength="36" /> &nbsp;<span
								id="pass_worderr" name="pass_worderr" class="errInfo"></span>
						</div>
						<div class="login_info">
							<label>验证码：</label><input type="text" id="data.checkCode" name="data.checkCode"
								class="login_code" maxlength="4" />&nbsp;&nbsp; <a
								onclick="changeCode();"><img id="codeImg" alt="点击更换" style="height:40px"
								title="点击更换" src="" /></a> &nbsp;<span   name="codeerr"  class="errInfo"></span>
						</div>
						<div class="login_info">
						</div>
						<div class="login_info">
							<input type="button" name="loginBtn"  onclick="loginFun()" value="登录" class="btn" /> 
							<input type="reset" name="cancelBtn" value="取消" class="btn" />
						</div>
					</form>
				</div>
			</div>
			<div class="login_left">
				<div style="width: 100%; height: auto; margin-top: 150px;">
					<div class="logo"></div>
					<div class="left_txt">
						1.基础的医务管理系统<br /> 2.简单使用的考勤管理<br /> 3.灵活扩展性的医务管理平台<br /> 4.实时的沟通互动、协调、解答疑问<br />
					</div>
				</div>
			</div>
		</div>
		<div class="bottom">Copyright &copy; 2013 复医凯泽管理系统</div>
	</div>
	<script type="text/javascript">
		
		String.prototype.trim = function () {
			return this.replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
		}
		//页面初始化
		{
			//1.显示返回的错误信息
  			displayErrorInfo();
  			//2.获取验证码图片
	  		changeCode();
  		}	
  		
  		//重置错误提示信息
		function resetErr() {
  			$(':input[name="nameerr"]').html('');
  			$(':input[name="data.pwd"]').html('');
  			$(':input[name="codeerr"]').html('');
		}
		
		//表单验证
  		function check() {
  			resetErr();
  			var username = $(':input[name="data.loginname"]').val();
  			var password = $(':input[name="data.pwd"]').val();
  			var checkcode = $(':input[name="data.checkCode"]').val();
			
  			var nameerr = $(':input[name="nameerr"]').val();
  			var pass_worderr = $(':input[name="pass_worderr"]').val();
  			var codeerr = $(':input[name="codeerr"]').val();
  			 
  			if(username == undefined || username.trim() == "") {
  				$(':input[name="nameerr"]').html('用户名不能为空');
				$(':input[name="data.loginname"]').focus();
				return false;
  			}
  			
  			if(password.trim() == "") {
  				$(':input[name="pass_worderr"]').html('密码不能为空');
  				$(':input[name="data.pwd"]').focus();
  				return false;
  			}
  			
  			if(checkcode.trim() == "") {
  				$(':input[name="codeerr"]').html('验证码不能为空');
  				$(':input[name="codeerr"]').focus();
  				return false;
  			}
  			
  			return true;
  		}
  		
  		//显示返回的错误信息
  		function displayErrorInfo() {
  			var errInfo = '${errInfo}';
			if(errInfo == '用户名或密码有误！') {
				document.getElementById('nameerr').innerHTML = '用户名或密码有误！';
			} else if(errInfo == '验证码输入有误！') {
				document.getElementById('codeerr').innerHTML = '验证码输入有误！';
			}
  		}
	    
		//获取验证码图片
		function changeCode(){
			document.getElementById('codeImg').src = '<%=path%>/image!doNotNeedSessionAndSecurity_execute.sy?time='+ genTimestamp();
		}
		
		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}
	</script>

</body>
</html>