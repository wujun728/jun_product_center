package com.pearadmin.modules.sys.controller;

import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.response.module.ResultTree;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.modules.sys.domain.SysDept;
import com.pearadmin.modules.sys.service.ISysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: 部门管理
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@RestController
@Api(tags = {"组织部门"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "dept")
public class SysDeptController extends BaseController {

    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "system/dept/";

    /**
     * Describe: 部门模块服务
     */
    @Resource
    private ISysDeptService sysDeptService;

    /**
     * Describe: 获取部门列表视图
     * Param ModelAndView
     * Return 用户列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/dept/main','sys:dept:main')")
    public ModelAndView main() {
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 获取部门列表数据
     * Param SysDept PageDomain
     * Return 部门列表数据
     */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/dept/data','sys:dept:data')")
    public ResultTable data(SysDept param) {
        List<SysDept> data = sysDeptService.list(param);
        return dataTable(data);
    }

    /**
     * Describe: 获取部门树状数据结构
     * Param ModelAndView
     * Return ModelAndView
     */
    @GetMapping("tree")
    public ResultTree tree(SysDept param) {
        List<SysDept> data = sysDeptService.list(param);
        return dataTree(data);
    }

    /**
     * Describe: 获取部门新增视图
     * Param ModelAndView
     * Return 部门新增视图
     */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/dept/add','sys:dept:add')")
    public ModelAndView add() {
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 保存部门信息
     * Param SysDept
     * Return 执行结果
     */
    @PostMapping("save")
    @ApiOperation(value = "保存部门数据")
    @PreAuthorize("hasPermission('/system/dept/add','sys:dept:add')")
    public Result save(@RequestBody SysDept sysDept) {
        sysDept.setDeptId(SequenceUtil.makeStringId());
        boolean result = sysDeptService.save(sysDept);
        return decide(result);
    }

    /**
     * Describe: 获取部门修改视图
     * Param ModelAndView
     * Return 部门修改视图
     */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public ModelAndView edit(ModelAndView modelAndView, String deptId) {
        modelAndView.addObject("sysDept", sysDeptService.getById(deptId));
        modelAndView.setViewName(MODULE_PATH + "edit");
        return modelAndView;
    }

    /**
     * Describe: 修改部门信息
     * Param SysDept
     * Return 执行结果
     */
    @PutMapping("update")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public Result update(@RequestBody SysDept SysDept) {
        boolean result = sysDeptService.update(SysDept);
        return decide(result);
    }

    /**
     * Describe: 部门删除接口
     * Param: id
     * Return: Result
     */
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/dept/remove','sys:dept:remove')")
    public Result remove(@PathVariable String id) {
        boolean result = sysDeptService.remove(id);
        return decide(result);
    }

    /**
     * Describe: 部门批量删除接口
     * Param: ids
     * Return: Result
     */
    @DeleteMapping("batchRemove/{ids}")
    @PreAuthorize("hasPermission('/system/dept/remove','sys:dept:remove')")
    public Result batchRemove(@PathVariable String ids) {
        boolean result = sysDeptService.batchRemove(ids.split(","));
        return decide(result);
    }

    /**
     * Describe: 根据 Id 启用部门
     * Param: roleId
     * Return: Result
     */
    @PutMapping("enable")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public Result enable(@RequestBody SysDept SysDept) {
        SysDept.setStatus("0");
        boolean result = sysDeptService.update(SysDept);
        return decide(result);
    }

    /**
     * Describe: 根据 Id 禁用部门
     * Param: roleId
     * Return: Result
     */
    @PutMapping("disable")
    @PreAuthorize("hasPermission('/system/dept/edit','sys:dept:edit')")
    public Result disable(@RequestBody SysDept SysDept) {
        SysDept.setStatus("1");
        boolean result = sysDeptService.update(SysDept);
        return decide(result);
    }
}
