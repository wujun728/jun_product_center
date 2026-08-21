package com.ruoyi.qixing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户信息对象 pj_customer
 *
 * @author template
 * @date 2026-06-11
 */
public class PjCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private String id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String customerCode;

    /** 客户类型 */
    @Excel(name = "客户类型")
    private String dictCustomerType;

    /** 公司电话 */
    @Excel(name = "公司电话")
    private String phone;

    /** 财务经理 */
    @Excel(name = "财务经理")
    private String financialManager;

    /** 财务经理电话 */
    @Excel(name = "财务经理电话")
    private String fmPhone;

    /** 客户联系信息 */
    @Excel(name = "客户联系信息")
    private String customerLinks;

    /** 客户营业信息 */
    @Excel(name = "客户营业信息")
    private String customerBusiness;

    /** 营业执照 */
    @Excel(name = "营业执照")
    private String businessLicense;

    /** 公司全称 */
    @Excel(name = "公司全称")
    private String conpanyName;

    /** 营业执照编码 */
    @Excel(name = "营业执照编码")
    private String businessLicenseNo;

    /** 法定代表人 */
    @Excel(name = "法定代表人")
    private String legalRepresentative;

    /** 注册资本 */
    @Excel(name = "注册资本")
    private String registeredCapital;

    /** 客户地址 */
    @Excel(name = "客户地址")
    private String customerAddress;

    /** 公司类型 */
    @Excel(name = "公司类型")
    private String dictCompanyType;

    /** 公司始建于 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "公司始建于", width = 30, dateFormat = "yyyy-MM-dd")
    private Date companyBegin;

    /** 实收资本 */
    @Excel(name = "实收资本")
    private String paiclupCapital;

    /** 经营范围 */
    @Excel(name = "经营范围")
    private String businessScope;

    /** 营业期限开始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "营业期限开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date businessTermStart;

    /** 营业期限结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "营业期限结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date businessTermEnd;

    /** 登记机关 */
    @Excel(name = "登记机关")
    private String registrationAuthority;

    /** 执照发放日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "执照发放日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date licensingDay;

    /** 客户开票信息 */
    @Excel(name = "客户开票信息")
    private String customerBillingInformation;

    /** 纳税人识别号 */
    @Excel(name = "纳税人识别号")
    private String taxRegNumber;

    /** 税号 */
    @Excel(name = "税号")
    private String dutyParagraph;

    /** 开户行 */
    @Excel(name = "开户行")
    private String openingBank;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNumber;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String companyAddress;

    /** 是否老客户 */
    @Excel(name = "是否老客户")
    private String dictIsOldCustomer;

    /** 客户对接责任人 */
    @Excel(name = "客户对接责任人")
    private String customerRespPerson;

    /** 客户关系维护责任人 */
    @Excel(name = "客户关系维护责任人")
    private String customerRelationPerson;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String creator;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createId;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateId;

    /** 是否弃用 */
    @Excel(name = "是否弃用")
    private Long deleted;

    /** 财务经理1 */
    @Excel(name = "财务经理1")
    private String financialManager1;

    /** 财务经理电话1 */
    @Excel(name = "财务经理电话1")
    private String fmPhone1;

    /** 财务经理2 */
    @Excel(name = "财务经理2")
    private String financialManager2;

    /** 财务经理电话2 */
    @Excel(name = "财务经理电话2")
    private String fmPhone2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long orderStatus;

    /** 编辑权限人员 */
    @Excel(name = "编辑权限人员")
    private String editor;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerName()
    {
        return customerName;
    }
    public void setCustomerCode(String customerCode)
    {
        this.customerCode = customerCode;
    }

    public String getCustomerCode()
    {
        return customerCode;
    }
    public void setDictCustomerType(String dictCustomerType)
    {
        this.dictCustomerType = dictCustomerType;
    }

    public String getDictCustomerType()
    {
        return dictCustomerType;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setFinancialManager(String financialManager)
    {
        this.financialManager = financialManager;
    }

    public String getFinancialManager()
    {
        return financialManager;
    }
    public void setFmPhone(String fmPhone)
    {
        this.fmPhone = fmPhone;
    }

    public String getFmPhone()
    {
        return fmPhone;
    }
    public void setCustomerLinks(String customerLinks)
    {
        this.customerLinks = customerLinks;
    }

    public String getCustomerLinks()
    {
        return customerLinks;
    }
    public void setCustomerBusiness(String customerBusiness)
    {
        this.customerBusiness = customerBusiness;
    }

    public String getCustomerBusiness()
    {
        return customerBusiness;
    }
    public void setBusinessLicense(String businessLicense)
    {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense()
    {
        return businessLicense;
    }
    public void setConpanyName(String conpanyName)
    {
        this.conpanyName = conpanyName;
    }

    public String getConpanyName()
    {
        return conpanyName;
    }
    public void setBusinessLicenseNo(String businessLicenseNo)
    {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getBusinessLicenseNo()
    {
        return businessLicenseNo;
    }
    public void setLegalRepresentative(String legalRepresentative)
    {
        this.legalRepresentative = legalRepresentative;
    }

    public String getLegalRepresentative()
    {
        return legalRepresentative;
    }
    public void setRegisteredCapital(String registeredCapital)
    {
        this.registeredCapital = registeredCapital;
    }

    public String getRegisteredCapital()
    {
        return registeredCapital;
    }
    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }
    public void setDictCompanyType(String dictCompanyType)
    {
        this.dictCompanyType = dictCompanyType;
    }

    public String getDictCompanyType()
    {
        return dictCompanyType;
    }
    public void setCompanyBegin(Date companyBegin)
    {
        this.companyBegin = companyBegin;
    }

    public Date getCompanyBegin()
    {
        return companyBegin;
    }
    public void setPaiclupCapital(String paiclupCapital)
    {
        this.paiclupCapital = paiclupCapital;
    }

    public String getPaiclupCapital()
    {
        return paiclupCapital;
    }
    public void setBusinessScope(String businessScope)
    {
        this.businessScope = businessScope;
    }

    public String getBusinessScope()
    {
        return businessScope;
    }
    public void setBusinessTermStart(Date businessTermStart)
    {
        this.businessTermStart = businessTermStart;
    }

    public Date getBusinessTermStart()
    {
        return businessTermStart;
    }
    public void setBusinessTermEnd(Date businessTermEnd)
    {
        this.businessTermEnd = businessTermEnd;
    }

    public Date getBusinessTermEnd()
    {
        return businessTermEnd;
    }
    public void setRegistrationAuthority(String registrationAuthority)
    {
        this.registrationAuthority = registrationAuthority;
    }

    public String getRegistrationAuthority()
    {
        return registrationAuthority;
    }
    public void setLicensingDay(Date licensingDay)
    {
        this.licensingDay = licensingDay;
    }

    public Date getLicensingDay()
    {
        return licensingDay;
    }
    public void setCustomerBillingInformation(String customerBillingInformation)
    {
        this.customerBillingInformation = customerBillingInformation;
    }

    public String getCustomerBillingInformation()
    {
        return customerBillingInformation;
    }
    public void setTaxRegNumber(String taxRegNumber)
    {
        this.taxRegNumber = taxRegNumber;
    }

    public String getTaxRegNumber()
    {
        return taxRegNumber;
    }
    public void setDutyParagraph(String dutyParagraph)
    {
        this.dutyParagraph = dutyParagraph;
    }

    public String getDutyParagraph()
    {
        return dutyParagraph;
    }
    public void setOpeningBank(String openingBank)
    {
        this.openingBank = openingBank;
    }

    public String getOpeningBank()
    {
        return openingBank;
    }
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTelephone()
    {
        return telephone;
    }
    public void setCompanyAddress(String companyAddress)
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress()
    {
        return companyAddress;
    }
    public void setDictIsOldCustomer(String dictIsOldCustomer)
    {
        this.dictIsOldCustomer = dictIsOldCustomer;
    }

    public String getDictIsOldCustomer()
    {
        return dictIsOldCustomer;
    }
    public void setCustomerRespPerson(String customerRespPerson)
    {
        this.customerRespPerson = customerRespPerson;
    }

    public String getCustomerRespPerson()
    {
        return customerRespPerson;
    }
    public void setCustomerRelationPerson(String customerRelationPerson)
    {
        this.customerRelationPerson = customerRelationPerson;
    }

    public String getCustomerRelationPerson()
    {
        return customerRelationPerson;
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
    public void setFinancialManager1(String financialManager1)
    {
        this.financialManager1 = financialManager1;
    }

    public String getFinancialManager1()
    {
        return financialManager1;
    }
    public void setFmPhone1(String fmPhone1)
    {
        this.fmPhone1 = fmPhone1;
    }

    public String getFmPhone1()
    {
        return fmPhone1;
    }
    public void setFinancialManager2(String financialManager2)
    {
        this.financialManager2 = financialManager2;
    }

    public String getFinancialManager2()
    {
        return financialManager2;
    }
    public void setFmPhone2(String fmPhone2)
    {
        this.fmPhone2 = fmPhone2;
    }

    public String getFmPhone2()
    {
        return fmPhone2;
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
    public void setEditor(String editor)
    {
        this.editor = editor;
    }

    public String getEditor()
    {
        return editor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerName", getCustomerName())
            .append("customerCode", getCustomerCode())
            .append("dictCustomerType", getDictCustomerType())
            .append("phone", getPhone())
            .append("financialManager", getFinancialManager())
            .append("fmPhone", getFmPhone())
            .append("customerLinks", getCustomerLinks())
            .append("customerBusiness", getCustomerBusiness())
            .append("businessLicense", getBusinessLicense())
            .append("conpanyName", getConpanyName())
            .append("businessLicenseNo", getBusinessLicenseNo())
            .append("legalRepresentative", getLegalRepresentative())
            .append("registeredCapital", getRegisteredCapital())
            .append("customerAddress", getCustomerAddress())
            .append("dictCompanyType", getDictCompanyType())
            .append("companyBegin", getCompanyBegin())
            .append("paiclupCapital", getPaiclupCapital())
            .append("businessScope", getBusinessScope())
            .append("businessTermStart", getBusinessTermStart())
            .append("businessTermEnd", getBusinessTermEnd())
            .append("registrationAuthority", getRegistrationAuthority())
            .append("licensingDay", getLicensingDay())
            .append("customerBillingInformation", getCustomerBillingInformation())
            .append("taxRegNumber", getTaxRegNumber())
            .append("dutyParagraph", getDutyParagraph())
            .append("openingBank", getOpeningBank())
            .append("accountNumber", getAccountNumber())
            .append("telephone", getTelephone())
            .append("companyAddress", getCompanyAddress())
            .append("dictIsOldCustomer", getDictIsOldCustomer())
            .append("customerRespPerson", getCustomerRespPerson())
            .append("customerRelationPerson", getCustomerRelationPerson())
            .append("remark", getRemark())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("createId", getCreateId())
            .append("updateTime", getUpdateTime())
            .append("updateId", getUpdateId())
            .append("deleted", getDeleted())
            .append("financialManager1", getFinancialManager1())
            .append("fmPhone1", getFmPhone1())
            .append("financialManager2", getFinancialManager2())
            .append("fmPhone2", getFmPhone2())
            .append("orderId", getOrderId())
            .append("orderStatus", getOrderStatus())
            .append("editor", getEditor())
            .toString();
    }
}
