package com.shuogesha.app.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.app.version.AccessToken;
import com.shuogesha.cms.entity.Ad;
import com.shuogesha.cms.entity.Nav;
import com.shuogesha.cms.service.AdService;
import com.shuogesha.cms.service.NavService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;

@Controller
@RequestMapping("/app/")
public class ApiHomeAct {
	private static Logger log = LoggerFactory.getLogger(ApiHomeAct.class);
 
	@RequestMapping(value = "get_home")
	@AccessToken
	public @ResponseBody Object get_home(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		List<Nav> navList=navService.findList();
		json.put("navList", navList);
		List<Ad> adList = adService.findList(1); 
		json.put("adList", adList); 
		return new JsonResult(ResultCode.SUCCESS, json);
	}

	@Autowired
	private NavService navService;
	@Autowired
	private AdService adService;

}
