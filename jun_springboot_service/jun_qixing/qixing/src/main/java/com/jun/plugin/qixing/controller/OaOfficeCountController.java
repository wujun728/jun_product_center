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
import com.jun.plugin.qixing.entity.OaOfficeCountEntity;
import com.jun.plugin.qixing.mapper.OaOfficeCountMapper;
import com.jun.plugin.qixing.service.OaOfficeCountService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 办公用品申领申购
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-08 10:08:35
 */
@Controller
@RequestMapping("/")
@Slf4j
public class OaOfficeCountController  extends BaseFlowController {

    @Autowired
    private OaOfficeCountService oaOfficeCountService;

    @Autowired
    private OaOfficeCountMapper oaOfficeCountMapper;

    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaOfficeCount")
    public String oaOfficeCount() {
        return "oaofficecount/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("oaOfficeCount/add")
    //@RequiresPermissions("oaOfficeCount:add")
    @ResponseBody
    public DataResult add(@RequestBody OaOfficeCountEntity oaOfficeCount){
        oaOfficeCountService.save(oaOfficeCount);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaOfficeCount/delete")
    //@RequiresPermissions("oaOfficeCount:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaOfficeCountService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaOfficeCount/update")
    //@RequiresPermissions("oaOfficeCount:update")
    @ResponseBody
    public DataResult update(@RequestBody OaOfficeCountEntity oaOfficeCount){
        oaOfficeCountService.updateById(oaOfficeCount);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaOfficeCount/listByPage")
    //@RequiresPermissions("oaOfficeCount:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaOfficeCountEntity oaOfficeCount){
        Page page = new Page(oaOfficeCount.getPage(), oaOfficeCount.getLimit());
        LambdaQueryWrapper<OaOfficeCountEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaOfficeCount.getCreateIds())) {
            queryWrapper.in(OaOfficeCountEntity::getCreateId, oaOfficeCount.getCreateIds());
        }
        queryWrapper.like(OaOfficeCountEntity::getOffiecProductName, oaOfficeCount.getOffiecProductName()==null?"":oaOfficeCount.getOffiecProductName());
        queryWrapper.like(OaOfficeCountEntity::getDictProductType, oaOfficeCount.getDictProductType()==null?"":oaOfficeCount.getDictProductType());
        queryWrapper.like(OaOfficeCountEntity::getWhyDesc, oaOfficeCount.getWhyDesc()==null?"":oaOfficeCount.getWhyDesc());
        queryWrapper.like(OaOfficeCountEntity::getOfficeTodo, oaOfficeCount.getOfficeTodo()==null?"":oaOfficeCount.getOfficeTodo());
        IPage<OaOfficeCountEntity> iPage = oaOfficeCountService.page(page, queryWrapper);
        List<OaOfficeCountEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						oaOfficeCountService.updateById(item);
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
    @PostMapping("oaOfficeCount/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaOfficeCountEntity oaOfficeCount){
        Page page = new Page(oaOfficeCount.getPage(), oaOfficeCount.getLimit());
        LambdaQueryWrapper<OaOfficeCountEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaOfficeCount.getCreateIds())) {
            queryWrapper.in(OaOfficeCountEntity::getCreateId, oaOfficeCount.getCreateIds());
        }
        //queryWrapper.like(OaOfficeCountEntity::getId, oaOfficeCount.getId());
        IPage<OaOfficeCountEntity> iPage = oaOfficeCountService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaOfficeCount/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaOfficeCountEntity oaOfficeCount){
    	LambdaQueryWrapper<OaOfficeCountEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaOfficeCountEntity::getId, oaOfficeCount.getId());
    	//OaOfficeCountEntity one = oaOfficeCountService.getOne(queryWrapper);
    	OaOfficeCountEntity one = oaOfficeCountService.getById(oaOfficeCount.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaOfficeCount/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaOfficeCountEntity oaOfficeCount){
        Page page = new Page(oaOfficeCount.getPage(), oaOfficeCount.getLimit());
        LambdaQueryWrapper<OaOfficeCountEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaOfficeCount.getCreateIds())) {
            queryWrapper.in(OaOfficeCountEntity::getCreateId, oaOfficeCount.getCreateIds());
        }
        //queryWrapper.like(OaOfficeCountEntity::getId, oaOfficeCount.getId());
        IPage<OaOfficeCountEntity> iPage = oaOfficeCountService.page(page, queryWrapper);
        log.info("\n this.oaOfficeCountMapper.selectCountUser()="+this.oaOfficeCountMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
