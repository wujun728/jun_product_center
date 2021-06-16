package com.lu.dynamic.modular.business.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.lu.dynamic.modular.business.model.Article;

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

}
