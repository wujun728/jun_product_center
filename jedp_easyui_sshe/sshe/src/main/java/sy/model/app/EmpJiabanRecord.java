package sy.model.app;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import sy.model.base.Syuser;

/**
 * 员工考勤记录
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "emp_jiaban_record", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
//@TableGenerator(name = "SEQ_EMP_JIABAN_RECORD", // 别名
//table = "GENERATOR_TABLE", // 生成的表名
//pkColumnName = "sequence_name", // key列名
//valueColumnName = "next_val", // value列名
//pkColumnValue = "SEQ_EMP_JIABAN_RECORD", // 具体key内容
//initialValue = 1, // 初始值
//allocationSize = 1)
//// 增长值
public class EmpJiabanRecord implements Serializable {
	/**
	 * 1 有效 99 删除
	 * 
	 * -1 取消 0 保存(新建) 1 提交(等待审批) 2 审批通过(syuser不能再修改了) 3 驳回 99删除状态
	 */
	@Transient
	public static final byte STATUS_CANCEL = (byte) -1;
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_SUBMIT = (byte) 1;
	@Transient
	public static final byte STATUS_ACCESS = (byte) 2;
	@Transient
	public static final byte STATUS_BACKEND = (byte) 3;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;

	/**
	 * 加班（定性：0 未使用；1已调休；2费用结算） 
	 */
	@Transient
	public static final byte OP_RESULT_NEW = (byte)0;
	@Transient
	public static final byte OP_RESULT_TIAOXIU = (byte)1;
	@Transient
	public static final byte OP_RESULT_FEE = (byte)2;
	
	
	/**
	 * 0 没有请假 1 请假
	 */
	@Transient
	public static final byte IS_LEAVE = (byte) 1;
	@Transient
	public static final byte NO_LEAVE = (byte) 0;
	
	private int id;
	/**
	 * 1 加班 2 请假
	 */
	private int type;
	/**
	 * 子类型
	 */
	private int subType;
	
	private Syuser syuser;
	private String opOrder;

	private String yyyymmdd;
	
	private Date startTime;
	
	private Date endTime;
	
	// 申请加班小时数
	private int requiredTime;
	// 实际加班小时数
	private int realTime;

	// 是否请假
	private byte isLeave;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;
	
	// 员工的物理地址
	private String phyAddr;
	// 冗余一个字段。 用于界面中的原因提交
	 
	private String reason;

	//定性
	private byte opResult;
	//时间单位
	private String unit;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null AUTO_INCREMENT  comment '编号'")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "YYYYMMDD", nullable = false, columnDefinition = "varchar(10) not null  comment '考勤日期'")
	public String getYyyymmdd() {
		return yyyymmdd;
	}

	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", columnDefinition = "varchar(36)   NULL default null comment '用户编号'")
	public Syuser getSyuser() {
		return syuser;
	}

	/**
	 * @param syuser
	 *            the syuser to set
	 */
	public void setSyuser(Syuser syuser) {
		this.syuser = syuser;
	}

	@Column(name = "IS_LEAVE", nullable = true, columnDefinition = "tinyint   null   comment '是否请假 0 没有请假 1 请假'")
	public byte getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(byte isLeave) {
		this.isLeave = isLeave;
	}

	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, columnDefinition = "varchar(36) default  0   comment '创建人'")
	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = true, columnDefinition = "datetime null  comment '创建时间'")
	public Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME", nullable = true, columnDefinition = "datetime   null  comment '修改时间'")
	public Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return new Date();
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "EXT1", nullable = true, columnDefinition = "varchar(200)   null comment '备注1'")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint null comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Column(name = "PHY_ADDR", nullable = true, columnDefinition = "varchar(20) default null  comment '员工绑定的物理地址'")
	public String getPhyAddr() {
		return phyAddr;
	}
	
	/**
	 * @param phyAddr
	 *            the phyAddr to set
	 */
	public void setPhyAddr(String phyAddr) {
		this.phyAddr = phyAddr;
	}

	@Column(name = "TYPE", nullable = true, columnDefinition = "tinyint not null default 1  comment ' 1 加班 2 请假'")
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * @return the opOrder
	 */
	@Column(name = "OP_ORDER", nullable = false, columnDefinition = "varchar(20) not null  comment '操作流水号'")
	public String getOpOrder() {
		return opOrder;
	}

	/**
	 * @param opOrder
	 *            the opOrder to set
	 */
	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}

	/**
	 * @return the startTime
	 */
	@Column(name = "START_TIME", nullable = true, columnDefinition = "datetime not null  comment '开始时间'")
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	@Column(name = "END_TIME", nullable = true, columnDefinition = "datetime not null  comment '结束时间'")
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the requiredTime
	 */
	@Column(name = "REQUIRED_TIME", nullable = true, columnDefinition = "int not null  comment '加班申请时间数'")
	public int getRequiredTime() {
		return requiredTime;
	}

	/**
	 * @param requiredTime
	 *            the requiredTime to set
	 */
	public void setRequiredTime(int requiredTime) {
		this.requiredTime = requiredTime;
	}

	/**
	 * @return the realTime
	 */
	@Column(name = "REAL_TIME", nullable = true, columnDefinition = "int not null default 0  comment '加班审批时间数'")
	public int getRealTime() {
		return realTime;
	}
	
	/**
	 * @param realTime
	 *            the realTime to set
	 */
	public void setRealTime(int realTime) {
		this.realTime = realTime;
	}

	/**
	 * @return the subType
	 */
	@Column(name = "SUB_TYPE", nullable = true, columnDefinition = "int(8) not null default 1  comment ' 1 加班 2 请假'")
	public int getSubType() {
		return subType;
	}

	/**
	 * @param subType the subType to set
	 */
	public void setSubType(int subType) {
		this.subType = subType;
	}

	/**
	 * @return the reason
	 */
	@Transient
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "OP_RESULT", nullable = true, columnDefinition = "tinyint   default 0   comment '加班（定性：0 未使用；1已调休；2费用结算） '")
	public byte getOpResult() {
		return opResult;
	}
	
	/**
	 * @param opResult the opResult to set
	 */
	public void setOpResult(byte opResult) {
		this.opResult = opResult;
	}
	
	@Column(name = "UNIT", nullable = true, length = 40, columnDefinition = "int(8) not null   comment '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}