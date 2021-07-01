package com.framework.security.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security.config
 * @description: Redis配置类
 * @author: lzd
 * @create: 2020-06-25 10:34
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 自定义springSessionDefaultRedisSerializer对象，将会替代默认的SESSION序列化对象。
     * 默认是JdkSerializationRedisSerializer，缺点是需要类实现Serializable接口。
     * 并且在反序列化时如果异常会抛出SerializationException异常，
     * 而SessionRepositoryFilter又没有处理异常，故如果序列化异常时就会导致请求异常
     */

    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * JacksonJsonRedisSerializer和GenericJackson2JsonRedisSerializer的区别：
     * GenericJackson2JsonRedisSerializer在json中加入@class属性，类的全路径包名，方便反系列化。
     * JacksonJsonRedisSerializer如果存放了List则在反系列化的时候，
     * 如果没指定TypeReference则会报错java.util.LinkedHashMap cannot be cast。
     *
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}