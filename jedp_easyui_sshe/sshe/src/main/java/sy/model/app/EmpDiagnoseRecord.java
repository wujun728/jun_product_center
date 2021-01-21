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

import sy.model.base.Syuser;

@SuppressWarnings("serial")
@Entity
@Table(name = "emp_diagnose_record", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_diagnose_record", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_diagnose_record", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpDiagnoseRecord implements Serializable {
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	private int medicalId;

	// private String userId;
	//private Syuser syuser;
	private CustUser custUser;

	private Date diagTime;

	private String sgiNo;
	//给药或者建议
	private String propose;
	//主诉
	private String mainInfo;
	//初步判断
	private String firstStep;
	
	//private int phyId;
	private PhysicalTypeDef physicalTypeDef;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;

	@Id
	@Column(name = "MEDICAL_ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '就诊编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_diagnose_record")
	public int getMedicalId() {
		return medicalId;
	}
	
	public void setMedicalId(int medicalId) {
		this.medicalId = medicalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", columnDefinition = "int(10) NULL default null comment '用户编号'")
	public CustUser getCustUser() {
		return custUser;
	}
	
	/**
	 * @param syuser
	 *            the syuser to set
	 */
	public void setCustUser(CustUser custUser) {
		this.custUser = custUser;
	}
	
	@Column(name = "DIAG_TIME", nullable = false, columnDefinition = "datetime null  comment '就诊时间'")
	public Date getDiagTime() {
		return diagTime;
	}

	public void setDiagTime(Date diagTime) {
		this.diagTime = diagTime;
	}

	@Column(name = "SGI_NO", nullable = true, columnDefinition = "varchar(30) default null  comment 'SGI编号'")
	public String getSgiNo() {
		return sgiNo;
	}

	public void setSgiNo(String sgiNo) {
		this.sgiNo = sgiNo;
	}

	@Column(name = "PROPOSE", nullable = true, columnDefinition = "varchar(500) default null  comment '给药建议'")
	public String getPropose() {
		return propose;
	}

	public void setPropose(String propose) {
		this.propose = propose;
	}

	@Column(name = "MAIN_INFO", nullable = true, columnDefinition = "varchar(500) default null  comment '主诉'")
	public String getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(String mainInfo) {
		this.mainInfo = mainInfo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHY_ID", nullable = true, columnDefinition = "int(8)    null  comment '体格检查类型'")
	public PhysicalTypeDef getPhysicalTypeDef() {
		return physicalTypeDef;
	}
	
	public void setPhysicalTypeDef(PhysicalTypeDef physicalTypeDef) {
		this.physicalTypeDef = physicalTypeDef;
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

	@Column(name = "FIRST_STEP", nullable = true, columnDefinition = "varchar(500) default null  comment '初步判断'")
	public String getFirstStep() {
		return firstStep;
	}	
	
	/**
	 * @param firstStep the firstStep to set
	 */
	public void setFirstStep(String firstStep) {
		this.firstStep = firstStep;
	}

}