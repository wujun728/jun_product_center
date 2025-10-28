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
 * 入职报道
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Data
@TableName("hr_user_entry_reported")
public class HrUserEntryReportedEntity extends BaseEntity implements Serializable {
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
	 * 直属领导
	 */
	@TableField(value = "ref_username1"  )
	private String refUsername1;
	/**
	 * 报道部门
	 */
	@TableField(value = "ref_job_deptname"  )
	private String refJobDeptname;
	/**
	 * 入职报告发起人
	 */
	@TableField(value = "username2"  )
	private String username2;
	/**
	 * 导师
	 */
	@TableField(value = "ref_username2"  )
	private String refUsername2;
	/**
	 * 试用期开始时间
	 */
	@TableField(value = "start_time1"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startTime1;
	/**
	 * 试用期结束时间
	 */
	@TableField(value = "end_time1"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime1;
	/**
	 * 合同开始时间
	 */
	@TableField(value = "begin_time2"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date beginTime2;
	/**
	 * 合同结束时间
	 */
	@TableField(value = "end_time2"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime2;
	/**
	 * 工作地点
	 */
	@TableField(value = "work_location"  )
	private String workLocation;
	/**
	 * 考勤班次
	 */
	@TableField(value = "workmark_times"  )
	private String workmarkTimes;
	/**
	 * 资料是否齐全
	 */
	@TableField(value = "is_full_entry_infomation"  )
	private String isFullEntryInfomation;
	/**
	 * 入职手续是否办理完成
	 */
	@TableField(value = "is_entry_job_filish"  )
	private String isEntryJobFilish;
	/**
	 * 合同是否签订
	 */
	@TableField(value = "is_sign_contract"  )
	private String isSignContract;
	/**
	 * 附件(证件影印件+合同影印件)
	 */
	@TableField(value = "files"  )
	private String files;
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
