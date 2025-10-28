package com.jun.plugin.qixing.controller;

import java.util.List;

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
import com.jun.plugin.qixing.entity.PjProjectDraftEntity;
import com.jun.plugin.qixing.mapper.PjProjectDraftMapper;
import com.jun.plugin.qixing.service.PjProjectDraftService;
import com.jun.plugin.snakerflow.process.BaseFlowController;
import com.jun.plugin.common.aop.annotation.DataScope;
//import com.jun.plugin.system.service.HttpSessionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;



/**
 * 项目底稿
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 23:03:16
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectDraftController extends BaseFlowController{

    @Autowired
    private PjProjectDraftService pjProjectDraftService;

    @Autowired
    private PjProjectDraftMapper pjProjectDraftMapper;
    
    //@Resource
   // HttpSessionService sessionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectDraft")
    public String pjProjectDraft() {
        return "pjprojectdraft/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectDraft/add")
    //@RequiresPermissions("pjProjectDraft:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectDraftEntity pjProjectDraft){
        pjProjectDraftService.save(pjProjectDraft);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectDraft/delete")
    //@RequiresPermissions("pjProjectDraft:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectDraftService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectDraft/update")
    //@RequiresPermissions("pjProjectDraft:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectDraftEntity pjProjectDraft){
        pjProjectDraftService.updateById(pjProjectDraft);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectDraft/listByPage")
    //@RequiresPermissions("pjProjectDraft:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectDraftEntity pjProjectDraft){
        Page page = new Page(pjProjectDraft.getPage(), pjProjectDraft.getLimit());
        LambdaQueryWrapper<PjProjectDraftEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjProjectDraftEntity::getDraftName, pjProjectDraft.getDraftName()==null?"":pjProjectDraft.getDraftName());
        queryWrapper.like(PjProjectDraftEntity::getRefProjectName, pjProjectDraft.getRefProjectName()==null?"":pjProjectDraft.getRefProjectName());
        //queryWrapper.like(PjProjectDraftEntity::getId, pjProjectDraft.getId()==null?"":pjProjectDraft.getId());
        if (!CollectionUtils.isEmpty(pjProjectDraft.getCreateIds())) {
        	queryWrapper.in(PjProjectDraftEntity::getCreateId, pjProjectDraft.getCreateIds());
        }
        IPage<PjProjectDraftEntity> iPage = pjProjectDraftService.page(page, queryWrapper);
        List<PjProjectDraftEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						pjProjectDraftService.updateById(item);
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
    @PostMapping("pjProjectDraft/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody PjProjectDraftEntity pjProjectDraft){
        Page page = new Page(pjProjectDraft.getPage(), pjProjectDraft.getLimit());
        LambdaQueryWrapper<PjProjectDraftEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectDraft.getCreateIds())) {
        	queryWrapper.in(PjProjectDraftEntity::getCreateId, pjProjectDraft.getCreateIds());
        }
        //queryWrapper.like(PjProjectDraftEntity::getId, pjProjectDraft.getId());
        IPage<PjProjectDraftEntity> iPage = pjProjectDraftService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectDraft/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectDraftEntity pjProjectDraft){
    	LambdaQueryWrapper<PjProjectDraftEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectDraftEntity::getId, pjProjectDraft.getId());
    	//PjProjectDraftEntity one = pjProjectDraftService.getOne(queryWrapper);
    	PjProjectDraftEntity one = pjProjectDraftService.getById(pjProjectDraft.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectDraft/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectDraftEntity pjProjectDraft){
        Page page = new Page(pjProjectDraft.getPage(), pjProjectDraft.getLimit());
        LambdaQueryWrapper<PjProjectDraftEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectDraft.getCreateIds())) {
        	queryWrapper.in(PjProjectDraftEntity::getCreateId, pjProjectDraft.getCreateIds());
        }
        //queryWrapper.like(PjProjectDraftEntity::getId, pjProjectDraft.getId());
        IPage<PjProjectDraftEntity> iPage = pjProjectDraftService.page(page, queryWrapper);
        log.info("\n this.pjProjectDraftMapper.selectCountUser()="+this.pjProjectDraftMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
