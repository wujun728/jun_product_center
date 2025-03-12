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
 * 项目计划
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 10:44:55
 */
@Data
@TableName("pj_project_plan")
public class PjProjectPlanEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 
	 */
	@TableField(value = "ref_project_code"  )
	private String refProjectCode;
	/**
	 * 项目名称
	 */
	@TableField(value = "ref_project_name"  )
	private String refProjectName;
	/**
	 * 项目计划标题
	 */
	@TableField(value = "plan_name"  )
	private String planName;
	/**
	 * 项目计划详细描述
	 */
	@TableField(value = "plan_detail"  )
	private String planDetail;
	/**
	 * 项目计划开始时间
	 */
	@TableField(value = "plan_time_start"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date planTimeStart;
	/**
	 * 项目计划结束时间
	 */
	@TableField(value = "plan_time_end"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date planTimeEnd;
	/**
	 * 工期(人天)
	 */
	@TableField(value = "plan_dates"  )
	private String planDates;
	/**
	 * 项目计划交付日期
	 */
	@TableField(value = "plan_given_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date planGivenTime;
	/**
	 * 项目计划完成天数
	 */
	@TableField(value = "plan_finash_days"  )
	private Integer planFinashDays;
	/**
	 * 项目实际完成天数
	 */
	@TableField(value = "plan_finash_days2"  )
	private Integer planFinashDays2;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 * 备注
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

}
