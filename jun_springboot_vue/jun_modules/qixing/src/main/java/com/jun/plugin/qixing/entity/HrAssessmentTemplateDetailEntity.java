package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 考核模板明细
 *
 * @author wujun
 * @email wujun728@mail.com
 * @date 2021-12-05 01:48:53
 */
@Data
@TableName("hr_assessment_template_detail")
public class HrAssessmentTemplateDetailEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 序号
	 */
	@TableField(value = "sortno"  )
	private Integer sortno;
	/**
	 * 
	 */
	@TableField(value = "teamplate_id"  )
	private String teamplateId;
	/**
	 * 考核模板名称
	 */
	@TableField(value = "ref_teamplate_name"  )
	private String refTeamplateName;
	/**
	 * 评分项目(大类)
	 */
	@TableField(value = "ass_type"  )
	private String assType;
	/**
	 * 项目类别
	 */
	@TableField(value = "ass_attr_type"  )
	private String assAttrType;
	/**
	 * 考核内容
	 */
	@TableField(value = "ass_attr_name"  )
	private String assAttrName;
	/**
	 * 分值 
	 */
	@TableField(value = "attr_score"  )
	private Integer attrScore;
	/**
	 * 评分标准
	 */
	@TableField(value = "attr_desc"  )
	private String attrDesc;
	/**
	 * 考核方式
	 */
	@TableField(value = "ass_attr_desc"  )
	private String assAttrDesc;
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
