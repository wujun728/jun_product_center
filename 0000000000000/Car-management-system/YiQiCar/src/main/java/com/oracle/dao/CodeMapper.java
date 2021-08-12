package com.oracle.dao;

import org.springframework.stereotype.Repository;

import com.oracle.vo.Code;

@Repository
public interface CodeMapper {
    int deleteByPrimaryKey(String code);

    int insert(Code record);

    int insertSelective(Code record);

    Code selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKey(Code record);
}