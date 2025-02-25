package com.jun.plugin.bizservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;

import com.jun.plugin.bizservice.entity.BizTestEntity;
import com.jun.plugin.bizservice.service.BizTestService;



/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2022-02-28 16:28:58
 */
@Controller
@RequestMapping("/public")
public class BizTestController {
    @Autowired
    private BizTestService bizTestService;


    /**
    * 跳转到页面
    */
    @GetMapping("/test")
    @ResponseBody
    public Result bizTest1() {
        return  Result.success("11111111111");
    }
    /**
     * 跳转到页面
     */
    @GetMapping("/json")
    @ResponseBody
    public Result bizTest2() {
    	return Result.success(getClass());
    }
    
    /**
     * 跳转到页面
     */
    @GetMapping("/index/bizTest")
    public String bizTest() {
    	return "biztest/list";
    }

    @ApiOperation(value = "新增")
    @PostMapping("bizTest/add")
    @RequiresPermissions("bizTest:add")
    @ResponseBody
    public Result add(@RequestBody BizTestEntity bizTest){
        bizTestService.save(bizTest);
        return Result.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("bizTest/delete")
    @RequiresPermissions("bizTest:delete")
    @ResponseBody
    public Result delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        bizTestService.removeByIds(ids);
        return Result.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("bizTest/update")
    @RequiresPermissions("bizTest:update")
    @ResponseBody
    public Result update(@RequestBody BizTestEntity bizTest){
        bizTestService.updateById(bizTest);
        return Result.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("bizTest/listByPage")
    @RequiresPermissions("bizTest:list")
    @ResponseBody
    public Result findListByPage(@RequestBody BizTestEntity bizTest){
        Page page = new Page(bizTest.getPage(), bizTest.getLimit());
        LambdaQueryWrapper<BizTestEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(BizTestEntity::getId, bizTest.getId());
        IPage<BizTestEntity> iPage = bizTestService.page(page, queryWrapper);
        return Result.success(iPage);
    }

}
