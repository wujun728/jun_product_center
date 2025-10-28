package com.jun.plugin.demo.jpa.tree.main;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.jun.plugin.common.dataaccess.entity.OrderEntity_;

@Generated(value="Dali", date="2018-11-30T00:17:04.970+0800")
@StaticMetamodel(MenuTree.class)
public class MenuTree_ extends OrderEntity_ {
	public static volatile SingularAttribute<MenuTree, String> iconCls;
	public static volatile SingularAttribute<MenuTree, String> type;
	public static volatile SingularAttribute<MenuTree, String> url;
	public static volatile SingularAttribute<MenuTree, String> permCode;
}
