package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.jun.plugin.common.aop.annotation.DataScope;

import com.jun.plugin.qixing.entity.OaPomsWorkmarksPayrollEntity;
import com.jun.plugin.qixing.mapper.OaPomsWorkmarksPayrollMapper;
import com.jun.plugin.qixing.service.OaPomsWorkmarksPayrollService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 工资审核发放
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:33
 */
@Controller
@RequestMapping("/")
@Slf4j
public class OaPomsWorkmarksPayrollController  extends BaseFlowController {

    @Autowired
    private OaPomsWorkmarksPayrollService oaPomsWorkmarksPayrollService;

    @Autowired
    private OaPomsWorkmarksPayrollMapper oaPomsWorkmarksPayrollMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaPomsWorkmarksPayroll")
    public String oaPomsWorkmarksPayroll() {
        return "oapomsworkmarkspayroll/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("oaPomsWorkmarksPayroll/add")
    //@RequiresPermissions("oaPomsWorkmarksPayroll:add")
    @ResponseBody
    public DataResult add(@RequestBody OaPomsWorkmarksPayrollEntity oaPomsWorkmarksPayroll){
        oaPomsWorkmarksPayrollService.save(oaPomsWorkmarksPayroll);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaPomsWorkmarksPayroll/delete")
    //@RequiresPermissions("oaPomsWorkmarksPayroll:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaPomsWorkmarksPayrollService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaPomsWorkmarksPayroll/update")
    //@RequiresPermissions("oaPomsWorkmarksPayroll:update")
    @ResponseBody
    public DataResult update(@RequestBody OaPomsWorkmarksPayrollEntity oaPomsWorkmarksPayroll){
        oaPomsWorkmarksPayrollService.updateById(oaPomsWorkmarksPayroll);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaPomsWorkmarksPayroll/listByPage")
    //@RequiresPermissions("oaPomsWorkmarksPayroll:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaPomsWorkmarksPayrollEntity oaPomsWorkmarksPayroll){
        Page page = new Page(oaPomsWorkmarksPayroll.getPage(), oaPomsWorkmarksPayroll.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksPayrollEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksPayrollEntity::getId, oaPomsWorkmarksPayroll.getId()==null?"":oaPomsWorkmarksPayroll.getId());
        IPage<OaPomsWorkmarksPayrollEntity> iPage = oaPomsWorkmarksPayrollService.page(page, queryWrapper);
        List<OaPomsWorkmarksPayrollEntity> records = iPage.getRecords();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						oaPomsWorkmarksPayrollService.updateById(item);
					}
				}
			}else {
				item.setOrderState(item.getOrderStatus());
			}

		});
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("oaPomsWorkmarksPayroll/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaPomsWorkmarksPayrollEntity oaPomsWorkmarksPayroll){
        Page page = new Page(oaPomsWorkmarksPayroll.getPage(), oaPomsWorkmarksPayroll.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksPayrollEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksPayrollEntity::getId, oaPomsWorkmarksPayroll.getId());
        IPage<OaPomsWorkmarksPayrollEntity> iPage = oaPomsWorkmarksPayrollService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaPomsWorkmarksPayroll/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaPomsWorkmarksPayrollEntity oaPomsWorkmarksPayroll){
    	LambdaQueryWrapper<OaPomsWorkmarksPayrollEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaPomsWorkmarksPayrollEntity::getId, oaPomsWorkmarksPayroll.getId());
    	//OaPomsWorkmarksPayrollEntity one = oaPomsWorkmarksPayrollService.getOne(queryWrapper);
    	OaPomsWorkmarksPayrollEntity one = oaPomsWorkmarksPayrollService.getById(oaPomsWorkmarksPayroll.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaPomsWorkmarksPayroll/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaPomsWorkmarksPayrollEntity oaPomsWorkmarksPayroll){
        Page page = new Page(oaPomsWorkmarksPayroll.getPage(), oaPomsWorkmarksPayroll.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksPayrollEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksPayrollEntity::getId, oaPomsWorkmarksPayroll.getId());
        IPage<OaPomsWorkmarksPayrollEntity> iPage = oaPomsWorkmarksPayrollService.page(page, queryWrapper);
        log.info("\n this.oaPomsWorkmarksPayrollMapper.selectCountUser()="+this.oaPomsWorkmarksPayrollMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
