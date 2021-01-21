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

import sy.model.base.Syuser;

@SuppressWarnings("serial")
@Entity
@Table(name = "drug_store_check", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
//@TableGenerator(name = "SEQ_DRUG_STORE_CHECK", // 别名
//table = "GENERATOR_TABLE", // 生成的表名
//pkColumnName = "sequence_name", // key列名
//valueColumnName = "next_val", // value列名
//pkColumnValue = "SEQ_DRUG_STORE_CHECK", // 具体key内容
//initialValue = 1, // 初始值
//allocationSize = 1)
//// 增长值
public class DrugStoreCheck implements Serializable {
 
	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;

	/**
	 * 10 入库 20 消耗 30 盘点 40 出库
	 */
	@Transient
	public static final byte OP_TYPE_IN = (byte) 10;
	/**
	 * 10 入库 20 消耗 30 盘点 40 出库
	 */
	@Transient
	public static final byte OP_TYPE_USE = (byte) 20;
	/**
	 * 10 入库 20 消耗 30 盘点 40 出库
	 */
	@Transient
	public static final byte OP_TYPE_PANDIAN = (byte) 30;
	/**
	 * 10 入库 20 消耗 30 盘点 40 出库
	 */
	@Transient
	public static final byte OP_TYPE_OUT = (byte) 40;
	
	/**
	 * 纠正
	 */
	@Transient
	public static final byte OP_TYPE_JIUZHG = (byte) 50;
	
	// 序号
	private int storeId;
	// 流水号
	private String opOrder;
	// 规格编号
	private DrugSpecInfo drugSpecInfo;
	// 客户编号
	private CustomerInfo customerInfo;
	// 规格
	private String specification;
	// 单位
	private String unit;
	// 库存数量
	private int num;
	// 盘点数量
	private int num2;
	// 批号
	private String drugLotNo;
	// 单价
	private BigDecimal price;

	private Syuser syuser;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;
	private byte opType;

	@Id
	@Column(name = "STORE_ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null  AUTO_INCREMENT    comment '序号'")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getStoreId() {
		return storeId;
	}
	
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPEC_ID", columnDefinition = "int(10) not null   comment '规格编号'")
	public DrugSpecInfo getDrugSpecInfo() {
		return drugSpecInfo;
	}

	/**
	 * @param drugSpecInfo
	 *            the drugSpecInfo to set
	 */
	public void setDrugSpecInfo(DrugSpecInfo drugSpecInfo) {
		this.drugSpecInfo = drugSpecInfo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = true, columnDefinition = "int(10) null comment '客户编号'")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	@Column(name = "DRUG_LOT_NO", nullable = false, columnDefinition = "varchar(20)  not null  comment '批次'")
	public String getDrugLotNo() {
		return drugLotNo;
	}

	/**
	 * @param drugLotNo
	 *            the drugLotNo to set
	 */
	public void setDrugLotNo(String drugLotNo) {
		this.drugLotNo = drugLotNo;
	}

	@Column(name = "OP_ORDER", nullable = false, columnDefinition = "varchar(20) not null  comment '操作流水号'")
	public String getOpOrder() {
		return opOrder;
	}

	public void setOpOrder(String opOrder) {
		this.opOrder = opOrder;
	}

	@Column(name = "UNIT", nullable = true, columnDefinition = "varchar(10) null comment '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "OP_TYPE", nullable = true, columnDefinition = "tinyint default  0   comment '操作类 10 入库20 消耗30 盘点40 出库型'")
	public byte getOpType() {
		return opType;
	}

	public void setOpType(byte opType) {
		this.opType = opType;
	}
	@Column(name = "NUM", nullable = true, columnDefinition = "int default 0 comment '数量'")
	public int getNum() {
		return num;
	}

	public void setNum(int number) {
		this.num = number;
	}

	/**
	 * @return the num2
	 */
	@Column(name = "NUM2", nullable = true, columnDefinition = "int default 0 comment '盘点数量'")
	public int getNum2() {
		return num2;
	}

	/**
	 * @param num2
	 *            the num2 to set
	 */
	public void setNum2(int num2) {
		this.num2 = num2;
	}

	@Column(name = "SPECIFICATION", nullable = true, columnDefinition = "varchar(40) null comment '规格'")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Column(name = "PRICE", nullable = true, columnDefinition = "numeric(8,2) default  0   comment '单价'")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OP_USER_ID", columnDefinition = "varchar(36)   NULL default null comment '用户编号'")
	public Syuser getSyuser() {
		return syuser;
	}

	/**
	 * @param syuser
	 *            the syuser to set
	 */
	public void setSyuser(Syuser syuser) {
		this.syuser = syuser;
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

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint null comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}