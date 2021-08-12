package com.shuogesha.platform.action.admin;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.service.SiteService;
import com.shuogesha.platform.web.CmsUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/site/")
@Api(tags = "系统管理")
public class SiteAct {

	@GetMapping(value = "/config")
	@ApiOperation(value = "获取配置", notes = "获取配置")
	public @ResponseBody Object v_config(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws UnsupportedEncodingException {
		Site site = CmsUtils.getSite(request);
		return new JsonResult(ResultCode.SUCCESS, site);
	}

	@PostMapping(value = "/config_update")
	@ApiOperation(value = "更新配置", notes = "更新配置")
	public @ResponseBody Object o_config_update(@RequestBody Site bean, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
		Site site = CmsUtils.getSite(request);
		bean.setId(site.getId());
		siteService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@Autowired
	public SiteService siteService;
}
