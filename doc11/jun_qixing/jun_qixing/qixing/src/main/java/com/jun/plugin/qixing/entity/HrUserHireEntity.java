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
 * 录用审批
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-11 18:03:44
 */
@Data
@TableName("hr_user_hire")
public class HrUserHireEntity extends BaseFlowEntity implements Serializable {
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
	 * 入职部门
	 */
	@TableField(value = "ref_job_deptname"  )
	private String refJobDeptname;
	/**
	 * 用工类型
	 */
	@TableField(value = "dict_job_type"  )
	private String dictJobType;
	/**
	 * 入职岗位
	 */
	@TableField(value = "dict_job"  )
	private String dictJob;
	/**
	 * 直属领导
	 */
	@TableField(value = "ref_entry_leader"  )
	private String refEntryLeader;
	/**
	 * 新员工导师
	 */
	@TableField(value = "ref_entry_teach"  )
	private String refEntryTeach;
	/**
	 * 薪资
	 */
	@TableField(value = "money"  )
	private Integer money;
	/**
	 * 入职时间
	 */
	@TableField(value = "entry_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date entryTime;
	/**
	 * 流程状态
	 */
	@TableField(value = "wfstate"  )
	private String wfstate;
	/**
	 * 当前节点
	 */
	@TableField(value = "curr_nodename"  )
	private String currNodename;
	/**
	 * 当前审批人
	 */
	@TableField(value = "curr_usercode"  )
	private String currUsercode;
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
