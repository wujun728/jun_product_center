package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.constant.TenantConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.service.SysPowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"权限"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "power")
public class SysPowerController extends BaseController {

    @Resource
    private SysPowerService sysPowerService;

    /**
     * 查询权限
     */
    @GetMapping("tree")
    @Log(title = "查询权限")
    @ApiOperation(value = "查询权限")
    public Result tree(){
        return success(sysPowerService.tree());
    }

    /**
     * 可分配的权限列表
     *
     * @return sysPower 权限实体
     * */
    @GetMapping("tree/assign")
    @ApiOperation(value = "可分配的权限")
    public Result treeAssign(){
        // Tenant 模式下 tenant_power 关联查询
        if(TenantConstant.enable) {
            return success(sysPowerService.treeByTenantId());
        } else {
            return success(sysPowerService.tree());
        }
    }

    /**
     * 新增权限
     *
     * @param sysPower 权限实体
     *
     * @return {@link Boolean}
     */
    @PostMapping("save")
    @Log(title = "新增权限")
    @ApiOperation(value = "新增权限")
    public Result save(@RequestBody SysPower sysPower){
        return auto(sysPowerService.save(sysPower));
    }

    /**
     * 修改权限
     *
     * @param sysPower 权限实体
     *
     * @return {@link Boolean}
     */
    @PutMapping("edit")
    @Log(title = "修改权限")
    @ApiOperation(value = "修改权限")
    public Result edit(@RequestBody SysPower sysPower){
        return auto(sysPowerService.updateById(sysPower));
    }

    /**
     * 删除权限
     *
     * @param id 权限编号
     *
     * @return {@link Boolean}
     */
    @DeleteMapping("remove")
    @Log(title = "删除权限")
    @ApiOperation(value = "删除权限")
    public Result remove(String id){
        return auto(sysPowerService.removeById(id));
    }

}
