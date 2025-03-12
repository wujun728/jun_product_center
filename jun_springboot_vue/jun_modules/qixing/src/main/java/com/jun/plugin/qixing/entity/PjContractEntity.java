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
 * 业务约定书
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 09:18:54
 */
@Data
@TableName("pj_contract")
public class PjContractEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 合同ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 
	 */
	@TableField(value = "refid_project_code_hide"  )
	private String refidProjectCodeHide;
	/**
	 * 项目名称
	 */
	@TableField(value = "ref_project_name"  )
	private String refProjectName;
	/**
	 * 合同状态
	 */
	@TableField(value = "dict_contract_state"  )
	private String dictContractState;
	/**
	 * 合同类型
	 */
	@TableField(value = "dict_contract_type"  )
	private String dictContractType;
	/**
	 * 合同号
	 */
	@TableField(value = "contract_no"  )
	private String contractNo;
	/**
	 * 合同标题
	 */
	@TableField(value = "contract_name"  )
	private String contractName;
	/**
	 * 签约日期
	 */
	@TableField(value = "sign_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date signTime;
	/**
	 * 报告数量
	 */
	@TableField(value = "report_count"  )
	private Integer reportCount;
	/**
	 * 报告详细描述
	 */
	@TableField(value = "report_detail"  )
	private String reportDetail;
	/**
	 * 约定书金额
	 */
	@TableField(value = "contract_money"  )
	private BigDecimal contractMoney;
	/**
	 * 签约/承诺人
	 */
	@TableField(value = "sign_by"  )
	private String signBy;
	/**
	 * 执业天数
	 */
	@TableField(value = "contract_days"  )
	private Integer contractDays;
	/**
	 * 约定书详细描述
	 */
	@TableField(value = "contract_details"  )
	private String contractDetails;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 * 
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
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
