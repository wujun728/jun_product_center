package com.jun.plugin.common.exception;



public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 错误码 **/
	private IErrorCode errorCode;
	/**
	 * 消息参数
	 */
	private Object[] messageArgs;

	private String message;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub

		if (null != message){
			return message;			
		}else if(null != messageArgs){
			return String.format(errorCode.getMessageFormat(), messageArgs);			
		}else{
			return errorCode.getMessageFormat();
		}
	}

	public AppException(IErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public AppException(IErrorCode errorCode, String message) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public AppException(IErrorCode errorCode, Object[] messageArgs) {
		super();
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	public IErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(IErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
