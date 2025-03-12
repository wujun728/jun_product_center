package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 员工请假
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:32
 */
@Data
@TableName("oa_poms_workmarks_leave")
public class OaPomsWorkmarksLeaveEntity extends BaseFlowEntity /* flow_test1 */ implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 员工工号
	 */
	@TableField(value = "ref_usercoce"  )
	private String refUsercoce;
	/**
	 * 员工姓名
	 */
	@TableField(value = "ref_username"  )
	private String refUsername;
	/**
	 * 员工部门
	 */
	@TableField(value = "ref_userdept"  )
	private String refUserdept;
	/**
	 * 请假日期
	 */
	@TableField(value = "leave_date"  )
//	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private String leaveDate;
	/**
	 * 请假结束日期
	 */
	@TableField(value = "leave_date_end"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date leaveDateEnd;
	/**
	 * 请假类型
	 */
	@TableField(value = "dict_leanve_type"  )
	private String dictLeanveType;
	/**
	 * 请假原因
	 */
	@TableField(value = "leave_desc"  )
	private String leaveDesc;
	/**
	 * 请假小时数
	 */
	@TableField(value = "leave_hours"  )
	private BigDecimal leaveHours;
	/**
	 * 实际请假小时数
	 */
	@TableField(value = "leave_hours2"  )
	private BigDecimal leaveHours2;
	/**
	 * 审批状态
	 */
	@TableField(value = "dict_approve_status"  )
	private String dictApproveStatus;
	/**
	 * 当前处理人
	 */
	@TableField(value = "ref_curr_todo_person"  )
	private String refCurrTodoPerson;
	/**
	 * 填报人
	 */
	@TableField(value = "creator" , fill = FieldFill.INSERT  )
	private String creator;
	
	@TableField(value = "create_id" , fill = FieldFill.INSERT  )
	private String createId;
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
	@TableField(value = "update_time" , fill = FieldFill.INSERT_UPDATE  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;
	/**
	 * 
	 */
	@TableField(value = "update_id" , fill = FieldFill.INSERT_UPDATE  )
	private String updateId;

}
