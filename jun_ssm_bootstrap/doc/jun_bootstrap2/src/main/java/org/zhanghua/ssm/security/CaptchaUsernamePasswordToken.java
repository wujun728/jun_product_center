package org.zhanghua.ssm.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扩展UsernamePasswordToken
 * 
 * 增加验证码
 * 
 * @author Wujun
 * 
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4161911256857358437L;
	
	public static Logger logger=LoggerFactory.getLogger(CaptchaUsernamePasswordToken.class);

	// 验证码
	private String captcha;

	public CaptchaUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
