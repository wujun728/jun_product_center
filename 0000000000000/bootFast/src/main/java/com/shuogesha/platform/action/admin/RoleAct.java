package com.shuogesha.platform.action.admin;

import static com.shuogesha.platform.web.mongo.SimplePage.cpn;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shuogesha.common.util.JsonResult;
import com.shuogesha.common.util.ResultCode;
import com.shuogesha.platform.entity.Menu;
import com.shuogesha.platform.entity.Role;
import com.shuogesha.platform.service.RoleService;
import com.shuogesha.platform.web.mongo.Pagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/role/")
@Api(tags = "角色管理")
public class RoleAct {
	@GetMapping(value = "/list")
	@ApiOperation(value = "获取列表", notes = "名称、分页、页码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "当前页", required = true, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示", required = true, dataType = "int") })
	public @ResponseBody Object v_list(String name, Integer pageNo, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Pagination pagination = roleService.getPage(name, cpn(pageNo), pageSize);
		return new JsonResult(ResultCode.SUCCESS, pagination);
	}

	@GetMapping(value = "/get")
	@ApiOperation(value = "根据id获取详细信息", notes = "获取单个详细信息")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long")
	public @ResponseBody Object v_get(Long id) {
		Role bean = roleService.findById(id);
		return new JsonResult(ResultCode.SUCCESS, bean);
	}

	@PostMapping(value = "/save")
	@ApiOperation(value = "保存", notes = "保存")
	public @ResponseBody Object o_save(@RequestBody Role bean) {
		roleService.save(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@PostMapping(value = "/update")
	@ApiOperation(value = "更新", notes = "更新")
	public @ResponseBody Object o_update(@RequestBody Role bean) {
		roleService.update(bean);
		return new JsonResult(ResultCode.SUCCESS, true);
	}

	@GetMapping(value = "/delete")
	@ApiOperation(value = "批量删除", notes = "批量删除")
	public @ResponseBody Object o_delete(Long[] ids) {
		roleService.removeByIds(ids);
		return new JsonResult(ResultCode.SUCCESS, true);
	}
	
	/**
	 * 存储用户角色的权限
	 * @param list
	 * @return
	 */
	@PostMapping(value = "/saveRoleMenus")
	@ApiOperation(value = "保存/修改角色权限", notes = "保存/修改角色权限")
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
	@GetMapping(value = "/getAllMenusByUser")
	@ApiOperation(value = "根据用户id获取菜单", notes = "根据用户id获取菜单")
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
	@GetMapping(value = "/getAllPermsByUser")
	@ApiOperation(value = "根据用户id获取资源权限", notes = "根据用户id获取资源权限") 
	public @ResponseBody Object getAllPermsByUser(Long userId, HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {
		Set<String> list= roleService.getAllPermsByUser(userId);
		return new JsonResult(ResultCode.SUCCESS, list);
	}
	/**
	 * 获取全部角色
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@GetMapping(value = "/getAllRole")
	@ApiOperation(value = "获取所有角色", notes = "获取所有角色")
	public @ResponseBody Object getAllRole( HttpServletRequest request,
			HttpServletResponse response)
			throws UnsupportedEncodingException {
		List<Role> roleList = roleService.getList();
		return new JsonResult(ResultCode.SUCCESS, roleList);
	}
	
	
	@Autowired
	public RoleService roleService;
}
