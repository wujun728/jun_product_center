package com.ruoyi.qixing.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 业务约定书对象 pj_contract
 *
 * @author template
 * @date 2026-06-11
 */
public class PjContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refidProjectCodeHide;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String refProjectName;

    /** 合同状态 */
    @Excel(name = "合同状态")
    private String dictContractState;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private String dictContractType;

    /** 合同号 */
    @Excel(name = "合同号")
    private String contractNo;

    /** 合同标题 */
    @Excel(name = "合同标题")
    private String contractName;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signTime;

    /** 报告数量 */
    @Excel(name = "报告数量")
    private Long reportCount;

    /** 报告详细描述 */
    @Excel(name = "报告详细描述")
    private String reportDetail;

    /** 约定书金额 */
    @Excel(name = "约定书金额")
    private BigDecimal contractMoney;

    /** 签约/承诺人 */
    @Excel(name = "签约/承诺人")
    private String signBy;

    /** 执业天数 */
    @Excel(name = "执业天数")
    private Long contractDays;

    /** 约定书详细描述 */
    @Excel(name = "约定书详细描述")
    private String contractDetails;

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
    private Long deleted;

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
    public void setRefidProjectCodeHide(String refidProjectCodeHide)
    {
        this.refidProjectCodeHide = refidProjectCodeHide;
    }

    public String getRefidProjectCodeHide()
    {
        return refidProjectCodeHide;
    }
    public void setRefProjectName(String refProjectName)
    {
        this.refProjectName = refProjectName;
    }

    public String getRefProjectName()
    {
        return refProjectName;
    }
    public void setDictContractState(String dictContractState)
    {
        this.dictContractState = dictContractState;
    }

    public String getDictContractState()
    {
        return dictContractState;
    }
    public void setDictContractType(String dictContractType)
    {
        this.dictContractType = dictContractType;
    }

    public String getDictContractType()
    {
        return dictContractType;
    }
    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }

    public String getContractNo()
    {
        return contractNo;
    }
    public void setContractName(String contractName)
    {
        this.contractName = contractName;
    }

    public String getContractName()
    {
        return contractName;
    }
    public void setSignTime(Date signTime)
    {
        this.signTime = signTime;
    }

    public Date getSignTime()
    {
        return signTime;
    }
    public void setReportCount(Long reportCount)
    {
        this.reportCount = reportCount;
    }

    public Long getReportCount()
    {
        return reportCount;
    }
    public void setReportDetail(String reportDetail)
    {
        this.reportDetail = reportDetail;
    }

    public String getReportDetail()
    {
        return reportDetail;
    }
    public void setContractMoney(BigDecimal contractMoney)
    {
        this.contractMoney = contractMoney;
    }

    public BigDecimal getContractMoney()
    {
        return contractMoney;
    }
    public void setSignBy(String signBy)
    {
        this.signBy = signBy;
    }

    public String getSignBy()
    {
        return signBy;
    }
    public void setContractDays(Long contractDays)
    {
        this.contractDays = contractDays;
    }

    public Long getContractDays()
    {
        return contractDays;
    }
    public void setContractDetails(String contractDetails)
    {
        this.contractDetails = contractDetails;
    }

    public String getContractDetails()
    {
        return contractDetails;
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
    public void setDeleted(Long deleted)
    {
        this.deleted = deleted;
    }

    public Long getDeleted()
    {
        return deleted;
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
            .append("refidProjectCodeHide", getRefidProjectCodeHide())
            .append("refProjectName", getRefProjectName())
            .append("dictContractState", getDictContractState())
            .append("dictContractType", getDictContractType())
            .append("contractNo", getContractNo())
            .append("contractName", getContractName())
            .append("signTime", getSignTime())
            .append("reportCount", getReportCount())
            .append("reportDetail", getReportDetail())
            .append("contractMoney", getContractMoney())
            .append("signBy", getSignBy())
            .append("contractDays", getContractDays())
            .append("contractDetails", getContractDetails())
            .append("remark", getRemark())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("deleted", getDeleted())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .toString();
    }
}
