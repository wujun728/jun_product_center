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
import com.ruoyi.qixing.domain.PjProject;
import com.ruoyi.qixing.service.IPjProjectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.service.IFlowHandleService;

@RestController
@RequestMapping("/system/project")
public class PjProjectController extends BaseController
{
    @Autowired
    private IPjProjectService pjProjectService;

    @Autowired
    private IFlowHandleService flowHandleService;

    @PreAuthorize("@ss.hasPermi('system:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProject pjProject)
    {
        startPage();
        List<PjProject> list = pjProjectService.selectPjProjectList(pjProject);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:project:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProject pjProject)
    {
        List<PjProject> list = pjProjectService.selectPjProjectList(pjProject);
        ExcelUtil<PjProject> util = new ExcelUtil<PjProject>(PjProject.class);
        util.exportExcel(response, list, "项目信息数据");
    }

    @PreAuthorize("@ss.hasPermi('system:project:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectService.selectPjProjectById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:project:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProject pjProject)
    {
        return toAjax(pjProjectService.insertPjProject(pjProject));
    }

    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProject pjProject)
    {
        return toAjax(pjProjectService.updatePjProject(pjProject));
    }

    @PreAuthorize("@ss.hasPermi('system:project:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectService.deletePjProjectByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProject> list = pjProjectService.selectPjProjectList(new PjProject());
        return success(list);
    }

    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProject pjProject, String templateId)
    {
        pjProjectService.insertPjProject(pjProject);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(pjProject.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("项目立项-" + pjProject.getProjectName());
        flowTaskVo.getVariables().put("businessId", pjProject.getId());
        flowTaskVo.getVariables().put("projectCode", pjProject.getProjectCode());
        flowTaskVo.getVariables().put("projectName", pjProject.getProjectName());
        flowTaskVo.getVariables().put("assign_step1", pjProject.getRefUndertakePerson());
        flowTaskVo.getVariables().put("assign_step2", "");
        flowTaskVo.getVariables().put("assign_step3", "");

        return success(flowHandleService.startFlow(flowTaskVo));
    }
}