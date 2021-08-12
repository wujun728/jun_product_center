package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.User;
import com.shuogesha.platform.service.RoleService;
import com.shuogesha.platform.service.UserService;
import com.shuogesha.platform.web.CmsUtils;
import com.shuogesha.platform.web.mongo.Pagination;
import com.shuogesha.platform.web.util.RequestUtils;
import com.shuogesha.platform.web.util.ResponseUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user/")
@Api(tags = "管理员管理")
public class UserAct {

	@GetMapping(value = "/list")
	@ApiOperation(value = "获取列表", notes = "用户名、分页、页码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "当前页", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示", required = true, dataType = "int") })
	public Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = userService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@GetMapping(value = "/get")
	@ApiOperation(value = "根据id获取详细信息", notes = "获取单个详细信息")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
	public Object v_get(Long id) {
		User bean = userService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@PostMapping(value = "/save")
	@ApiOperation(value = "保存", notes = "保存")
 	public Object o_save(@ModelAttribute @RequestBody JSONObject json, HttpServletRequest request) {
		String ip = RequestUtils.getIpAddr(request);
		User bean = new JSONObject().parseObject(json.toJSONString(), User.class);
		bean.setSiteId(CmsUtils.getSiteId(request));
		userService.saveAdmin(bean, bean.getUsername(), json.getString("password"), "", ip);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@PostMapping(value = "/update")
	@ApiOperation(value = "更新", notes = "更新")
 	public Object o_update(@RequestBody JSONObject json) {
		User bean = new JSONObject().parseObject(json.toJSONString(), User.class);
		userService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@GetMapping(value = "/delete")
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public Object o_delete(Long[] ids) {
		userService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@GetMapping(value = "/v_check_username")
	@ApiOperation(value = "检查用户名是否存在", notes = "检查用户名是否存在")
	@ApiImplicitParam(name = "username", value = "username", required = true, dataType = "String")
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
