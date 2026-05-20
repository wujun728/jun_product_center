package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsDocumentCommentLike;

import java.util.List;

/**
 * 知识库文档评论点赞Service接口
 *
 * @author wocurr.com
 */
public interface IKbsDocumentCommentLikeService {

    /**
     * 新增知识库文档评论点赞
     *
     * @param kbsDocumentCommentLike 知识库文档评论点赞
     * @return 结果
     */
    public int saveKbsDocumentCommentLike(KbsDocumentCommentLike kbsDocumentCommentLike);

    /**
     * 根据评论ID集合查询点赞记录
     *
     * @param commentIds 评论ID集合
     * @return 点赞记录
     */
    List<KbsDocumentCommentLike> listKbsDocumentCommentLikeByCommentIds(List<String> commentIds);

    /**
     * 根据评论ID删除点赞记录
     *
     * @param commentId
     * @return 结果
     */
    int deleteKbsDocumentCommentLikeByCommentId(String commentId);
}
