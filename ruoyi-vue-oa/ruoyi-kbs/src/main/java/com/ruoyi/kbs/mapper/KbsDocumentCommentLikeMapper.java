package com.ruoyi.kbs.mapper;

import java.util.List;
import com.ruoyi.kbs.domain.KbsDocumentCommentLike;
import com.ruoyi.kbs.domain.qo.KbsDocumentCommentLikeQo;

/**
 * 知识库文档评论点赞Mapper接口
 * 
 * @author wocurr.com
 * @date 2025-06-16
 */
public interface KbsDocumentCommentLikeMapper {
    /**
     * 查询知识库文档评论点赞
     * 
     * @param id 知识库文档评论点赞主键
     * @return 知识库文档评论点赞
     */
    public KbsDocumentCommentLike selectKbsDocumentCommentLikeById(String id);

    /**
     * 查询知识库文档评论点赞列表
     * 
     * @param kbsDocumentCommentLike 知识库文档评论点赞
     * @return 知识库文档评论点赞集合
     */
    public List<KbsDocumentCommentLike> selectKbsDocumentCommentLikeList(KbsDocumentCommentLike kbsDocumentCommentLike);

    /**
     * 新增知识库文档评论点赞
     * 
     * @param kbsDocumentCommentLike 知识库文档评论点赞
     * @return 结果
     */
    public int insertKbsDocumentCommentLike(KbsDocumentCommentLike kbsDocumentCommentLike);

    /**
     * 修改知识库文档评论点赞
     * 
     * @param kbsDocumentCommentLike 知识库文档评论点赞
     * @return 结果
     */
    public int updateKbsDocumentCommentLike(KbsDocumentCommentLike kbsDocumentCommentLike);

    /**
     * 删除知识库文档评论点赞
     * 
     * @param id 知识库文档评论点赞主键
     * @return 结果
     */
    public int deleteKbsDocumentCommentLikeById(String id);

    /**
     * 批量删除知识库文档评论点赞
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsDocumentCommentLikeByIds(String[] ids);

    /**
     * 根据评论ID集合查询点赞记录
     *
     * @param commentIds 评论ID集合
     * @return 点赞记录
     */
    List<KbsDocumentCommentLike> selectKbsDocumentCommentLikeByCommentIds(List<String> commentIds);

    /**
     * 根据评论ID删除点赞记录
     *
     * @param qo 查询条件
     * @return 结果
     */
    int deleteKbsDocumentCommentLikeByComment(KbsDocumentCommentLikeQo qo);
}
