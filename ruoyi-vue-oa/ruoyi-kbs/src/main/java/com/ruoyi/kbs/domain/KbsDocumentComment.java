package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库文档评论对象 t_kbs_document_comment
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsDocumentComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 文档ID
     */
    private String docId;

    /**
     * 评论
     */
    private String comment;

    /**
     * 根父评论ID
     */
    private String rootParentId;

    /**
     * 父评论ID
     */
    private String parentId;

    /**
     * 回复人ID
     */
    private String replierId;

    /**
     * 回复人名称
     */
    private String replier;

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;

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
                .append("docId", getDocId())
                .append("comment", getComment())
                .append("rootParentId", getRootParentId())
                .append("parentId", getParentId())
                .append("replierId", getReplierId())
                .append("replier", getReplier())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
