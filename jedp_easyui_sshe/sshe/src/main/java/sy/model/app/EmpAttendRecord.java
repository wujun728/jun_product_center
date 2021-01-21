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
@Table(name = "emp_attend_record", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_attend_record", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_attend_record", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpAttendRecord implements Serializable {
	/**
	 * 1 有效 99 删除 2 审批通过
	 */
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_ACCESS = (byte) 2;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	
	/**
	 *  0 正常 1 加班 2 请假
	 */
	@Transient
	public static final byte IS_QINGJIA = (byte)2;
	@Transient
	public static final byte IS_JIABAN = (byte)1;
	@Transient
	public static final byte NO_LEAVE = (byte) 0;
	
	/**
	 * 加班（定性：0 未使用；1已调休；2费用结算） 
	 */
	@Transient
	public static final byte OP_RESULT_NEW = (byte)0;
	@Transient
	public static final byte OP_RESULT_TIAOXIU = (byte)1;
	@Transient
	public static final byte OP_RESULT_FEE = (byte)2;
	
	private int id;
	
	//private String userId;
	private Syuser syuser;

	private String yyyymmdd;

	private Date workTime;

	private Date afterWorkTime;
	//请假或者加班标志 1 加班 2 请假 0 正常
	private byte isLeave;
	//操作时间，对应加班小时数或者请假的小时数
	private int opTime;
	
	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;
	
	//员工的物理地址
	private String phyAddr;
	//定性
	private byte opResult;
	
	@Column(name = "YYYYMMDD", nullable = false, columnDefinition = "varchar(10) not null  comment '考勤日期'")
	public String getYyyymmdd() {
		return yyyymmdd;
	}
	
	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_attend_record")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
//
//	@Column(name = "USER_ID", nullable = false, length = 36, columnDefinition = "varchar(36) not null   comment '账户id'")
//	public String getUserId() {
//		return userId;
//	}
//
//	/**
//	 * @param userId
//	 *            the userId to set
//	 */
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID",columnDefinition="varchar(36)   NULL default null comment '用户编号'")
	public Syuser getSyuser() {
		return syuser;
	}
	
	/**
	 * @param syuser the syuser to set
	 */
	public void setSyuser(Syuser syuser) {
		this.syuser = syuser;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WORK_TIME", nullable = true, columnDefinition = "datetime   null   comment '上班时间'")
	public Date getWorkTime() {
		//if (this.workTime != null)
			return this.workTime;
		//return new Date();
	}
	
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AFTER_WORK_TIME", nullable = true, columnDefinition = "datetime   null   comment '下班时间'")
	public Date getAfterWorkTime() {
		//if (this.afterWorkTime != null)
			return this.afterWorkTime;
		//return new Date();
	}
	
	public void setAfterWorkTime(Date afterWorkTime) {
		this.afterWorkTime = afterWorkTime;
	}

	@Column(name = "IS_LEAVE", nullable = true, columnDefinition = "tinyint   default 0   comment '是否请假 0 正常 1 加班 2 请假'")
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
	 * @param phyAddr the phyAddr to set
	 */
	public void setPhyAddr(String phyAddr) {
		this.phyAddr = phyAddr;
	}
	
	@Column(name = "OP_TIME", nullable = true, columnDefinition = "int default 0 comment '操作时间'")
	public int getOpTime() {
		return opTime;
	}
	
	/**
	 * @param opTime the opTime to set
	 */
	public void setOpTime(int opTime) {
		this.opTime = opTime;
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

}