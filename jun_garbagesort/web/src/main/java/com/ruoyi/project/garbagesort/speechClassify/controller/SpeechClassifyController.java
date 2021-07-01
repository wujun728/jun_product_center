package com.ruoyi.project.garbagesort.speechClassify.controller;

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
import com.ruoyi.project.garbagesort.speechClassify.domain.SpeechClassify;
import com.ruoyi.project.garbagesort.speechClassify.service.ISpeechClassifyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 语音识别记录Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/speechClassify")
public class SpeechClassifyController extends BaseController
{
    private String prefix = "garbagesort/speechClassify";

    @Autowired
    private ISpeechClassifyService speechClassifyService;

    @RequiresPermissions("garbagesort:speechClassify:view")
    @GetMapping()
    public String speechClassify()
    {
        return prefix + "/speechClassify";
    }

    /**
     * 查询语音识别记录列表
     */
    @RequiresPermissions("garbagesort:speechClassify:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SpeechClassify speechClassify)
    {
        startPage();
        List<SpeechClassify> list = speechClassifyService.selectSpeechClassifyList(speechClassify);
        return getDataTable(list);
    }

    /**
     * 导出语音识别记录列表
     */
    @RequiresPermissions("garbagesort:speechClassify:export")
    @Log(title = "语音识别记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SpeechClassify speechClassify)
    {
        List<SpeechClassify> list = speechClassifyService.selectSpeechClassifyList(speechClassify);
        ExcelUtil<SpeechClassify> util = new ExcelUtil<SpeechClassify>(SpeechClassify.class);
        return util.exportExcel(list, "语音识别记录数据");
    }

    /**
     * 新增语音识别记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存语音识别记录
     */
    @RequiresPermissions("garbagesort:speechClassify:add")
    @Log(title = "语音识别记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SpeechClassify speechClassify)
    {
        return toAjax(speechClassifyService.insertSpeechClassify(speechClassify));
    }

    /**
     * 修改语音识别记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SpeechClassify speechClassify = speechClassifyService.selectSpeechClassifyById(id);
        mmap.put("speechClassify", speechClassify);
        return prefix + "/edit";
    }

    /**
     * 修改保存语音识别记录
     */
    @RequiresPermissions("garbagesort:speechClassify:edit")
    @Log(title = "语音识别记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SpeechClassify speechClassify)
    {
        return toAjax(speechClassifyService.updateSpeechClassify(speechClassify));
    }

    /**
     * 删除语音识别记录
     */
    @RequiresPermissions("garbagesort:speechClassify:remove")
    @Log(title = "语音识别记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(speechClassifyService.deleteSpeechClassifyByIds(ids));
    }
}
