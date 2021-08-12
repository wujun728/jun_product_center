package cc.mrbird.febs.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.charset.StandardCharsets;

/**
 * 授权码保存到Redis，以确保认证服务器集群的一致性
 *
 * @author MrBird
 */
@Slf4j
@Service
public class RedisAuthenticationCodeService extends RandomValueAuthorizationCodeServices {


    private static final String AUTH_CODE_KEY = "auth_code";
    private final RedisConnectionFactory connectionFactory;

    public RedisAuthenticationCodeService(RedisConnectionFactory connectionFactory) {
        Assert.notNull(connectionFactory, "RedisConnectionFactory required");
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        RedisConnection conn = getConnection();
        try {
            byte[] bytes = conn.hGet(AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8));
            if (bytes == null || ArrayUtils.isEmpty(bytes)) {
                return null;
            }
            OAuth2Authentication authentication = SerializationUtils.deserialize(bytes);
            if (null != authentication) {
                conn.hDel(AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8));
            }
            return authentication;
        } catch (Exception e) {
            return null;
        } finally {
            conn.close();
        }
    }

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        RedisConnection conn = getConnection();
        try {
            conn.hSet(AUTH_CODE_KEY.getBytes(StandardCharsets.UTF_8), code.getBytes(StandardCharsets.UTF_8),
                    SerializationUtils.serialize(authentication));
            log.info("保存authentication code: {}至redis", code);
        } catch (Exception e) {
            log.error("保存authentication code至redis失败", e);
        } finally {
            conn.close();
        }
    }

    private RedisConnection getConnection() {
        return connectionFactory.getConnection();
    }

}
