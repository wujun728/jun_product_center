package com.ruoyi.project.garbagesort.questionBank.controller;

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
import com.ruoyi.project.garbagesort.questionBank.domain.QuestionBank;
import com.ruoyi.project.garbagesort.questionBank.service.IQuestionBankService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 题库Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/questionBank")
public class QuestionBankController extends BaseController
{
    private String prefix = "garbagesort/questionBank";

    @Autowired
    private IQuestionBankService questionBankService;

    @RequiresPermissions("garbagesort:questionBank:view")
    @GetMapping()
    public String questionBank()
    {
        return prefix + "/questionBank";
    }

    /**
     * 查询题库列表
     */
    @RequiresPermissions("garbagesort:questionBank:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QuestionBank questionBank)
    {
        startPage();
        List<QuestionBank> list = questionBankService.selectQuestionBankList(questionBank);
        return getDataTable(list);
    }

    /**
     * 导出题库列表
     */
    @RequiresPermissions("garbagesort:questionBank:export")
    @Log(title = "题库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QuestionBank questionBank)
    {
        List<QuestionBank> list = questionBankService.selectQuestionBankList(questionBank);
        ExcelUtil<QuestionBank> util = new ExcelUtil<QuestionBank>(QuestionBank.class);
        return util.exportExcel(list, "题库数据");
    }

    /**
     * 新增题库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存题库
     */
    @RequiresPermissions("garbagesort:questionBank:add")
    @Log(title = "题库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QuestionBank questionBank)
    {
        return toAjax(questionBankService.insertQuestionBank(questionBank));
    }

    /**
     * 修改题库
     */
    @GetMapping("/edit/{questionId}")
    public String edit(@PathVariable("questionId") Long questionId, ModelMap mmap)
    {
        QuestionBank questionBank = questionBankService.selectQuestionBankById(questionId);
        mmap.put("questionBank", questionBank);
        return prefix + "/edit";
    }

    /**
     * 修改保存题库
     */
    @RequiresPermissions("garbagesort:questionBank:edit")
    @Log(title = "题库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QuestionBank questionBank)
    {
        return toAjax(questionBankService.updateQuestionBank(questionBank));
    }

    /**
     * 删除题库
     */
    @RequiresPermissions("garbagesort:questionBank:remove")
    @Log(title = "题库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(questionBankService.deleteQuestionBankByIds(ids));
    }
}
