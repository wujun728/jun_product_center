package com.jun.plugin.qixing.controller;

import com.alibaba.fastjson2.JSON;
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

////import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.entity.OaOfficeCount2Entity;
import com.jun.plugin.qixing.mapper.OaOfficeCount2Mapper;
import com.jun.plugin.qixing.service.OaOfficeCount2Service;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 办公用品申领申购
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-11-27 11:09:09
 */
@Controller
@RequestMapping("/")
@Slf4j
public class OaOfficeCount2Controller  extends BaseFlowController  {

    @Autowired
    private OaOfficeCount2Service oaOfficeCount2Service;

    @Autowired
    private OaOfficeCount2Mapper oaOfficeCount2Mapper;
    
//    @Resource
//    HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaOfficeCount2")
    public String oaOfficeCount2() {
        return "oaofficecount2/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("oaOfficeCount2/add")
    //@RequiresPermissions("oaOfficeCount2:add")
    @ResponseBody
    public DataResult add(@RequestBody OaOfficeCount2Entity oaOfficeCount2){
        oaOfficeCount2Service.save(oaOfficeCount2);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaOfficeCount2/delete")
    //@RequiresPermissions("oaOfficeCount2:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaOfficeCount2Service.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaOfficeCount2/update")
    //@RequiresPermissions("oaOfficeCount2:update")
    @ResponseBody
    public DataResult update(@RequestBody OaOfficeCount2Entity oaOfficeCount2){
        oaOfficeCount2Service.updateById(oaOfficeCount2);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaOfficeCount2/listByPage")
    //@RequiresPermissions("oaOfficeCount2:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaOfficeCount2Entity oaOfficeCount2){
        Page page = new Page(oaOfficeCount2.getPage(), oaOfficeCount2.getLimit());
        LambdaQueryWrapper<OaOfficeCount2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaOfficeCount2.getCreateIds())) {
            queryWrapper.in(OaOfficeCount2Entity::getCreateId, oaOfficeCount2.getCreateIds());
        }
        queryWrapper.like(OaOfficeCount2Entity::getId, oaOfficeCount2.getId()==null?"":oaOfficeCount2.getId());
        queryWrapper.like(OaOfficeCount2Entity::getOffiecProductName, oaOfficeCount2.getOffiecProductName()==null?"":oaOfficeCount2.getOffiecProductName());
        queryWrapper.like(OaOfficeCount2Entity::getDictProductType, oaOfficeCount2.getDictProductType()==null?"":oaOfficeCount2.getDictProductType());
        queryWrapper.like(OaOfficeCount2Entity::getWhyDesc, oaOfficeCount2.getWhyDesc()==null?"":oaOfficeCount2.getWhyDesc());
        queryWrapper.like(OaOfficeCount2Entity::getOfficeTodo, oaOfficeCount2.getOfficeTodo()==null?"":oaOfficeCount2.getOfficeTodo());
        IPage<OaOfficeCount2Entity> iPage = oaOfficeCount2Service.page(page, queryWrapper);
        List<OaOfficeCount2Entity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()!=null && item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						oaOfficeCount2Service.updateById(item);
						log.info("oaOfficeCount2Service.updateById(item)="+JSON.toJSONString(item));
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
    @PostMapping("oaOfficeCount2/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaOfficeCount2Entity oaOfficeCount2){
        Page page = new Page(oaOfficeCount2.getPage(), oaOfficeCount2.getLimit());
        LambdaQueryWrapper<OaOfficeCount2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaOfficeCount2.getCreateIds())) {
            queryWrapper.in(OaOfficeCount2Entity::getCreateId, oaOfficeCount2.getCreateIds());
        }
        //queryWrapper.like(OaOfficeCount2Entity::getId, oaOfficeCount2.getId());
        IPage<OaOfficeCount2Entity> iPage = oaOfficeCount2Service.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaOfficeCount2/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaOfficeCount2Entity oaOfficeCount2){
    	LambdaQueryWrapper<OaOfficeCount2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaOfficeCount2Entity::getId, oaOfficeCount2.getId());
    	//OaOfficeCount2Entity one = oaOfficeCount2Service.getOne(queryWrapper);
    	OaOfficeCount2Entity one = oaOfficeCount2Service.getById(oaOfficeCount2.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaOfficeCount2/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaOfficeCount2Entity oaOfficeCount2){
        Page page = new Page(oaOfficeCount2.getPage(), oaOfficeCount2.getLimit());
        LambdaQueryWrapper<OaOfficeCount2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(oaOfficeCount2.getCreateIds())) {
            queryWrapper.in(OaOfficeCount2Entity::getCreateId, oaOfficeCount2.getCreateIds());
        }
        //queryWrapper.like(OaOfficeCount2Entity::getId, oaOfficeCount2.getId());
        IPage<OaOfficeCount2Entity> iPage = oaOfficeCount2Service.page(page, queryWrapper);
        log.info("\n this.oaOfficeCount2Mapper.selectCountUser()="+this.oaOfficeCount2Mapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
