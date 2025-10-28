package com.jun.plugin.fieldmeta.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.jun.plugin.common.BaseEntity_;

@Generated(value="Dali", date="2019-08-25T04:55:54.487+0800")
@StaticMetamodel(EntityField.class)
public class EntityField_ extends BaseEntity_ {
	public static volatile SingularAttribute<EntityField, Long> tableId;
	public static volatile SingularAttribute<EntityField, String> tableName;
	public static volatile SingularAttribute<EntityField, String> columnName;
	public static volatile SingularAttribute<EntityField, BigDecimal> columnSort;
	public static volatile SingularAttribute<EntityField, String> columnType;
	public static volatile SingularAttribute<EntityField, String> comments;
	public static volatile SingularAttribute<EntityField, String> attrName;
	public static volatile SingularAttribute<EntityField, String> attrType;
	public static volatile SingularAttribute<EntityField, Long> length;
	public static volatile SingularAttribute<EntityField, Long> decimalPlaces;
	public static volatile SingularAttribute<EntityField, String> pkRestrict;
	public static volatile SingularAttribute<EntityField, String> notNullRestrict;
	public static volatile SingularAttribute<EntityField, String> uniqueRestrict;
	public static volatile SingularAttribute<EntityField, String> notInsertRestrict;
	public static volatile SingularAttribute<EntityField, String> notUpdateRestrict;
	public static volatile SingularAttribute<EntityField, String> fieldValidCode;
}
