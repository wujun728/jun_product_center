package org.coderfun.fieldmeta.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;




public class ModuleServicceTest {
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	EntityManager em;
	
	/**
	 * 没有带上version ， object references an unsaved transient instance
	 */
//	@Test
//	public void testSave(){
//		Module module=new Module();
//		module.setModuleName("test");
//		module.setProject(MyBeanUtil.newAndSet(Project.class, "id", 2L));
//		
//		moduleService.save(module);
//	}
	
	/**
	 * 带上version 保存成功
	 */
	
//	@Test
//	public void testSave1(){
//		Module module=new Module();
//		module.setModuleName("test");
//		Project project=MyBeanUtil.newAndSet(Project.class, "id", 2L);
//		 //project.setVersion(projectService.getById(2L).getVersion());
//		module.setProject(project);
//		
//		moduleService.save(module);
//	}
}
