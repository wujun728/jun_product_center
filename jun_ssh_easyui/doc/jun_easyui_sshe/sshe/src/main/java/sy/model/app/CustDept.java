package sy.model.app;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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


@SuppressWarnings("serial")
@Entity
@Table(name = "cust_dept", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_cust_dept", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_cust_dept", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// // 增长值
public class CustDept implements java.io.Serializable {
	private int pid;// 虚拟属性，用于获得当前资源的父资源ID

	private int deptId;
	// private int customerId;
	private String deptName;
	private Date createTime;
	private Date fireTime;
	private int employeNum;
	private String deptAddr;
	private String deptCode;
	private String ICONCLS;
	private int seq;
	// private int pDeptId;
	private String deptMaster;
	private String opUserId;
	private CustomerInfo customerInfo;
	
	private CustDept custDept;
	private Set<CustDept> custDepts;
	
	@Id
	@Column(name = "DEPT_ID", unique = true, length = 10, columnDefinition = "int(10) not null   comment '部门编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_cust_dept")
	// @GeneratedValue(strategy = GenerationType.TABLE )
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	// public int getCustomerId() {
	// return customerId;
	// }

	// public void setCustomerId(int customerId) {
	// this.customerId = customerId;
	// }
	/**
	 * @return the customerInfo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = true, columnDefinition = "int(10) not null comment '客户编号'")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	/**
	 * @param customerInfo
	 *            the customerInfo to set
	 */
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	@Column(name = "DEPT_NAME", nullable = true, length = 30, columnDefinition = "varchar(30) not null comment '部门名称'")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		if (this.createTime != null)
			return this.createTime;
		return new Date();
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIRE_TIME")
	public Date getFireTime() {
		if (this.fireTime != null)
			return this.fireTime;
		return new Date();
	}

	public void setFireTime(Date fireTime) {
		this.fireTime = fireTime;
	}

	@Column(name = "EMPLOYE_NUM", nullable = true, length = 10, columnDefinition = "int(10) default 0 comment '现有员工数'")
	public int getEmployeNum() {
		return employeNum;
	}

	public void setEmployeNum(int employeNum) {
		this.employeNum = employeNum;
	}

	/**
	 * @return the deptAddr
	 */
	@Column(name = "DEPT_ADDR", nullable = true, length = 200, columnDefinition = "varchar(200)  null comment '部门地址'")
	public String getDeptAddr() {
		return deptAddr;
	}

	/**
	 * @param deptAddr
	 *            the deptAddr to set
	 */
	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}

	/**
	 * @return the deptCode
	 */
	@Column(name = "DEPT_CODE", nullable = true, length = 200, columnDefinition = "varchar(200)  null comment '部门编码'")
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode
	 *            the deptCode to set
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return the iCONCLS
	 */
	@Column(name = "ICONCLS", nullable = true, length = 200, columnDefinition = "varchar(200)  null comment 'ICONCLS'")
	public String getICONCLS() {
		return ICONCLS;
	}

	/**
	 * @param iCONCLS
	 *            the iCONCLS to set
	 */
	public void setICONCLS(String iCONCLS) {
		ICONCLS = iCONCLS;
	}

	/**
	 * @return the seq
	 */
	@Column(name = "SEQ", nullable = true, length = 11, columnDefinition = "int(11)  null comment '排序号'")
	public int getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 *            the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Transient
	public int getPid() {
		if (custDept != null && custDept.getDeptId() >= 0) {
			return custDept.getDeptId();
		}
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	// /**
	// * @return the pDeptId
	// */
	// @Column(name = "P_DEPT_ID",nullable=true,length = 11, columnDefinition =
	// "int(11)  null comment '上级部门编号'")
	// public int getpDeptId() {
	// return pDeptId;
	// }
	//
	// /**
	// * @param pDeptId
	// * the pDeptId to set
	// */
	// public void setpDeptId(int pDeptId) {
	// this.pDeptId = pDeptId;
	// }

	/**
	 * @return the deptMaster
	 */
	@Column(name = "DEPT_MASTER", nullable = true, length = 50, columnDefinition = "varchar(50) null comment '部门主管姓名'")
	public String getDeptMaster() {
		return deptMaster;
	}

	/**
	 * @param deptMaster
	 *            the deptMaster to set
	 */
	public void setDeptMaster(String deptMaster) {
		this.deptMaster = deptMaster;
	}

	/**
	 * @return the custDept
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_DEPT_ID", nullable = true , columnDefinition = "int(10) default null  comment '上级编号'")
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

	/**
	 * @return the custDepts
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "custDept", cascade = CascadeType.ALL)
	public Set<CustDept> getCustDepts() {
		return custDepts;
	}

	/**
	 * @param custDepts
	 *            the custDepts to set
	 */
	public void setCustDepts(Set<CustDept> custDepts) {
		this.custDepts = custDepts;
	}
	
	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, columnDefinition = "varchar(36) default null   comment '创建人'")
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