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
import com.ruoyi.qixing.domain.PjProjectReport;
import com.ruoyi.qixing.service.IPjProjectReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目报告Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/report")
public class PjProjectReportController extends BaseController
{
    @Autowired
    private IPjProjectReportService pjProjectReportService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectReport pjProjectReport)
    {
        startPage();
        List<PjProjectReport> list = pjProjectReportService.selectPjProjectReportList(pjProjectReport);
        return getDataTable(list);
    }

    /**
     * 导出项目报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectReport pjProjectReport)
    {
        List<PjProjectReport> list = pjProjectReportService.selectPjProjectReportList(pjProjectReport);
        ExcelUtil<PjProjectReport> util = new ExcelUtil<PjProjectReport>(PjProjectReport.class);
        util.exportExcel(response, list, "项目报告数据");
    }

    /**
     * 获取项目报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectReportService.selectPjProjectReportById(id));
    }

    /**
     * 新增项目报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectReport pjProjectReport)
    {
        return toAjax(pjProjectReportService.insertPjProjectReport(pjProjectReport));
    }

    /**
     * 修改项目报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectReport pjProjectReport)
    {
        return toAjax(pjProjectReportService.updatePjProjectReport(pjProjectReport));
    }

    /**
     * 删除项目报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectReportService.deletePjProjectReportByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectReport> list = pjProjectReportService.selectPjProjectReportList(new PjProjectReport());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectReport entity, String templateId)
    {
        pjProjectReportService.insertPjProjectReport(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectReport-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}