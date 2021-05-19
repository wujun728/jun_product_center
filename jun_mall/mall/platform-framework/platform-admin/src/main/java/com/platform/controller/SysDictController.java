package com.platform.controller;

import com.platform.annotation.SysLog;
import com.platform.cache.CacheUtil;
import com.platform.entity.SysDictEntity;
import com.platform.service.SysDictService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 系统数据字典Controller
 *
 * @author lipengjun
 * @date 2017-12-25 18:26:15
 */
@Controller
@RequestMapping("/sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService dictService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<SysDictEntity> dictList = dictService.queryList(query);
        int total = dictService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(dictList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        SysDictEntity dict = dictService.queryObject(id);

        return R.ok().put("dict", dict);
    }

    /**
     * 保存
     */
    @SysLog("新增字典")
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    @ResponseBody
    public R save(@RequestBody SysDictEntity dict) {
        dictService.save(dict);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改字典")
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    @ResponseBody
    public R update(@RequestBody SysDictEntity dict) {
        dictService.update(dict);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除字典")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        dictService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<SysDictEntity> list = dictService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看
     */
    @RequestMapping("/getDictValue")
    @ResponseBody
    public R getDictValue(@RequestParam Map<String, String> params) {

        String groupCode = params.get("groupCode");
        String dictKey = params.get("dictKey");
        String dictValue = CacheUtil.getDictValue(groupCode, dictKey);

        return R.ok().put("dictValue", dictValue);
    }
}
