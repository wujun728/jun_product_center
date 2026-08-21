package com.ruoyi.kbs.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsDocumentInfo;
import com.ruoyi.kbs.service.IKbsDocumentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 知识库文档详情Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/document/info")
public class KbsDocumentInfoController extends BaseController {
    @Autowired
    private IKbsDocumentInfoService kbsDocumentInfoService;

    /**
     * 获取知识库文档详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('document:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(kbsDocumentInfoService.getKbsDocumentInfoById(id));
    }

    /**
     * 新增知识库文档详情
     */
    @PreAuthorize("@ss.hasPermi('document:info:add')")
    @Log(title = "知识库文档详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KbsDocumentInfo kbsDocumentInfo) {
        return toAjax(kbsDocumentInfoService.saveKbsDocumentInfo(kbsDocumentInfo));
    }

    /**
     * 修改知识库文档详情
     */
    @PreAuthorize("@ss.hasPermi('document:info:edit')")
    @Log(title = "知识库文档详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KbsDocumentInfo kbsDocumentInfo) {
        return toAjax(kbsDocumentInfoService.updateKbsDocumentInfo(kbsDocumentInfo));
    }

    /**
     * 获取知识库文档统计数据
     */
    @GetMapping(value = "/stat/{id}")
    public AjaxResult getDocumentStatNum(@PathVariable("id") String id) {
        return success(kbsDocumentInfoService.getDocumentStatNum(id));
    }
}
