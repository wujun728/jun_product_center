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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "emp_his", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_his", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_his", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpHis implements Serializable {
	
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	
	
	private int hisId;
	
	private CustUser custUser;

	private String workComp;

	private Date startTime;

	private Date endTime;

	private String workShop;

	private int workType;

	private String harmful;

	private String protect;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;
	
	@Id
	@Column(name = "HIS_ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '就诊编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_his")
	public int getHisId() {
		return hisId;
	}
	
	public void setHisId(int hisId) {
		this.hisId = hisId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", columnDefinition = "int(10)   NULL default null comment '用户编号'")
	public CustUser getCustUser() {
		return custUser;
	}

	/**
	 * @param custUser
	 *            the custUser to set
	 */
	public void setCustUser(CustUser custUser) {
		this.custUser = custUser;
	}

	@Column(name = "WORK_COMP", nullable = true, columnDefinition = "varchar(50) default null  comment '工作单位'")
	public String getWorkComp() {
		return workComp;
	}

	public void setWorkComp(String workComp) {
		this.workComp = workComp;
	}
	
	@Column(name = "START_TIME", nullable = true, columnDefinition = "datetime   null   comment '开始时间'")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME", nullable = true, columnDefinition = "datetime   null   comment '结束时间'")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "WORK_SHOP", nullable = true, columnDefinition = "varchar(100) default null  comment '车间'")
	public String getWorkShop() {
		return workShop;
	}

	public void setWorkShop(String workShop) {
		this.workShop = workShop;
	}

	@Column(name = "WORK_TYPE", nullable = true, columnDefinition = "int(8) default null  comment '工种'")
	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}

	@Column(name = "HARMFUL", nullable = true, columnDefinition = "varchar(100) default null  comment '有害因素'")
	public String getHarmful() {
		return harmful;
	}

	public void setHarmful(String harmful) {
		this.harmful = harmful;
	}

	@Column(name = "PROTECT", nullable = true, columnDefinition = "varchar(100) default null  comment '防护措施'")
	public String getProtect() {
		return protect;
	}

	public void setProtect(String protect) {
		this.protect = protect;
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
}