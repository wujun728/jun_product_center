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
import com.jun.plugin.qixing.entity.HrUserResumeEntity;
import com.jun.plugin.qixing.mapper.HrUserResumeMapper;
import com.jun.plugin.qixing.service.HrUserResumeService;



/**
 * 面试候选人
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:37
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrUserResumeController {

    @Autowired
    private HrUserResumeService hrUserResumeService;

    @Autowired
    private HrUserResumeMapper hrUserResumeMapper;
    
    //@Resource
   // HttpSessionService sessionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrUserResume")
    public String hrUserResume() {
        return "hruserresume/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrUserResume/add")
    //@RequiresPermissions("hrUserResume:add")
    @ResponseBody
    public DataResult add(@RequestBody HrUserResumeEntity hrUserResume){
        hrUserResumeService.save(hrUserResume);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrUserResume/delete")
    //@RequiresPermissions("hrUserResume:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrUserResumeService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrUserResume/update")
    //@RequiresPermissions("hrUserResume:update")
    @ResponseBody
    public DataResult update(@RequestBody HrUserResumeEntity hrUserResume){
        hrUserResumeService.updateById(hrUserResume);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrUserResume/listByPage")
    //@RequiresPermissions("hrUserResume:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrUserResumeEntity hrUserResume){
        Page page = new Page(hrUserResume.getPage(), hrUserResume.getLimit());
        LambdaQueryWrapper<HrUserResumeEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
//        if (!CollectionUtils.isEmpty(hrUserResume.getCreateIds())) {
//            queryWrapper.in(HrUserResumeEntity::getCreateId, hrUserResume.getCreateIds());
//        }
        queryWrapper.like(HrUserResumeEntity::getName, hrUserResume.getName()==null?"":hrUserResume.getName());
        queryWrapper.like(HrUserResumeEntity::getPhone, hrUserResume.getPhone()==null?"":hrUserResume.getPhone());
        if (!CollectionUtils.isEmpty(hrUserResume.getCreateIds())) {
            queryWrapper.in(HrUserResumeEntity::getCreateId, hrUserResume.getCreateIds());
        }
        IPage<HrUserResumeEntity> iPage = hrUserResumeService.page(page, queryWrapper);
        List<HrUserResumeEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("hrUserResume/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrUserResumeEntity hrUserResume){
        Page page = new Page(hrUserResume.getPage(), hrUserResume.getLimit());
        LambdaQueryWrapper<HrUserResumeEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(HrUserResumeEntity::getId, hrUserResume.getId());
        IPage<HrUserResumeEntity> iPage = hrUserResumeService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrUserResume/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrUserResumeEntity hrUserResume){
    	LambdaQueryWrapper<HrUserResumeEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrUserResumeEntity::getId, hrUserResume.getId());
    	//HrUserResumeEntity one = hrUserResumeService.getOne(queryWrapper);
    	HrUserResumeEntity one = hrUserResumeService.getById(hrUserResume.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrUserResume/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrUserResumeEntity hrUserResume){
        Page page = new Page(hrUserResume.getPage(), hrUserResume.getLimit());
        LambdaQueryWrapper<HrUserResumeEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(HrUserResumeEntity::getId, hrUserResume.getId());
        IPage<HrUserResumeEntity> iPage = hrUserResumeService.page(page, queryWrapper);
        log.info("\n this.hrUserResumeMapper.selectCountUser()="+this.hrUserResumeMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
