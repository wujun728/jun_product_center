package org.coderfun.sys.dict.service;

import org.coderfun.sys.dict.dao.CodeClassDAO;
import org.coderfun.sys.dict.entity.CodeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klg.j2ee.common.dataaccess.BaseServiceImpl;

@Service
public class CodeClassServiceImpl  extends BaseServiceImpl<CodeClass, Long> implements CodeClassService{
	@Autowired
	CodeClassDAO codeClassDAO;
}
