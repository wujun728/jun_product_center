package com.wys.admin.service;

import com.wys.admin.mapper.CategoryMapper;
import com.wys.admin.mapper.PropertyMapper;
import com.wys.admin.pojo.Property;
import com.wys.admin.pojo.PropertyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/5/3.
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper mapper;

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public Property get() {
        return null;
    }

    @Override
    public List list(int cid) {
        PropertyExample example = new PropertyExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        return mapper.selectByExample(example);
    }
}
