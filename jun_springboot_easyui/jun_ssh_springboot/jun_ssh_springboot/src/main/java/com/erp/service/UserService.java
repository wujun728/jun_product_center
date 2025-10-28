package com.erp.service;

import java.util.List;
import java.util.Map;

import com.erp.dto.UserRoleModel;
import com.erp.model.Users;
import com.erp.util.PageUtil;

/**
 * @Author Administrator
 * @CreateDate 2018/4/17 10:18
 */
public interface UserService {

//    Oauth2User findOauth2UsersByName(String username);
//    void  saveUser(Oauth2User u) throws Exception;
    
	boolean persistenceUsers(Map<String, List<Users>> map );

	List<Users> findAllUserList(Map<String, Object> map, PageUtil pageUtil);

	Long getCount(Map<String, Object> map , PageUtil pageUtil);

	List<UserRoleModel> findUsersRolesList(Integer userId );

	boolean saveUserRoles(Integer userId, String isCheckedIds );

	boolean persistenceUsers(Users u );

	boolean delUsers(Integer userId );



}
