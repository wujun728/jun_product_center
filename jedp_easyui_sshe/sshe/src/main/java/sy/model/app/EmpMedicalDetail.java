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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 员工体检详细信息
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "emp_medical_detail", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
// @TableGenerator(name = "SEQ_EMP_MEDICAL_DETAIL", // 别名
// table = "GENERATOR_TABLE", // 生成的表名
// pkColumnName = "sequence_name", // key列名
// valueColumnName = "next_val", // value列名
// pkColumnValue = "SEQ_EMP_MEDICAL_DETAIL", // 具体key内容
// initialValue = 1, // 初始值
// allocationSize = 1)
// // 增长值
public class EmpMedicalDetail implements java.io.Serializable {
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;

	private int id;

	private int pid;// 虚拟属性，用于获得当前父ID
	private EmpMedicalDetail empMedicalDetail;
	private Set<EmpMedicalDetail> EmpMedicalDetails;

	// 流水号
	private String opOrder;

	private MedicalReportDef medicalReportDef;
	// private int userId;
	private CustUser custUser;

	private Date medicalTime;

	private int medicalType;
	
	private int medicalBaseType;

	private String medicalProject;

	private Date expectReportTime;

	private Date realReportTime;

	// 1 已经签收 2 未签收
	private String reportFax;

	private String medicalResult;

	private byte isInduction;

	private String medocaConclusion;

	private String medocaConclusionOp;

	private Date offTime;

	private byte whetherOff;

	private byte hasCheck;

	private Date medicalCheckTime;

	private Date acceptReportTime;

	private String reviewResult;

	private String reviewConclusion;

	private String reviewConclusionOp;

