package com.ruoyi.workflow.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 最新使用模板对象 t_workflow_rece_template
 *
 * @author wocurr.com
 */
@Data
public class ReceTemplate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 模板ID
     */
    @Excel(name = "模板ID")
    private String templateId;

    /**
     * 使用人员ID
     */
    @Excel(name = "使用人员ID")
    private String userId;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createId;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("templateId", getTemplateId())
                .append("userId", getUserId())
                .append("createTime", getCreateTime())
                .append("createId", getCreateId())
                .toString();
    }
}
