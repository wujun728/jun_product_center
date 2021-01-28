package org.coderfun.fieldmeta.service;

import org.coderfun.fieldmeta.entity.EntityField;

import klg.j2ee.common.dataaccess.BaseService;

public interface EntityFieldService extends BaseService<EntityField, Long>{

	/**
	 * 方法被禁用
	 */
	@Override
	@Deprecated
	EntityField save(EntityField arg0);
	
	/**
	 * 方法被禁用
	 */	
	@Deprecated
	@Override
	void delete(Long arg0);
}
