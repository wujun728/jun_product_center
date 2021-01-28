package org.zhanghua.ssm.web.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zhanghua.ssm.security.IncorrectCaptchaException;

/**
 *  登录
 * @author Wujun
 *
 */
@Controller
public class LoginController {

	/**
	 * 真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		return "login";
	}

	/**
	 * 真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, String captcha, Model model) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		// 获取登录错误信息
		String authExp = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String error = null;
		if (IncorrectCaptchaException.class.getName().equals(authExp)) {
			error = "验证码错误，请重试";
		} else if (LockedAccountException.class.getName().equals(authExp)) {
			error = "用户帐号被锁定，请重试";
		} else {
			error = "用户名或密码错误, 请重试";
		}
		model.addAttribute("error", error);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		/*model.addAttribute("captcha", captcha);*/
		return "login";
	}
}
