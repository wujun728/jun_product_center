package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.jun.plugin.qixing.entity.PjProjectReportnumberV2Entity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.jun.plugin.common.aop.annotation.DataScope;

import com.jun.plugin.qixing.mapper.PjProjectReportnumberMapper;
import com.jun.plugin.qixing.service.PjProjectReportnumberService;



/**
 * 项目报告文号
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 10:44:42
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class PjProjectReportnumberController {

    @Autowired
    private PjProjectReportnumberService pjProjectReportnumberService;

    @Autowired
    private PjProjectReportnumberMapper pjProjectReportnumberMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectReportnumber")
    public String pjProjectReportnumber() {
        return "pjprojectreportnumber/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectReportnumber/add")
    //@RequiresPermissions("pjProjectReportnumber:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectReportnumberV2Entity pjProjectReportnumber){
        pjProjectReportnumberService.save(pjProjectReportnumber);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectReportnumber/delete")
    //@RequiresPermissions("pjProjectReportnumber:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectReportnumberService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectReportnumber/update")
    //@RequiresPermissions("pjProjectReportnumber:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectReportnumberV2Entity pjProjectReportnumber){
        pjProjectReportnumberService.updateById(pjProjectReportnumber);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectReportnumber/listByPage")
    //@RequiresPermissions("pjProjectReportnumber:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectReportnumberV2Entity pjProjectReportnumber){
        Page page = new Page(pjProjectReportnumber.getPage(), pjProjectReportnumber.getLimit());
        LambdaQueryWrapper<PjProjectReportnumberV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjProjectReportnumberV2Entity::getRefReportnumberTitle, pjProjectReportnumber.getRefReportnumberTitle()==null?"":pjProjectReportnumber.getRefReportnumberTitle());
        queryWrapper.like(PjProjectReportnumberV2Entity::getReportnumberCode, pjProjectReportnumber.getReportnumberCode()==null?"":pjProjectReportnumber.getReportnumberCode());
        IPage<PjProjectReportnumberV2Entity> iPage = pjProjectReportnumberService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("pjProjectReportnumber/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody PjProjectReportnumberV2Entity pjProjectReportnumber){
        Page page = new Page(pjProjectReportnumber.getPage(), pjProjectReportnumber.getLimit());
        LambdaQueryWrapper<PjProjectReportnumberV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjProjectReportnumberEntity::getId, pjProjectReportnumber.getId());
        IPage<PjProjectReportnumberV2Entity> iPage = pjProjectReportnumberService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectReportnumber/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectReportnumberV2Entity pjProjectReportnumber){
    	LambdaQueryWrapper<PjProjectReportnumberV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectReportnumberEntity::getId, pjProjectReportnumber.getId());
//    	PjProjectReportnumberEntity one = pjProjectReportnumberService.getOne(queryWrapper);
    	PjProjectReportnumberV2Entity one = pjProjectReportnumberService.getById(pjProjectReportnumber.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectReportnumber/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody PjProjectReportnumberV2Entity pjProjectReportnumber){
        Page page = new Page(pjProjectReportnumber.getPage(), pjProjectReportnumber.getLimit());
        LambdaQueryWrapper<PjProjectReportnumberV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjProjectReportnumberEntity::getId, pjProjectReportnumber.getId());
        IPage<PjProjectReportnumberV2Entity> iPage = pjProjectReportnumberService.page(page, queryWrapper);
        log.info("\n this.pjProjectReportnumberMapper.selectCountUser()="+this.pjProjectReportnumberMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
