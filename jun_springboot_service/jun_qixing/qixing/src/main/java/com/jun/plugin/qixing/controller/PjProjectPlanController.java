package com.jun.plugin.qixing.controller;

import java.util.List;

import javax.annotation.Resource;

import com.jun.plugin.common.utils.DataResult;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.qixing.entity.PjProjectPlanEntity;
import com.jun.plugin.qixing.mapper.PjProjectPlanMapper;
import com.jun.plugin.qixing.service.PjProjectPlanService;
import com.jun.plugin.snakerflow.process.BaseFlowController;
import com.jun.plugin.common.aop.annotation.DataScope;
//import com.jun.plugin.system.service.HttpSessionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;



/**
 * 项目计划
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 10:44:55
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectPlanController  extends BaseFlowController {

    @Autowired
    private PjProjectPlanService pjProjectPlanService;

    @Autowired
    private PjProjectPlanMapper pjProjectPlanMapper;
    
    //@Resource
   // HttpSessionService sessionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectPlan")
    public String pjProjectPlan() {
        return "pjprojectplan/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectPlan/add")
    //@RequiresPermissions("pjProjectPlan:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectPlanEntity pjProjectPlan){
        pjProjectPlanService.save(pjProjectPlan);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectPlan/delete")
    //@RequiresPermissions("pjProjectPlan:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectPlanService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectPlan/update")
    //@RequiresPermissions("pjProjectPlan:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectPlanEntity pjProjectPlan){
        pjProjectPlanService.updateById(pjProjectPlan);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectPlan/listByPage")
    //@RequiresPermissions("pjProjectPlan:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectPlanEntity pjProjectPlan){
        Page page = new Page(pjProjectPlan.getPage(), pjProjectPlan.getLimit());
        LambdaQueryWrapper<PjProjectPlanEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectPlan.getCreateIds())) {
            queryWrapper.in(PjProjectPlanEntity::getCreateId, pjProjectPlan.getCreateIds());
        }
        queryWrapper.like(PjProjectPlanEntity::getRefProjectName, pjProjectPlan.getRefProjectName()==null?"":pjProjectPlan.getRefProjectName());
        queryWrapper.like(PjProjectPlanEntity::getPlanName, pjProjectPlan.getPlanName()==null?"":pjProjectPlan.getPlanName());
        IPage<PjProjectPlanEntity> iPage = pjProjectPlanService.page(page, queryWrapper);
        List<PjProjectPlanEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						pjProjectPlanService.updateById(item);
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
    @PostMapping("pjProjectPlan/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody PjProjectPlanEntity pjProjectPlan){
        Page page = new Page(pjProjectPlan.getPage(), pjProjectPlan.getLimit());
        LambdaQueryWrapper<PjProjectPlanEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectPlan.getCreateIds())) {
            queryWrapper.in(PjProjectPlanEntity::getCreateId, pjProjectPlan.getCreateIds());
        }
        //queryWrapper.like(PjProjectPlanEntity::getId, pjProjectPlan.getId());
        IPage<PjProjectPlanEntity> iPage = pjProjectPlanService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectPlan/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectPlanEntity pjProjectPlan){
    	LambdaQueryWrapper<PjProjectPlanEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectPlanEntity::getId, pjProjectPlan.getId());
    	//PjProjectPlanEntity one = pjProjectPlanService.getOne(queryWrapper);
    	PjProjectPlanEntity one = pjProjectPlanService.getById(pjProjectPlan.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectPlan/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectPlanEntity pjProjectPlan){
        Page page = new Page(pjProjectPlan.getPage(), pjProjectPlan.getLimit());
        LambdaQueryWrapper<PjProjectPlanEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectPlan.getCreateIds())) {
            queryWrapper.in(PjProjectPlanEntity::getCreateId, pjProjectPlan.getCreateIds());
        }
        //queryWrapper.like(PjProjectPlanEntity::getId, pjProjectPlan.getId());
        IPage<PjProjectPlanEntity> iPage = pjProjectPlanService.page(page, queryWrapper);
        log.info("\n this.pjProjectPlanMapper.selectCountUser()="+this.pjProjectPlanMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
