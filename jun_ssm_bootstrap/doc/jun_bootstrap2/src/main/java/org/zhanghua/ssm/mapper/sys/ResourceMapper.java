package org.zhanghua.ssm.mapper.sys;

import java.util.List;

import org.zhanghua.ssm.common.mapper.CrudMapper;
import org.zhanghua.ssm.entity.sys.Resource;

public interface ResourceMapper extends CrudMapper<Resource, String> {

	/**
	 * 查询所有的菜单（超级管理员）
	 * 
	 * @return
	 */
	public List<Resource> queryAllMenus();

	/**
	 * 根据用户名查询菜单
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public List<Resource> queryMenus(String username);

	/**
	 * 查询所有的权限 （超级管理员）
	 * 
	 * @return
	 */
	public List<Resource> queryAllPermissions();

	/**
	 * 根据用户名查询权限
	 * 
	 * @param username
	 * @return
	 */
	public List<Resource> queryPermissions(String username);

}