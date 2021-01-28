package org.zhanghua.ssm.common.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 实体类基类
 * 
 * @author Wujun
 * 
 */
public abstract class BaseEntity/* <ID extends Serializable> */{

	public abstract String getId();

	public abstract void setId(final String id);

	// public abstract ID getId();
	//
	// public abstract void setId(final ID id);

	public boolean isNew() {
		return StringUtils.isBlank(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity that = (BaseEntity) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
