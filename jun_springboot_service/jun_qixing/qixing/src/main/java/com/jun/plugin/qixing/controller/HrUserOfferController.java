package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.jun.plugin.qixing.entity.HrUserOfferV2Entity;
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

import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.mapper.HrUserOfferMapper;
import com.jun.plugin.qixing.service.HrUserOfferService;



/**
 * Offer发放
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class HrUserOfferController {

    @Autowired
    private HrUserOfferService hrUserOfferService;

    @Autowired
    private HrUserOfferMapper hrUserOfferMapper;

//    @Resource
//    HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrUserOffer")
    public String hrUserOffer() {
        return "hruseroffer/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrUserOffer/add")
    //@RequiresPermissions("hrUserOffer:add")
    @ResponseBody
    public DataResult add(@RequestBody HrUserOfferV2Entity hrUserOffer){
        hrUserOfferService.save(hrUserOffer);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrUserOffer/delete")
    //@RequiresPermissions("hrUserOffer:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrUserOfferService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrUserOffer/update")
    //@RequiresPermissions("hrUserOffer:update")
    @ResponseBody
    public DataResult update(@RequestBody HrUserOfferV2Entity hrUserOffer){
        hrUserOfferService.updateById(hrUserOffer);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrUserOffer/listByPage")
    //@RequiresPermissions("hrUserOffer:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrUserOfferV2Entity hrUserOffer){
        Page page = new Page(hrUserOffer.getPage(), hrUserOffer.getLimit());
        LambdaQueryWrapper<HrUserOfferV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserOffer.getCreateIds())) {
            queryWrapper.in(HrUserOfferV2Entity::getCreateId, hrUserOffer.getCreateIds());
        }
        queryWrapper.like(HrUserOfferV2Entity::getRefJobUsername, hrUserOffer.getRefJobUsername()==null?"":hrUserOffer.getRefJobUsername());
        IPage<HrUserOfferV2Entity> iPage = hrUserOfferService.page(page, queryWrapper);
        List<HrUserOfferV2Entity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrUserOffer/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrUserOfferV2Entity hrUserOffer){
        Page page = new Page(hrUserOffer.getPage(), hrUserOffer.getLimit());
        LambdaQueryWrapper<HrUserOfferV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserOffer.getCreateIds())) {
            queryWrapper.in(HrUserOfferV2Entity::getCreateId, hrUserOffer.getCreateIds());
        }
        //queryWrapper.like(HrUserOfferEntity::getId, hrUserOffer.getId());
        IPage<HrUserOfferV2Entity> iPage = hrUserOfferService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrUserOffer/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrUserOfferV2Entity hrUserOffer){
    	LambdaQueryWrapper<HrUserOfferV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrUserOfferEntity::getId, hrUserOffer.getId());
    	//HrUserOfferEntity one = hrUserOfferService.getOne(queryWrapper);
    	HrUserOfferV2Entity one = hrUserOfferService.getById(hrUserOffer.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrUserOffer/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrUserOfferV2Entity hrUserOffer){
        Page page = new Page(hrUserOffer.getPage(), hrUserOffer.getLimit());
        LambdaQueryWrapper<HrUserOfferV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserOffer.getCreateIds())) {
            queryWrapper.in(HrUserOfferV2Entity::getCreateId, hrUserOffer.getCreateIds());
        }
        //queryWrapper.like(HrUserOfferEntity::getId, hrUserOffer.getId());
        IPage<HrUserOfferV2Entity> iPage = hrUserOfferService.page(page, queryWrapper);
        log.info("\n this.hrUserOfferMapper.selectCountUser()="+this.hrUserOfferMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
