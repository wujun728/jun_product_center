package com.jun.plugin.fieldmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.fieldmeta.dao.ModuleDAO;
import com.jun.plugin.fieldmeta.entity.Module;

@Service
public class ModuleServiceImpl  extends BaseServiceImpl<Module, Long> implements ModuleService{
	@Autowired
	ModuleDAO moduleDAO;
}
