package com.ruoyi.message.service.impl;

import com.ruoyi.im.chat.model.BusinessSystemMessage;
import com.ruoyi.im.chat.sender.ChatSender;
import com.ruoyi.message.service.IBusinessSystemMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BusinessSystemMessageService implements IBusinessSystemMessageService {

    @Autowired
    private ChatSender imSender;

    /**
     * 发送业务系统通知消息
     *
     * @param type 消息类型
     * @param senderId 发送者id
     * @param recvIds 接收者id列表
     * @param msgContent 消息内容
     */
    @Override
    public void sendBusinessSystemMessage(Integer type, String senderId, List<String> recvIds, String msgContent) {
        BusinessSystemMessage message = new BusinessSystemMessage();
        if (CollectionUtils.isNotEmpty(recvIds)) {
            message.getRecvIds().addAll(recvIds);
        }
        if (StringUtils.isNotBlank(senderId)) {
            message.getRecvIds().add(senderId);
        }
        if (CollectionUtils.isEmpty(message.getRecvIds())) {
            log.error("发送系统消息失败，参数错误！");
            return;
        }
        message.setType(type);
        message.setSenderId(senderId);
        message.setContent(msgContent);
        imSender.sendBusinessSystemMessage(message);
    }

}
