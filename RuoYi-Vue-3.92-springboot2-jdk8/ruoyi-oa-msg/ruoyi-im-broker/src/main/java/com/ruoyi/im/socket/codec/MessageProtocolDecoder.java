package com.ruoyi.im.socket.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.im.socket.model.SendInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * 消息协议解码器
 * <p>
 * 该类用于将接收到的 WebSocket 文本帧解码为 SendInfo 对象。
 * 它使用 Jackson 库将 JSON 字符串转换为 Java 对象。
 * </p>
 */
public class MessageProtocolDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        SendInfo sendInfo = objectMapper.readValue(textWebSocketFrame.text(), SendInfo.class);
        list.add(sendInfo);
    }
}
