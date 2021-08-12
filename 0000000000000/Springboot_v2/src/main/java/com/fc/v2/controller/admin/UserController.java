package com.fc.v2.controller.admin;

import java.util.List;
import com.fc.v2.common.domain.ResultTable;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.SysDepartment;
import com.fc.v2.model.auto.SysDepartmentExample;
import com.fc.v2.model.auto.SysPosition;
import com.fc.v2.model.auto.SysPositionExample;
import com.fc.v2.model.auto.TsysRole;
import com.fc.v2.model.auto.TsysUser;
import com.fc.v2.model.custom.RoleVo;
import com.fc.v2.model.custom.Tablepar;
import com.fc.v2.service.SysDepartmentService;
import com.fc.v2.service.SysPositionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户Controller
 * @ClassName: UserController
 * @author fuce
 * @date 2019-11-20 22:35
 */
@Api(value = "用户数据")
@Controller
@RequestMapping("/UserController")
public class UserController extends BaseController{
	
	private final String prefix = "admin/user";
	//部门
	@Autowired
	private SysDepartmentService departmentService;
	//岗位
	@Autowired
	private SysPositionService positionService;
	
	/**
	 * 展示跳转页面
	 * @param model
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:14:34
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("system:user:view")
    public String view(ModelMap model)
    {
		return prefix + "/list";
    }
	
	
	/**
	 * list集合
	 * @param tablepar
	 * @param searchText
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:14:40
	 */
	//@Log(title = "分页查询", action = "1")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/list")
	@RequiresPermissions("system:user:list")
	@ResponseBody
	public ResultTable list(Tablepar tablepar){
		PageInfo<TsysUser> page=sysUserService.list(tablepar) ;
		return pageTable(page.getList(),page.getTotal());
	}
	/**
	 * 新增跳转
	 * @param modelMap
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:14:51
	 */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
    	//添加角色列表
		List<TsysRole> tsysRoleList=sysRoleService.queryList();
		//部门列表
		List<SysDepartment> departments=departmentService.selectByExample(new SysDepartmentExample());
		//岗位列表
		List<SysPosition> sysPositions=positionService.selectByExample(new SysPositionExample());
		//角色
		modelMap.put("tsysRoleList",tsysRoleList);
		//部门
		modelMap.put("departmentsList",departments);
		//岗位
		modelMap.put("sysPositionsList",sysPositions);
        return prefix + "/add";
    }
	/**
	 * 新增保存
	 * @param user
	 * @param model
	 * @param roles
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:14:57
	 */
    @Log(title = "用户新增", action = "111")
    @ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("system:user:add")
	@ResponseBody
	public AjaxResult add(TsysUser user,@RequestParam(value="roleIds", required = false)String roleIds){
		int b=sysUserService.insertUserRoles(user,roleIds);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
    //@Log(title = "删除用户", action = "1")
  	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@RequiresPermissions("system:user:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysUserService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查用户
	 * @param tsysUser
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkLoginNameUnique")
	@ResponseBody
	public int checkLoginNameUnique(TsysUser tsysUser){
		int b=sysUserService.checkLoginNameUnique(tsysUser);
		if(b>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 修改用户跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
		//查询所有角色
		List<RoleVo> roleVos=sysUserService.getUserIsRole(id);
		//岗位列表
		List<SysPosition> sysPositions=positionService.selectByExample(new SysPositionExample());
		mmap.put("roleVos",roleVos);
        mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
        //岗位
        mmap.put("sysPositionsList",sysPositions);
        return prefix + "/edit";
    }
	
	/**
     * 修改保存用户
     */
	//@Log(title = "修改保存用户", action = "1")
    @ApiOperation(value = "修改保存用户", notes = "修改保存用户")
    @RequiresPermissions("system:user:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TsysUser tsysUser,@RequestParam(value="roleIds", required = false)String roleIds)
    {
        return toAjax(sysUserService.updateUserRoles(tsysUser,roleIds));
    }

    
    
    /**
	 * 修改用户密码跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
    //@Log(title = "修改用户密码", action = "1")
    @ApiOperation(value = "修改用户密码跳转", notes = "修改用户密码跳转")
	@GetMapping("/editPwd/{id}")
    public String editPwd(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
        return prefix + "/editPwd";
    }
	/**
     * 修改保存用户
     */
    //@Log(title = "修改用户密码", action = "1")
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @RequiresPermissions("system:user:editPwd")
    @PostMapping("/editPwd")
    @ResponseBody
    public AjaxResult editPwdSave(TsysUser tsysUser)
    {
        return toAjax(sysUserService.updateUserPassword(tsysUser));
    }


}
