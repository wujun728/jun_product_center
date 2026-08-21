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
import com.ruoyi.qixing.domain.HrAssessmentTemplateDetail;
import com.ruoyi.qixing.service.IHrAssessmentTemplateDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考核模板明细Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/templateDetail")
public class HrAssessmentTemplateDetailController extends BaseController
{
    @Autowired
    private IHrAssessmentTemplateDetailService hrAssessmentTemplateDetailService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询考核模板明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrAssessmentTemplateDetail hrAssessmentTemplateDetail)
    {
        startPage();
        List<HrAssessmentTemplateDetail> list = hrAssessmentTemplateDetailService.selectHrAssessmentTemplateDetailList(hrAssessmentTemplateDetail);
        return getDataTable(list);
    }

    /**
     * 导出考核模板明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrAssessmentTemplateDetail hrAssessmentTemplateDetail)
    {
        List<HrAssessmentTemplateDetail> list = hrAssessmentTemplateDetailService.selectHrAssessmentTemplateDetailList(hrAssessmentTemplateDetail);
        ExcelUtil<HrAssessmentTemplateDetail> util = new ExcelUtil<HrAssessmentTemplateDetail>(HrAssessmentTemplateDetail.class);
        util.exportExcel(response, list, "考核模板明细数据");
    }

    /**
     * 获取考核模板明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrAssessmentTemplateDetailService.selectHrAssessmentTemplateDetailById(id));
    }

    /**
     * 新增考核模板明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrAssessmentTemplateDetail hrAssessmentTemplateDetail)
    {
        return toAjax(hrAssessmentTemplateDetailService.insertHrAssessmentTemplateDetail(hrAssessmentTemplateDetail));
    }

    /**
     * 修改考核模板明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrAssessmentTemplateDetail hrAssessmentTemplateDetail)
    {
        return toAjax(hrAssessmentTemplateDetailService.updateHrAssessmentTemplateDetail(hrAssessmentTemplateDetail));
    }

    /**
     * 删除考核模板明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrAssessmentTemplateDetailService.deleteHrAssessmentTemplateDetailByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrAssessmentTemplateDetail> list = hrAssessmentTemplateDetailService.selectHrAssessmentTemplateDetailList(new HrAssessmentTemplateDetail());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrAssessmentTemplateDetail entity, String templateId)
    {
        hrAssessmentTemplateDetailService.insertHrAssessmentTemplateDetail(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrAssessmentTemplateDetail-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}