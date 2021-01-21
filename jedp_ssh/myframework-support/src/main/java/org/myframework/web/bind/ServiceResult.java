package org.myframework.web.bind;


public class ServiceResult {

	/**
	 * 是否成功
	 */
	private boolean success;

	/**
	 * 错误类型
	 */
	private String errorType;

	/**
	 * 错误消息
	 */
	private String errorMessage;
	/**
	 * 结果的内容
	 */
	private Object content;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	
	public ServiceResult(){
		
	}
	public ServiceResult(Boolean isSuccess,String message){
		this.success=isSuccess==null?false:isSuccess;
		this.errorMessage=message;
	}
	@Override
	public String toString() {
		return "ServiceResult [success=" + success + ", errorType=" + errorType
				+ ", errorMessage=" + errorMessage + ", content=" + content
				+ "]";
	}

}
