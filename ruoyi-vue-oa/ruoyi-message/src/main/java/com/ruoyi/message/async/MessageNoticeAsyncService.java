package com.ruoyi.message.async;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.message.domain.MessageNotice;
import com.ruoyi.message.enums.BusinessMessageType;
import com.ruoyi.message.service.IBusinessSystemMessageService;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.execute.IAsyncHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统消息通知异步服务
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class MessageNoticeAsyncService implements IAsyncHandler {

    @Autowired
    private IBusinessSystemMessageService systemMessageService;

    private static final String MSG_CONTENT = "您有新的待办，请及时处理哦！";

    @Override
    public void doAsync(AsyncLog asyncLog) {
        log.info("收到消息，消息内容：{}", asyncLog);
        try {
            MessageNotice messageNotice = getMessageNotice(asyncLog);
            systemMessageService.sendBusinessSystemMessage(BusinessMessageType.TODO_RECEIVE.getCode(), null, messageNotice.getRecvIds(), MSG_CONTENT);
        } catch (Exception e) {
            log.error("异步处理消息通知失败，异常原因：", e);
            throw new BaseException("异步处理消息通知失败:" + e.getMessage());
        }
    }

    /**
     * 获取消息通知
     *
     * @param asyncLog
     * @return
     */
    private MessageNotice getMessageNotice(AsyncLog asyncLog) {
        if (StringUtils.isBlank(asyncLog.getMessageContent())) {
            throw new BaseException("消息内容不能为空！");
        }
        return JSONObject.parseObject(asyncLog.getMessageContent(), MessageNotice.class);
    }
}
