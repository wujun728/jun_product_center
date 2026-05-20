package com.ruoyi.mq.execute;

import com.ruoyi.mq.domain.AsyncLog;

/**
 * <p> 异步处理器 </p>
 *
 * @Author wocurr.com
 */
public interface IAsyncHandler {

    /**
     * 执行异步方法
     *
     * @param asyncLog 异步日志
     */
    void doAsync(AsyncLog asyncLog);
}
