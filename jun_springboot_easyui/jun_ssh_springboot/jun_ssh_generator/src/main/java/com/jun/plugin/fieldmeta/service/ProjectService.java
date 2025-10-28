package com.jun.plugin.fieldmeta.service;

import com.jun.plugin.common.dataaccess.BaseService;
import com.jun.plugin.fieldmeta.entity.Project;

public interface ProjectService extends BaseService<Project, Long>{
	
	public Project getDefaultProject();
	

}
