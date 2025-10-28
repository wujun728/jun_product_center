package com.jun.plugin.demo.hello.Main;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EmbeddableType;

import org.junit.Test;

import com.jun.plugin.basetest.BaseTest;
import com.jun.plugin.common.utils.MyPrinter;

public class MetaModelTest extends BaseTest {
	@PersistenceContext
	EntityManager em;

	@Test
	public void testEntityClazzs() {
		for (javax.persistence.metamodel.EntityType<?> et : em.getMetamodel().getEntities()) {
			System.out.println("entityClass:" + et.getJavaType());
			for (Attribute attr : et.getAttributes()) {
				Member member = attr.getJavaMember();
				if (member instanceof Field) {
					Field field = (Field) member;
					// System.out.println("field:"
					// +"\t"+field.getAnnotation(ManyToOne.class));
					System.out.println("field:" + field.getName());
				} else {
					Method method = (Method) member;
					System.out.println("method:" + method.getName());
				}
			}
		}
	}

	@Test
	public void testMetaModel() {

		for (EmbeddableType<?> mt : em.getMetamodel().getEmbeddables()) {
			MyPrinter.print(mt.getJavaType());
		}
	}
}
