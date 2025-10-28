package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目报告文号
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-01 10:44:42
 */
@Data
@TableName("pj_project_reportnumber")
public class PjProjectReportnumberEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 
	 */
	@TableField(value = "ref_reportnumber_code"  )
	private String refReportnumberCode;
	/**
	 * 项目报告
	 */
	@TableField(value = "ref_reportnumber_title"  )
	private String refReportnumberTitle;
	/**
	 * 报告文号(生成)
	 */
	@TableField(value = "reportnumber_code"  )
	private String reportnumberCode;
	/**
	 * 报告号状态
	 */
	@TableField(value = "dict_rp_status"  )
	private String dictRpStatus;
	/**
	 * 报告出具人
	 */
	@TableField(value = "ref_reportnumber_man"  )
	private String refReportnumberMan;
	/**
	 * 报告审核人
	 */
	@TableField(value = "ref_reportnumber_check_man"  )
	private String refReportnumberCheckMan;
	/**
	 * 签字注册会计师
	 */
	@TableField(value = "ref_signature_accountant"  )
	private String refSignatureAccountant;
	/**
	 * 申请人
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private Double remark;
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
