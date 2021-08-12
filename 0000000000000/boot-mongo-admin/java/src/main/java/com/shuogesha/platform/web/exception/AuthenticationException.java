package com.shuogesha.platform.web.exception;

/**
 * 登录异常
 */
@SuppressWarnings("serial")
public class AuthenticationException extends Exception {
	private Object extraInformation;

	public AuthenticationException() {

	}

	public AuthenticationException(String msg) {
		super(msg);
	}

	public AuthenticationException(String msg, Object extraInformation) {
		super(msg);
		this.extraInformation = extraInformation;
	}

	/**
	 * Any additional information about the exception. Generally a
	 * <code>UserDetails</code> object.
	 * 
	 * @return extra information or <code>null</code>
	 */
	public Object getExtraInformation() {
		return extraInformation;
	}

	public void clearExtraInformation() {
		this.extraInformation = null;
	}
}
