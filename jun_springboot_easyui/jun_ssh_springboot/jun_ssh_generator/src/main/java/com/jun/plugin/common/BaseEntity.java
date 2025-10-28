package com.jun.plugin.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;

import com.jun.plugin.common.utils.DatePartten;

/**
 * Entity - 基类
 * 
 * @version 4.0
 */
@EntityListeners(EntityListener.class)
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
	/** ID */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	/** 创建日期 */
	@DateTimeFormat(pattern = DatePartten.DATE_TIME)
	@Column(name = "create_time")
	private Date createTime;

	/** 修改日期 */
	@DateTimeFormat(pattern = DatePartten.DATE_TIME)
	@Column(name = "modify_time")
	private Date modifyTime;
	/** 备注 */
	@Lob
	@Column(name = "remark", columnDefinition = "TEXT")
	private String remark;

	private static final long serialVersionUID = -7103197793672462521L;

	/** "ID"属性名称 */
	public static final String ID_PROPERTY_NAME = "id";

	/** "创建日期"属性名称 */
	public static final String CREATE_TIME_PROPERTY_NAME = "createTime";

	/** "修改日期"属性名称 */
	public static final String MODIFY_TIME_PROPERTY_NAME = "modifyTime";

	/** "版本"属性名称 */
	public static final String VERSION_PROPERTY_NAME = "version";

	/** 备注属性名称 */
	public static final String REMARK_PROPERTY_NAME = "remark";

	/**
	 * 保存验证组
	 */
	public interface Save extends Default {

	}

	/**
	 * 更新验证组
	 */
	public interface Update extends Default {

	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 判断是否为新建对象
	 * 
	 * @return 是否为新建对象
	 */
	@Transient
	public boolean isNew() {
		return getId() == null;
	}

	/**
	 * 重写toString方法
	 * 
	 * @return 字符串
	 */
	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", getClass().getName(), getId());
	}

	/**
	 * 重写equals方法
	 * 
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseEntity<?> other = (BaseEntity<?>) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	/**
	 * 重写hashCode方法
	 * 
	 * @return HashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += getId() != null ? getId().hashCode() * 31 : 0;
		return hashCode;
	}

}
