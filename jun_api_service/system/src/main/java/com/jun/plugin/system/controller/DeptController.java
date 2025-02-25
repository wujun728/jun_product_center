package com.jun.plugin.system.controller;

import com.jun.plugin.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.jun.plugin.system.entity.SysDept;
import com.jun.plugin.system.service.DeptService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 部门管理
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-机构管理")
public class DeptController {
    @Resource
    private DeptService deptService;

    @PostMapping("/dept")
    @ApiOperation(value = "新增组织接口")
    //@RequiresPermissions("sys:dept:add")
    public Result addDept(@RequestBody @Valid SysDept vo) {
        deptService.addDept(vo);
        return Result.success();
    }

    @DeleteMapping("/dept/{id}")
    @ApiOperation(value = "删除组织接口")
    //@RequiresPermissions("sys:dept:deleted")
    public Result deleted(@PathVariable("id") String id) {
        deptService.deleted(id);
        return Result.success();
    }

    @PutMapping("/dept")
    @ApiOperation(value = "更新组织信息接口")
    //@RequiresPermissions("sys:dept:update")
    public Result updateDept(@RequestBody SysDept vo) {
        if (StringUtils.isEmpty(vo.getId())) {
            return Result.fail("id不能为空");
        }
        deptService.updateDept(vo);
        return Result.success();
    }

    @GetMapping("/dept/{id}")
    @ApiOperation(value = "查询组织详情接口")
    //@RequiresPermissions("sys:dept:detail")
    public Result detailInfo(@PathVariable("id") String id) {
        return Result.success(deptService.getById(id));
    }

    @GetMapping("/dept/tree")
    @ApiOperation(value = "树型组织列表接口")
    //    //@RequiresPermissions(value = {"sys:user:list", "sys:user:update", "sys:user:add", "sys:dept:add", "sys:dept:update"}, logical = Logical.OR)
    public Result getTree(@RequestParam(required = false) String deptId) {
        return Result.success(deptService.deptTreeList(deptId, false));
    }

    @GetMapping("/depts")
    @ApiOperation(value = "获取机构列表接口")
    //@RequiresPermissions("sys:dept:list")
    public Result getDeptAll() {
        List<SysDept> deptList = deptService.list();
        deptList.parallelStream().forEach(entity -> {
        	SysDept parentDept = null;
        	for (SysDept tmp : deptList) {
        		if(tmp.getId().equalsIgnoreCase(entity.getPid())) {
        			parentDept = tmp;
        		}
        	}
            //SysDept parentDept = deptService.getById(entity.getPid());
            if (parentDept != null) {
                entity.setPidName(parentDept.getName());
            }
        });
        return Result.success(deptList);
    }

}
