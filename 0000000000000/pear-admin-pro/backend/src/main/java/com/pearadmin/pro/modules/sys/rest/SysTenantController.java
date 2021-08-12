package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysTenant;
import com.pearadmin.pro.modules.sys.param.SysTenantGiveRequest;
import com.pearadmin.pro.modules.sys.param.SysTenantRequest;
import com.pearadmin.pro.modules.sys.param.SysTenantSaveRequest;
import com.pearadmin.pro.modules.sys.service.SysTenantService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 租户控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "tenant")
public class SysTenantController extends BaseController {

    @Resource
    private SysTenantService sysTenantService;

    /**
     * 查询租户
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    public Result page(SysTenantRequest request) {
        return success(sysTenantService.page(request));
    }

    /**
     * 查询租户
     *
     * @param request 查询参数
     * */
    @GetMapping("list")
    public Result list(SysTenantRequest request) { return success(sysTenantService.list(request)); }

    /**
     * 新增租户
     *
     * @param request 租户实体 / 用户实体 / 归属权限
     */
    @PostMapping("save")
    public Result save(@RequestBody SysTenantSaveRequest request) {
        return success(sysTenantService.save(request));
    }

    /**
     * 权限分配
     *
     * @param request 分配关系
     * */
    @PostMapping("give")
    public Result give(@RequestBody SysTenantGiveRequest request) {
        return success(sysTenantService.give(request));
    }

    /**
     * 修改租户
     *
     * @param sysTenant 租户实体
     */
    @PutMapping("edit")
    public Result edit(@RequestBody SysTenant sysTenant) {
        return success(sysTenantService.updateById(sysTenant));
    }

    /**
     * 删除租户
     *
     * @param id 租户编号
     */
    @DeleteMapping("remove")
    public Result remove(@RequestParam String id) {
        return success(sysTenantService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids 租户编号
     */
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestParam List<String> ids) {
        return success(sysTenantService.removeByIds(ids));
    }

    /**
     * 租户权限
     *
     * @param
     * */
    @GetMapping("power")
    public Result power(String tenantId) {
        return success(sysTenantService.power(tenantId));
    }
}