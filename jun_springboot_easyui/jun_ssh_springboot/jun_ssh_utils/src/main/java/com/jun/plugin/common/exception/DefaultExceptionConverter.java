package com.jun.plugin.common.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindException;


/**
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2018年11月11日
 */
public class DefaultExceptionConverter implements ExceptionConverter{

	/**
	 * 转换Exception ，便于统一返回错误
	 * 
	 * @param ex
	 * @return
	 */

	public AppException convertException(Exception ex) {
		if (ex instanceof DuplicateKeyException){
			return new AppException(ErrorCodeEnum.DATA_EXISTED); 			
		}
		if (ex instanceof EmptyResultDataAccessException)
			return new AppException(ErrorCodeEnum.DATA_NOTEXIST);
		if (ex instanceof DataIntegrityViolationException)
			return new AppException(ErrorCodeEnum.DATA_INTEGRITY_ERROR);
		if (ex instanceof IllegalArgumentException || ex instanceof BindException) {
			return new AppException(ErrorCodeEnum.BADPARAM);
		}
		return null;
	}
	
}
