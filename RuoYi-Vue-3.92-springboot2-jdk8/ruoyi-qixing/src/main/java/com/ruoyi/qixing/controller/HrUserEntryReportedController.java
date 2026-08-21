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
import com.ruoyi.qixing.domain.HrUserEntryReported;
import com.ruoyi.qixing.service.IHrUserEntryReportedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入职报道Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/reported")
public class HrUserEntryReportedController extends BaseController
{
    @Autowired
    private IHrUserEntryReportedService hrUserEntryReportedService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询入职报道列表
     */
    @PreAuthorize("@ss.hasPermi('system:reported:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrUserEntryReported hrUserEntryReported)
    {
        startPage();
        List<HrUserEntryReported> list = hrUserEntryReportedService.selectHrUserEntryReportedList(hrUserEntryReported);
        return getDataTable(list);
    }

    /**
     * 导出入职报道列表
     */
    @PreAuthorize("@ss.hasPermi('system:reported:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrUserEntryReported hrUserEntryReported)
    {
        List<HrUserEntryReported> list = hrUserEntryReportedService.selectHrUserEntryReportedList(hrUserEntryReported);
        ExcelUtil<HrUserEntryReported> util = new ExcelUtil<HrUserEntryReported>(HrUserEntryReported.class);
        util.exportExcel(response, list, "入职报道数据");
    }

    /**
     * 获取入职报道详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reported:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrUserEntryReportedService.selectHrUserEntryReportedById(id));
    }

    /**
     * 新增入职报道
     */
    @PreAuthorize("@ss.hasPermi('system:reported:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrUserEntryReported hrUserEntryReported)
    {
        return toAjax(hrUserEntryReportedService.insertHrUserEntryReported(hrUserEntryReported));
    }

    /**
     * 修改入职报道
     */
    @PreAuthorize("@ss.hasPermi('system:reported:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrUserEntryReported hrUserEntryReported)
    {
        return toAjax(hrUserEntryReportedService.updateHrUserEntryReported(hrUserEntryReported));
    }

    /**
     * 删除入职报道
     */
    @PreAuthorize("@ss.hasPermi('system:reported:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrUserEntryReportedService.deleteHrUserEntryReportedByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrUserEntryReported> list = hrUserEntryReportedService.selectHrUserEntryReportedList(new HrUserEntryReported());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrUserEntryReported entity, String templateId)
    {
        hrUserEntryReportedService.insertHrUserEntryReported(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrUserEntryReported-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}