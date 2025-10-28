package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.jun.plugin.qixing.entity.PjCustomerEntity;
import com.jun.plugin.qixing.mapper.PjCustomerMapper;
import com.jun.plugin.qixing.service.PjCustomerService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class PjCustomerController   extends BaseFlowController {

    @Autowired
    private PjCustomerService pjCustomerService;

    @Autowired
    private PjCustomerMapper pjCustomerMapper;
    
    @Autowired
    private PrimaryKeyService pks;
    
    //@Resource
   // HttpSessionService sessionService;
    

    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjCustomer")
    public String pjCustomer(Model model) {
    	model.addAttribute("customerCode", pks.genCodeAndCheckExists("CUS"));
        return "pjcustomer/list";
    }

    @ApiOperation(value = "新增")
    @PostMapping("pjCustomer/add")
    //@RequiresPermissions("pjCustomer:add")
    @ResponseBody
    public DataResult add(@RequestBody PjCustomerEntity pjCustomer){
    	LambdaQueryWrapper<PjCustomerEntity> queryWrapper = Wrappers.lambdaQuery();
    	queryWrapper.eq(PjCustomerEntity::getCustomerName, pjCustomer.getCustomerName());
    	PjCustomerEntity one = pjCustomerService.getOne(queryWrapper);
    	if(one==null) {
            String userid = SecurityUtils.getUserId().toString();
    		String userId = userid;
    		pjCustomer.setEditor(userId);
    		pjCustomerService.save(pjCustomer);
    		return DataResult.success();
    	}else {
    		return DataResult.fail("客户名称重复！");
    	}
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjCustomer/delete")
    //@RequiresPermissions("pjCustomer:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjCustomerService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjCustomer/update")
    //@RequiresPermissions("pjCustomer:update")
    @ResponseBody
    public DataResult update(@RequestBody PjCustomerEntity pjCustomer){
        pjCustomerService.updateById(pjCustomer);
        return DataResult.success();
    }
    

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjCustomer/list")
    //@RequiresPermissions("pjCustomer:list")
    @ResponseBody
    @DataScope
    public DataResult findList(@RequestBody PjCustomerEntity pjCustomer){
        Page page = new Page(pjCustomer.getPage(), pjCustomer.getLimit());
        LambdaQueryWrapper<PjCustomerEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjCustomerEntity::getCustomerName, pjCustomer.getCustomerName()==null?"":pjCustomer.getCustomerName());
        queryWrapper.like(PjCustomerEntity::getCustomerCode, pjCustomer.getCustomerCode()==null?"":pjCustomer.getCustomerCode());
        //新增数据查询权限
        if (!CollectionUtils.isEmpty(pjCustomer.getCreateIds())) {
            queryWrapper.in(PjCustomerEntity::getCreateId, pjCustomer.getCreateIds());
        }
        IPage<PjCustomerEntity> iPage = pjCustomerService.page(page, queryWrapper);
        //新增数据权限判断
        List<PjCustomerEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)  ) {
				this.setFlowStatusInfo(item);
//				System.err.println(item.getOrderStatus());
//				if(item.getOrderState()==0) {
//					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
//						item.setOrderStatus(item.getOrderState());
//						new Thread(new Runnable() {
//							@Override
//							public void run() {
//								pjCustomerService.updateById(item);
//							}
//						}).start();
//					}
//				}
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
    @PostMapping("pjCustomer/listByPage")
    //@RequiresPermissions("pjCustomer:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjCustomerEntity pjCustomer){
        Page page = new Page(pjCustomer.getPage(), pjCustomer.getLimit());
        LambdaQueryWrapper<PjCustomerEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjCustomerEntity::getCustomerName, pjCustomer.getCustomerName()==null?"":pjCustomer.getCustomerName());
        queryWrapper.like(PjCustomerEntity::getCustomerName, pjCustomer.getKeyword()==null?"":pjCustomer.getKeyword());
        queryWrapper.like(PjCustomerEntity::getCustomerCode, pjCustomer.getCustomerCode()==null?"":pjCustomer.getCustomerCode());
        //新增数据查询权限
        if (!CollectionUtils.isEmpty(pjCustomer.getCreateIds())) {
            queryWrapper.in(PjCustomerEntity::getCreateId, pjCustomer.getCreateIds());
        }
        IPage<PjCustomerEntity> iPage = pjCustomerService.page(page, queryWrapper);
        //新增数据权限判断
        List<PjCustomerEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }
    
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjCustomer/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjCustomerEntity pjCustomer){
    	LambdaQueryWrapper<PjCustomerEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjCustomerEntity::getId, pjCustomer.getId());
    	//PjCustomerEntity one = pjCustomerService.getOne(queryWrapper);
    	PjCustomerEntity one = pjCustomerService.getById(pjCustomer.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjCustomer/listBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody PjCustomerEntity pjCustomer){
        Page page = new Page(pjCustomer.getPage(), pjCustomer.getLimit());
        LambdaQueryWrapper<PjCustomerEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjCustomer.getCreateIds())) {
            queryWrapper.in(PjCustomerEntity::getCreateId, pjCustomer.getCreateIds());
        }
        //queryWrapper.eq(PjCustomerEntity::getId, pjCustomer.getId());
        IPage<PjCustomerEntity> iPage = pjCustomerService.page(page, queryWrapper);
        log.info("\n this.pjCustomerMapper.selectCountUser()="+this.pjCustomerMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
