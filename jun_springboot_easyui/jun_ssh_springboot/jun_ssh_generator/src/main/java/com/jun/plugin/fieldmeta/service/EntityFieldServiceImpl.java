package com.jun.plugin.fieldmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.fieldmeta.dao.EntityFieldDAO;
import com.jun.plugin.fieldmeta.dao.PageFieldDAO;
import com.jun.plugin.fieldmeta.entity.EntityField;

@Service
public class EntityFieldServiceImpl extends BaseServiceImpl<EntityField, Long> implements EntityFieldService {
	@Autowired
	EntityFieldDAO entityFieldDAO;

	@Autowired
	PageFieldDAO pageFieldDAO;

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public EntityField save(EntityField entityField) {
		// TODO Auto-generated method stub

		return null;
	}
}
