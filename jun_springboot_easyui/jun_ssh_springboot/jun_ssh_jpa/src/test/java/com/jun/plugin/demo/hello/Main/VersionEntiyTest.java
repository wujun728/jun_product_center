package com.jun.plugin.demo.hello.Main;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.demo.jpa.respository.UserDAO;
import com.jun.plugin.demo.jpa.respository.baseEntity.Aentity;

public class VersionEntiyTest extends BaseTest{
	@Autowired
	UserDAO userDAO;
	
	@PersistenceContext
	EntityManager em;
	
	
	@Test
	@Transactional
	@Rollback
	public void testSave() {	
		Aentity aentity = new Aentity();
		aentity.setId(3L);
		aentity.setContent("----");
		aentity.setVersion(1L);
		em.merge(aentity);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdate(){			
		Aentity aentity=em.find(Aentity.class,1L);
		aentity.setContent("||||||");
		em.persist(aentity);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdate2() throws JsonProcessingException{
		Aentity aentity=em.find(Aentity.class,1L);
		aentity.setContent("kkk");
	}
}
