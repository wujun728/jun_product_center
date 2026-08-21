package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考核模板对象 hr_assessment_template
 *
 * @author template
 * @date 2026-06-11
 */
public class HrAssessmentTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 考核模板名称 */
    @Excel(name = "考核模板名称")
    private String teamplateName;

    /** 考核总分值 */
    @Excel(name = "考核总分值")
    private Long scoreTotal;

    /** 适用范围 */
    @Excel(name = "适用范围")
    private String forUsePeoples;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long orderStatus;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setTeamplateName(String teamplateName)
    {
        this.teamplateName = teamplateName;
    }

    public String getTeamplateName()
    {
        return teamplateName;
    }
    public void setScoreTotal(Long scoreTotal)
    {
        this.scoreTotal = scoreTotal;
    }

    public Long getScoreTotal()
    {
        return scoreTotal;
    }
    public void setForUsePeoples(String forUsePeoples)
    {
        this.forUsePeoples = forUsePeoples;
    }

    public String getForUsePeoples()
    {
        return forUsePeoples;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public String getCreateId()
    {
        return createId;
    }
    public void setUpdateId(String updateId)
    {
        this.updateId = updateId;
    }

    public String getUpdateId()
    {
        return updateId;
    }
    public void setOrderStatus(Long orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus()
    {
        return orderStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teamplateName", getTeamplateName())
            .append("scoreTotal", getScoreTotal())
            .append("forUsePeoples", getForUsePeoples())
            .append("remark", getRemark())
            .append("orderId", getOrderId())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
