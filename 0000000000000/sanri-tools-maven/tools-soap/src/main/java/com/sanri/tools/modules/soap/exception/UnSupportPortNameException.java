package com.sanri.tools.modules.soap.exception;

/**
 * 
 * 创建时间:2017-6-24上午8:42:10<br/>
 * 创建者:sanri<br/>
 * 功能:不支持的 portName<br/>
 */
public class UnSupportPortNameException extends RuntimeException{

	public UnSupportPortNameException(String message){
		super(message);
	}
}
