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
 * 工资审核发放
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-11-09 09:39:33
 */
@Data
@TableName("oa_poms_workmarks_payroll")
public class OaPomsWorkmarksPayrollEntity extends BaseFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId("id")
	private String id;
	/**
	 * 工号
	 */
	@TableField(value = "usercode"  )
	private String usercode;
	/**
	 * 用户名称
	 */
	@TableField(value = "ref_username"  )
	private String refUsername;
	/**
	 * 部门
	 */
	@TableField(value = "deptname"  )
	private String deptname;
	/**
	 * 岗位
	 */
	@TableField(value = "workrole"  )
	private String workrole;
	/**
	 * 所属月份
	 */
	@TableField(value = "month"  )
	private String month;
	/**
	 * 实发工资
	 */
	@TableField(value = "payroll_act"  )
	private BigDecimal payrollAct;
	/**
	 * 工资期间
	 */
	@TableField(value = "payroll_datatime_se"  )
	private String payrollDatatimeSe;
	/**
	 * 标准月薪
	 */
	@TableField(value = "payroll_base"  )
	private BigDecimal payrollBase;
	/**
	 * 本月月薪
	 */
	@TableField(value = "payroll_cur_mouth"  )
	private BigDecimal payrollCurMouth;
	/**
	 * 税前补发
	 */
	@TableField(value = "tax_before_bufa"  )
	private BigDecimal taxBeforeBufa;
	/**
	 * 税前扣款
	 */
	@TableField(value = "tax_before_koukuan"  )
	private BigDecimal taxBeforeKoukuan;
	/**
	 * 请假小时数
	 */
	@TableField(value = "hours_qingjia"  )
	private BigDecimal hoursQingjia;
	/**
	 * 缺勤小时数
	 */
	@TableField(value = "hours_queqing"  )
	private BigDecimal hoursQueqing;
	/**
	 * 收入
	 */
	@TableField(value = "money_all_in"  )
	private BigDecimal moneyAllIn;
	/**
	 * 社保扣款
	 */
	@TableField(value = "sb_koukuan"  )
	private BigDecimal sbKoukuan;
	/**
	 * 公积金扣款
	 */
	@TableField(value = "gjj_koukuan"  )
	private BigDecimal gjjKoukuan;
	/**
	 * 个税
	 */
	@TableField(value = "geshui"  )
	private BigDecimal geshui;
	/**
	 * 税后补发
	 */
	@TableField(value = "tax_after_bufa"  )
	private BigDecimal taxAfterBufa;
	/**
	 * 税后扣款
	 */
	@TableField(value = "tax_after_koukuan"  )
	private BigDecimal taxAfterKoukuan;
	/**
	 * 实际出勤天数
	 */
	@TableField(value = "work_day_count"  )
	private Integer workDayCount;
	/**
	 * 当月应出勤天数
	 */
	@TableField(value = "sholdbe_work_day_count"  )
	private Integer sholdbeWorkDayCount;
	/**
	 * 工作时长(小时)
	 */
	@TableField(value = "work_total_hours"  )
	private BigDecimal workTotalHours;
	/**
	 * 应出勤时长(小时)
	 */
	@TableField(value = "shoulbe_work_hours"  )
	private BigDecimal shoulbeWorkHours;
	/**
	 * 工资审核状态
	 */
	@TableField(value = "payroll_state"  )
	private String payrollState;
	/**
	 * 工资审核意见
	 */
	@TableField(value = "payroll_state_msg"  )
	private String payrollStateMsg;
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
	/**
	 * 
	 */
	@TableField(value = "deleted" , fill = FieldFill.INSERT  )
	private Integer deleted;

}
