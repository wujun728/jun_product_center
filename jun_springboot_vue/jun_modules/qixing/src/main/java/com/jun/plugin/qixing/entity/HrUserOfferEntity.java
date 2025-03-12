package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Offer发放
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Data
@TableName("hr_user_offer")
public class HrUserOfferEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 入职人员名称
	 */
	@TableField(value = "ref_job_username"  )
	private String refJobUsername;
	/**
	 * 工作地点
	 */
	@TableField(value = "location"  )
	private String location;
	/**
	 * 部门
	 */
	@TableField(value = "ref_deptname"  )
	private String refDeptname;
	/**
	 * 招聘专员
	 */
	@TableField(value = "ref_username1"  )
	private String refUsername1;
	/**
	 * Offer状态
	 */
	@TableField(value = "dict_offer_status"  )
	private String dictOfferStatus;
	/**
	 * Offer邮件发送
	 */
	@TableField(value = "dict_yes_no"  )
	private String dictYesNo;
	/**
	 * 入职时间
	 */
	@TableField(value = "job_in_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date jobInTime;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
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

}
