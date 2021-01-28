package org.coderfun.fieldmeta.service;

import java.util.List;

import org.coderfun.fieldmeta.entity.PageField;

import klg.j2ee.common.dataaccess.BaseService;

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
