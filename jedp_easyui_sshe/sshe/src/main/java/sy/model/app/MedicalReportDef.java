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
@Table(name = "medical_report_def", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_medical_report_def", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_medical_report_def", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class MedicalReportDef implements Serializable {
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 1;

	private int templateId;

	//private int customerId;
	
	private CustomerInfo customerInfo;

	private String templateName;

	private String templateContent;

	private byte reportType;

	private String belongToHispital;
	
	private int medicalReportType;
	
	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;

	private Byte status;
	
	@Id
	@Column(name = "TEMPLATE_ID", unique = true, nullable = false, length = 8, columnDefinition = "int(8) not null   comment '体检报告编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_medical_report_def")
	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = true, columnDefinition = "int(10) not null comment '客户编号'")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	@Column(name = "TEMPLATE_NAME", nullable = false, length = 50, columnDefinition = "varchar(50) not null comment '报告名称'")
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	@Column(name = "TEMPLATE_CONTENT", nullable = false, length = 500, columnDefinition = "varchar(500)   null comment '报告描述'")
	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	@Column(name = "REPORT_TYPE", nullable = false, columnDefinition = "tinyint not null default 1  comment '报告类型 : 1 体检报告； 2 体检结果'")
	public byte getReportType() {
		return reportType;
	}

	public void setReportType(byte reportType) {
		this.reportType = reportType;
	}
	@Column(name = "BELONG_TO_HISPITAL", nullable = true, length=200,columnDefinition = "varchar(200) comment '所属医院'")
	public String getBelongToHispital() {
		return belongToHispital;
	}

	public void setBelongToHispital(String belongToHispital) {
		this.belongToHispital = belongToHispital;
	}
	@Column(name = "MEDICAL_REPORT_TYPE", nullable = false, columnDefinition = "int(8) not null default 0  comment '体检报告分类 : 入职体检 定期年度体检  入职体检(职业病) 离职体检(职业病) 定期年度体检(职业病)'")
	public int getMedicalReportType() {
		return medicalReportType;
	}
	
	public void setMedicalReportType(int medicalReportType) {
		this.medicalReportType = medicalReportType;
	}
	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, insertable=false, updatable=false, columnDefinition = "varchar(36) default  0   comment '创建人'")
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
	@Column(name = "STATUS", nullable = false, columnDefinition = "tinyint not null default 0  comment '状态 0 正常状态 1 删除状态'")
	public Byte getStatus() {
		return status;
	}
	
	public void setStatus(Byte status) {
		this.status = status;
	}
}