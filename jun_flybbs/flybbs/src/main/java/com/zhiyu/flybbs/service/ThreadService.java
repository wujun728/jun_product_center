package com.zhiyu.flybbs.service;

import com.zhiyu.flybbs.domain.PageInfo;
import com.zhiyu.flybbs.domain.Thread;

import java.util.Map;

public interface ThreadService {

    int insert(Thread thread);

    int update(Thread thread);

    Thread queryById(int id);

    Map<String, Object> queryPages(PageInfo pageInfo);
}
