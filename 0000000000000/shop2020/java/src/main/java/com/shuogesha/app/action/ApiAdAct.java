package com.shuogesha.app.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessToken;
import com.shuogesha.cms.entity.Ad;
import com.shuogesha.cms.service.AdService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;

@Controller
@RequestMapping("/app/")
public class ApiAdAct {
	private static Logger log = LoggerFactory.getLogger(ApiAdAct.class);

	/**
	 * 广告banner获取
	 * 
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "get_ad_list")
	@AccessToken
	public @ResponseBody Object ad_list(Integer type, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
 		if (type == null || type <= 0) { 
			return new JsonResult(ResultCode.PARAMS_ERROR,  "参数错误",null);
		}
		List<Ad> list = adService.findList(type); 
		return new JsonResult(ResultCode.SUCCESS,  list);
	}

	@Autowired
	private AdService adService;

}
