package com.wys.admin.mapper;

import com.wys.admin.pojo.User;
import com.wys.admin.pojo.UserExample;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    boolean insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}