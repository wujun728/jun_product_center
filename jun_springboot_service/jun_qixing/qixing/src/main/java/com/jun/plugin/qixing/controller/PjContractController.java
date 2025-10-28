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

//import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;

import com.jun.plugin.qixing.entity.PjContractEntity;
import com.jun.plugin.qixing.mapper.PjContractMapper;
import com.jun.plugin.qixing.service.PjContractService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 业务约定书
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 09:18:54
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjContractController  extends BaseFlowController {

    @Autowired
    private PjContractService pjContractService;

    @Autowired
    private PjContractMapper pjContractMapper;
    
    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjContract")
    public String pjContract() {
        return "pjcontract/list";
        }
    
    
    @GetMapping("/index/index_prj")
    public String index_prj() {
    	return "index_prj";
    }
    /**
     * 跳转到页面
     */
    @GetMapping("/index/pjContract2")
    public String pjContract2() {
    	return "pjcontract/list";
    }

    @ApiOperation(value = "新增")
    @PostMapping("pjContract/add")
    //@RequiresPermissions("pjContract:add")
    @ResponseBody
    public DataResult add(@RequestBody PjContractEntity pjContract){
        pjContractService.save(pjContract);
        return DataResult.success(pjContract);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjContract/delete")
    //@RequiresPermissions("pjContract:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjContractService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjContract/update")
    //@RequiresPermissions("pjContract:update")
    @ResponseBody
    public DataResult update(@RequestBody PjContractEntity pjContract){
        pjContractService.updateById(pjContract);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjContract/listByPage")
    //@RequiresPermissions("pjContract:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjContractEntity pjContract){
        Page page = new Page(pjContract.getPage(), pjContract.getLimit());
        LambdaQueryWrapper<PjContractEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjContractEntity::getRefProjectName, pjContract.getRefProjectName()==null?"":pjContract.getRefProjectName());
        queryWrapper.like(PjContractEntity::getContractName, pjContract.getContractName()==null?"":pjContract.getContractName());
        queryWrapper.like(PjContractEntity::getSignBy, pjContract.getSignBy()==null?"":pjContract.getSignBy());
        //queryWrapper.eq(PjContractEntity::getId, pjContract.getId());
        if (!CollectionUtils.isEmpty(pjContract.getCreateIds())) {
            queryWrapper.in(PjContractEntity::getCreateId, pjContract.getCreateIds());
        }
        IPage<PjContractEntity> iPage = pjContractService.page(page, queryWrapper);
        List<PjContractEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						//pjContractService.updateById(item);
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
    @PostMapping("pjContract/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody PjContractEntity pjContract){
        Page page = new Page(pjContract.getPage(), pjContract.getLimit());
        LambdaQueryWrapper<PjContractEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjContractEntity::getId, pjContract.getId());
        queryWrapper.like(PjContractEntity::getRefProjectName, pjContract.getRefProjectName()==null?"":pjContract.getRefProjectName());
        queryWrapper.like(PjContractEntity::getContractName, pjContract.getContractName()==null?"":pjContract.getContractName());
        queryWrapper.like(PjContractEntity::getSignBy, pjContract.getSignBy()==null?"":pjContract.getSignBy());
        IPage<PjContractEntity> iPage = pjContractService.page(page, queryWrapper);
        
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjContract/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjContractEntity pjContract){
    	LambdaQueryWrapper<PjContractEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjContractEntity::getId, pjContract.getId());
    	//PjContractEntity one = pjContractService.getOne(queryWrapper);
    	PjContractEntity one = pjContractService.getById(pjContract.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjContract/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody PjContractEntity pjContract){
        Page page = new Page(pjContract.getPage(), pjContract.getLimit());
        LambdaQueryWrapper<PjContractEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(PjContractEntity::getId, pjContract.getId());
        IPage<PjContractEntity> iPage = pjContractService.page(page, queryWrapper);
        if (!CollectionUtils.isEmpty(pjContract.getCreateIds())) {
            queryWrapper.in(PjContractEntity::getCreateId, pjContract.getCreateIds());
        }
        log.info("\n this.pjContractMapper.selectCountUser()="+this.pjContractMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
