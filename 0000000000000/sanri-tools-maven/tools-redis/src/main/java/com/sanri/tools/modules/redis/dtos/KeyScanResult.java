package com.sanri.tools.modules.redis.dtos;

import lombok.Data;
import redis.clients.jedis.HostAndPort;

import java.util.List;

@Data
public class KeyScanResult {
    private List<KeyResult> keys;
    private String cursor;
    private int hostIndex;
    private HostAndPort target;
    private boolean finish;
    private boolean done;

    public KeyScanResult() {
    }

    public KeyScanResult(List<KeyResult> keys, String cursor, int hostIndex, HostAndPort target) {
        this.keys = keys;
        this.cursor = cursor;
        this.hostIndex = hostIndex;
        this.target = target;
    }

    @Data
    public static class KeyResult{
        private String key;
        private String type;
        private Long ttl;
        private Long pttl;
        private long length;
        private int slot;

        public KeyResult() {
        }

        public KeyResult(String key, String type, Long ttl, Long pttl, long length) {
            this.key = key;
            this.type = type;
            this.ttl = ttl;
            this.pttl = pttl;
            this.length = length;
        }

        public KeyResult(String key, String type, Long ttl, Long pttl, long length, int slot) {
            this.key = key;
            this.type = type;
            this.ttl = ttl;
            this.pttl = pttl;
            this.length = length;
            this.slot = slot;
        }
    }
}
