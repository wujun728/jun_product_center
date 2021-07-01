package com.ruoyi.project.garbagesort.keywordResult.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.garbagesort.keywordResult.domain.KeywordResult;
import com.ruoyi.project.garbagesort.keywordResult.service.IKeywordResultService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 关键词+结果记录Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/keywordResult")
public class KeywordResultController extends BaseController
{
    private String prefix = "garbagesort/keywordResult";

    @Autowired
    private IKeywordResultService keywordResultService;

    @RequiresPermissions("garbagesort:keywordResult:view")
    @GetMapping()
    public String keywordResult()
    {
        return prefix + "/keywordResult";
    }

    /**
     * 查询关键词+结果记录列表
     */
    @RequiresPermissions("garbagesort:keywordResult:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KeywordResult keywordResult)
    {
        startPage();
        List<KeywordResult> list = keywordResultService.selectKeywordResultList(keywordResult);
        return getDataTable(list);
    }

    /**
     * 导出关键词+结果记录列表
     */
    @RequiresPermissions("garbagesort:keywordResult:export")
    @Log(title = "关键词+结果记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KeywordResult keywordResult)
    {
        List<KeywordResult> list = keywordResultService.selectKeywordResultList(keywordResult);
        ExcelUtil<KeywordResult> util = new ExcelUtil<KeywordResult>(KeywordResult.class);
        return util.exportExcel(list, "关键词+结果记录数据");
    }

    /**
     * 新增关键词+结果记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存关键词+结果记录
     */
    @RequiresPermissions("garbagesort:keywordResult:add")
    @Log(title = "关键词+结果记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KeywordResult keywordResult)
    {
        return toAjax(keywordResultService.insertKeywordResult(keywordResult));
    }

    /**
     * 修改关键词+结果记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KeywordResult keywordResult = keywordResultService.selectKeywordResultById(id);
        mmap.put("keywordResult", keywordResult);
        return prefix + "/edit";
    }

    /**
     * 修改保存关键词+结果记录
     */
    @RequiresPermissions("garbagesort:keywordResult:edit")
    @Log(title = "关键词+结果记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KeywordResult keywordResult)
    {
        return toAjax(keywordResultService.updateKeywordResult(keywordResult));
    }

    /**
     * 删除关键词+结果记录
     */
    @RequiresPermissions("garbagesort:keywordResult:remove")
    @Log(title = "关键词+结果记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(keywordResultService.deleteKeywordResultByIds(ids));
    }
}
