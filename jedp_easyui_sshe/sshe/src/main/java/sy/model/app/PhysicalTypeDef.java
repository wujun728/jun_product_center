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
@Table(name = "physical_type_def", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
@TableGenerator(name = "seq_physical_type_def", // 别名
table = "generator_table", // 生成的表名
pkColumnName = "sequence_name", // key列名
valueColumnName = "next_val", // value列名
pkColumnValue = "seq_physical_type_def", // 具体key内容
initialValue = 1, // 初始值
allocationSize = 1)
// 增长值
public class PhysicalTypeDef implements java.io.Serializable {
	@Transient
	public static final byte STATUS_NEW = (byte)0;
	@Transient
	public static final byte  STATUS_DELETED = (byte)99;
	
	private int pid;// 虚拟属性，用于获得当前资源的父资源ID
	
	private int phyId;

	private String phyName;

	private byte phyType;

	private byte level;

	private byte isLeaf;

	private Date createTime;

	private Date updateTime;

	private String uid;

	private String ext1;

	private String ext2;

	private String ext3;

	private String ext4;

	private PhysicalTypeDef physicalTypeDef;
	private Set<PhysicalTypeDef> physicalTypeDefs;
	
	private String sName;
	private byte status;
	
	@Id
	@Column(name = "PHY_ID", unique = true, nullable = false, length = 8, columnDefinition = "int(8) not null   comment '编码'")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seq_physical_type_def")
	// @GeneratedValue(strategy = GenerationType.TABLE)
	public int getPhyId() {
		return phyId;
	}
	
	public void setPhyId(int phyId) {
		this.phyId = phyId;
	}

	@Column(name = "PHY_NAME")
	public String getPhyName() {
		return phyName;
	}

	public void setPhyName(String phyName) {
		this.phyName = phyName;
	}

	@Column(name = "PHY_TYPE")
	public byte getPhyType() {
		return phyType;
	}

	public void setPhyType(byte phyType) {
		this.phyType = phyType;
	}

	@Column(name = "LEVEL", precision = 8, scale = 0)
	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	@Column(name = "IS_LEAF")
	public byte getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(byte isLeaf) {
		this.isLeaf = isLeaf;
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
	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		if (this.updateTime != null)
			return this.updateTime;
		return new Date();
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UID")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "EXT1")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT2")
	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT3")
	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT4")
	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	@Transient
	public int getPid() {
		if (physicalTypeDef != null && physicalTypeDef.getPhyId() >= 0) {
			return physicalTypeDef.getPhyId();
		}
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return the physicalTypeDef
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID", nullable = true, columnDefinition = "int(8) default null comment '上级编码'")
	public PhysicalTypeDef getPhysicalTypeDef() {
		return physicalTypeDef;
	}

	/**
	 * @param physicalTypeDef
	 *            the physicalTypeDef to set
	 */
	public void setPhysicalTypeDef(PhysicalTypeDef physicalTypeDef) {
		this.physicalTypeDef = physicalTypeDef;
	}

	/**
	 * @return the physicalTypeDefs
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "physicalTypeDef", cascade = CascadeType.ALL)
	public Set<PhysicalTypeDef> getPhysicalTypeDefs() {
		return physicalTypeDefs;
	}

	/**
	 * @param physicalTypeDefs
	 *            the physicalTypeDefs to set
	 */
	public void setPhysicalTypeDefs(Set<PhysicalTypeDef> physicalTypeDefs) {
		this.physicalTypeDefs = physicalTypeDefs;
	}
	
	
	@Column(name = "S_NAME", nullable=true,length=30, columnDefinition="varchar(30) comment '简称'")
	public String getsName() {
		return sName;
	}
	
	/**
	 * @param sName the sName to set
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}
	
	@Column(name = "STATUS", nullable = true, columnDefinition = "tinyint default 0 comment '状态'")
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}