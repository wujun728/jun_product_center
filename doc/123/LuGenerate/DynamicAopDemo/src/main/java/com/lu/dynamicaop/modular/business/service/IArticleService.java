package com.lu.dynamicaop.modular.business.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lu.dynamicaop.modular.business.model.Article;

/**
 * <p>
 * </p>
 *
 * @author lu
 * @since 2019-02-07
 */
public interface IArticleService extends IService<Article> {

    Object get01();

    Object get02();

}
