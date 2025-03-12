package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

import javax.annotation.Resource;

//import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;

import com.jun.plugin.qixing.entity.OaPomsWorkmarksLeaveEntity;
import com.jun.plugin.qixing.mapper.OaPomsWorkmarksLeaveMapper;
import com.jun.plugin.qixing.service.OaPomsWorkmarksLeaveService;
import com.jun.plugin.snakerflow.process.BaseFlowController;



/**
 * 员工请假
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:32
 */
@Controller
@RequestMapping("/")
@Slf4j
public class OaPomsWorkmarksLeaveController extends BaseFlowController /* flow_test1 */ {

    @Autowired
    private OaPomsWorkmarksLeaveService oaPomsWorkmarksLeaveService;

    @Autowired
    private OaPomsWorkmarksLeaveMapper oaPomsWorkmarksLeaveMapper;
    

    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaPomsWorkmarksLeave")
    public String oaPomsWorkmarksLeave() {
        return "oapomsworkmarksleave/list";
    }
    /**
     * 跳转到页面
     * http://qixing.vip321.vip/index/oaPomsWorkmarksLeave/view/1461527612906463233
     */
    @GetMapping("/index/oaPomsWorkmarksLeave/view/{id}")
    public String oaPomsWorkmarksLeaveView(Model model,@PathVariable String id) {
    	model.addAttribute("flagType", "view");
    	model.addAttribute("id", id);
    	return "oapomsworkmarksleave/list";
    }

    @ApiOperation(value = "新增")
    @PostMapping("oaPomsWorkmarksLeave/add")
    //@RequiresPermissions("oaPomsWorkmarksLeave:add")
    @ResponseBody
    public DataResult add(@RequestBody OaPomsWorkmarksLeaveEntity oaPomsWorkmarksLeave){
        oaPomsWorkmarksLeaveService.save(oaPomsWorkmarksLeave);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaPomsWorkmarksLeave/delete")
    //@RequiresPermissions("oaPomsWorkmarksLeave:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaPomsWorkmarksLeaveService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaPomsWorkmarksLeave/update")
    //@RequiresPermissions("oaPomsWorkmarksLeave:update")
    @ResponseBody
    public DataResult update(@RequestBody OaPomsWorkmarksLeaveEntity oaPomsWorkmarksLeave){
        oaPomsWorkmarksLeaveService.updateById(oaPomsWorkmarksLeave);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaPomsWorkmarksLeave/listByPage")
    //@RequiresPermissions("oaPomsWorkmarksLeave:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaPomsWorkmarksLeaveEntity oaPomsWorkmarksLeave){
        Page page = new Page(oaPomsWorkmarksLeave.getPage(), oaPomsWorkmarksLeave.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksLeaveEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(OaPomsWorkmarksLeaveEntity::getRefUsercoce, oaPomsWorkmarksLeave.getRefUsercoce()==null?"":oaPomsWorkmarksLeave.getRefUsercoce());
        queryWrapper.like(OaPomsWorkmarksLeaveEntity::getRefUsername, oaPomsWorkmarksLeave.getRefUsername()==null?"":oaPomsWorkmarksLeave.getRefUsername());
        IPage<OaPomsWorkmarksLeaveEntity> iPage = oaPomsWorkmarksLeaveService.page(page, queryWrapper);
        //查询流程数据，当前处理人、流程状态、流程节点  flow_test1
        List<OaPomsWorkmarksLeaveEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if((item.getOrderId()!=null && item.getOrderId().length()>0) && (item.getOrderStatus()==null)) {
				this.setFlowStatusInfo(item);
				System.err.println(item.getOrderStatus());
				if(item.getOrderState()==0) {
					if((item.getOrderStatus()==null || item.getOrderStatus()!=0)) {
						item.setOrderStatus(item.getOrderState());
						oaPomsWorkmarksLeaveService.updateById(item);
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
    @PostMapping("oaPomsWorkmarksLeave/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaPomsWorkmarksLeaveEntity oaPomsWorkmarksLeave){
        Page page = new Page(oaPomsWorkmarksLeave.getPage(), oaPomsWorkmarksLeave.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksLeaveEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksLeaveEntity::getId, oaPomsWorkmarksLeave.getId());
        IPage<OaPomsWorkmarksLeaveEntity> iPage = oaPomsWorkmarksLeaveService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaPomsWorkmarksLeave/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaPomsWorkmarksLeaveEntity oaPomsWorkmarksLeave){
    	LambdaQueryWrapper<OaPomsWorkmarksLeaveEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaPomsWorkmarksLeaveEntity::getId, oaPomsWorkmarksLeave.getId());
    	//OaPomsWorkmarksLeaveEntity one = oaPomsWorkmarksLeaveService.getOne(queryWrapper);
    	OaPomsWorkmarksLeaveEntity one = oaPomsWorkmarksLeaveService.getById(oaPomsWorkmarksLeave.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaPomsWorkmarksLeave/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaPomsWorkmarksLeaveEntity oaPomsWorkmarksLeave){
        Page page = new Page(oaPomsWorkmarksLeave.getPage(), oaPomsWorkmarksLeave.getLimit());
        LambdaQueryWrapper<OaPomsWorkmarksLeaveEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaPomsWorkmarksLeaveEntity::getId, oaPomsWorkmarksLeave.getId());
        IPage<OaPomsWorkmarksLeaveEntity> iPage = oaPomsWorkmarksLeaveService.page(page, queryWrapper);
        log.info("\n this.oaPomsWorkmarksLeaveMapper.selectCountUser()="+this.oaPomsWorkmarksLeaveMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
