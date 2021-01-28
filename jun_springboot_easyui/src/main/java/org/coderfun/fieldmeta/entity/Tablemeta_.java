package org.coderfun.fieldmeta.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.coderfun.common.BaseEntity_;

@Generated(value="Dali", date="2019-08-25T04:55:54.511+0800")
@StaticMetamodel(Tablemeta.class)
public class Tablemeta_ extends BaseEntity_ {
	public static volatile SingularAttribute<Tablemeta, Long> moduleId;
	public static volatile SingularAttribute<Tablemeta, String> moduleName;
	public static volatile SingularAttribute<Tablemeta, String> entityName;
	public static volatile SingularAttribute<Tablemeta, String> businessName;
	public static volatile SingularAttribute<Tablemeta, String> simpleName;
	public static volatile SingularAttribute<Tablemeta, Date> createTime;
	public static volatile SingularAttribute<Tablemeta, String> canDelete;
	public static volatile SingularAttribute<Tablemeta, String> canEdit;
	public static volatile SingularAttribute<Tablemeta, Date> modifyTime;
	public static volatile SingularAttribute<Tablemeta, String> options;
	public static volatile SingularAttribute<Tablemeta, String> parentTableFkName;
	public static volatile SingularAttribute<Tablemeta, String> parentTableName;
	public static volatile SingularAttribute<Tablemeta, String> tableName;
	public static volatile SingularAttribute<Tablemeta, String> entitySuperClass;
}
