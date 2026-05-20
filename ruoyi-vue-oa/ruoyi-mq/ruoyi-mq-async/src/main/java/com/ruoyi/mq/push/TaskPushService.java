package com.ruoyi.mq.push;

import com.ruoyi.mq.product.IAsyncTaskProduction;
import com.ruoyi.mq.config.MqConfig;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.factory.MqAsyncServerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> 异步任务推送服务 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Component
public class TaskPushService {

    @Resource
    private MqConfig mqConfig;

    /**
     * 异步任务日志列表
     */
    private final ThreadLocal<List<AsyncLog>> th = ThreadLocal.withInitial(ArrayList::new);

    /**
     * 提交异步任务
     *
     * @param asyncLog      异步日志记录
     * @param isBatchSubmit 是否批量提交
     */
    public void submit(AsyncLog asyncLog, boolean isBatchSubmit) {
        if (!isBatchSubmit) {
            submit(asyncLog);
        } else {
            th.get().add(asyncLog);
        }
    }

    /**
     * 批量提交
     */
    public void batchSubmit() {
        try {
            List<AsyncLog> asyncLogs = th.get();
            if (CollectionUtils.isNotEmpty(asyncLogs)) {
                submit(asyncLogs);
            }
        } finally {
            th.remove();
        }
    }

    /**
     * 执行提交异步任务
     *
     * @param asyncLog 异步日志记录
     */
    private void submit(AsyncLog asyncLog) {
        if (asyncLog == null) {
            return;
        }
        // 1.确认使用哪种消息中间件生产者
        IAsyncTaskProduction production = MqAsyncServerFactory.getInstance().getProduction(mqConfig.getAsyncType());
        try {
            production.produce(asyncLog);
        } catch (Exception e) {
            log.error("提交异步任务失败：{}", e);
            throw new RuntimeException("提交异步任务失败！");
        }
    }

    /**
     * 执行批量提交
     *
     * @param asyncLogs 批量异步日志记录
     */
    private void submit(List<AsyncLog> asyncLogs) {
        if (CollectionUtils.isEmpty(asyncLogs)) {
            return;
        }
        // 1.确认使用哪种消息中间件生产者
        IAsyncTaskProduction production = MqAsyncServerFactory.getInstance().getProduction(mqConfig.getAsyncType());
        try {
            production.batchProduct(asyncLogs);
        } catch (Exception e) {
            log.error("执行提交异步任务失败：{}", e);
            throw new RuntimeException("执行提交异步任务失败！");
        }
    }
}
