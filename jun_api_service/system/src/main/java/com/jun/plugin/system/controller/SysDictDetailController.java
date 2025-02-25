package com.jun.plugin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.common.Result;
import com.jun.plugin.system.entity.SysDictDetailEntity;
import com.jun.plugin.system.service.SysDictDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 字典明细管理
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "字典明细管理")
@RestController
@RequestMapping("/sysDictDetail")
public class SysDictDetailController {
    @Resource
    private SysDictDetailService sysDictDetailService;
    
//    update `sys_dict_detail` t set t.`value2` = t.value WHERE t.dict_id = 7504415258303672;
//    update `sys_dict_detail` t set t.`value` = t.label WHERE t.dict_id = 7504415258303672;
//    update `sys_dict_detail` t set t.`label` = t.value2 WHERE t.dict_id = 7504415258303672;
//    update `sys_dict_detail` t set t.`value2` = null WHERE t.dict_id = 7504415258303672;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    //@RequiresPermissions("sysDict:add")
    public Result add(@RequestBody SysDictDetailEntity sysDictDetail) {
        if (StringUtils.isEmpty(sysDictDetail.getValue())) {
            return Result.fail("字典值不能为空");
        }
        LambdaQueryWrapper<SysDictDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysDictDetailEntity::getValue, sysDictDetail.getValue());
        queryWrapper.eq(SysDictDetailEntity::getDictId, sysDictDetail.getDictId());
        SysDictDetailEntity q = sysDictDetailService.getOne(queryWrapper);
        if (q != null) {
            return Result.fail("字典名称-字典值已存在");
        }
        sysDictDetailService.save(sysDictDetail);
        return Result.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    //@RequiresPermissions("sysDict:delete")
    public Result delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
        sysDictDetailService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    //@RequiresPermissions("sysDict:update")
    public Result update(@RequestBody SysDictDetailEntity sysDictDetail) {
        if (StringUtils.isEmpty(sysDictDetail.getValue())) {
            return Result.fail("字典值不能为空");
        }
        LambdaQueryWrapper<SysDictDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysDictDetailEntity::getValue, sysDictDetail.getValue());
        queryWrapper.eq(SysDictDetailEntity::getDictId, sysDictDetail.getDictId());
        SysDictDetailEntity q = sysDictDetailService.getOne(queryWrapper);
        if (q != null && !q.getId().equals(sysDictDetail.getId())) {
            return Result.fail("字典名称-字典值已存在");
        }

        sysDictDetailService.updateById(sysDictDetail);
        return Result.success();
    }


    @ApiOperation(value = "查询列表数据")
    @PostMapping("/listByPage")
    //@RequiresPermissions("sysDict:list")
    public Result findListByPage(@RequestBody SysDictDetailEntity sysDictDetail) {
        Page page = new Page(sysDictDetail.getPage(), sysDictDetail.getLimit());
        if (StringUtils.isEmpty(sysDictDetail.getDictId())) {
            return Result.success();
        }
        IPage<SysDictDetailEntity> iPage = sysDictDetailService.listByPage(page, sysDictDetail.getDictId());
        return Result.success(iPage);
    }

}
