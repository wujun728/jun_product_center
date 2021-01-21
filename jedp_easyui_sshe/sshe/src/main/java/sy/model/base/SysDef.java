package sy.model.base;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sys_def", schema = "" )
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysDef implements java.io.Serializable {

	private int pid;// 虚拟属性，用于获得当前资源的父资源ID
	
	private int sysCode;
	private String sysName;
	private short type;
	private String sName;
	// private int parentCode;
	private short level;
	private short isLeaf;
	private Date createdatetime;
	private Date updatedatetime;
	private String uid;
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_CODE", nullable=true)
	public SysDef getSysDef() {
		return sysDef;
	}

	public void setSysDef(SysDef sysDef) {
		this.sysDef = sysDef;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysDef", cascade = CascadeType.ALL)
	public Set<SysDef> getSysDefs() {
		return sysDefs;
	}

	public void setSysDefs(Set<SysDef> sysDefs) {
		this.sysDefs = sysDefs;
	}

	private SysDef sysDef;
	private Set<SysDef> sysDefs;

	@Id
	@Column(name = "SYS_CODE", unique = true, nullable = false, length = 8)
	public int getSysCode() {
		if (this.sysCode > 0) {
			return sysCode;
		}
		return (int) UUID.randomUUID().node();
	}

	public void setSysCode(int sysCode) {
		this.sysCode = sysCode;
	}

	@Column(name = "SYS_NAME")
	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	@Column(name = "TYPE")
	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	@Column(name = "LEVEL", precision = 8, scale = 0)
	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	@Column(name = "IS_LEAF")
	public short getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(short isLeaf) {
		this.isLeaf = isLeaf;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}

	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
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
		if (sysDef != null && sysDef.getSysCode()  >= 0) {
			return sysDef.getSysCode();
		}
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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
}
