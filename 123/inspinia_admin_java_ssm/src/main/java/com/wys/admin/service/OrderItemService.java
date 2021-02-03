package com.wys.admin.service;

import com.wys.admin.pojo.Order;
import com.wys.admin.pojo.OrderItem;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/7/17.
 */
public interface OrderItemService {
    void add(OrderItem c);
    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List list();
    void fill(List<Order> os);
    void fill(Order order);
}
