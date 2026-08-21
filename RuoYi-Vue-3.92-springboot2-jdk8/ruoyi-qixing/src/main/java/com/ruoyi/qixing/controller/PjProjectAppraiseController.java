package com.ruoyi.qixing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.service.IFlowHandleService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qixing.domain.PjProjectAppraise;
import com.ruoyi.qixing.service.IPjProjectAppraiseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目总结及评价Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/appraise")
public class PjProjectAppraiseController extends BaseController
{
    @Autowired
    private IPjProjectAppraiseService pjProjectAppraiseService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目总结及评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectAppraise pjProjectAppraise)
    {
        startPage();
        List<PjProjectAppraise> list = pjProjectAppraiseService.selectPjProjectAppraiseList(pjProjectAppraise);
        return getDataTable(list);
    }

    /**
     * 导出项目总结及评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectAppraise pjProjectAppraise)
    {
        List<PjProjectAppraise> list = pjProjectAppraiseService.selectPjProjectAppraiseList(pjProjectAppraise);
        ExcelUtil<PjProjectAppraise> util = new ExcelUtil<PjProjectAppraise>(PjProjectAppraise.class);
        util.exportExcel(response, list, "项目总结及评价数据");
    }

    /**
     * 获取项目总结及评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectAppraiseService.selectPjProjectAppraiseById(id));
    }

    /**
     * 新增项目总结及评价
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectAppraise pjProjectAppraise)
    {
        return toAjax(pjProjectAppraiseService.insertPjProjectAppraise(pjProjectAppraise));
    }

    /**
     * 修改项目总结及评价
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectAppraise pjProjectAppraise)
    {
        return toAjax(pjProjectAppraiseService.updatePjProjectAppraise(pjProjectAppraise));
    }

    /**
     * 删除项目总结及评价
     */
    @PreAuthorize("@ss.hasPermi('system:appraise:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectAppraiseService.deletePjProjectAppraiseByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectAppraise> list = pjProjectAppraiseService.selectPjProjectAppraiseList(new PjProjectAppraise());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectAppraise entity, String templateId)
    {
        pjProjectAppraiseService.insertPjProjectAppraise(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectAppraise-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}