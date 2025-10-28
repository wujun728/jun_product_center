package io.github.wujun728.admin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author hyz
 * @date 2020/11/16 15:34
 */
public class TokenUtil {

    public static String getToken(String salt,String key,String password,long millisecond){

        Date start = new Date();
        Date end = new Date(System.currentTimeMillis()+millisecond);

        String token = JWT.create().withAudience(key).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(salt+password));
        return token;
    }
    public static String getKey(String token){
        try{
            String key = JWT.decode(token).getAudience().get(0);
            return key;
        }catch (Exception e){
            return null;
        }
    }
    public static boolean verify(String salt,String token,String password){
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(salt+password)).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
