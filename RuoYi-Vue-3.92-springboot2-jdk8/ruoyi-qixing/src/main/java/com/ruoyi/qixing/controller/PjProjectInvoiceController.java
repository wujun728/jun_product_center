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
import com.ruoyi.qixing.domain.PjProjectInvoice;
import com.ruoyi.qixing.service.IPjProjectInvoiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目开票Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/invoice")
public class PjProjectInvoiceController extends BaseController
{
    @Autowired
    private IPjProjectInvoiceService pjProjectInvoiceService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目开票列表
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectInvoice pjProjectInvoice)
    {
        startPage();
        List<PjProjectInvoice> list = pjProjectInvoiceService.selectPjProjectInvoiceList(pjProjectInvoice);
        return getDataTable(list);
    }

    /**
     * 导出项目开票列表
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectInvoice pjProjectInvoice)
    {
        List<PjProjectInvoice> list = pjProjectInvoiceService.selectPjProjectInvoiceList(pjProjectInvoice);
        ExcelUtil<PjProjectInvoice> util = new ExcelUtil<PjProjectInvoice>(PjProjectInvoice.class);
        util.exportExcel(response, list, "项目开票数据");
    }

    /**
     * 获取项目开票详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectInvoiceService.selectPjProjectInvoiceById(id));
    }

    /**
     * 新增项目开票
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectInvoice pjProjectInvoice)
    {
        return toAjax(pjProjectInvoiceService.insertPjProjectInvoice(pjProjectInvoice));
    }

    /**
     * 修改项目开票
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectInvoice pjProjectInvoice)
    {
        return toAjax(pjProjectInvoiceService.updatePjProjectInvoice(pjProjectInvoice));
    }

    /**
     * 删除项目开票
     */
    @PreAuthorize("@ss.hasPermi('system:invoice:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectInvoiceService.deletePjProjectInvoiceByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectInvoice> list = pjProjectInvoiceService.selectPjProjectInvoiceList(new PjProjectInvoice());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectInvoice entity, String templateId)
    {
        pjProjectInvoiceService.insertPjProjectInvoice(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectInvoice-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}