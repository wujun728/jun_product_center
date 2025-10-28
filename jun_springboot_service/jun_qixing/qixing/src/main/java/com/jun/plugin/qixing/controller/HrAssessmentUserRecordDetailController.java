package com.jun.plugin.qixing.controller;

import java.util.List;
import java.util.Map;

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
import com.google.common.collect.Maps;
import com.jun.plugin.qixing.entity.HrAssessmentUserRecordDetailEntity;
import com.jun.plugin.qixing.mapper.BizCommonMapper;
import com.jun.plugin.qixing.mapper.HrAssessmentUserRecordDetailMapper;
import com.jun.plugin.qixing.service.BizCommonService;
import com.jun.plugin.qixing.service.HrAssessmentUserRecordDetailService;
import com.jun.plugin.snakerflow.process.BaseFlowController;
import com.jun.plugin.common.aop.annotation.DataScope;
//import com.jun.plugin.system.service.HttpSessionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;



/**
 * 考核记录明细
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 20:28:30
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrAssessmentUserRecordDetailController  extends BaseFlowController{

    @Autowired
    private HrAssessmentUserRecordDetailService hrAssessmentUserRecordDetailService;

    @Autowired
    private HrAssessmentUserRecordDetailMapper hrAssessmentUserRecordDetailMapper;

    @Autowired
    private BizCommonService bizCommonService;
    
    @Autowired
    private BizCommonMapper bizCommonMapper;
    
    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrAssessmentUserRecordDetail")
    public String hrAssessmentUserRecordDetail() {
        return "hrassessmentuserrecorddetail/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrAssessmentUserRecordDetail/add")
    //@RequiresPermissions("hrAssessmentUserRecordDetail:add")
    @ResponseBody
    public DataResult add(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
        String userid = SecurityUtils.getUserId().toString();
        String username = SecurityUtils.getUsername();
    	hrAssessmentUserRecordDetail.setUsercode(userid);
    	hrAssessmentUserRecordDetail.setRefUsername(username);
    	if(hrAssessmentUserRecordDetail.getAttr_score() !=0 && hrAssessmentUserRecordDetail.getScore()>hrAssessmentUserRecordDetail.getAttr_score()) {
    		return DataResult.fail("评分无效(不能大于评分标准分值)!");
    	}
    	if(this.checkExists(hrAssessmentUserRecordDetail)) {
    		hrAssessmentUserRecordDetailService.updateById(hrAssessmentUserRecordDetail);
    	}else {
    		hrAssessmentUserRecordDetailService.save(hrAssessmentUserRecordDetail);
    	}
    	this.bizCommonMapper.updateAssessmentUserRecord(hrAssessmentUserRecordDetail.getRef_record_id());
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrAssessmentUserRecordDetail/delete")
    //@RequiresPermissions("hrAssessmentUserRecordDetail:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrAssessmentUserRecordDetailService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrAssessmentUserRecordDetail/update")
    //@RequiresPermissions("hrAssessmentUserRecordDetail:update")
    @ResponseBody
    public DataResult update(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
        hrAssessmentUserRecordDetailService.updateById(hrAssessmentUserRecordDetail);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrAssessmentUserRecordDetail/listByPage")
    //@RequiresPermissions("hrAssessmentUserRecordDetail:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
        Page page = new Page(hrAssessmentUserRecordDetail.getPage(), hrAssessmentUserRecordDetail.getLimit());
        LambdaQueryWrapper<HrAssessmentUserRecordDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentUserRecordDetail.getCreateIds())) {
            queryWrapper.in(HrAssessmentUserRecordDetailEntity::getCreateId, hrAssessmentUserRecordDetail.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //queryWrapper.like(HrAssessmentUserRecordDetailEntity::getId, hrAssessmentUserRecordDetail.getId()==null?"":hrAssessmentUserRecordDetail.getId());
        IPage<HrAssessmentUserRecordDetailEntity> iPage = hrAssessmentUserRecordDetailService.page(page, queryWrapper);
        //关联流程查询待办、处理人、状态 
        List<HrAssessmentUserRecordDetailEntity> records = iPage.getRecords();
        records.forEach(item -> {
        	if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						hrAssessmentUserRecordDetailService.updateById(item);
					}
				}
			}else {
				item.setOrderState(item.getOrderStatus());
			}

		});
        return DataResult.success(iPage);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "查询分页数据")
    @PostMapping("hrAssessmentUserRecordDetail/listByPageV2")
    //@RequiresPermissions("hrAssessmentUserRecordDetail:list")
    @ResponseBody
    @DataScope
    public Map findListByPageV2(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
//    	Page page = new Page(hrAssessmentUserRecordDetail.getPage(), hrAssessmentUserRecordDetail.getLimit());
//    	if (!CollectionUtils.isEmpty(hrAssessmentUserRecordDetail.getCreateIds())) {
//    		queryWrapper.in(HrAssessmentUserRecordDetailEntity::getCreateId, hrAssessmentUserRecordDetail.getCreateIds());
//    	}
    	int current = hrAssessmentUserRecordDetail.getPage();
    	int limit = hrAssessmentUserRecordDetail.getLimit();
    	Map para = Maps.newConcurrentMap();
    	Map datas = Maps.newConcurrentMap();
    	Map result = Maps.newConcurrentMap();
    	para.put("current", current);
    	para.put("limit", limit);
    	para.put("star", (current-1)*limit);
    	para.put("end", current*limit);
    	para.put("ref_record_id", hrAssessmentUserRecordDetail.getRef_record_id());
    	List list = this.bizCommonMapper.getDataList(para);
		long total = this.bizCommonMapper.getDataListTotal(para);
		datas.put("records", list);
		datas.put("total", total);
		result.put("code", 0);
		result.put("msg", "操作成功");
		result.put("data", datas);
    	return result;
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrAssessmentUserRecordDetail/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
        Page page = new Page(hrAssessmentUserRecordDetail.getPage(), hrAssessmentUserRecordDetail.getLimit());
        LambdaQueryWrapper<HrAssessmentUserRecordDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentUserRecordDetail.getCreateIds())) {
            queryWrapper.in(HrAssessmentUserRecordDetailEntity::getCreateId, hrAssessmentUserRecordDetail.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //查询条件示例
        //queryWrapper.like(HrAssessmentUserRecordDetailEntity::getId, hrAssessmentUserRecordDetail.getId());
        IPage<HrAssessmentUserRecordDetailEntity> iPage = hrAssessmentUserRecordDetailService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrAssessmentUserRecordDetail/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
    	LambdaQueryWrapper<HrAssessmentUserRecordDetailEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrAssessmentUserRecordDetailEntity::getId, hrAssessmentUserRecordDetail.getId());
    	//HrAssessmentUserRecordDetailEntity one = hrAssessmentUserRecordDetailService.getOne(queryWrapper);
    	HrAssessmentUserRecordDetailEntity one = hrAssessmentUserRecordDetailService.getById(hrAssessmentUserRecordDetail.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrAssessmentUserRecordDetail/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
        Page page = new Page(hrAssessmentUserRecordDetail.getPage(), hrAssessmentUserRecordDetail.getLimit());
        LambdaQueryWrapper<HrAssessmentUserRecordDetailEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(hrAssessmentUserRecordDetail.getCreateIds())) {
            queryWrapper.in(HrAssessmentUserRecordDetailEntity::getCreateId, hrAssessmentUserRecordDetail.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //queryWrapper.like(HrAssessmentUserRecordDetailEntity::getId, hrAssessmentUserRecordDetail.getId());
        IPage<HrAssessmentUserRecordDetailEntity> iPage = hrAssessmentUserRecordDetailService.page(page, queryWrapper);
        log.info("\n this.hrAssessmentUserRecordDetailMapper.selectCountUser()="+this.hrAssessmentUserRecordDetailMapper.selectCountUser());
        return DataResult.success(iPage);
    }
    
    @PostMapping("hrAssessmentUserRecordDetail/checkExists")
    @ResponseBody
    public Boolean checkExists(@RequestBody HrAssessmentUserRecordDetailEntity hrAssessmentUserRecordDetail){
    	LambdaQueryWrapper<HrAssessmentUserRecordDetailEntity> queryWrapper = Wrappers.lambdaQuery();
    	queryWrapper.eq(HrAssessmentUserRecordDetailEntity::getId, hrAssessmentUserRecordDetail.getId());// 这里换成自己查询的关键条件
    	HrAssessmentUserRecordDetailEntity one = hrAssessmentUserRecordDetailService.getOne(queryWrapper.last("LIMIT 1"));
    	if(one == null) {
    		return false;
    	}else {
    		return true;
    	}
    }

}
