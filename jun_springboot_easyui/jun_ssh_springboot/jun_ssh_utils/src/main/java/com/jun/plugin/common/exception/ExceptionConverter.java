package com.jun.plugin.common.exception;


/**
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2018年11月11日
 */

public interface ExceptionConverter {
	/**
	 * 转换Exception ，便于统一返回错误
	 * 
	 * @param ex
	 * @return
	 */

	public AppException convertException(Exception ex);
	
}
