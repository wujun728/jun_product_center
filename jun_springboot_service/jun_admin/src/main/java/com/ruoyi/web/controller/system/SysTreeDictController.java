package com.ruoyi.web.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysTreeDict;
import com.ruoyi.system.service.ISysTreeDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统树形Controller
 *
 * @author wangzongrun
 * @date 2021-06-04
 */
@RestController
@RequestMapping("system/dict/tree")
public class SysTreeDictController extends BaseController {

    @Autowired
    private ISysTreeDictService SysTreeDictService;

    /**
     * 分页查询系统树形列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysTreeDict sysTreeDict) {
        IPage<SysTreeDict> list = SysTreeDictService.selectList(getPage(), sysTreeDict);
        return getDataTable(list);
    }

    /**
     * 查询字典类型详细
     */
    @GetMapping(value = "/{dictId}")
    public AjaxResult getInfo(@PathVariable String dictId) {
        return success(SysTreeDictService.selectById(dictId));
    }

    /**
     * 导出系统树形列表
     */
    @Log(title = "系统树形", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysTreeDict sysTreeDict) {
        List<SysTreeDict> list = SysTreeDictService.selectListAll(sysTreeDict);
        ExcelUtil<SysTreeDict> util = new ExcelUtil<SysTreeDict>(SysTreeDict.class);
        return util.exportExcel(list, "系统树形");
    }

    /**
     * 新增系统树形
     */
    @Log(title = "系统树形", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTreeDict sysTreeDict) {
        return toAjax(SysTreeDictService.insert(sysTreeDict));
    }

    /**
     * 修改系统树形
     */
    @Log(title = "系统树形", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTreeDict sysTreeDict) {
        return toAjax(SysTreeDictService.update(sysTreeDict));
    }

    /**
     * 删除系统树形
     */
    @Log(title = "系统树形", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable String[] sids) {
        return toAjax(SysTreeDictService.deleteByIds(sids));
    }
}
