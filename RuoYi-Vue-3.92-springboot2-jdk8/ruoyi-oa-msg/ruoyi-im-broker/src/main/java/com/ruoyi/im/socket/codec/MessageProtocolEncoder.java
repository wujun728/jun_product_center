package com.ruoyi.im.socket.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.im.socket.model.SendInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * 消息协议编码器
 * <p>
 * 该类用于将 SendInfo 对象编码为 WebSocket 文本帧。
 * 它使用 Jackson 库将 Java 对象转换为 JSON 字符串。
 * </p>
 */
public class MessageProtocolEncoder extends MessageToMessageEncoder<SendInfo> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SendInfo sendInfo, List<Object> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String text = objectMapper.writeValueAsString(sendInfo);
        TextWebSocketFrame frame = new TextWebSocketFrame(text);
        list.add(frame);
    }
}
