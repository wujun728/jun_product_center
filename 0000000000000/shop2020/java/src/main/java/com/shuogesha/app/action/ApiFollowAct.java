package com.shuogesha.app.action;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Follow;
import com.shuogesha.cms.service.FollowService;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;

@Controller
@RequestMapping("/app/")
public class ApiFollowAct { 
	/**
	 * 查看用户是否关注
	 * @param bean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "check_follow", method = RequestMethod.POST)  
	@AccessTokenRequired
	public @ResponseBody Object check_follow(Follow bean,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
 		bean.setDateline(RequestUtils.getNow()); 
 		bean.setFromUid(ApiUtils.getUnifiedUserId(request));
 		Follow follow=followService.findByFollw(bean);
 		if(follow!=null){ 
			return new JsonResult(ResultCode.SUCCESS, true);
 		}else { 
 			return new JsonResult(ResultCode.SUCCESS, false); 
 		}  
 	}
	
	/**
	 * 关注用户
	 * @param bean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "save_follow", method = RequestMethod.POST) 
	@AccessTokenRequired
	public @ResponseBody Object save_follow(Follow bean,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception { 
		if(bean.getToUid()==null||bean.getToUid()<=0){  
			return new JsonResult(ResultCode.FAIL, "参数错误",null);
  		} 
		if(bean.getToUid()==ApiUtils.getUnifiedUserId(request)){  
		    return new JsonResult(ResultCode.FAIL, "不能关注自己",null);
		} 
		bean.setDateline(RequestUtils.getNow()); 
 		bean.setFromUid(ApiUtils.getUnifiedUserId(request));
 		Follow follow=followService.findByFollw(bean);
 		if(follow!=null){ 
			followService.removeById(follow.getId());
			return new JsonResult(ResultCode.SUCCESS, "取消关注",false);
		}else { 
			followService.save(bean);  
 	 		return new JsonResult(ResultCode.SUCCESS, "关注成功",true);
		}  
 	}
	
	/**
	 * 关注我的粉丝
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "get_follow_list") 
	@AccessTokenRequired
 	public @ResponseBody Object get_follow_list(Integer pageNo, Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException { 
		Pagination pagination = followService.getFollowPage(null,null,ApiUtils.getUnifiedUserId(request),cpn(pageNo),pageSize);
		return new JsonResult(ResultCode.SUCCESS,pagination);
	}
	/**
	 * 我关注
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "get_my_follow_list") 
	@AccessTokenRequired
 	public @ResponseBody Object get_my_follow_list(Integer pageNo, Integer pageSize, 
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException { 
		Pagination pagination = followService.getPage(null,ApiUtils.getUnifiedUserId(request),null,cpn(pageNo),pageSize);
		return new JsonResult(ResultCode.SUCCESS,pagination);
	} 
	


	
	@Autowired
	private FollowService followService;  
}
