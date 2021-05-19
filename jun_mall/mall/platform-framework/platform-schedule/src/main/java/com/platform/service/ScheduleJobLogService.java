package com.platform.service;

import com.platform.entity.ScheduleJobLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 *
 * @author lipengjun
 * @date 2017年11月20日 下午2:16:40
 */
public interface ScheduleJobLogService {

    /**
     * 根据ID，查询定时任务日志
     *
     * @param jobId jobId
     * @return entity
     */
    ScheduleJobLogEntity queryObject(Long jobId);

    /**
     * 查询定时任务日志列表
     *
     * @param map map
     * @return list
     */
    List<ScheduleJobLogEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     *
     * @param map map
     * @return int
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务日志
     *
     * @param log log
     */
    void save(ScheduleJobLogEntity log);

}
