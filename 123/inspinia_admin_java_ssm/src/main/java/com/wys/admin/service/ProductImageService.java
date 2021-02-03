package com.wys.admin.service;

import com.wys.admin.pojo.Product;
import com.wys.admin.pojo.ProductImage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/6/14.
 */
public interface  ProductImageService {
    String type_single = "type_single";
    String type_detail = "type_detail";

    void add(ProductImage productImage);
    void delete(int id);
    void update(ProductImage pi);
    ProductImage get(int id);
    List list(int pid,String type);
}
