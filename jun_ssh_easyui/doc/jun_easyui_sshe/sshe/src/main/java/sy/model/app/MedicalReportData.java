package sy.model.app;

import java.io.Serializable;
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

/**
 * 员工体检数据
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "medical_report_data", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_medical_report_data", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_medical_report_data", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class MedicalReportData implements Serializable {
	// 编号
	private int dataId;
	// 流水号
	private String opOrder;

	private int pid;// 虚拟属性，用于获得当前资源的父资源ID
	private MedicalReportData medicalReportData;
	private Set<MedicalReportData> medicalReportDatas;

	private CustUser custUser;

	private Date medicalTime;

	private int medicalType;

//	private int templateId;
	private MedicalReportDef medicalReportDef;
	private int attrId;

	private String attrName;

	private String attrValue;

	private String attrDesc;

	private int attrKeshi;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private String attrCankao;
	// 检查类型
	private int attrCheck;
	// 属性单位
	private String attrUnit;
	// 属性数值类型
	private int isIndicators;
	//是否异常
	private int isAbnormal;

	@Id
	@Column(name = "DATA_ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null  comment '体检序号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_medical_report_data")
	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	@Column(name = "OP_ORDER", nullable = false, columnDefinition = "varchar(20) not null comment '体检流水号'")
	public String getOpOrder() {
		return opOrder;
	}

	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}

	@Column(name = "MEDICAL_TIME", nullable = true, columnDefinition = "datetime   null  comment '体检日期'")
	public Date getMedicalTime() {
		return medicalTime;
	}

	public void setMedicalTime(Date medicalTime) {
		this.medicalTime = medicalTime;
	}

	@Column(name = "MEDICAL_TYPE", nullable = true, columnDefinition = "int(8)   null default 0  comment '体检性质'")
	public int getMedicalType() {
		return medicalType;
	}

	public void setMedicalType(int medicalType) {
		this.medicalType = medicalType;
	}

//	@Column(name = "TEMPLATE_ID", nullable = false, columnDefinition = "int(8)  not null comment '体检报告编号'")
//	public int getTemplateId() {
//		return templateId;
//	}
//	
//	public void setTemplateId(int templateId) {
//		this.templateId = templateId;
//	}
	
//	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID", nullable = false, columnDefinition = "int(8)  not null comment '体检报告编号'" )
	public MedicalReportDef getMedicalReportDef() {
		return medicalReportDef;
	}
	
	public void setMedicalReportDef(MedicalReportDef medicalReportDef) {
		this.medicalReportDef = medicalReportDef;
	}

	
	
	@Column(name = "ATTR_ID", nullable = false, columnDefinition = "int(8)  not null comment '属性编号'")
	public int getAttrId() {
		return attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}

	@Column(name = "ATTR_NAME", nullable = false, columnDefinition = "varchar(100)  not null comment '属性名称'")
	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	@Column(name = "ATTR_VALUE", nullable = true, columnDefinition = "varchar(100) default null comment '属性值'")
	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	@Column(name = "ATTR_DESC", nullable = true, length = 100, columnDefinition = "varchar(100)    null comment '属性提示'")
	public String getAttrDesc() {
		return attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

	@Column(name = "ATTR_KESHI", nullable = true, length = 100, columnDefinition = "int(8)   null comment '体检科室'")
	public int getAttrKeshi() {
		return attrKeshi;
	}

	public void setAttrKeshi(int attrKeshi) {
		this.attrKeshi = attrKeshi;
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
	@JoinColumn(name = "USER_ID")
	public CustUser getCustUser() {
		return custUser;
	}

	public void setCustUser(CustUser custUser) {
		this.custUser = custUser;
	}

	@Column(name = "ATTR_CANKAO", nullable = true, length = 100, columnDefinition = "varchar(100) default null comment '属性参考值'")
	public String getAttrCankao() {
		return attrCankao;
	}

	public void setAttrCankao(String attrCankao) {
		this.attrCankao = attrCankao;
	}

	@Column(name = "ATTR_CHECK", nullable = true, length = 5, columnDefinition = "int(5)  comment '属性检查类型'")
	public int getAttrCheck() {
		return attrCheck;
	}

	public void setAttrCheck(int attrCheck) {
		this.attrCheck = attrCheck;
	}

	@Column(name = "ATTR_UNIT", nullable = true, length = 100, columnDefinition = "varchar(100) comment '属性单位'")
	public String getAttrUnit() {
		return attrUnit;
	}

	public void setAttrUnit(String attrUnit) {
		this.attrUnit = attrUnit;
	}

	@Column(name = "IS_INDICATORS", nullable = true, columnDefinition = "int(8) default 0 comment '1 数值属性;描述类型：描述3 类型： 取值范围是类型4 列表： 例如 听力表数据'")
	public int getIsIndicators() {
		return isIndicators;
	}

	public void setIsIndicators(int isIndicators) {
		this.isIndicators = isIndicators;
	}

	@Transient
	public int getPid() {
		if (medicalReportData != null && medicalReportData.getAttrId() >= 0) {
			return medicalReportData.getAttrId();
		}
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_ATTR_ID", referencedColumnName="ATTR_ID", nullable = true,columnDefinition="int(8) DEFAULT NULL COMMENT '属性编号'")
	public MedicalReportData getMedicalReportData() {
		return medicalReportData;
	}
	
	/**
	 * @param medicalReportData
	 *            the medicalReportData to set
	 */
	public void setMedicalReportData(MedicalReportData medicalReportData) {
		this.medicalReportData = medicalReportData;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicalReportData", cascade = CascadeType.ALL)
	public Set<MedicalReportData> getMedicalReportDatas() {
		return medicalReportDatas;
	}
	
	/**
	 * @param medicalReportDatas
	 *            the medicalReportDatas to set
	 */
	public void setMedicalReportDatas(Set<MedicalReportData> medicalReportDatas) {
		this.medicalReportDatas = medicalReportDatas;
	}

	@Column(name = "IS_ABNORMAL", nullable = true, columnDefinition = "int(8) default 0 comment '是否异常  正常   异常 参见系统字典中的定义  '")
	public int getIsAbnormal() {
		return isAbnormal;
	}

	/**
	 * @param isAbnormal the isAbnormal to set
	 */
	public void setIsAbnormal(int isAbnormal) {
		this.isAbnormal = isAbnormal;
	}
}