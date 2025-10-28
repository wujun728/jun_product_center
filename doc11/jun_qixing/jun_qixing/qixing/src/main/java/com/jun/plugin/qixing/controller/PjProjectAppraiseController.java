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
import com.jun.plugin.qixing.entity.PjProjectAppraiseEntity;
import com.jun.plugin.qixing.mapper.PjProjectAppraiseMapper;
import com.jun.plugin.qixing.service.PjProjectAppraiseService;



/**
 * 项目总结及评价
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 11:16:12
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectAppraiseController {

    @Autowired
    private PjProjectAppraiseService pjProjectAppraiseService;

    @Autowired
    private PjProjectAppraiseMapper pjProjectAppraiseMapper;

    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectAppraise")
    public String pjProjectAppraise() {
        return "pjprojectappraise/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectAppraise/add")
    //@RequiresPermissions("pjProjectAppraise:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectAppraiseEntity pjProjectAppraise){
        pjProjectAppraiseService.save(pjProjectAppraise);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectAppraise/delete")
    //@RequiresPermissions("pjProjectAppraise:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectAppraiseService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectAppraise/update")
    //@RequiresPermissions("pjProjectAppraise:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectAppraiseEntity pjProjectAppraise){
        pjProjectAppraiseService.updateById(pjProjectAppraise);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectAppraise/listByPage")
    //@RequiresPermissions("pjProjectAppraise:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectAppraiseEntity pjProjectAppraise){
        Page page = new Page(pjProjectAppraise.getPage(), pjProjectAppraise.getLimit());
        LambdaQueryWrapper<PjProjectAppraiseEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjProjectAppraiseEntity::getRefProjectName, pjProjectAppraise.getRefProjectName()==null?"":pjProjectAppraise.getRefProjectName());
        queryWrapper.like(PjProjectAppraiseEntity::getRefUsername, pjProjectAppraise.getRefUsername()==null?"":pjProjectAppraise.getRefUsername());
        if (!CollectionUtils.isEmpty(pjProjectAppraise.getCreateIds())) {
            queryWrapper.in(PjProjectAppraiseEntity::getCreateId, pjProjectAppraise.getCreateIds());
        }
        IPage<PjProjectAppraiseEntity> iPage = pjProjectAppraiseService.page(page, queryWrapper);
        List<PjProjectAppraiseEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("pjProjectAppraise/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody PjProjectAppraiseEntity pjProjectAppraise){
        Page page = new Page(pjProjectAppraise.getPage(), pjProjectAppraise.getLimit());
        LambdaQueryWrapper<PjProjectAppraiseEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjProjectAppraiseEntity::getId, pjProjectAppraise.getId());
        if (!CollectionUtils.isEmpty(pjProjectAppraise.getCreateIds())) {
            queryWrapper.in(PjProjectAppraiseEntity::getCreateId, pjProjectAppraise.getCreateIds());
        }
        IPage<PjProjectAppraiseEntity> iPage = pjProjectAppraiseService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectAppraise/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectAppraiseEntity pjProjectAppraise){
    	LambdaQueryWrapper<PjProjectAppraiseEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectAppraiseEntity::getId, pjProjectAppraise.getId());
    	//PjProjectAppraiseEntity one = pjProjectAppraiseService.getOne(queryWrapper);
    	PjProjectAppraiseEntity one = pjProjectAppraiseService.getById(pjProjectAppraise.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectAppraise/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectAppraiseEntity pjProjectAppraise){
        Page page = new Page(pjProjectAppraise.getPage(), pjProjectAppraise.getLimit());
        LambdaQueryWrapper<PjProjectAppraiseEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjProjectAppraiseEntity::getId, pjProjectAppraise.getId());
        IPage<PjProjectAppraiseEntity> iPage = pjProjectAppraiseService.page(page, queryWrapper);
        if (!CollectionUtils.isEmpty(pjProjectAppraise.getCreateIds())) {
            queryWrapper.in(PjProjectAppraiseEntity::getCreateId, pjProjectAppraise.getCreateIds());
        }
        log.info("\n this.pjProjectAppraiseMapper.selectCountUser()="+this.pjProjectAppraiseMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
