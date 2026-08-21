package com.ruoyi.mq.consume;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mq.execute.IAsyncHandler;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.enums.AsyncStatusEnum;
import com.ruoyi.mq.service.IAsyncLogService;
import com.ruoyi.tools.lock.RedisLock;
import com.ruoyi.tools.utils.bean.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p> 消费服务 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Component
public class AsyncTaskConsumeService {

    @Resource
    private RedisLock redisLock;
    @Resource
    private RedisCache redisCache;
    @Autowired
    private IAsyncLogService asyncLogService;

    private static final String ASYNC_LOCK_KEY = "async:lock:";
    private static final String ASYNC_LOG_KEY = "async:log:";

    /**
     * 执行Spring容器bean的目标方法
     *
     * @param asyncLog
     */
    public void consume(AsyncLog asyncLog) {
        if (StringUtils.isBlank(asyncLog.getBeanName())) {
            log.error("未指定执行异步任务的bean!");
        } else {
            redisLock.doLock(ASYNC_LOCK_KEY + asyncLog.getId(), () -> {
                IAsyncHandler handler = null;
                try {
                    handler = ApplicationContextHelper.getBean(asyncLog.getBeanName(), IAsyncHandler.class);
                } catch (Exception ex) {
                    log.error("异步消息处理异常，无法获取注册的bean：{}", ex);
                }

                if (null != handler && redisCache.getCacheObject(ASYNC_LOG_KEY + asyncLog.getId()) == null) {
                    AsyncLog asyncLogById = asyncLogService.getAsyncLogById(asyncLog.getId());
                    if (asyncLogById == null || AsyncStatusEnum.SUCCESS.getCode().equals(asyncLogById.getStatus())) {
                        asyncLogService.saveAsyncLog(asyncLog);
                    }
                    try {
                        handler.doAsync(asyncLog);
                        asyncLog.setStatus(AsyncStatusEnum.SUCCESS.getCode());
                        asyncLogService.updateAsyncLog(asyncLog);
                        redisCache.setCacheObject(ASYNC_LOG_KEY + asyncLog.getId(), asyncLog, 5, TimeUnit.MINUTES);
                    } catch (Exception e) {
                        asyncLog.setStatus(AsyncStatusEnum.FAIL.getCode());
                        if (e.getMessage() != null) {
                            if (e.getMessage().length() > 500) {
                                asyncLog.setFailReason(e.getMessage().substring(0, 500));
                            } else {
                                asyncLog.setFailReason(e.getMessage());
                            }
                        }
                        asyncLogService.updateAsyncLog(asyncLog);
                        log.error("消费异步消息异常：msg={}, exception={}", JSONObject.toJSONString(asyncLog), e);
                    }
                }
            });
        }
    }

    /**
     * 执行自定义目标方法
     *
     * @param message 消息
     * @param function 自定义方法
     */
    public void consume(Message message, Function function) {
        MessageProperties messageProperties = message.getMessageProperties();
        String asyncLogId = messageProperties.getCorrelationId();
        redisLock.doLock(ASYNC_LOCK_KEY + asyncLogId, () -> {
            if (redisCache.getCacheObject(ASYNC_LOG_KEY + asyncLogId) == null) {
                AsyncLog asyncLog = asyncLogService.getAsyncLogById(asyncLogId);
                if (asyncLog == null) {
                    asyncLogService.addLogMessage(messageProperties.getReceivedExchange(), message.getMessageProperties().getReceivedRoutingKey(), message.getBody());
                }
                try {
                    function.execute();
                    asyncLog.setStatus(AsyncStatusEnum.SUCCESS.getCode());
                    asyncLogService.updateAsyncLog(asyncLog);
                    redisCache.setCacheObject(ASYNC_LOG_KEY + asyncLog.getId(), asyncLog, 5, TimeUnit.MINUTES);
                } catch (Exception e) {
                    asyncLog.setStatus(AsyncStatusEnum.FAIL.getCode());
                    if (e.getMessage() != null) {
                        if (e.getMessage().length() > 500) {
                            asyncLog.setFailReason(e.getMessage().substring(0, 500));
                        } else {
                            asyncLog.setFailReason(e.getMessage());
                        }
                    }
                    asyncLogService.updateAsyncLog(asyncLog);
                    log.error("消费异步消息异常：msg={}, exception={}", JSONObject.toJSONString(asyncLog), e);
                }
            }
        });
    }

    /**
     * 函数接口
     */
    @FunctionalInterface
    public interface Function {
        /**
         * 执行方法
         */
        void execute();
    }
}
