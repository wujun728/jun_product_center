package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.qixing.entity.PjProjectRecheckV2Entity;
import com.jun.plugin.qixing.service.PjProjectRecheckService;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.mapper.PjProjectRecheckMapper;


/**
 * 项目复核
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class PjProjectRecheckController {

    @Autowired
    private PjProjectRecheckService pjProjectRecheckService;

    @Autowired
    private PjProjectRecheckMapper pjProjectRecheckMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectRecheck")
    public String pjProjectRecheck() {
        return "pjprojectrecheck/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectRecheck/add")
    //@RequiresPermissions("pjProjectRecheck:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectRecheckV2Entity pjProjectRecheck){
        pjProjectRecheckService.save(pjProjectRecheck);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectRecheck/delete")
    //@RequiresPermissions("pjProjectRecheck:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectRecheckService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectRecheck/update")
    //@RequiresPermissions("pjProjectRecheck:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectRecheckV2Entity pjProjectRecheck){
        pjProjectRecheckService.updateById(pjProjectRecheck);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectRecheck/listByPage")
    //@RequiresPermissions("pjProjectRecheck:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectRecheckV2Entity pjProjectRecheck){
        Page page = new Page(pjProjectRecheck.getPage(), pjProjectRecheck.getLimit());
        LambdaQueryWrapper<PjProjectRecheckV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectRecheck.getCreateIds())) {
            queryWrapper.in(PjProjectRecheckV2Entity::getCreateId, pjProjectRecheck.getCreateIds());
        }
        //queryWrapper.eq(PjProjectRecheckEntity::getId, pjProjectRecheck.getId());
        IPage<PjProjectRecheckV2Entity> iPage = pjProjectRecheckService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectRecheck/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectRecheckV2Entity pjProjectRecheck){
    	LambdaQueryWrapper<PjProjectRecheckV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectRecheckEntity::getId, pjProjectRecheck.getId());
    	//PjProjectRecheckEntity one = pjProjectRecheckService.getOne(queryWrapper);
    	PjProjectRecheckV2Entity one = pjProjectRecheckService.getById(pjProjectRecheck.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectRecheck/listBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody PjProjectRecheckV2Entity pjProjectRecheck){
        Page page = new Page(pjProjectRecheck.getPage(), pjProjectRecheck.getLimit());
        LambdaQueryWrapper<PjProjectRecheckV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectRecheck.getCreateIds())) {
            queryWrapper.in(PjProjectRecheckV2Entity::getCreateId, pjProjectRecheck.getCreateIds());
        }
        //queryWrapper.eq(PjProjectRecheckEntity::getId, pjProjectRecheck.getId());
        IPage<PjProjectRecheckV2Entity> iPage = pjProjectRecheckService.page(page, queryWrapper);
        log.info("\n this.pjProjectRecheckMapper.selectCountUser()="+this.pjProjectRecheckMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
