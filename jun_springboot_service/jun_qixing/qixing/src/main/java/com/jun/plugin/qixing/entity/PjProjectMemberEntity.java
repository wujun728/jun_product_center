package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目成员与结算
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-29 11:22:36
 */
@Data
@TableName("pj_project_member")
public class PjProjectMemberEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 成员ID
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
	 * 成员名称
	 */
	@TableField(value = "ref_member_name"  )
	private String refMemberName;
	/**
	 * 成员项目角色
	 */
	@TableField(value = "dict_member_role"  )
	private String dictMemberRole;
	/**
	 * 成员工作内容
	 */
	@TableField(value = "member_work_content"  )
	private String memberWorkContent;
	/**
	 * 是否参与分成
	 */
	@TableField(value = "dict_yes_no"  )
	private String dictYesNo;
	/**
	 * 成员工作分成比例
	 */
	@TableField(value = "member_parts"  )
	private Integer memberParts;
	/**
	 * 成员合计投入项目工作日
	 */
	@TableField(value = "member_work_days"  )
	private Integer memberWorkDays;
	/**
	 * 分成金额
	 */
	@TableField(value = "member_parts_money"  )
	private Integer memberPartsMoney;
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
