package com.ruoyi.kbs.controller;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsDocumentTemplate;
import com.ruoyi.kbs.service.IKbsDocumentTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 知识库文档模板Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/document/template")
public class KbsDocumentTemplateController extends BaseController {
    @Autowired
    private IKbsDocumentTemplateService kbsDocumentTemplateService;

    /**
     * 查询知识库文档模板列表
     */
    @PreAuthorize("@ss.hasPermi('document:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(KbsDocumentTemplate kbsDocumentTemplate) {
        startPage();
        List<KbsDocumentTemplate> list = kbsDocumentTemplateService.listKbsDocumentTemplate(kbsDocumentTemplate);
        return getDataTable(list);
    }

    /**
     * 导出知识库文档模板列表
     */
    @PreAuthorize("@ss.hasPermi('document:template:export')")
    @Log(title = "知识库文档模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, KbsDocumentTemplate kbsDocumentTemplate) {
        List<KbsDocumentTemplate> list = kbsDocumentTemplateService.listKbsDocumentTemplate(kbsDocumentTemplate);
        ExcelUtil<KbsDocumentTemplate> util = new ExcelUtil<KbsDocumentTemplate>(KbsDocumentTemplate.class);
        util.exportExcel(response, list, "知识库文档模板数据");
    }

    /**
     * 获取知识库文档模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('document:template:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(kbsDocumentTemplateService.getKbsDocumentTemplateById(id));
    }

    /**
     * 新增知识库文档模板
     */
    @PreAuthorize("@ss.hasPermi('document:template:add')")
    @Log(title = "知识库文档模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KbsDocumentTemplate kbsDocumentTemplate) {
        return toAjax(kbsDocumentTemplateService.saveKbsDocumentTemplate(kbsDocumentTemplate));
    }

    /**
     * 修改知识库文档模板
     */
    @PreAuthorize("@ss.hasPermi('document:template:edit')")
    @Log(title = "知识库文档模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KbsDocumentTemplate kbsDocumentTemplate) {
        return toAjax(kbsDocumentTemplateService.updateKbsDocumentTemplate(kbsDocumentTemplate));
    }

    /**
     * 删除知识库文档模板
     */
    @PreAuthorize("@ss.hasPermi('document:template:remove')")
    @Log(title = "知识库文档模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(kbsDocumentTemplateService.deleteKbsDocumentTemplateByIds(ids));
    }
}
