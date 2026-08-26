package com.ruoyi.kbs.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.kbs.domain.KbsDocumentView;
import com.ruoyi.kbs.service.IKbsDocumentViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 知识库文档浏览Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/document/view")
public class KbsDocumentViewController extends BaseController {
    @Autowired
    private IKbsDocumentViewService kbsDocumentViewService;

    /**
     * 新增知识库文档浏览
     */
    @PostMapping
    public AjaxResult add(@RequestBody KbsDocumentView kbsDocumentView) {
        return toAjax(kbsDocumentViewService.saveKbsDocumentView(kbsDocumentView));
    }
}
