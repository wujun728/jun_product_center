package com.ruoyi.kbs.domain.vo;

import com.ruoyi.kbs.domain.KbsDocumentComment;
import lombok.Data;

import java.util.List;

/**
 * 知识库文档评论对象 t_kbs_document_comment
 *
 * @author wocurr.com
 */
@Data
public class KbsDocumentCommentVo extends KbsDocumentComment {

    /**
     * 是否点赞标识
     */
    private Boolean likeFlag;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     * 评论人头像
     */
    private String userAvatar;

    /**
     * 回复人头像
     */
    private String replierAvatar;

    /**
     * 子评论
     */
    private List<KbsDocumentCommentVo> children;
}
