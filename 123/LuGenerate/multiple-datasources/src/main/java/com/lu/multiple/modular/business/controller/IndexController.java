package com.lu.multiple.modular.business.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.lu.multiple.modular.business.mapper.ArticleMapper;
import com.lu.multiple.modular.business.mapper2.ArticleMapper2;
import com.lu.multiple.modular.business.model.Article;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lu.multiple.config.SpringContextHolder;

import java.util.Map;

/**
 * @program LuGenerate
 * @description:
 * @author: zhanglu
 * @create: 2019-12-11 17:14:00
 */
@RestController
public class IndexController {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleMapper2 articleMapper2;

    @RequestMapping("/g1")
    public Object get01() {
        Article article = articleMapper.selectByPrimaryKey(97);
        return article;
    }

    @RequestMapping("/g2")
    public Object get02() {
        com.lu.multiple.modular.business.model2.Article article = articleMapper2.selectByPrimaryKey(97);
        return article;
    }

}
