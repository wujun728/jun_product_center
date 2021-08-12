package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.web.encoder.Md5PwdEncoder;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;
import com.shuogesha.platform.web.util.ResponseUtils;

@Controller
@RequestMapping("/api/unifiedUser/")
public class UnifiedUserAct {

	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = unifiedUserService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		UnifiedUser bean = unifiedUserService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody UnifiedUser bean,HttpServletRequest request) {
		String now = RequestUtils.getNow();
		bean.setRegisterIp(RequestUtils.getIpAddr(request));
		bean.setRegisterTime(now); 
		bean.setPassword(pwdEncoder.encodePassword(bean.getPassword()));
 		unifiedUserService.save(bean);
 		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody UnifiedUser bean) {
		if(StringUtils.isNotBlank(bean.getPassword())){
			bean.setPassword(pwdEncoder.encodePassword(bean.getPassword()));
		}
		unifiedUserService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		unifiedUserService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}
	
	@RequestMapping(value = "/v_check_username.do")
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
		String username=RequestUtils.getQueryParam(request,"username");
		String pass;
		if (StringUtils.isBlank(username)) {
			pass = "false";
		} else {
			pass = unifiedUserService.usernameNotExist(username) ? "true" : "false";
		}
		ResponseUtils.renderJson(response, pass);
	}
	
	/**
	 * 禁用用户
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/stop")
	public @ResponseBody Object o_stop(Long[] ids, HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {
		unifiedUserService.updateStatus(ids,true);
		return new JsonResult(ResultCode.SUCCESS, true);
	}
	/**
	 * 启用用户
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/start")
	public @ResponseBody Object o_start(Long[] ids, HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {
		unifiedUserService.updateStatus(ids,false);
		return new JsonResult(ResultCode.SUCCESS, true);
	} 
	
	@Autowired
	public UnifiedUserService unifiedUserService;
	@Autowired
	private Md5PwdEncoder pwdEncoder;
}
