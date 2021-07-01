package com.pms.soft.util;

/**
 * 请求的返回结果。 如果:success=true resultObject=处理结果； 如果:success=false errorMessage=错误信息
 * 
 *
 */
public class ResponseObject {

	public static final Integer status_400 = 400; // 业务异常
	public static final Integer status_511 = 511; // 业务错误-未登录
	public static final Integer status_512 = 512; // 业务错误-校验失败
	public static final Integer status_513 = 513; // 业务错误-手机短信验证码发送失败
	public static final Integer status_200 = 200; // 正常请求
	public static final Integer status_514 = 514; // 业务错误-其他
	public static final Integer status_500 = 500; // 系统未知异常

	private Integer status = status_200; // 正常请求

	private String errorMessage;

	private Object resultObject;

	public static ResponseObject newErrorResponseObject(Integer status, String errorMessage) {
		ResponseObject res = new ResponseObject();
		res.setStatus(status);
		res.setErrorMessage(errorMessage);
		return res;
	}

	public static ResponseObject newSuccessResponseObject(Object resultObject) {
		ResponseObject res = new ResponseObject();
		res.setResultObject(resultObject);
		return res;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
