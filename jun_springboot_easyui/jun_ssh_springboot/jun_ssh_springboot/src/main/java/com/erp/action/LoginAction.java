package com.erp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.Json;
import com.erp.service.LoginService;
import com.erp.shiro.CaptchaUsernamePasswordToken;
import com.erp.shiro.IncorrectCaptchaException;
//import com.google.code.kaptcha.Constants;
import com.erp.util.Constants;

import lombok.extern.slf4j.Slf4j;

//@Action(value = "systemAction", results = { @Result(name = Constants.LOGIN_SUCCESS_URL, location = "/index.jsp"),
//		@Result(name = Constants.LOGIN_URL,location = "/login.jsp"),
//		@Result(name = Constants.LOGIN_LOGIN_OUT_URL,type="redirect",location = "systemAction!loginInit.action")})
@Slf4j
@Controller
@RequestMapping("/system")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -6019556530071263499L;
	@Autowired
	private LoginService loginService;
	
	private String userName;
	private String password;
	private String captcha;
	private String userMacAddr;
	private String userKey;

	@ModelAttribute
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	@ModelAttribute
	public void setUserMacAddr(String userMacAddr) {
		this.userMacAddr = userMacAddr;
	}

	@ModelAttribute
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@ModelAttribute
	public void setUserName(String userName) {
		this.userName = userName;
	}
 
	@ModelAttribute
	public void setPassword(String password) {
		this.password = password;
	}

	@ResponseBody
	@RequestMapping(value = "/load")
	public String load() throws Exception {
		Subject subject = SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		token.setUsername(userName);
		token.setPassword(password.toCharArray());
		token.setCaptcha(captcha);
		token.setRememberMe(true);
		Json json = new Json();
		json.setTitle("登录提示");
		try {
			subject.login(token);
			System.out.println("sessionTimeout===>" + subject.getSession().getTimeout());
			json.setStatus(true);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			log.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			log.error(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException e) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		// token.clear();
		return null;
	}

	@RequestMapping(value = "/login")
	public ResponseEntity<Void> login(/* @RequestBody UserDto loginInfo, */HttpServletRequest request,
			HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		try {
			// 将用户请求参数封装后，直接提交给Shiro处理
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray());
			token.setRememberMe(true);
			subject.login(token);
			// Shiro认证通过后会将user信息放到subject内，生成token并返回
//            UserDto user = (UserDto) subject.getPrincipal();
//            String newToken = userService.generateJwtToken(user.getUsername());
//            response.setHeader("x-auth-token", newToken);

			return ResponseEntity.ok().build();
		} catch (AuthenticationException e) {
			// 如果校验失败，shiro会抛出异常，返回客户端失败
			log.error("User {} login fail, Reason:{}", userName, e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(value = "/logout2")
	public ResponseEntity<Void> logout2() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.getPrincipals() != null) {
//            UserDto user = (UserDto)subject.getPrincipals().getPrimaryPrincipal();
//            userService.deleteLoginInfo(user.getUsername());
		}
		SecurityUtils.getSubject().logout();
		return ResponseEntity.ok().build();
	}

	/**
	 * 函数功能说明 TODO:用户登出 Administrator修改者名字 2013-5-9修改日期 修改内容 @Title:
	 * logout @Description: @param @return @param @throws Exception 设定文件 @return
	 * String 返回类型 @throws
	 */

	@ResponseBody
	@RequestMapping(value = "/logout")
	public String logout() throws Exception {
		SecurityUtils.getSubject().logout();
		Json json = new Json();
		json.setStatus(true);
		OutputJson(json);
		return null;
	}

	/**
	 * 函数功能说明 TODO:查询用户所有权限菜单 Administrator修改者名字 2013-5-9修改日期 修改内容 @Title:
	 * findAllFunctionList @Description: @param @return @param @throws Exception
	 * 设定文件 @return String 返回类型 @throws
	 */

	@ResponseBody
	@RequestMapping(value = "/findAllFunctionList")
	public String findAllFunctionList() throws Exception {
		OutputJson(loginService.findMenuList());
		return null;
	}

}
