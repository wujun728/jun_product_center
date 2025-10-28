package com.jun.plugin.qixing.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jun.plugin.common.entity.BaseFlowEntity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 离职
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-10 17:55:36
 */
@Data
@TableName("hr_user_dimission")
public class HrUserDimissionEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Id
	@TableId(value = "id",type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 工号
	 */
	@TableField(value = "usercode"  )
	private String usercode;
	/**
	 * 姓名
	 */
	@TableField(value = "username"  )
	private String username;
	/**
	 * 部门
	 */
	@TableField(value = "deptname"  )
	private String deptname;
	/**
	 * 职务
	 */
	@TableField(value = "postname"  )
	private String postname;
	/**
	 * 员工类型
	 */
	@TableField(value = "usertype"  )
	private String usertype;
	/**
	 * 入职日期
	 */
	@TableField(value = "entry_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date entryTime;
	/**
	 * 合同结束日期
	 */
	@TableField(value = "contract_end_time"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date contractEndTime;
	/**
	 * 项目组
	 */
	@TableField(value = "project_name"  )
	private String projectName;
	/**
	 * 工作地点
	 */
	@TableField(value = "work_location"  )
	private String workLocation;
	/**
	 * 离职原因分类
	 */
	@TableField(value = "leanve_reason_type"  )
	private String leanveReasonType;
	/**
	 * 离职原因详细
	 */
	@TableField(value = "leanve_reason_detail"  )
	private String leanveReasonDetail;
	/**
	 * 是否需要离职证明
	 */
	@TableField(value = "is_need_leave_prove"  )
	private String isNeedLeaveProve;
	/**
	 * 离职证明领取方式
	 */
	@TableField(value = "prove_get_way"  )
	private String proveGetWay;
	/**
	 * 离职证明邮寄地址
	 */
	@TableField(value = "prove_get_adress"  )
	private String proveGetAdress;
	/**
	 * 收件人
	 */
	@TableField(value = "prove_get_people"  )
	private String proveGetPeople;
	/**
	 * 收件电话
	 */
	@TableField(value = "prove_get_phone"  )
	private String proveGetPhone;
	/**
	 * 直接上级替换人工号
	 */
	@TableField(value = "leader_usercode"  )
	private String leaderUsercode;
	/**
	 * 直接上级替换人姓名
	 */
	@TableField(value = "leader_username"  )
	private String leaderUsername;
	/**
	 * 最后工作日
	 */
	@TableField(value = "last_work_day"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date lastWorkDay;
	/**
	 * 薪资结算日
	 */
	@TableField(value = "pay_on_last_day"  )
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date payOnLastDay;
	/**
	 * 是否涉及工作交接
	 */
	@TableField(value = "is_need_job_handover"  )
	private String isNeedJobHandover;
	/**
	 * 工作交接是否完成
	 */
	@TableField(value = "is_handover_filish"  )
	private String isHandoverFilish;
	/**
	 * 申请时间
	 */
	@TableField(value = "create_time" , fill = FieldFill.INSERT  )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 流程状态
	 */
	@TableField(value = "wfsate"  )
	private String wfsate;
	/**
	 * 当前节点名称
	 */
	@TableField(value = "curr_nodename"  )
	private String currNodename;
	/**
	 * 当前节点审批人
	 */
	@TableField(value = "curr_node_username"  )
	private String currNodeUsername;
	/**
	 * 备注
	 */
	@TableField(value = "remark"  )
	private String remark;
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
