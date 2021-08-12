package com.oracle.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.vo.PartsRepBill;

@Repository
public interface PartsRepBillMapper {
	
    int deleteByPrimaryKey(Integer billid);

    int insert(PartsRepBill record);

    int insertSelective(PartsRepBill record);

    PartsRepBill selectByPrimaryKey(Integer billid);

    int updateByPrimaryKeySelective(PartsRepBill record);

    int updateByPrimaryKey(PartsRepBill record);
    
    List<Map<String,Object>> getAll();
    
    List<Map<String,Object>> getCode(@Param("type") String type);
    
    List<Map<String,Object>> getBillsByLike(@Param("map") Map<String,Object> map);
}