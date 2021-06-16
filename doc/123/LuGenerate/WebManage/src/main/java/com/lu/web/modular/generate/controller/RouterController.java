package com.lu.web.modular.generate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program LuGenerate
 * @description: 路由控制器
 * @author: zhanglu
 * @create: 2019-12-11 10:53:00
 */
@Controller
public class RouterController {

    @Autowired
    private Environment env;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("monitorUrl", env.getProperty("management.endpoints.web.base-path"));
        return "/modular/monitor/index.html";
    }

    @GetMapping("/generate/modular")
    public String modular(){
        return "/modular/generate/modular/index.html";
    }

    @GetMapping("/generate/project")
    public String project(){
        return "/modular/generate/project/index.html";
    }


}
