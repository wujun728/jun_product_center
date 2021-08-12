package com.shuogesha.platform.action.admin;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.web.CmsUtils;

@Controller
@RequestMapping("/api/site/")
public class SiteAct {
	 
	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Site bean = siteService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	} 

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody Site bean) {
		siteService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}
	
	@RequestMapping(value = "/config")
	public @ResponseBody Object v_config(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		Site site =CmsUtils.getSite(request);
		return new JsonResult(ResultCode.SUCCESS, site);
	}
	
	@RequestMapping(value = "/config_update", method = RequestMethod.POST)
	public @ResponseBody Object o_config_update(@RequestBody Site bean,HttpServletRequest request,
			HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		Site site =CmsUtils.getSite(request);
		bean.setId(site.getId());
		siteService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}
	
	@Autowired
	public SiteService siteService;
}
