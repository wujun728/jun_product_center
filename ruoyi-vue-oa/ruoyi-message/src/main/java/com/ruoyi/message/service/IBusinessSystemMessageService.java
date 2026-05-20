package com.ruoyi.message.service;


import java.util.List;

/**
 * 业务系统通知消息服务
 * @author wocurr.com
 */
public interface IBusinessSystemMessageService {


    /**
     * 发送业务系统通知消息
     *
     * @param type 消息类型
     * @param senderId 发送者id
     * @param recvIds 接收者id列表
     * @param msgContent 消息内容
     */
    void sendBusinessSystemMessage(Integer type, String senderId, List<String> recvIds, String msgContent);

}
