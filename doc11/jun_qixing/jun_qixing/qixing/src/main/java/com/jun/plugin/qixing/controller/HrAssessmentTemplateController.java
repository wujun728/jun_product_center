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
import com.jun.plugin.snakerflow.process.BaseFlowController;

import com.jun.plugin.qixing.entity.HrAssessmentTemplateEntity;
import com.jun.plugin.qixing.mapper.HrAssessmentTemplateMapper;
import com.jun.plugin.qixing.service.HrAssessmentTemplateService;



/**
 * 考核模板
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 01:48:53
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrAssessmentTemplateController  extends BaseFlowController{

    @Autowired
    private HrAssessmentTemplateService hrAssessmentTemplateService;

    @Autowired
    private HrAssessmentTemplateMapper hrAssessmentTemplateMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrAssessmentTemplate")
    public String hrAssessmentTemplate() {
        return "hrassessmenttemplate/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrAssessmentTemplate/add")
    //@RequiresPermissions("hrAssessmentTemplate:add")
    @ResponseBody
    public DataResult add(@RequestBody HrAssessmentTemplateEntity hrAssessmentTemplate){
    	if(this.checkExists(hrAssessmentTemplate)) {
    		return DataResult.fail("同名记录信息已存在！");
    	}
        hrAssessmentTemplateService.save(hrAssessmentTemplate);
        return DataResult.success();
    }
    
    @PostMapping("hrAssessmentTemplate/checkExists")
    @ResponseBody
    public Boolean checkExists(@RequestBody HrAssessmentTemplateEntity hrAssessmentTemplate){
    	LambdaQueryWrapper<HrAssessmentTemplateEntity> queryWrapper = Wrappers.lambdaQuery();
    	queryWrapper.eq(HrAssessmentTemplateEntity::getTeamplateName, hrAssessmentTemplate.getTeamplateName());
    	HrAssessmentTemplateEntity one = hrAssessmentTemplateService.getOne(queryWrapper.last("LIMIT 1"));
    	if(one == null) {
    		return false;
    	}else {
    		return true;
    	}
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrAssessmentTemplate/delete")
    //@RequiresPermissions("hrAssessmentTemplate:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrAssessmentTemplateService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrAssessmentTemplate/update")
    //@RequiresPermissions("hrAssessmentTemplate:update")
    @ResponseBody
    public DataResult update(@RequestBody HrAssessmentTemplateEntity hrAssessmentTemplate){
        hrAssessmentTemplateService.updateById(hrAssessmentTemplate);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrAssessmentTemplate/listByPage")
    //@RequiresPermissions("hrAssessmentTemplate:list")
    @ResponseBody
    public DataResult findListByPage(@RequestBody HrAssessmentTemplateEntity hrAssessmentTemplate){
    	long time1= System.currentTimeMillis();
        Page page = new Page(hrAssessmentTemplate.getPage(), hrAssessmentTemplate.getLimit());
        LambdaQueryWrapper<HrAssessmentTemplateEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentTemplate.getCreateIds())) {
            queryWrapper.in(HrAssessmentTemplateEntity::getCreateId, hrAssessmentTemplate.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        queryWrapper.like(HrAssessmentTemplateEntity::getTeamplateName, hrAssessmentTemplate.getTeamplateName()==null?"":hrAssessmentTemplate.getTeamplateName());
        IPage<HrAssessmentTemplateEntity> iPage = hrAssessmentTemplateService.page(page, queryWrapper);
        long time2= System.currentTimeMillis()-time1;
        System.err.println("time cost 111 = "+(time2));
        //关联流程查询待办、处理人、状态 
        List<HrAssessmentTemplateEntity> records = iPage.getRecords();
        records.forEach(item -> {
        	if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						hrAssessmentTemplateService.updateById(item);
					}
				}
			}else {
				item.setOrderState(item.getOrderStatus());
			}

		});
        time2= System.currentTimeMillis()-time1;
        System.err.println("time cost 222 = "+(time2));
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrAssessmentTemplate/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrAssessmentTemplateEntity hrAssessmentTemplate){
        Page page = new Page(hrAssessmentTemplate.getPage(), hrAssessmentTemplate.getLimit());
        LambdaQueryWrapper<HrAssessmentTemplateEntity> queryWrapper = Wrappers.lambdaQuery();
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentTemplate.getCreateIds())) {
            queryWrapper.in(HrAssessmentTemplateEntity::getCreateId, hrAssessmentTemplate.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //查询条件示例
        //queryWrapper.like(HrAssessmentTemplateEntity::getId, hrAssessmentTemplate.getId());
        IPage<HrAssessmentTemplateEntity> iPage = hrAssessmentTemplateService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrAssessmentTemplate/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrAssessmentTemplateEntity hrAssessmentTemplate){
    	LambdaQueryWrapper<HrAssessmentTemplateEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrAssessmentTemplateEntity::getId, hrAssessmentTemplate.getId());
    	//HrAssessmentTemplateEntity one = hrAssessmentTemplateService.getOne(queryWrapper);
    	HrAssessmentTemplateEntity one = hrAssessmentTemplateService.getById(hrAssessmentTemplate.getId());
    	return DataResult.success(one);
    }
    
    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrAssessmentTemplate/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrAssessmentTemplateEntity hrAssessmentTemplate){
        Page page = new Page(hrAssessmentTemplate.getPage(), hrAssessmentTemplate.getLimit());
        LambdaQueryWrapper<HrAssessmentTemplateEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentTemplate.getCreateIds())) {
            queryWrapper.in(HrAssessmentTemplateEntity::getCreateId, hrAssessmentTemplate.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //queryWrapper.like(HrAssessmentTemplateEntity::getId, hrAssessmentTemplate.getId());
        IPage<HrAssessmentTemplateEntity> iPage = hrAssessmentTemplateService.page(page, queryWrapper);
        log.info("\n this.hrAssessmentTemplateMapper.selectCountUser()="+this.hrAssessmentTemplateMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
