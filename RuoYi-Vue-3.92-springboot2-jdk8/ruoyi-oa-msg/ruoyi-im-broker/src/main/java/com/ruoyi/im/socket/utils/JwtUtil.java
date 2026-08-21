package com.ruoyi.im.socket.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public final class JwtUtil {

    private JwtUtil() {
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseToken(String token, String secret)
    {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
