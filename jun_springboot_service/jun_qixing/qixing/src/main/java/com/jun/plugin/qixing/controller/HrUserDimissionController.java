package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

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

import javax.annotation.Resource;

import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.entity.HrUserDimissionEntity;
import com.jun.plugin.qixing.mapper.HrUserDimissionMapper;
import com.jun.plugin.qixing.service.HrUserDimissionService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 离职
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrUserDimissionController  extends BaseFlowController{

    @Autowired
    private HrUserDimissionService hrUserDimissionService;

    @Autowired
    private HrUserDimissionMapper hrUserDimissionMapper;

    //@Resource
   // HttpSessionService sessionService;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrUserDimission")
    public String hrUserDimission() {
        return "hruserdimission/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrUserDimission/add")
    //@RequiresPermissions("hrUserDimission:add")
    @ResponseBody
    public DataResult add(@RequestBody HrUserDimissionEntity hrUserDimission){
        hrUserDimissionService.save(hrUserDimission);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrUserDimission/delete")
    //@RequiresPermissions("hrUserDimission:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrUserDimissionService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrUserDimission/update")
    //@RequiresPermissions("hrUserDimission:update")
    @ResponseBody
    public DataResult update(@RequestBody HrUserDimissionEntity hrUserDimission){
        hrUserDimissionService.updateById(hrUserDimission);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrUserDimission/listByPage")
    //@RequiresPermissions("hrUserDimission:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrUserDimissionEntity hrUserDimission){
        Page page = new Page(hrUserDimission.getPage(), hrUserDimission.getLimit());
        LambdaQueryWrapper<HrUserDimissionEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserDimission.getCreateIds())) {
            queryWrapper.in(HrUserDimissionEntity::getCreateId, hrUserDimission.getCreateIds());
        }
        queryWrapper.like(HrUserDimissionEntity::getUsername, hrUserDimission.getUsername()==null?"":hrUserDimission.getUsername());
        IPage<HrUserDimissionEntity> iPage = hrUserDimissionService.page(page, queryWrapper);
        List<HrUserDimissionEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						hrUserDimissionService.updateById(item);
					}
				}
			}else {
				item.setOrderState(item.getOrderStatus());
			}
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}

		});
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrUserDimission/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrUserDimissionEntity hrUserDimission){
        Page page = new Page(hrUserDimission.getPage(), hrUserDimission.getLimit());
        LambdaQueryWrapper<HrUserDimissionEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserDimission.getCreateIds())) {
            queryWrapper.in(HrUserDimissionEntity::getCreateId, hrUserDimission.getCreateIds());
        }
        //queryWrapper.like(HrUserDimissionEntity::getId, hrUserDimission.getId());
        IPage<HrUserDimissionEntity> iPage = hrUserDimissionService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrUserDimission/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrUserDimissionEntity hrUserDimission){
    	LambdaQueryWrapper<HrUserDimissionEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrUserDimissionEntity::getId, hrUserDimission.getId());
    	//HrUserDimissionEntity one = hrUserDimissionService.getOne(queryWrapper);
    	HrUserDimissionEntity one = hrUserDimissionService.getById(hrUserDimission.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrUserDimission/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrUserDimissionEntity hrUserDimission){
        Page page = new Page(hrUserDimission.getPage(), hrUserDimission.getLimit());
        LambdaQueryWrapper<HrUserDimissionEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserDimission.getCreateIds())) {
            queryWrapper.in(HrUserDimissionEntity::getCreateId, hrUserDimission.getCreateIds());
        }
        //queryWrapper.like(HrUserDimissionEntity::getId, hrUserDimission.getId());
        IPage<HrUserDimissionEntity> iPage = hrUserDimissionService.page(page, queryWrapper);
        log.info("\n this.hrUserDimissionMapper.selectCountUser()="+this.hrUserDimissionMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
