package com.ruoyi.kbs.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.kbs.domain.KbsDocumentCommentLike;
import com.ruoyi.kbs.domain.qo.KbsDocumentCommentLikeQo;
import com.ruoyi.kbs.mapper.KbsDocumentCommentLikeMapper;
import com.ruoyi.kbs.service.IKbsDocumentCommentLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 知识库文档评论点赞Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class KbsDocumentCommentLikeServiceImpl implements IKbsDocumentCommentLikeService {
    @Autowired
    private KbsDocumentCommentLikeMapper kbsDocumentCommentLikeMapper;

    /**
     * 新增知识库文档评论点赞
     *
     * @param kbsDocumentCommentLike 知识库文档评论点赞
     * @return 结果
     */
    @Override
    public int saveKbsDocumentCommentLike(KbsDocumentCommentLike kbsDocumentCommentLike) {
        kbsDocumentCommentLike.setId(IdUtils.fastSimpleUUID());
        kbsDocumentCommentLike.setCreateId(SecurityUtils.getUserId());
        kbsDocumentCommentLike.setCreateTime(DateUtils.getNowDate());
        return kbsDocumentCommentLikeMapper.insertKbsDocumentCommentLike(kbsDocumentCommentLike);
    }

    /**
     * 根据评论ID集合查询点赞记录
     *
     * @param commentIds 评论ID集合
     * @return 点赞记录
     */
    @Override
    public List<KbsDocumentCommentLike> listKbsDocumentCommentLikeByCommentIds(List<String> commentIds) {
        return kbsDocumentCommentLikeMapper.selectKbsDocumentCommentLikeByCommentIds(commentIds);
    }

    /**
     * 根据评论ID删除点赞记录
     *
     * @param commentId
     * @return 结果
     */
    @Override
    public int deleteKbsDocumentCommentLikeByCommentId(String commentId) {
        KbsDocumentCommentLikeQo qo = new KbsDocumentCommentLikeQo();
        qo.setCommentId(commentId);
        qo.setUserId(SecurityUtils.getUserId());
        return kbsDocumentCommentLikeMapper.deleteKbsDocumentCommentLikeByComment(qo);
    }
}
