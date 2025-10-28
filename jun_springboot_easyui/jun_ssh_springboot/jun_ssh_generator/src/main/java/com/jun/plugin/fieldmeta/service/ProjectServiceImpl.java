package com.jun.plugin.fieldmeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.dataaccess.BaseServiceImpl;
import com.jun.plugin.fieldmeta.dao.ProjectDAO;
import com.jun.plugin.fieldmeta.entity.Project;
import com.jun.plugin.fieldmeta.entity.Project_;
import com.jun.plugin.query.jpa.expr.AExpr;
import com.jun.plugin.sys.dict.SystemCode;

@Service
public class ProjectServiceImpl  extends BaseServiceImpl<Project, Long> implements ProjectService{
	@Autowired
	ProjectDAO projectDAO;

	@Override
	public Project getDefaultProject() {
		// TODO Auto-generated method stub
		Project project = projectDAO.getOne(AExpr.eq(Project_.isDefaultCode, SystemCode.YES));
		return project;
	}
}
