package com.ruoyi.kbs.mapper;

import java.util.List;

import com.ruoyi.kbs.domain.KbsDocumentComment;
import com.ruoyi.kbs.domain.qo.KbsDocumentCommentUpdateQo;

/**
 * 知识库文档评论Mapper接口
 *
 * @author wocurr.com
 */
public interface KbsDocumentCommentMapper {
    /**
     * 查询知识库文档评论
     *
     * @param id 知识库文档评论主键
     * @return 知识库文档评论
     */
    public KbsDocumentComment selectKbsDocumentCommentById(String id);

    /**
     * 查询知识库文档评论列表
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 知识库文档评论集合
     */
    public List<KbsDocumentComment> selectKbsDocumentCommentList(KbsDocumentComment kbsDocumentComment);

    /**
     * 新增知识库文档评论
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 结果
     */
    public int insertKbsDocumentComment(KbsDocumentComment kbsDocumentComment);

    /**
     * 修改知识库文档评论
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 结果
     */
    public int updateKbsDocumentComment(KbsDocumentComment kbsDocumentComment);

    /**
     * 删除知识库文档评论
     *
     * @param id 知识库文档评论主键
     * @return 结果
     */
    public int deleteKbsDocumentCommentById(String id);

    /**
     * 批量删除知识库文档评论
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteKbsDocumentCommentByIds(String[] ids);

    /**
     * 统计文档的评论数量
     *
     * @param docId 文档ID
     * @return 评论数量
     */
    Long statDocumentCommentNum(String docId);

    /**
     * 根据父评论ID列表查询评论信息
     *
     * @param commentIds 评论ID列表
     * @return 评论信息
     */
    List<KbsDocumentComment> selectKbsDocumentCommentByParentIds(List<String> commentIds);

    /**
     * 根据根评论ID列表查询评论信息
     * @param kbsDocumentComment
     * @return
     */
    List<KbsDocumentComment> selectKbsDocumentCommentByRootParentId(KbsDocumentComment kbsDocumentComment);

    /**
     * 批量更新删除标识状态
     *
     * @param qo
     * @return
     */
    int updateDelFlagByIds(KbsDocumentCommentUpdateQo qo);
}
