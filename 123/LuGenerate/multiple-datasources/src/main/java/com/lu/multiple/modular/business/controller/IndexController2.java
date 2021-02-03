package com.lu.multiple.modular.business.controller;

import com.lu.multiple.modular.business.mapper.ArticleMapper;
import com.lu.multiple.modular.business.model.Article;
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
@RequestMapping("/test")
public class IndexController2 {

    @Autowired
    private ArticleMapper articleMapper;

    @RequestMapping("/g1")
    public Object get01() {
        Article article = articleMapper.selectByPrimaryKey(97);
        return article;
    }

    @RequestMapping("/g2")
    public Object get02() throws Exception {
        Article article = articleMapper.selectByPrimaryKey(97);
        return article;
    }

}
