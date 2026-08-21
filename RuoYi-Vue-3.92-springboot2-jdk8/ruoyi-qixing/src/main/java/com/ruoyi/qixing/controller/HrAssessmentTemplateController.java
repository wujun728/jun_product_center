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
import com.ruoyi.qixing.domain.HrAssessmentTemplate;
import com.ruoyi.qixing.service.IHrAssessmentTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考核模板Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/template")
public class HrAssessmentTemplateController extends BaseController
{
    @Autowired
    private IHrAssessmentTemplateService hrAssessmentTemplateService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询考核模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrAssessmentTemplate hrAssessmentTemplate)
    {
        startPage();
        List<HrAssessmentTemplate> list = hrAssessmentTemplateService.selectHrAssessmentTemplateList(hrAssessmentTemplate);
        return getDataTable(list);
    }

    /**
     * 导出考核模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrAssessmentTemplate hrAssessmentTemplate)
    {
        List<HrAssessmentTemplate> list = hrAssessmentTemplateService.selectHrAssessmentTemplateList(hrAssessmentTemplate);
        ExcelUtil<HrAssessmentTemplate> util = new ExcelUtil<HrAssessmentTemplate>(HrAssessmentTemplate.class);
        util.exportExcel(response, list, "考核模板数据");
    }

    /**
     * 获取考核模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:template:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrAssessmentTemplateService.selectHrAssessmentTemplateById(id));
    }

    /**
     * 新增考核模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrAssessmentTemplate hrAssessmentTemplate)
    {
        return toAjax(hrAssessmentTemplateService.insertHrAssessmentTemplate(hrAssessmentTemplate));
    }

    /**
     * 修改考核模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrAssessmentTemplate hrAssessmentTemplate)
    {
        return toAjax(hrAssessmentTemplateService.updateHrAssessmentTemplate(hrAssessmentTemplate));
    }

    /**
     * 删除考核模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrAssessmentTemplateService.deleteHrAssessmentTemplateByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrAssessmentTemplate> list = hrAssessmentTemplateService.selectHrAssessmentTemplateList(new HrAssessmentTemplate());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrAssessmentTemplate entity, String templateId)
    {
        hrAssessmentTemplateService.insertHrAssessmentTemplate(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrAssessmentTemplate-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}