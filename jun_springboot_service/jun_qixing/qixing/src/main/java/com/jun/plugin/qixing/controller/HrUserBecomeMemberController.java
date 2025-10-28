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

import com.jun.plugin.common.aop.annotation.DataScope;

import com.jun.plugin.qixing.entity.HrUserBecomeMemberEntity;
import com.jun.plugin.qixing.mapper.HrUserBecomeMemberMapper;
import com.jun.plugin.qixing.service.HrUserBecomeMemberService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 转正
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HrUserBecomeMemberController  extends BaseFlowController {

    @Autowired
    private HrUserBecomeMemberService hrUserBecomeMemberService;

    @Autowired
    private HrUserBecomeMemberMapper hrUserBecomeMemberMapper;

//    @Resource
//    HttpSessionService sessionService;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/hrUserBecomeMember")
    public String hrUserBecomeMember() {
        return "hruserbecomemember/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("hrUserBecomeMember/add")
    //@RequiresPermissions("hrUserBecomeMember:add")
    @ResponseBody
    public DataResult add(@RequestBody HrUserBecomeMemberEntity hrUserBecomeMember){
        hrUserBecomeMemberService.save(hrUserBecomeMember);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("hrUserBecomeMember/delete")
    //@RequiresPermissions("hrUserBecomeMember:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        hrUserBecomeMemberService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("hrUserBecomeMember/update")
    //@RequiresPermissions("hrUserBecomeMember:update")
    @ResponseBody
    public DataResult update(@RequestBody HrUserBecomeMemberEntity hrUserBecomeMember){
        hrUserBecomeMemberService.updateById(hrUserBecomeMember);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("hrUserBecomeMember/listByPage")
    //@RequiresPermissions("hrUserBecomeMember:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody HrUserBecomeMemberEntity hrUserBecomeMember){
        Page page = new Page(hrUserBecomeMember.getPage(), hrUserBecomeMember.getLimit());
        LambdaQueryWrapper<HrUserBecomeMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserBecomeMember.getCreateIds())) {
            queryWrapper.in(HrUserBecomeMemberEntity::getCreateId, hrUserBecomeMember.getCreateIds());
        }
        queryWrapper.like(HrUserBecomeMemberEntity::getUsername, hrUserBecomeMember.getUsername()==null?"":hrUserBecomeMember.getUsername());
        IPage<HrUserBecomeMemberEntity> iPage = hrUserBecomeMemberService.page(page, queryWrapper);
        List<HrUserBecomeMemberEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						hrUserBecomeMemberService.updateById(item);
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
    @PostMapping("hrUserBecomeMember/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody HrUserBecomeMemberEntity hrUserBecomeMember){
        Page page = new Page(hrUserBecomeMember.getPage(), hrUserBecomeMember.getLimit());
        LambdaQueryWrapper<HrUserBecomeMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserBecomeMember.getCreateIds())) {
            queryWrapper.in(HrUserBecomeMemberEntity::getCreateId, hrUserBecomeMember.getCreateIds());
        }
        //queryWrapper.like(HrUserBecomeMemberEntity::getId, hrUserBecomeMember.getId());
        IPage<HrUserBecomeMemberEntity> iPage = hrUserBecomeMemberService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("hrUserBecomeMember/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody HrUserBecomeMemberEntity hrUserBecomeMember){
    	LambdaQueryWrapper<HrUserBecomeMemberEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(HrUserBecomeMemberEntity::getId, hrUserBecomeMember.getId());
    	//HrUserBecomeMemberEntity one = hrUserBecomeMemberService.getOne(queryWrapper);
    	HrUserBecomeMemberEntity one = hrUserBecomeMemberService.getById(hrUserBecomeMember.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("hrUserBecomeMember/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody HrUserBecomeMemberEntity hrUserBecomeMember){
        Page page = new Page(hrUserBecomeMember.getPage(), hrUserBecomeMember.getLimit());
        LambdaQueryWrapper<HrUserBecomeMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(hrUserBecomeMember.getCreateIds())) {
            queryWrapper.in(HrUserBecomeMemberEntity::getCreateId, hrUserBecomeMember.getCreateIds());
        }
        //queryWrapper.like(HrUserBecomeMemberEntity::getId, hrUserBecomeMember.getId());
        IPage<HrUserBecomeMemberEntity> iPage = hrUserBecomeMemberService.page(page, queryWrapper);
        log.info("\n this.hrUserBecomeMemberMapper.selectCountUser()="+this.hrUserBecomeMemberMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
