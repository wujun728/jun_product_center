package com.ruoyi.tools.lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [MIG] 分布式锁 RedissonClient 显式配置（OA 迁移引入）
 * <p>
 * 老项目在 ruoyi-common 引入 redisson-spring-boot-starter；迁移后基座 ruoyi-common 不带 redisson，
 * 本模块（ruoyi-tools）因 RedisLock 需要 RedissonClient。为避免 redisson-spring-boot-starter 的
 * RedissonAutoConfiguration 覆盖基座 Lettuce Redis factory 并在大本营无密码 Redis 上触发 AUTH，
 * 这里**排除 starter 自动配置**并显式构造无密码单机 RedissonClient，随基座 spring.redis.* 参数走。
 * </p>
 *
 * @author Wujun
 */
@Configuration
public class RedissonLockConfig
{
    /** Redis 地址（含端口），对齐基座 spring.redis.host/port */
    @Value("${spring.redis.host:localhost}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.database:0}")
    private int database;

    /**
     * 构建无密码单机 RedissonClient（本地开发 Redis 无密码）
     *
     * @return RedissonClient
     */
    @Bean
    @ConditionalOnMissingBean
    public RedissonClient redissonClient()
    {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setPassword(null);
        return Redisson.create(config);
    }
}