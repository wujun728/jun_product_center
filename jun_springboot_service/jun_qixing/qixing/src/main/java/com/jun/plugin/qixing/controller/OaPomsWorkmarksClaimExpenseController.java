package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

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
import com.jun.plugin.qixing.entity.OaPomsWorkmarksClaimExpenseEntity;
import com.jun.plugin.qixing.mapper.OaPomsWorkmarksClaimExpenseMapper;
import com.jun.plugin.qixing.service.OaPomsWorkmarksClaimExpenseService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 费用报销
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 23:30:11
 */
@Controller
@RequestMapping("/")
@Slf4j
public class OaPomsWorkmarksClaimExpenseController   extends BaseFlowController {

    @Autowired
    private OaPomsWorkmarksClaimExpenseService oaPomsWorkmarksClaimExpenseService;

    @Autowired
    private OaPomsWorkmarksClaimExpenseMapper oaPomsWorkmarksClaimExpenseMapper;

    //@Resource
   // HttpSessionService sessionService;
    
    @Autowired
    private PrimaryKeyService pks;
    
    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaPomsWorkmarksClaimExpense")
    public String oaPomsWorkmarksClaimExpense(Model model) {
    	model.addAttribute("costCode", pks.genCodeAndCheckExists("COST"));
        return "oapomsworkmarksclaimexpense/list";
    }

    @ApiOperation(value = "新增")
    @PostMapping("oaPomsWorkmarksClaimExpense/add")
    //@RequiresPermissions("oaPomsWorkmarksClaimExpense:add")
    @ResponseBody
    public DataResult add(@RequestBody OaPomsWorkmarksClaimExpenseEntity oaPomsWorkmarksClaimExpense){
        oaPomsWorkmarksClaimExpenseService.save(oaPomsWorkmarksClaimExpense);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaPomsWorkmarksClaimExpense/delete")
    //@RequiresPermissions("oaPomsWorkmarksClaimExpense:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaPomsWorkmarksClaimExpenseService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaPomsWorkmarksClaimExpense/update")
    //@RequiresPermissions("oaPomsWorkmarksClaimExpense:update")
    @ResponseBody
    public DataResult update(@RequestBody OaPomsWorkmarksClaimExpenseEntity oaPomsWorkmarksClaimExpense){
        oaPomsWorkmarksClaimExpenseService.updateById(oaPomsWorkmarksClaimExpense);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaPomsWorkmarksClaimExpense/listByPage")
    //@RequiresPermissions("oaPomsWorkmarksClaimExpense:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaPomsWorkmarksClaimExpenseEntity oaPomsWorkmarksClaimExpense){
        Page page = new Page(oaPomsWorkmarksClaimExpense.getPage(), oaPomsWorkmarksClaimExpense.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksClaimExpenseEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaPomsWorkmarksClaimExpense.getCreateIds())) {
            queryWrapper.in(OaPomsWorkmarksClaimExpenseEntity::getCreateId, oaPomsWorkmarksClaimExpense.getCreateIds());
        }
        //queryWrapper.like(OaPomsWorkmarksClaimExpenseEntity::getId, oaPomsWorkmarksClaimExpense.getId()==null?"":oaPomsWorkmarksClaimExpense.getId());
        IPage<OaPomsWorkmarksClaimExpenseEntity> iPage = oaPomsWorkmarksClaimExpenseService.page(page, queryWrapper);
        List<OaPomsWorkmarksClaimExpenseEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						oaPomsWorkmarksClaimExpenseService.updateById(item);
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
    @PostMapping("oaPomsWorkmarksClaimExpense/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaPomsWorkmarksClaimExpenseEntity oaPomsWorkmarksClaimExpense){
        Page page = new Page(oaPomsWorkmarksClaimExpense.getPage(), oaPomsWorkmarksClaimExpense.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksClaimExpenseEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaPomsWorkmarksClaimExpense.getCreateIds())) {
            queryWrapper.in(OaPomsWorkmarksClaimExpenseEntity::getCreateId, oaPomsWorkmarksClaimExpense.getCreateIds());
        }
        //queryWrapper.like(OaPomsWorkmarksClaimExpenseEntity::getId, oaPomsWorkmarksClaimExpense.getId());
        IPage<OaPomsWorkmarksClaimExpenseEntity> iPage = oaPomsWorkmarksClaimExpenseService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaPomsWorkmarksClaimExpense/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaPomsWorkmarksClaimExpenseEntity oaPomsWorkmarksClaimExpense){
    	LambdaQueryWrapper<OaPomsWorkmarksClaimExpenseEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaPomsWorkmarksClaimExpenseEntity::getId, oaPomsWorkmarksClaimExpense.getId());
    	//OaPomsWorkmarksClaimExpenseEntity one = oaPomsWorkmarksClaimExpenseService.getOne(queryWrapper);
    	OaPomsWorkmarksClaimExpenseEntity one = oaPomsWorkmarksClaimExpenseService.getById(oaPomsWorkmarksClaimExpense.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaPomsWorkmarksClaimExpense/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaPomsWorkmarksClaimExpenseEntity oaPomsWorkmarksClaimExpense){
        Page page = new Page(oaPomsWorkmarksClaimExpense.getPage(), oaPomsWorkmarksClaimExpense.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksClaimExpenseEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaPomsWorkmarksClaimExpense.getCreateIds())) {
            queryWrapper.in(OaPomsWorkmarksClaimExpenseEntity::getCreateId, oaPomsWorkmarksClaimExpense.getCreateIds());
        }
        //queryWrapper.like(OaPomsWorkmarksClaimExpenseEntity::getId, oaPomsWorkmarksClaimExpense.getId());
        IPage<OaPomsWorkmarksClaimExpenseEntity> iPage = oaPomsWorkmarksClaimExpenseService.page(page, queryWrapper);
        log.info("\n this.oaPomsWorkmarksClaimExpenseMapper.selectCountUser()="+this.oaPomsWorkmarksClaimExpenseMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
