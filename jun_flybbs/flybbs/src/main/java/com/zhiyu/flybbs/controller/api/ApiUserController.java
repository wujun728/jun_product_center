package com.zhiyu.flybbs.controller.api;

import com.zhiyu.flybbs.domain.CommonRes;
import com.zhiyu.flybbs.domain.User;
import com.zhiyu.flybbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhiyu
 */
@RestController
@RequestMapping("api/user/")
public class ApiUserController {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/signIn")
    public CommonRes signIn(HttpServletRequest request, User user) {
        CommonRes res = userServiceImpl.register(user);
        if (res.isSuccess()) {
            userServiceImpl.login(request, user);
        }
        return userServiceImpl.register(user);
    }

    @RequestMapping("/logon")
    public CommonRes logon(HttpServletRequest request, User user) {
        return userServiceImpl.login(request, user);
    }

    @RequestMapping("/logout")
    public CommonRes logout(HttpServletRequest request) {
        return userServiceImpl.logout(request);
    }
}
