package com.shuogesha.app.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Collect;
import com.shuogesha.cms.service.CollectService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.service.SystemLogService;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;

@Controller
@RequestMapping("/app/")
public class ApiCollectAct {
	private static Logger log = LoggerFactory.getLogger(ApiCollectAct.class);

	/**
	 * 校验用户是否收藏
	 * 
	 * @param bean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "check_collect")
 	@AccessTokenRequired
	public @ResponseBody Object check_collect(Collect bean, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
 		bean.setName(bean.getRefer());
		bean.setUserId(ApiUtils.getUnifiedUserId(request));
		if (collectService.findByRefer(bean) != null) {// 已经点赞
			return new JsonResult(ResultCode.SUCCESS, false);
			
		} else {
			return new JsonResult(ResultCode.SUCCESS, true);
		} 
	} 

	@RequestMapping(value = "collect_save") 
	@AccessTokenRequired
	public @ResponseBody Object collect_save(Collect bean, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
 		if (StringUtils.isBlank(bean.getRefer())) { 
			return new JsonResult(ResultCode.FAIL, "参数错误",null);
		}
		bean.setName(bean.getRefer());
		bean.setUserId(ApiUtils.getUnifiedUserId(request));
		Collect collect = collectService.findByRefer(bean);
		if (collect != null) {// 判断是否重复收藏 
			collectService.removeById(collect.getId());
			return new JsonResult(ResultCode.SUCCESS, "已取消收藏",false);
		}
		bean.setDateline(RequestUtils.getNow());
		collectService.save(bean); 
		return new JsonResult(ResultCode.SUCCESS, "已取消收藏",true);
	}

	@RequestMapping(value = "collect_delete") 
	@AccessTokenRequired
	public @ResponseBody Object collect_delete(Long[] ids, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		Map<String, Object> map = new HashMap<>();
		collectService.removeByIds(ids); 
		return new JsonResult(ResultCode.SUCCESS, "删除成功",null);
	}
	
	@Autowired
	public CollectService collectService;
	@Autowired
	public SystemLogService systemLogService;
}
