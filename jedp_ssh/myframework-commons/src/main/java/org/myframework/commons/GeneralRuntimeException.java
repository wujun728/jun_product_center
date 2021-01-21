package org.myframework.commons;

/**
 * 
 * 该基类定义为RuntimeException类型，非检查型异常，
 * 在程序中可能出现异常的地方，程序员应主动地捕获并处理。
 * 
 */
@SuppressWarnings("serial")
public abstract class GeneralRuntimeException extends RuntimeException {
	/**
	 * 国际化使用的错误代码
	 */
	protected String errorCode;
	
	/**
	 * 国际化使用的错误代码中的占位符信息
	 */
	protected String[] nativeMessage;
	

	public GeneralRuntimeException() {
		super();
	}
	
	public GeneralRuntimeException(String errorCode, String[] nativeMessage) {
		super();
		this.errorCode = errorCode;
		this.nativeMessage = nativeMessage;
	}

	public GeneralRuntimeException(String message) {
		super(message);
	}

	public GeneralRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneralRuntimeException(Throwable cause) {
		super(cause);
	}

	public GeneralRuntimeException(Throwable cause, String errorCode, String[] nativeMessage) {
		super(cause);
		this.errorCode = errorCode;
		this.nativeMessage = nativeMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String[] getNativeMessage() {
		return nativeMessage;
	}

	public void setNativeMessage(String[] nativeMessage) {
		this.nativeMessage = nativeMessage;
	}
}
