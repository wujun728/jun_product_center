//package com.jun.plugin.qixing.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.jun.plugin.common.utils.DataResult;
//import com.jun.plugin.qixing.service.SysPostService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.stereotype.Controller;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import java.util.List;
//import com.jun.plugin.common.aop.annotation.DataScope;
//
//import com.jun.plugin.qixing.entity.SysPostEntity;
//
//
///**
// * 岗位信息表
// *
// * @author wujun
// * @email wujun728@hotmail.com
// * @date 2021-10-27 11:37:16
// */
//@Controller
//@RequestMapping("/")
//@Slf4j
//public class SysPost2Controller {
//
////    @Autowired
////    private SysPostService sysPostService;
//
////    @Autowired
////    private SysPostMapper sysPostMapper;
//
//
//    /**
//    * 跳转到页面
//    */
//    @GetMapping("/index/sysPost")
//    public String sysPost() {
//        return "syspost/list";
//        }
//
//    @ApiOperation(value = "新增")
//    @PostMapping("sysPost/add")
//    //@RequiresPermissions("sysPost:add")
//    @ResponseBody
//    public DataResult add(@RequestBody SysPostEntity sysPost){
//        sysPostService.save(sysPost);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "删除")
//    @DeleteMapping("sysPost/delete")
//    //@RequiresPermissions("sysPost:delete")
//    @ResponseBody
//    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
//        sysPostService.removeByIds(ids);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "更新")
//    @PutMapping("sysPost/update")
//    //@RequiresPermissions("sysPost:update")
//    @ResponseBody
//    public DataResult update(@RequestBody SysPostEntity sysPost){
//        sysPostService.updateById(sysPost);
//        return DataResult.success();
//    }
//
//    @ApiOperation(value = "查询分页数据")
//    @PostMapping("sysPost/listByPage")
//    //@RequiresPermissions("sysPost:list")
//    @ResponseBody
//    @DataScope
//    public DataResult findListByPage(@RequestBody SysPostEntity sysPost){
//        Page page = new Page(sysPost.getPage(), sysPost.getLimit());
//        LambdaQueryWrapper<SysPostEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        //queryWrapper.eq(SysPostEntity::getId, sysPost.getId());
//        IPage<SysPostEntity> iPage = sysPostService.page(page, queryWrapper);
//        return DataResult.success(iPage);
//    }
//
//    @ApiOperation(value = "查询单条数据")
//    @PostMapping("sysPost/findOne")
//    @ResponseBody
//    public DataResult findOne(@RequestBody SysPostEntity sysPost){
//    	LambdaQueryWrapper<SysPostEntity> queryWrapper = Wrappers.lambdaQuery();
//    	//查询条件示例
//    	//queryWrapper.eq(SysPostEntity::getId, sysPost.getId());
//    	//SysPostEntity one = sysPostService.getOne(queryWrapper);
//    	SysPostEntity one = sysPostService.getById(sysPost.getId());
//    	return DataResult.success(one);
//    }
//
//    @ApiOperation(value = "查询下拉框数据")
//    @PostMapping("sysPost/listBySelect")
//    @ResponseBody
//    public DataResult findListBySelect(@RequestBody SysPostEntity sysPost){
//        Page page = new Page(sysPost.getPage(), sysPost.getLimit());
//        LambdaQueryWrapper<SysPostEntity> queryWrapper = Wrappers.lambdaQuery();
//        //查询条件示例
//        //queryWrapper.eq(SysPostEntity::getId, sysPost.getId());
//        IPage<SysPostEntity> iPage = sysPostService.page(page, queryWrapper);
//        //log.info("\n this.sysPostMapper.selectCountUser()="+this.sysPostMapper.selectCountUser());
//        return DataResult.success(iPage);
//    }
//
//}
