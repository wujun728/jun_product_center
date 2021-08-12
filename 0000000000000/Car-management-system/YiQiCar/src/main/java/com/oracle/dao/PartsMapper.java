package com.oracle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.vo.Parts;

@Repository
public interface PartsMapper {
    int deleteByPrimaryKey(Integer partsid);

    void insert(Parts record);

    int insertSelective(Parts record);

    Parts selectByPrimaryKey(Integer partsid);
    
    List<Parts> getAll();

    int updateByPrimaryKeySelective(Parts record);

    void updateByPrimaryKey(Parts record);
    
    List<Parts> getPartsByLiike(@Param("PartsName") String partsname);
}