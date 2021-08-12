package com.shuogesha.app.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.cms.entity.Yaoqing;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Captcha;
import com.shuogesha.platform.entity.Site;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.AccountService;
import com.shuogesha.platform.service.CaptchaService;
import com.shuogesha.platform.service.SystemLogService;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.service.UnifiedUserTokenService;
import com.shuogesha.platform.web.exception.BadCredentialsException;
import com.shuogesha.platform.web.exception.UsernameNotFoundException;
import com.shuogesha.platform.web.util.RequestUtils;

@Controller
@RequestMapping("/app/")
public class ApiLoginAct {

	@RequestMapping(value = "login", method = RequestMethod.POST)  
	public @ResponseBody Object token_1(@RequestBody JSONObject param,HttpServletRequest request,
			HttpServletResponse response) { 
		String username=param.getString("username");
		String password=param.getString("password");
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new JsonResult(ResultCode.FAIL, "参数错误", null);
		}
		try {
			UnifiedUser unifiedUser = unifiedUserService.login(username, password, RequestUtils.getIpAddr(request));
			if (unifiedUser == null) {
				return new JsonResult(ResultCode.FAIL, "用户名或密码错误", null);
			} else if (unifiedUser.isDisabled()) {
				return new JsonResult(ResultCode.FAIL, "用户已经被禁用", null);
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				unifiedUser.setAccount(accountService.findById(unifiedUser.getId()));
				data.put("unifiedUser", unifiedUser);
				data.put("token", unifiedUserTokenService.createToken(ApiUtils.getAppId(request), unifiedUser.getId().toString()));
				systemLogService.log(unifiedUser.getId(), "用户登录", RequestUtils.getIpAddr(request), SystemLog.PC);
				return new JsonResult(ResultCode.SUCCESS, "登录成功", data);
			}
		} catch (BadCredentialsException e) {
			return new JsonResult(ResultCode.FAIL, "用户名或密码错误", null);
		} catch (UsernameNotFoundException e) {
			return new JsonResult(ResultCode.FAIL, "用户名不存在", null);
		}
	}
	
	

	/***
	 * 退出登录
	 * 
	 * @param request
	 * @param tokenStr
	 * @return
	 */
	@RequestMapping(value = "logout")
	public @ResponseBody Object logout_1(HttpServletRequest request, String appid) {
		try {
			unifiedUserService.logout(ApiUtils.getAppId(request), ApiUtils.getUnifiedUserId(request));
			systemLogService.log(ApiUtils.getUnifiedUserId(request), "退出登录", RequestUtils.getIpAddr(request),
					SystemLog.APP);
		} catch (Exception e) {

		}
		return new JsonResult(ResultCode.SUCCESS, "退出成功", null);
	}
	
	private boolean validateCaptcha(String username,String captcha, HttpServletRequest request,HttpServletResponse response) {
		Captcha bean= captchaService.findByName(username); 
		if(bean!=null){ 
			if((System.currentTimeMillis()-bean.getDateline().getTime())>timeout){
				return false;
			}
			if(bean.getCaptcha().equals(captcha)){
				captchaService.removeById(bean.getId());
				return true;
			}
		}
		return false;
	}
	
	private int timeout = 30 * 60 * 1000; // 30分钟
	
	@Autowired
	public CaptchaService captchaService;  
	@Autowired
	private AccountService accountService;
	@Autowired
	private UnifiedUserService unifiedUserService;
	@Autowired
	private UnifiedUserTokenService unifiedUserTokenService;
	@Autowired
	private SystemLogService systemLogService;

}
