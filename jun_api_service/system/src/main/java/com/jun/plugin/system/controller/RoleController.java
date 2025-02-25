package com.jun.plugin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.common.Result;
import com.jun.plugin.system.entity.SysRole;
import com.jun.plugin.system.entity.SysRoleDeptEntity;
import com.jun.plugin.system.service.RoleService;
import com.jun.plugin.system.service.SysRoleDeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-角色管理")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private SysRoleDeptService sysRoleDeptService;

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    //@RequiresPermissions("sys:role:add")
    public Result addRole(@RequestBody @Valid SysRole vo) {
        roleService.addRole(vo);
        return Result.success();
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    //@RequiresPermissions("sys:role:deleted")
    public Result deleted(@PathVariable("id") String id) {
        roleService.deletedRole(id);
        return Result.success();
    }

    @PutMapping("/role")
    @ApiOperation(value = "更新角色信息接口")
    //@RequiresPermissions("sys:role:update")
    public Result updateDept(@RequestBody SysRole vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return Result.fail("id不能为空");
        }
        roleService.updateRole(vo);
        return Result.success();
    }

    @PostMapping("/role/bindDept")
    @ApiOperation(value = "绑定角色部门接口")
    //@RequiresPermissions("sys:role:bindDept")
    public Result bindDept(@RequestBody SysRole vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return Result.fail("id不能为空");
        }
        if (roleService.getById(vo.getId()) == null) {
            return Result.fail("获取角色失败");
        }

        //先删除所有绑定
        sysRoleDeptService.remove(Wrappers.<SysRoleDeptEntity>lambdaQuery().eq(SysRoleDeptEntity::getRoleId, vo.getId()));
        //如果不是自定义
        if (vo.getDataScope() != 2) {
            vo.setDepts(null);
        }
        if (!CollectionUtils.isEmpty(vo.getDepts())) {
            List<SysRoleDeptEntity> list = new ArrayList<>();
            for (String deptId : vo.getDepts()) {
                SysRoleDeptEntity sysRoleDeptEntity = new SysRoleDeptEntity();
                sysRoleDeptEntity.setDeptId(deptId);
                sysRoleDeptEntity.setRoleId(vo.getId());
                list.add(sysRoleDeptEntity);
            }
            sysRoleDeptService.saveBatch(list);
        }
        roleService.updateById(new SysRole().setId(vo.getId()).setDataScope(vo.getDataScope()));
        return Result.success();
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "查询角色详情接口")
    //@RequiresPermissions("sys:role:detail")
    public Result detailInfo(@PathVariable("id") String id) {
        return Result.success(roleService.detailInfo(id));
    }

    @PostMapping("/roles")
    @ApiOperation(value = "分页获取角色信息接口")
    //@RequiresPermissions("sys:role:list")
    @SuppressWarnings("unchecked")
    public Result pageInfo(@RequestBody SysRole vo) {
        Page page = new Page(vo.getPage(), vo.getLimit());
        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
        if (!StringUtils.isEmpty(vo.getName())) {
            queryWrapper.like(SysRole::getName, vo.getName());
        }
        if (!StringUtils.isEmpty(vo.getStartTime())) {
            queryWrapper.gt(SysRole::getCreateTime, vo.getStartTime());
        }
        if (!StringUtils.isEmpty(vo.getEndTime())) {
            queryWrapper.lt(SysRole::getCreateTime, vo.getEndTime());
        }
        if (!StringUtils.isEmpty(vo.getStatus())) {
            queryWrapper.eq(SysRole::getStatus, vo.getStatus());
        }
        queryWrapper.orderByDesc(SysRole::getCreateTime);
        return Result.success(roleService.page(page, queryWrapper));
    }

}
