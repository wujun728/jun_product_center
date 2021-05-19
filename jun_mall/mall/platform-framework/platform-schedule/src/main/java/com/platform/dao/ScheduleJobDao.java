package com.platform.dao;

import com.platform.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author lipengjun
 * @date 2017年11月20日 下午2:16:40
 */
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {

    /**
     * 批量更新状态
     * @param map map
     * @return int
     */
    int updateBatch(Map<String, Object> map);
}
