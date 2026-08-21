package com.ruoyi.im.socket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT 配置类
 * <p>
 * 该类用于读取 JWT 相关的配置参数，如密钥等。
 * </p>
 */
@Component
public class JwtConfig {
    @Value("${jwt.accessToken.secret}")
    private String secret;

    public String getSecret() {
        return secret;
    }
}

