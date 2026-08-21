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
import com.ruoyi.qixing.domain.HrUserHire;
import com.ruoyi.qixing.service.IHrUserHireService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 录用审批Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/hire")
public class HrUserHireController extends BaseController
{
    @Autowired
    private IHrUserHireService hrUserHireService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询录用审批列表
     */
    @PreAuthorize("@ss.hasPermi('system:hire:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrUserHire hrUserHire)
    {
        startPage();
        List<HrUserHire> list = hrUserHireService.selectHrUserHireList(hrUserHire);
        return getDataTable(list);
    }

    /**
     * 导出录用审批列表
     */
    @PreAuthorize("@ss.hasPermi('system:hire:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrUserHire hrUserHire)
    {
        List<HrUserHire> list = hrUserHireService.selectHrUserHireList(hrUserHire);
        ExcelUtil<HrUserHire> util = new ExcelUtil<HrUserHire>(HrUserHire.class);
        util.exportExcel(response, list, "录用审批数据");
    }

    /**
     * 获取录用审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:hire:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrUserHireService.selectHrUserHireById(id));
    }

    /**
     * 新增录用审批
     */
    @PreAuthorize("@ss.hasPermi('system:hire:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrUserHire hrUserHire)
    {
        return toAjax(hrUserHireService.insertHrUserHire(hrUserHire));
    }

    /**
     * 修改录用审批
     */
    @PreAuthorize("@ss.hasPermi('system:hire:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrUserHire hrUserHire)
    {
        return toAjax(hrUserHireService.updateHrUserHire(hrUserHire));
    }

    /**
     * 删除录用审批
     */
    @PreAuthorize("@ss.hasPermi('system:hire:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrUserHireService.deleteHrUserHireByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrUserHire> list = hrUserHireService.selectHrUserHireList(new HrUserHire());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrUserHire entity, String templateId)
    {
        hrUserHireService.insertHrUserHire(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrUserHire-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}