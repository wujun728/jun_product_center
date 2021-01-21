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

/**
 * 药品规格信息表
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "drug_spec_info", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_drug_spec_info", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_drug_spec_info", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class DrugSpecInfo implements Serializable {

	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	
	private int specId;
	private DrugInfo drugInfo;
	private String specification;
	
	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;
	private String unit;

	@Id
	@Column(name = "SPEC_ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '规格编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_drug_spec_info")
	public int getSpecId() {
		return specId;
	}
	
	/**
	 * @param specId the specId to set
	 */
	public void setSpecId(int specId) {
		this.specId = specId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DRUG_CODE", nullable = false,   columnDefinition = "int(10)  not null   comment '药品编号'")
	public DrugInfo getDrugInfo() {
		return drugInfo;
	}

	/**
	 * @param drugInfo the drugInfo to set
	 */
	public void setDrugInfo(DrugInfo drugInfo) {
		this.drugInfo = drugInfo;
	}
	
	@Column(name = "SPECIFICATION", nullable = true, columnDefinition = "varchar(50) not null  comment '规格'")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	
	@Column(name = "UNIT", nullable = true, columnDefinition = "varchar(10) null comment '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * @return the opUserId
	 */
	@Column(name = "OP_USER_ID", nullable = true, insertable = false, updatable = false, columnDefinition = "varchar(36) default  0   comment '创建人'")
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

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint default 0 comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	


}