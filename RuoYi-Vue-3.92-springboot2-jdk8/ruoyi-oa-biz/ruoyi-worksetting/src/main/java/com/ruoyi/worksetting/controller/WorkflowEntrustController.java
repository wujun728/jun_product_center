package com.ruoyi.worksetting.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.worksetting.domain.WorkflowEntrust;
import com.ruoyi.worksetting.domain.vo.WorkflowEntrustVO;
import com.ruoyi.worksetting.service.IWorkflowEntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程办理委托Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/worksetting/entrust")
public class WorkflowEntrustController extends BaseController {
    @Autowired
    private IWorkflowEntrustService workflowEntrustService;

    /**
     * 查询流程办理委托列表
     */
    @PreAuthorize("@ss.hasPermi('worksetting:entrust:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkflowEntrust queryParam) {
        startPage();
        List<WorkflowEntrustVO> list = workflowEntrustService.listWorkflowEntrust(queryParam);
        return getDataTable(list);
    }

    /**
     * 获取流程办理委托详细信息
     */
    @PreAuthorize("@ss.hasPermi('worksetting:entrust:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(workflowEntrustService.getWorkflowEntrustById(id));
    }

    /**
     * 新增流程办理委托
     */
    @PreAuthorize("@ss.hasPermi('worksetting:entrust:add')")
    @Log(title = "流程办理委托", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkflowEntrustVO workflowEntrustVo) {
        return toAjax(workflowEntrustService.saveWorkflowEntrust(workflowEntrustVo));
    }

    /**
     * 修改流程办理委托
     */
    @PreAuthorize("@ss.hasPermi('worksetting:entrust:edit')")
    @Log(title = "流程办理委托", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkflowEntrustVO workflowEntrustVo) {
        return toAjax(workflowEntrustService.updateWorkflowEntrust(workflowEntrustVo));
    }

    /**
     * 改变启用禁用状态
     * @param param
     * @return
     */
    @PutMapping("/changeEnableFlag")
    public AjaxResult changeEnableFlag(@RequestBody WorkflowEntrust param) {
        return toAjax(workflowEntrustService.changeEnableFlag(param));
    }

    /**
     * 删除流程办理委托
     */
    @PreAuthorize("@ss.hasPermi('worksetting:entrust:remove')")
    @Log(title = "流程办理委托", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workflowEntrustService.deleteWorkflowEntrustByIds(ids));
    }
}
