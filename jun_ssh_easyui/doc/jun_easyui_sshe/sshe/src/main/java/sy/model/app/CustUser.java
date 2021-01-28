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

@SuppressWarnings("serial")
@Entity
@Table(name = "cust_user", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_cust_user", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_cust_user", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class CustUser implements Serializable {
	/**
	 * 1 在职 2 离职 3 待业 99 删除
	 */
	@Transient
	public static final byte CUSTUSER_STATUS_NEW = (byte) 0;
	@Transient
	public static final byte CUSTUSER_STATUS_VALID = (byte) 1;
	@Transient
	public static final byte CUSTUSER_STATUS_INVALID = (byte) 2;
	@Transient
	public static final byte CUSTUSER_STATUS_DELETED = (byte) 99;

	private int userId;
	
	private String userCode;
	
	private CustomerInfo customerInfo;
	
	private CustDept custDept;
	// private int deptId;

	private String userName;

	private String firstName;

	private String lastName;

	private String phone;

	private byte sex;

	private Date employeTime;

	private Date outdutyTime;

	private int workYears;

	private String address;

	private String email;

	private Date birthdate;

	private String specialty;

	private String hobbies;

	private String icon;

	private byte onJob;

	private String positionZh;

	private String positionEn;

	private byte positionType;

	private BigDecimal costCenter;

	private String labor;

	private int certificateType;

	private String certificate;

	private Date groupDate;

	private Date entryDate;

	private byte marriage;

	private String nationality;

	private String jzContact2;

	private String jzContact1;

	private String jhrContact;

	private String jrContact;

	private byte isEmpHurt;

	private String ext2;

	private String ext3;

	private String ext4;

	private byte status;

	private String opUserId;

	private Date createTime;

	private Date updateTime;
	private String ext1;

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '员工编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_cust_user")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "USER_CODE", nullable = true, length = 20, columnDefinition = "varchar(20) default null comment '员工代码'")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = true, columnDefinition = "int(10) null comment '客户编号'")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	// @Column(name = "DEPT_ID", nullable = true, length = 10, columnDefinition
	// = "int(10 ) null comment '部门编号'")
	// public int getDeptId() {
	// return deptId;
	// }
	//
	// public void setDeptId(int deptId) {
	// this.deptId = deptId;
	// }

	/**
	 * @return the custDept
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID", nullable = true, columnDefinition = "int(10) null comment '部门编号'")
	public CustDept getCustDept() {
		return custDept;
	}
	
	/**
	 * @param custDept
	 *            the custDept to set
	 */
	public void setCustDept(CustDept custDept) {
		this.custDept = custDept;
	}

	@Column(name = "USER_NAME", nullable = true, length = 30, columnDefinition = "varchar(30) null comment '员工姓名'")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "FIRST_NAME", nullable = true, length = 30, columnDefinition = "varchar(30) null comment 'First Name'")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = true, length = 30, columnDefinition = "varchar(30) null comment 'Last Name'")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "PHONE", nullable = true, length = 20, columnDefinition = "varchar(20) null comment '手机号码'")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "SEX", nullable = true, columnDefinition = "tinyint null comment '性别'")
	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	@Column(name = "EMPLOYE_TIME", nullable = true, columnDefinition = "datetime null comment '入职时间'")
	public Date getEmployeTime() {
		return employeTime;
	}

	public void setEmployeTime(Date employeTime) {
		this.employeTime = employeTime;
	}

	@Column(name = "OUTDUTY_TIME", nullable = true, columnDefinition = "datetime null comment '离职时间'")
	public Date getOutdutyTime() {
		return outdutyTime;
	}

	public void setOutdutyTime(Date outdutyTime) {
		this.outdutyTime = outdutyTime;
	}

	@Column(name = "WORK_YEARS", nullable = true, length = 10, columnDefinition = "int(10) null comment '工作年限'")
	public int getWorkYears() {
		return workYears;
	}

	public void setWorkYears(int workYears) {
		this.workYears = workYears;
	}

	@Column(name = "ADDRESS", nullable = true, length = 80, columnDefinition = "varchar(80) null comment '员工简历地址'")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "EMAIL", nullable = true, length = 20, columnDefinition = "varchar(20) null comment 'Email'")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "BIRTHDATE", nullable = true, columnDefinition = "datetime null comment '出生日期'")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "SPECIALTY", nullable = true, length = 80, columnDefinition = "varchar(80) null comment 'Last Name'")
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Column(name = "HOBBIES", nullable = true, length = 80, columnDefinition = "varchar(80) null comment '兴趣爱好'")
	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Column(name = "ICON", nullable = true, columnDefinition = "varchar(40) null comment 'ICON'")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "ON_JOB", nullable = true, columnDefinition = "tinyint null comment '在职情况'")
	public byte getOnJob() {
		return onJob;
	}

	public void setOnJob(byte onJob) {
		this.onJob = onJob;
	}

	@Column(name = "POSITION_ZH", nullable = true, length = 240, columnDefinition = "varchar(240) null comment '岗位中文'")
	public String getPositionZh() {
		return positionZh;
	}

	public void setPositionZh(String positionZh) {
		this.positionZh = positionZh;
	}

	@Column(name = "POSITION_EN", nullable = true, length = 240, columnDefinition = "varchar(240) null comment '岗位英文'")
	public String getPositionEn() {
		return positionEn;
	}

	public void setPositionEn(String positionEn) {
		this.positionEn = positionEn;
	}

	@Column(name = "POSITION_TYPE", nullable = true, columnDefinition = "tinyint null comment '岗位类别'")
	public byte getPositionType() {
		return positionType;
	}

	public void setPositionType(byte positionType) {
		this.positionType = positionType;
	}

	@Column(name = "COST_CENTER", nullable = true, precision = 10, scale = 2, columnDefinition = "numeric(10,2) null comment '成本中心'")
	public BigDecimal getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(BigDecimal costCenter) {
		this.costCenter = costCenter;
	}

	@Column(name = "LABOR", nullable = true, length = 40, columnDefinition = "varchar(40) null comment '劳动关系'")
	public String getLabor() {
		return labor;
	}

	public void setLabor(String labor) {
		this.labor = labor;
	}

	@Column(name = "CERTIFICATE_TYPE", nullable = true, columnDefinition = "int(11) null comment '证件类型'")
	public int getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(int certificateType) {
		this.certificateType = certificateType;
	}

	@Column(name = "CERTIFICATE", nullable = true, columnDefinition = "varchar(40) null comment '证件号码'")
	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	@Column(name = "GROUP_DATE", nullable = true, columnDefinition = "datetime null comment '入集团日期'")
	public Date getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}

	@Column(name = "ENTRY_DATE", nullable = true, columnDefinition = "datetime null comment '入司日期'")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "MARRIAGE", nullable = true, columnDefinition = "tinyint null comment '婚姻状况'")
	public byte getMarriage() {
		return marriage;
	}

	public void setMarriage(byte marriage) {
		this.marriage = marriage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = true, columnDefinition = "datetime null comment '创建时间'")
	public Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME", nullable = true, columnDefinition = "datetime null comment '修改时间'")
	public Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return new Date();
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint null comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Column(name = "NATIONALITY", nullable = true, length = 40, columnDefinition = "varchar(40) null comment '国籍'")
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "JZ_CONTACT2", nullable = true, length = 40, columnDefinition = "varchar(40) null comment '家长联系方式2'")
	public String getJzContact2() {
		return jzContact2;
	}

	public void setJzContact2(String jzContact2) {
		this.jzContact2 = jzContact2;
	}

	@Column(name = "JZ_CONTACT1", nullable = true, length = 40, columnDefinition = "varchar(40) null comment '家长联系方式1'")
	public String getJzContact1() {
		return jzContact1;
	}

	public void setJzContact1(String jzContact1) {
		this.jzContact1 = jzContact1;
	}

	@Column(name = "JHR_CONTACT", nullable = true, length = 40, columnDefinition = "varchar(40) null comment '监护人联系方式'")
	public String getJhrContact() {
		return jhrContact;
	}

	public void setJhrContact(String jhrContact) {
		this.jhrContact = jhrContact;
	}

	@Column(name = "JR_CONTACT", nullable = true, length = 40, columnDefinition = "varchar(40) null comment '家人联系方式'")
	public String getJrContact() {
		return jrContact;
	}

	public void setJrContact(String jrContact) {
		this.jrContact = jrContact;
	}

	@Column(name = "IS_EMP_HURT", nullable = true, columnDefinition = "tinyint null comment '是否接触职业危害因素'")
	public byte getIsEmpHurt() {
		return isEmpHurt;
	}

	public void setIsEmpHurt(byte isEmpHurt) {
		this.isEmpHurt = isEmpHurt;
	}

	@Column(name = "EXT1", nullable = true, length = 40, columnDefinition = "varchar(40) null comment 'EXT1'")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT2", nullable = true, length = 40, columnDefinition = "varchar(40) null comment 'EXT2'")
	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT3", nullable = true, length = 40, columnDefinition = "varchar(40) null comment 'EXT3'")
	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT4", nullable = true, length = 40, columnDefinition = "varchar(40)  null comment 'EXT4'")
	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, columnDefinition = "varchar(36)   default 0   comment '创建人'")
	public String getOpUserId() {
		return opUserId;
	}

	/**
	 * @param opUserId
	 *            the opUserId to set
	 */
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
}