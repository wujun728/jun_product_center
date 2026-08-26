package com.ruoyi.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flowable.domain.FlowableExpression;
import com.ruoyi.flowable.service.IFlowableExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程达式Controller
 *
 * @author wocurr.com
 */
@RestController
@RequestMapping("/flowable/expression")
public class FlowableExpressionController extends BaseController {
    @Autowired
    private IFlowableExpressionService flowableExpressionService;

    /**
     * 查询流程达式列表
     */
    @PreAuthorize("@ss.hasPermi('flowable:expression:list')")
    @GetMapping("/list")
    public TableDataInfo list(FlowableExpression flowableExpression) {
        startPage();
        List<FlowableExpression> list = flowableExpressionService.listFlowableExpression(flowableExpression);
        return getDataTable(list);
    }

    /**
     * 获取流程达式详细信息
     */
    @PreAuthorize("@ss.hasPermi('flowable:expression:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(flowableExpressionService.getFlowableExpressionById(id));
    }

    /**
     * 新增流程达式
     */
    @PreAuthorize("@ss.hasPermi('flowable:expression:add')")
    @Log(title = "流程达式", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowableExpression flowableExpression) {
        return toAjax(flowableExpressionService.saveFlowableExpression(flowableExpression));
    }

    /**
     * 修改流程达式
     */
    @PreAuthorize("@ss.hasPermi('flowable:expression:edit')")
    @Log(title = "流程达式", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowableExpression flowableExpression) {
        return toAjax(flowableExpressionService.updateFlowableExpression(flowableExpression));
    }

    /**
     * 删除流程达式
     */
    @PreAuthorize("@ss.hasPermi('flowable:expression:remove')")
    @Log(title = "流程达式", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(flowableExpressionService.deleteFlowableExpressionByIds(ids));
    }
}
