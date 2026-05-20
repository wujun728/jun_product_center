package com.ruoyi.im.socket.config;

import com.ruoyi.im.socket.server.SocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p> Socket服务器配置 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Configuration
@ConditionalOnProperty(
        prefix = "socket.server",
        name = "enabled",
        havingValue = "true"
)
public class SocketConfig implements CommandLineRunner, DisposableBean {

    /**
     * Socket服务器
     */
    private SocketServer chatServer;
    /**
     * Socket服务器配置
     */
    @Resource
    private SocketServerProperties properties;
    /**
     * JWT配置
     */
    @Resource
    private JwtConfig jwtConfig;

    @Override
    public void run(String... args) {
        try {
            chatServer = new SocketServer(Integer.parseInt(properties.getPort()), properties.getUri(), jwtConfig);
            chatServer.start();
        } catch (Exception e) {
            log.error("Socket服务器监听端口[{}]启动失败：", properties.getPort(), e);
        }
    }

    @Override
    public void destroy() {
        try {
            chatServer.shutdown();
        } catch (Exception e) {
            log.error("Socket服务器关闭失败：", e);
        }
    }
}
