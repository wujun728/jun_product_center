package org.coderfun.sys.dict.service;

import org.coderfun.sys.dict.dao.CodeItemDAO;
import org.coderfun.sys.dict.entity.CodeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klg.j2ee.common.dataaccess.BaseServiceImpl;

@Service
public class CodeItemServiceImpl  extends BaseServiceImpl<CodeItem, Long> implements CodeItemService{
	@Autowired
	CodeItemDAO codeItemDAO;
}
