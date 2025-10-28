package com.jun.plugin.fieldmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.fieldmeta.dao.ValidationDAO;
import com.jun.plugin.fieldmeta.entity.Validation;

@Service
public class ValidationServiceImpl  extends BaseServiceImpl<Validation, Long> implements ValidationService{
	@Autowired
	ValidationDAO validationDAO;
}
