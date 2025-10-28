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

////import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.entity.HrUserHireEntity;
import com.jun.plugin.qixing.mapper.HrUserHireMapper;
import com.jun.plugin.qixing.service.HrUserHireService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 录用审批
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-11 18:03:44
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrUserHireController  extends BaseFlowController {

    @Autowired
    private HrUserHireService hrUserHireService;

    @Autowired
    private HrUserHireMapper hrUserHireMapper;

//    @Resource
//    HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrUserHire")
    public String hrUserHire() {
        return "hruserhire/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrUserHire/add")
    //@RequiresPermissions("hrUserHire:add")
    @ResponseBody
    public DataResult add(@RequestBody HrUserHireEntity hrUserHire){
        hrUserHireService.save(hrUserHire);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrUserHire/delete")
    //@RequiresPermissions("hrUserHire:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrUserHireService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrUserHire/update")
    //@RequiresPermissions("hrUserHire:update")
    @ResponseBody
    public DataResult update(@RequestBody HrUserHireEntity hrUserHire){
        hrUserHireService.updateById(hrUserHire);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrUserHire/listByPage")
    //@RequiresPermissions("hrUserHire:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrUserHireEntity hrUserHire){
        Page page = new Page(hrUserHire.getPage(), hrUserHire.getLimit());
        LambdaQueryWrapper<HrUserHireEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserHire.getCreateIds())) {
            queryWrapper.in(HrUserHireEntity::getCreateId, hrUserHire.getCreateIds());
        }
        queryWrapper.like(HrUserHireEntity::getRefJobUsername, hrUserHire.getRefJobUsername()==null?"":hrUserHire.getRefJobUsername());
        IPage<HrUserHireEntity> iPage = hrUserHireService.page(page, queryWrapper);
        List<HrUserHireEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()!=null && item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						hrUserHireService.updateById(item);
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
    @PostMapping("hrUserHire/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrUserHireEntity hrUserHire){
        Page page = new Page(hrUserHire.getPage(), hrUserHire.getLimit());
        LambdaQueryWrapper<HrUserHireEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserHire.getCreateIds())) {
            queryWrapper.in(HrUserHireEntity::getCreateId, hrUserHire.getCreateIds());
        }
        queryWrapper.eq(HrUserHireEntity::getOrderStatus, 0);
        IPage<HrUserHireEntity> iPage = hrUserHireService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrUserHire/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrUserHireEntity hrUserHire){
    	LambdaQueryWrapper<HrUserHireEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrUserHireEntity::getId, hrUserHire.getId());
    	//HrUserHireEntity one = hrUserHireService.getOne(queryWrapper);
    	HrUserHireEntity one = hrUserHireService.getById(hrUserHire.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrUserHire/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrUserHireEntity hrUserHire){
        Page page = new Page(hrUserHire.getPage(), hrUserHire.getLimit());
        LambdaQueryWrapper<HrUserHireEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserHire.getCreateIds())) {
            queryWrapper.in(HrUserHireEntity::getCreateId, hrUserHire.getCreateIds());
        }
        //queryWrapper.like(HrUserHireEntity::getId, hrUserHire.getId());
        IPage<HrUserHireEntity> iPage = hrUserHireService.page(page, queryWrapper);
        log.info("\n this.hrUserHireMapper.selectCountUser()="+this.hrUserHireMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
