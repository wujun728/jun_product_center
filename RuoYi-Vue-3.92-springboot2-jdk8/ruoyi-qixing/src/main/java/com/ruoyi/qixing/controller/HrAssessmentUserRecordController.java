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
import com.ruoyi.qixing.domain.HrAssessmentUserRecord;
import com.ruoyi.qixing.service.IHrAssessmentUserRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考核记录Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/record")
public class HrAssessmentUserRecordController extends BaseController
{
    @Autowired
    private IHrAssessmentUserRecordService hrAssessmentUserRecordService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询考核记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrAssessmentUserRecord hrAssessmentUserRecord)
    {
        startPage();
        List<HrAssessmentUserRecord> list = hrAssessmentUserRecordService.selectHrAssessmentUserRecordList(hrAssessmentUserRecord);
        return getDataTable(list);
    }

    /**
     * 导出考核记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrAssessmentUserRecord hrAssessmentUserRecord)
    {
        List<HrAssessmentUserRecord> list = hrAssessmentUserRecordService.selectHrAssessmentUserRecordList(hrAssessmentUserRecord);
        ExcelUtil<HrAssessmentUserRecord> util = new ExcelUtil<HrAssessmentUserRecord>(HrAssessmentUserRecord.class);
        util.exportExcel(response, list, "考核记录数据");
    }

    /**
     * 获取考核记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrAssessmentUserRecordService.selectHrAssessmentUserRecordById(id));
    }

    /**
     * 新增考核记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrAssessmentUserRecord hrAssessmentUserRecord)
    {
        return toAjax(hrAssessmentUserRecordService.insertHrAssessmentUserRecord(hrAssessmentUserRecord));
    }

    /**
     * 修改考核记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrAssessmentUserRecord hrAssessmentUserRecord)
    {
        return toAjax(hrAssessmentUserRecordService.updateHrAssessmentUserRecord(hrAssessmentUserRecord));
    }

    /**
     * 删除考核记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrAssessmentUserRecordService.deleteHrAssessmentUserRecordByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrAssessmentUserRecord> list = hrAssessmentUserRecordService.selectHrAssessmentUserRecordList(new HrAssessmentUserRecord());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrAssessmentUserRecord entity, String templateId)
    {
        hrAssessmentUserRecordService.insertHrAssessmentUserRecord(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrAssessmentUserRecord-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}