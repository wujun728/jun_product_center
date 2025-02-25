package com.jun.plugin.shiro;

import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.exception.code.BaseResponseCode;
import com.jun.plugin.common.service.RedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import lombok.extern.slf4j.Slf4j;

/**
 * 认证
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Slf4j
public class CustomHashedCredentialsMatcher extends SimpleCredentialsMatcher {

    @Lazy
    @Autowired
    private RedisService redisDb;
    @Value("${spring.redis.key.prefix.userToken}")
    private String userTokenPrefix;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String accessToken = (String) token.getPrincipal();
        if (!redisDb.exists(userTokenPrefix + accessToken)) {
        	log.error("-------------------------------------------------------------------------------");
        	log.error("userTokenPrefix + accessToken:"+userTokenPrefix + accessToken);
        	log.error("userTokenPrefix :"+userTokenPrefix );
        	log.error("accessToken:"+ accessToken);
        	log.error("-------------------------------------------------------------------------------");
            SecurityUtils.getSubject().logout();
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        return true;
    }
}
