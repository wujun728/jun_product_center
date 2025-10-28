package com.jun.plugin.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jun.plugin.common.exception.BusinessException;
import com.jun.plugin.common.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JAVA-JWT工具类
 */
//@Component
public class JwtUtil {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 过期时间改为从配置文件获取
     */
    private static String accessTokenExpireTime;

    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static String encryptJWTKey;

//    @Value("${accessTokenExpireTime}")
    public void setAccessTokenExpireTime(String accessTokenExpireTime) {
        JwtUtil.accessTokenExpireTime = accessTokenExpireTime;
    }

//    @Value("${encryptJWTKey}")
    public void setEncryptJWTKey(String encryptJWTKey) {
        JwtUtil.encryptJWTKey = encryptJWTKey;
    }

    /**
     * 校验token是否正确
     * @param token Token
     * @return boolean 是否正确
     * @author Wang926454
     * @date 2018/8/31 9:05
     */
    public static boolean verify(String token) throws BusinessException {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, Constant.ACCOUNT) + Base64ConvertUtil.decode(encryptJWTKey);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            logger.error("JWTToken The Token has expired Token失效  TokenExpiredException异常:{}", e.getMessage());
            throw new BusinessException("Token过期失效，请重新认证:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("JWTToken认证解密出现UnsupportedEncodingException异常:{}", e.getMessage());
            throw new BusinessException("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        } catch (Exception e) {
            logger.error("JWTToken认证解密异常:{}", e.getMessage());
            throw new BusinessException("JWTToken认证异常，非法的TOKEN:" + e.getMessage());
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/9/7 16:54
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            logger.error("解密Token中的公共信息出现JWTDecodeException异常:{}", e.getMessage());
            throw new BusinessException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

    /**
     * 生成签名
     * @param account 帐号
     * @return java.lang.String 返回加密的Token
     * @author Wang926454
     * @date 2018/8/31 9:07
     */
    public static String sign(String account, String currentTimeMillis) {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(encryptJWTKey);
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpireTime) * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim(Constant.ACCOUNT, account)
                    .withClaim("currentTimeMillis", currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            logger.error("JWTToken加密出现UnsupportedEncodingException异常:{}", e.getMessage());
            throw new BusinessException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }
    /**
     * 生成签名
     * @param account 帐号
     * @return java.lang.String 返回加密的Token
     * @author Wang926454
     * @date 2018/8/31 9:07
     */
    public static String sign(String account) {
    	try {
    		// 帐号加JWT私钥加密
    		String secret = account + Base64ConvertUtil.decode(encryptJWTKey);
    		// 此处过期时间是以毫秒为单位，所以乘以1000
    		Date date = new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpireTime) * 1000);
    		Algorithm algorithm = Algorithm.HMAC256(secret);
    		// 附带account帐号信息
    		return JWT.create()
    				.withClaim(Constant.ACCOUNT, account)
    				.withClaim("currentTimeMillis", String.valueOf(System.currentTimeMillis()))
    				.withExpiresAt(date)
    				.sign(algorithm);
    	} catch (UnsupportedEncodingException e) {
    		logger.error("JWTToken加密出现UnsupportedEncodingException异常:{}", e.getMessage());
    		throw new BusinessException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
    	}
    }
}
