package com.du.lin.constant.state;

/**
 * 日志状态
 */
public enum LogState {
	SUCCESS("成功"),
	FAIL("失败");
	String message;

	
	private LogState(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
