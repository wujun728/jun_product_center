package com.du.lin.constant.state;

/**
 * 日志类型
 */
public enum LogType {
	LOGIN("登陆"),
	LOGIN_FAIL("登陆失败"),
	EXIT("退出"),
	BUSSINESS("业务日志"),
	EXCEPTION("异常日志");
	
	
	
	String message;

	
	
	private LogType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
