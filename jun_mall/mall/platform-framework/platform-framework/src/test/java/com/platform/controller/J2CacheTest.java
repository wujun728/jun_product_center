/*
 * 创建时间：2018/07/10 16:02
 * 项目名称:platform
 * 类名称:J2CacheTest.java
 * 包名称:com.platform.controller
 *
 * 修改履历:
 *          日期              修正者        主要内容
 *
 *
 * Copyright (c) 2016-2017 皖通科技
 */
package com.platform.controller;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;

/**
 * 名称：J2CacheTest <br>
 * 描述：<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class J2CacheTest {
    public static void main(String[] args) {
        CacheChannel cache = J2Cache.getChannel();

        //缓存操作
        cache.set("default", "1", "Hello J2Cache");
        System.out.println(cache.get("default", "1"));
        cache.evict("default", "1");
        System.out.println(cache.get("default", "1"));

        cache.close();
    }
}
