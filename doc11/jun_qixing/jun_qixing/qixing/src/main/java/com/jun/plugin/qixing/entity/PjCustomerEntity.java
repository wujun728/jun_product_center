package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 客户信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
@Data
@TableName("pj_customer")
public class PjCustomerEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户ID
	 */
	@Id
	@TableId("id")
	private String id;

	/**
	 * 客户名称
	 */
	@TableField(value = "customer_name"  )
	private String customerName;

	/**
	 * 客户编码
	 */
	@TableField(value = "customer_code"  )
	private String customerCode;

	/**
	 * 客户类型
	 */
	@TableField(value = "dict_customer_type"  )
	private String dictCustomerType;

	/**
	 * 公司电话
	 */
	@TableField(value = "phone"  )
	private String phone;

	/**
	 * 财务经理
	 */
	@TableField(value = "financial_manager"  )
	private String financialManager;

	/**
	 * 财务经理电话
	 */
	@TableField(value = "fm_phone"  )
	private String fmPhone;
	
	/**
	 */
	@TableField(value = "financial_manager1"  )
	private String financialManager1;
	
	/**
	 */
	@TableField(value = "fm_phone1"  )
	private String fmPhone1;
	
	/**
	 */
	@TableField(value = "financial_manager2"  )
	private String financialManager2;
	
	/**
	 */
	@TableField(value = "fm_phone2"  )
	private String fmPhone2;

	/**
	 * 客户联系信息
	 */
	@TableField(value = "customer_links"  )
	private String customerLinks;

	/**
	 * 客户营业信息
	 */
	@TableField(value = "customer_business"  )
	private String customerBusiness;

	/**
	 * 营业执照
	 */
	@TableField(value = "business_license"  )
	private String businessLicense;

	/**
	 * 公司全称
	 */
	@TableField(value = "conpany_name"  )
	private String conpanyName;

	/**
	 * 营业执照编码
	 */
	@TableField(value = "business_license_no"  )
	private String businessLicenseNo;

	/**
	 * 法定代表人
	 */
	@TableField(value = "legal_representative"  )
	private String legalRepresentative;

	/**
	 * 注册资本
	 */
	@TableField(value = "registered_capital"  )
	private String registeredCapital;

	/**
	 * 客户地址
	 */
	@TableField(value = "customer_address"  )
	private String customerAddress;

	/**
	 * 公司类型
	 */
	@TableField(value = "dict_company_type"  )
	private String dictCompanyType;

	/**
	 * 公司始建于
	 */
	@TableField(value = "company_begin"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date companyBegin;

	/**
	 * 实收资本
	 */
	@TableField(value = "paiclup_capital"  )
	private String paiclupCapital;

	/**
	 * 经营范围
	 */
	@TableField(value = "business_scope"  )
	private String businessScope;

	/**
	 * 营业期限开始
	 */
	@TableField(value = "business_term_start"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date businessTermStart;

	/**
	 * 营业期限结束
	 */
	@TableField(value = "business_term_end"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date businessTermEnd;

	/**
	 * 登记机关
	 */
	@TableField(value = "registration_authority"  )
	private String registrationAuthority;

	/**
	 * 执照发放日
	 */
	@TableField(value = "licensing_day"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date licensingDay;

	/**
	 * 客户开票信息
	 */
	@TableField(value = "customer_billing_information"  )
	private String customerBillingInformation;

	/**
	 * 纳税人识别号
	 */
	@TableField(value = "tax_reg_number"  )
	private String taxRegNumber;

	/**
	 * 税号
	 */
	@TableField(value = "duty_paragraph"  )
	private String dutyParagraph;

	/**
	 * 开户行
	 */
	@TableField(value = "opening_bank"  )
	private String openingBank;

	/**
	 * 账号
	 */
	@TableField(value = "account_number"  )
	private String accountNumber;

	/**
	 * 电话
	 */
	@TableField(value = "telephone"  )
	private String telephone;

	/**
	 * 公司地址
	 */
	@TableField(value = "company_address"  )
	private String companyAddress;

	/**
	 * 是否老客户
	 */
	@TableField(value = "dict_is_old_customer"  )
	private String dictIsOldCustomer;

	/**
	 * 客户对接责任人
	 */
	@TableField(value = "customer_resp_person"  )
	private String customerRespPerson;

	/**
	 * 客户关系维护责任人
	 */
	@TableField(value = "customer_relation_person"  )
	private String customerRelationPerson;

	/**
	 * 备注信息
	 */
	@TableField(value = "remark"  )
	private String remark;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	/**
	 * 创建人
	 */
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;
	/**
	 * 创建人
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time" , fill = FieldFill.INSERT_UPDATE  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 更新人
	 */
	@TableField(value = "update_id" , fill = FieldFill.INSERT_UPDATE  )
	private String updateId;

	/**
	 * 是否弃用
	 */
	@TableField(value = "deleted" , fill = FieldFill.INSERT  )
	private Integer deleted;
	
	@TableField(value = "editor" , fill = FieldFill.INSERT  )
	private String editor;


}
