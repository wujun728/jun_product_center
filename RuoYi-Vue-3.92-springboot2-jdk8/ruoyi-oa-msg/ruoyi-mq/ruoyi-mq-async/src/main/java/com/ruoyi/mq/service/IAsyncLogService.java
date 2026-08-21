package com.ruoyi.mq.service;

import java.util.List;
import com.ruoyi.mq.domain.AsyncLog;

/**
 * 异步任务日志记录Service接口
 * 
 * @author wocurr.com
 */
public interface IAsyncLogService {
    /**
     * 查询异步任务日志记录
     * 
     * @param id 异步任务日志记录主键
     * @return 异步任务日志记录
     */
    public AsyncLog getAsyncLogById(String id);

    /**
     * 查询异步任务日志记录列表
     * 
     * @param asyncLog 异步任务日志记录
     * @return 异步任务日志记录集合
     */
    public List<AsyncLog> listAsyncLog(AsyncLog asyncLog);

    /**
     * 新增异步任务日志记录
     * 
     * @param asyncLog 异步任务日志记录
     * @return 结果
     */
    public int saveAsyncLog(AsyncLog asyncLog);

    /**
     * 修改异步任务日志记录
     * 
     * @param asyncLog 异步任务日志记录
     * @return 结果
     */
    public int updateAsyncLog(AsyncLog asyncLog);

    /**
     * 批量删除异步任务日志记录
     * 
     * @param ids 需要删除的异步任务日志记录主键集合
     * @return 结果
     */
    public int deleteAsyncLogByIds(String[] ids);

    /**
     * 批量新增异步任务日志记录
     *
     * @param asyncLogs 异步任务日志记录
     * @return 结果
     */
    public int saveBatchAsyncLog(List<AsyncLog> asyncLogs);

    /**
     * 重试异步任务
     *
     * @param id
     * @return
     */
    void retry(String id);

    /**
     * 新增日志消息
     *
     * @param exchangeName
     * @param routingKey
     * @param message
     */
    String addLogMessage(String exchangeName, String routingKey, Object message);
}
