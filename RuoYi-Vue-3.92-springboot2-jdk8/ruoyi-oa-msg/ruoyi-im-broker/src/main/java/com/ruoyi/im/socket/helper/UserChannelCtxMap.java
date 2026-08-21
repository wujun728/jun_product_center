package com.ruoyi.im.socket.helper;

import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserChannelCtxMap {

    /**
     *  维护userId和ctx的关联关系，格式:Map<userId,map<terminal，ctx>>
     */
    private static final Map<String, Map<String, ChannelHandlerContext>> channelMap = new ConcurrentHashMap();

    public static void addChannelCtx(String userId, String channel, ChannelHandlerContext ctx) {
        channelMap.computeIfAbsent(userId, key -> new ConcurrentHashMap()).put(channel, ctx);
    }

    public static void removeChannelCtx(String userId, String token) {
        if (userId != null && StringUtils.isNotBlank(token) && channelMap.containsKey(userId)) {
            Map<String, ChannelHandlerContext> userChannelMap = channelMap.get(userId);
            userChannelMap.remove(token);
            if (userChannelMap.isEmpty()) {
                channelMap.remove(userId);
            }
        }
    }

    public static ChannelHandlerContext getChannelCtx(String userId, String token) {
        if (userId != null && StringUtils.isNotBlank(token) && channelMap.containsKey(userId)) {
            Map<String, ChannelHandlerContext> userChannelMap = channelMap.get(userId);
            if (userChannelMap.containsKey(token)) {
                return userChannelMap.get(token);
            }
        }
        return null;
    }
}
