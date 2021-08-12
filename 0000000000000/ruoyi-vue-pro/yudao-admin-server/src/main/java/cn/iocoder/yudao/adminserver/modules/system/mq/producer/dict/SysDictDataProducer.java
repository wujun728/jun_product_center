package cn.iocoder.yudao.adminserver.modules.system.mq.producer.dict;

import cn.iocoder.yudao.framework.mq.core.util.RedisMessageUtils;
import cn.iocoder.yudao.adminserver.modules.system.mq.message.dict.SysDictDataRefreshMessage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * DictData 字典数据相关消息的 Producer
 */
@Component
public class SysDictDataProducer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送 {@link SysDictDataRefreshMessage} 消息
     */
    public void sendDictDataRefreshMessage() {
        SysDictDataRefreshMessage message = new SysDictDataRefreshMessage();
        RedisMessageUtils.sendChannelMessage(stringRedisTemplate, message);
    }

}
