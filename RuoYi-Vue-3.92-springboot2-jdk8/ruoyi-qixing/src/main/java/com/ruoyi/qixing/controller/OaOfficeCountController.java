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
import com.ruoyi.qixing.domain.OaOfficeCount;
import com.ruoyi.qixing.service.IOaOfficeCountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 办公用品申领申购Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/count")
public class OaOfficeCountController extends BaseController
{
    @Autowired
    private IOaOfficeCountService oaOfficeCountService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询办公用品申领申购列表
     */
    @PreAuthorize("@ss.hasPermi('system:count:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaOfficeCount oaOfficeCount)
    {
        startPage();
        List<OaOfficeCount> list = oaOfficeCountService.selectOaOfficeCountList(oaOfficeCount);
        return getDataTable(list);
    }

    /**
     * 导出办公用品申领申购列表
     */
    @PreAuthorize("@ss.hasPermi('system:count:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaOfficeCount oaOfficeCount)
    {
        List<OaOfficeCount> list = oaOfficeCountService.selectOaOfficeCountList(oaOfficeCount);
        ExcelUtil<OaOfficeCount> util = new ExcelUtil<OaOfficeCount>(OaOfficeCount.class);
        util.exportExcel(response, list, "办公用品申领申购数据");
    }

    /**
     * 获取办公用品申领申购详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:count:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaOfficeCountService.selectOaOfficeCountById(id));
    }

    /**
     * 新增办公用品申领申购
     */
    @PreAuthorize("@ss.hasPermi('system:count:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaOfficeCount oaOfficeCount)
    {
        return toAjax(oaOfficeCountService.insertOaOfficeCount(oaOfficeCount));
    }

    /**
     * 修改办公用品申领申购
     */
    @PreAuthorize("@ss.hasPermi('system:count:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaOfficeCount oaOfficeCount)
    {
        return toAjax(oaOfficeCountService.updateOaOfficeCount(oaOfficeCount));
    }

    /**
     * 删除办公用品申领申购
     */
    @PreAuthorize("@ss.hasPermi('system:count:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaOfficeCountService.deleteOaOfficeCountByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaOfficeCount> list = oaOfficeCountService.selectOaOfficeCountList(new OaOfficeCount());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody OaOfficeCount entity, String templateId)
    {
        oaOfficeCountService.insertOaOfficeCount(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("OaOfficeCount-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}