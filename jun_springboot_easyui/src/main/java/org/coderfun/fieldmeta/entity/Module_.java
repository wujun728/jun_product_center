package org.coderfun.fieldmeta.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.coderfun.common.BaseEntity_;

@Generated(value="Dali", date="2019-08-17T14:52:46.446+0800")
@StaticMetamodel(Module.class)
public class Module_ extends BaseEntity_ {
	public static volatile SingularAttribute<Module, String> moduleName;
	public static volatile SingularAttribute<Module, Project> project;
	public static volatile SingularAttribute<Module, String> description;
	public static volatile SingularAttribute<Module, String> packageName;
	public static volatile SingularAttribute<Module, String> author;
	public static volatile SingularAttribute<Module, String> copyRight;
}
