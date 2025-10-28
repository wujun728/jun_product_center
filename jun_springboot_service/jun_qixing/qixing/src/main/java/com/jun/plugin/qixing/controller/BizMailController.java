package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.jun.plugin.qixing.entity.BizMailV2Entity;
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

import com.jun.plugin.qixing.mapper.BizMailMapper;
import com.jun.plugin.qixing.service.BizMailService;



/**
 * 短信&消息&邮件
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class BizMailController {

    @Autowired
    private BizMailService bizMailService;

    @Autowired
    private BizMailMapper bizMailMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/bizMail")
    public String bizMail() {
        return "bizmail/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("bizMail/add")
    //@RequiresPermissions("bizMail:add")
    @ResponseBody
    public DataResult add(@RequestBody BizMailV2Entity bizMail){
        bizMailService.save(bizMail);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("bizMail/delete")
    //@RequiresPermissions("bizMail:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        bizMailService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("bizMail/update")
    //@RequiresPermissions("bizMail:update")
    @ResponseBody
    public DataResult update(@RequestBody BizMailV2Entity bizMail){
        bizMailService.updateById(bizMail);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("bizMail/listByPage")
    //@RequiresPermissions("bizMail:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody BizMailV2Entity bizMail){
        Page page = new Page(bizMail.getPage(), bizMail.getLimit());
        LambdaQueryWrapper<BizMailV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(BizMailEntity::getId, bizMail.getId());
        IPage<BizMailV2Entity> iPage = bizMailService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("bizMail/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody BizMailV2Entity bizMail){
    	LambdaQueryWrapper<BizMailV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(BizMailEntity::getId, bizMail.getId());
    	//BizMailEntity one = bizMailService.getOne(queryWrapper);
    	BizMailV2Entity one = bizMailService.getById(bizMail.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("bizMail/listBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody BizMailV2Entity bizMail){
        Page page = new Page(bizMail.getPage(), bizMail.getLimit());
        LambdaQueryWrapper<BizMailV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(BizMailEntity::getId, bizMail.getId());
        IPage<BizMailV2Entity> iPage = bizMailService.page(page, queryWrapper);
        log.info("\n this.bizMailMapper.selectCountUser()="+this.bizMailMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
