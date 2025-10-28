package com.jun.plugin.common.exception;


/**
 * 错误码统一接口 <br>
 * 
 * code：是错误码标识<br>
 * 
 * messageFormat：是错误消息的格式
 * 
 * @author klguang
 *
 */

public interface IErrorCode {
	

	public int getHttpStatus();
	
	public long getCode();
	
	public String getMessageFormat();

}
