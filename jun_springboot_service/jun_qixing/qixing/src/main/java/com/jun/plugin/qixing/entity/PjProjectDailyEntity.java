package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目日报周报
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 22:00:09
 */
@Data
@TableName("pj_project_daily")
public class PjProjectDailyEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 日报标题
	 */
	@TableField(value = "plan_name"  )
	private String planName;
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
	 * 日报周报
	 */
	@TableField(value = "dict_daily_type"  )
	private String dictDailyType;
	/**
	 * 日期
	 */
	@TableField(value = "daily_date"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dailyDate;
	/**
	 * 工作内容描述
	 */
	@TableField(value = "daily_detail"  )
	private String dailyDetail;
	/**
	 * 投入工时
	 */
	@TableField(value = "cost_time"  )
	private Integer costTime;
	/**
	 * 风险级别
	 */
	@TableField(value = "dict_risk"  )
	private String dictRisk;
	/**
	 * 是否求助
	 */
	@TableField(value = "dict_ask_help"  )
	private String dictAskHelp;
	/**
	 * 是否延迟
	 */
	@TableField(value = "dict_is_delay"  )
	private String dictIsDelay;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 * 填报人
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
