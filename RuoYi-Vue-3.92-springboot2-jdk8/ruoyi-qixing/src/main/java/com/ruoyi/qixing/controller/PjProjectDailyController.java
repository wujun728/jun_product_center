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
import com.ruoyi.qixing.domain.PjProjectDaily;
import com.ruoyi.qixing.service.IPjProjectDailyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目日报周报Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/daily")
public class PjProjectDailyController extends BaseController
{
    @Autowired
    private IPjProjectDailyService pjProjectDailyService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目日报周报列表
     */
    @PreAuthorize("@ss.hasPermi('system:daily:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectDaily pjProjectDaily)
    {
        startPage();
        List<PjProjectDaily> list = pjProjectDailyService.selectPjProjectDailyList(pjProjectDaily);
        return getDataTable(list);
    }

    /**
     * 导出项目日报周报列表
     */
    @PreAuthorize("@ss.hasPermi('system:daily:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectDaily pjProjectDaily)
    {
        List<PjProjectDaily> list = pjProjectDailyService.selectPjProjectDailyList(pjProjectDaily);
        ExcelUtil<PjProjectDaily> util = new ExcelUtil<PjProjectDaily>(PjProjectDaily.class);
        util.exportExcel(response, list, "项目日报周报数据");
    }

    /**
     * 获取项目日报周报详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:daily:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectDailyService.selectPjProjectDailyById(id));
    }

    /**
     * 新增项目日报周报
     */
    @PreAuthorize("@ss.hasPermi('system:daily:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectDaily pjProjectDaily)
    {
        return toAjax(pjProjectDailyService.insertPjProjectDaily(pjProjectDaily));
    }

    /**
     * 修改项目日报周报
     */
    @PreAuthorize("@ss.hasPermi('system:daily:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectDaily pjProjectDaily)
    {
        return toAjax(pjProjectDailyService.updatePjProjectDaily(pjProjectDaily));
    }

    /**
     * 删除项目日报周报
     */
    @PreAuthorize("@ss.hasPermi('system:daily:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectDailyService.deletePjProjectDailyByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectDaily> list = pjProjectDailyService.selectPjProjectDailyList(new PjProjectDaily());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectDaily entity, String templateId)
    {
        pjProjectDailyService.insertPjProjectDaily(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectDaily-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}