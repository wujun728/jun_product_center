package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.jun.plugin.qixing.entity.PjProjectEntity;
import com.jun.plugin.qixing.mapper.PjProjectMapper;
import com.jun.plugin.qixing.service.PjProjectService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 项目信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-28 09:55:20
 */
@Controller
@RequestMapping("/")
@Slf4j
public class PjProjectController  extends BaseFlowController {

    @Autowired
    private PjProjectService pjProjectService;

    @Autowired
    private PjProjectMapper pjProjectMapper;
    
    @Autowired
    private PrimaryKeyService pks;
    
    //@Resource
   // HttpSessionService sessionService;


    /**
    * 跳转到页面
    */
    @GetMapping("/index/pjProject")
    public String pjProject(Model model) {
    	model.addAttribute("pkCode", pks.genCodeAndCheckExists("PRJ"));
        return "pjproject/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("pjProject/add")
    //@RequiresPermissions("pjProject:add")
    @ResponseBody
    public DataResult add(@RequestBody PjProjectEntity pjProject){
        pjProjectService.save(pjProject);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("pjProject/delete")
    //@RequiresPermissions("pjProject:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        pjProjectService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("pjProject/update")
    //@RequiresPermissions("pjProject:update")
    @ResponseBody
    public DataResult update(@RequestBody PjProjectEntity pjProject){
        pjProjectService.updateById(pjProject);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("pjProject/listByPage")
    //@RequiresPermissions("pjProject:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody PjProjectEntity pjProject){
        Page page = new Page(pjProject.getPage(), pjProject.getLimit());
        LambdaQueryWrapper<PjProjectEntity> queryWrapper = Wrappers.lambdaQuery();
    	queryWrapper.like(PjProjectEntity::getRefProjectManager, pjProject.getRefProjectManager()==null?"":pjProject.getRefProjectManager());
    	queryWrapper.like(PjProjectEntity::getProjectName, pjProject.getProjectName()==null?"":pjProject.getProjectName());
    	queryWrapper.like(PjProjectEntity::getRefCusname, pjProject.getRefCusname()==null?"":pjProject.getRefCusname());
        //查询条件示例
    	//数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(pjProject.getCreateIds())) {
            queryWrapper.in(PjProjectEntity::getCreateId, pjProject.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
        //queryWrapper.eq(PjProjectEntity::getId, pjProject.getId());
        IPage<PjProjectEntity> iPage = pjProjectService.page(page, queryWrapper);
        List<PjProjectEntity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		/*records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null) && (item.getOrderState()!=null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						new Thread(new Runnable() {
							@Override
							public void run() {
								pjProjectService.updateById(item);
							}
						}).start();
					}
				}
			}else {
				item.setOrderState(item.getOrderStatus());
			}
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});*/
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("pjProject/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody PjProjectEntity pjProject){
    	LambdaQueryWrapper<PjProjectEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//数据权限示例， 需手动添加此条件 begin
        if (!CollectionUtils.isEmpty(pjProject.getCreateIds())) {
            queryWrapper.in(PjProjectEntity::getCreateId, pjProject.getCreateIds());
        }
        //数据权限示例， 需手动添加此条件 end
    	//queryWrapper.eq(PjProjectEntity::getId, pjProject.getId());
    	//PjProjectEntity one = pjProjectService.getOne(queryWrapper);
    	PjProjectEntity one = pjProjectService.getById(pjProject.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("pjProject/listBySelect")
    @ResponseBody
    @DataScope
    public DataResult findListBySelect(@RequestBody PjProjectEntity pjProject){
        Page page = new Page(pjProject.getPage(), pjProject.getLimit());
        LambdaQueryWrapper<PjProjectEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(PjProjectEntity::getOrderStatus, 0);
        IPage<PjProjectEntity> iPage = pjProjectService.page(page, queryWrapper);
        log.info("\n this.pjProjectMapper.selectCountUser()="+this.pjProjectMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
