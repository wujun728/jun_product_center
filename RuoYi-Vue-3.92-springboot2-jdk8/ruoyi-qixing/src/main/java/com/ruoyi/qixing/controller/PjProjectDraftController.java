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
import com.ruoyi.qixing.domain.PjProjectDraft;
import com.ruoyi.qixing.service.IPjProjectDraftService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目底稿Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/draft")
public class PjProjectDraftController extends BaseController
{
    @Autowired
    private IPjProjectDraftService pjProjectDraftService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目底稿列表
     */
    @PreAuthorize("@ss.hasPermi('system:draft:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectDraft pjProjectDraft)
    {
        startPage();
        List<PjProjectDraft> list = pjProjectDraftService.selectPjProjectDraftList(pjProjectDraft);
        return getDataTable(list);
    }

    /**
     * 导出项目底稿列表
     */
    @PreAuthorize("@ss.hasPermi('system:draft:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectDraft pjProjectDraft)
    {
        List<PjProjectDraft> list = pjProjectDraftService.selectPjProjectDraftList(pjProjectDraft);
        ExcelUtil<PjProjectDraft> util = new ExcelUtil<PjProjectDraft>(PjProjectDraft.class);
        util.exportExcel(response, list, "项目底稿数据");
    }

    /**
     * 获取项目底稿详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:draft:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectDraftService.selectPjProjectDraftById(id));
    }

    /**
     * 新增项目底稿
     */
    @PreAuthorize("@ss.hasPermi('system:draft:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectDraft pjProjectDraft)
    {
        return toAjax(pjProjectDraftService.insertPjProjectDraft(pjProjectDraft));
    }

    /**
     * 修改项目底稿
     */
    @PreAuthorize("@ss.hasPermi('system:draft:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectDraft pjProjectDraft)
    {
        return toAjax(pjProjectDraftService.updatePjProjectDraft(pjProjectDraft));
    }

    /**
     * 删除项目底稿
     */
    @PreAuthorize("@ss.hasPermi('system:draft:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectDraftService.deletePjProjectDraftByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectDraft> list = pjProjectDraftService.selectPjProjectDraftList(new PjProjectDraft());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectDraft entity, String templateId)
    {
        pjProjectDraftService.insertPjProjectDraft(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectDraft-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}