package com.jun.plugin.fieldmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.fieldmeta.dao.PageFieldDAO;
import com.jun.plugin.fieldmeta.entity.PageField;

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
