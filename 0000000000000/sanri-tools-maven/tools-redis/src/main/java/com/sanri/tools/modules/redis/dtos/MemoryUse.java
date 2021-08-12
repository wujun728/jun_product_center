package com.sanri.tools.modules.redis.dtos;

import lombok.Data;
import redis.clients.jedis.HostAndPort;

@Data
public class MemoryUse {
    private HostAndPort target;
    private long rss;
    private long max;
    private String policy;
    private long system;
    private long lua;
    private long dbSize;
    private String role;

    public MemoryUse() {
    }

    public MemoryUse(HostAndPort target) {
        this.target = target;
    }
}
