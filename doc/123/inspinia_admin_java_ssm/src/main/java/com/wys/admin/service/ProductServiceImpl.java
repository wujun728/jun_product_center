package com.wys.admin.service;

import com.wys.admin.mapper.ProductMapper;
import com.wys.admin.pojo.Category;
import com.wys.admin.pojo.Product;
import com.wys.admin.pojo.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Created by wangyushuai@fang.com on 2018/6/14.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;

    @Override
    public boolean add(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public boolean delete(int id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setCategory(p);
        return p;
    }

    public void setCategory(Product p) {
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }

    public void setCategoryList(List<Product> ps) {
        for (Product p : ps) {
            setCategory(p);
        }
    }

    @Override
    public List list(Integer cid) {
        ProductExample productExample = new ProductExample();
        if (cid != null && cid > 0) {
            productExample.createCriteria().andCidEqualTo(cid);
        }
        productExample.setOrderByClause("id desc");
        List result = productMapper.selectByExample(productExample);
        return result;
    }
}
