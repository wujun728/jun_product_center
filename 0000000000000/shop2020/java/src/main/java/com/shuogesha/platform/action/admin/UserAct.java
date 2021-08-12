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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Role;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.service.RoleService;
import com.shuogesha.platform.service.UserService;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;
import com.shuogesha.platform.web.util.ResponseUtils;

@Controller
@RequestMapping("/api/user/")
public class UserAct {

	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = userService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		User bean = userService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody JSONObject json,HttpServletRequest request) {
		String ip = RequestUtils.getIpAddr(request);
		User bean = new JSONObject().parseObject(json.toJSONString(), User.class); 
		bean.setSiteId(CmsUtils.getSiteId(request));  
		userService.saveAdmin(bean,bean.getUsername(),json.getString("password"),"",ip);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody JSONObject json) {
		User bean = new JSONObject().parseObject(json.toJSONString(), User.class); 
		userService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		userService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/v_check_username")
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
		String username = RequestUtils.getQueryParam(request, "username");
		String pass;
		if (StringUtils.isBlank(username)) {
			pass = "false";
		} else {
			pass = userService.usernameNotExist(username) ? "true" : "false";
		}
		ResponseUtils.renderJson(response, pass);
	}

	@Autowired
	public RoleService roleService;
	@Autowired
	public UserService userService;
}
