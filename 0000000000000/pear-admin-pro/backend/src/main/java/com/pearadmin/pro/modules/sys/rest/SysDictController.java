package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.modules.sys.domain.SysDict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.pearadmin.pro.modules.sys.param.SysDictRequest;
import com.pearadmin.pro.modules.sys.service.SysDictService;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典类型控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/28
 * */
@Api(tags = {"字典"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dict")
public class SysDictController extends BaseController {

    @Resource
    private SysDictService sysDictService;

    /**
     * 查询字典类型列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "字典列表")
    @ApiOperation(value = "字典列表")
    public Result page(SysDictRequest request){
       return success(sysDictService.page(request));
    }

    @PostMapping("save")
    @Log(title = "新增字典")
    @ApiOperation(value = "新增字典")
    public Result save(@RequestBody SysDict sysDict) {
        return auto(sysDictService.save(sysDict));
    }

    @PutMapping("edit")
    @Log(title = "修改字典")
    @ApiOperation(value = "修改字典")
    public Result edit(@RequestBody SysDict sysDict) {
        return auto(sysDictService.updateById(sysDict));
    }

    @DeleteMapping("remove")
    @Log(title = "删除字典")
    @ApiOperation(value = "删除字典")
    public Result remove(@RequestParam String id) {
        return auto(sysDictService.removeById(id));
    }

    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysDictService.removeByIds(ids));
    }
}