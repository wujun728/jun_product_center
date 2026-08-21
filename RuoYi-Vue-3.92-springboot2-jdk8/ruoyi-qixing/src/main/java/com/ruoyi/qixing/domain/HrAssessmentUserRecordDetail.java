package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考核记录明细对象 hr_assessment_user_record_detail
 *
 * @author template
 * @date 2026-06-11
 */
public class HrAssessmentUserRecordDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String usercode;

    /** 考核人 */
    @Excel(name = "考核人")
    private String refUsername;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refTemplateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refRecordId;

    /** 考核模板名称 */
    @Excel(name = "考核模板名称")
    private String refTemplateName;

    /** 考核指标 */
    @Excel(name = "考核指标")
    private Long sortno;

    /** 自评分值 */
    @Excel(name = "自评分值")
    private Long score;

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
    public void setUsercode(String usercode)
    {
        this.usercode = usercode;
    }

    public String getUsercode()
    {
        return usercode;
    }
    public void setRefUsername(String refUsername)
    {
        this.refUsername = refUsername;
    }

    public String getRefUsername()
    {
        return refUsername;
    }
    public void setRefTemplateId(String refTemplateId)
    {
        this.refTemplateId = refTemplateId;
    }

    public String getRefTemplateId()
    {
        return refTemplateId;
    }
    public void setRefRecordId(String refRecordId)
    {
        this.refRecordId = refRecordId;
    }

    public String getRefRecordId()
    {
        return refRecordId;
    }
    public void setRefTemplateName(String refTemplateName)
    {
        this.refTemplateName = refTemplateName;
    }

    public String getRefTemplateName()
    {
        return refTemplateName;
    }
    public void setSortno(Long sortno)
    {
        this.sortno = sortno;
    }

    public Long getSortno()
    {
        return sortno;
    }
    public void setScore(Long score)
    {
        this.score = score;
    }

    public Long getScore()
    {
        return score;
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
            .append("usercode", getUsercode())
            .append("refUsername", getRefUsername())
            .append("refTemplateId", getRefTemplateId())
            .append("refRecordId", getRefRecordId())
            .append("refTemplateName", getRefTemplateName())
            .append("sortno", getSortno())
            .append("score", getScore())
            .append("orderId", getOrderId())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
