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
import com.ruoyi.qixing.domain.OaOfficeCount2;
import com.ruoyi.qixing.service.IOaOfficeCount2Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 办公用品申领申购Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/count2")
public class OaOfficeCount2Controller extends BaseController
{
    @Autowired
    private IOaOfficeCount2Service oaOfficeCount2Service;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询办公用品申领申购列表
     */
    @PreAuthorize("@ss.hasPermi('system:count2:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaOfficeCount2 oaOfficeCount2)
    {
        startPage();
        List<OaOfficeCount2> list = oaOfficeCount2Service.selectOaOfficeCount2List(oaOfficeCount2);
        return getDataTable(list);
    }

    /**
     * 导出办公用品申领申购列表
     */
    @PreAuthorize("@ss.hasPermi('system:count2:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaOfficeCount2 oaOfficeCount2)
    {
        List<OaOfficeCount2> list = oaOfficeCount2Service.selectOaOfficeCount2List(oaOfficeCount2);
        ExcelUtil<OaOfficeCount2> util = new ExcelUtil<OaOfficeCount2>(OaOfficeCount2.class);
        util.exportExcel(response, list, "办公用品申领申购数据");
    }

    /**
     * 获取办公用品申领申购详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:count2:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaOfficeCount2Service.selectOaOfficeCount2ById(id));
    }

    /**
     * 新增办公用品申领申购
     */
    @PreAuthorize("@ss.hasPermi('system:count2:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaOfficeCount2 oaOfficeCount2)
    {
        return toAjax(oaOfficeCount2Service.insertOaOfficeCount2(oaOfficeCount2));
    }

    /**
     * 修改办公用品申领申购
     */
    @PreAuthorize("@ss.hasPermi('system:count2:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaOfficeCount2 oaOfficeCount2)
    {
        return toAjax(oaOfficeCount2Service.updateOaOfficeCount2(oaOfficeCount2));
    }

    /**
     * 删除办公用品申领申购
     */
    @PreAuthorize("@ss.hasPermi('system:count2:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaOfficeCount2Service.deleteOaOfficeCount2ByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaOfficeCount2> list = oaOfficeCount2Service.selectOaOfficeCount2List(new OaOfficeCount2());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody OaOfficeCount2 entity, String templateId)
    {
        oaOfficeCount2Service.insertOaOfficeCount2(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("OaOfficeCount2-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}