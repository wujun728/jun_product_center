package com.ruoyi.kbs.service;

import com.ruoyi.kbs.domain.KbsDocumentComment;
import com.ruoyi.kbs.domain.vo.KbsDocumentCommentVo;

import java.util.List;

/**
 * 知识库文档评论Service接口
 *
 * @author wocurr.com
 */
public interface IKbsDocumentCommentService {

    /**
     * 查询知识库文档评论列表
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 知识库文档评论集合
     */
    public List<KbsDocumentCommentVo> listKbsDocumentComment(KbsDocumentComment kbsDocumentComment);

    /**
     * 查询子评论列表
     * @param kbsDocumentComment
     * @return
     */
    public List<KbsDocumentCommentVo> listChildKbsDocumentComment(KbsDocumentComment kbsDocumentComment);

    /**
     * 新增知识库文档评论
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 结果
     */
    public String saveKbsDocumentComment(KbsDocumentComment kbsDocumentComment);

    /**
     * 修改知识库文档评论
     *
     * @param kbsDocumentComment 知识库文档评论
     * @return 结果
     */
    public int updateKbsDocumentComment(KbsDocumentComment kbsDocumentComment);

    /**
     * 批量删除知识库文档评论
     *
     * @param ids 需要删除的知识库文档评论主键集合
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
}
