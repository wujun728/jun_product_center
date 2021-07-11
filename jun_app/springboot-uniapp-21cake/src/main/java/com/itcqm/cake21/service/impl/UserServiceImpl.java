package com.itcqm.cake21.service.impl;

import com.itcqm.cake21.entity.User;
import com.itcqm.cake21.mapper.UserMapper;
import com.itcqm.cake21.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CQM
 * @since 2020-09-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
