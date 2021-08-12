package com.sanri.tools.modules.redis.dtos;

import lombok.Data;
import redis.clients.jedis.HostAndPort;

import java.util.List;

/**
 * redis 的客户端连接
 */
@Data
public class ClientConnection {
    private HostAndPort target;
    private String role;
    private List<Client> clients;

    @Data
    public static class Client{
        private String id;
        private HostAndPort connect;
        private String age;
        private String idle;
        private String cmd;
    }

    public ClientConnection() {
    }

    public ClientConnection(HostAndPort target, String role) {
        this.target = target;
        this.role = role;
    }

    public ClientConnection(HostAndPort target) {
        this.target = target;
    }
}
