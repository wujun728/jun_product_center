package com.ruoyi.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.workflow.domain.ReceTemplate;
import com.ruoyi.workflow.service.IReceTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 最新使用模板Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workflow/receTemplate")
public class ReceTemplateController extends BaseController {
    @Autowired
    private IReceTemplateService receTemplateService;

    /**
     * 新增最新使用模板
     */
    @PreAuthorize("@ss.hasPermi('workflow:receTemplate:add')")
    @Log(title = "最新使用模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReceTemplate receTemplate) {
        return toAjax(receTemplateService.saveReceTemplate(receTemplate));
    }

    /**
     * 查询最新使用模板列表
     */
    @GetMapping("/list")
    public AjaxResult getReceTemplateList() {
        return success(receTemplateService.listReceTemplate());
    }
}
