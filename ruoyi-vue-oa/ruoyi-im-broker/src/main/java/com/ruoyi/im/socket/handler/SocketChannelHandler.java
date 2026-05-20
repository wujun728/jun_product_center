package com.ruoyi.im.socket.handler;

import com.ruoyi.im.socket.config.RedisExtendTemplate;
import com.ruoyi.im.socket.constant.ChannelAttrKey;
import com.ruoyi.im.socket.constant.SocketRedisKey;
import com.ruoyi.im.socket.enums.CmdTypeEnum;
import com.ruoyi.im.socket.helper.UserChannelCtxMap;
import com.ruoyi.im.socket.model.SendInfo;
import com.ruoyi.im.socket.processor.AbstractMessageProcessor;
import com.ruoyi.im.socket.processor.ProcessorFactory;
import com.ruoyi.im.socket.utils.SpringContextHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * <p> 聊天消息处理器 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
public class SocketChannelHandler extends SimpleChannelInboundHandler<SendInfo> {

    /**
     * 读取消息
     *
     * @param ctx   channel上下文
     * @param sendInfo 消息内容
     * @throws Exception 抛出异常
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendInfo sendInfo) throws Exception {
        //判断是否可以写
        if (ctx.channel().isActive() && ctx.channel().isWritable()) {
            // 创建处理器进行处理
            AbstractMessageProcessor processor = ProcessorFactory.createProcessor(CmdTypeEnum.fromCode(sendInfo.getCmd()));
            if (processor == null) {
                // 如果处理器不存在，直接关闭连接
                ctx.channel().close();
                log.error("{}连接, 处理器不存在，直接关闭连接", ctx.channel().id().asLongText());
                return;
            }
            processor.process(ctx, processor.transForm(sendInfo.getData()));
        }
    }

    /**
     * 出现异常的处理 打印报错日志
     *
     * @param ctx   channel上下文
     * @param cause 异常信息
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 判断异常类型
        if (cause instanceof DecoderException) {
            ctx.channel().close();
            log.error("{}连接, 解码异常：", ctx.channel().id().asLongText(), cause);
        } else if (cause instanceof IOException) {
            ctx.channel().close();
            log.error("{}连接, IO异常：", ctx.channel().id().asLongText(), cause);
        } else {
            log.error("{}连接, 处理消息异常：", ctx.channel().id().asLongText(), cause);
        }
    }

    /**
     * 监控客户端上线
     *
     * @param ctx channel上下文
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.info("{}连接", ctx.channel().id().asLongText());
    }

    /**
     * 监控客户端离开
     *
     * @param ctx channel上下文
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        AttributeKey<String> userIdAttr = AttributeKey.valueOf(ChannelAttrKey.USER_ID);
        String userId = ctx.channel().attr(userIdAttr).get();
        AttributeKey<String> tokenAttr = AttributeKey.valueOf(ChannelAttrKey.USER_TOKEN);
        String token = ctx.channel().attr(tokenAttr).get();
        ChannelHandlerContext context = UserChannelCtxMap.getChannelCtx(userId, token);
        // 判断一下，避免异地登录导致的误删
        if (context != null && ctx.channel().id().equals(context.channel().id())) {
            // 移除channel
            UserChannelCtxMap.removeChannelCtx(userId, token);
            RedisExtendTemplate redisExtendTemplate = SpringContextHolder.getBean(RedisExtendTemplate.class);
            if (redisExtendTemplate == null) {
                log.error("redisExtendTemplate is null, cannot remove user channel context");
                return;
            }
            // 用户下线
            String key = String.join(":", SocketRedisKey.IM_USER_SERVER_ID, userId, token);
            redisExtendTemplate.delete(key);
            log.error("断开连接, channelId:{},userId:{},token:{}", ctx.channel().id().asLongText(), userId, token);
        }
    }

    /**
     * 心跳超时，自动断开连接
     *
     * @param ctx channel上下文
     * @param evt 触发的事件
     * @throws Exception 抛出异常
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                // 在规定时间内没有收到客户端的上行数据, 主动断开连接
                AttributeKey<String> attr = AttributeKey.valueOf(ChannelAttrKey.USER_ID);
                String userId = ctx.channel().attr(attr).get();
                AttributeKey<String> tokenAttr = AttributeKey.valueOf(ChannelAttrKey.USER_TOKEN);
                String token = ctx.channel().attr(tokenAttr).get();
                log.error("心跳超时，即将断开连接，用户id:{}，token:{}", userId, token);
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
