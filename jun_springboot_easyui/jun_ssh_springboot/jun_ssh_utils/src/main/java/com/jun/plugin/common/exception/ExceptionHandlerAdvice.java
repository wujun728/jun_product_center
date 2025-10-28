package com.jun.plugin.common.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.jun.plugin.common.model.JsonData;

/**
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2018年11月11日
 */

@ControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@Resource(name = "exceptionConvertHandler")
	ExceptionConvertHandler exceptionConvertHandler;

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<JsonData> resolveException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
		// 日志记录异常message和stacktrace
		logger.info("ControllerAdvice: 全局异常处理: 捕获到异常\r\n{}", exception.getMessage());

		JsonData jsonData = null;

		AppException appException = exceptionConvertHandler.executeExceptionConvert(exception);
		
		logger.info("ControllerAdvice: 全局异常处理: ResponseEntity，设置HttpStatus以及error响应类容");
		//如果转换处理为空，说明未知错误！
		if (null != appException) {
			appException.printStackTrace();
			jsonData = JsonData.error(appException.getErrorCode().getCode(), appException.getMessage());
			return new ResponseEntity<>(jsonData, HttpStatus.valueOf(appException.getErrorCode().getHttpStatus())); 
		} else {
			logger.error("未知异常，无匹配的ExceptionConverter！", exception);
			jsonData = JsonData.error(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), ErrorCodeEnum.UNKNOWN_ERROR.getMessageFormat());
			return new ResponseEntity<>(jsonData, HttpStatus.valueOf(ErrorCodeEnum.UNKNOWN_ERROR.getHttpStatus())); 
		}
	}


}