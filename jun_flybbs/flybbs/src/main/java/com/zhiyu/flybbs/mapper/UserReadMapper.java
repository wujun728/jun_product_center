package com.zhiyu.flybbs.mapper;

import com.zhiyu.flybbs.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserReadMapper {

    User queryUserById(@Param("id") int id);

    List<User> queryUserByParams(@Param("params") Map<String, Object> params);
}
