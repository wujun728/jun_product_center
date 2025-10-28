package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目借阅
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 13:58:41
 */
@Data
@TableName("pj_project_borrow")
public class PjProjectBorrowEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
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
	 * 
	 */
	@TableField(value = "ref_user_code"  )
	private String refUserCode;
	/**
	 * 用户名称
	 */
	@TableField(value = "ref_user_name"  )
	private String refUserName;
	/**
	 * 借阅原因
	 */
	@TableField(value = "borrow_desc"  )
	private String borrowDesc;
	/**
	 * 借阅结束日期
	 */
	@TableField(value = "end_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;
	/**
	 * 流程状态
	 */
	@TableField(value = "dict_borrow_state"  )
	private String dictBorrowState;
	/**
	 * 提交人
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 创建人
	 */
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;
	/**
	 * 更新时间
	 */
	@TableField(value = "update_time" , fill = FieldFill.INSERT_UPDATE  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	/**
	 * 更新人
	 */
	@TableField(value = "update_id" , fill = FieldFill.INSERT_UPDATE  )
	private String updateId;

}
