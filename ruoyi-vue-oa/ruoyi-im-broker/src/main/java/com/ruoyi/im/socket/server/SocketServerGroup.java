package com.ruoyi.im.socket.server;

import com.ruoyi.im.socket.constant.SocketRedisKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketServerGroup implements CommandLineRunner {

    public static volatile long serverId = 0;

    private final RedisTemplate<Object, Object> redisTemplate;

    private final List<ISocketServer> socketServers;

    /***
     * 判断服务器是否就绪
     *
     **/

    public boolean isReady() {
        for (ISocketServer server : socketServers) {
            if (!server.isReady()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run(String... args) {
        // 初始化SERVER_ID
        String key = SocketRedisKey.IM_MAX_SERVER_ID;
        serverId = Optional.ofNullable(redisTemplate.opsForValue().increment(key, 1L)).orElse(0L);
        // 启动服务
        for (ISocketServer server : socketServers) {
            server.start();
        }
    }

    @PreDestroy
    public void destroy() {
        // 停止服务
        for (ISocketServer server : socketServers) {
            server.shutdown();
        }
    }
}
