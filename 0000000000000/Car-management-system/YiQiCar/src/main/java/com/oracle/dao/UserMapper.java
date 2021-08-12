package com.oracle.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.oracle.vo.User;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);
    
    User selectByNameAndPwd(@Param("name") String name,@Param("password") String password);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
   
}