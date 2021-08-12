package com.shuogesha.app.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.app.version.AccessTokenRequired;
import com.shuogesha.app.web.util.ApiUtils;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.service.AccountService;
import com.shuogesha.platform.service.UnifiedUserService;

@Controller
@RequestMapping("/{version}/")
public class ApiUserAct {

	/**
	 * 获取自己用户信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "get_user_info")
	@AccessTokenRequired
	public @ResponseBody Object get_user_info(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws IOException {
		UnifiedUser unifiedUser = ApiUtils.getUnifiedUser(request);
		unifiedUser.setAccount(accountService.init(ApiUtils.getUnifiedUserId(request)));
		return new JsonResult(ResultCode.SUCCESS, unifiedUser);
	}
	/**
	 * 修改用户资料
	 * @param param
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "save_profile", method = RequestMethod.POST)
 	@AccessTokenRequired
	public @ResponseBody Object save_profile(@RequestBody JSONObject param,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		String nickName=param.getString("nickName");
		String sex=param.getString("sex");
 		String avatar=param.getString("avatar");  
		if(StringUtils.isBlank(nickName)&&StringUtils.isBlank(sex)&&StringUtils.isBlank(avatar)){
			return new JsonResult(ResultCode.FAIL, "参数错误", null);
		}
		UnifiedUser unifiedUser= new UnifiedUser();
		unifiedUser.setId(ApiUtils.getUnifiedUserId(request)); 
		if(StringUtils.isNotBlank(avatar)){
			unifiedUser.setAvatar(avatar);
		} 
		if(StringUtils.isNotBlank(nickName)){
			unifiedUser.setNickName(nickName); 
		}
		if(StringUtils.isNotBlank(sex)){
			unifiedUser.setSex(sex);
		}  
 		unifiedUser=unifiedUserService.updateProfile(unifiedUser); 
  		unifiedUser.setAccount(accountService.init(unifiedUser.getId()));
 		return new JsonResult(ResultCode.SUCCESS, "保存成功", unifiedUser);
	}

	@Autowired
	private UnifiedUserService unifiedUserService;
	@Autowired
	private AccountService accountService;

}
