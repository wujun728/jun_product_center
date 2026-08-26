package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库文档评论点赞对象 t_kbs_document_comment_like
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsDocumentCommentLike extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 评论ID
     */
    private String commentId;

    /**
     * 创建人ID
     */
    private String createId;

    /**
     * 更新人ID
     */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("commentId", getCommentId())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
