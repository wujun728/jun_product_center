package com.ruoyi.mq.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.mq.domain.AsyncLog;

import java.util.List;

/**
 * <p> 异步任务生产者接口 </p>
 *
 * @Author wocurr.com
 */
public interface IAsyncTaskProduction {

    /**
     * 生产消息
     *
     * @param asyncLog 异步任务
     */
    void produce(AsyncLog asyncLog) throws JsonProcessingException;

    /**
     * 批量生产消息
     *
     * @param asyncLogs 异步任务列表
     */
    void batchProduct(List<AsyncLog> asyncLogs) throws JsonProcessingException;
}
