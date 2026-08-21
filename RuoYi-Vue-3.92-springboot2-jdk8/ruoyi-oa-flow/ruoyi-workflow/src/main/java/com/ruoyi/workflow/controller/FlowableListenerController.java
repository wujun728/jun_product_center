package com.ruoyi.workflow.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.flowable.domain.FlowableListener;
import com.ruoyi.flowable.service.IFlowableListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程监听Controller
 *
 * @author wocurr.com
 */
@RestController
@RequestMapping("/flowable/listener")
public class FlowableListenerController extends BaseController {
    @Autowired
    private IFlowableListenerService flowListenerService;

    /**
     * 查询流程监听列表
     */
    @PreAuthorize("@ss.hasPermi('flowable:listener:list')")
    @GetMapping("/list")
    public TableDataInfo list(FlowableListener flowableListener) {
        startPage();
        List<FlowableListener> list = flowListenerService.listFlowableListener(flowableListener);
        return getDataTable(list);
    }

    /**
     * 获取流程监听详细信息
     */
    @PreAuthorize("@ss.hasPermi('flowable:listener:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(flowListenerService.getFlowableListenerById(id));
    }

    /**
     * 新增流程监听
     */
    @PreAuthorize("@ss.hasPermi('flowable:listener:add')")
    @Log(title = "流程监听", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowableListener flowableListener) {
        return toAjax(flowListenerService.saveFlowableListener(flowableListener));
    }

    /**
     * 修改流程监听
     */
    @PreAuthorize("@ss.hasPermi('flowable:listener:edit')")
    @Log(title = "流程监听", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowableListener flowableListener) {
        return toAjax(flowListenerService.updateFlowableListener(flowableListener));
    }

    /**
     * 删除流程监听
     */
    @PreAuthorize("@ss.hasPermi('flowable:listener:remove')")
    @Log(title = "流程监听", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(flowListenerService.deleteFlowableListenerByIds(ids));
    }
}
