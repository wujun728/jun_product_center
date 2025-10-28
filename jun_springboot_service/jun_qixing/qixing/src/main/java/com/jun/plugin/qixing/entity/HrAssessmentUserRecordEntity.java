package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 考核记录
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 21:12:45
 */
@Data
@TableName("hr_assessment_user_record")
public class HrAssessmentUserRecordEntity extends BaseFlowEntity implements Serializable {
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
	@TableField(value = "ref_usercode"  )
	private String refUsercode;
	/**
	 * 考核人
	 */
	@TableField(value = "ref_username"  )
	private String refUsername;
	
	@TableField(value = "record_name"  )
	private String record_name;
	/**
	 * 考核模板
	 */
	@TableField(value = "ref_teamplate_name"  )
	private String refTeamplateName;
	/**
	 * 考核自评分值
	 */
	@TableField(value = "score"  )
	private Integer score;
	/**
	 * 考核终评分值
	 */
	@TableField(value = "score1"  )
	private Integer score1;
	/**
	 * 考核终评人
	 */
	@TableField(value = "score1_username"  )
	private String score1Username;
	/**
	 * 考核人终评详细评价
	 */
	@TableField(value = "score1_desc"  )
	private String score1Desc;
	/**
	 * 
	 */
	@TableField(value = "teamplate_id"  )
	private String teamplateId;
	/**
	 * 
	 */
	@TableField(value = "order_id"  )
	private String orderId;
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
