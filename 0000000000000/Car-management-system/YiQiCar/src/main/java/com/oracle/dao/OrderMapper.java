package com.oracle.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.vo.Order;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Map<String,Object>> getAllOrder();
    
    List<Map<String,Object>> getOrders(Map<String,Object> map);
    
    Map<String,Object> getOrderById(Integer orderid);
    
    List<String> getOrderCode();
    
    List<Map<String,Object>> getAllBills();
    
    void updateFlag(@Param("orderid") Integer orderid);
    
    void updateFlag1(@Param("orderid") Integer orderid);
    
    List<Map<String,Object>> getBillDetail(Integer orderid);
}