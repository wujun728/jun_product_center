package com.wys.admin.service;

import com.wys.admin.mapper.CategoryMapper;
import com.wys.admin.pojo.Category;
import com.wys.admin.pojo.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/4/13.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public int add(Category category) {
        return  categoryMapper.insert(category);
    }

    @Override
    public boolean delete(int id) {
        return categoryMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean update(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category) > 0;
    }
}
