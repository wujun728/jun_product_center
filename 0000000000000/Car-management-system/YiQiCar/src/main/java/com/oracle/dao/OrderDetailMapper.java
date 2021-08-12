package com.oracle.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.vo.OrderDetail;

@Repository
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer orderdetailid);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer orderdetailid);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
    
    int getOrderId(String ordercode);
    
    void updateNum(@Param("partsid") Integer partsid,@Param("partsreqcount") Integer count);
    
    List<Map<String,Object>> getChecks(Map<String,Object> map);
    
    
    
}