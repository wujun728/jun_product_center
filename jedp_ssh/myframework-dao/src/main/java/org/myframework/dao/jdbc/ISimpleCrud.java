package org.myframework.dao.jdbc;

import java.util.Map;

/**
 * 按照表名和beanName的对应关系，还有表字段和param中键名的对应关系，完成表的单条记录的增删改查;
 * 特别说明：删改查时param必须指定主键字段的值；新增时可以指定主键生成策略默认生成策略是UuidGenerator；
 *
 * 表名不带前缀举例：custViewPlan 对应的表名 CUST_VIEW_PLAN ; planId 对应主键字段 PLAN_ID
 *     beanName = "custViewPlan" ;
 * 新增操作：
 *      param.put("lanName", "contact");
 *		param.put(IdGenerator.ID_TYPE_KEY, SequenceGenerator.SEQUENCE );
 *		param.put(IdGenerator.SEQUENCE_KEY,"SEQ_CUST_CHANNEL_PLAN_ID") ;
 *		ISimpleCrud.save("custViewPlan",  param);
 * 读取操作：
 * 		param.put("planId", 1);
 *		Map result = ISimpleCrud.read(beanName,  param);
 * 删除操作：
 *      param.put("planId", 1);
 *		Map result = ISimpleCrud.delete(beanName,  param);
 * 修改操作：
 *      param.put("planId", 1);
 *		Map result = ISimpleCrud.update(beanName,  param);
 *------------------------------------------------------------------------------
 * 表名带前缀举例：custViewPlan 对应的表名 TBL_CUST_VIEW_PLAN ; planId 对应主键字段 PLAN_ID
 * 如果 beanName = "tblCustViewPlan" ,那么调用和上面的例子一样；
 *
 * 如果beanName = "custViewPlan"  ,那么就要设置prefix ="TBL_",并调用相关带prefix的方法；
 * 新增操作：
 *      public abstract void save(String beanName,  prefix,
			Map<String, Object> param);
 *
 * <ol>
 * <li>{@link  }
 * </ol>
 *
 * @author Wujun
 * @since 1.0
 */
public interface ISimpleCrud {

	/**
	 * @param beanName 表名对应的JavaBean参数名
	 * @param prefix 数据库设计时的表名前缀
	 * @param param  表字段作为键名,字段值作为value
	 */
	public abstract void save(String beanName, String prefix,
			Map<String, Object> param);

	/**
	 * @param beanName
	 * @param param
	 */
	public abstract void save(String beanName, Map<String, Object> param);

	/**
	 * 删改查时param必须指定主键字段的值
	 * @param beanName 表名对应的JavaBean参数名
	 * @param prefix 数据库设计时的表名前缀
	 * @param param  表字段作为键名,字段值作为value
	 */
	public abstract void update(String beanName, String prefix,
			Map<String, Object> param);

	/**
	 * @param beanName
	 * @param param
	 */
	public abstract void update(String beanName, Map<String, Object> param);

	/**
	 * 删改查时param必须指定主键字段的值
	 * @param beanName 表名对应的JavaBean参数名
	 * @param prefix 数据库设计时的表名前缀
	 * @param param  表字段作为键名,字段值作为value
	 */
	public abstract void delete(String beanName, String prefix,
			Map<String, Object> param);

	/**
	 * @param beanName
	 * @param param
	 */
	public abstract void delete(String beanName, Map<String, Object> param);

	/**
	 * 删改查时param必须指定主键字段的值
	 * @param beanName 表名对应的JavaBean参数名
	 * @param prefix 数据库设计时的表名前缀
	 * @param param  表字段作为键名,字段值作为value
	 */
	public abstract Map<String, Object> read(String beanName, String prefix,
			Map<String, Object> param);

	/**
	 * @param beanName
	 * @param param
	 * @return
	 */
	public abstract Map<String, Object> read(String beanName,
			Map<String, Object> param);
}
