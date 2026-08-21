package com.ruoyi.workfile.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.workfile.domain.WorkflowAttachment;
import com.ruoyi.workfile.module.BizAttachmentDTO;
import com.ruoyi.workfile.service.IWorkflowAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程业务附件Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workfile/attachment")
public class WorkflowAttachmentController extends BaseController {
    @Autowired
    private IWorkflowAttachmentService workflowAttachmentService;

    /**
     * 查询流程业务附件列表
     */
    @GetMapping("/list/{businessId}")
    public AjaxResult list(@PathVariable("businessId") String businessId) {
        List<BizAttachmentDTO> list = workflowAttachmentService.listAttachment(businessId);
        return success(list);
    }

    /**
     * 新增流程业务附件
     */
    @Log(title = "流程业务附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkflowAttachment workflowAttachment) {
        return toAjax(workflowAttachmentService.saveWorkflowAttachment(workflowAttachment));
    }

    /**
     * 删除流程业务附件
     */
    @Log(title = "流程业务附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileId}")
    public AjaxResult remove(@PathVariable String fileId) {
        return toAjax(workflowAttachmentService.remove(fileId));
    }
}
