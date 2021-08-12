package com.pearadmin.system.controller;

import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.tools.sequence.SequenceUtil;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.response.module.ResultTree;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.system.domain.SysPower;
import com.pearadmin.system.service.ISysPowerService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: 权 限 控 制 器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@RestController
@Api(tags = {"系统权限"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "power")
public class SysPowerController extends BaseController {

    /**
     * Describe: 基础路径
     * */
    private static String MODULE_PATH = "system/power/";

    /**
     * Describe: 权限模块服务
     * */
    @Resource
    private ISysPowerService sysPowerService;


    /**
     * Describe: 获取权限列表视图
     * Param ModelAndView
     * Return 权限列表视图
     * */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/power/main','sys:power:main')")
    public ModelAndView main(ModelAndView modelAndView){
        return jumpPage(MODULE_PATH + "main");
    }

    /**
     * Describe: 获取权限列表数据
     * Param ModelAndView
     * Return 权限列表数据
     * */
    @GetMapping("data")
    @PreAuthorize("hasPermission('/system/power/data','sys:power:data')")
    public ResultTable data(SysPower sysPower){
        return treeTable(sysPowerService.list(sysPower));
    }

    /**
     * Describe: 获取权限新增视图
     * Param ModelAndView
     * Return 权限新增视图
     * */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/system/power/add','sys:power:add')")
    public ModelAndView add(){
        return jumpPage(MODULE_PATH + "add");
    }

    /**
     * Describe: 获取权限修改视图
     * Param ModelAndView
     * Return 权限修改视图
     * */
    @GetMapping("edit")
    @PreAuthorize("hasPermission('/system/power/edit','sys:power:edit')")
    public ModelAndView edit(Model model, String powerId){
        model.addAttribute("sysPower",sysPowerService.getById(powerId));
        return jumpPage(MODULE_PATH + "edit");
    }

    /**
     * Describe: 保存权限信息
     * Param: SysPower
     * Return: ResuBean
     * */
    @PostMapping("save")
    @PreAuthorize("hasPermission('/system/power/add','sys:power:add')")
    public Result save(@RequestBody SysPower sysPower){
        if(Strings.isBlank(sysPower.getParentId())){
            return failure("请选择上级菜单");
        }
        sysPower.setPowerId(SequenceUtil.makeStringId());
        boolean result = sysPowerService.save(sysPower);
        return decide(result);
    }

    /**
     * Describe: 修改权限信息
     * Param SysPower
     * Return 执行结果
     * */
    @PutMapping("update")
    @PreAuthorize("hasPermission('/system/power/edit','sys:power:edit')")
    public Result update(@RequestBody SysPower sysPower){
        if(Strings.isBlank(sysPower.getParentId())){
            return failure("请选择上级菜单");
        }
        boolean result = sysPowerService.update(sysPower);
        return decide(result);
    }

    /**
     * Describe: 根据 id 进行删除
     * Param id
     * Return Result
     * */
    @DeleteMapping("remove/{id}")
    @PreAuthorize("hasPermission('/system/power/remove','sys:power:remove')")
    public Result remove(@PathVariable String id){
        if(sysPowerService.selectByParentId(id).size() > 0) {
            return failure("请先删除下级权限");
        }
        boolean result = sysPowerService.remove(id);
        return decide(result);
    }

    /**
     * Describe: 获取父级权限选择数据
     * Param sysPower
     * Return Result
     * */
    @GetMapping("selectParent")
    public ResultTree selectParent(SysPower sysPower){
        List<SysPower> list = sysPowerService.list(sysPower);
        SysPower basePower = new SysPower();
        basePower.setPowerName("顶级权限");
        basePower.setPowerId("0");
        basePower.setParentId("-1");
        list.add(basePower);
        return dataTree(list);
    }

    /**
     * Describe: 根据 Id 开启用户
     * Param powerId
     * Return Result
     * */
    @PutMapping("enable")
    public Result enable(@RequestBody SysPower sysPower){
        sysPower.setEnable(true);
        boolean result = sysPowerService.update(sysPower);
        return decide(result);
    }

    /**
     * Describe: 根据 Id 禁用用户
     * Param powerId
     * Return Result
     * */
    @PutMapping("disable")
    public Result disable(@RequestBody SysPower sysPower){
        sysPower.setEnable(false);
        boolean result = sysPowerService.update(sysPower);
        return decide(result);
    }
}
