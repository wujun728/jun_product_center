package com.wys.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wys.admin.pojo.User;
import com.wys.admin.service.UserService;
import com.wys.util.bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/7/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/list")
    public String list(Model model, Page page) {
        page.setCount(5);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> list = userService.list();
        int total = (int) new PageInfo<>(list).getTotal();
        model.addAttribute("ul",list);
        return "/admin/user/list";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(User user) {
        boolean isSuccess =  userService.add(user);
        return "redirect: /admin/user/list";
    }

    @RequestMapping(value = "/delete")
    public String delete(int id) {
        boolean isSuccess = userService.delete(id);
        return "redirect: /admin/user/list";
    }

}