	private String appointment;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private String isInductionExt;
	private Byte status;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null  AUTO_INCREMENT comment '序号'")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "OP_ORDER", nullable = false, columnDefinition = "varchar(20) not null  comment '操作流水号'")
	public String getOpOrder() {
		return opOrder;
	}

	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}

	@Column(name = "MEDICAL_TIME", nullable = true, columnDefinition = "date   null  comment '体检日期'")
	public Date getMedicalTime() {
		return medicalTime;
	}

	public void setMedicalTime(Date medicalTime) {
		this.medicalTime = medicalTime;
	}

	@Column(name = "MEDICAL_TYPE", nullable = true, columnDefinition = "int(10)  not  null default 0  comment '体检性质'")
	public int getMedicalType() {
		return medicalType;
	}

	public void setMedicalType(int medicalType) {
		this.medicalType = medicalType;
	}

	@Column(name = "MEDICAL_PROJECT", nullable = false, length = 200, columnDefinition = "varchar(200) not null comment '体检项目'")
	public String getMedicalProject() {
		return medicalProject;
	}

	public void setMedicalProject(String medicalProject) {
		this.medicalProject = medicalProject;
	}

	@Column(name = "EXPECT_REPORT_TIME", nullable = true, columnDefinition = "datetime   null  comment '预计出报告时间'")
	public Date getExpectReportTime() {
		return expectReportTime;
	}

	public void setExpectReportTime(Date expectReportTime) {
		this.expectReportTime = expectReportTime;
	}

	@Column(name = "REAL_REPORT_TIME", nullable = true, columnDefinition = "datetime   null  comment '实际出报告时间'")
	public Date getRealReportTime() {
		return realReportTime;
	}

	public void setRealReportTime(Date realReportTime) {
		this.realReportTime = realReportTime;
	}

	@Column(name = "REPORT_FAX", nullable = true, length = 50, columnDefinition = "varchar(50)  null  comment '报告传真签收情况'")
	public String getReportFax() {
		return reportFax;
	}

	public void setReportFax(String reportFax) {
		this.reportFax = reportFax;
	}

	@Column(name = "MEDICAL_RESULT", nullable = true, length = 50, columnDefinition = "varchar(50)  null  comment '体检结果'")
	public String getMedicalResult() {
		return medicalResult;
	}

	public void setMedicalResult(String medicalResult) {
		this.medicalResult = medicalResult;
	}

	@Column(name = "IS_INDUCTION", nullable = true, columnDefinition = "tinyint null comment '是否可以入职 1 可以入职 2 不可以入职'")
	public byte getIsInduction() {
		return isInduction;
	}

	public void setIsInduction(byte isInduction) {
		this.isInduction = isInduction;
	}

	@Column(name = "IS_INDUCTION_EXT", nullable = true, columnDefinition = "varchar(100) null comment '是否可以入职的建议文字'")
	public String getIsInductionExt() {
		return isInductionExt;
	}

	public void setIsInductionExt(String isInductionExt) {
		this.isInductionExt = isInductionExt;
	}

	@Column(name = "MEDOCA_CONCLUSION", nullable = true, length = 50, columnDefinition = "varchar(50)  null  comment '检查结论'")
	public String getMedocaConclusion() {
		return medocaConclusion;
	}

	public void setMedocaConclusion(String medocaConclusion) {
		this.medocaConclusion = medocaConclusion;
	}

	@Column(name = "MEDOCA_CONCLUSION_OP", nullable = true, length = 50, columnDefinition = "varchar(50)  null  comment '结论确定者'")
	public String getMedocaConclusionOp() {
		return medocaConclusionOp;
	}

	public void setMedocaConclusionOp(String medocaConclusionOp) {
		this.medocaConclusionOp = medocaConclusionOp;
	}

	@Column(name = "OFF_TIME", nullable = true, columnDefinition = "datetime   null  comment '离职时间'")
	public Date getOffTime() {
		return offTime;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	@Column(name = "WHETHER_OFF", nullable = true, columnDefinition = "tinyint comment '是否至医务室办离职手续 1 可以办理手续 2 不能办理离职手续'")
	public byte getWhetherOff() {
		return whetherOff;
	}

	public void setWhetherOff(byte whetherOff) {
		this.whetherOff = whetherOff;
	}

	@Column(name = "HAS_CHECK", nullable = true, columnDefinition = "tinyint    comment '是否已参检 1 参检， 2 没有参检'")
	public byte getHasCheck() {
		return hasCheck;
	}

	public void setHasCheck(byte hasCheck) {
		this.hasCheck = hasCheck;
	}

	@Column(name = "MEDICAL_CHECK_TIME", nullable = true, columnDefinition = "datetime   null  comment '复查体检开始日期'")
	public Date getMedicalCheckTime() {
		return medicalCheckTime;
	}

	public void setMedicalCheckTime(Date medicalCheckTime) {
		this.medicalCheckTime = medicalCheckTime;
	}

	@Column(name = "ACCEPT_REPORT_TIME", nullable = true, columnDefinition = "datetime   null  comment '复查接收报告时间'")
	public Date getAcceptReportTime() {
		return acceptReportTime;
	}

	public void setAcceptReportTime(Date acceptReportTime) {
		this.acceptReportTime = acceptReportTime;
	}

	@Column(name = "REVIEW_RESULT", nullable = true, length = 50, columnDefinition = "varchar(50)    null  comment '复查结果'")
	public String getReviewResult() {
		return reviewResult;
	}

	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}

	@Column(name = "REVIEW_CONCLUSION", nullable = true, length = 50, columnDefinition = "varchar(50)    null  comment '复查结论'")
	public String getReviewConclusion() {
		return reviewConclusion;
	}

	public void setReviewConclusion(String reviewConclusion) {
		this.reviewConclusion = reviewConclusion;
	}

	@Column(name = "REVIEW_CONCLUSION_OP", nullable = true, length = 50, columnDefinition = "varchar(50)    null  comment '复查结论确定者'")
	public String getReviewConclusionOp() {
		return reviewConclusionOp;
	}

	public void setReviewConclusionOp(String reviewConclusionOp) {
		this.reviewConclusionOp = reviewConclusionOp;
	}

	@Column(name = "APPOINTMENT", nullable = true, length = 50, columnDefinition = "varchar(50)   null  comment '体检预约人'")
	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false, columnDefinition = "int(10) not null   comment '员工编号'")
	public CustUser getCustUser() {
		return custUser;
	}

	public void setCustUser(CustUser custUser) {
		this.custUser = custUser;
	}

	@Column(name = "STATUS", nullable = false, columnDefinition = "tinyint not null default 0  comment '状态 0 正常状态 1 删除状态'")
	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID", nullable = false, columnDefinition = "int(10) not null comment '体检报告编号'")
	public MedicalReportDef getMedicalReportDef() {
		return medicalReportDef;
	}

	/**
	 * @param medicalReportDef
	 *            the medicalReportDef to set
	 */
	public void setMedicalReportDef(MedicalReportDef medicalReportDef) {
		this.medicalReportDef = medicalReportDef;
	}

	@Transient
	public int getPid() {
		if (empMedicalDetail != null && empMedicalDetail.getId() >= 0) {
			return empMedicalDetail.getId();
		}
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_ID", referencedColumnName = "ID", nullable = true, columnDefinition = "int(8) DEFAULT NULL COMMENT '上级属性编号'")
	public EmpMedicalDetail getEmpMedicalDetail() {
		return empMedicalDetail;
	}
	
	/**
	 * @param empMedicalDetail
	 *            the empMedicalDetail to set
	 */
	public void setEmpMedicalDetail(EmpMedicalDetail empMedicalDetail) {
		this.empMedicalDetail = empMedicalDetail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empMedicalDetail", cascade = CascadeType.ALL)
	public Set<EmpMedicalDetail> getEmpMedicalDetails() {
		return EmpMedicalDetails;
	}

	/**
	 * @param empMedicalDetails
	 *            the empMedicalDetails to set
	 */
	public void setEmpMedicalDetails(Set<EmpMedicalDetail> empMedicalDetails) {
		EmpMedicalDetails = empMedicalDetails;
	}

	@Column(name = "MEDICAL_BASE_TYPE", nullable = true, columnDefinition = "int(10)  not  null default 0  comment '基本类型 0 一般体检 1 复查体检； 复查支持多次检查'")
	public int getMedicalBaseType() {
		return medicalBaseType;
	}
	
	/**
	 * @param medicalBaseType the medicalBaseType to set
	 */
	public void setMedicalBaseType(int medicalBaseType) {
		this.medicalBaseType = medicalBaseType;
	}
}