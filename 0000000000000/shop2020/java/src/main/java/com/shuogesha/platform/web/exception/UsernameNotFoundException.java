package com.shuogesha.platform.web.exception;

/**
 * 用户名没有找到异常
 */
@SuppressWarnings("serial")
public class UsernameNotFoundException extends AuthenticationException {
	public UsernameNotFoundException() {
	}

	public UsernameNotFoundException(String msg) {
		super(msg);
	}
}