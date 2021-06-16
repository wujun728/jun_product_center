package com.lu.single.modular.business.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lu.single.modular.business.model.Article;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 * 文章列表 服务类
 * </p>
 *
 * @author lu
 * @since 2019-02-07
 */
public interface IArticleService extends IService<Article> {

    Object get01();

    Object get02();

    void region();

    void map(String s1, String s2) throws Exception;

    /**
     * 搜索生成文件
     * @param s2
     */
    void t(String s2);

    /**
     * 圆形搜索生成文件
     * @param s2
     */
    void t2(String s2);

    /**
     * 根据地点匹配经纬度
     */
    void getRanger() throws Exception;

    List<Article> articleList();

    Integer testSave() throws Exception;
}
