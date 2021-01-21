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

/**
 * 药品批次规格
 * 
 * @author Wujun
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "drug_times", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_drug_times", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_drug_times", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class DrugTimes implements Serializable {

	@Transient
	public static final byte STATUS_NEW = (byte) 0;
	@Transient
	public static final byte STATUS_DELETED = (byte) 99;

	private int id;
	private DrugSpecInfo drugSpecInfo;
	private String drugLotNo;
	private String produceComp;
	private Date expireTime;
	private Date produceTime;
	private String contact;
	private String unit;
	private String specification;
	private BigDecimal price;
	private int total;
	private int rest;

	private Syuser syuser;
	private Date createTime;
	private Date updateTime;
	private String ext1;
	private byte status;

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10, columnDefinition = "int(10) not null   comment '编号'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_drug_times")
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	@Column(name = "UNIT", nullable = true, columnDefinition = "varchar(10) null comment '单位'")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "PRICE", nullable = true, columnDefinition = "numeric(8,2) default  0   comment '单价'")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "EXPIRE_TIME", nullable = true, columnDefinition = "datetime null  comment '过期时间'")
	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@Column(name = "PRODUCE_COMP", nullable = true, columnDefinition = "varchar(500) default null  comment '生产厂商'")
	public String getProduceComp() {
		return produceComp;
	}

	public void setProduceComp(String produceComp) {
		this.produceComp = produceComp;
	}

	@Column(name = "PRODUCE_TIME", nullable = true, columnDefinition = "datetime null  comment '生产时间'")
	public Date getProduceTime() {
		return produceTime;
	}

	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}

	@Column(name = "SPECIFICATION", nullable = true, columnDefinition = "varchar(50) not null  comment '规格'")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Column(name = "CONTACT", nullable = true, columnDefinition = "varchar(100)   null  comment '生产厂商联系地址'")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint default 0 comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Column(name = "TOTAL", nullable = true, columnDefinition = "int default  0   comment '总量'")
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Column(name = "REST", nullable = true, columnDefinition = "int default  0   comment '剩余量'")
	public int getRest() {
		return rest;
	}

	/**
	 * @param rest
	 *            the rest to set
	 */
	public void setRest(int rest) {
		this.rest = rest;
	}

}