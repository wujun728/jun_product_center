package com.ruoyi.project.garbagesort.keywordSearchNum.controller;

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
import com.ruoyi.project.garbagesort.keywordSearchNum.domain.KeywordSearchNum;
import com.ruoyi.project.garbagesort.keywordSearchNum.service.IKeywordSearchNumService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 搜索关键词+次数记录Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/keywordSearchNum")
public class KeywordSearchNumController extends BaseController
{
    private String prefix = "garbagesort/keywordSearchNum";

    @Autowired
    private IKeywordSearchNumService keywordSearchNumService;

    @RequiresPermissions("garbagesort:keywordSearchNum:view")
    @GetMapping()
    public String keywordSearchNum()
    {
        return prefix + "/keywordSearchNum";
    }

    /**
     * 查询搜索关键词+次数记录列表
     */
    @RequiresPermissions("garbagesort:keywordSearchNum:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KeywordSearchNum keywordSearchNum)
    {
        startPage();
        List<KeywordSearchNum> list = keywordSearchNumService.selectKeywordSearchNumList(keywordSearchNum);
        return getDataTable(list);
    }

    /**
     * 导出搜索关键词+次数记录列表
     */
    @RequiresPermissions("garbagesort:keywordSearchNum:export")
    @Log(title = "搜索关键词+次数记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KeywordSearchNum keywordSearchNum)
    {
        List<KeywordSearchNum> list = keywordSearchNumService.selectKeywordSearchNumList(keywordSearchNum);
        ExcelUtil<KeywordSearchNum> util = new ExcelUtil<KeywordSearchNum>(KeywordSearchNum.class);
        return util.exportExcel(list, "搜索关键词+次数记录数据");
    }

    /**
     * 新增搜索关键词+次数记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存搜索关键词+次数记录
     */
    @RequiresPermissions("garbagesort:keywordSearchNum:add")
    @Log(title = "搜索关键词+次数记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KeywordSearchNum keywordSearchNum)
    {
        return toAjax(keywordSearchNumService.insertKeywordSearchNum(keywordSearchNum));
    }

    /**
     * 修改搜索关键词+次数记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KeywordSearchNum keywordSearchNum = keywordSearchNumService.selectKeywordSearchNumById(id);
        mmap.put("keywordSearchNum", keywordSearchNum);
        return prefix + "/edit";
    }

    /**
     * 修改保存搜索关键词+次数记录
     */
    @RequiresPermissions("garbagesort:keywordSearchNum:edit")
    @Log(title = "搜索关键词+次数记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KeywordSearchNum keywordSearchNum)
    {
        return toAjax(keywordSearchNumService.updateKeywordSearchNum(keywordSearchNum));
    }

    /**
     * 删除搜索关键词+次数记录
     */
    @RequiresPermissions("garbagesort:keywordSearchNum:remove")
    @Log(title = "搜索关键词+次数记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(keywordSearchNumService.deleteKeywordSearchNumByIds(ids));
    }
}
