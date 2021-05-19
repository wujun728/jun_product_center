package com.platform.service;

import com.platform.entity.ScheduleJobEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author lipengjun
 * @date 2017年11月20日 下午2:16:40
 */
public interface ScheduleJobService {

    /**
     * 根据ID，查询定时任务
     *
     * @param jobId jobId
     * @return entity
     */
    ScheduleJobEntity queryObject(Long jobId);

    /**
     * 查询定时任务列表
     *
     * @param map map
     * @return list
     */
    List<ScheduleJobEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     *
     * @param map map
     * @return int
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务
     *
     * @param scheduleJob scheduleJob
     */
    void save(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     *
     * @param scheduleJob scheduleJob
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     *
     * @param jobIds jobIds
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     *
     * @param jobIds jobIds
     * @param status 状态
     * @return int
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     *
     * @param jobIds jobIds
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     *
     * @param jobIds jobIds
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     *
     * @param jobIds jobIds
     */
    void resume(Long[] jobIds);
}
