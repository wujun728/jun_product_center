package com.jun.plugin.fieldmeta.service;

import com.jun.plugin.common.dataaccess.BaseService;
import com.jun.plugin.fieldmeta.entity.EntityField;

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
