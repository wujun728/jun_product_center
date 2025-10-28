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

////import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.entity.PjProjectReportEntity;
import com.jun.plugin.qixing.mapper.PjProjectReportMapper;
import com.jun.plugin.qixing.service.PjProjectReportService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 项目报告
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 23:22:15
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectReportController extends BaseFlowController {

    @Autowired
    private PjProjectReportService pjProjectReportService;

    @Autowired
    private PjProjectReportMapper pjProjectReportMapper;

//    @Resource
//    HttpSessionService sessionService;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectReport")
    public String pjProjectReport() {
        return "pjprojectreport/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectReport/add")
    //@RequiresPermissions("pjProjectReport:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectReportEntity pjProjectReport){
        pjProjectReportService.save(pjProjectReport);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectReport/delete")
    //@RequiresPermissions("pjProjectReport:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectReportService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectReport/update")
    //@RequiresPermissions("pjProjectReport:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectReportEntity pjProjectReport){
        pjProjectReportService.updateById(pjProjectReport);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectReport/listByPage")
    //@RequiresPermissions("pjProjectReport:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectReportEntity pjProjectReport){
        Page page = new Page(pjProjectReport.getPage(), pjProjectReport.getLimit());
        LambdaQueryWrapper<PjProjectReportEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjProjectReportEntity::getRefProjectName, pjProjectReport.getRefProjectName()==null?"":pjProjectReport.getRefProjectName());
        queryWrapper.like(PjProjectReportEntity::getReportName, pjProjectReport.getReportName()==null?"":pjProjectReport.getReportName());
        queryWrapper.like(PjProjectReportEntity::getReportDetail, pjProjectReport.getReportDetail()==null?"":pjProjectReport.getReportDetail());
        if (!CollectionUtils.isEmpty(pjProjectReport.getCreateIds())) {
            queryWrapper.in(PjProjectReportEntity::getCreateId, pjProjectReport.getCreateIds());
        }
        IPage<PjProjectReportEntity> iPage = pjProjectReportService.page(page, queryWrapper);
        List<PjProjectReportEntity> records = iPage.getRecords();
        String userid = "";//sessionService.getCurrentUserId();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						pjProjectReportService.updateById(item);
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
    @PostMapping("pjProjectReport/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody PjProjectReportEntity pjProjectReport){
        Page page = new Page(pjProjectReport.getPage(), pjProjectReport.getLimit());
        LambdaQueryWrapper<PjProjectReportEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectReport.getCreateIds())) {
            queryWrapper.in(PjProjectReportEntity::getCreateId, pjProjectReport.getCreateIds());
        }
        //queryWrapper.like(PjProjectReportEntity::getId, pjProjectReport.getId());
        IPage<PjProjectReportEntity> iPage = pjProjectReportService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectReport/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectReportEntity pjProjectReport){
    	LambdaQueryWrapper<PjProjectReportEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectReportEntity::getId, pjProjectReport.getId());
    	//PjProjectReportEntity one = pjProjectReportService.getOne(queryWrapper);
    	PjProjectReportEntity one = pjProjectReportService.getById(pjProjectReport.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectReport/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody PjProjectReportEntity pjProjectReport){
        Page page = new Page(pjProjectReport.getPage(), pjProjectReport.getLimit());
        LambdaQueryWrapper<PjProjectReportEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectReport.getCreateIds())) {
            queryWrapper.in(PjProjectReportEntity::getCreateId, pjProjectReport.getCreateIds());
        }
        //queryWrapper.like(PjProjectReportEntity::getId, pjProjectReport.getId());
        IPage<PjProjectReportEntity> iPage = pjProjectReportService.page(page, queryWrapper);
        log.info("\n this.pjProjectReportMapper.selectCountUser()="+this.pjProjectReportMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
