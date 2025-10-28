package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseV2Entity;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 考勤记录
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:33
 */
@Data
@TableName("oa_poms_workmarks_worktimes")
public class OaPomsWorkmarksWorktimesV2Entity extends BaseV2Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 工号
	 */
	@TableField(value = "usercode"  )
	private String usercode;
	/**
	 * 用户名称
	 */
	@TableField(value = "ref_username"  )
	private String refUsername;
	/**
	 * 日期
	 */
	@TableField(value = "work_day"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date workDay;
	/**
	 * 开始时间
	 */
	@TableField(value = "begin_time"  )
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
	private Date beginTime;
	/**
	 * 结束时间
	 */
	@TableField(value = "end_time"  )
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
	private Date endTime;
	/**
	 * 工作时长
	 */
	@TableField(value = "work_total_time"  )
	private BigDecimal workTotalTime;
	/**
	 * 入场地点
	 */
	@TableField(value = "leave_in_case"  )
	private String leaveInCase;
	/**
	 * 入场经纬度
	 */
	@TableField(value = "leave_in_xy"  )
	private String leaveInXy;
	/**
	 * 离场地点
	 */
	@TableField(value = "leanve_out_case"  )
	private String leanveOutCase;
	/**
	 * 离场经纬度
	 */
	@TableField(value = "leanve_out_xy"  )
	private String leanveOutXy;
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
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
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

	/**
	 *
	 */
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;
}
