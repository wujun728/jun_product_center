package com.ruoyi.kbs.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsDocumentComment;
import com.ruoyi.kbs.domain.vo.KbsDocumentCommentVo;
import com.ruoyi.kbs.service.IKbsDocumentCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库文档评论Controller
 *
 * @author wocurr.com
 */
@RestController
@RequestMapping("/document/comment")
public class KbsDocumentCommentController extends BaseController {
    @Autowired
    private IKbsDocumentCommentService kbsDocumentCommentService;

    /**
     * 查询知识库文档评论列表
     */
    @GetMapping("/list")
    public TableDataInfo list(KbsDocumentComment kbsDocumentComment) {
        startPage();
        List<KbsDocumentCommentVo> list = kbsDocumentCommentService.listKbsDocumentComment(kbsDocumentComment);
        return getDataTable(list);
    }

    /**
     * 新增知识库文档评论
     */
    @Log(title = "知识库文档评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KbsDocumentComment kbsDocumentComment) {
        return AjaxResult.success("操作成功", kbsDocumentCommentService.saveKbsDocumentComment(kbsDocumentComment));
    }

    /**
     * 修改知识库文档评论
     */
    @Log(title = "知识库文档评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KbsDocumentComment kbsDocumentComment) {
        return toAjax(kbsDocumentCommentService.updateKbsDocumentComment(kbsDocumentComment));
    }

    /**
     * 删除知识库文档评论
     */
    @Log(title = "知识库文档评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(kbsDocumentCommentService.deleteKbsDocumentCommentByIds(ids));
    }

    /**
     * 获取知识库文档评论详细信息
     */
    @GetMapping("/listByParent")
    public TableDataInfo getListByParent(KbsDocumentComment kbsDocumentComment) {
        startPage();
        List<KbsDocumentCommentVo> list = kbsDocumentCommentService.listChildKbsDocumentComment(kbsDocumentComment);
        return getDataTable(list);
    }
}
