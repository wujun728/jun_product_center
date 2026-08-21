package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库文档详情对象 t_kbs_document_info
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsDocumentInfo extends BaseEntity {
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
     * 文档内容
     */
    private String content;

    /**
     * 路径
     */
    private String url;

    /**
     * 标签
     */
    private String tag;

    /**
     * 封面图片ID
     */
    private String picId;

    /**
     * 摘要
     */
    private String summary;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("docId", getDocId())
                .append("content", getContent())
                .append("url", getUrl())
                .append("tag", getTag())
                .append("picId", getPicId())
                .append("summary", getSummary())
                .toString();
    }
}
