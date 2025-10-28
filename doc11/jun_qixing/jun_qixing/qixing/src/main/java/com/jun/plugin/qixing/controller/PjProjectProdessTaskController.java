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
import com.jun.plugin.qixing.entity.PjProjectProdessTaskEntity;
import com.jun.plugin.qixing.mapper.PjProjectProdessTaskMapper;
import com.jun.plugin.qixing.service.PjProjectProdessTaskService;



/**
 * 项目进度与任务(WBS)
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 21:10:39
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectProdessTaskController {

    @Autowired
    private PjProjectProdessTaskService pjProjectProdessTaskService;

    @Autowired
    private PjProjectProdessTaskMapper pjProjectProdessTaskMapper;
    
    //@Resource
   // HttpSessionService sessionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectProdessTask")
    public String pjProjectProdessTask() {
        return "pjprojectprodesstask/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectProdessTask/add")
    //@RequiresPermissions("pjProjectProdessTask:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectProdessTaskEntity pjProjectProdessTask){
        pjProjectProdessTaskService.save(pjProjectProdessTask);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectProdessTask/delete")
    //@RequiresPermissions("pjProjectProdessTask:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectProdessTaskService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectProdessTask/update")
    //@RequiresPermissions("pjProjectProdessTask:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectProdessTaskEntity pjProjectProdessTask){
        pjProjectProdessTaskService.updateById(pjProjectProdessTask);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectProdessTask/listByPage")
    //@RequiresPermissions("pjProjectProdessTask:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectProdessTaskEntity pjProjectProdessTask){
        Page page = new Page(pjProjectProdessTask.getPage(), pjProjectProdessTask.getLimit());
        LambdaQueryWrapper<PjProjectProdessTaskEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(PjProjectProdessTaskEntity::getRefProjectName, pjProjectProdessTask.getRefProjectName()==null?"":pjProjectProdessTask.getRefProjectName());
    	queryWrapper.like(PjProjectProdessTaskEntity::getTaskName, pjProjectProdessTask.getTaskName()==null?"":pjProjectProdessTask.getTaskName());
    	queryWrapper.like(PjProjectProdessTaskEntity::getTaskDetail, pjProjectProdessTask.getTaskDetail()==null?"":pjProjectProdessTask.getTaskDetail());
        //查询条件示例
    	if (!CollectionUtils.isEmpty(pjProjectProdessTask.getCreateIds())) {
            queryWrapper.in(PjProjectProdessTaskEntity::getCreateId, pjProjectProdessTask.getCreateIds());
        }
        //queryWrapper.like(PjProjectProdessTaskEntity::getId, pjProjectProdessTask.getId());
        IPage<PjProjectProdessTaskEntity> iPage = pjProjectProdessTaskService.page(page, queryWrapper);
        List<PjProjectProdessTaskEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("pjProjectProdessTask/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody PjProjectProdessTaskEntity pjProjectProdessTask){
        Page page = new Page(pjProjectProdessTask.getPage(), pjProjectProdessTask.getLimit());
        LambdaQueryWrapper<PjProjectProdessTaskEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectProdessTask.getCreateIds())) {
            queryWrapper.in(PjProjectProdessTaskEntity::getCreateId, pjProjectProdessTask.getCreateIds());
        }
        //queryWrapper.like(PjProjectProdessTaskEntity::getId, pjProjectProdessTask.getId());
        IPage<PjProjectProdessTaskEntity> iPage = pjProjectProdessTaskService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectProdessTask/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectProdessTaskEntity pjProjectProdessTask){
    	LambdaQueryWrapper<PjProjectProdessTaskEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectProdessTaskEntity::getId, pjProjectProdessTask.getId());
    	//PjProjectProdessTaskEntity one = pjProjectProdessTaskService.getOne(queryWrapper);
    	PjProjectProdessTaskEntity one = pjProjectProdessTaskService.getById(pjProjectProdessTask.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectProdessTask/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectProdessTaskEntity pjProjectProdessTask){
        Page page = new Page(pjProjectProdessTask.getPage(), pjProjectProdessTask.getLimit());
        LambdaQueryWrapper<PjProjectProdessTaskEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectProdessTask.getCreateIds())) {
            queryWrapper.in(PjProjectProdessTaskEntity::getCreateId, pjProjectProdessTask.getCreateIds());
        }
        //queryWrapper.like(PjProjectProdessTaskEntity::getId, pjProjectProdessTask.getId());
        IPage<PjProjectProdessTaskEntity> iPage = pjProjectProdessTaskService.page(page, queryWrapper);
        log.info("\n this.pjProjectProdessTaskMapper.selectCountUser()="+this.pjProjectProdessTaskMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
