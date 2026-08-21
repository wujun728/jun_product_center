package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目报告对象 pj_project_report
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告ID */
    private String id;

    /** 报告名称 */
    @Excel(name = "报告名称")
    private String reportName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** 报告类型 */
    @Excel(name = "报告类型")
    private String dictReportType;

    /** 报告详细描述 */
    @Excel(name = "报告详细描述")
    private String reportDetail;

    /** 客户原始述求 */
    @Excel(name = "客户原始述求")
    private String customerReq;

    /** 报告输出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报告输出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reportTime;

    /** 报告输出责任人 */
    @Excel(name = "报告输出责任人")
    private String refReportBy;

    /** 报告完成天数 */
    @Excel(name = "报告完成天数")
    private Long reportFinashDays;

    /** 报告状态 */
    @Excel(name = "报告状态")
    private String dictReportStatus;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 报告文号 */
    @Excel(name = "报告文号")
    private String reportnumCode;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createId;

    /** 更新人 */
    @Excel(name = "更新人")
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
    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

    public String getReportName()
    {
        return reportName;
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
    public void setDictReportType(String dictReportType)
    {
        this.dictReportType = dictReportType;
    }

    public String getDictReportType()
    {
        return dictReportType;
    }
    public void setReportDetail(String reportDetail)
    {
        this.reportDetail = reportDetail;
    }

    public String getReportDetail()
    {
        return reportDetail;
    }
    public void setCustomerReq(String customerReq)
    {
        this.customerReq = customerReq;
    }

    public String getCustomerReq()
    {
        return customerReq;
    }
    public void setReportTime(Date reportTime)
    {
        this.reportTime = reportTime;
    }

    public Date getReportTime()
    {
        return reportTime;
    }
    public void setRefReportBy(String refReportBy)
    {
        this.refReportBy = refReportBy;
    }

    public String getRefReportBy()
    {
        return refReportBy;
    }
    public void setReportFinashDays(Long reportFinashDays)
    {
        this.reportFinashDays = reportFinashDays;
    }

    public Long getReportFinashDays()
    {
        return reportFinashDays;
    }
    public void setDictReportStatus(String dictReportStatus)
    {
        this.dictReportStatus = dictReportStatus;
    }

    public String getDictReportStatus()
    {
        return dictReportStatus;
    }
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public String getCreator()
    {
        return creator;
    }
    public void setReportnumCode(String reportnumCode)
    {
        this.reportnumCode = reportnumCode;
    }

    public String getReportnumCode()
    {
        return reportnumCode;
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
            .append("reportName", getReportName())
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("dictReportType", getDictReportType())
            .append("reportDetail", getReportDetail())
            .append("customerReq", getCustomerReq())
            .append("reportTime", getReportTime())
            .append("refReportBy", getRefReportBy())
            .append("reportFinashDays", getReportFinashDays())
            .append("dictReportStatus", getDictReportStatus())
            .append("creator", getCreator())
            .append("reportnumCode", getReportnumCode())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
