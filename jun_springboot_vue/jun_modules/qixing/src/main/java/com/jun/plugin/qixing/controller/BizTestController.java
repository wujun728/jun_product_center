package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

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

import com.jun.plugin.qixing.entity.BizTestEntity;
import com.jun.plugin.qixing.mapper.BizTestMapper;
import com.jun.plugin.qixing.service.BizTestService;



/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
@Controller
@RequestMapping("/")
@Slf4j
public class BizTestController {

    @Autowired
    private BizTestService bizTestService;

    @Autowired
    private BizTestMapper bizTestMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/bizTest")
    public String bizTest() {
        return "biztest/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("bizTest/add")
    //@RequiresPermissions("bizTest:add")
    @ResponseBody
    public DataResult add(@RequestBody BizTestEntity bizTest){
        bizTestService.save(bizTest);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("bizTest/delete")
    //@RequiresPermissions("bizTest:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        bizTestService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("bizTest/update")
    //@RequiresPermissions("bizTest:update")
    @ResponseBody
    public DataResult update(@RequestBody BizTestEntity bizTest){
        bizTestService.updateById(bizTest);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("bizTest/listByPage")
    //@RequiresPermissions("bizTest:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody BizTestEntity bizTest){
        Page page = new Page(bizTest.getPage(), bizTest.getLimit());
        LambdaQueryWrapper<BizTestEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(BizTestEntity::getId, bizTest.getId());
        IPage<BizTestEntity> iPage = bizTestService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("bizTest/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody BizTestEntity bizTest){
    	LambdaQueryWrapper<BizTestEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(BizTestEntity::getId, bizTest.getId());
    	//BizTestEntity one = bizTestService.getOne(queryWrapper);
    	BizTestEntity one = bizTestService.getById(bizTest.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("bizTest/listBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody BizTestEntity bizTest){
        Page page = new Page(bizTest.getPage(), bizTest.getLimit());
        LambdaQueryWrapper<BizTestEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(BizTestEntity::getId, bizTest.getId());
        IPage<BizTestEntity> iPage = bizTestService.page(page, queryWrapper);
        log.info("\n this.bizTestMapper.selectCountUser()="+this.bizTestMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
