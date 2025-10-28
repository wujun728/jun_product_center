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

import com.jun.plugin.qixing.entity.OaPomsWorkmarksTimesEntity;
import com.jun.plugin.qixing.mapper.OaPomsWorkmarksTimesMapper;
import com.jun.plugin.qixing.service.OaPomsWorkmarksTimesService;



/**
 * 考勤记录
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
@Controller
@RequestMapping("/")
@Slf4j
public class OaPomsWorkmarksTimesController {

    @Autowired
    private OaPomsWorkmarksTimesService oaPomsWorkmarksTimesService;

    @Autowired
    private OaPomsWorkmarksTimesMapper oaPomsWorkmarksTimesMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaPomsWorkmarksTimes")
    public String oaPomsWorkmarksTimes() {
        return "oapomsworkmarkstimes/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("oaPomsWorkmarksTimes/add")
    //@RequiresPermissions("oaPomsWorkmarksTimes:add")
    @ResponseBody
    public DataResult add(@RequestBody OaPomsWorkmarksTimesEntity oaPomsWorkmarksTimes){
        oaPomsWorkmarksTimesService.save(oaPomsWorkmarksTimes);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaPomsWorkmarksTimes/delete")
    //@RequiresPermissions("oaPomsWorkmarksTimes:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaPomsWorkmarksTimesService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaPomsWorkmarksTimes/update")
    //@RequiresPermissions("oaPomsWorkmarksTimes:update")
    @ResponseBody
    public DataResult update(@RequestBody OaPomsWorkmarksTimesEntity oaPomsWorkmarksTimes){
        oaPomsWorkmarksTimesService.updateById(oaPomsWorkmarksTimes);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaPomsWorkmarksTimes/listByPage")
    //@RequiresPermissions("oaPomsWorkmarksTimes:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaPomsWorkmarksTimesEntity oaPomsWorkmarksTimes){
        Page page = new Page(oaPomsWorkmarksTimes.getPage(), oaPomsWorkmarksTimes.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksTimesEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(OaPomsWorkmarksTimesEntity::getId, oaPomsWorkmarksTimes.getId());
        IPage<OaPomsWorkmarksTimesEntity> iPage = oaPomsWorkmarksTimesService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaPomsWorkmarksTimes/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaPomsWorkmarksTimesEntity oaPomsWorkmarksTimes){
    	LambdaQueryWrapper<OaPomsWorkmarksTimesEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaPomsWorkmarksTimesEntity::getId, oaPomsWorkmarksTimes.getId());
    	//OaPomsWorkmarksTimesEntity one = oaPomsWorkmarksTimesService.getOne(queryWrapper);
    	OaPomsWorkmarksTimesEntity one = oaPomsWorkmarksTimesService.getById(oaPomsWorkmarksTimes.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaPomsWorkmarksTimes/listBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaPomsWorkmarksTimesEntity oaPomsWorkmarksTimes){
        Page page = new Page(oaPomsWorkmarksTimes.getPage(), oaPomsWorkmarksTimes.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksTimesEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(OaPomsWorkmarksTimesEntity::getId, oaPomsWorkmarksTimes.getId());
        IPage<OaPomsWorkmarksTimesEntity> iPage = oaPomsWorkmarksTimesService.page(page, queryWrapper);
        log.info("\n this.oaPomsWorkmarksTimesMapper.selectCountUser()="+this.oaPomsWorkmarksTimesMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
