package com.wys.admin.service;

import com.wys.admin.pojo.Order;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/7/17.
 */
public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add();
    void delete();
    void update();
    Order get(int id);
    List list();
}
