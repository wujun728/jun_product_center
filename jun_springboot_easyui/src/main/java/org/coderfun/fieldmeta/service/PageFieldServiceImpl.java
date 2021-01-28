package org.coderfun.fieldmeta.service;

import org.coderfun.fieldmeta.dao.PageFieldDAO;
import org.coderfun.fieldmeta.entity.PageField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klg.j2ee.common.dataaccess.BaseServiceImpl;

@Service
public class PageFieldServiceImpl  extends BaseServiceImpl<PageField, Long> implements PageFieldService{
	@Autowired
	PageFieldDAO pageFieldDAO;
	

	/**
	 * 方法被禁用
	 */
	@Override
	public PageField save(PageField entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}
}
