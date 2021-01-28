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
 * 员工加班考情记录--明细
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "emp_jiaban_record_detail", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_jiaban_record_detail", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_jiaban_record_detail", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpJiabanRecordDetail implements Serializable {
	/**
	 * 1 有效 99 删除
	 * 
	 * 0 保存(新建) 1 提交(等待审批) 2 审批通过(syuser不能再修改了) 3 驳回 99删除状态
	 */
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

	private int id;
	private String opOrder;
	// 请假人或者加班人
	private Syuser syuser;
	private byte status;
	private byte newStatus;

	// 创建人
	private String opUserId;

	private Date createTime;
	private Date updateTime;
	private String ext1;

	// 员工的物理地址
	private String phyAddr;
	//原因
	private String reason;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_jiaban_record_detail")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint null comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Column(name = "NEW_STATUS", nullable = true, columnDefinition = "tinyint null comment '新状态'")
	public byte getNewStatus() {
		return newStatus;
	}

	/**
	 * @param newStatus
	 *            the newStatus to set
	 */
	public void setNewStatus(byte newStatus) {
		this.newStatus = newStatus;
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

	@Column(name = "OP_ORDER", nullable = false, columnDefinition = "varchar(20) not null  comment '操作流水号'")
	public String getOpOrder() {
		return opOrder;
	}	
	
	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}
	
	@Column(name = "REASON", nullable = true, columnDefinition = "varchar(200)   null comment '原因'")
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}