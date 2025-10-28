package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 办公用品申领申购
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-11-27 11:09:09
 */
@Data
@TableName("oa_office_count2")
public class OaOfficeCount2Entity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 办公用品名称
	 */
	@TableField(value = "offiec_product_name"  )
	private String offiecProductName;
	/**
	 * 办公用品类型
	 */
	@TableField(value = "dict_product_type"  )
	private String dictProductType;
	/**
	 * 办公用品用途
	 */
	@TableField(value = "office_todo"  )
	private String officeTodo;
	/**
	 * 需求数量
	 */
	@TableField(value = "req_num"  )
	private Integer reqNum;
	/**
	 * 申请原因
	 */
	@TableField(value = "why_desc"  )
	private String whyDesc;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 * 申请人
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
	/**
	 * 审批状态
	 */
	@TableField(value = "dict_approve"  )
	private String dictApprove;
	/**
	 * 审批意见
	 */
	@TableField(value = "desc_approve"  )
	private String descApprove;
	/**
	 * 审批人
	 */
	@TableField(value = "approvetor"  )
	private String approvetor;
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
	@TableField(value = "order_id"  )
	private String orderId;
	/**
	 * 
	 */
//	@TableField(value = "order_state"  )
//	private String orderState;

}
