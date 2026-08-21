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
import com.ruoyi.qixing.domain.HrAssessmentUserRecordDetail;
import com.ruoyi.qixing.service.IHrAssessmentUserRecordDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考核记录明细Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/recordDetail")
public class HrAssessmentUserRecordDetailController extends BaseController
{
    @Autowired
    private IHrAssessmentUserRecordDetailService hrAssessmentUserRecordDetailService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询考核记录明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail)
    {
        startPage();
        List<HrAssessmentUserRecordDetail> list = hrAssessmentUserRecordDetailService.selectHrAssessmentUserRecordDetailList(hrAssessmentUserRecordDetail);
        return getDataTable(list);
    }

    /**
     * 导出考核记录明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail)
    {
        List<HrAssessmentUserRecordDetail> list = hrAssessmentUserRecordDetailService.selectHrAssessmentUserRecordDetailList(hrAssessmentUserRecordDetail);
        ExcelUtil<HrAssessmentUserRecordDetail> util = new ExcelUtil<HrAssessmentUserRecordDetail>(HrAssessmentUserRecordDetail.class);
        util.exportExcel(response, list, "考核记录明细数据");
    }

    /**
     * 获取考核记录明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrAssessmentUserRecordDetailService.selectHrAssessmentUserRecordDetailById(id));
    }

    /**
     * 新增考核记录明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail)
    {
        return toAjax(hrAssessmentUserRecordDetailService.insertHrAssessmentUserRecordDetail(hrAssessmentUserRecordDetail));
    }

    /**
     * 修改考核记录明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrAssessmentUserRecordDetail hrAssessmentUserRecordDetail)
    {
        return toAjax(hrAssessmentUserRecordDetailService.updateHrAssessmentUserRecordDetail(hrAssessmentUserRecordDetail));
    }

    /**
     * 删除考核记录明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrAssessmentUserRecordDetailService.deleteHrAssessmentUserRecordDetailByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrAssessmentUserRecordDetail> list = hrAssessmentUserRecordDetailService.selectHrAssessmentUserRecordDetailList(new HrAssessmentUserRecordDetail());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrAssessmentUserRecordDetail entity, String templateId)
    {
        hrAssessmentUserRecordDetailService.insertHrAssessmentUserRecordDetail(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrAssessmentUserRecordDetail-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}