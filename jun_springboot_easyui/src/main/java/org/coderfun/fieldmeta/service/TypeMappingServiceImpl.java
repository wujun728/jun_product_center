package org.coderfun.fieldmeta.service;

import org.coderfun.fieldmeta.dao.TypeMappingDAO;
import org.coderfun.fieldmeta.entity.TypeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klg.j2ee.common.dataaccess.BaseServiceImpl;

@Service
public class TypeMappingServiceImpl  extends BaseServiceImpl<TypeMapping, Long> implements TypeMappingService{
	@Autowired
	TypeMappingDAO typeMappingDAO;
}
