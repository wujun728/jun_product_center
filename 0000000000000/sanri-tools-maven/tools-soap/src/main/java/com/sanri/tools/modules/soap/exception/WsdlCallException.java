package com.sanri.tools.modules.soap.exception;

import com.alibaba.fastjson.JSONObject;

public class WsdlCallException extends RuntimeException{
	private JSONObject exceptionJsonObject;
	private String xmlString;

	public void setExceptionJsonObject(JSONObject buildExceptionJsonObject) {
		this.exceptionJsonObject = buildExceptionJsonObject;
	}

	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
	
	
}
