package com.ruoyi.kbs.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 知识库文档模板对象 t_kbs_document_template
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class KbsDocumentTemplate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 模板名称
     */
    @Excel(name = "模板名称")
    private String name;

    /**
     * 模板内容
     */
    @Excel(name = "模板内容")
    private String content;

    /**
     * 删除标识，0-未删除，1-已删除
     */
    private String delFlag;

    /**
     * 创建人ID
     */
    @Excel(name = "创建人ID")
    private String createId;

    /**
     * 更新人ID
     */
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("content", getContent())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
