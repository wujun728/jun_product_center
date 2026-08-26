package com.ruoyi.im.chat.sender;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.im.chat.config.ChatProcessProperties;
import com.ruoyi.im.chat.config.RedisExtendTemplate;
import com.ruoyi.im.chat.constant.ChatRedisKey;
import com.ruoyi.im.chat.domain.SystemMessage;
import com.ruoyi.im.chat.enums.ChatCmdType;
import com.ruoyi.im.chat.enums.ChatSendCode;
import com.ruoyi.im.chat.model.*;
import com.ruoyi.im.chat.utils.JsonUtil;
import com.ruoyi.mq.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ChatSender {

    @Value("${chat.client.name}")
    private String appName;

    @Autowired
    private RedisExtendTemplate redisExtendTemplate;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ChatProcessProperties chatClientProperties;
    @Autowired(required = false)
    private RabbitService rabbitService;

    /**
     * 发送业务系统消息
     *
     * @param message 系统消息
     */
    public <T> void sendBusinessSystemMessage(BusinessSystemMessage message) {
        Map<String, List<ChatUserInfo>> serverMap = getServerMap(message);
        BusinessRecvInfo recvInfo;
        // 逐个server发送
        for (Map.Entry<String, List<ChatUserInfo>> entry : serverMap.entrySet()) {
            recvInfo = new BusinessRecvInfo();
            recvInfo.setCmd(ChatCmdType.BUSINESS_SYSTEM_MESSAGE.code());
            recvInfo.setSender(message.getSenderId());
            recvInfo.setReceivers(new LinkedList<>(entry.getValue()));
            recvInfo.setServiceName(appName);
            recvInfo.setData(getDefaultData(message));
            try {
                rabbitService.convertAndSend(chatClientProperties.getSystemExchange(), chatClientProperties.getSystemRoutingKey(), JsonUtil.encode(recvInfo));
            } catch (Exception e) {
                log.error("推送业务系统消息到消息队列失败!", e);
            }
        }
    }

    /**
     * 默认系统消息
     *
     * @param message 系统消息
     * @return 系统消息
     */
    private <T> Object getDefaultData(BusinessSystemMessage message) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setId(IdUtils.fastSimpleUUID());
        systemMessage.setType(message.getType());
        systemMessage.setContent(message.getContent());
        systemMessage.setStatus(ChatSendCode.SUCCESS.code());
        systemMessage.setSendId(message.getSenderId());
        systemMessage.setCreateId(message.getSenderId());
        systemMessage.setSendTime(DateUtils.getNowDate());
        systemMessage.setCreateTime(DateUtils.getNowDate());
        return systemMessage;
    }

    /**
     * 获取在线token
     *
     * @param userIds 用户id集合
     * @return 在线token集合
     */
    public Map<String, List<String>> getOnlineToken(List<String> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyMap();
        }
        // 把所有用户的key都存起来
        Map<String, ChatUserInfo> userMap = new HashMap<>();
        for (String id : userIds) {
            List<String> tokenList = redisCache.getCacheList(ChatRedisKey.USER_TOKEN_KEY + id);
            for (String token : tokenList) {
                String key = String.join(":", ChatRedisKey.IM_USER_SERVER_ID, id, token);
                userMap.put(key, new ChatUserInfo(id, token));
            }
        }
        // 批量拉取
        List<Object> serverIds = redisExtendTemplate.opsForValue().multiGet(userMap.keySet());
        int idx = 0;
        Map<String, List<String>> onlineMap = new HashMap<>();
        for (Map.Entry<String, ChatUserInfo> entry : userMap.entrySet()) {
            // serverId有值表示用户在线
            if (serverIds.get(idx++) != null) {
                ChatUserInfo userInfo = entry.getValue();
                List<String> tokens = onlineMap.computeIfAbsent(userInfo.getId(), o -> new LinkedList<>());
                tokens.add(userInfo.getToken());
            }
        }
        // 去重并返回
        return onlineMap;
    }

    /**
     * 是否在线
     *
     * @param userId 用户id
     * @param token  用户token
     * @return 是否在线
     */
    public Boolean isOnline(String userId, String token) {
        String key = String.join(":", ChatRedisKey.IM_USER_SERVER_ID, userId, token);
        return redisExtendTemplate.hasKey(key);
    }

    /**
     * 获取在线用户
     *
     * @param userIds 用户id集合
     * @return 在线用户集合
     */
    public List<String> getOnlineUser(List<String> userIds) {
        return new LinkedList<>(getOnlineToken(userIds).keySet());
    }

    /**
     * 获取服务器的用户
     *
     * @param message 消息
     * @return 服务器用户集合
     */
    private Map<String, List<ChatUserInfo>> getServerMap(Object message) {
        // 格式:map<服务器id,list<接收方>>
        Map<String, List<ChatUserInfo>> serverMap = new HashMap<>();
        List<String> recvIds = new LinkedList<>();
        if (message instanceof ChatSystemMessage) {
            recvIds = ((ChatSystemMessage<?>) message).getRecvIds();
        } else if (message instanceof BusinessSystemMessage) {
            recvIds = ((BusinessSystemMessage) message).getRecvIds();
        }
        if (CollectionUtils.isEmpty(recvIds)) {
            return serverMap;
        }
        // 根据群聊每个成员所连的IM-server，进行分组
        Map<String, ChatUserInfo> sendMap = new HashMap<>();
        recvIds.forEach(id -> {
            List<String> tokenList = redisCache.getCacheList(ChatRedisKey.USER_TOKEN_KEY + id);
            for (String token : tokenList) {
                String key = String.join(":", ChatRedisKey.IM_USER_SERVER_ID, id, token);
                sendMap.put(key, new ChatUserInfo(id, token));
            }
        });
        // 批量拉取
        List<Object> serverIds = redisExtendTemplate.opsForValue().multiGet(sendMap.keySet());
        int idx = 0;
        for (Map.Entry<String, ChatUserInfo> entry : sendMap.entrySet()) {
            Object serverId = serverIds.get(idx++);
            if (Objects.nonNull(serverId)) {
                List<ChatUserInfo> list = serverMap.computeIfAbsent(String.valueOf(serverId), o -> new LinkedList<>());
                list.add(entry.getValue());
            }
        }
        return serverMap;
    }
}
