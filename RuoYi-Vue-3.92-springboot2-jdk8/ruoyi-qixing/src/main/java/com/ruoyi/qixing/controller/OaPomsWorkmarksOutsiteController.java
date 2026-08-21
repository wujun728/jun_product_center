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
import com.ruoyi.qixing.domain.OaPomsWorkmarksOutsite;
import com.ruoyi.qixing.service.IOaPomsWorkmarksOutsiteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 外出信息Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/outsite")
public class OaPomsWorkmarksOutsiteController extends BaseController
{
    @Autowired
    private IOaPomsWorkmarksOutsiteService oaPomsWorkmarksOutsiteService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询外出信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:outsite:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite)
    {
        startPage();
        List<OaPomsWorkmarksOutsite> list = oaPomsWorkmarksOutsiteService.selectOaPomsWorkmarksOutsiteList(oaPomsWorkmarksOutsite);
        return getDataTable(list);
    }

    /**
     * 导出外出信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:outsite:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite)
    {
        List<OaPomsWorkmarksOutsite> list = oaPomsWorkmarksOutsiteService.selectOaPomsWorkmarksOutsiteList(oaPomsWorkmarksOutsite);
        ExcelUtil<OaPomsWorkmarksOutsite> util = new ExcelUtil<OaPomsWorkmarksOutsite>(OaPomsWorkmarksOutsite.class);
        util.exportExcel(response, list, "外出信息数据");
    }

    /**
     * 获取外出信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:outsite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaPomsWorkmarksOutsiteService.selectOaPomsWorkmarksOutsiteById(id));
    }

    /**
     * 新增外出信息
     */
    @PreAuthorize("@ss.hasPermi('system:outsite:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite)
    {
        return toAjax(oaPomsWorkmarksOutsiteService.insertOaPomsWorkmarksOutsite(oaPomsWorkmarksOutsite));
    }

    /**
     * 修改外出信息
     */
    @PreAuthorize("@ss.hasPermi('system:outsite:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaPomsWorkmarksOutsite oaPomsWorkmarksOutsite)
    {
        return toAjax(oaPomsWorkmarksOutsiteService.updateOaPomsWorkmarksOutsite(oaPomsWorkmarksOutsite));
    }

    /**
     * 删除外出信息
     */
    @PreAuthorize("@ss.hasPermi('system:outsite:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaPomsWorkmarksOutsiteService.deleteOaPomsWorkmarksOutsiteByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaPomsWorkmarksOutsite> list = oaPomsWorkmarksOutsiteService.selectOaPomsWorkmarksOutsiteList(new OaPomsWorkmarksOutsite());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody OaPomsWorkmarksOutsite entity, String templateId)
    {
        oaPomsWorkmarksOutsiteService.insertOaPomsWorkmarksOutsite(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("OaPomsWorkmarksOutsite-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}