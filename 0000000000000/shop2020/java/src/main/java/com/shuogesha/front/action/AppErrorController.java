package com.shuogesha.front.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
/**
 * 自定义错误返回
 * @author zhaohaiyuan
 *
 */
@Controller
public class AppErrorController implements ErrorController {
	private static Logger log = LoggerFactory.getLogger(AppErrorController.class);

	private final static String ERROR_PATH = "/error";

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public Object error(HttpServletRequest request) { 
		return new JsonResult(ResultCode.UNKNOWN_ERROR,ResultCode.UNKNOWN_ERROR.msg(), null);
	}

}
