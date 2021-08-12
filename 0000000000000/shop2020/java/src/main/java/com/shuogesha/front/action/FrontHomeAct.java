package com.shuogesha.front.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;

@Controller
public class FrontHomeAct {

	@RequestMapping(value = "/index")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
 		return new JsonResult(ResultCode.SUCCESS, "index");
	}
 
}
