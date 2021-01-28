package org.coderfun.fieldmeta.service;

import org.coderfun.fieldmeta.dao.ProjectDAO;
import org.coderfun.fieldmeta.entity.Project;
import org.coderfun.fieldmeta.entity.Project_;
import org.coderfun.sys.dict.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klg.j2ee.common.dataaccess.BaseServiceImpl;
import klg.j2ee.query.jpa.expr.AExpr;

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
