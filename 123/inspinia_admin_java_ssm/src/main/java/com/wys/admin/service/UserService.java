package com.wys.admin.service;

import com.wys.admin.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/7/16.
 */
public interface UserService {
     User get(Integer id);
     boolean add(User user);
     boolean delete(Integer id);
     List<User> list();
}
