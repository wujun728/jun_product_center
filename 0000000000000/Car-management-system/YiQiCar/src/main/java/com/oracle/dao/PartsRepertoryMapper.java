package com.oracle.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.vo.PartsRepertory;
@Repository
public interface PartsRepertoryMapper {
    int deleteByPrimaryKey(Integer partsrepid);

    int insert(PartsRepertory record);

    int insertSelective(PartsRepertory record);

    PartsRepertory selectByPrimaryKey(Integer partsrepid);

    int updateByPrimaryKeySelective(PartsRepertory record);

    int updateByPrimaryKey(PartsRepertory record);
    
    List<Map<String,Object>> getAll();
    
    
    
    List<Map<String,Object>> getCode(@Param("type") String type);
    
    List<Map<String,Object>> getName();
    
    List<Map<String,Object>> getEmps();
    
    List<Integer> getPartsid();
    
    void updateCount1(@Param("partsreqcount") Integer partsidreqcount,@Param("partsid")Integer partsid);
    
    void updateCount2(@Param("partsreqcount") Integer partsidreqcount,@Param("partsid")Integer partsid);
    
    void insertBill(@Param("partsid")Integer partsid,@Param("partsreqcount") Integer partsidreqcount);
    
    
    List<Map<String,Object>> getReps(Map<String,Object> map);
    
    List<Map<String,Object>> getPartsByLike(Map<String,Object> map);
    
    Map<String,Object> getSelected(Integer partsid);
    
    
}