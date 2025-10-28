package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目开票
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 13:52:25
 */
@Data
@TableName("pj_project_invoice")
public class PjProjectInvoiceEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 
	 */
	@TableField(value = "ref_customer_code"  )
	private String refCustomerCode;
	/**
	 * 开票客户
	 */
	@TableField(value = "ref_customer_name"  )
	private String refCustomerName;
	/**
	 * 
	 */
	@TableField(value = "ref_project_code"  )
	private String refProjectCode;
	/**
	 * 开票项目
	 */
	@TableField(value = "ref_project_name"  )
	private String refProjectName;
	/**
	 * 开票类型
	 */
	@TableField(value = "dict_type"  )
	private String dictType;
	/**
	 * 开票单位名称
	 */
	@TableField(value = "invoice_company_name"  )
	private String invoiceCompanyName;
	/**
	 * 纳税人识别号
	 */
	@TableField(value = "invoice_tax_no"  )
	private String invoiceTaxNo;
	/**
	 * 地址
	 */
	@TableField(value = "address"  )
	private String address;
	/**
	 * 电话
	 */
	@TableField(value = "telephone"  )
	private String telephone;
	/**
	 * 开户行
	 */
	@TableField(value = "dict_bank"  )
	private String dictBank;
	/**
	 * 开户行账号
	 */
	@TableField(value = "bank_card_no"  )
	private String bankCardNo;
	/**
	 * 开票金额
	 */
	@TableField(value = "invoice_money"  )
	private BigDecimal invoiceMoney;
	/**
	 * 审批状态
	 */
	@TableField(value = "dict_invoice_state"  )
	private String dictInvoiceState;
	/**
	 * 流程状态
	 */
	@TableField(value = "dict_wf_state"  )
	private String dictWfState;
	/**
	 * 开票人
	 */
	@TableField(value = "ref_invoice_man"  )
	private String refInvoiceMan;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private Double remark;
	/**
	 * 
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 
	 */
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;
	/**
	 * 
	 */
	@TableField(value = "update_time" , fill = FieldFill.INSERT_UPDATE  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	/**
	 * 
	 */
	@TableField(value = "update_id" , fill = FieldFill.INSERT_UPDATE  )
	private String updateId;
	/**
	 * 
	 */
	@TableField(value = "deleted" , fill = FieldFill.INSERT  )
	private Integer deleted;

}
