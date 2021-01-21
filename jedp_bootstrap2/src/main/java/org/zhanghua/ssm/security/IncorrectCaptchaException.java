package org.zhanghua.ssm.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 扩展验证码异常
 * @author Wujun
 *
 */
public class IncorrectCaptchaException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6815990585156198584L;

	public IncorrectCaptchaException() {
		super();
	}

	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCaptchaException(String message) {
		super(message);
	}

	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
	}

}
