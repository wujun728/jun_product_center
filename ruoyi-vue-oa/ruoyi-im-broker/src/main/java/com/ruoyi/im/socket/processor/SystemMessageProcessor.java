package com.ruoyi.im.socket.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.im.socket.config.SocketServerProperties;
import com.ruoyi.im.socket.constant.SocketRedisKey;
import com.ruoyi.im.socket.enums.CmdTypeEnum;
import com.ruoyi.im.socket.enums.SendCodeEnum;
import com.ruoyi.im.socket.helper.UserChannelCtxMap;
import com.ruoyi.im.socket.model.RecvSimpleInfo;
import com.ruoyi.im.socket.model.SendInfo;
import com.ruoyi.im.socket.model.SendSimpleResult;
import com.ruoyi.im.socket.model.UserInfo;
import com.ruoyi.im.socket.utils.JsonUtil;
import com.ruoyi.im.socket.utils.RedisCache;
import com.ruoyi.im.socket.utils.SpringContextHolder;
import com.ruoyi.mq.service.RabbitService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 系统消息处理
 *
 * @author wocurr.com
 */
@Slf4j
@Component
public class SystemMessageProcessor extends AbstractMessageProcessor<RecvSimpleInfo> {

    private RabbitService rabbitService;
    private SocketServerProperties chatServerProperties;
    private RedisCache redisCache;

    @Override
    public void process(ChannelHandlerContext ctx, RecvSimpleInfo recvInfo) {
        log.info("接收到系统消息,接收用户数量:{}，内容:{}", recvInfo.getReceivers().size(), recvInfo.getData());
        for (String receId : recvInfo.getReceivers()) {
            boolean sendStatus = false;
            try {
                if (redisCache == null) {
                    redisCache = SpringContextHolder.getBean(RedisCache.class);
                }
                List<String> tokenList = redisCache.getCacheList(SocketRedisKey.USER_TOKEN_KEY + receId);
                for (String token : tokenList) {
                    ChannelHandlerContext channelCtx = UserChannelCtxMap.getChannelCtx(receId, token);
                    if (!Objects.isNull(channelCtx)) {
                        // 推送消息到用户
                        SendInfo<Object> sendInfo = new SendInfo<>();
                        sendInfo.setCmd(CmdTypeEnum.SYSTEM_MESSAGE.code());
                        sendInfo.setData(recvInfo.getData());
                        channelCtx.channel().writeAndFlush(sendInfo);
                        sendStatus = true;
                    } else {
                        log.error("未找到channel，接收者:{}，内容:{}", receId, recvInfo.getData());
                    }
                }
                if (sendStatus) {
                    // 发送成功
                    persistentMessage(recvInfo, receId, SendCodeEnum.SUCCESS);
                } else {
                    // 用户不在线
                    persistentMessage(recvInfo, receId, SendCodeEnum.NOT_ONLINE);
                }
            } catch (Exception e) {
                // 发送失败，入库后待重新拉取
                persistentMessage(recvInfo, receId, SendCodeEnum.UNKNOW_ERROR);
                log.error("发送异常，接收者:{}，内容:{}", receId, recvInfo.getData(), e);
            }
        }
    }

    /**
     * 消息持久化
     *
     * @param recvInfo   接收者信息
     * @param sendCode   发送状态
     */
    private void persistentMessage(RecvSimpleInfo recvInfo, String receId, SendCodeEnum sendCode) {
        SendSimpleResult<Object> result = new SendSimpleResult<>();
        result.setCmd(recvInfo.getCmd());
        result.setSender(recvInfo.getSender());
        result.setReceiver(new UserInfo(receId, null));
        result.setCode(sendCode.code());
        result.setSendResult(recvInfo.getSendResult());
        result.setData(recvInfo.getData());
        try {
            if (rabbitService == null) {
                rabbitService = SpringContextHolder.getBean(RabbitService.class);
            }
            if (chatServerProperties == null) {
                chatServerProperties = SpringContextHolder.getBean(SocketServerProperties.class);
            }
            rabbitService.convertAndSend(chatServerProperties.getSystemStoreExchange(), chatServerProperties.getSystemStoreRoutingKey(), JsonUtil.encode(result));
        } catch (JsonProcessingException e) {
            log.error("消息推送确认异常，接收者:{}，内容:",recvInfo.getData(), e);
        }
    }

    /**
     * 转换数据
     *
     * @param obj 数据对象
     * @return    转换后数据
     */
    @Override
    public RecvSimpleInfo transForm(Object obj) throws InstantiationException, IllegalAccessException {
        return transForm(obj, RecvSimpleInfo.class.newInstance());
    }
}
