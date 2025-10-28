package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 考核模板
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 01:48:53
 */
@Data
@TableName("hr_assessment_template")
public class HrAssessmentTemplateEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 考核模板名称
	 */
	@TableField(value = "teamplate_name"  )
	private String teamplateName;
	/**
	 * 考核总分值
	 */
	@TableField(value = "score_total"  )
	private Double scoreTotal;
	/**
	 * 适用范围
	 */
	@TableField(value = "for_use_peoples"  )
	private String forUsePeoples;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
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
