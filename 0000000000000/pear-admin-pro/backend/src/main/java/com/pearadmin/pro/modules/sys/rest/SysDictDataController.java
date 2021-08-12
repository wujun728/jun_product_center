package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.param.SysDictDataRequest;
import com.pearadmin.pro.modules.sys.service.SysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典值控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"字典"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dict/data")
public class SysDictDataController extends BaseController {

    @Resource
    private SysDictDataService sysDictDataService;

    /**
     * 查询字典值列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "字典数据列表")
    @ApiOperation(value = "字典数据列表")
    public Result page(SysDictDataRequest request){
       return success(sysDictDataService.page(request));
    }

    @PostMapping("save")
    @Log(title = "新增字典数据")
    @ApiOperation(value = "新增字典数据")
    public Result save(@RequestBody SysDictData sysDictData){
        return auto(sysDictDataService.save(sysDictData));
    }

    @PutMapping("edit")
    @Log(title = "修改字典数据")
    @ApiOperation(value = "修改字典数据")
    public Result edit(@RequestBody SysDictData sysDictData){
        return auto(sysDictDataService.updateById(sysDictData));
    }

    @DeleteMapping("remove")
    @Log(title = "删除字典数据")
    @ApiOperation(value = "删除字典数据")
    public Result remove(@RequestParam String id){
        return auto(sysDictDataService.removeById(id));
    }

    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids){
        return auto(sysDictDataService.removeByIds(ids));
    }
}
