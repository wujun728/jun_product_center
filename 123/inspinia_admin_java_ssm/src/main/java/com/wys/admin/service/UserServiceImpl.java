package com.wys.admin.service;

import com.wys.admin.mapper.UserMapper;
import com.wys.admin.pojo.User;
import com.wys.admin.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/7/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean delete(Integer id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<User> list() {
        UserExample example = new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
    }
}
