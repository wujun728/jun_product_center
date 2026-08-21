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
import com.ruoyi.qixing.domain.OaPomsWorkmarksPayroll;
import com.ruoyi.qixing.service.IOaPomsWorkmarksPayrollService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工资审核发放Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/payroll")
public class OaPomsWorkmarksPayrollController extends BaseController
{
    @Autowired
    private IOaPomsWorkmarksPayrollService oaPomsWorkmarksPayrollService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询工资审核发放列表
     */
    @PreAuthorize("@ss.hasPermi('system:payroll:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll)
    {
        startPage();
        List<OaPomsWorkmarksPayroll> list = oaPomsWorkmarksPayrollService.selectOaPomsWorkmarksPayrollList(oaPomsWorkmarksPayroll);
        return getDataTable(list);
    }

    /**
     * 导出工资审核发放列表
     */
    @PreAuthorize("@ss.hasPermi('system:payroll:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll)
    {
        List<OaPomsWorkmarksPayroll> list = oaPomsWorkmarksPayrollService.selectOaPomsWorkmarksPayrollList(oaPomsWorkmarksPayroll);
        ExcelUtil<OaPomsWorkmarksPayroll> util = new ExcelUtil<OaPomsWorkmarksPayroll>(OaPomsWorkmarksPayroll.class);
        util.exportExcel(response, list, "工资审核发放数据");
    }

    /**
     * 获取工资审核发放详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:payroll:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaPomsWorkmarksPayrollService.selectOaPomsWorkmarksPayrollById(id));
    }

    /**
     * 新增工资审核发放
     */
    @PreAuthorize("@ss.hasPermi('system:payroll:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll)
    {
        return toAjax(oaPomsWorkmarksPayrollService.insertOaPomsWorkmarksPayroll(oaPomsWorkmarksPayroll));
    }

    /**
     * 修改工资审核发放
     */
    @PreAuthorize("@ss.hasPermi('system:payroll:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll)
    {
        return toAjax(oaPomsWorkmarksPayrollService.updateOaPomsWorkmarksPayroll(oaPomsWorkmarksPayroll));
    }

    /**
     * 删除工资审核发放
     */
    @PreAuthorize("@ss.hasPermi('system:payroll:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaPomsWorkmarksPayrollService.deleteOaPomsWorkmarksPayrollByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaPomsWorkmarksPayroll> list = oaPomsWorkmarksPayrollService.selectOaPomsWorkmarksPayrollList(new OaPomsWorkmarksPayroll());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody OaPomsWorkmarksPayroll entity, String templateId)
    {
        oaPomsWorkmarksPayrollService.insertOaPomsWorkmarksPayroll(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("OaPomsWorkmarksPayroll-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}