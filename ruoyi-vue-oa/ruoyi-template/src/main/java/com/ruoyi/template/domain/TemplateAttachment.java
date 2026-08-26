package com.ruoyi.template.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 附件配置对象 t_template_attachment
 * 
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TemplateAttachment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String name;

    /** 限制大小（M） */
    @Excel(name = "限制大小", readConverterExp = "M=")
    private String limitSize;

    /** 附件限制类型 */
    @Excel(name = "附件限制类型")
    private String limitType;

    /** 提示内容 */
    @Excel(name = "提示内容")
    private String tipContent;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String templateId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createId;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("limitSize", getLimitSize())
            .append("limitType", getLimitType())
            .append("tipContent", getTipContent())
            .append("templateId", getTemplateId())
            .append("createId", getCreateId())
            .append("createTime", getCreateTime())
            .append("updateId", getUpdateId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
