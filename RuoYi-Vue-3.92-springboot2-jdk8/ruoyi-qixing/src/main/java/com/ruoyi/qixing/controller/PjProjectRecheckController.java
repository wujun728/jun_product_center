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
import com.ruoyi.qixing.domain.PjProjectRecheck;
import com.ruoyi.qixing.service.IPjProjectRecheckService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目复核Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/recheck")
public class PjProjectRecheckController extends BaseController
{
    @Autowired
    private IPjProjectRecheckService pjProjectRecheckService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目复核列表
     */
    @PreAuthorize("@ss.hasPermi('system:recheck:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectRecheck pjProjectRecheck)
    {
        startPage();
        List<PjProjectRecheck> list = pjProjectRecheckService.selectPjProjectRecheckList(pjProjectRecheck);
        return getDataTable(list);
    }

    /**
     * 导出项目复核列表
     */
    @PreAuthorize("@ss.hasPermi('system:recheck:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectRecheck pjProjectRecheck)
    {
        List<PjProjectRecheck> list = pjProjectRecheckService.selectPjProjectRecheckList(pjProjectRecheck);
        ExcelUtil<PjProjectRecheck> util = new ExcelUtil<PjProjectRecheck>(PjProjectRecheck.class);
        util.exportExcel(response, list, "项目复核数据");
    }

    /**
     * 获取项目复核详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:recheck:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectRecheckService.selectPjProjectRecheckById(id));
    }

    /**
     * 新增项目复核
     */
    @PreAuthorize("@ss.hasPermi('system:recheck:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectRecheck pjProjectRecheck)
    {
        return toAjax(pjProjectRecheckService.insertPjProjectRecheck(pjProjectRecheck));
    }

    /**
     * 修改项目复核
     */
    @PreAuthorize("@ss.hasPermi('system:recheck:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectRecheck pjProjectRecheck)
    {
        return toAjax(pjProjectRecheckService.updatePjProjectRecheck(pjProjectRecheck));
    }

    /**
     * 删除项目复核
     */
    @PreAuthorize("@ss.hasPermi('system:recheck:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectRecheckService.deletePjProjectRecheckByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectRecheck> list = pjProjectRecheckService.selectPjProjectRecheckList(new PjProjectRecheck());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectRecheck entity, String templateId)
    {
        pjProjectRecheckService.insertPjProjectRecheck(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectRecheck-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}