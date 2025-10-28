package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.jun.plugin.qixing.entity.OaPomsWorkmarksWorktimesV2Entity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
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
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.mapper.OaPomsWorkmarksWorktimesMapper;
import com.jun.plugin.qixing.service.OaPomsWorkmarksWorktimesService;



/**
 * 考勤记录
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:33
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class OaPomsWorkmarksWorktimesController {

    @Autowired
    private OaPomsWorkmarksWorktimesService oaPomsWorkmarksWorktimesService;

    @Autowired
    private OaPomsWorkmarksWorktimesMapper oaPomsWorkmarksWorktimesMapper;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaPomsWorkmarksWorktimes")
    public String oaPomsWorkmarksWorktimes() {
        return "oapomsworkmarksworktimes/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("oaPomsWorkmarksWorktimes/add")
    //@RequiresPermissions("oaPomsWorkmarksWorktimes:add")
    @ResponseBody
    public DataResult add(@RequestBody OaPomsWorkmarksWorktimesV2Entity oaPomsWorkmarksWorktimes){
        oaPomsWorkmarksWorktimesService.save(oaPomsWorkmarksWorktimes);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaPomsWorkmarksWorktimes/delete")
    //@RequiresPermissions("oaPomsWorkmarksWorktimes:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaPomsWorkmarksWorktimesService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaPomsWorkmarksWorktimes/update")
    //@RequiresPermissions("oaPomsWorkmarksWorktimes:update")
    @ResponseBody
    public DataResult update(@RequestBody OaPomsWorkmarksWorktimesV2Entity oaPomsWorkmarksWorktimes){
        oaPomsWorkmarksWorktimesService.updateById(oaPomsWorkmarksWorktimes);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaPomsWorkmarksWorktimes/listByPage")
    //@RequiresPermissions("oaPomsWorkmarksWorktimes:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaPomsWorkmarksWorktimesV2Entity oaPomsWorkmarksWorktimes){
        Page page = new Page(oaPomsWorkmarksWorktimes.getPage(), oaPomsWorkmarksWorktimes.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksWorktimesV2Entity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(OaPomsWorkmarksWorktimesV2Entity::getCreateTime);
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaPomsWorkmarksWorktimes.getCreateIds())) {
            queryWrapper.in(OaPomsWorkmarksWorktimesV2Entity::getCreateId, oaPomsWorkmarksWorktimes.getCreateIds());
        }
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksWorktimesEntity::getId, oaPomsWorkmarksWorktimes.getId()==null?"":oaPomsWorkmarksWorktimes.getId());
        IPage<OaPomsWorkmarksWorktimesV2Entity> iPage = oaPomsWorkmarksWorktimesService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("oaPomsWorkmarksWorktimes/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaPomsWorkmarksWorktimesV2Entity oaPomsWorkmarksWorktimes){
        Page page = new Page(oaPomsWorkmarksWorktimes.getPage(), oaPomsWorkmarksWorktimes.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksWorktimesV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksWorktimesEntity::getId, oaPomsWorkmarksWorktimes.getId());
        IPage<OaPomsWorkmarksWorktimesV2Entity> iPage = oaPomsWorkmarksWorktimesService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaPomsWorkmarksWorktimes/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaPomsWorkmarksWorktimesV2Entity oaPomsWorkmarksWorktimes){
    	LambdaQueryWrapper<OaPomsWorkmarksWorktimesV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaPomsWorkmarksWorktimesEntity::getId, oaPomsWorkmarksWorktimes.getId());
    	//OaPomsWorkmarksWorktimesEntity one = oaPomsWorkmarksWorktimesService.getOne(queryWrapper);
    	OaPomsWorkmarksWorktimesV2Entity one = oaPomsWorkmarksWorktimesService.getById(oaPomsWorkmarksWorktimes.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaPomsWorkmarksWorktimes/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaPomsWorkmarksWorktimesV2Entity oaPomsWorkmarksWorktimes){
        Page page = new Page(oaPomsWorkmarksWorktimes.getPage(), oaPomsWorkmarksWorktimes.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksWorktimesV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksWorktimesEntity::getId, oaPomsWorkmarksWorktimes.getId());
        IPage<OaPomsWorkmarksWorktimesV2Entity> iPage = oaPomsWorkmarksWorktimesService.page(page, queryWrapper);
        log.info("\n this.oaPomsWorkmarksWorktimesMapper.selectCountUser()="+this.oaPomsWorkmarksWorktimesMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
