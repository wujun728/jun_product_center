package org.myframework.dao.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;

/**
 * 基础数据定义， 与持久化数据相关，
 * 定义数据的创建时间和创建者等信息
 * 
 */
@MappedSuperclass
@Audited
/**
 * 0, 1, 2  
 * ADD,MOD and DEL
 */
public abstract class BaseDomain<PK extends Serializable> implements Serializable {	
	
	private static final long serialVersionUID = -607252936278440783L;

	// 创建者主键值
    @Column(name="CREATOR")
    protected String creator;

    // 创建时间
    @Column(name="CREATE_TIME")
    protected String createTime;

    // 最后修改者主键值
    @Column(name="LAST_MODIFIER")
    protected String lastModifier;

    // 最后修改时间
    @Column(name="LAST_MODIFY_TIME")
    protected String lastModifyTime;
	
	// 备注
	@Column(name="REMARK")
	protected String remark;
	
	// 租户ID
	@Column(name="DOMAIN_ID")
	protected String domainId;


	public void setCreator(String creator) {
		this.creator = creator;
	}
	
//	@CsvColumn(desc ="创建人" , defValue=CsvTable.CREATOR_ID,imported=false)
	public String getCreator() {
		return creator;
	}
	
//	@CsvColumn(desc ="创建时间" ,defValue=CsvTable.NOW_VALUE,imported=false)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	
}
