package com.sanri.tools.modules.redis.dtos;

import lombok.Data;
import redis.clients.jedis.Jedis;

public class JedisClient {
    public Jedis jedis;
    public boolean isCluster;

    public JedisClient(Jedis jedis, boolean isCluster) {
        this.jedis = jedis;
        this.isCluster = isCluster;
    }
}
