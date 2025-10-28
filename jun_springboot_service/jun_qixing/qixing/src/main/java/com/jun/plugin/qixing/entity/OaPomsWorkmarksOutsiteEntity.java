package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 外出信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:32
 */
@Data
@TableName("oa_poms_workmarks_outsite")
public class OaPomsWorkmarksOutsiteEntity extends BaseFlowEntity implements Serializable {
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
	 * 外出日期
	 */
	@TableField(value = "work_day"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date workDay;
	/**
	 * 外出事由(出差、拜访客户)
	 */
	@TableField(value = "outsite_desc"  )
	private String outsiteDesc;
	/**
	 * 开始时间
	 */
	@TableField(value = "begin_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beginTime;
	/**
	 * 结束时间
	 */
	@TableField(value = "end_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;
	/**
	 * 外出时长
	 */
	@TableField(value = "work_total_time"  )
	private BigDecimal workTotalTime;
	/**
	 * 审批状态
	 */
	@TableField(value = "dict_wfstate_outsite"  )
	private String dictWfstateOutsite;
	/**
	 * 当前处理人
	 */
	@TableField(value = "curr_todo"  )
	private String currTodo;
	/**
	 * 填报人
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
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
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;

}
