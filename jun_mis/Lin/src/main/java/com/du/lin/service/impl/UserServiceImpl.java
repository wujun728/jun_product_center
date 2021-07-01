package com.du.lin.service.impl;

import java.util.List;

import com.du.lin.bean.ShiroUser;
import com.du.lin.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.du.lin.bean.User;
import com.du.lin.constant.Constant;
import com.du.lin.dao.UserMapper;
import com.du.lin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;
	@Autowired
	private BeanUtil beanUtil;
	@Autowired
	private LinTools linTools;
	@Autowired
	private JqgridUtil jqgridUtil;

	@Override
	public List<User> getAllUser() {

		return beanUtil.toUserList(mapper.selectList(null));
	}

	@Override
	public String addUser(String username, String avator, String dept, String role) {

		ShiroUser user = new ShiroUser();
		
		user.setUsername(username);
		user.setPassword(MD5Util.encrypt("111111"));
		user.setAvator(avator);
		user.setDeptid(Integer.parseInt(dept));
		user.setRoleid(Integer.parseInt(role));
		user.setSalt(linTools.getSalt());
		return mapper.insert(user) + "";
	}

	@Override
	public String resetPassword(int id) {
		ShiroUser user = new ShiroUser();
		user.setId(id);
		user.setPassword(MD5Util.encrypt("111111"));
		int result = mapper.updateById(user);
		return result + "";
	}

	@Override
	public String deleteUser(int id) {
		int userresult = mapper.deleteById(id);
		return userresult + "";
	}

	@Override
	public String modifyInfo(String newId, String newDeptId, String newRoleId) {

		ShiroUser user = new ShiroUser();
		user.setId(Integer.parseInt(newId));
		user.setDeptid(Integer.parseInt(newDeptId));
		user.setRoleid(Integer.parseInt(newRoleId));
		int result = mapper.updateById(user);
		return result + "";
	}

	@Override
	public String changePassword(String oldPassword , String newPassword) {
		ShiroUser user = mapper.selectById(Userinfo.getUserid());

		if (!user.getPassword().equals(MD5Util.encrypt(oldPassword))) {
			return Constant.ERROR_SET_PASSWORD_NO_MATCH;
		}

		user = new ShiroUser();
		user.setId(Userinfo.getUser().getId());
		user.setPassword(MD5Util.encrypt(newPassword));
		int result = mapper.updateById(user);
		return result+"";
	}

	@Override
	public String getAllUserJson(int page , int count) {
		List<User> all = getAllUser();
		int toIndex = count * page;
		if (all.size() < toIndex) {
			toIndex = all.size();
		}
		List<User> list = all.subList(count * (page - 1), toIndex);
		return jqgridUtil.getJson(list, page + "", all.size() , count);
	}

}
