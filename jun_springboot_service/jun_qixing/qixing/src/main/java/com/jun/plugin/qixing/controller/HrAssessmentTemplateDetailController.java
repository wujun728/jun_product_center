package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

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
import com.jun.plugin.snakerflow.process.BaseFlowController;

import com.jun.plugin.qixing.entity.HrAssessmentTemplateDetailEntity;
import com.jun.plugin.qixing.mapper.HrAssessmentTemplateDetailMapper;
import com.jun.plugin.qixing.service.HrAssessmentTemplateDetailService;



/**
 * 考核模板明细
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 01:48:53
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrAssessmentTemplateDetailController  extends BaseFlowController{

    @Autowired
    private HrAssessmentTemplateDetailService hrAssessmentTemplateDetailService;

    @Autowired
    private HrAssessmentTemplateDetailMapper hrAssessmentTemplateDetailMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrAssessmentTemplateDetail")
    public String hrAssessmentTemplateDetail() {
        return "hrassessmenttemplatedetail/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrAssessmentTemplateDetail/add")
    //@RequiresPermissions("hrAssessmentTemplateDetail:add")
    @ResponseBody
    public DataResult add(@RequestBody HrAssessmentTemplateDetailEntity hrAssessmentTemplateDetail){
        hrAssessmentTemplateDetailService.save(hrAssessmentTemplateDetail);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrAssessmentTemplateDetail/delete")
    //@RequiresPermissions("hrAssessmentTemplateDetail:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrAssessmentTemplateDetailService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrAssessmentTemplateDetail/update")
    //@RequiresPermissions("hrAssessmentTemplateDetail:update")
    @ResponseBody
    public DataResult update(@RequestBody HrAssessmentTemplateDetailEntity hrAssessmentTemplateDetail){
        hrAssessmentTemplateDetailService.updateById(hrAssessmentTemplateDetail);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrAssessmentTemplateDetail/listByPage")
    //@RequiresPermissions("hrAssessmentTemplateDetail:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrAssessmentTemplateDetailEntity hrAssessmentTemplateDetail){
        Page page = new Page(hrAssessmentTemplateDetail.getPage(), hrAssessmentTemplateDetail.getLimit());
        LambdaQueryWrapper<HrAssessmentTemplateDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentTemplateDetail.getCreateIds())) {
            queryWrapper.in(HrAssessmentTemplateDetailEntity::getCreateId, hrAssessmentTemplateDetail.getCreateIds());
        }
        queryWrapper.orderByAsc(HrAssessmentTemplateDetailEntity::getRefTeamplateName,HrAssessmentTemplateDetailEntity::getSortno);
        //数据权限示例， 需手动添加此条件 end
        queryWrapper.like(HrAssessmentTemplateDetailEntity::getRefTeamplateName, hrAssessmentTemplateDetail.getRefTeamplateName()==null?"":hrAssessmentTemplateDetail.getRefTeamplateName());
        IPage<HrAssessmentTemplateDetailEntity> iPage = hrAssessmentTemplateDetailService.page(page, queryWrapper);
        //关联流程查询待办、处理人、状态 
        List<HrAssessmentTemplateDetailEntity> records = iPage.getRecords();
        records.forEach(item -> {
        	if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						hrAssessmentTemplateDetailService.updateById(item);
					}
				}
			}else {
				item.setOrderState(item.getOrderStatus());
			}

		});
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrAssessmentTemplateDetail/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody HrAssessmentTemplateDetailEntity hrAssessmentTemplateDetail){
        Page page = new Page(hrAssessmentTemplateDetail.getPage(), hrAssessmentTemplateDetail.getLimit());
        LambdaQueryWrapper<HrAssessmentTemplateDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentTemplateDetail.getCreateIds())) {
            queryWrapper.in(HrAssessmentTemplateDetailEntity::getCreateId, hrAssessmentTemplateDetail.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //查询条件示例
        //queryWrapper.like(HrAssessmentTemplateDetailEntity::getId, hrAssessmentTemplateDetail.getId());
        IPage<HrAssessmentTemplateDetailEntity> iPage = hrAssessmentTemplateDetailService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrAssessmentTemplateDetail/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrAssessmentTemplateDetailEntity hrAssessmentTemplateDetail){
    	LambdaQueryWrapper<HrAssessmentTemplateDetailEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrAssessmentTemplateDetailEntity::getId, hrAssessmentTemplateDetail.getId());
    	//HrAssessmentTemplateDetailEntity one = hrAssessmentTemplateDetailService.getOne(queryWrapper);
    	HrAssessmentTemplateDetailEntity one = hrAssessmentTemplateDetailService.getById(hrAssessmentTemplateDetail.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrAssessmentTemplateDetail/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody HrAssessmentTemplateDetailEntity hrAssessmentTemplateDetail){
        Page page = new Page(hrAssessmentTemplateDetail.getPage(), hrAssessmentTemplateDetail.getLimit());
        LambdaQueryWrapper<HrAssessmentTemplateDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentTemplateDetail.getCreateIds())) {
            queryWrapper.in(HrAssessmentTemplateDetailEntity::getCreateId, hrAssessmentTemplateDetail.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //queryWrapper.like(HrAssessmentTemplateDetailEntity::getId, hrAssessmentTemplateDetail.getId());
        IPage<HrAssessmentTemplateDetailEntity> iPage = hrAssessmentTemplateDetailService.page(page, queryWrapper);
        log.info("\n this.hrAssessmentTemplateDetailMapper.selectCountUser()="+this.hrAssessmentTemplateDetailMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
