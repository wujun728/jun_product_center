package com.wys.admin.service;

import com.wys.admin.pojo.Category;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/3/8.
 */
public interface CategoryService {
    List<Category> list();
    int add(Category category);
    boolean delete(int id);
    Category get(int id);
    boolean update(Category category);
}
