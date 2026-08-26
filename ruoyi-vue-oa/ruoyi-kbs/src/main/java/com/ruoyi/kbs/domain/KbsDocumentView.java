package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库文档浏览对象 t_kbs_document_view
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsDocumentView extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 文档ID
     */
    @Excel(name = "文档ID")
    private String docId;

    /**
     * 浏览人ID
     */
    @Excel(name = "浏览人ID")
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("docId", getDocId())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .toString();
    }
}
