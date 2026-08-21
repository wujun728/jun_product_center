package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目日报周报对象 pj_project_daily
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectDaily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 日报标题 */
    @Excel(name = "日报标题")
    private String planName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** 日报周报 */
    @Excel(name = "日报周报")
    private String dictDailyType;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dailyDate;

    /** 工作内容描述 */
    @Excel(name = "工作内容描述")
    private String dailyDetail;

    /** 投入工时 */
    @Excel(name = "投入工时")
    private Long costTime;

    /** 风险级别 */
    @Excel(name = "风险级别")
    private String dictRisk;

    /** 是否求助 */
    @Excel(name = "是否求助")
    private String dictAskHelp;

    /** 是否延迟 */
    @Excel(name = "是否延迟")
    private String dictIsDelay;

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
    public void setPlanName(String planName)
    {
        this.planName = planName;
    }

    public String getPlanName()
    {
        return planName;
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
    public void setDictDailyType(String dictDailyType)
    {
        this.dictDailyType = dictDailyType;
    }

    public String getDictDailyType()
    {
        return dictDailyType;
    }
    public void setDailyDate(Date dailyDate)
    {
        this.dailyDate = dailyDate;
    }

    public Date getDailyDate()
    {
        return dailyDate;
    }
    public void setDailyDetail(String dailyDetail)
    {
        this.dailyDetail = dailyDetail;
    }

    public String getDailyDetail()
    {
        return dailyDetail;
    }
    public void setCostTime(Long costTime)
    {
        this.costTime = costTime;
    }

    public Long getCostTime()
    {
        return costTime;
    }
    public void setDictRisk(String dictRisk)
    {
        this.dictRisk = dictRisk;
    }

    public String getDictRisk()
    {
        return dictRisk;
    }
    public void setDictAskHelp(String dictAskHelp)
    {
        this.dictAskHelp = dictAskHelp;
    }

    public String getDictAskHelp()
    {
        return dictAskHelp;
    }
    public void setDictIsDelay(String dictIsDelay)
    {
        this.dictIsDelay = dictIsDelay;
    }

    public String getDictIsDelay()
    {
        return dictIsDelay;
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
            .append("planName", getPlanName())
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("dictDailyType", getDictDailyType())
            .append("dailyDate", getDailyDate())
            .append("dailyDetail", getDailyDetail())
            .append("costTime", getCostTime())
            .append("dictRisk", getDictRisk())
            .append("dictAskHelp", getDictAskHelp())
            .append("dictIsDelay", getDictIsDelay())
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
