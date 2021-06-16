package com.wys.admin.mapper;

import com.wys.admin.pojo.Product;
import com.wys.admin.pojo.ProductExample;
import java.util.List;

public interface ProductMapper {
    boolean deleteByPrimaryKey(Integer id);

    boolean insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}