package com.jun.plugin.sys.dict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.sys.dict.dao.CodeClassDAO;
import com.jun.plugin.sys.dict.entity.CodeClass;

@Service
public class CodeClassServiceImpl  extends BaseServiceImpl<CodeClass, Long> implements CodeClassService{
	@Autowired
	CodeClassDAO codeClassDAO;
}
