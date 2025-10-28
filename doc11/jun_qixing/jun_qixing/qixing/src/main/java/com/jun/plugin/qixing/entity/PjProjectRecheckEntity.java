package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jun.plugin.common.entity.BaseEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目复核
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:31
 */
@Data
@TableName("pj_project_recheck")
public class PjProjectRecheckEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 成员ID
	 */
	@Id
	@TableId("id")
	private String id;

	/**
	 * 
	 */
	@TableField(value = "ref_pcode"  )
	private String refPcode;

	/**
	 * 项目名称
	 */
	@TableField(value = "ref_pname"  )
	private String refPname;

	/**
	 * 项目经理名称
	 */
	@TableField(value = "ref_pmanager"  )
	private String refPmanager;

	/**
	 * 项目报告
	 */
	@TableField(value = "ref_preport"  )
	private String refPreport;

	/**
	 * 项目报告责任人
	 */
	@TableField(value = "ref_pmanager2"  )
	private String refPmanager2;

	/**
	 * 项目复核责任人
	 */
	@TableField(value = "recheck_man"  )
	private String recheckMan;

	/**
	 * 项目复核意见
	 */
	@TableField(value = "recheck_advice"  )
	private String recheckAdvice;

	/**
	 * 项目复核状态
	 */
	@TableField(value = "recheck_state"  )
	private String recheckState;

	/**
	 * 当前处理人
	 */
	@TableField(value = "curr_man"  )
	private String currMan;

	/**
	 * 
	 */
	@TableField(value = "dict_wf_state"  )
	private String dictWfState;

	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private Double remark;

	/**
	 * 
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;

	/**
	 * 
	 */
	@TableField(value = "update_id" , fill = FieldFill.INSERT_UPDATE  )
	private String updateId;


}
