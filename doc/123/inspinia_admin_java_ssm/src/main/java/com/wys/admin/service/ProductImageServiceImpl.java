package com.wys.admin.service;

import com.wys.admin.mapper.ProductImageMapper;
import com.wys.admin.pojo.Product;
import com.wys.admin.pojo.ProductImage;
import com.wys.admin.pojo.ProductImageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/6/14.
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage pi) {
        productImageMapper.updateByPrimaryKeySelective(pi);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }
}
