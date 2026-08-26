package com.ruoyi.framework.web.service;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 部门缓存服务
 *
 * @author wocurr.com
 */
@Slf4j
@Component
public class DeptCacheService {

    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private RedisCache redisCache;

    private static final String DEPT_TREE_KEY_PREFIX = "dept:tree";

    /**
     * 初始化部门树
     */
    @PostConstruct
    public void init(){
        long startTime = System.currentTimeMillis();
        //初始化部门树到缓存中
        SysDept queryParam = new SysDept();
        List<SysDept> sysDepts = sysDeptService.buildDeptTreeCache(queryParam);
        if (CollectionUtils.isEmpty(sysDepts)) {
            return;
        }
        redisCache.setCacheObject(DEPT_TREE_KEY_PREFIX, sysDepts);
        long endTime = System.currentTimeMillis();
        if (log.isDebugEnabled()) {
            log.debug("部门树初始化完成，缓存key: {}, 耗时：{}", DEPT_TREE_KEY_PREFIX, endTime - startTime);
        }
    }
}
