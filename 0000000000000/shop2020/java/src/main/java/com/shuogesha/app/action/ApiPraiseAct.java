package com.shuogesha.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Praise;
import com.shuogesha.cms.service.PraiseService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.util.RequestUtils;
/**
 * 喜欢
 * @author zhaohaiyuan
 *
 */
@Controller
@RequestMapping("/app/")
public class ApiPraiseAct{
	private static Logger log = LoggerFactory.getLogger(ApiPraiseAct.class); 
	
	@RequestMapping(value = "save_praise", method = RequestMethod.POST) 
	@AccessTokenRequired
	public @ResponseBody Object save_praise(Praise bean,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
 		bean.setDateline(RequestUtils.getNow()); 
 		bean.setUserId(ApiUtils.getUnifiedUserId(request));
		Praise praise=praiseService.findByRefer(bean);
 		if(praise!=null){//已经点赞 
			praiseService.removeById(praise.getId());
			return new JsonResult(ResultCode.SUCCESS,false);
		}else { 
	 		praiseService.save(bean); 
	 		return new JsonResult(ResultCode.SUCCESS,true);
		}   
	}
	/**
	 * 校验用户是否点赞
	 * @param bean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "check_praise", method = RequestMethod.POST) 
	@AccessTokenRequired
	public @ResponseBody Object check_praise(Praise bean,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
 		bean.setDateline(RequestUtils.getNow()); 
 		bean.setUserId(ApiUtils.getUnifiedUserId(request));
		Praise praise=praiseService.findByRefer(bean);
 		if(praise!=null){//已经点赞 
			return new JsonResult(ResultCode.SUCCESS,false);
 		}else { 
  	 		return new JsonResult(ResultCode.SUCCESS,true);
		}  
 	}
 	
	
	@Autowired
	public PraiseService praiseService; 
	 
}
