package sy.model.app;

import java.io.Serializable;
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
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "emp_diagnose_record_detail", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_emp_diagnose_record_detail", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_emp_diagnose_record_detail", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class EmpDiagnoseRecordDetail implements Serializable {

	/**
	 * 体格检查类型 =0
	 */
	@Transient
	public static final byte TYPE_TG = 0;
	/**
	 * 辅助检查类型 =1
	 */
	@Transient
	public static final byte TYPE_FZ = 1;

	private int pid;// 虚拟属性，用于获得当前资源的父资源ID

	private int id;

	private int medicalId;

	private byte type;

	private PhysicalTypeDef physicalTypeDef;

	private EmpDiagnoseRecordDetail empDiagnoseRecordDetail;

	private Set<EmpDiagnoseRecordDetail> empDiagnoseRecordDetails;

	private String description;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '明细编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_emp_diagnose_record_detail")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "MEDICAL_ID", nullable = false, length = 10, columnDefinition = "int(10) not null   comment '就诊编号'")
	public int getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(int medicalId) {
		this.medicalId = medicalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PHY_ID", nullable = false, columnDefinition = "int(8)  not null  comment '体格检查类型'")
	public PhysicalTypeDef getPhysicalTypeDef() {
		return physicalTypeDef;
	}

	public void setPhysicalTypeDef(PhysicalTypeDef physicalTypeDef) {
		this.physicalTypeDef = physicalTypeDef;
	}

	@Transient
	public int getPid() {
		if (empDiagnoseRecordDetail != null
				&& empDiagnoseRecordDetail.getId() >= 0) {
			return empDiagnoseRecordDetail.getId();
		}
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID", nullable = true, columnDefinition = "int(8) default null comment '上级编码'")
	public EmpDiagnoseRecordDetail getEmpDiagnoseRecordDetail() {
		return empDiagnoseRecordDetail;
	}

	/**
	 * @param empDiagnoseRecordDetail
	 *            the empDiagnoseRecordDetail to set
	 */
	public void setEmpDiagnoseRecordDetail(
			EmpDiagnoseRecordDetail empDiagnoseRecordDetail) {
		this.empDiagnoseRecordDetail = empDiagnoseRecordDetail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empDiagnoseRecordDetail", cascade = CascadeType.ALL)
	public Set<EmpDiagnoseRecordDetail> getEmpDiagnoseRecordDetails() {
		return empDiagnoseRecordDetails;
	}

	/**
	 * @param empDiagnoseRecordDetails
	 *            the empDiagnoseRecordDetails to set
	 */
	public void setEmpDiagnoseRecordDetails(
			Set<EmpDiagnoseRecordDetail> empDiagnoseRecordDetails) {
		this.empDiagnoseRecordDetails = empDiagnoseRecordDetails;
	}

	@Column(name = "DESCRIPTION", nullable = true, columnDefinition = "varchar(200)   null comment '备注1'")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TYPE", nullable = true, columnDefinition = "tinyint null default 0  comment '状态'")
	public byte getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(byte type) {
		this.type = type;
	}

}