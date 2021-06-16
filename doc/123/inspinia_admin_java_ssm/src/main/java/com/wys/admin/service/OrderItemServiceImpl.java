package com.wys.admin.service;

import com.wys.admin.mapper.OrderItemMapper;
import com.wys.admin.pojo.Order;
import com.wys.admin.pojo.OrderItem;
import com.wys.admin.pojo.OrderItemExample;
import com.wys.admin.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangyushuai@fang.com on 2018/7/17.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem c) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(OrderItem c) {

    }

    @Override
    public OrderItem get(int id) {
        return null;
    }

    @Override
    public List list() {
        return null;
    }

    @Override
    public void fill(List<Order> os) {
        for (Order o : os) {
            fill(o);
        }
    }

    @Override
    public void fill(Order order) {
        OrderItemExample example  = new OrderItemExample();
        example.createCriteria().andOidEqualTo(order.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);

        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(ois);
    }

    public void setProduct(List<OrderItem> ois) {
        for (OrderItem oi : ois) {
            setProduct(oi);
        }
    }

    private void setProduct(OrderItem oi) {
        Product product = productService.get(oi.getPid());
        oi.setProduct(product);
    }

}
