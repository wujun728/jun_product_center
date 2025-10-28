package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 项目进度与任务(WBS)
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 21:10:39
 */
@Data
@TableName("pj_project_prodess_task")
public class PjProjectProdessTaskEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 项目名称
	 */
	@TableField(value = "ref_project_name"  )
	private String refProjectName;
	/**
	 * 
	 */
	@TableField(value = "ref_project_code"  )
	private String refProjectCode;
	/**
	 * 任务名称
	 */
	@TableField(value = "task_name"  )
	private String taskName;
	/**
	 * 任务内容
	 */
	@TableField(value = "task_detail"  )
	private String taskDetail;
	/**
	 * 工时估算
	 */
	@TableField(value = "cost_time"  )
	private Integer costTime;
	/**
	 * 费用估算
	 */
	@TableField(value = "cost_money"  )
	private Integer costMoney;
	/**
	 * 开始时间
	 */
	@TableField(value = "task_time_start"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date taskTimeStart;
	/**
	 * 结束时间
	 */
	@TableField(value = "task_time_end"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date taskTimeEnd;
	
	/**
	 * 项目进度
	 */
	@TableField(value = "task_progress"  )
	private BigDecimal taskProgress;
	
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
