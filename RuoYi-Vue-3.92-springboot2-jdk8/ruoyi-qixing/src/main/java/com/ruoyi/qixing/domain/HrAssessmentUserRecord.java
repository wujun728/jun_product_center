package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考核记录对象 hr_assessment_user_record
 *
 * @author template
 * @date 2026-06-11
 */
public class HrAssessmentUserRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refUsercode;

    /** 考核人 */
    @Excel(name = "考核人")
    private String refUsername;

    /** 考核模板 */
    @Excel(name = "考核模板")
    private String refTeamplateName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String recordName;

    /** 考核自评分值 */
    @Excel(name = "考核自评分值")
    private Long score;

    /** 考核终评分值 */
    @Excel(name = "考核终评分值")
    private Long score1;

    /** 考核终评人 */
    @Excel(name = "考核终评人")
    private String score1Username;

    /** 考核人终评详细评价 */
    @Excel(name = "考核人终评详细评价")
    private String score1Desc;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String teamplateId;

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
    public void setRefUsercode(String refUsercode)
    {
        this.refUsercode = refUsercode;
    }

    public String getRefUsercode()
    {
        return refUsercode;
    }
    public void setRefUsername(String refUsername)
    {
        this.refUsername = refUsername;
    }

    public String getRefUsername()
    {
        return refUsername;
    }
    public void setRefTeamplateName(String refTeamplateName)
    {
        this.refTeamplateName = refTeamplateName;
    }

    public String getRefTeamplateName()
    {
        return refTeamplateName;
    }
    public void setRecordName(String recordName)
    {
        this.recordName = recordName;
    }

    public String getRecordName()
    {
        return recordName;
    }
    public void setScore(Long score)
    {
        this.score = score;
    }

    public Long getScore()
    {
        return score;
    }
    public void setScore1(Long score1)
    {
        this.score1 = score1;
    }

    public Long getScore1()
    {
        return score1;
    }
    public void setScore1Username(String score1Username)
    {
        this.score1Username = score1Username;
    }

    public String getScore1Username()
    {
        return score1Username;
    }
    public void setScore1Desc(String score1Desc)
    {
        this.score1Desc = score1Desc;
    }

    public String getScore1Desc()
    {
        return score1Desc;
    }
    public void setTeamplateId(String teamplateId)
    {
        this.teamplateId = teamplateId;
    }

    public String getTeamplateId()
    {
        return teamplateId;
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
            .append("refUsercode", getRefUsercode())
            .append("refUsername", getRefUsername())
            .append("refTeamplateName", getRefTeamplateName())
            .append("recordName", getRecordName())
            .append("score", getScore())
            .append("score1", getScore1())
            .append("score1Username", getScore1Username())
            .append("score1Desc", getScore1Desc())
            .append("teamplateId", getTeamplateId())
            .append("orderId", getOrderId())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
