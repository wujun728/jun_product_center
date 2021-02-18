package com.zhiyu.flybbs.mapper;

import com.zhiyu.flybbs.domain.Thread;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wujun
 */
@Repository
public interface ThreadReadMapper {

    Thread queryById(@Param("id") int id);

    List<Thread> queryAll();
}
