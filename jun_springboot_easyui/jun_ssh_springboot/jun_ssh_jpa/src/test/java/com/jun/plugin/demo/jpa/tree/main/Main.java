package com.jun.plugin.demo.jpa.tree.main;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter;

public class Main extends BaseTest{
	@PersistenceContext
	EntityManager em;	
	
	
	
	/**
	 * 利用hibernate 注解实现tree
	 */
	
	@Test
	public void testHibernateTree(){
		TreeEntityHA root = em.find(TreeEntityHA.class, 1L);
		MyPrinter.printJson(root);
	}	
	
	
	@Test
	public void testNullQuery(){
		String sql = "select * from tree_entity where parent_id = :parent_id";
		Query query = em.createNativeQuery(sql, TreeEntityHA.class);
		query.setParameter("parent_id", null);
		MyPrinter.print(query.getResultList());
	}
	
	@Test
	public void testConvertUtils(){
		Long value = (Long) ConvertUtils.convert("125", Long.class);
		System.out.println(value);
	}
	
	@Test
	public void testArrayListToArray(){
		String[] strs = {"asd1","asd2"};
		String[] str2 =(String[]) Arrays.asList(strs).toArray();
		MyPrinter.print(str2);
	}
}
