package com.jun.plugin.demo.jpa.tree.main;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-06-04T21:46:51.602+0800")
@StaticMetamodel(TreeEntityHA.class)
public class TreeEntityHA_ {
	public static volatile SingularAttribute<TreeEntityHA, Long> id;
	public static volatile SingularAttribute<TreeEntityHA, Long> parentId;
	public static volatile SingularAttribute<TreeEntityHA, String> name;
	public static volatile ListAttribute<TreeEntityHA, TreeEntityHA> children;
}
