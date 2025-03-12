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

import javax.annotation.Resource;

//import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.snakerflow.process.BaseFlowController;

import com.jun.plugin.qixing.entity.HrAssessmentUserRecordEntity;
import com.jun.plugin.qixing.mapper.HrAssessmentUserRecordMapper;
import com.jun.plugin.qixing.service.HrAssessmentUserRecordService;



/**
 * 考核记录
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 21:12:45
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrAssessmentUserRecordController  extends BaseFlowController{

    @Autowired
    private HrAssessmentUserRecordService hrAssessmentUserRecordService;

    @Autowired
    private HrAssessmentUserRecordMapper hrAssessmentUserRecordMapper;

    //@Resource
   // HttpSessionService sessionService;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrAssessmentUserRecord")
    public String hrAssessmentUserRecord() {
        return "hrassessmentuserrecord/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrAssessmentUserRecord/add")
    //@RequiresPermissions("hrAssessmentUserRecord:add")
    @ResponseBody
    public DataResult add(@RequestBody HrAssessmentUserRecordEntity hrAssessmentUserRecord){
    	if(this.checkExists(hrAssessmentUserRecord)) {
    		return DataResult.fail("同名记录信息已存在！");
    	}
        hrAssessmentUserRecordService.save(hrAssessmentUserRecord);
        return DataResult.success(hrAssessmentUserRecord.getId());
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrAssessmentUserRecord/delete")
    //@RequiresPermissions("hrAssessmentUserRecord:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrAssessmentUserRecordService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrAssessmentUserRecord/update")
    //@RequiresPermissions("hrAssessmentUserRecord:update")
    @ResponseBody
    public DataResult update(@RequestBody HrAssessmentUserRecordEntity hrAssessmentUserRecord){
        hrAssessmentUserRecordService.updateById(hrAssessmentUserRecord);
        return DataResult.success(hrAssessmentUserRecord.getId());
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrAssessmentUserRecord/listByPage")
    //@RequiresPermissions("hrAssessmentUserRecord:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrAssessmentUserRecordEntity hrAssessmentUserRecord){
        Page page = new Page(hrAssessmentUserRecord.getPage(), hrAssessmentUserRecord.getLimit());
        LambdaQueryWrapper<HrAssessmentUserRecordEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentUserRecord.getCreateIds())) {
            queryWrapper.in(HrAssessmentUserRecordEntity::getCreateId, hrAssessmentUserRecord.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //queryWrapper.like(HrAssessmentUserRecordEntity::getId, hrAssessmentUserRecord.getId()==null?"":hrAssessmentUserRecord.getId());
        IPage<HrAssessmentUserRecordEntity> iPage = hrAssessmentUserRecordService.page(page, queryWrapper);
        //关联流程查询待办、处理人、状态 
        List<HrAssessmentUserRecordEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
        records.forEach(item -> {
        	if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						hrAssessmentUserRecordService.updateById(item);
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
    @PostMapping("hrAssessmentUserRecord/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody HrAssessmentUserRecordEntity hrAssessmentUserRecord){
        Page page = new Page(hrAssessmentUserRecord.getPage(), hrAssessmentUserRecord.getLimit());
        LambdaQueryWrapper<HrAssessmentUserRecordEntity> queryWrapper = Wrappers.lambdaQuery();
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentUserRecord.getCreateIds())) {
            queryWrapper.in(HrAssessmentUserRecordEntity::getCreateId, hrAssessmentUserRecord.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //查询条件示例
        //queryWrapper.like(HrAssessmentUserRecordEntity::getId, hrAssessmentUserRecord.getId());
        IPage<HrAssessmentUserRecordEntity> iPage = hrAssessmentUserRecordService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrAssessmentUserRecord/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrAssessmentUserRecordEntity hrAssessmentUserRecord){
    	LambdaQueryWrapper<HrAssessmentUserRecordEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrAssessmentUserRecordEntity::getId, hrAssessmentUserRecord.getId());
    	//HrAssessmentUserRecordEntity one = hrAssessmentUserRecordService.getOne(queryWrapper);
    	HrAssessmentUserRecordEntity one = hrAssessmentUserRecordService.getById(hrAssessmentUserRecord.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrAssessmentUserRecord/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody HrAssessmentUserRecordEntity hrAssessmentUserRecord){
        Page page = new Page(hrAssessmentUserRecord.getPage(), hrAssessmentUserRecord.getLimit());
        LambdaQueryWrapper<HrAssessmentUserRecordEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentUserRecord.getCreateIds())) {
            queryWrapper.in(HrAssessmentUserRecordEntity::getCreateId, hrAssessmentUserRecord.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //queryWrapper.like(HrAssessmentUserRecordEntity::getId, hrAssessmentUserRecord.getId());
        IPage<HrAssessmentUserRecordEntity> iPage = hrAssessmentUserRecordService.page(page, queryWrapper);
        log.info("\n this.hrAssessmentUserRecordMapper.selectCountUser()="+this.hrAssessmentUserRecordMapper.selectCountUser());
        return DataResult.success(iPage);
    }
    
    @PostMapping("hrAssessmentUserRecord/checkExists")
    @ResponseBody
    public Boolean checkExists(@RequestBody HrAssessmentUserRecordEntity hrAssessmentUserRecord){
    	LambdaQueryWrapper<HrAssessmentUserRecordEntity> queryWrapper = Wrappers.lambdaQuery();
    	queryWrapper.eq(HrAssessmentUserRecordEntity::getId, hrAssessmentUserRecord.getId());// 这里换成自己查询的关键条件
    	HrAssessmentUserRecordEntity one = hrAssessmentUserRecordService.getOne(queryWrapper.last("LIMIT 1"));
    	if(one == null) {
    		return false;
    	}else {
    		return true;
    	}
    }

}
