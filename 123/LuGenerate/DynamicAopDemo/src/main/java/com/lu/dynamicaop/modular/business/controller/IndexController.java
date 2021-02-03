package com.lu.dynamicaop.modular.business.controller;

import com.lu.dynamicaop.modular.business.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IArticleService articleService;

    @RequestMapping("/g1")
    public Object get01(){
        return articleService.getById(96);
    }

    @RequestMapping("/g2")
    public Object get02(){
        return articleService.get02();
    }

}
