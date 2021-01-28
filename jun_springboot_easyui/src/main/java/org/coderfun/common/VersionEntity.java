package org.coderfun.common;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * 线程安全的实体
 * 
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class VersionEntity<ID extends Serializable> extends BaseEntity<ID> {

	private static final long serialVersionUID = 1L;
	/** 版本 */
	@Version
	@Column(nullable = false)
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
