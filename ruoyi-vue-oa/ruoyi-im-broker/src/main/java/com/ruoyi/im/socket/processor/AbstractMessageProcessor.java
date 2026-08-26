package com.ruoyi.im.socket.processor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象消息处理
 *
 * @author wocurr.com
 */
public abstract class AbstractMessageProcessor<T> {

    public abstract void process(ChannelHandlerContext ctx, T data);

    public T transForm(Object o) throws InstantiationException, IllegalAccessException {
        return (T) o;
    }

    /**
     * 转换数据
     *
     * @param source 源数据
     * @return 转换后的对象
     */
    public T transForm(Object source, T target) {
        Map receMap = (HashMap) source;
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T) objMapper.convertValue(receMap, target.getClass());
    }
}
