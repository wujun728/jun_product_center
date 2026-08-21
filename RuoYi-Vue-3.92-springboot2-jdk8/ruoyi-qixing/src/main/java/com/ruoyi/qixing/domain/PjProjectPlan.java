package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目计划对象 pj_project_plan
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** 项目计划标题 */
    @Excel(name = "项目计划标题")
    private String planName;

    /** 项目计划详细描述 */
    @Excel(name = "项目计划详细描述")
    private String planDetail;

    /** 项目计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planTimeStart;

    /** 项目计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planTimeEnd;

    /** 工期(人天) */
    @Excel(name = "工期(人天)")
    private String planDates;

    /** 项目计划交付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目计划交付日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planGivenTime;

    /** 项目计划完成天数 */
    @Excel(name = "项目计划完成天数")
    private Long planFinashDays;

    /** 项目实际完成天数 */
    @Excel(name = "项目实际完成天数")
    private Long planFinashDays2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String creator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updateId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderId;

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
    public void setRefProjectCode(String refProjectCode)
    {
        this.refProjectCode = refProjectCode;
    }

    public String getRefProjectCode()
    {
        return refProjectCode;
    }
    public void setRefProjectName(String refProjectName)
    {
        this.refProjectName = refProjectName;
    }

    public String getRefProjectName()
    {
        return refProjectName;
    }
    public void setPlanName(String planName)
    {
        this.planName = planName;
    }

    public String getPlanName()
    {
        return planName;
    }
    public void setPlanDetail(String planDetail)
    {
        this.planDetail = planDetail;
    }

    public String getPlanDetail()
    {
        return planDetail;
    }
    public void setPlanTimeStart(Date planTimeStart)
    {
        this.planTimeStart = planTimeStart;
    }

    public Date getPlanTimeStart()
    {
        return planTimeStart;
    }
    public void setPlanTimeEnd(Date planTimeEnd)
    {
        this.planTimeEnd = planTimeEnd;
    }

    public Date getPlanTimeEnd()
    {
        return planTimeEnd;
    }
    public void setPlanDates(String planDates)
    {
        this.planDates = planDates;
    }

    public String getPlanDates()
    {
        return planDates;
    }
    public void setPlanGivenTime(Date planGivenTime)
    {
        this.planGivenTime = planGivenTime;
    }

    public Date getPlanGivenTime()
    {
        return planGivenTime;
    }
    public void setPlanFinashDays(Long planFinashDays)
    {
        this.planFinashDays = planFinashDays;
    }

    public Long getPlanFinashDays()
    {
        return planFinashDays;
    }
    public void setPlanFinashDays2(Long planFinashDays2)
    {
        this.planFinashDays2 = planFinashDays2;
    }

    public Long getPlanFinashDays2()
    {
        return planFinashDays2;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
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
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
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
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("planName", getPlanName())
            .append("planDetail", getPlanDetail())
            .append("planTimeStart", getPlanTimeStart())
            .append("planTimeEnd", getPlanTimeEnd())
            .append("planDates", getPlanDates())
            .append("planGivenTime", getPlanGivenTime())
            .append("planFinashDays", getPlanFinashDays())
            .append("planFinashDays2", getPlanFinashDays2())
            .append("remark", getRemark())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
