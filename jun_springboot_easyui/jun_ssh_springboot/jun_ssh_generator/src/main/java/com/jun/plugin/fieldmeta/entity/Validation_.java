package com.jun.plugin.fieldmeta.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.jun.plugin.common.BaseEntity_;

@Generated(value="Dali", date="2019-08-16T00:24:40.558+0800")
@StaticMetamodel(Validation.class)
public class Validation_ extends BaseEntity_ {
	public static volatile SingularAttribute<Validation, String> code;
	public static volatile SingularAttribute<Validation, String> description;
	public static volatile SingularAttribute<Validation, String> javaValid;
	public static volatile SingularAttribute<Validation, String> jsValid;
	public static volatile SingularAttribute<Validation, String> name;
}
