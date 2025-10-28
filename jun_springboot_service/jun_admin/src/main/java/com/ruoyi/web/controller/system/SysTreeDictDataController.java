package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysTreeDictData;
import com.ruoyi.system.service.ISysTreeDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 树形字典数据Controller
 *
 * @author wangzongrun
 * @date 2021-05-31
 */
@RestController
@RequestMapping("system/tree/dict/data")
public class SysTreeDictDataController extends BaseController {

    @Autowired
    private ISysTreeDictDataService sysTreeDictDataService;

    /**
     * 分页查询树形字典数据列表
     */
    @GetMapping("list")
    public AjaxResult list(SysTreeDictData sysTreeDictData) {
        List<Object> list = sysTreeDictDataService.buildTree(sysTreeDictData);
        return success(list);
    }

    /**
     * 查询树形字典数据列表
     */
    @GetMapping("/tree")
    public AjaxResult tree(SysTreeDictData sysTreeDictData) {
        List<Object> list = sysTreeDictDataService.buildTree(sysTreeDictData);
        return success(list);
    }

    /**
     * 新增树形字典数据
     */
    @Log(title = "树形字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTreeDictData sysTreeDictData) {
        return toAjax(sysTreeDictDataService.insert(sysTreeDictData));
    }

    /**
     * 修改树形字典数据
     */
    @Log(title = "树形字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTreeDictData sysTreeDictData) {
        return toAjax(sysTreeDictDataService.update(sysTreeDictData));
    }

    /**
     * 删除树形字典数据
     */
    @Log(title = "树形字典数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(sysTreeDictDataService.deleteByIds(ids));
    }

    /**
     * 检查名称唯一性
     */
    @PostMapping(value = "/check/unique/label")
    public AjaxResult checkUniqueByLabel(@RequestBody SysTreeDictData sysTreeDictData) {
        return sysTreeDictDataService.checkUniqueByLabel(sysTreeDictData);
    }

    /**
     * 校验编码
     */
    @PostMapping(value = "/check/code")
    public AjaxResult checkCode(@RequestBody SysTreeDictData sysTreeDictData) {
        return sysTreeDictDataService.checkCode(sysTreeDictData);
    }
}
