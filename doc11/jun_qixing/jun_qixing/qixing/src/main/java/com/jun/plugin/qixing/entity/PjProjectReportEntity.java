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
 * 项目报告
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-31 23:22:15
 */
@Data
@TableName("pj_project_report")
public class PjProjectReportEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 报告ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 报告名称
	 */
	@TableField(value = "report_name"  )
	private String reportName;
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
	 * 报告类型
	 */
	@TableField(value = "dict_report_type"  )
	private String dictReportType;
	/**
	 * 报告详细描述
	 */
	@TableField(value = "report_detail"  )
	private String reportDetail;
	/**
	 * 客户原始述求
	 */
	@TableField(value = "customer_req"  )
	private String customerReq;
	/**
	 * 报告输出时间
	 */
	@TableField(value = "report_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date reportTime;
	/**
	 * 报告输出责任人
	 */
	@TableField(value = "ref_report_by"  )
	private String refReportBy;
	/**
	 * 报告完成天数
	 */
	@TableField(value = "report_finash_days"  )
	private Integer reportFinashDays;
	/**
	 * 报告状态
	 */
	@TableField(value = "dict_report_status"  )
	private String dictReportStatus;
	/**
	 * 报告文号
	 */
	@TableField(value = "reportnum_code" )
	private String reportnumCode;
	/**
	 * 创建人
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
