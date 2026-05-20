package com.ruoyi.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.workflow.domain.WorkflowRecycle;
import com.ruoyi.workflow.service.IWorkflowRecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回收站Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workflow/recycle")
public class WorkflowRecycleController extends BaseController {
    @Autowired
    private IWorkflowRecycleService workflowRecycleService;

    /**
     * 查询回收站列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:recycle:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkflowRecycle workflowRecycle) {
        startPage();
        List<WorkflowRecycle> list = workflowRecycleService.listWorkflowRecycle(workflowRecycle);
        return getDataTable(list);
    }

    /**
     * 获取回收站详细信息
     */
    @PreAuthorize("@ss.hasPermi('workflow:recycle:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workflowRecycleService.getWorkflowRecycleById(id));
    }

    /**
     * 删除回收站
     */
    @PreAuthorize("@ss.hasPermi('workflow:recycle:remove')")
    @Log(title = "回收站", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workflowRecycleService.deleteWorkflowRecycleByIds(ids));
    }
}
