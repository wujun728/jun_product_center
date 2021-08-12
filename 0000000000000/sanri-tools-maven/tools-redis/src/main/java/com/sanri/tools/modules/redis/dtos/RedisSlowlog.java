package com.sanri.tools.modules.redis.dtos;

import lombok.Data;
import redis.clients.jedis.HostAndPort;
import redis.clients.util.Slowlog;

import java.util.List;

@Data
public class RedisSlowlog {
    private HostAndPort target;
    private List<Slowlog> slowlogs;
    private String role;

    public RedisSlowlog() {
    }

    public RedisSlowlog(HostAndPort target, List<Slowlog> slowlogs) {
        this.target = target;
        this.slowlogs = slowlogs;
    }

    public RedisSlowlog(HostAndPort target, List<Slowlog> slowlogs, String role) {
        this.target = target;
        this.slowlogs = slowlogs;
        this.role = role;
    }
}
