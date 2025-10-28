package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

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
import com.jun.plugin.qixing.entity.HrUserEntryReportedV2Entity;
import com.jun.plugin.qixing.mapper.HrUserEntryReportedMapper;
import com.jun.plugin.qixing.service.HrUserEntryReportedService;



/**
 * 入职报道
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class HrUserEntryReportedController {

    @Autowired
    private HrUserEntryReportedService hrUserEntryReportedService;

    @Autowired
    private HrUserEntryReportedMapper hrUserEntryReportedMapper;

    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrUserEntryReported")
    public String hrUserEntryReported() {
        return "hruserentryreported/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrUserEntryReported/add")
    //@RequiresPermissions("hrUserEntryReported:add")
    @ResponseBody
    public DataResult add(@RequestBody HrUserEntryReportedV2Entity hrUserEntryReported){
        hrUserEntryReportedService.save(hrUserEntryReported);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrUserEntryReported/delete")
    //@RequiresPermissions("hrUserEntryReported:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrUserEntryReportedService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrUserEntryReported/update")
    //@RequiresPermissions("hrUserEntryReported:update")
    @ResponseBody
    public DataResult update(@RequestBody HrUserEntryReportedV2Entity hrUserEntryReported){
        hrUserEntryReportedService.updateById(hrUserEntryReported);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrUserEntryReported/listByPage")
    //@RequiresPermissions("hrUserEntryReported:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrUserEntryReportedV2Entity hrUserEntryReported){
        Page page = new Page(hrUserEntryReported.getPage(), hrUserEntryReported.getLimit());
        LambdaQueryWrapper<HrUserEntryReportedV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserEntryReported.getCreateIds())) {
            queryWrapper.in(HrUserEntryReportedV2Entity::getCreateId, hrUserEntryReported.getCreateIds());
        }
        queryWrapper.like(HrUserEntryReportedV2Entity::getRefJobUsername, hrUserEntryReported.getRefJobUsername()==null?"":hrUserEntryReported.getRefJobUsername());
        IPage<HrUserEntryReportedV2Entity> iPage = hrUserEntryReportedService.page(page, queryWrapper);
        List<HrUserEntryReportedV2Entity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}

		});
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrUserEntryReported/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrUserEntryReportedV2Entity hrUserEntryReported){
        Page page = new Page(hrUserEntryReported.getPage(), hrUserEntryReported.getLimit());
        LambdaQueryWrapper<HrUserEntryReportedV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserEntryReported.getCreateIds())) {
            queryWrapper.in(HrUserEntryReportedV2Entity::getCreateId, hrUserEntryReported.getCreateIds());
        }
        //queryWrapper.like(HrUserEntryReportedEntity::getId, hrUserEntryReported.getId());
        IPage<HrUserEntryReportedV2Entity> iPage = hrUserEntryReportedService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrUserEntryReported/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrUserEntryReportedV2Entity hrUserEntryReported){
    	LambdaQueryWrapper<HrUserEntryReportedV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrUserEntryReportedEntity::getId, hrUserEntryReported.getId());
    	//HrUserEntryReportedEntity one = hrUserEntryReportedService.getOne(queryWrapper);
    	HrUserEntryReportedV2Entity one = hrUserEntryReportedService.getById(hrUserEntryReported.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrUserEntryReported/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrUserEntryReportedV2Entity hrUserEntryReported){
        Page page = new Page(hrUserEntryReported.getPage(), hrUserEntryReported.getLimit());
        LambdaQueryWrapper<HrUserEntryReportedV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserEntryReported.getCreateIds())) {
            queryWrapper.in(HrUserEntryReportedV2Entity::getCreateId, hrUserEntryReported.getCreateIds());
        }
        //queryWrapper.like(HrUserEntryReportedEntity::getId, hrUserEntryReported.getId());
        IPage<HrUserEntryReportedV2Entity> iPage = hrUserEntryReportedService.page(page, queryWrapper);
        log.info("\n this.hrUserEntryReportedMapper.selectCountUser()="+this.hrUserEntryReportedMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
