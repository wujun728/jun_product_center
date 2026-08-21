package com.ruoyi.template.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.template.domain.TemplateDynamicForm;
import com.ruoyi.template.service.ITemplateDynamicFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动态单Controller
 * 
 * @author wucorr.com
 */
@RestController
@RequestMapping("/template/dynamic/form")
public class TemplateDynamicFormController extends BaseController {
    @Autowired
    private ITemplateDynamicFormService templateDynamicFormService;

    /**
     * 查询动态单列表
     */
    @PreAuthorize("@ss.hasPermi('template.dynamic:form:list')")
    @GetMapping("/list")
    public TableDataInfo list(TemplateDynamicForm templateDynamicForm) {
        startPage();
        templateDynamicForm.setEnableFlag(WhetherStatus.YES.getCode());
        templateDynamicForm.setDelFlag(WhetherStatus.NO.getCode());
        List<TemplateDynamicForm> list = templateDynamicFormService.listTemplateDynamicForm(templateDynamicForm);
        return getDataTable(list);
    }

    /**
     * 获取动态单详细信息
     */
    @PreAuthorize("@ss.hasPermi('template.dynamic:form:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(templateDynamicFormService.getTemplateDynamicFormById(id));
    }

    /**
     * 新增动态单
     */
    @PreAuthorize("@ss.hasPermi('template.dynamic:form:add')")
    @Log(title = "动态单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TemplateDynamicForm templateDynamicForm) {
        return toAjax(templateDynamicFormService.saveTemplateDynamicForm(templateDynamicForm));
    }

    /**
     * 修改动态单
     */
    @PreAuthorize("@ss.hasPermi('template.dynamic:form:edit')")
    @Log(title = "动态单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TemplateDynamicForm templateDynamicForm) {
        return toAjax(templateDynamicFormService.updateTemplateDynamicForm(templateDynamicForm));
    }

    /**
     * 删除动态单
     */
    @PreAuthorize("@ss.hasPermi('template.dynamic:form:remove')")
    @Log(title = "动态单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(templateDynamicFormService.deleteTemplateDynamicFormByIds(ids));
    }

    /**
     * 获取可关联动态表单定义列表
     */
    @GetMapping("/optionSelect")
    public AjaxResult optionSelect() {
        return AjaxResult.success(templateDynamicFormService.getOptionSelect());
    }
}
