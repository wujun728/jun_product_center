package com.wys.admin.service;

import com.wys.admin.pojo.Property;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/5/3.
 */
public interface PropertyService {
    boolean add();
    boolean delete();
    boolean update();
    Property get();
    List list(int cid);
}
