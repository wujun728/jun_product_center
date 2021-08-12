package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.entity.Role;
import com.shuogesha.platform.service.RoleService;
import com.shuogesha.platform.web.mongo.Pagination;

@Controller
@RequestMapping("/api/role/")
public class RoleAct {
	@RequestMapping(value = "/list")
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = roleService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@RequestMapping(value = "/get")
	public @ResponseBody Object v_get(Long id) {
		Role bean = roleService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Object o_save(@RequestBody Role bean) {
		roleService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object o_update(@RequestBody Role bean) {
		roleService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Object o_delete(Long[] ids) {
		roleService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}
	
	/**
	 * 存储用户角色的权限
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/saveRoleMenus", method = RequestMethod.POST)
	public @ResponseBody Object saveRoleMenus(@RequestBody Role bean) {
		roleService.saveRoleMenus(bean);
 		return new JsonResult(ResultCode.SUCCESS, true);
	}
	/**
	 * 根据用户获取他的资源权限
	 * @param userId
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllMenusByUser")
	public @ResponseBody Object getAllMenusByUser(Long userId,Integer type, HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {
		List<Menu> list= roleService.getAllMenusByUser(userId,type);
		return new JsonResult(ResultCode.SUCCESS, list);
	}
	
	/**
	 * 根据用户获取他的资源权限
	 * @param userId
	 * @param type
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllPermsByUser")
	public @ResponseBody Object getAllPermsByUser(Long userId, HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {
		Set<String> list= roleService.getAllPermsByUser(userId);
		return new JsonResult(ResultCode.SUCCESS, list);
	}
	
	@RequestMapping(value = "/getAllRole")
	public @ResponseBody Object getAllRole( HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {
		List<Role> roleList = roleService.getList();
		return new JsonResult(ResultCode.SUCCESS, roleList);
	}
	
	
	@Autowired
	public RoleService roleService;
}
