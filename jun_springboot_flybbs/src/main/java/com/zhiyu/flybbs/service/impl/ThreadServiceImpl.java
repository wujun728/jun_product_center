package com.zhiyu.flybbs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhiyu.flybbs.domain.PageInfo;
import com.zhiyu.flybbs.domain.Thread;
import com.zhiyu.flybbs.mapper.ThreadReadMapper;
import com.zhiyu.flybbs.mapper.ThreadWriteMapper;
import com.zhiyu.flybbs.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadServiceImpl implements ThreadService {
    @Autowired
    private ThreadWriteMapper threadWriteMapper;

    @Autowired
    private ThreadReadMapper threadReadMapper;

    @Override
    public int insert(Thread thread) {
        return threadWriteMapper.insert(thread);
    }

    @Override
    public int update(Thread thread) {
        return threadWriteMapper.update(thread);
    }

    @Override
    public Thread queryById(int id) {
        return threadReadMapper.queryById(id);
    }

    @Override
    public Map<String, Object> queryPages(PageInfo pageInfo) {
        Map<String, Object> result = new HashMap<>();
        Page<Thread> page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Thread> threadList = threadReadMapper.queryAll();
        pageInfo.setTotalCount(page.getTotal());
        result.put("threadList", threadList);
        result.put("pageInfo", pageInfo);
        return result;
    }
}
