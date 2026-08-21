package com.ruoyi.mq.mapper;

import java.util.List;
import com.ruoyi.mq.domain.AsyncLog;

/**
 * 异步任务日志记录Mapper接口
 * 
 * @author wocurr.com
 */
public interface AsyncLogMapper {
    /**
     * 查询异步任务日志记录
     * 
     * @param id 异步任务日志记录主键
     * @return 异步任务日志记录
     */
    public AsyncLog selectAsyncLogById(String id);

    /**
     * 查询异步任务日志记录列表
     * 
     * @param asyncLog 异步任务日志记录
     * @return 异步任务日志记录集合
     */
    public List<AsyncLog> selectAsyncLogList(AsyncLog asyncLog);

    /**
     * 新增异步任务日志记录
     * 
     * @param asyncLog 异步任务日志记录
     * @return 结果
     */
    public int insertAsyncLog(AsyncLog asyncLog);

    /**
     * 修改异步任务日志记录
     * 
     * @param asyncLog 异步任务日志记录
     * @return 结果
     */
    public int updateAsyncLog(AsyncLog asyncLog);

    /**
     * 删除异步任务日志记录
     * 
     * @param id 异步任务日志记录主键
     * @return 结果
     */
    public int deleteAsyncLogById(String id);

    /**
     * 批量删除异步任务日志记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAsyncLogByIds(String[] ids);

    /**
     * 批量新增异步任务日志记录
     *
     * @param asyncLogs 异步任务日志记录
     * @return 结果
     */
    int batchInsertAsyncLog(List<AsyncLog> asyncLogs);

    /**
     * 更新重试次数
     *
     * @param id
     */
    void updateAsyncLogRetryTimeById(String id);
}
