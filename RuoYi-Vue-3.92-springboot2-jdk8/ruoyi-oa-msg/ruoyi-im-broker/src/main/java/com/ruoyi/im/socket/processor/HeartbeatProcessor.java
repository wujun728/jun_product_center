package com.ruoyi.im.socket.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.im.socket.config.RedisExtendTemplate;
import com.ruoyi.im.socket.constant.ChannelAttrKey;
import com.ruoyi.im.socket.constant.SocketConstant;
import com.ruoyi.im.socket.constant.SocketRedisKey;
import com.ruoyi.im.socket.enums.CmdTypeEnum;
import com.ruoyi.im.socket.model.HeartbeatInfo;
import com.ruoyi.im.socket.model.SendInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 心跳消息处理
 *
 * @author wocurr.com
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HeartbeatProcessor extends AbstractMessageProcessor<HeartbeatInfo> {

    private final RedisExtendTemplate redisExtendTemplate;

    @Override
    public void process(ChannelHandlerContext ctx, HeartbeatInfo beatInfo) {
        // 响应ws
        SendInfo<Object> sendInfo = new SendInfo<>();
        sendInfo.setCmd(CmdTypeEnum.HEART_BEAT.code());
        ctx.channel().writeAndFlush(sendInfo);
        // 设置属性
        AttributeKey<Long> heartBeatAttr = AttributeKey.valueOf(ChannelAttrKey.HEARTBEAT_TIMES);
        Long heartbeatTimes = ctx.channel().attr(heartBeatAttr).get();
        ctx.channel().attr(heartBeatAttr).set(++heartbeatTimes);
        if (heartbeatTimes % 10 == 0) {
            // 每心跳10次，用户在线状态续一次命
            AttributeKey<String> userIdAttr = AttributeKey.valueOf(ChannelAttrKey.USER_ID);
            String userId = ctx.channel().attr(userIdAttr).get();
            AttributeKey<String> tokenAttr = AttributeKey.valueOf(ChannelAttrKey.USER_TOKEN);
            String token = ctx.channel().attr(tokenAttr).get();
            String key = String.join(":", SocketRedisKey.IM_USER_SERVER_ID, userId, token);
            redisExtendTemplate.expire(key, SocketConstant.ONLINE_TIMEOUT_SECOND, TimeUnit.SECONDS);
        }
    }

    @Override
    public HeartbeatInfo transForm(Object o) {
        HashMap map = (HashMap) o;
        ObjectMapper objMapper =  new ObjectMapper();
        return objMapper.convertValue(map, HeartbeatInfo.class);
    }
}
