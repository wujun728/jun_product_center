package com.jun.plugin.system.controller;

import com.jun.plugin.common.Result;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.Logical;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.jun.plugin.system.entity.SysPermission;
import com.jun.plugin.system.service.PermissionService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 菜单权限管理
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-菜单权限管理")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("/permission")
    @ApiOperation(value = "新增菜单权限接口")
    //@RequiresPermissions("sys:permission:add")
    public Result addPermission(@RequestBody @Valid SysPermission vo) {
        verifyFormPid(vo);
        vo.setStatus(1);
        permissionService.save(vo);
        return Result.success();
    }

    @DeleteMapping("/permission/{id}")
    @ApiOperation(value = "删除菜单权限接口")
    //@RequiresPermissions("sys:permission:deleted")
    public Result deleted(@PathVariable("id") String id) {
        permissionService.deleted(id);
        return Result.success();
    }

    @PutMapping("/permission")
    @ApiOperation(value = "更新菜单权限接口")
    //@RequiresPermissions("sys:permission:update")
    public Result updatePermission(@RequestBody @Valid SysPermission vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return Result.fail("id不能为空");
        }
        SysPermission sysPermission = permissionService.getById(vo.getId());
        if (null == sysPermission) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        // 只有类型变更或者所属菜单变更
        if (sysPermission.getType().equals(vo.getType()) || !sysPermission.getPid().equals(vo.getPid())) {
            verifyFormPid(vo);
        }
        permissionService.updatePermission(vo);
        return Result.success();
    }

    @GetMapping("/permission/{id}")
    @ApiOperation(value = "查询菜单权限接口")
    //@RequiresPermissions("sys:permission:detail")
    public Result detailInfo(@PathVariable("id") String id) {
        return Result.success(permissionService.getById(id));

    }

    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有菜单权限接口")
    //@RequiresPermissions("sys:permission:list")
    public Result getAllMenusPermission() {
        return Result.success(permissionService.selectAll());
    }

    @GetMapping("/permission/tree")
    @ApiOperation(value = "获取所有目录菜单树接口")
    //@RequiresPermissions(value = {"sys:permission:update", "sys:permission:add"}, logical = Logical.OR)
    public Result getAllMenusPermissionTree(@RequestParam(required = false) String permissionId) {
        return Result.success(permissionService.selectAllMenuByTree(permissionId));
    }

    @GetMapping("/permission/tree/all")
    @ApiOperation(value = "获取所有目录菜单树接口")
    //@RequiresPermissions(value = {"sys:role:update", "sys:role:add"}, logical = Logical.OR)
    public Result getAllPermissionTree() {
        return Result.success(permissionService.selectAllByTree());
    }

    /**
     * 操作后的菜单类型是目录的时候 父级必须为目录
     * 操作后的菜单类型是菜单的时候，父类必须为目录类型
     * 操作后的菜单类型是按钮的时候 父类必须为菜单类型
     */
    private void verifyFormPid(SysPermission sysPermission) {
        SysPermission parent;
        parent = permissionService.getById(sysPermission.getPid());
        switch (sysPermission.getType()) {
            case 1:
                if (parent != null) {
                    if (parent.getType() != 1) {
                        throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                } else if (!"0".equals(sysPermission.getPid())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:
                if (parent == null || parent.getType() != 1) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }

                break;
            case 3:
                if (parent == null || parent.getType() != 2) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if (StringUtils.isEmpty(sysPermission.getPerms())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_PERMS_NULL);
                }
                if (StringUtils.isEmpty(sysPermission.getUrl())) {
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                break;
            default:
        }
    }
}
