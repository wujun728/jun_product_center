package com.jun.plugin.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.common.Result;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.system.entity.SysContentEntity;
import com.jun.plugin.system.service.SysContentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 文章管理
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/sysContent")
public class SysContentController {
    @Resource
    private SysContentService sysContentService;


    @ApiOperation(value = "新增")
    @PostMapping("/add")
    //@RequiresPermissions("sysContent:add")
    public Result add(@RequestBody SysContentEntity sysContent) {
        sysContentService.save(sysContent);
        return Result.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    //@RequiresPermissions("sysContent:delete")
    public Result delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
        sysContentService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    //@RequiresPermissions("sysContent:update")
    public Result update(@RequestBody SysContentEntity sysContent) {
        sysContentService.updateById(sysContent);
        return Result.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    //@RequiresPermissions("sysContent:list")
    @DataScope
    public Result findListByPage(@RequestBody SysContentEntity sysContent) {
        Page page = new Page(sysContent.getPage(), sysContent.getLimit());
        LambdaQueryWrapper<SysContentEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!StringUtils.isEmpty(sysContent.getTitle())) {
            queryWrapper.like(SysContentEntity::getTitle, sysContent.getTitle());
        }
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(sysContent.getCreateIds())) {
            queryWrapper.in(SysContentEntity::getCreateId, sysContent.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end

        IPage<SysContentEntity> iPage = sysContentService.page(page, queryWrapper);
        return Result.success(iPage);
    }
}
