package com.jun.plugin.common.utils;

import com.jun.plugin.common.util.SpringContextUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * EhCache缓存操作工具
 * @author wujun
 * @date 2018/11/7
 */
public class EhCacheUtil {

    /**
     * 获取EhCacheManager管理对象
     */
    public static CacheManager getCacheManager(){
        return SpringContextUtil.getBean(CacheManager.class);
    }

    /**
     * 获取EhCache缓存对象
     */
    public static Cache getCache(String name){
        return getCacheManager().getCache(name);
    }

    /**
     * 获取字典缓存对象
     */
    public static Cache getDictCache(){
        return getCacheManager().getCache("dictionary");
    }

}
