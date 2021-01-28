package com.sz.message.retun;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sz.message.IMessage;

public class ErrorReturnMessage implements IMessage {
	
	private String errorCode;

	private String errorCodeMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCodeMessage() {
		return errorCodeMessage;
	}

	public void setErrorCodeMessage(String errorCodeMessage) {
		this.errorCodeMessage = errorCodeMessage;
	}
	
	@JsonIgnore
	public String getTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
