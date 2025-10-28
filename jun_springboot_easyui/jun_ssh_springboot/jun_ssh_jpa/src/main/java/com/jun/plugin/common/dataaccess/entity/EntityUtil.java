package com.jun.plugin.common.dataaccess.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class EntityUtil {
	// 解析缓存，ConcurrentHashMap
	private static Map<String,RelationParser> parserCache=new ConcurrentHashMap<String,RelationParser>();
	
	public static Set<String> getRelatedEntities(Class<?> entityClass,EntityManager em){
		return getRelationParser(entityClass, em).getRelatedEntities();
	}
		
	public static List<Relation> getRelations(Class<?> entityClass,EntityManager em){
		RelationParser parser = getRelationParser(entityClass, em);
		return parser.getRelations();
	}
	public static boolean hasRelatedData(Class<?> entityClass, Object id,EntityManager em){
		List<Relation> relations=getRelations(entityClass, em);
		for(Relation relation:relations){
			String sql="select count(*) from " + relation.getTableName()
					+ " where "+ relation.getJoinColumn() +" =:col ";
			Query query=em.createNativeQuery(sql);
			query.setParameter("col", id);
			BigInteger count=(BigInteger) query.getResultList().iterator().next();
			if(count.longValue() !=0 ){
				return true;
			}
		}
		return false;
	}
	
	private static RelationParser getRelationParser(Class<?> entityClass,EntityManager em){
		if(parserCache.containsKey(entityClass.getName())){
			return parserCache.get(entityClass.getName());
		}else{
			RelationParser relationParser=new RelationParser(entityClass, em);
			relationParser.parse();
			parserCache.put(entityClass.getName(), relationParser);
			return relationParser;
		}
	}
	
	/**
	 * 获取主键类型
	 * @param entityClass
	 * @param entityManager
	 * @return
	 */
	public <T> Class<?> getIdType(Class<T> entityClass , EntityManager entityManager){
		Metamodel meta = entityManager.getMetamodel();
		EntityType<T> entityType = meta.entity(entityClass);
		return entityType.getIdType().getJavaType();
	}
	
	/**
	 * 获取表名
	 * 
	 * @param em
	 * @param entityClass
	 * @return
	 */
	public static <T> String getTableName(EntityManager em, Class<T> entityClass) {
		/*
		 * Check if the specified class is present in the metamodel. Throws
		 * IllegalArgumentException if not.
		 */
		Metamodel meta = em.getMetamodel();
		EntityType<T> entityType = meta.entity(entityClass);

		// Check whether @Table annotation is present on the class.
		Table t = entityClass.getAnnotation(Table.class);

		String tableName = (t == null) ? entityType.getName().toUpperCase() : t.name();
		return tableName;
	}
	/**
	 * 获取数据库字段名
	 * 
	 * @param propertyName
	 * @param entityClass
	 * @param em
	 * @return
	 */
	public static <T> String getCloumnName(String propertyName,Class<T> entityClass,EntityManager em){
		Metamodel meta = em.getMetamodel();
		EntityType<T> entityType = meta.entity(entityClass);
		for (Attribute attr : entityType.getAttributes()) {
			if(!attr.getName().equals(propertyName)){
				continue;
			}else{
				Member member = attr.getJavaMember();
				JoinColumn joinColumn = null;
				Column column = null;

				if (member instanceof Field) {
					Field field = (Field) member;
					joinColumn = field.getAnnotation(JoinColumn.class);
					column = field.getAnnotation(Column.class);
				} else {
					Method method = (Method) member;
					joinColumn = method.getAnnotation(JoinColumn.class);
					column = method.getAnnotation(Column.class);
				}
				
				if(column != null){
					return column.name();
				}
				if(joinColumn !=null){
					return joinColumn.name();
				}
				return propertyName;			
			}		
		}
		
		return null;
	}
}
