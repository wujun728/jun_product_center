package com.ruoyi.kbs.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsDocumentHistory;
import com.ruoyi.kbs.service.IKbsDocumentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库文档历史记录Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/document/history")
public class KbsDocumentHistoryController extends BaseController {
    @Autowired
    private IKbsDocumentHistoryService kbsDocumentHistoryService;

    /**
     * 查询知识库文档历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('document:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(KbsDocumentHistory kbsDocumentHistory) {
        startPage();
        List<KbsDocumentHistory> list = kbsDocumentHistoryService.listKbsDocumentHistory(kbsDocumentHistory);
        return getDataTable(list);
    }

    /**
     * 获取知识库文档历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('document:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(kbsDocumentHistoryService.getKbsDocumentHistoryById(id));
    }

    /**
     * 新增知识库文档历史记录
     */
    @PreAuthorize("@ss.hasPermi('document:history:add')")
    @Log(title = "知识库文档历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KbsDocumentHistory kbsDocumentHistory) {
        return toAjax(kbsDocumentHistoryService.saveKbsDocumentHistory(kbsDocumentHistory));
    }

    /**
     * 删除知识库文档历史记录
     */
    @PreAuthorize("@ss.hasPermi('document:history:remove')")
    @Log(title = "知识库文档历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(kbsDocumentHistoryService.deleteKbsDocumentHistoryByIds(ids));
    }

    /**
     * 恢复知识库文档历史记录
     */
    @PreAuthorize("@ss.hasPermi('document:history:update')")
    @Log(title = "知识库文档历史记录", businessType = BusinessType.UPDATE)
    @PostMapping("/recover/{id}")
    public AjaxResult recoverHistory(@PathVariable("id") String id) {
        return toAjax(kbsDocumentHistoryService.recoverHistory(id));
    }
}
