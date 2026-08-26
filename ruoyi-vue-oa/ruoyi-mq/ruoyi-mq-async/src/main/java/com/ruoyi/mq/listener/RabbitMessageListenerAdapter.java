package com.ruoyi.mq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * <p> 监听适配器 </p>
 *
 * @Author wocurr.com
 */
public class RabbitMessageListenerAdapter extends MessageListenerAdapter {

    public RabbitMessageListenerAdapter(Object delegate) {
        super(delegate);
    }

    @Override
    protected Object[] buildListenerArguments(Object extractedMessage, Channel channel, Message message) {
        return new Object[] {
                extractedMessage, channel, message
        };
    }
}
