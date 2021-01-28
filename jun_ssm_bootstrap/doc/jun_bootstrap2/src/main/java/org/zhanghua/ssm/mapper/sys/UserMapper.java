package org.zhanghua.ssm.mapper.sys;

import org.zhanghua.ssm.common.mapper.CrudMapper;
import org.zhanghua.ssm.entity.sys.User;

public interface UserMapper extends CrudMapper<User, String> {

	/**
	 * 根据用户名查询用户对象
	 * @param username 用户名
	 * @return 用户对象
	 */
	public User getByUsername(String username);

	public void deleteUserRole(User user);

	public void saveUserRole(User user);

	public void resetPwd(User user);
}
