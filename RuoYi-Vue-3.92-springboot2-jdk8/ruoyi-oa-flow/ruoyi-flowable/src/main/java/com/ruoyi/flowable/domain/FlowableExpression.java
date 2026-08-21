package com.ruoyi.flowable.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程达式对象 sys_expression
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowableExpression extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 表单主键
     */
    private String id;

    /**
     * 表达式名称
     */
    @Excel(name = "表达式名称")
    private String name;

    /**
     * 表达式内容
     */
    @Excel(name = "表达式内容")
    private String expression;

    /**
     * 表达式类型
     */
    @Excel(name = "表达式类型")
    private String dataType;

    /**
     * 状态
     */
    private Integer status;

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
                .append("name", getName())
                .append("expression", getExpression())
                .append("dataType", getDataType())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("status", getStatus())
                .append("remark", getRemark())
                .toString();
    }
}
