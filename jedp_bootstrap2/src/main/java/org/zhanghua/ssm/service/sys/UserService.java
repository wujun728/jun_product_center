package org.zhanghua.ssm.service.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhanghua.ssm.common.Global;
import org.zhanghua.ssm.common.security.Digests;
import org.zhanghua.ssm.common.security.Encodes;
import org.zhanghua.ssm.common.service.CrudService;
import org.zhanghua.ssm.entity.sys.User;
import org.zhanghua.ssm.mapper.sys.UserMapper;

@Service
public class UserService extends CrudService<User, String> {

	@Autowired
	private UserMapper mapper;

	public User getByUsername(String username) {
		return mapper.getByUsername(username);
	}

	@Override
	public void save(User user) {
		entryptPassword(user);
		// 删除用户与角色的关联关系
		mapper.deleteUserRole(user);
		// 保存用户与角色的关联关系
		if (user.getRoles().size() > 0) {
			mapper.saveUserRole(user);
		}
		super.save(user);
	}

	@Override
	public void update(User user) {
		// 删除用户与角色的关联关系
		mapper.deleteUserRole(user);
		// 保存用户与角色的关联关系
		if (user.getRoles().size() > 0) {
			mapper.saveUserRole(user);
		}
		super.update(user);
	}

//	@Override
//	public void saveOrUpdate(User user) {
//		if (user.isNew()) {
//			entryptPassword(user);
//		}
//		// 删除用户与角色的关联关系
//		mapper.deleteUserRole(user);
//		// 保存用户与角色的关联关系
//		if (user.getRoles().size() > 0) {
//			mapper.saveUserRole(user);
//		}
//
//		super.saveOrUpdate(user);
//	}

	public void resetPwd(User user) {
		entryptPassword(user);
		mapper.resetPwd(user);
	}

	public boolean checkPassword(String plainPassword, User user) {
		return validatePassword(plainPassword, user);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(Global.SALT_INTERATIONS);
		user.setSalt(Encodes.encodeHex(salt));
		byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, Global.HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 * 校验密码
	 * 
	 * @param plainPassword
	 *            明文密码
	 * @param user
	 * @return
	 */
	private boolean validatePassword(String plainPassword, User user) {
		byte[] salt = Encodes.decodeHex(user.getSalt());
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, Global.HASH_INTERATIONS);
		return StringUtils.equals(user.getPassword(), Encodes.encodeHex(hashPassword));
	}

}
