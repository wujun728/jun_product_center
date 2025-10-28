package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 考核记录明细
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 20:28:30
 */
@Data
@TableName("hr_assessment_user_record_detail")
public class HrAssessmentUserRecordDetailEntity extends BaseFlowEntity implements Serializable {
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
	@TableField(value = "usercode"  )
	private String usercode;
	/**
	 * 考核人
	 */
	@TableField(value = "ref_username"  )
	private String refUsername;
	/**
	 * 
	 */
	@TableField(value = "ref_template_id"  )
	private String teamplate_id;
	
	@TableField(value = "ref_record_id"  )
	private String ref_record_id;
	
	@TableField(exist = false  )
	private Integer attr_score;
	/**
	 * 考核模板名称
	 */
	@TableField(value = "ref_template_name"  )
	private String refTemplateName;
	/**
	 * 考核指标
	 */
	@TableField(value = "sortno"  )
	private Integer sortno;
	/**
	 * 自评分值
	 */
	@TableField(value = "score"  )
	private Integer score;
	/**
	 * 
	 */
	@TableField(value = "order_id"  )
	private String orderId;
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
