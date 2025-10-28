package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目底稿
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 23:03:16
 */
@Data
@TableName("pj_project_draft")
public class PjProjectDraftEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 底稿ID
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 底稿名称
	 */
	@TableField(value = "draft_name"  )
	private String draftName;
	/**
	 * 底稿类型
	 */
	@TableField(value = "dict_draft_type"  )
	private String dictDraftType;
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
	 * 底稿描述
	 */
	@TableField(value = "draft_desc"  )
	private String draftDesc;
	/**
	 * 底稿输出时间
	 */
	@TableField(value = "draft_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date draftTime;
	/**
	 * 底稿输出责任人(承做)
	 */
	@TableField(value = "ref_draft_by"  )
	private String refDraftBy;
	/**
	 * 底稿整理进度
	 */
	@TableField(value = "dict_draft_status"  )
	private String dictDraftStatus;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 * 
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
