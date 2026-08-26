package com.ruoyi.workflow.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.workflow.domain.WorkflowMyDraft;
import com.ruoyi.workflow.service.IWorkflowMyDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 我起草的流程表Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workflow/draft")
public class WorkflowMyDraftController extends BaseController {
    @Autowired
    private IWorkflowMyDraftService workflowMyDraftService;

    /**
     * 查询我起草的流程表列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:draft:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkflowMyDraft workflowMyDraft) {
        startPage();
        List<WorkflowMyDraft> list = workflowMyDraftService.listWorkflowMyDraft(workflowMyDraft);
        return getDataTable(list);
    }

    /**
     * 统计我起草的流程表总数
     *
     * @return
     */
    @GetMapping("/count")
    public AjaxResult staticTodo() {
        return success(workflowMyDraftService.count());
    }
}
