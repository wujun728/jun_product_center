package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.modules.sys.param.SysRoleGiveRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"角色"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "role")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 查询角色列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysRole}
     */
    @GetMapping("page")
    @Log(title = "查询角色")
    @ApiOperation(value = "查询角色")
    public Result page(SysRoleRequest request){
        return success(sysRoleService.page(request));
    }

    /**
     * 查询角色列表
     *
     * @param request 查询参数
     *
     * @return {@link SysRole}
     */
    @GetMapping("list")
    @Log(title = "查询角色")
    @ApiOperation(value = "查询角色")
    public Result list(SysRoleRequest request) {
        return success(sysRoleService.list(request));
    }

    /**
     * 新增角色
     *
     * @param sysRole 角色实体
     *
     * @return {@link Boolean}
     */
    @PostMapping("save")
    @Log(title = "新增角色")
    @ApiOperation(value = "新增角色")
    public Result save(@RequestBody SysRole sysRole){
        return auto(sysRoleService.save(sysRole));
    }

    /**
     * 修改角色
     *
     * @param sysRole 角色实体
     *
     * @return {@link Boolean}
     */
    @PutMapping("edit")
    @Log(title = "修改角色")
    @ApiOperation(value = "修改角色")
    public Result edit(@RequestBody SysRole sysRole){
        return auto(sysRoleService.updateById(sysRole));
    }

    /**
     * 删除角色
     *
     * @param id 角色编号
     *
     * @return {@link Boolean}
     */
    @DeleteMapping("remove")
    @Log(title = "删除角色")
    @ApiOperation(value = "删除角色")
    public Result remove(@RequestParam String id){
        return auto(sysRoleService.removeById(id));
    }

    /**
     * 删除角色
     *
     * @param ids 角色编号
     *
     * @return {@link Boolean}
     * */
    @DeleteMapping("removeBatch")
    @Log(title = "删除角色")
    @ApiOperation(value = "删除角色")
    public Result removeBatch(@RequestParam List<String> ids) { return auto(sysRoleService.removeByIds(ids)); }

    /**
     * 权限分配
     *
     * @param request 参数实体
     * */
    @PostMapping("give")
    @Log(title = "分配权限")
    @ApiOperation(value = "分配权限")
    public Result give(@RequestBody SysRoleGiveRequest request){
        return success(sysRoleService.give(request));
    }

    /**
     * 角色权限
     *
     * @param roleId 角色编号
     * */
    @GetMapping("power")
    @Log(title = "角色权限")
    @ApiOperation(value = "角色权限")
    public Result power(@RequestParam String roleId){
        return success(sysRoleService.power(roleId));
    }


    /**
     * 角色部门
     *
     * @param roleId 角色编号
     * */
    @GetMapping("dept")
    @Log(title = "角色部门")
    @ApiOperation(value = "角色部门")
    public Result dept(@RequestParam String roleId) { return success(sysRoleService.dept(roleId)); }
}
