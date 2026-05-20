package com.ruoyi.mq.push;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.mq.api.ISyncPush;
import com.ruoyi.mq.config.MqConfig;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.enums.MqTypeEnum;
import com.ruoyi.mq.exception.AsyncLogParamCheckException;
import com.ruoyi.mq.managment.MessageQueueManagement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p> 同步任务推送接口实现 </p>
 *
 * @Author wocurr.com
 */
@Component
public class SyncTaskPushService implements ISyncPush {

    @Autowired
    private MqConfig mqConfig;
    @Autowired
    private MessageQueueManagement mqQueueManagement;
    @Autowired
    private TaskPushService taskPushService;

    @Override
    public void push(String consumerBeanName, String jsonContent, String queue) {
        push(consumerBeanName, jsonContent, queue, SecurityUtils.getUserId());
    }

    @Override
    public void push(String consumerBeanName, String jsonContent, String queue, String creatorId) {
        checkSubmitParams(consumerBeanName, jsonContent);
        AsyncLog asyncLog = new AsyncLog();
        asyncLog.setId(IdUtils.fastSimpleUUID());
        asyncLog.setBeanName(consumerBeanName);
        asyncLog.setMessageContent(jsonContent);
        asyncLog.setCreateId(creatorId);
        handleQueue(asyncLog, queue);
        taskPushService.submit(asyncLog, false);
    }

    /**
     * 处理传入的队列，并设置到AsyncLog中
     *
     * @param asyncLog
     * @param queue
     */
    private void handleQueue(AsyncLog asyncLog, String queue) {
        MqTypeEnum mqTypeEnum = MqTypeEnum.getEnum(mqConfig.getAsyncType());
        if (mqTypeEnum == null) {
            throw new AsyncLogParamCheckException("消息队列类型配置错误！");
        }
        if (mqTypeEnum == MqTypeEnum.RABBITMQ) {
            handleRabbitMqQueue(asyncLog, queue);
        }
    }

    /**
     * 处理rabbitmq 队列
     *
     * @param asyncLog
     * @param queue
     */
    private void handleRabbitMqQueue(AsyncLog asyncLog, String queue) {
        if (StringUtils.isBlank(queue)) {
            queue = mqQueueManagement.getDefaultQueueKey();
        }
        if (StringUtils.isBlank(mqQueueManagement.getExchangeMap().get(queue))) {
            throw new AsyncLogParamCheckException("queue对应的exchangeKey为空！");
        }
        if (StringUtils.isBlank(mqQueueManagement.getRouterMap().get(queue))) {
            throw new AsyncLogParamCheckException("queue对应的routingKey为空！");
        }
        //通过queue，拿到exchangeKey和routingKey
        asyncLog.setExchangeKey(mqQueueManagement.getExchangeMap().get(queue));
        //不设置特定的路由，则提交到默认的队列消费
        asyncLog.setRoutingKey(mqQueueManagement.getRouterMap().get(queue));
    }

    /**
     * 校验提交参数
     *
     * @param consumerBeanName
     * @param jsonContent
     */
    private void checkSubmitParams(String consumerBeanName, String jsonContent) {
        if (StringUtils.isBlank(consumerBeanName)) {
            throw new AsyncLogParamCheckException("consumerBeanName为空！");
        }
        if (StringUtils.isBlank(jsonContent)) {
            throw new AsyncLogParamCheckException("jsonContent为空！");
        }
    }
}
