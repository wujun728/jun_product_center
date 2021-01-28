package org.zhanghua.ssm.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhanghua.ssm.common.service.CrudService;
import org.zhanghua.ssm.common.utils.UserUtils;
import org.zhanghua.ssm.entity.sys.Resource;
import org.zhanghua.ssm.entity.sys.User;
import org.zhanghua.ssm.mapper.sys.ResourceMapper;

@Service
public class ResourceService extends CrudService<Resource, String> {

	@Autowired
	private ResourceMapper mapper;


	/**
	 *  查询左侧菜单
	 * @param username
	 * @return
	 */
	public List<Resource> queryMenus(String username) {
		// 判断是否超级管理员
		User user = UserUtils.getUser();
		if (1 == user.getIsAdmin()) {
			return mapper.queryAllMenus();
		}
		return mapper.queryMenus(username);
	}

	/**
	 * 查询权限
	 * @param username
	 * @return
	 */
	public List<Resource> queryPermissions(String username) {
		// 判断是否超级管理员
		User user = UserUtils.getUser();
		if (1 == user.getIsAdmin()) {
			return mapper.queryAllPermissions();
		}
		return mapper.queryPermissions(username);
	}

}
