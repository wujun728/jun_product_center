package com.ruoyi.qixing.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目开票对象 pj_project_invoice
 *
 * @author template
 * @date 2026-06-11
 */
public class PjProjectInvoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refCustomerCode;

    /** 开票客户 */
    @Excel(name = "开票客户")
    private String refCustomerName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String refProjectCode;

    /** 开票项目 */
    @Excel(name = "开票项目")
    private String refProjectName;

    /** 开票类型 */
    @Excel(name = "开票类型")
    private String dictType;

    /** 开票单位名称 */
    @Excel(name = "开票单位名称")
    private String invoiceCompanyName;

    /** 纳税人识别号 */
    @Excel(name = "纳税人识别号")
    private String invoiceTaxNo;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 开户行 */
    @Excel(name = "开户行")
    private String dictBank;

    /** 开户行账号 */
    @Excel(name = "开户行账号")
    private String bankCardNo;

    /** 开票金额 */
    @Excel(name = "开票金额")
    private BigDecimal invoiceMoney;

    /** 开票审批状态 */
    @Excel(name = "开票审批状态")
    private String dictInvoiceState;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String dictWfState;

    /** 开票人 */
    @Excel(name = "开票人")
    private String refInvoiceMan;

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
    public void setRefCustomerCode(String refCustomerCode)
    {
        this.refCustomerCode = refCustomerCode;
    }

    public String getRefCustomerCode()
    {
        return refCustomerCode;
    }
    public void setRefCustomerName(String refCustomerName)
    {
        this.refCustomerName = refCustomerName;
    }

    public String getRefCustomerName()
    {
        return refCustomerName;
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
    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getDictType()
    {
        return dictType;
    }
    public void setInvoiceCompanyName(String invoiceCompanyName)
    {
        this.invoiceCompanyName = invoiceCompanyName;
    }

    public String getInvoiceCompanyName()
    {
        return invoiceCompanyName;
    }
    public void setInvoiceTaxNo(String invoiceTaxNo)
    {
        this.invoiceTaxNo = invoiceTaxNo;
    }

    public String getInvoiceTaxNo()
    {
        return invoiceTaxNo;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTelephone()
    {
        return telephone;
    }
    public void setDictBank(String dictBank)
    {
        this.dictBank = dictBank;
    }

    public String getDictBank()
    {
        return dictBank;
    }
    public void setBankCardNo(String bankCardNo)
    {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardNo()
    {
        return bankCardNo;
    }
    public void setInvoiceMoney(BigDecimal invoiceMoney)
    {
        this.invoiceMoney = invoiceMoney;
    }

    public BigDecimal getInvoiceMoney()
    {
        return invoiceMoney;
    }
    public void setDictInvoiceState(String dictInvoiceState)
    {
        this.dictInvoiceState = dictInvoiceState;
    }

    public String getDictInvoiceState()
    {
        return dictInvoiceState;
    }
    public void setDictWfState(String dictWfState)
    {
        this.dictWfState = dictWfState;
    }

    public String getDictWfState()
    {
        return dictWfState;
    }
    public void setRefInvoiceMan(String refInvoiceMan)
    {
        this.refInvoiceMan = refInvoiceMan;
    }

    public String getRefInvoiceMan()
    {
        return refInvoiceMan;
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
            .append("refCustomerCode", getRefCustomerCode())
            .append("refCustomerName", getRefCustomerName())
            .append("refProjectCode", getRefProjectCode())
            .append("refProjectName", getRefProjectName())
            .append("dictType", getDictType())
            .append("invoiceCompanyName", getInvoiceCompanyName())
            .append("invoiceTaxNo", getInvoiceTaxNo())
            .append("address", getAddress())
            .append("telephone", getTelephone())
            .append("dictBank", getDictBank())
            .append("bankCardNo", getBankCardNo())
            .append("invoiceMoney", getInvoiceMoney())
            .append("dictInvoiceState", getDictInvoiceState())
            .append("dictWfState", getDictWfState())
            .append("refInvoiceMan", getRefInvoiceMan())
            .append("remark", getRemark())
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
