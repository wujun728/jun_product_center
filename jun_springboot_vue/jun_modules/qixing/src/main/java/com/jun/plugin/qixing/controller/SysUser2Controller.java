package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.qixing.mapper.SysUser2Mapper;
import com.jun.plugin.qixing.service.SysUser2Service;

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

import com.jun.plugin.qixing.entity.SysUser2Entity;


/**
 * 用户信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-02 13:49:54
 */
@Controller
@RequestMapping("/")
@Slf4j
public class SysUser2Controller {

    @Autowired
    private SysUser2Service sysUser2Service;

    @Autowired
    private SysUser2Mapper sysUser2Mapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/sysUser2")
    public String sysUser2() {
        return "sysuser2/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("sysUser2/add")
    //@RequiresPermissions("sysUser2:add")
    @ResponseBody
    public DataResult add(@RequestBody SysUser2Entity sysUser2){
        sysUser2Service.save(sysUser2);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("sysUser2/delete")
    //@RequiresPermissions("sysUser2:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        sysUser2Service.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("sysUser2/update")
    //@RequiresPermissions("sysUser2:update")
    @ResponseBody
    public DataResult update(@RequestBody SysUser2Entity sysUser2){
        sysUser2Service.updateById(sysUser2);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("sysUser2/listByPage")
    //@RequiresPermissions("sysUser2:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody SysUser2Entity sysUser2){
        Page page = new Page(sysUser2.getPage(), sysUser2.getLimit());
        LambdaQueryWrapper<SysUser2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(SysUser2Entity::getId, sysUser2.getId()==null?"":sysUser2.getId());
        IPage<SysUser2Entity> iPage = sysUser2Service.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("sysUser2/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody SysUser2Entity sysUser2){
        Page page = new Page(sysUser2.getPage(), sysUser2.getLimit());
        LambdaQueryWrapper<SysUser2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(SysUser2Entity::getId, sysUser2.getId());
        IPage<SysUser2Entity> iPage = sysUser2Service.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("sysUser2/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody SysUser2Entity sysUser2){
    	LambdaQueryWrapper<SysUser2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(SysUser2Entity::getId, sysUser2.getId());
    	//SysUser2Entity one = sysUser2Service.getOne(queryWrapper);
    	SysUser2Entity one = sysUser2Service.getById(sysUser2.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("sysUser2/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody SysUser2Entity sysUser2){
        Page page = new Page(sysUser2.getPage(), sysUser2.getLimit());
        LambdaQueryWrapper<SysUser2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(SysUser2Entity::getId, sysUser2.getId());
        IPage<SysUser2Entity> iPage = sysUser2Service.page(page, queryWrapper);
        log.info("\n this.sysUser2Mapper.selectCountUser()="+this.sysUser2Mapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
