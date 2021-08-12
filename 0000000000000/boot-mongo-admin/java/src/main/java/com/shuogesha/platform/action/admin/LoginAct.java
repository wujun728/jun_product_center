package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.service.AuthenticationService.AUTH_KEY;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Authentication;
import com.shuogesha.platform.entity.SystemLog;
import com.shuogesha.platform.entity.UnifiedUser;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.service.AuthenticationService;
import com.shuogesha.platform.service.SystemLogService;
import com.shuogesha.platform.service.UnifiedUserService;
import com.shuogesha.platform.service.UserService;
import com.shuogesha.platform.web.exception.BadCredentialsException;
import com.shuogesha.platform.web.exception.UsernameNotFoundException;
import com.shuogesha.platform.web.session.SessionProvider;
import com.shuogesha.platform.web.util.RequestUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/")
@Api(tags = "登录/退出")
public class LoginAct {

	@PostMapping(value = "/login")
	@ApiOperation(value = "用户登录", notes = "用户登录")
	public @ResponseBody Object login(@RequestBody JSONObject param,HttpServletRequest request,
			HttpServletResponse response) { 
		String username=param.getString("username");
		String password=param.getString("password");
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			try {
				String ip = RequestUtils.getIpAddr(request);
				Authentication auth = authenticationService.login(username, password, ip, request, response, session);
				// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
				unifiedUserService.updateLoginInfo(auth.getUid(), ip);
				UnifiedUser unifieduser = unifiedUserService.findById(auth.getUid());
				if (unifieduser.isDisabled()||!"Admin".equals(unifieduser.getType())) {
					// 如果已经禁用，则退出登录。
					authenticationService.removeById(auth.getId());
					session.logout(request, response);
					return new JsonResult(ResultCode.FAIL, "用户已经被禁用",null);
				}
				Cookie cookie = new Cookie("ssid", auth.getId());
				cookie.setMaxAge(24 * 30 * 30);
				response.addCookie(cookie);
				systemLogService.log(unifieduser.getId(), "用户登录", ip, SystemLog.PC);
				User user=userService.findById(unifieduser.getId());
				if (user != null) {
					Map<String, Object> data = new HashMap<String, Object>();
					data.put("unifieduser", unifieduser);
					data.put("authId", auth.getId());
					data.put("user", user); 
					return new JsonResult(ResultCode.SUCCESS, "登录成功", data);
				} else {
					return new JsonResult(ResultCode.FAIL, "登录失败",null);
				}
			} catch (UsernameNotFoundException e) {
				return new JsonResult(ResultCode.FAIL, "用户不存在",null);
			} catch (BadCredentialsException e) {
				return new JsonResult(ResultCode.FAIL, "用户名密码错误",null);
			}
		}
		// 登录失败
		return new JsonResult(ResultCode.FAIL, "参数错误",null);
	}

	@GetMapping(value = "/logout")
	@ApiOperation(value = "用户退出", notes = "用户退出")
	public @ResponseBody Object logout(HttpServletRequest request, HttpServletResponse response) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if(authId==null) {//从head里面取
			authId = request.getHeader("shuogesha_auth_id");
		}
		if (authId != null) {
			Authentication authentication =authenticationService.findById(authId);
			if(authentication!=null) {
				String uid = authenticationService.findById(authId).getUid();
				systemLogService.log(uid, "退出登录", RequestUtils.getIpAddr(request), SystemLog.PC);
			} 
			authenticationService.removeById(authId);
			session.logout(request, response);
		}
		Cookie cookie = new Cookie("ssid", session.getSessionId(request, response));
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		} 
		return new JsonResult(ResultCode.SUCCESS, "退出成功", true);
	}

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private UnifiedUserService unifiedUserService;
	@Autowired
	private UserService userService;
	@Autowired
	private SessionProvider session;
	@Autowired
	private SystemLogService systemLogService;
}
