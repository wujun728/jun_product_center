package com.jun.plugin.qixing.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jun.plugin.common.utils.DataResult;

import com.jun.plugin.qixing.entity.OaNotesInfoV2Entity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

//import com.jun.plugin.system.service.HttpSessionService;
import com.jun.plugin.common.aop.annotation.DataScope;
import com.jun.plugin.qixing.mapper.BizCommonMapper;
import com.jun.plugin.qixing.mapper.OaNotesInfoMapper;
import com.jun.plugin.qixing.service.OaNotesInfoService;



/**
 * 公告通知
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-08 15:07:34
 */
@Controller
@RequestMapping("/")
@Slf4j
@DataSource(DataSourceType.QIXING)
public class OaNotesInfoController {

    @Autowired
    private OaNotesInfoService oaNotesInfoService;

    @Autowired
    private OaNotesInfoMapper oaNotesInfoMapper;

    @Autowired
    private BizCommonMapper bizCommonMapper;

    //@Resource
   // HttpSessionService sessionService;

    /**
    * 跳转到页面
    */
    @GetMapping("/index/oaNotesInfo")
    public String oaNotesInfo() {
        return "oanotesinfo/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("oaNotesInfo/add")
    //@RequiresPermissions("oaNotesInfo:add")
    @ResponseBody
    public DataResult add(@RequestBody OaNotesInfoV2Entity oaNotesInfo){
        oaNotesInfoService.save(oaNotesInfo);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("oaNotesInfo/delete")
    //@RequiresPermissions("oaNotesInfo:delete")
    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        oaNotesInfoService.removeByIds(ids);
        return DataResult.success();
    }


    @ApiOperation(value = "发布")
    @DeleteMapping("oaNotesInfo/publish")
    @ResponseBody
    public DataResult publish(@RequestBody @ApiParam(value = "id集合") List<String> ids){
    	ids.forEach(item->{
    		bizCommonMapper.updateRecordByColumnValue("oa_notes_info", "dict_is_draft", "1", item);
    	});
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("oaNotesInfo/update")
    //@RequiresPermissions("oaNotesInfo:update")
    @ResponseBody
    public DataResult update(@RequestBody OaNotesInfoV2Entity oaNotesInfo){
        oaNotesInfoService.updateById(oaNotesInfo);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("oaNotesInfo/listByPage")
    //@RequiresPermissions("oaNotesInfo:list")
    @ResponseBody
    @DataScope
    public DataResult findListByPage(@RequestBody OaNotesInfoV2Entity oaNotesInfo){
        Page page = new Page(oaNotesInfo.getPage(), oaNotesInfo.getLimit());
        LambdaQueryWrapper<OaNotesInfoV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.like(OaNotesInfoV2Entity::getId, oaNotesInfo.getId()==null?"":oaNotesInfo.getId());
        queryWrapper.like(OaNotesInfoV2Entity::getTitle, oaNotesInfo.getTitle()==null?"":oaNotesInfo.getTitle());
        queryWrapper.like(OaNotesInfoV2Entity::getContent, oaNotesInfo.getContent()==null?"":oaNotesInfo.getContent());
        queryWrapper.like(OaNotesInfoV2Entity::getDictMsgType, oaNotesInfo.getDictMsgType()==null?"":oaNotesInfo.getDictMsgType());
        queryWrapper.like(OaNotesInfoV2Entity::getDictIsDraft, oaNotesInfo.getDictIsDraft()==null?"":oaNotesInfo.getDictIsDraft());
        IPage<OaNotesInfoV2Entity> iPage = oaNotesInfoService.page(page, queryWrapper);
        List<OaNotesInfoV2Entity> records = iPage.getRecords();
        String userid = SecurityUtils.getUserId().toString();
		records.forEach(item -> {
			if(userid.equalsIgnoreCase(item.getCreateId())) {
				item.setIsOwner(1);
			}
		});
        return DataResult.success(iPage);
    }


    @ApiOperation(value = "查询下拉列表数据")
    @PostMapping("oaNotesInfo/listBySelect")
    @ResponseBody
    public DataResult listBySelect(@RequestBody OaNotesInfoV2Entity oaNotesInfo){
        Page page = new Page(oaNotesInfo.getPage(), oaNotesInfo.getLimit());
        LambdaQueryWrapper<OaNotesInfoV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaNotesInfoEntity::getId, oaNotesInfo.getId());
        IPage<OaNotesInfoV2Entity> iPage = oaNotesInfoService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "查询单条数据")
    @PostMapping("oaNotesInfo/findOne")
    @ResponseBody
    public DataResult findOne(@RequestBody OaNotesInfoV2Entity oaNotesInfo){
    	LambdaQueryWrapper<OaNotesInfoV2Entity> queryWrapper = Wrappers.lambdaQuery();
    	//查询条件示例
    	//queryWrapper.eq(OaNotesInfoEntity::getId, oaNotesInfo.getId());
    	//OaNotesInfoEntity one = oaNotesInfoService.getOne(queryWrapper);
    	OaNotesInfoV2Entity one = oaNotesInfoService.getById(oaNotesInfo.getId());
    	return DataResult.success(one);
    }

    @ApiOperation(value = "查询下拉框数据")
    @PostMapping("oaNotesInfo/findListBySelect")
    @ResponseBody
    public DataResult findListBySelect(@RequestBody OaNotesInfoV2Entity oaNotesInfo){
        Page page = new Page(oaNotesInfo.getPage(), oaNotesInfo.getLimit());
        LambdaQueryWrapper<OaNotesInfoV2Entity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.like(OaNotesInfoEntity::getId, oaNotesInfo.getId());
        IPage<OaNotesInfoV2Entity> iPage = oaNotesInfoService.page(page, queryWrapper);
        log.info("\n this.oaNotesInfoMapper.selectCountUser()="+this.oaNotesInfoMapper.selectCountUser());
        return DataResult.success(iPage);
    }

}
