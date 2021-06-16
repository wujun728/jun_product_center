package com.lu.single.modular.business.controller;

import com.lu.single.core.aop.LuBootLog;
import com.lu.single.modular.business.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
    @LuBootLog(descript = "master数据源")
    public Object get01(){
        return articleService.get01();
    }

    @RequestMapping("/g2")
    @LuBootLog(descript = "slave01数据源")
    public Object get02(){
        return articleService.get02();
    }

    @RequestMapping("/g3")
    @LuBootLog(descript = "测试缓存")
    public Object articleList(){
        return articleService.articleList();
    }

    @RequestMapping("/region")
    @ResponseBody
    public void region(HttpServletResponse response){
        articleService.region();
    }

    @RequestMapping("/map")
    @ResponseBody
    public void map(String s1, String s2) throws Exception {
        articleService.map(s1, s2);
    }


    @RequestMapping("/t")
    @ResponseBody
    public void t(String s2) throws Exception {
        articleService.t(s2);
    }

    @RequestMapping("/t2")
    @ResponseBody
    public void t2(String s2) throws Exception {
        articleService.t2(s2);
    }

    @RequestMapping("/getRanger")
    @ResponseBody
    public void getRanger() throws Exception {
        articleService.getRanger();
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save() throws Exception {
        articleService.testSave();
    }

}
