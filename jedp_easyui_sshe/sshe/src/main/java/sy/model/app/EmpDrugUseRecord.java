package sy.model.app;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "emp_drug_use_record", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_drug_use_record", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_drug_use_record", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpDrugUseRecord implements Serializable {
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	/**
	 * 甲乙丙 1 2 3
	 */
	@Transient
	public static final byte EB_TYPE_A = (byte) 1;
	@Transient
	public static final byte EB_TYPE_B = (byte) 2;
	@Transient
	public static final byte EB_TYPE_C = (byte) 3;
	
	private int id;

	private int medicalId;

//	private String drugCode;
	private DrugTimes drugTimes;
	
//	private String drugName;

	private BigDecimal price;

	private int number;

	private BigDecimal medicalFee;

	private BigDecimal empTakeFee;

	private BigDecimal insuranceFee;
	/**
	 * 医保类型
	 */
	private byte ebType;
//	private String opUserId;
	private Syuser syuser;
	
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '序号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_drug_use_record")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "MEDICAL_ID", unique = false, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '就诊编号'")
	public int getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(int medicalId) {
		this.medicalId = medicalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DRUG_TIMES_ID", columnDefinition = "int(10) not null   comment '药品批次编号'")
	public DrugTimes getDrugTimes() {
		return drugTimes;
	}
	
	/**
	 * @param drugInfo
	 *            the drugInfo to set
	 */
	public void setDrugTimes(DrugTimes drugTimes) {
		this.drugTimes = drugTimes;
	}
	
//	@Column(name = "DRUG_NAME", nullable = true, columnDefinition = "varchar(30) default null  comment '药品名称'")
//	public String getDrugName() {
//		return drugName;
//	}
//
//	public void setDrugName(String drugName) {
//		this.drugName = drugName;
//	}

	@Column(name = "PRICE", nullable = true, columnDefinition = "numeric(8,2) default 0  comment '单价'")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "NUMBER", nullable = true, columnDefinition = "int default 0  comment '数量'")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Column(name = "MEDICAL_FEE", nullable = true, columnDefinition = "numeric(8,2) default 0  comment '医疗费用'")
	public BigDecimal getMedicalFee() {
		return medicalFee;
	}

	public void setMedicalFee(BigDecimal medicalFee) {
		this.medicalFee = medicalFee;
	}
	
	@Transient
	@Column(name = "EMP_TAKE_FEE", nullable = true, columnDefinition = "numeric(8,2) default 0  comment '员工承担费用'")
	public BigDecimal getEmpTakeFee() {
		return empTakeFee;
	}
	
	public void setEmpTakeFee(BigDecimal empTakeFee) {
		this.empTakeFee = empTakeFee;
	}
	
	@Transient
	@Column(name = "INSURANCE_FEE", nullable = true, columnDefinition = "numeric(8,2) default 0  comment '保险公司承担费用'")
	public BigDecimal getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(BigDecimal insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

//	/**
//	 * @return the opUserId
//	 */
//	@Column(name = "OP_USER_ID", nullable = true, columnDefinition = "varchar(36) default  0   comment '创建人'")
//	public String getOpUserId() {
//		return opUserId;
//	}
//
//	public void setOpUserId(String opUserId) {
//		this.opUserId = opUserId;
//	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OP_USER_ID",nullable = true, columnDefinition = "varchar(36)   NULL default null comment '创建人'")
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

	@Column(name = "EB_TYPE", nullable = true, columnDefinition = "tinyint default 1 comment '医保类型: 1甲2乙3丙'")
	public byte getEbType() {
		return ebType;
	}
	
	/**
	 * @param ebType the ebType to set
	 */
	public void setEbType(byte ebType) {
		this.ebType = ebType;
	}

}