package com.xiongyw.mapper.base;

import java.util.List;

/**
 * 已经实现基本的 增,删,改,查接口,不需要重复写
 * 所有mapper都继承这个BaseMapper
 */
public interface BaseMapper {
	/**
	 * 1：传入继承FormMap的子类对象,返回是一个List<T>集合<br/>
	 * 2：调用findByPage接口,必须传入PageView对象! formMap.set("paging", pageView);<br/>
	 * 1：根据多字段分页查询 <br/>
	 * 3：如果是多个id,用","组成字符串. <br/>
	 * 4：formMap.put("id","xxx") 或 formMap.put("id","xxx,xxx,xxx") <br/>
	 * 5：formMap.put("name","xxx") 或 formMap.put("name","xxx,xxx,xxx") <br/>
	 * 6：兼容模糊查询。 formMap.put("name","用户%") 或 formMap.put("name","%用户%") <br/>
	 * 7：兼容排序查询 : formMap.put("$orderby","order by id desc");  
	 * 
	 * @return <T> List<T>
	 */
	public <T> List<T> findByPage(T formMap);

	/**
	 * 
	 * 1：自定义where查询条件，传入继承FormMap的子类对象,返回是一个List<T>集合<br/>
	 * 2：返回查询条件数据,如不传入.则返回所有数据..如果附加条件.如下 <br/>
	 * 3：formMap.put('where","id=XX and name= XX order by XX") <br/>
	 * 
	 * @return <T> List<T>
	 */
	public <T> List<T> findByWhere(T formMap);

	/**
	 * 1：更新数据<br/>
	 * 2：传入继承FormMap的子类对象<br/>
	 * @return formMap
	 * @throws Exception
	 */
	public void editEntity(Object formMap) throws Exception;

	/**
	 * 1：根据多字段查询 <br/>
	 * 2：传入继承FormMap的子类对象 <br/>
	 * 3：如果是多个id,用","组成字符串. <br/>
	 * 4：formMap.put("id","xxx") 或 formMap.put("id","xxx,xxx,xxx") <br/>
	 * 5：formMap.put("name","xxx") 或 formMap.put("name","xxx,xxx,xxx") <br/>
	 * 6：兼容模糊查询。 formMap.put("name","用户%") 或 formMap.put("name","%用户%") <br/>
	 * 7：兼容排序查询 : formMap.put("$orderby","order by id desc");  
	 * 
	 * @return List<T>
	 */
	public <T> List<T> findByNames(T formMap);

	/**
	 * 1：根据某个字段查询数据 <br/>
	 * 
	 * @return <T> List<T>
	 */
	public <T> List<T> findByAttribute(String key, String value, Class<T> clazz);

	/**
	 * 1：根据某个字段删除数据 <br/>
	 * 
	 * @throws Exception
	 */
	public void deleteByAttribute(String key, String value, Class<?> clazz) throws Exception;

	/**
	 * 1：传入继承FormMap的子类对象 <br/>
	 * 2：保存数据,保存数据后返回子类对象的所有数据包括id..主建统一返回为id <br/>
	 * 
	 * @throws Exception
	 */
	public void addEntity(Object formMap) throws Exception;
	
	/**
	 * 1：批量保存数据,如果是mysql,在驱动连接加上&allowMultiQueries=true这个参数 <br/>
	 * 2：传入继承FormMap的子类对象的一个list集合 <br/>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void batchSave(List formMap) throws Exception;

	/**
	 * 1：根据多个字段删除 ,传入继承FormMap的子类对象<br/>
	 * 2：如果是多个id值,用","组成字符串. <br/>
	 * 3：formMap.put("id","xxx") 或 formMap.put("id","xxx,xxx,xxx")<br/>
	 * 4：formMap.put("name","xxx") 或 formMap.put("name","xxx,xxx,xxx") <br/>
	 * 
	 * @throws Exception
	 */
	public void deleteByNames(Object formMap) throws Exception;
	
	/**
	 * 1：根据某个字段查询数据，返回一个对象，如果返回多个值，则异常 <br/>
	 */
	public <T> T findbyFrist(String key,String value,Class<T> clazz);
	/**
	 * 1：获取表字段存在放缓存 <br/>
	 * 
	 * @return List<Map<?, ?>>
	 */
	public <T> List<T> initTableField(T formMap);
	
}
