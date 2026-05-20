package com.ruoyi.worksetting.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.worksetting.domain.WorkflowEntrust;
import com.ruoyi.worksetting.domain.WorkflowSecretary;
import com.ruoyi.worksetting.domain.vo.WorkflowSecretaryVO;
import com.ruoyi.worksetting.service.IWorkflowSecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程办理秘书Controller
 *
 * @author wocurr.com
 */
@RestController
@RequestMapping("/worksetting/secretary")
public class WorkflowSecretaryController extends BaseController {
    @Autowired
    private IWorkflowSecretaryService workflowSecretaryService;

    /**
     * 查询流程办理秘书列表
     */
    @PreAuthorize("@ss.hasPermi('worksetting:secretary:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkflowSecretary workflowSecretary) {
        startPage();
        List<WorkflowSecretary> list = workflowSecretaryService.listWorkflowSecretary(workflowSecretary);
        return getDataTable(list);
    }

    /**
     * 获取流程办理秘书详细信息
     */
    @PreAuthorize("@ss.hasPermi('worksetting:secretary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workflowSecretaryService.getWorkflowSecretaryVOById(id));
    }

    /**
     * 新增流程办理秘书
     */
    @PreAuthorize("@ss.hasPermi('worksetting:secretary:add')")
    @Log(title = "流程办理秘书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkflowSecretaryVO workflowSecretary) {
        return toAjax(workflowSecretaryService.saveWorkflowSecretary(workflowSecretary));
    }

    /**
     * 修改流程办理秘书
     */
    @PreAuthorize("@ss.hasPermi('worksetting:secretary:edit')")
    @Log(title = "流程办理秘书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkflowSecretaryVO workflowSecretary) {
        return toAjax(workflowSecretaryService.updateWorkflowSecretary(workflowSecretary));
    }

    /**
     * 改变启用禁用状态
     * @param param
     * @return
     */
    @PutMapping("/changeEnableFlag")
    public AjaxResult changeEnableFlag(@RequestBody WorkflowSecretary param) {
        return toAjax(workflowSecretaryService.changeEnableFlag(param));
    }

    /**
     * 删除流程办理秘书
     */
    @PreAuthorize("@ss.hasPermi('worksetting:secretary:remove')")
    @Log(title = "流程办理秘书", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workflowSecretaryService.deleteWorkflowSecretaryByIds(ids));
    }
}
