package com.du.lin.service;

import java.util.List;

import com.du.lin.bean.User;

public interface UserService {
	public String getAllUserJson(int page , int count);
	
	public List<User> getAllUser();
	
	public String addUser(String username , String avator , String dept , String role);
	
	public String resetPassword(int id);
	
	public String deleteUser(int id);
	
	public String modifyInfo(String newId , String newDeptId , String newRoleId);
	
	public String changePassword(String oldPassword , String newPassword);
}
