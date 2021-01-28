package org.coderfun.fieldmeta.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-08-25T04:55:54.501+0800")
@StaticMetamodel(PageField.class)
public class PageField_ {
	public static volatile SingularAttribute<PageField, Long> id;
	public static volatile SingularAttribute<PageField, Long> tableId;
	public static volatile SingularAttribute<PageField, String> tableName;
	public static volatile SingularAttribute<PageField, EntityField> entityField;
	public static volatile SingularAttribute<PageField, String> fieldTitle;
	public static volatile SingularAttribute<PageField, String> canList;
	public static volatile SingularAttribute<PageField, String> canQuery;
	public static volatile SingularAttribute<PageField, String> queryType;
	public static volatile SingularAttribute<PageField, String> canEdit;
	public static volatile SingularAttribute<PageField, String> required;
	public static volatile SingularAttribute<PageField, String> gridRowCol;
	public static volatile SingularAttribute<PageField, String> needNewLine;
	public static volatile SingularAttribute<PageField, String> codeClass;
	public static volatile SingularAttribute<PageField, String> codeName;
	public static volatile SingularAttribute<PageField, String> formType;
	public static volatile SingularAttribute<PageField, String> fieldFormatter;
}
