package com.jun.plugin.system.controller;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.jun.plugin.common.Result;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.jun.plugin.common.service.RedisService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.system.entity.SysDictDetailEntity;
import com.jun.plugin.system.entity.SysDictEntity;
import com.jun.plugin.system.service.SysDictDetailService;
import com.jun.plugin.system.service.SysDictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * 字典管理
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping("/sysDict")
public class SysDictController {
    @Resource
    private SysDictService sysDictService;
    @Resource
    private SysDictDetailService sysDictDetailService;
    @Resource
    private RedisService redisService;


    @ApiOperation(value = "新增")
    @PostMapping("/add")
    //@RequiresPermissions("sysDict:add")
    public Result add(@RequestBody SysDictEntity sysDict) {
        if (StringUtils.isEmpty(sysDict.getName())) {
            return Result.fail("字典名称不能为空");
        }
        SysDictEntity q = sysDictService.getOne(Wrappers.<SysDictEntity>lambdaQuery().eq(SysDictEntity::getName, sysDict.getName()));
        if (q != null) {
            return Result.fail("字典名称已存在");
        }
        sysDictService.save(sysDict);
        return Result.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    //@RequiresPermissions("sysDict:delete")
    public Result delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
        sysDictService.removeByIds(ids);
        //删除detail
        sysDictDetailService.remove(Wrappers.<SysDictDetailEntity>lambdaQuery().in(SysDictDetailEntity::getDictId, ids));
        return Result.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("/update")
    //@RequiresPermissions("sysDict:update")
    public Result update(@RequestBody SysDictEntity sysDict) {
        if (StringUtils.isEmpty(sysDict.getName())) {
            return Result.fail("字典名称不能为空");
        }

        SysDictEntity q = sysDictService.getOne(Wrappers.<SysDictEntity>lambdaQuery().eq(SysDictEntity::getName, sysDict.getName()));
        if (q != null && !q.getId().equals(sysDict.getId())) {
            return Result.fail("字典名称已存在");
        }

        sysDictService.updateById(sysDict);
        return Result.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    //@RequiresPermissions("sysDict:list")
//    @CacheEvict(value = "valueName",allEntries = true)
//    @CacheEvict(cacheNames = "com.jun.plugin.business.service.impl.ProviderServiceImpl", key = "#id")
//    @Cacheable(cacheNames = "SysDictController.findListByPage", key = "#sysDict")
    public Result findListByPage(@RequestBody SysDictEntity sysDict) {
        Page page = new Page(sysDict.getPage(), sysDict.getLimit());
        LambdaQueryWrapper<SysDictEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!StringUtils.isEmpty(sysDict.getName())) {
            queryWrapper.like(SysDictEntity::getName, sysDict.getName());
            queryWrapper.or();
            queryWrapper.like(SysDictEntity::getRemark, sysDict.getName());
        }
        queryWrapper.orderByAsc(SysDictEntity::getName);
        IPage<SysDictEntity> iPage = sysDictService.page(page, queryWrapper);
        return Result.success(iPage);
    }
    
    @ApiOperation(value = "查询字典明细数据")
    @RequestMapping("/getType/{name}")
    public JSONArray getType(@PathVariable String name) {
        if(!redisService.exists(name)){
            JSONArray json = sysDictService.getType(name);
            redisService.setAndExpire(name,json.toJSONString(JSONWriter.Feature.WriteMapNullValue),3600);
            return json;
        }else{
            String jsonStr = redisService.get(name);
            return JSONArray.parseArray(jsonStr, JSONReader.Feature.SupportAutoType);
        }
    }
    
    

}
