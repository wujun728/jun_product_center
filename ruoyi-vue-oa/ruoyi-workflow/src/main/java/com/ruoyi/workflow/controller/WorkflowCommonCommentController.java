package com.ruoyi.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.workflow.domain.WorkflowCommonComment;
import com.ruoyi.workflow.service.IWorkflowCommonCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程常用意见Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workflow/comment")
public class WorkflowCommonCommentController extends BaseController {
    @Autowired
    private IWorkflowCommonCommentService workflowCommonCommentService;

    /**
     * 查询流程常用意见列表
     */
    @GetMapping("/list")
    public TableDataInfo list(WorkflowCommonComment workflowCommonComment) {
        startPage();
        List<WorkflowCommonComment> list = workflowCommonCommentService.listWorkflowCommonComment(workflowCommonComment);
        return getDataTable(list);
    }

    /**
     * 新增流程常用意见
     */
    @Log(title = "流程常用意见", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkflowCommonComment workflowCommonComment) {
        return AjaxResult.success("操作成功", workflowCommonCommentService.saveWorkflowCommonComment(workflowCommonComment));
    }

    /**
     * 修改流程常用意见
     */
    @Log(title = "流程常用意见", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkflowCommonComment workflowCommonComment) {
        return toAjax(workflowCommonCommentService.updateWorkflowCommonComment(workflowCommonComment));
    }

    /**
     * 删除流程常用意见
     */
    @Log(title = "流程常用意见", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workflowCommonCommentService.deleteWorkflowCommonCommentByIds(ids));
    }
}
