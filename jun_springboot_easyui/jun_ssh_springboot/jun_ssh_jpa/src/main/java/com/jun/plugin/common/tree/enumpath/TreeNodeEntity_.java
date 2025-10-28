package com.jun.plugin.common.tree.enumpath;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.jun.plugin.common.dataaccess.entity.OrderEntity_;

@Generated(value="Dali", date="2018-06-07T19:34:57.687+0800")
@StaticMetamodel(TreeNodeEntity.class)
public class TreeNodeEntity_ extends OrderEntity_ {
	public static volatile SingularAttribute<TreeNodeEntity, String> nodeName;
	public static volatile SingularAttribute<TreeNodeEntity, String> description;
	public static volatile SingularAttribute<TreeNodeEntity, String> parentNodePath;
	public static volatile SingularAttribute<TreeNodeEntity, Integer> nodeLevel;
	public static volatile SingularAttribute<TreeNodeEntity, Integer> nodeDegree;
	public static volatile SingularAttribute<TreeNodeEntity, Serializable> parentId;
	public static volatile SingularAttribute<TreeNodeEntity, Long> version;
}
