package com.shuogesha.front.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.shuogesha.cms.service.CountCacheService;
import com.shuogesha.cms.service.CountService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.util.ResponseUtils;
/**
 * 浏览量统计
 * @author zhaohaiyuan
 *
 */
@Controller
 public class FrontCountAct{
	private static Logger log = LoggerFactory.getLogger(FrontCountAct.class);
	
	@RequestMapping(value = "/ajax/count_view")
 	public @ResponseBody Object view(Long id, HttpServletRequest request,
			HttpServletResponse response) throws JSONException, ParseException {
		if (id==null||id<=0) {
			ResponseUtils.renderJson(response, "[]");
			return new JsonResult(ResultCode.SUCCESS, new JSONArray());
		}
		int[] counts = countCacheService.viewAndGet(id);
 		if (counts != null) { 
			return new JsonResult(ResultCode.SUCCESS, counts);
		} else {
			return new JsonResult(ResultCode.SUCCESS, new JSONArray());
		}
	}
	 
	
	
	@Autowired
	public CountCacheService countCacheService;
	@Autowired
	public CountService countService;
	
}
