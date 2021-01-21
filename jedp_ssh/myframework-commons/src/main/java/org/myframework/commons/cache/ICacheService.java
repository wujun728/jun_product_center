/**  

* Licensed to SAICMotor,Inc. under the terms of the SAICMotor 
* Software License version 1.0.

* See the NOTICE file distributed with this work for additional 
* information regarding copyright ownership.  
* ----------------------------------------------------------------------------
* Date           Who      Version        Comments
* 2011-12-28     gugh     1.0            Initial Version

*/  

package org.myframework.commons.cache;

/**
 * 
 */
public interface ICacheService {
	
	public static String CACHE_TEST_KEY = "_$_test_key_$_";
	
	/**
	 * 设置对象缓存的超时时间（以毫秒为单位）
	 * @param time
	 */
	public void setExpiredTime(long time);
	
	/**
	 * 返回指定key的cache对象
	 * @param key
	 * @return
	 */
	public Object get(String key);

	/**
	 * 将指定的对象value以key为主键缓存到cache服务中
	 * @param key
	 * @param value
	 * @return
	 */
	public void put(String key, Object value);

	/**
	 * 删除指定的缓存对象
	 * @param key
	 */
	public void delete(String key);

	/**
	 * 关闭缓存
	 */
	public void shutDown();
	
	/**
	 * 缓存提供者是否有效
	 * @return
	 */
	public boolean isAvailable();
	

}
