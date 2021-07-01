package com.pms.soft.exception;

import java.util.List;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pms.soft.util.CustomExceptionMsg;
import com.pms.soft.util.ResponseObject;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

	/**
	 * 未知异常拦截处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public CustomExceptionMsg handleException(Exception e) {
		e.printStackTrace();
		log.info(e.getMessage());
		return new CustomExceptionMsg(ResponseObject.status_500, "系统错误，请联系管理员！");
	}
	
	/**
	 * 空指针异常拦截处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public CustomExceptionMsg nullException(Exception e) {
		e.printStackTrace();
		log.info(e.getMessage());
		return new CustomExceptionMsg(ResponseObject.status_514, "参数不能为空！");
	}
	
	/**
	 * 拦截参数验证错误，在controller方法不能加参数BindingResult。如果加该参数，错误请求不会到这个异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public CustomExceptionMsg validateErrorHandler(BindException e) {
		BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> errorList = bindingResult.getFieldErrors();
            String errorMsg = errorList.get(0).getField() + " 字段错误，错误原因:" + errorList.get(0).getDefaultMessage();
            return new CustomExceptionMsg(ResponseObject.status_512, errorMsg);
        } else {
        	return new CustomExceptionMsg(ResponseObject.status_512, "参数不能为空！");
        }
	}
	
	
}
