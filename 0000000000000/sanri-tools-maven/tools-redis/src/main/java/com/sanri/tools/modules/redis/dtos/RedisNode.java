package com.sanri.tools.modules.redis.dtos;

import lombok.Data;
import redis.clients.jedis.HostAndPort;

@Data
public class RedisNode {
    private String id;
    private HostAndPort hostAndPort;
    private String role;
    private String master;

    // 槽位范围,只有集群模式才会有
    private int slotStart;
    private int slotEnd;

    private long dbSize;

    public boolean isMaster(){
        return "master".equals(role);
    }
}
