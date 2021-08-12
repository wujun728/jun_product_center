package com.shuogesha.common.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
/**
 * ehcache工具类
 * @author zhaohaiyuan
 *
 */
@Configuration
public class EhCacheUtils {
    private static CacheManager manager;

    @Autowired
    public void setManager(CacheManager manager) {
        EhCacheUtils.manager = manager;
    }

    public static Object get(String cacheName, Object key) {
        return cache(cacheName).get(key).getObjectValue();
    }

    public static void put(String cacheName, Object key, Object value, Integer ttl, Integer tti) {
        Element e = new Element(key, value);
        //不设置则使用xml配置
        if (ttl != null)
            e.setTimeToLive(ttl);
        if (tti != null)
            e.setTimeToIdle(tti);
        cache(cacheName).put(e);
    }

    public static boolean remove(String cacheName, Object key) {
        return cache(cacheName).remove(key);
    }

    public static void removeAll(String cacheName) {
        cache(cacheName).removeAll();
    }

    public static Cache cache(String cacheName) {
        net.sf.ehcache.CacheManager cacheManager = ((EhCacheCacheManager) manager).getCacheManager();
        if (!cacheManager.cacheExists(cacheName))
            cacheManager.addCache(cacheName);
        return cacheManager.getCache(cacheName);
    }
}