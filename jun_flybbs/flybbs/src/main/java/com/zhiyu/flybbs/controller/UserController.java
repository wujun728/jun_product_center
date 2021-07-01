package com.zhiyu.flybbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhiyu
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "user/reg";
    }

    @RequestMapping("/set")
    public String set() {
        return "user/set";
    }

    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }
}
