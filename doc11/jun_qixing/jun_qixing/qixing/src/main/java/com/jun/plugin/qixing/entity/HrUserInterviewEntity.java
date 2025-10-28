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
 * 面试汇总
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Data
@TableName("hr_user_interview")
public class HrUserInterviewEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 候选人
	 */
	@TableField(value = "ref_people_name"  )
	private String refPeopleName;
	/**
	 * 面试时间
	 */
	@TableField(value = "iv_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date ivTime;
	/**
	 * 面试类型
	 */
	@TableField(value = "dict_iv_type"  )
	private String dictIvType;
	/**
	 * 面试官名称
	 */
	@TableField(value = "ref_iv_username"  )
	private String refIvUsername;
	/**
	 * 面试官电话
	 */
	@TableField(value = "iv_phone"  )
	private String ivPhone;
	/**
	 * 面试结果
	 */
	@TableField(value = "dict_result"  )
	private String dictResult;
	/**
	 * 面试评价
	 */
	@TableField(value = "iv_evaluate"  )
	private String ivEvaluate;
	/**
	 * 工作岗位
	 */
	@TableField(value = "job_name"  )
	private String jobName;
	/**
	 * 工作描述
	 */
	@TableField(value = "job_desc"  )
	private String jobDesc;
	/**
	 * 工作年限
	 */
	@TableField(value = "work_year"  )
	private Integer workYear;
	/**
	 * 工作地点
	 */
	@TableField(value = "work_location"  )
	private String workLocation;
	/**
	 * 工作内容
	 */
	@TableField(value = "work_content"  )
	private String workContent;
	/**
	 * 期望薪资
	 */
	@TableField(value = "job_money"  )
	private Integer jobMoney;
	/**
	 * 到岗时间
	 */
	@TableField(value = "get_in_compay_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date getInCompayTime;
	/**
	 * 离职原因
	 */
	@TableField(value = "out_job_desc"  )
	private String outJobDesc;
	/**
	 * 职业技能特长
	 */
	@TableField(value = "job_skill"  )
	private String jobSkill;
	/**
	 * 候选人优缺点
	 */
	@TableField(value = "job_interview"  )
	private String jobInterview;
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
