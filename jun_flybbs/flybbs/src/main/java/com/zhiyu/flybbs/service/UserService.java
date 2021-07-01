package com.zhiyu.flybbs.service;

import com.zhiyu.flybbs.domain.CommonRes;
import com.zhiyu.flybbs.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author zhiyu
 */
public interface UserService {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    User queryUserById(int id);

    List<User> queryUserByParams(Map<String, Object> params);

    User queryUserByEmail(String email);

    int insertUser(User user);

    CommonRes register(User user);

    CommonRes login(HttpServletRequest request, User user);

    CommonRes logout(HttpServletRequest request);

    int updateUser(User user);
}
