package com.zt.controller;

import com.zt.common.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    //获取系统菜单树
    @GetMapping("/menuTree.json")
    public JsonData menuTree(){
        return JsonData.success();
    }

    @GetMapping("/index.page")
    public String index(){
        return "index";
    }

    @GetMapping("/main.page")
    public String main(){
        return "main";
    }
}
