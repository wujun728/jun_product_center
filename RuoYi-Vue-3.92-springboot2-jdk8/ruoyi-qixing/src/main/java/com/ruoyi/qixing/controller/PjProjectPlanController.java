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
import com.ruoyi.qixing.domain.PjProjectPlan;
import com.ruoyi.qixing.service.IPjProjectPlanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目计划Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/plan")
public class PjProjectPlanController extends BaseController
{
    @Autowired
    private IPjProjectPlanService pjProjectPlanService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目计划列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectPlan pjProjectPlan)
    {
        startPage();
        List<PjProjectPlan> list = pjProjectPlanService.selectPjProjectPlanList(pjProjectPlan);
        return getDataTable(list);
    }

    /**
     * 导出项目计划列表
     */
    @PreAuthorize("@ss.hasPermi('system:plan:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectPlan pjProjectPlan)
    {
        List<PjProjectPlan> list = pjProjectPlanService.selectPjProjectPlanList(pjProjectPlan);
        ExcelUtil<PjProjectPlan> util = new ExcelUtil<PjProjectPlan>(PjProjectPlan.class);
        util.exportExcel(response, list, "项目计划数据");
    }

    /**
     * 获取项目计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:plan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectPlanService.selectPjProjectPlanById(id));
    }

    /**
     * 新增项目计划
     */
    @PreAuthorize("@ss.hasPermi('system:plan:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectPlan pjProjectPlan)
    {
        return toAjax(pjProjectPlanService.insertPjProjectPlan(pjProjectPlan));
    }

    /**
     * 修改项目计划
     */
    @PreAuthorize("@ss.hasPermi('system:plan:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectPlan pjProjectPlan)
    {
        return toAjax(pjProjectPlanService.updatePjProjectPlan(pjProjectPlan));
    }

    /**
     * 删除项目计划
     */
    @PreAuthorize("@ss.hasPermi('system:plan:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectPlanService.deletePjProjectPlanByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectPlan> list = pjProjectPlanService.selectPjProjectPlanList(new PjProjectPlan());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectPlan entity, String templateId)
    {
        pjProjectPlanService.insertPjProjectPlan(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectPlan-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}