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

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

//import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.entity.PjProjectDailyEntity;
import com.jun.plugin.qixing.mapper.PjProjectDailyMapper;
import com.jun.plugin.qixing.service.PjProjectDailyService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 项目日报周报
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 22:00:09
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectDailyController extends BaseFlowController{

    @Autowired
    private PjProjectDailyService pjProjectDailyService;

    @Autowired
    private PjProjectDailyMapper pjProjectDailyMapper;
    
    //@Resource
   // HttpSessionService sessionService;
    
    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectDaily")
    public String pjProjectDaily() {
        return "pjprojectdaily/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectDaily/add")
    //@RequiresPermissions("pjProjectDaily:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectDailyEntity pjProjectDaily){
        pjProjectDailyService.save(pjProjectDaily);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectDaily/delete")
    //@RequiresPermissions("pjProjectDaily:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectDailyService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectDaily/update")
    //@RequiresPermissions("pjProjectDaily:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectDailyEntity pjProjectDaily){
        pjProjectDailyService.updateById(pjProjectDaily);
        return DataResult.success();
    }
    
    @ApiOperation(value = "复制")
    @PutMapping("pjProjectDaily/copy")
    @ResponseBody
    public DataResult copy(@RequestBody PjProjectDailyEntity pjProjectDaily){
//    	pjProjectDaily.setId(null);
    	pjProjectDailyService.save(pjProjectDaily);
    	return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectDaily/listByPage")
    //@RequiresPermissions("pjProjectDaily:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectDailyEntity pjProjectDaily){
        Page page = new Page(pjProjectDaily.getPage(), pjProjectDaily.getLimit());
        LambdaQueryWrapper<PjProjectDailyEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjProjectDailyEntity::getRefProjectName, pjProjectDaily.getRefProjectName()==null?"":pjProjectDaily.getRefProjectName());
        queryWrapper.like(PjProjectDailyEntity::getDailyDetail, pjProjectDaily.getDailyDetail()==null?"":pjProjectDaily.getDailyDetail());
        queryWrapper.like(PjProjectDailyEntity::getPlanName, pjProjectDaily.getPlanName()==null?"":pjProjectDaily.getPlanName());
        if (!CollectionUtils.isEmpty(pjProjectDaily.getCreateIds())) {
        	queryWrapper.in(PjProjectDailyEntity::getCreateId, pjProjectDaily.getCreateIds());
        }
        IPage<PjProjectDailyEntity> iPage = pjProjectDailyService.page(page, queryWrapper);
        List<PjProjectDailyEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						pjProjectDailyService.updateById(item);
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
    
    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectDaily/viewListByPage")
//    //@RequiresPermissions("pjProjectDaily:list")
    @ResponseBody
//    @DataScope
    public DataResult viewListByPage(@RequestBody PjProjectDailyEntity pjProjectDaily){
    	Page page = new Page(pjProjectDaily.getPage(), pjProjectDaily.getLimit());
    	LambdaQueryWrapper<PjProjectDailyEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
//    	queryWrapper.like(PjProjectDailyEntity::getRefProjectName, pjProjectDaily.getRefProjectName()==null?"":pjProjectDaily.getRefProjectName());
//    	queryWrapper.like(PjProjectDailyEntity::getDailyDetail, pjProjectDaily.getDailyDetail()==null?"":pjProjectDaily.getDailyDetail());
//    	queryWrapper.like(PjProjectDailyEntity::getPlanName, pjProjectDaily.getPlanName()==null?"":pjProjectDaily.getPlanName());
//    	if (!CollectionUtils.isEmpty(pjProjectDaily.getCreateIds())) {
//    		queryWrapper.in(PjProjectDailyEntity::getCreateId, pjProjectDaily.getCreateIds());
//    	}
    	String id = pjProjectDaily.getId();
    	String pid = pjProjectDaily.getRefProjectCode();
    	if(id!=null && id.contains(",")) {
    		List<String> ids = Arrays.asList(id.split(","));
    		queryWrapper.in(PjProjectDailyEntity::getId, ids);
    	}
    	String sql = " SELECT id from pj_project_daily pjm \r\n"
    			+ "where pjm.ref_project_code in (\r\n"
    			+ "	SELECT p.project_code from pj_project p where p.id = '"+pid+"'\r\n"
    			+ ")\r\n"
    			+ "or ref_project_name in (\r\n"
    			+ "	SELECT p.project_name from pj_project p where p.id = '"+pid+"'\r\n"
    			+ ") ";
		queryWrapper.inSql(true,PjProjectDailyEntity::getId, sql);
    	IPage<PjProjectDailyEntity> iPage = pjProjectDailyService.page(page, queryWrapper);
    	List<PjProjectDailyEntity> records = iPage.getRecords();
    	String userid = SecurityUtils.getUserId().toString();
    	records.forEach(item -> {
    		if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
    			this.setFlowStatusInfo(item);
    			System.err.println(item.getOrderStatus());
    			if(item.getOrderState()==0) {
    				if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
    					item.setOrderStatus(item.getOrderState());
    					pjProjectDailyService.updateById(item);
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
    @PostMapping("pjProjectDaily/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody PjProjectDailyEntity pjProjectDaily){
        Page page = new Page(pjProjectDaily.getPage(), pjProjectDaily.getLimit());
        LambdaQueryWrapper<PjProjectDailyEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjProjectDailyEntity::getId, pjProjectDaily.getId());
        if (!CollectionUtils.isEmpty(pjProjectDaily.getCreateIds())) {
        	queryWrapper.in(PjProjectDailyEntity::getCreateId, pjProjectDaily.getCreateIds());
        }
        IPage<PjProjectDailyEntity> iPage = pjProjectDailyService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectDaily/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectDailyEntity pjProjectDaily){
    	LambdaQueryWrapper<PjProjectDailyEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectDailyEntity::getId, pjProjectDaily.getId());
    	//PjProjectDailyEntity one = pjProjectDailyService.getOne(queryWrapper);
    	PjProjectDailyEntity one = pjProjectDailyService.getById(pjProjectDaily.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectDaily/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectDailyEntity pjProjectDaily){
        Page page = new Page(pjProjectDaily.getPage(), pjProjectDaily.getLimit());
        LambdaQueryWrapper<PjProjectDailyEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjProjectDailyEntity::getId, pjProjectDaily.getId());
        IPage<PjProjectDailyEntity> iPage = pjProjectDailyService.page(page, queryWrapper);
        if (!CollectionUtils.isEmpty(pjProjectDaily.getCreateIds())) {
        	queryWrapper.in(PjProjectDailyEntity::getCreateId, pjProjectDaily.getCreateIds());
        }
        log.info("\n this.pjProjectDailyMapper.selectCountUser()="+this.pjProjectDailyMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
