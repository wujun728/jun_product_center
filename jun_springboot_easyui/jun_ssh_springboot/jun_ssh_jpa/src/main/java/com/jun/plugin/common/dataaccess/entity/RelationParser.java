package com.jun.plugin.common.dataaccess.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;

public class RelationParser {
	private Set<String> relatedEntities = new LinkedHashSet<String>();
	private List<Relation> relations = new ArrayList<Relation>();
	private Class<?> entityClass;
	private EntityManager em;

	public RelationParser(Class<?> entityClass, EntityManager em) {
		super();
		this.entityClass = entityClass;
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public RelationParser parse() {
		for (EntityType<?> et : em.getMetamodel().getEntities()) {
			for (Attribute attr : et.getAttributes()) {
				Member member = attr.getJavaMember();
				JoinColumn joinColumn = null;
				Class oneEntity = null;
				if (member instanceof Field) {
					Field field = (Field) member;
					joinColumn = field.getAnnotation(JoinColumn.class);
					oneEntity = field.getType();
				} else {
					Method method = (Method) member;
					joinColumn = method.getAnnotation(JoinColumn.class);
					oneEntity = method.getReturnType();
				}
				if (joinColumn != null && oneEntity.isAnnotationPresent(Entity.class)) {
					if(oneEntity == entityClass){
						relations.add(new Relation(EntityUtil.getTableName(em,
								et.getJavaType()), joinColumn.name()));
						relatedEntities.add(et.getJavaType().getName());
					}
				}
			}
		}
		return this;
	}

	public Set<String> getRelatedEntities() {
		return relatedEntities;
	}

	public void setRelatedEntities(Set<String> relatedEntities) {
		this.relatedEntities = relatedEntities;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

}
