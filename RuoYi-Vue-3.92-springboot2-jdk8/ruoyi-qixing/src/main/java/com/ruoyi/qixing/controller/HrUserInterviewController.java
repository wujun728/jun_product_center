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
import com.ruoyi.qixing.domain.HrUserInterview;
import com.ruoyi.qixing.service.IHrUserInterviewService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 面试汇总Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/interview")
public class HrUserInterviewController extends BaseController
{
    @Autowired
    private IHrUserInterviewService hrUserInterviewService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询面试汇总列表
     */
    @PreAuthorize("@ss.hasPermi('system:interview:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrUserInterview hrUserInterview)
    {
        startPage();
        List<HrUserInterview> list = hrUserInterviewService.selectHrUserInterviewList(hrUserInterview);
        return getDataTable(list);
    }

    /**
     * 导出面试汇总列表
     */
    @PreAuthorize("@ss.hasPermi('system:interview:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrUserInterview hrUserInterview)
    {
        List<HrUserInterview> list = hrUserInterviewService.selectHrUserInterviewList(hrUserInterview);
        ExcelUtil<HrUserInterview> util = new ExcelUtil<HrUserInterview>(HrUserInterview.class);
        util.exportExcel(response, list, "面试汇总数据");
    }

    /**
     * 获取面试汇总详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:interview:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrUserInterviewService.selectHrUserInterviewById(id));
    }

    /**
     * 新增面试汇总
     */
    @PreAuthorize("@ss.hasPermi('system:interview:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrUserInterview hrUserInterview)
    {
        return toAjax(hrUserInterviewService.insertHrUserInterview(hrUserInterview));
    }

    /**
     * 修改面试汇总
     */
    @PreAuthorize("@ss.hasPermi('system:interview:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrUserInterview hrUserInterview)
    {
        return toAjax(hrUserInterviewService.updateHrUserInterview(hrUserInterview));
    }

    /**
     * 删除面试汇总
     */
    @PreAuthorize("@ss.hasPermi('system:interview:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrUserInterviewService.deleteHrUserInterviewByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrUserInterview> list = hrUserInterviewService.selectHrUserInterviewList(new HrUserInterview());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrUserInterview entity, String templateId)
    {
        hrUserInterviewService.insertHrUserInterview(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrUserInterview-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}