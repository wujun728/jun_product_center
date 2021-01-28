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
@Table(name = "drug_info", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_drug_info", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_drug_info", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class DrugInfo implements Serializable {

	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;
	
	private int drugCode;
	private CustomerInfo customerInfo;
	private String drugName;
	private String drugDesc;
	// 简写
	private String shortName;

	private String opUserId;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;

	@Id
	@Column(name = "DRUG_CODE", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '药品编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_drug_info")
	public int getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(int drugCode) {
		this.drugCode = drugCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = true, columnDefinition = "int(10) null comment '客户编号'")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	@Column(name = "DRUG_NAME", nullable = true, columnDefinition = "varchar(500) not null  comment '药品名称'")
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	@Column(name = "DRUG_DESC", nullable = true, columnDefinition = "varchar(200)  null  comment '药品描述'")
	public String getDrugDesc() {
		return drugDesc;
	}

	public void setDrugDesc(String drugDesc) {
		this.drugDesc = drugDesc;
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
	

	@Column(name = "SHORT_NAME", nullable = true, length = 30, columnDefinition = "varchar(30) comment '简称'")
	public String getShortName() {
		return shortName;
	}
	
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}