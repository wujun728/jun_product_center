package com.framework.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.framework.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.framework.security.util
 * @description: token工具类·
 * @author: lzd
 * @create: 2020-06-25 09:56
 **/
@Slf4j
@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expireTime;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 创建Token
     *
     * @param issDate
     * @param expireDate
     * @param userCode   用户
     * @return token字符串
     */
    public String createToken(Date issDate, Date expireDate, String userCode) {
        //用户唯一标识
        return JWT.create().withClaim(Constants.LOGIN_USER_KEY, userCode)
                .withIssuedAt(issDate).withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证令牌是否有效
     *
     * @param token 字符串
     */
    public boolean verifyToken(String token) {
        JWTVerifier build = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            build.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.info("token已过期：{}", token);
            return false;
        }
    }

    /**
     * 获取用户Id
     *
     * @param token 字符串
     */
    public String getUserCode(String token) {
        JWTVerifier build = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            DecodedJWT jwt = build.verify(token);
            return jwt.getClaim(Constants.LOGIN_USER_KEY).asString();
        } catch (JWTVerificationException e) {
            log.info("token已过期：{}", token);
            return null;
        }
    }
}
