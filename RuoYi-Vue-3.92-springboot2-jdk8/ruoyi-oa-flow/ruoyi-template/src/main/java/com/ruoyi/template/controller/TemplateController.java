package com.ruoyi.template.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.module.TemplateDTO;
import com.ruoyi.template.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模板配置Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/template/template")
public class TemplateController extends BaseController {
    @Autowired
    private ITemplateService templateService;

    /**
     * 查询模板配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Template template) {
        startPage();
        List<Template> list = templateService.listTemplate(template);
        return getDataTable(list);
    }

    /**
     * 获取模板配置详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(templateService.getTemplateDTOById(id));
    }

    /**
     * 新增模板配置
     */
    @PreAuthorize("@ss.hasPermi('workflow:template:add')")
    @Log(title = "模板配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TemplateDTO template) {
        return toAjax(templateService.saveTemplate(template));
    }

    /**
     * 修改模板配置
     */
    @PreAuthorize("@ss.hasPermi('workflow:template:edit')")
    @Log(title = "模板配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TemplateDTO template) {
        return toAjax(templateService.updateTemplate(template));
    }

    /**
     * 删除模板配置
     */
    @PreAuthorize("@ss.hasPermi('workflow:template:remove')")
    @Log(title = "模板配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(templateService.deleteTemplateByIds(ids));
    }

    /**
     * 获取模板配置详细信息
     */
    @GetMapping(value = "/newStart")
    public AjaxResult getNewStartTemplateList() {
        return success(templateService.listNewStartTemplate());
    }

    /**
     * 更新模板配置状态
     */
    @PreAuthorize("@ss.hasPermi('workflow:template:status')")
    @Log(title = "模板配置", businessType = BusinessType.UPDATE)
    @PutMapping("/changeEnableFlag")
    public AjaxResult enableFlag(@RequestBody Template template) {
        return toAjax(templateService.changeEnableFlag(template));
    }

    /**
     * 获取下拉模板列表
     */
    @GetMapping(value = "/select")
    public AjaxResult getSelectTemplateList() {
        return success(templateService.getSelectTemplateList());
    }
}
