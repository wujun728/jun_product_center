package com.zhiyu.flybbs.service.impl;

import com.zhiyu.flybbs.SessionConstant;
import com.zhiyu.flybbs.domain.CommonRes;
import com.zhiyu.flybbs.domain.User;
import com.zhiyu.flybbs.mapper.UserReadMapper;
import com.zhiyu.flybbs.mapper.UserWriteMapper;
import com.zhiyu.flybbs.service.UserService;
import com.zhiyu.flybbs.utils.DateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserReadMapper userReadMapper;
    @Autowired
    private UserWriteMapper userWriteMapper;

    @Override
    public User queryUserById(int id) {
        return userReadMapper.queryUserById(id);
    }

    @Override
    public int insertUser(User user) {
        return userWriteMapper.insertUser(user);
    }

    @Override
    public List<User> queryUserByParams(Map<String, Object> params) {
        return userReadMapper.queryUserByParams(params);
    }

    @Override
    public User queryUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        List<User> userList = userReadMapper.queryUserByParams(params);
        return CollectionUtils.isNotEmpty(userList) ? userList.get(0) : null;
    }

    @Override
    public CommonRes register(User user) {
        if (StringUtils.isEmpty(user.getEmail())) {
            return new CommonRes(false, "邮箱不得为空");
        }
        User exist = queryUserByEmail(user.getEmail());
        if (exist != null) {
            return new CommonRes(false, "该邮箱已被注册");
        }
        if (StringUtils.isEmpty(user.getUserName())) {
            return new CommonRes(false, "昵称不得为空");
        }
        if (StringUtils.isEmpty(user.getLoginPwd())) {
            return new CommonRes(false, "密码不得为空");
        } else {
            user.setLoginPwd(DigestUtils.md5Hex(user.getLoginPwd()));
        }
        user.setGmtCreate(DateUtil.getCurrentTime());
        int result = insertUser(user);
        return CommonRes.build(result, "注册成功", "注册失败", user);
    }

    @Override
    public CommonRes login(HttpServletRequest request, User user) {
        User dbUser = queryUserByEmail(user.getEmail());
        if (dbUser != null && dbUser.getLoginPwd().equals(DigestUtils.md5Hex(user.getLoginPwd()))) {
            request.getSession().setAttribute(SessionConstant.USER, dbUser);
            return new CommonRes(true, "登陆成功");
        } else {
            return new CommonRes(false, "用户名或密码错误");
        }
    }

    @Override
    public CommonRes logout(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionConstant.USER);
        return new CommonRes(true, "注销成功");
    }

    @Override
    public int updateUser(User user) {
        user.setGmtModified(DateUtil.getCurrentTime());
        return userWriteMapper.updateUser(user);
    }
}
