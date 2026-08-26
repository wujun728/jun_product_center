package com.ruoyi.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.workflow.domain.WorkflowMainSeal;
import com.ruoyi.workflow.module.WorkflowSealResult;
import com.ruoyi.workflow.service.IWorkflowMainSealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 正文印章Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workflow/seal")
public class WorkflowMainSealController extends BaseController {
    @Autowired
    private IWorkflowMainSealService workflowMainSealService;

    /**
     * 查询正文印章列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:seal:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkflowMainSeal workflowMainSeal) {
        startPage();
        List<WorkflowMainSeal> list = workflowMainSealService.listWorkflowMainSeal(workflowMainSeal);
        return getDataTable(list);
    }

    /**
     * 获取所有有效印章
     * @return
     */
    @GetMapping("/findAllSeals")
    public AjaxResult findAllSeals() {
        List<WorkflowSealResult> list = workflowMainSealService.findAllSeals();
        return success(list);
    }

    /**
     * 获取正文印章详细信息
     */
    @PreAuthorize("@ss.hasPermi('workflow:seal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workflowMainSealService.getWorkflowDetail(id));
    }

    /**
     * 修改正文印章
     */
    @PreAuthorize("@ss.hasPermi('workflow:seal:edit')")
    @Log(title = "正文印章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkflowMainSeal workflowMainSeal) {
        return toAjax(workflowMainSealService.updateWorkflowMainSeal(workflowMainSeal));
    }

    /**
     * 删除正文印章
     */
    @PreAuthorize("@ss.hasPermi('workflow:seal:remove')")
    @Log(title = "正文印章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workflowMainSealService.deleteWorkflowMainSealByIds(ids));
    }

    /**
     * 启用禁用
     * @param mainSeal
     * @return
     */
    @PutMapping("/changeEnableFlag")
    public AjaxResult changeEnableFlag(@RequestBody WorkflowMainSeal mainSeal) {
        return toAjax(workflowMainSealService.changeEnableFlag(mainSeal));
    }

    /**
     * 预览印章
     * @param mainSeal
     * @return
     */
    @PostMapping(value = "/previewMainSeal")
    public AjaxResult previewSeal(@RequestBody WorkflowMainSeal mainSeal) {
        return AjaxResult.success(workflowMainSealService.previewMainSeal(mainSeal));
    }

    /**
     * 创建印章
     * @param mainSeal
     * @return
     */
    @PreAuthorize("@ss.hasPermi('workflow:seal:add')")
    @PostMapping(value = "createMainSeal")
    public AjaxResult createEsignSeal(@RequestBody WorkflowMainSeal mainSeal) {
        return toAjax(workflowMainSealService.createMainSeal(mainSeal));
    }
}
