package org.myframework.commons.jredis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisDAOImpl implements RedisDAO{
    private RedisTemplate<String, Object> redisTemplate = null;

    public RedisDAOImpl() {

    }


    @Override
    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }


    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
