package com.jun.plugin.system.controller;

import com.jun.plugin.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.Logical;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.system.service.RolePermissionService;
import com.jun.plugin.system.vo.req.RolePermissionOperationReqVO;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 角色和菜单关联
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织管理-角色和菜单关联接口")
public class RolePermissionController {
    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping("/role/permission")
    @ApiOperation(value = "修改或者新增角色菜单权限接口")
    //@RequiresPermissions(value = {"sys:role:update", "sys:role:add"}, logical = Logical.OR)
    public Result operationRolePermission(@RequestBody @Valid RolePermissionOperationReqVO vo) {
        rolePermissionService.addRolePermission(vo);
        return Result.success();
    }
}
