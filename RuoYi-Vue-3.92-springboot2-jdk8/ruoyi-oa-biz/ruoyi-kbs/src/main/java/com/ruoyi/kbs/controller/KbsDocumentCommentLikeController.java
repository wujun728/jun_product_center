package com.ruoyi.kbs.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsDocumentCommentLike;
import com.ruoyi.kbs.service.IKbsDocumentCommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 知识库文档评论点赞Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/document/comment/like")
public class KbsDocumentCommentLikeController extends BaseController {
    @Autowired
    private IKbsDocumentCommentLikeService kbsDocumentCommentLikeService;

    /**
     * 新增知识库文档评论点赞
     */
    @PostMapping
    public AjaxResult add(@RequestBody KbsDocumentCommentLike kbsDocumentCommentLike) {
        return toAjax(kbsDocumentCommentLikeService.saveKbsDocumentCommentLike(kbsDocumentCommentLike));
    }

    /**
     * 删除知识库文档评论点赞
     */
    @Log(title = "知识库文档评论点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commentId}")
    public AjaxResult remove(@PathVariable("commentId") String commentId) {
        return toAjax(kbsDocumentCommentLikeService.deleteKbsDocumentCommentLikeByCommentId(commentId));
    }
}
