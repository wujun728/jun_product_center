package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.jun.plugin.qixing.entity.HrUserInterviewV2Entity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.SecurityUtils;
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

//import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.mapper.HrUserInterviewMapper;
import com.jun.plugin.qixing.service.HrUserInterviewService;



/**
 * 面试汇总
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class HrUserInterviewController {

    @Autowired
    private HrUserInterviewService hrUserInterviewService;

    @Autowired
    private HrUserInterviewMapper hrUserInterviewMapper;

    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrUserInterview")
    public String hrUserInterview() {
        return "hruserinterview/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrUserInterview/add")
    //@RequiresPermissions("hrUserInterview:add")
    @ResponseBody
    public DataResult add(@RequestBody HrUserInterviewV2Entity hrUserInterview){
        hrUserInterviewService.save(hrUserInterview);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrUserInterview/delete")
    //@RequiresPermissions("hrUserInterview:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrUserInterviewService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrUserInterview/update")
    //@RequiresPermissions("hrUserInterview:update")
    @ResponseBody
    public DataResult update(@RequestBody HrUserInterviewV2Entity hrUserInterview){
        hrUserInterviewService.updateById(hrUserInterview);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrUserInterview/listByPage")
    //@RequiresPermissions("hrUserInterview:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrUserInterviewV2Entity hrUserInterview){
        Page page = new Page(hrUserInterview.getPage(), hrUserInterview.getLimit());
        LambdaQueryWrapper<HrUserInterviewV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserInterview.getCreateIds())) {
            queryWrapper.in(HrUserInterviewV2Entity::getCreateId, hrUserInterview.getCreateIds());
        }
        queryWrapper.like(HrUserInterviewV2Entity::getRefIvUsername, hrUserInterview.getRefIvUsername()==null?"":hrUserInterview.getRefIvUsername());
        queryWrapper.like(HrUserInterviewV2Entity::getRefPeopleName, hrUserInterview.getRefPeopleName()==null?"":hrUserInterview.getRefPeopleName());
        IPage<HrUserInterviewV2Entity> iPage = hrUserInterviewService.page(page, queryWrapper);
        List<HrUserInterviewV2Entity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrUserInterview/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrUserInterviewV2Entity hrUserInterview){
        Page page = new Page(hrUserInterview.getPage(), hrUserInterview.getLimit());
        LambdaQueryWrapper<HrUserInterviewV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserInterview.getCreateIds())) {
            queryWrapper.in(HrUserInterviewV2Entity::getCreateId, hrUserInterview.getCreateIds());
        }
        //queryWrapper.like(HrUserInterviewEntity::getId, hrUserInterview.getId());
        IPage<HrUserInterviewV2Entity> iPage = hrUserInterviewService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrUserInterview/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrUserInterviewV2Entity hrUserInterview){
    	LambdaQueryWrapper<HrUserInterviewV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrUserInterviewEntity::getId, hrUserInterview.getId());
    	//HrUserInterviewEntity one = hrUserInterviewService.getOne(queryWrapper);
    	HrUserInterviewV2Entity one = hrUserInterviewService.getById(hrUserInterview.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrUserInterview/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrUserInterviewV2Entity hrUserInterview){
        Page page = new Page(hrUserInterview.getPage(), hrUserInterview.getLimit());
        LambdaQueryWrapper<HrUserInterviewV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserInterview.getCreateIds())) {
            queryWrapper.in(HrUserInterviewV2Entity::getCreateId, hrUserInterview.getCreateIds());
        }
        //queryWrapper.like(HrUserInterviewEntity::getId, hrUserInterview.getId());
        IPage<HrUserInterviewV2Entity> iPage = hrUserInterviewService.page(page, queryWrapper);
        log.info("\n this.hrUserInterviewMapper.selectCountUser()="+this.hrUserInterviewMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
