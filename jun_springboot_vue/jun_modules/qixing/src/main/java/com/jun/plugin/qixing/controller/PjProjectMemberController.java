package com.jun.plugin.qixing.controller;

import cn.hutool.core.util.IdUtil;
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

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

//import com.jun.plugin.system.common.utils.SnowflakeIdWorker;
//import com.jun.plugin.system.service.HttpSessionService;

import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.entity.PjProjectMemberEntity;
import com.jun.plugin.qixing.mapper.PjProjectMemberMapper;
import com.jun.plugin.qixing.service.PjProjectMemberService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 项目成员与结算
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 11:22:36
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectMemberController extends BaseFlowController {

    @Autowired
    private PjProjectMemberService pjProjectMemberService;

    @Autowired
    private PjProjectMemberMapper pjProjectMemberMapper;
    
    //@Resource
   // HttpSessionService sessionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProjectMember")
    public String pjProjectMember() {
        return "pjprojectmember/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProjectMember/add")
    //@RequiresPermissions("pjProjectMember:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectMemberEntity pjProjectMember){
    	String mname = pjProjectMember.getRefMemberName();
    	for(String name : mname.split(",")) {
    		pjProjectMember.setRefMemberName(name);
//    		pjProjectMember.setId(String.valueOf(SnowflakeIdWorker.getID()));
    		pjProjectMember.setId(String.valueOf(IdUtil.getSnowflakeNextId()));
    		pjProjectMemberService.save(pjProjectMember);
    	}
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProjectMember/delete")
    //@RequiresPermissions("pjProjectMember:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectMemberService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProjectMember/update")
    //@RequiresPermissions("pjProjectMember:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectMemberEntity pjProjectMember){
        pjProjectMemberService.updateById(pjProjectMember);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectMember/listByPage")
    //@RequiresPermissions("pjProjectMember:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectMemberEntity pjProjectMember){
        Page page = new Page(pjProjectMember.getPage(), pjProjectMember.getLimit());
        LambdaQueryWrapper<PjProjectMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectMember.getCreateIds())) {
            queryWrapper.in(PjProjectMemberEntity::getCreateId, pjProjectMember.getCreateIds());
        }
        //queryWrapper.like(PjProjectMemberEntity::getId, pjProjectMember.getId());
        queryWrapper.like(PjProjectMemberEntity::getRefProjectName, pjProjectMember.getRefProjectName()==null?"":pjProjectMember.getRefProjectName());
        queryWrapper.like(PjProjectMemberEntity::getRefMemberName, pjProjectMember.getRefMemberName()==null?"":pjProjectMember.getRefMemberName());
        
        String id = pjProjectMember.getId();
        if(id!=null && id.contains(",")) {
        	List<String> ids = Arrays.asList(id.split(","));
        	queryWrapper.in(PjProjectMemberEntity::getId, ids);
        }
        IPage<PjProjectMemberEntity> iPage = pjProjectMemberService.page(page, queryWrapper);
        List<PjProjectMemberEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						pjProjectMemberService.updateById(item);
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
    
    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProjectMember/viewListByPage")
//    //@RequiresPermissions("pjProjectMember:list")
    @ResponseBody
//    @DataScope
    public DataResult viewListByPage(@RequestBody PjProjectMemberEntity pjProjectMember){
    	Page page = new Page(pjProjectMember.getPage(), pjProjectMember.getLimit());
    	LambdaQueryWrapper<PjProjectMemberEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
//    	if (!CollectionUtils.isEmpty(pjProjectMember.getCreateIds())) {
//    		queryWrapper.in(PjProjectMemberEntity::getCreateId, pjProjectMember.getCreateIds());
//    	}
    	//queryWrapper.like(PjProjectMemberEntity::getId, pjProjectMember.getId());
//    	queryWrapper.like(PjProjectMemberEntity::getRefProjectName, pjProjectMember.getRefProjectName()==null?"":pjProjectMember.getRefProjectName());
//    	queryWrapper.like(PjProjectMemberEntity::getRefMemberName, pjProjectMember.getRefMemberName()==null?"":pjProjectMember.getRefMemberName());
    	
    	String id = pjProjectMember.getId();
    	String pid = pjProjectMember.getRefProjectCode();
    	if(id!=null && id.contains(",")) {
    		List<String> ids = Arrays.asList(id.split(","));
    		queryWrapper.in(PjProjectMemberEntity::getId, ids);
    	}
    	String sql = " SELECT id from pj_project_member pjm \r\n"
    			+ "where pjm.ref_project_code in (\r\n"
    			+ "	SELECT p.project_code from pj_project p where p.id = '"+pid+"'\r\n"
    			+ ")\r\n"
    			+ "or ref_project_name in (\r\n"
    			+ "	SELECT p.project_name from pj_project p where p.id = '"+pid+"'\r\n"
    			+ ") ";
		queryWrapper.inSql(true,PjProjectMemberEntity::getId, sql);
    	IPage<PjProjectMemberEntity> iPage = pjProjectMemberService.page(page, queryWrapper);
    	List<PjProjectMemberEntity> records = iPage.getRecords();
    	String userid = "admin";// sessionService.getCurrentUserId();
    	records.forEach(item -> {
    		if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
    			this.setFlowStatusInfo(item);
    			System.err.println(item.getOrderStatus());
    			if(item.getOrderState()==0) {
    				if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
    					item.setOrderStatus(item.getOrderState());
    					pjProjectMemberService.updateById(item);
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
    @PostMapping("pjProjectMember/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody PjProjectMemberEntity pjProjectMember){
        Page page = new Page(pjProjectMember.getPage(), pjProjectMember.getLimit());
        LambdaQueryWrapper<PjProjectMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectMember.getCreateIds())) {
            queryWrapper.in(PjProjectMemberEntity::getCreateId, pjProjectMember.getCreateIds());
        }
        //queryWrapper.like(PjProjectMemberEntity::getId, pjProjectMember.getId());
        IPage<PjProjectMemberEntity> iPage = pjProjectMemberService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProjectMember/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectMemberEntity pjProjectMember){
    	LambdaQueryWrapper<PjProjectMemberEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(PjProjectMemberEntity::getId, pjProjectMember.getId());
    	//PjProjectMemberEntity one = pjProjectMemberService.getOne(queryWrapper);
    	PjProjectMemberEntity one = pjProjectMemberService.getById(pjProjectMember.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProjectMember/findListBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectMemberEntity pjProjectMember){
        Page page = new Page(pjProjectMember.getPage(), pjProjectMember.getLimit());
        LambdaQueryWrapper<PjProjectMemberEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!CollectionUtils.isEmpty(pjProjectMember.getCreateIds())) {
            queryWrapper.in(PjProjectMemberEntity::getCreateId, pjProjectMember.getCreateIds());
        }
        //queryWrapper.like(PjProjectMemberEntity::getId, pjProjectMember.getId());
        IPage<PjProjectMemberEntity> iPage = pjProjectMemberService.page(page, queryWrapper);
        log.info("\n this.pjProjectMemberMapper.selectCountUser()="+this.pjProjectMemberMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
