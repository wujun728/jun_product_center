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
import com.jun.plugin.qixing.entity.PjProjectBorrowEntity;
import com.jun.plugin.qixing.mapper.PjProjectBorrowMapper;
import com.jun.plugin.qixing.service.PjProjectBorrowService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 项目借阅
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 13:58:41
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectBorrowController extends BaseFlowController{

    @Autowired
    private PjProjectBorrowService pjProjectBorrowService;

    @Autowired
    private PjProjectBorrowMapper pjProjectBorrowMapper;

    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectBorrow")
    public String pjProjectBorrow() {
        return "pjprojectborrow/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectBorrow/add")
    //@RequiresPermissions("pjProjectBorrow:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectBorrowEntity pjProjectBorrow){
        pjProjectBorrowService.save(pjProjectBorrow);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectBorrow/delete")
    //@RequiresPermissions("pjProjectBorrow:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectBorrowService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectBorrow/update")
    //@RequiresPermissions("pjProjectBorrow:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectBorrowEntity pjProjectBorrow){
        pjProjectBorrowService.updateById(pjProjectBorrow);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectBorrow/listByPage")
    //@RequiresPermissions("pjProjectBorrow:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectBorrowEntity pjProjectBorrow){
        Page page = new Page(pjProjectBorrow.getPage(), pjProjectBorrow.getLimit());
        LambdaQueryWrapper<PjProjectBorrowEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(PjProjectBorrowEntity::getRefProjectName, pjProjectBorrow.getRefProjectName()==null?"":pjProjectBorrow.getRefProjectName());
        queryWrapper.like(PjProjectBorrowEntity::getRefUserName, pjProjectBorrow.getRefUserName()==null?"":pjProjectBorrow.getRefUserName());
        IPage<PjProjectBorrowEntity> iPage = pjProjectBorrowService.page(page, queryWrapper);
        if (!CollectionUtils.isEmpty(pjProjectBorrow.getCreateIds())) {
            queryWrapper.in(PjProjectBorrowEntity::getCreateId, pjProjectBorrow.getCreateIds());
        }
        List<PjProjectBorrowEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						pjProjectBorrowService.updateById(item);
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
    @PostMapping("pjProjectBorrow/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult listBySelect(@RequestBody PjProjectBorrowEntity pjProjectBorrow){
        Page page = new Page(pjProjectBorrow.getPage(), pjProjectBorrow.getLimit());
        LambdaQueryWrapper<PjProjectBorrowEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(PjProjectBorrowEntity::getId, pjProjectBorrow.getId());
        if (!CollectionUtils.isEmpty(pjProjectBorrow.getCreateIds())) {
            queryWrapper.in(PjProjectBorrowEntity::getCreateId, pjProjectBorrow.getCreateIds());
        }
        IPage<PjProjectBorrowEntity> iPage = pjProjectBorrowService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectBorrow/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectBorrowEntity pjProjectBorrow){
    	LambdaQueryWrapper<PjProjectBorrowEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectBorrowEntity::getId, pjProjectBorrow.getId());
    	//PjProjectBorrowEntity one = pjProjectBorrowService.getOne(queryWrapper);
    	PjProjectBorrowEntity one = pjProjectBorrowService.getById(pjProjectBorrow.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectBorrow/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectBorrowEntity pjProjectBorrow){
        Page page = new Page(pjProjectBorrow.getPage(), pjProjectBorrow.getLimit());
        LambdaQueryWrapper<PjProjectBorrowEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectBorrow.getCreateIds())) {
        	queryWrapper.in(PjProjectBorrowEntity::getCreateId, pjProjectBorrow.getCreateIds());
        }
        //queryWrapper.like(PjProjectBorrowEntity::getId, pjProjectBorrow.getId());
        IPage<PjProjectBorrowEntity> iPage = pjProjectBorrowService.page(page, queryWrapper);
        log.info("\n this.pjProjectBorrowMapper.selectCountUser()="+this.pjProjectBorrowMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
