package org.zhanghua.ssm.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhanghua.ssm.common.utils.SpringContextHolder;

import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 扩展FormAuthenticationFilter
 * 
 * 增加验证码校验
 * 
 * @author Wujun
 * 
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

	public static Logger logger = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	// 创建 Token
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {

		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		return new CaptchaUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
	}

	// 验证码校验
	protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
		
		ImageCaptchaService imageCaptchaService = SpringContextHolder.getBean(ImageCaptchaService.class);
		
		if(!(imageCaptchaService.validateResponseForID(request.getSession().getId(), token.getCaptcha()).booleanValue())){
			logger.error("验证码错误！");
			throw new IncorrectCaptchaException("验证码错误！");
		}
	}

	// 认证
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);
		try {
			//验证码验证
			doCaptchaValidate((HttpServletRequest) request, token);
			Subject subject = getSubject(request, response);
			//登录验证
			subject.login(token);
			logger.info("登录成功:" + token.getUsername());
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			logger.error("登录失败:" + e.getMessage());
			e.printStackTrace();
			return onLoginFailure(token, e, request, response);
		}
	}

}
