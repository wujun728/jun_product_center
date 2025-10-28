package com.jun.plugin.fieldmeta.service;

import com.jun.plugin.common.dataaccess.BaseService;
import com.jun.plugin.fieldmeta.entity.PageField;

public interface PageFieldService extends BaseService<PageField, Long> {
	/**
	 * 方法被禁用
	 */
	@Override
	@Deprecated
	PageField save(PageField arg0);
	/**
	 * 方法被禁用
	 */
	@Override
	@Deprecated
	void delete(Long arg0);
}
