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

import sy.model.base.SysDef;

@SuppressWarnings("serial")
@Entity
@Table(name = "medical_report_attr", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_medical_report_attr", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_medical_report_attr", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class MedicalReportAttr implements Serializable {

	/**
	 * 1 有效 99 删除
	 */
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;

	private int attrId;
	private int pid;// 虚拟属性，用于获得当前资源的父资源ID
	private MedicalReportAttr medicalReportAttr;
	private Set<MedicalReportAttr> medicalReportAttrs;

	// private int templateId;
	private MedicalReportDef medicalReportDef;

	private String attrName;

	private String attrCankao;

	private int attrKeshi;

	private int attrCheck;

	private String attrUnit;

	private String unitData;

	private int isIndicators;

	private String attrDesc;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;

	private byte status;
	private int isAbnormal;

	@Id
	@Column(name = "ATTR_ID", unique = true, nullable = false, length = 8, columnDefinition = "int(8) not null   comment '属性编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_medical_report_attr")
	public int getAttrId() {
		return attrId;
	}

	public void setAttrId(int attrId) {
		this.attrId = attrId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "P_ATTR_ID", nullable = true,columnDefinition = "int(8)  null   comment '上级属性编号'")
	public MedicalReportAttr getMedicalReportAttr() {
		return medicalReportAttr;
	}

	public void setMedicalReportAttr(MedicalReportAttr medicalReportAttr) {
		this.medicalReportAttr = medicalReportAttr;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicalReportAttr", cascade = CascadeType.ALL)
	public Set<MedicalReportAttr> getMedicalReportAttrs() {
		return medicalReportAttrs;
	}

	/**
	 * @param medicalReportAttrs
	 *            the medicalReportAttrs to set
	 */
	public void setMedicalReportAttrs(Set<MedicalReportAttr> medicalReportAttrs) {
		this.medicalReportAttrs = medicalReportAttrs;
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

	// public int getTemplateId() {
	// return templateId;
	// }
	//
	// public void setTemplateId(int templateId) {
	// this.templateId = templateId;
	// }
	@Column(name = "ATTR_NAME", nullable = true, length = 100, columnDefinition = "varchar(100) not null comment '属性名称'")
	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	@Column(name = "ATTR_CANKAO", nullable = true, length = 100, columnDefinition = "varchar(100) default null comment '属性参考值'")
	public String getAttrCankao() {
		return attrCankao;
	}
	
	public void setAttrCankao(String attrCankao) {
		this.attrCankao = attrCankao;
	}

	@Column(name = "ATTR_KESHI", nullable = true, length = 8, columnDefinition = "int(8)  comment '所属科室'")
	public int getAttrKeshi() {
		return attrKeshi;
	}

	public void setAttrKeshi(int attrKeshi) {
		this.attrKeshi = attrKeshi;
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

	@Column(name = "UNIT_DATA", nullable = true, length = 100, columnDefinition = "varchar(100) comment '属性数值'")
	public String getUnitData() {
		return unitData;
	}

	public void setUnitData(String unitData) {
		this.unitData = unitData;
	}

	@Column(name = "IS_INDICATORS", nullable = true, columnDefinition = "int(8) default 0 comment '1 数值属性;描述类型：描述3 类型： 取值范围是类型4 列表： 例如 听力表数据'")
	public int getIsIndicators() {
		return isIndicators;
	}

	public void setIsIndicators(int isIndicators) {
		this.isIndicators = isIndicators;
	}

	@Column(name = "ATTR_DESC", nullable = true, length = 300, columnDefinition = "varchar(300) comment '属性描述'")
	public String getAttrDesc() {
		return attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
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

	@Transient
	public int getPid() {
		if (medicalReportAttr != null && medicalReportAttr.getAttrId() >= 0) {
			return medicalReportAttr.getAttrId();
		}
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint null comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
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