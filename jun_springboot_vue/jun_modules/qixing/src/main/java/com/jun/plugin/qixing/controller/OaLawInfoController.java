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

import javax.annotation.Resource;

//import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;

import com.jun.plugin.qixing.entity.OaLawInfoEntity;
import com.jun.plugin.qixing.mapper.BizCommonMapper;
import com.jun.plugin.qixing.mapper.OaLawInfoMapper;
import com.jun.plugin.qixing.service.OaLawInfoService;



/**
 * 政策法规
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-08 15:07:34
 */
@Controller
@RequestMapping("/")
@Slf4j
public class OaLawInfoController {

    @Autowired
    private OaLawInfoService oaLawInfoService;

    @Autowired
    private OaLawInfoMapper oaLawInfoMapper;
    
    @Autowired
    private BizCommonMapper bizCommonMapper;

    //@Resource
   // HttpSessionService sessionService;
    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaLawInfo")
    public String oaLawInfo() {
        return "oalawinfo/list";
    }
    
    @GetMapping("/index/oaSysInfo")
    public String oaSysInfo() {
    	return "oalawinfo/list-left-right";
    }

    @ApiOperation(value = "新增")
    @PostMapping("oaLawInfo/add")
    //@RequiresPermissions("oaLawInfo:add")
    @ResponseBody
    public DataResult add(@RequestBody OaLawInfoEntity oaLawInfo){
        oaLawInfoService.save(oaLawInfo);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaLawInfo/delete")
    //@RequiresPermissions("oaLawInfo:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaLawInfoService.removeByIds(ids);
        return DataResult.success();
    }
    

    @ApiOperation(value = "发布")
    @DeleteMapping("oaLawInfo/publish")
    @ResponseBody
    public DataResult publish(@RequestBody @ApiParam(value = "id集合") List<String> ids){
    	ids.forEach(item->{
    		bizCommonMapper.updateRecordByColumnValue("oa_law_info", "dict_is_draft", "1", item);
    	});
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaLawInfo/update")
    //@RequiresPermissions("oaLawInfo:update")
    @ResponseBody
    public DataResult update(@RequestBody OaLawInfoEntity oaLawInfo){
        oaLawInfoService.updateById(oaLawInfo);
        return DataResult.success();
    }
    
    
    
    
    
    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaLawInfo/listByLeftPage")
    @ResponseBody
//    @DataScope
    public DataResult findlistByLeftPage(@RequestBody OaLawInfoEntity oaLawInfo){
        Page page = new Page(oaLawInfo.getPage(), oaLawInfo.getLimit());
        LambdaQueryWrapper<OaLawInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
//        queryWrapper.like(OaLawInfoEntity::getTitle, oaLawInfo.getTitle()==null?"":oaLawInfo.getTitle());
//        queryWrapper.like(OaLawInfoEntity::getContent, oaLawInfo.getContent()==null?"":oaLawInfo.getContent());
//        queryWrapper.like(OaLawInfoEntity::getDictMsgType, oaLawInfo.getDictMsgType()==null?"":oaLawInfo.getDictMsgType());
//        queryWrapper.like(OaLawInfoEntity::getDictIsDraft, oaLawInfo.getDictIsDraft()==null?"":oaLawInfo.getDictIsDraft());
        queryWrapper.isNull(OaLawInfoEntity::getPid);
        IPage<OaLawInfoEntity> iPage = oaLawInfoService.page(page, queryWrapper);
        List<OaLawInfoEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }
    
    
    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaLawInfo/listByRightPage")
    @ResponseBody
//    @DataScope
    public DataResult findlistByRightPage(@RequestBody OaLawInfoEntity oaLawInfo){
        Page page = new Page(oaLawInfo.getPage(), oaLawInfo.getLimit());
        LambdaQueryWrapper<OaLawInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
//        queryWrapper.like(OaLawInfoEntity::getTitle, oaLawInfo.getTitle()==null?"":oaLawInfo.getTitle());
//        queryWrapper.like(OaLawInfoEntity::getContent, oaLawInfo.getContent()==null?"":oaLawInfo.getContent());
//        queryWrapper.like(OaLawInfoEntity::getDictMsgType, oaLawInfo.getDictMsgType()==null?"":oaLawInfo.getDictMsgType());
//        queryWrapper.like(OaLawInfoEntity::getDictIsDraft, oaLawInfo.getDictIsDraft()==null?"":oaLawInfo.getDictIsDraft());
        queryWrapper.eq(OaLawInfoEntity::getPid, oaLawInfo.getPid()==null?"":oaLawInfo.getPid());
        queryWrapper.isNotNull(OaLawInfoEntity::getPid);
        //IPage<OaLawInfoEntity> iPage = oaLawInfoService.page(page, queryWrapper);
        List<OaLawInfoEntity> records = bizCommonMapper.queryOaLawInfoEntityTreeBypid(oaLawInfo.getPid());
        //List<OaLawInfoEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(records);
    }
    
    
    
    
    

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaLawInfo/listByPage")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaLawInfoEntity oaLawInfo){
        Page page = new Page(oaLawInfo.getPage(), oaLawInfo.getLimit());
        LambdaQueryWrapper<OaLawInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(OaLawInfoEntity::getTitle, oaLawInfo.getTitle()==null?"":oaLawInfo.getTitle());
        queryWrapper.like(OaLawInfoEntity::getContent, oaLawInfo.getContent()==null?"":oaLawInfo.getContent());
        queryWrapper.like(OaLawInfoEntity::getDictMsgType, oaLawInfo.getDictMsgType()==null?"":oaLawInfo.getDictMsgType());
        queryWrapper.like(OaLawInfoEntity::getDictIsDraft, oaLawInfo.getDictIsDraft()==null?"":oaLawInfo.getDictIsDraft());
        IPage<OaLawInfoEntity> iPage = oaLawInfoService.page(page, queryWrapper);
        List<OaLawInfoEntity> records = iPage.getRecords();
        String userid = "admin";// sessionService.getCurrentUserId();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }
    

    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("oaLawInfo/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaLawInfoEntity oaLawInfo){
        Page page = new Page(oaLawInfo.getPage(), oaLawInfo.getLimit());
        LambdaQueryWrapper<OaLawInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaLawInfoEntity::getId, oaLawInfo.getId());
        IPage<OaLawInfoEntity> iPage = oaLawInfoService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaLawInfo/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaLawInfoEntity oaLawInfo){
    	LambdaQueryWrapper<OaLawInfoEntity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaLawInfoEntity::getId, oaLawInfo.getId());
    	//OaLawInfoEntity one = oaLawInfoService.getOne(queryWrapper);
    	OaLawInfoEntity one = oaLawInfoService.getById(oaLawInfo.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaLawInfo/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaLawInfoEntity oaLawInfo){
        Page page = new Page(oaLawInfo.getPage(), oaLawInfo.getLimit());
        LambdaQueryWrapper<OaLawInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaLawInfoEntity::getId, oaLawInfo.getId());
        IPage<OaLawInfoEntity> iPage = oaLawInfoService.page(page, queryWrapper);
        log.info("\n this.oaLawInfoMapper.selectCountUser()="+this.oaLawInfoMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
