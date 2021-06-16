package com.nbclass.mapper;

import com.nbclass.model.User;
import com.nbclass.util.MyMapper;

import java.util.List;
import java.util.Map;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
public interface UserMapper extends MyMapper<User> {
    /**
     * 根据user参数查询用户列表
     * @param user
     * @return list
     */
    List<User> selectUsers(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return user
     */
    User selectByUsername(String username);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return user
     */
    User selectByUserId(String userId);

    /**
     * 更新最后登录时间
     * @param user
     */
    void updateLastLoginTime(User user);

    /**
     * 根据用户id更新用户信息
     * @param user
     * @return int
     */
    int updateByUserId(User user);

    /**
     * 根据参数批量修改用户状态
     * @param params
     * @return int
     */
    int updateStatusBatch(Map<String, Object> params);

    /**
     * 根据角色id查询用户list
     * @param roleId
     * @return list
     */
    List<User> findByRoleId(String roleId);

    /**
     * 根据角色id查询用户list
     * @param roleIds
     * @return list
     */
    List<User> findByRoleIds(List<String> roleIds);
}
