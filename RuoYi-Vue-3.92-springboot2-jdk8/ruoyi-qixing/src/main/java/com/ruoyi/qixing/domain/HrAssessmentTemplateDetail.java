package com.ruoyi.qixing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考核模板明细对象 hr_assessment_template_detail
 *
 * @author template
 * @date 2026-06-11
 */
public class HrAssessmentTemplateDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 序号 */
    @Excel(name = "序号")
    private Long sortno;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String teamplateId;

    /** 考核模板名称 */
    @Excel(name = "考核模板名称")
    private String refTeamplateName;

    /** 评分项目(大类) */
    @Excel(name = "评分项目(大类)")
    private String assType;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String assAttrType;

    /** 考核内容 */
    @Excel(name = "考核内容")
    private String assAttrName;

    /** 分值  */
    @Excel(name = "分值 ")
    private Long attrScore;

    /** 评分标准 */
    @Excel(name = "评分标准")
    private String attrDesc;

    /** 考核方式 */
    @Excel(name = "考核方式")
    private String assAttrDesc;

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
    public void setSortno(Long sortno)
    {
        this.sortno = sortno;
    }

    public Long getSortno()
    {
        return sortno;
    }
    public void setTeamplateId(String teamplateId)
    {
        this.teamplateId = teamplateId;
    }

    public String getTeamplateId()
    {
        return teamplateId;
    }
    public void setRefTeamplateName(String refTeamplateName)
    {
        this.refTeamplateName = refTeamplateName;
    }

    public String getRefTeamplateName()
    {
        return refTeamplateName;
    }
    public void setAssType(String assType)
    {
        this.assType = assType;
    }

    public String getAssType()
    {
        return assType;
    }
    public void setAssAttrType(String assAttrType)
    {
        this.assAttrType = assAttrType;
    }

    public String getAssAttrType()
    {
        return assAttrType;
    }
    public void setAssAttrName(String assAttrName)
    {
        this.assAttrName = assAttrName;
    }

    public String getAssAttrName()
    {
        return assAttrName;
    }
    public void setAttrScore(Long attrScore)
    {
        this.attrScore = attrScore;
    }

    public Long getAttrScore()
    {
        return attrScore;
    }
    public void setAttrDesc(String attrDesc)
    {
        this.attrDesc = attrDesc;
    }

    public String getAttrDesc()
    {
        return attrDesc;
    }
    public void setAssAttrDesc(String assAttrDesc)
    {
        this.assAttrDesc = assAttrDesc;
    }

    public String getAssAttrDesc()
    {
        return assAttrDesc;
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
            .append("sortno", getSortno())
            .append("teamplateId", getTeamplateId())
            .append("refTeamplateName", getRefTeamplateName())
            .append("assType", getAssType())
            .append("assAttrType", getAssAttrType())
            .append("assAttrName", getAssAttrName())
            .append("attrScore", getAttrScore())
            .append("attrDesc", getAttrDesc())
            .append("assAttrDesc", getAssAttrDesc())
            .append("orderId", getOrderId())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
