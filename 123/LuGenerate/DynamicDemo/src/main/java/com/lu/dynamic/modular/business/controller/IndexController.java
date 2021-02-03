package com.lu.dynamic.modular.business.controller;

import com.lu.dynamic.modular.business.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 17:14:00
 */
@RestController
public class IndexController {

    @Autowired
    private IArticleService articleService;

    @RequestMapping("/g1")
    public Object get01(){
        return articleService.get01();
    }

    @RequestMapping("/g2")
    public Object get02(){
        return articleService.get02();
    }

}
