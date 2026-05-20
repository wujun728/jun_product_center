package com.ruoyi.kbs.domain.qo;

import lombok.Data;

/**
 * 知识库文档评论点赞对象
 *
 * @author wocurr.com
 */
@Data
public class KbsDocumentCommentLikeQo {

    /**
     * 评论ID
     */
    private String commentId;

    /**
     * 用户ID
     */
    private String userId;
}
