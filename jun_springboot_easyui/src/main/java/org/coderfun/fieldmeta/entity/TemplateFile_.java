package org.coderfun.fieldmeta.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.coderfun.common.OrderEntity_;

@Generated(value="Dali", date="2019-08-22T23:50:56.968+0800")
@StaticMetamodel(TemplateFile.class)
public class TemplateFile_ extends OrderEntity_ {
	public static volatile SingularAttribute<TemplateFile, String> name;
	public static volatile SingularAttribute<TemplateFile, String> uuidName;
	public static volatile SingularAttribute<TemplateFile, String> dir;
	public static volatile SingularAttribute<TemplateFile, String> genFiledirPattern;
	public static volatile SingularAttribute<TemplateFile, String> genFilekeyPattern;
	public static volatile SingularAttribute<TemplateFile, String> canMerge;
}
