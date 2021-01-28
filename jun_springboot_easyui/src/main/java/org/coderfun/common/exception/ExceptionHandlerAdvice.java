package org.coderfun.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import klg.j2ee.common.model.JsonData;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonData resolveException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
		// 日志记录异常message和stacktrace
		logger.error(exception.getMessage(), exception);

		JsonData jsonData = null;

		Exception ex = convertException(exception);

		if (ex instanceof AppException) {
			AppException bex = (AppException) ex;
			jsonData = JsonData.error(bex.getErrorCode().getCode(), bex.getMessage());
		} else {
			jsonData = JsonData.error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), ErrorCodeEnum.UNKNOWN_ERROR.getMessageFormat());
		}
		return jsonData;

	}

	/**
	 * 转换Exception ，便于统一返回错误
	 * 
	 * @param ex
	 * @return
	 */

	protected Exception convertException(Exception ex) {
		if (ex instanceof DuplicateKeyException)
			return new AppException(ErrorCodeEnum.DATA_EXISTED);
		if (ex instanceof EmptyResultDataAccessException)
			return new AppException(ErrorCodeEnum.DATA_NOTEXIST);
		if (ex instanceof DataIntegrityViolationException)
			return new AppException(ErrorCodeEnum.DATA_INTEGRITY_ERROR);
		if (ex instanceof IllegalArgumentException || ex instanceof BindException) {
			return new AppException(ErrorCodeEnum.BADPARAM);
		}
		if(ex instanceof InvalidDataAccessResourceUsageException){
			return new AppException(ErrorCodeEnum.DEMO_NOT_UPDATE);
		}
		return ex;
	}
}