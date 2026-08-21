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
import com.ruoyi.qixing.domain.HrUserDimission;
import com.ruoyi.qixing.service.IHrUserDimissionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 离职Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/dimission")
public class HrUserDimissionController extends BaseController
{
    @Autowired
    private IHrUserDimissionService hrUserDimissionService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询离职列表
     */
    @PreAuthorize("@ss.hasPermi('system:dimission:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrUserDimission hrUserDimission)
    {
        startPage();
        List<HrUserDimission> list = hrUserDimissionService.selectHrUserDimissionList(hrUserDimission);
        return getDataTable(list);
    }

    /**
     * 导出离职列表
     */
    @PreAuthorize("@ss.hasPermi('system:dimission:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrUserDimission hrUserDimission)
    {
        List<HrUserDimission> list = hrUserDimissionService.selectHrUserDimissionList(hrUserDimission);
        ExcelUtil<HrUserDimission> util = new ExcelUtil<HrUserDimission>(HrUserDimission.class);
        util.exportExcel(response, list, "离职数据");
    }

    /**
     * 获取离职详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dimission:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrUserDimissionService.selectHrUserDimissionById(id));
    }

    /**
     * 新增离职
     */
    @PreAuthorize("@ss.hasPermi('system:dimission:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrUserDimission hrUserDimission)
    {
        return toAjax(hrUserDimissionService.insertHrUserDimission(hrUserDimission));
    }

    /**
     * 修改离职
     */
    @PreAuthorize("@ss.hasPermi('system:dimission:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrUserDimission hrUserDimission)
    {
        return toAjax(hrUserDimissionService.updateHrUserDimission(hrUserDimission));
    }

    /**
     * 删除离职
     */
    @PreAuthorize("@ss.hasPermi('system:dimission:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrUserDimissionService.deleteHrUserDimissionByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrUserDimission> list = hrUserDimissionService.selectHrUserDimissionList(new HrUserDimission());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrUserDimission entity, String templateId)
    {
        hrUserDimissionService.insertHrUserDimission(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrUserDimission-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}