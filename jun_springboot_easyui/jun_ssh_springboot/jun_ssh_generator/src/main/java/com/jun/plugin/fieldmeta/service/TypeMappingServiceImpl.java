package com.jun.plugin.fieldmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.fieldmeta.dao.TypeMappingDAO;
import com.jun.plugin.fieldmeta.entity.TypeMapping;

@Service
public class TypeMappingServiceImpl  extends BaseServiceImpl<TypeMapping, Long> implements TypeMappingService{
	@Autowired
	TypeMappingDAO typeMappingDAO;
}
