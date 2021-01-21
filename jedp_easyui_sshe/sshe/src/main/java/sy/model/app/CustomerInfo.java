package sy.model.app;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "customer_info", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_customer_info", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_customer_info", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class CustomerInfo implements java.io.Serializable {
	@Transient
	public static final byte CUSTOMER_STATUS_NEW = (byte)0;
	@Transient
	public static final byte CUSTOMER_STATUS_VALID = (byte)1;
	@Transient
	public static final byte CUSTOMER_STATUS_INVALID = (byte)2;
	@Transient
	public static final byte CUSTOMER_STATUS_DELETED = (byte)99;
	/**
	 * 企业
	 */
	@Transient
	public static final byte CUSTOMER_TYPE_COMPANY = (byte)0;
	/**
	 * 学校 
	 */
	@Transient
	public static final byte CUSTOMER_TYPE_SCHOOL = (byte)1;
	
	
	private int customerId;
	private String customerName;
	private Byte isPerOrComp;
	private Byte certificateType;
	private String certificateNo;
	private String customerAddr;
	private String customerContact;
	private String postCode;
	private Byte enterpriseScale;
	private String otherContact;
	private String customerLogo;
	private String customerWebsite;
	private String legalPerson;
	private String repreUser;
	private String repreUserContact;
	private Date createTime;
	private Date updateTime;
	private Byte customerStatus;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private Byte customerType;
	private String customerEnName;
	private Date gotoWorkTime;
	private Date afterWorkTime;
	private String opUserId;
	private Set<Syuser> syusers = new HashSet<Syuser>(0);
	
	@Id
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '客户编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_customer_info")
	// @GeneratedValue(strategy = GenerationType.TABLE)
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CUSTOMER_NAME", nullable = false, length = 40, columnDefinition = "varchar(40) not null comment '客户名称'")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "IS_PER_OR_COMP", nullable = false, columnDefinition = "tinyint not null default 0  comment '企业还是个人 0 企业，1 个人'")
	public Byte getIsPerOrComp() {
		return isPerOrComp;
	}

	public void setIsPerOrComp(Byte isPerOrComp) {
		this.isPerOrComp = isPerOrComp;
	}

	@Column(name = "CERTIFICATE_TYPE", nullable = false, columnDefinition = "tinyint not null default 1  comment '1 身份证 2 护照 3 驾驶证 4 其他'")
	public Byte getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Byte certificateType) {
		this.certificateType = certificateType;
	}

	@Column(name = "CERTIFICATE_NO", nullable = true, columnDefinition = "tinyint   null default 0  comment '证件号码'")
	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	@Column(name = "CUSTOMER_ADDR", nullable = false, length = 80, columnDefinition = "varchar(80) not null default 0  comment '客户地址'")
	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	@Column(name = "CUSTOMER_CONTACT", nullable = false, length = 80, columnDefinition = "varchar(80) not null default 0  comment '客户联系方式'")
	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	@Column(name = "POST_CODE", nullable = true, length = 10, columnDefinition = "varchar(10) not null default 0  comment '邮编'")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "ENTERPRISE_SCALE", nullable = true, columnDefinition = "tinyint not null  default 0  comment '1 > 10 人以下  2 > 50 人以下 3 > 100 以下 4 > 500 人以下 5 > 1000 人以下 6 > 5000 人以下  7 > 10000 人以下   8 > 50000 人以下 9 > 100000 人以下10 > 更多'")
	public Byte getEnterpriseScale() {
		return enterpriseScale;
	}

	public void setEnterpriseScale(Byte enterpriseScale) {
		this.enterpriseScale = enterpriseScale;
	}

	@Column(name = "OTHER_CONTACT", nullable = true, columnDefinition = "varchar(50)   null   comment '其他联系方式'")
	public String getOtherContact() {
		return otherContact;
	}

	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}

	@Column(name = "CUSTOMER_LOGO", nullable = true, columnDefinition = "varchar(150)   null    comment '企业LOGO'")
	public String getCustomerLogo() {
		return customerLogo;
	}

	public void setCustomerLogo(String customerLogo) {
		this.customerLogo = customerLogo;
	}

	@Column(name = "CUSTOMER_WEBSITE", nullable = true, columnDefinition = "varchar(80)  default null   comment '企业官网'")
	public String getCustomerWebsite() {
		return customerWebsite;
	}

	public void setCustomerWebsite(String customerWebsite) {
		this.customerWebsite = customerWebsite;
	}

	@Column(name = "LEGAL_PERSON", nullable = true, columnDefinition = "varchar(30) default null comment '企业法人'")
	public String getLegalPerson() {
		return legalPerson;
	}
	
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	@Column(name = "REPRE_USER", nullable = true, columnDefinition = "varchar(30) null comment '企业代表'")
	public String getRepreUser() {
		return repreUser;
	}

	public void setRepreUser(String repreUser) {
		this.repreUser = repreUser;
	}

	@Column(name = "REPRE_USER_CONTACT", nullable = true, columnDefinition = "varchar(50) null comment '代表联系方式'")
	public String getRepreUserContact() {
		return repreUserContact;
	}

	public void setRepreUserContact(String repreUserContact) {
		this.repreUserContact = repreUserContact;
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

	@Column(name = "CUSTOMER_STATUS", nullable = true, columnDefinition = "tinyint not null default 0  comment '客户状态 0 新建 1 合作有效2 合作无效'")
	public Byte getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(Byte customerStatus) {
		this.customerStatus = customerStatus;
	}

	@Column(name = "EXT1", nullable = true, columnDefinition = "varchar(200)   null comment '备注1'")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT2", nullable = true, columnDefinition = "varchar(200)   null  comment '备注2'")
	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT3", nullable = true, columnDefinition = "varchar(200)   null  comment '备注3'")
	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT4", nullable = true, columnDefinition = "varchar(200)   null   comment '备注4'")
	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	@Column(name = "CUSTOMER_TYPE", nullable = true, columnDefinition = "tinyint not null default 0  comment '客户类型 类型为   0企业  ,    1 学校，学校的学生采用家长联系方式；企业的员工， 家人联系方式'")
	public Byte getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Byte customerType) {
		this.customerType = customerType;
	}

	@Column(name = "CUSTOMER_EN_NAME", nullable = true, columnDefinition = "varchar(150)   null   comment '客户英文名称'")
	public String getCustomerEnName() {
		return customerEnName;
	}

	public void setCustomerEnName(String customerEnName) {
		this.customerEnName = customerEnName;
	}

	@Column(name = "GOTO_WORK_TIME", nullable = true, columnDefinition = "datetime   null   comment '上班时间设定'")
	public Date getGotoWorkTime() {
		return gotoWorkTime;
	}

	public void setGotoWorkTime(Date gotoWorkTime) {
		this.gotoWorkTime = gotoWorkTime;
	}

	@Column(name = "AFTER_WORK_TIME", nullable = true, columnDefinition = "datetime   null   comment '下班时间设定'")
	public Date getAfterWorkTime() {
		return afterWorkTime;
	}

	public void setAfterWorkTime(Date afterWorkTime) {
		this.afterWorkTime = afterWorkTime;
	}
	
	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, columnDefinition = "varchar(36) default null  comment '创建人'")
	public String getOpUserId() {
		return opUserId;
	}

	/**
	 * @param opUserId the opUserId to set
	 */
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "syuser_customer", schema = "", joinColumns = { @JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) })
	public Set<Syuser> getSyusers() {
		return this.syusers;
	}

	public void setSyusers(Set<Syuser> syusers) {
		this.syusers = syusers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Transient
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Transient
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInfo other = (CustomerInfo) obj;
		if (customerId != other.customerId)
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		return true;
	}
	
	
}