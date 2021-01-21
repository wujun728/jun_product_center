package sy.model.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import sy.model.app.CustomerInfo;

@Entity
@Table(name = "syuser", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
// @TableGenerator(name = "SEQ_SYUSER", // 别名
// table = "GENERATOR_TABLE", // 生成的表名
// pkColumnName = "sequence_name", // key列名
// valueColumnName = "next_val", // value列名
// pkColumnValue = "SEQ_SYUSER", // 具体key内容
// initialValue = 1, // 初始值
// allocationSize = 1)
// // 增长值
public class Syuser implements java.io.Serializable {

	public static final byte IS_SYN_CHAT_SUCC = (byte) 1;
	public static final byte IS_SYN_CHAT_FAIL = (byte) 0;

	private String ip;// 此属性不存数据库，虚拟属性
	private boolean isAdmin;// 不存数据库中，虚拟属性

	private String id;
	private Date createdatetime;
	private Date updatedatetime;
	private String checkCode;
	private String loginname;
	private String pwd;
	private String name;
	private String sex;
	private Integer age;
	private String photo;
	private byte isSynChat;

	private Set<Syorganization> syorganizations = new HashSet<Syorganization>(0);
	private Set<Syrole> syroles = new HashSet<Syrole>(0);

	public Syuser() {
	}

	public Syuser(String id) {
		this.id = id;
	}

	// 客户编号
	// private CustomerInfo customerInfo;
	// private int customerId;
	private Set<CustomerInfo> sycustomerInfos = new HashSet<CustomerInfo>(0);
	// 员工的物理地址-设置好了，一般用户不可修改
	private String phyAddr;
	// 原密码-为了聊天服务器同步;暂时不知道聊天的密码加密
	private String oriPwd;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36, columnDefinition = "varchar(36) NOT NULL comment '用户编号'")
	// @GeneratedValue(strategy = GenerationType.TABLE, generator =
	// "SEQ_SYUSER")
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
		// return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATETIME", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}

	@Column(name = "LOGINNAME", nullable = false, length = 100)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "PWD", length = 100)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "AGE", precision = 8, scale = 0)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "PHOTO", length = 200)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "syuser_syorganization", schema = "", joinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYORGANIZATION_ID", nullable = false, updatable = false) })
	public Set<Syorganization> getSyorganizations() {
		return this.syorganizations;
	}

	public void setSyorganizations(Set<Syorganization> syorganizations) {
		this.syorganizations = syorganizations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "syuser_syrole", schema = "", joinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "SYROLE_ID", nullable = false, updatable = false) })
	public Set<Syrole> getSyroles() {
		return this.syroles;
	}

	public void setSyroles(Set<Syrole> syroles) {
		this.syroles = syroles;
	}

	@Transient
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	// /**
	// * @return the customerInfo
	// */
	// @OneToOne(optional = true)
	// @PrimaryKeyJoinColumn
	// public CustomerInfo getCustomerInfo() {
	// return customerInfo;
	// }
	//
	// /**
	// * @param customerInfo the customerInfo to set
	// */
	// public void setCustomerInfo(CustomerInfo customerInfo) {
	// this.customerInfo = customerInfo;
	// }
	// @Column(name = "CUSTOMER_ID", nullable = true, length = 10,
	// columnDefinition = "int(10) not null   comment '客户编号'")
	// public int getCustomerId() {
	// return customerId;
	// }
	//
	// public void setCustomerId(int customerId) {
	// this.customerId = customerId;
	// }

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "syuser_customer", schema = "", joinColumns = { @JoinColumn(name = "SYUSER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false) })
	public Set<CustomerInfo> getSycustomerInfos() {
		return sycustomerInfos;
	}

	/**
	 * @param sycustomerInfo
	 *            the sycustomerInfo to set
	 */
	public void setSycustomerInfos(Set<CustomerInfo> sycustomerInfos) {
		this.sycustomerInfos = sycustomerInfos;
	}

	/**
	 * @return the isAdmin
	 */
	@Transient
	public boolean isAdmin() {
		if (id != null && id.equals("0")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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

	/**
	 * @return the checkCode
	 */
	@Transient
	public String getCheckCode() {
		return checkCode;
	}

	/**
	 * @param checkCode
	 *            the checkCode to set
	 */
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Column(name = "IS_SYN_CHAT", nullable = true, columnDefinition = "tinyint default 0 comment '同步到聊天系统；1同步，0 没有'")
	public byte getIsSynChat() {
		return isSynChat;
	}

	/**
	 * @param isSynChat
	 *            the isSynChat to set
	 */
	public void setIsSynChat(byte isSynChat) {
		this.isSynChat = isSynChat;
	}

	@Column(name = "ORI_PWD", nullable = true, length = 100)
	public String getOriPwd() {
		return this.oriPwd;
	}
	
	public void setOriPwd(String pwd) {
		this.oriPwd = pwd;
	}

}
