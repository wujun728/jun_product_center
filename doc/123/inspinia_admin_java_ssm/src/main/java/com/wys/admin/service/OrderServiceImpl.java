package com.wys.admin.service;

import com.wys.admin.mapper.OrderMapper;
import com.wys.admin.pojo.Order;
import com.wys.admin.pojo.OrderExample;
import com.wys.admin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/7/17.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public List list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id");
        List<Order> result = orderMapper.selectByExample(example);
        setOrders(result);
        return result;
    }

    private void setOrders(List<Order> os) {
        for (Order o: os) {
            setOrder(o);
        }
    }

    private void setOrder(Order order) {
        User user = userService.get(order.getUid());
        order.setUser(user);
        orderItemService.fill(order);
    }
}
