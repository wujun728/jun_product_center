package com.pearadmin.common.plugin.system.service;

import com.pearadmin.common.plugin.system.domain.*;
import java.util.List;

/**
 * Describe: 系统基础 API
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public interface SysContext {
	
	/**
	  * 根据用户账号查询用户信息
	 * @param username
	 * @return
	 */
	SysBaseUser getUserByName(String username);


	/**
	  * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	SysBaseUser getUserById(String id);
	
	/**
	 * 通过用户账号查询角色集合
	 * @param username
	 * @return
	 */
	List<SysBaseRole> getRolesByUsername(String username);

	/**
	 * 根据字典code获取可用的字典列表数据
	 * @param typeCode
	 * @return
	 */
	List<SysBaseDict> selectDictByCode(String typeCode);

	/**
	 * 查询表字典通过查询指定table的 text code key 获取字典值
	 * @param table 表名
	 * @param text label
	 * @param code value
	 * @return
	 */
    List<SysBaseDict> queryTableDictItemsByCode(String table, String text, String code);


	/**
	 * 查询表字典 通过查询指定table的 text code 获取字典（指定查询条件）
	 * @param table 表名
	 * @param text label
	 * @param code value
	 * @return
	 */
	List<SysBaseDict> queryTableDictItemsByCodeAndFilter(String table, String text, String code, String filterSql);


	/**
	 * 查询表字典 通过查询指定table的 text code key 获取字典值，包含value
	 * @param table 表名
	 * @param text label
	 * @param code value
	 * @param keyArray values
	 * @return
	 */
	List<SysBaseDict>queryTableDictByKeys(String table, String text, String code, String[] keyArray);

	/**
	 * 查询系统配置
	 *
	 * @param code
	 * */
	String getConfig(String code);

	/**
	 * 存储日志
	 * */
	Boolean saveLog(SysBaseLog log);
}
