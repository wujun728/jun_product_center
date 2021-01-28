package org.coderfun.common;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;



/**
 * Listener - 创建日期、修改日期、版本处理
 * 
 * @author YISHOP Team
 * @version 4.0
 */
public class EntityListener {

	/**
	 * 保存前处理
	 * 
	 * @param entity
	 *            实体对象
	 */
	@PrePersist
	public void prePersist(BaseEntity<?> entity) {
		entity.setCreateTime(new Date());
		entity.setModifyTime(new Date());
	}

	/**
	 * 更新前处理
	 * 
	 * @param entity
	 *            实体对象
	 */
	@PreUpdate
	public void preUpdate(BaseEntity<?> entity) {
		entity.setModifyTime(new Date());
	}

}