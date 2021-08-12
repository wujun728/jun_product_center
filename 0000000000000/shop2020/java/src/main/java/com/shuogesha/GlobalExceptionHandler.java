package com.shuogesha;

 
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.action.ApiAdAct;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;

/**
 * 全局异常处理
 * 
 * @author zhaohaiyuan
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static Logger log = LoggerFactory.getLogger(ApiAdAct.class);

 
 
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public JsonResult javaExceptionHandler(Exception ex) {
		log.error(ex.getMessage());
		return new JsonResult(ResultCode.SYS_ERROR,ResultCode.SYS_ERROR.msg(), ex.getMessage());
	}

	 
}
