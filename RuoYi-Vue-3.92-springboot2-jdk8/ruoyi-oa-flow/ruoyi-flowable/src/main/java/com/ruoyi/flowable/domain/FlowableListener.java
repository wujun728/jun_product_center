package com.ruoyi.flowable.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程监听对象 sys_listener
 *
 * @author wocurr.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FlowableListener extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 表单主键
     */
    private String id;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 监听类型
     */
    @Excel(name = "监听类型")
    private String type;

    /**
     * 事件类型
     */
    @Excel(name = "事件类型")
    private String eventType;

    /**
     * 值类型
     */
    @Excel(name = "值类型")
    private String valueType;

    /**
     * 执行内容
     */
    @Excel(name = "执行内容")
    private String value;

    /**
     * 状态
     */
    @Excel(name = "状态")
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
                .append("type", getType())
                .append("eventType", getEventType())
                .append("valueType", getValueType())
                .append("value", getValue())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("status", getStatus())
                .append("remark", getRemark())
                .toString();
    }
}
