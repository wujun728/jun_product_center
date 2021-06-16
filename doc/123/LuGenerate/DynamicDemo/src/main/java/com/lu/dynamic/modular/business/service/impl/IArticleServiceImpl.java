package com.lu.dynamic.modular.business.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lu.dynamic.modular.business.mapper.ArticleMapper;
import com.lu.dynamic.modular.business.model.Article;
import com.lu.dynamic.modular.business.service.IArticleService;
import org.springframework.stereotype.Service;

@Service
public class IArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @DS("master")
    @Override
    public Object get01() {
        return this.baseMapper.selectById(96);
    }

    @DS("slave01")
    @Override
    public Object get02() {
        return this.baseMapper.selectById(96);
    }
}
