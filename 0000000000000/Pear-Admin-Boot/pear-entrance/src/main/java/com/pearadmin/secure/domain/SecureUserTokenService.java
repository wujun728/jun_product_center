package com.pearadmin.secure.domain;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;

/**
 * Describe: Token 记录服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@Component
public class SecureUserTokenService implements PersistentTokenRepository {

    private final static String USERNAME_KEY = "spring:security:rememberMe:username_key:";
    private final static String SERIES_KEY = "spring:security:rememberMe:series_key:";

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        String series = persistentRememberMeToken.getSeries();
        String key = generateKey(series, SERIES_KEY);
        String usernameKey = generateKey(persistentRememberMeToken.getUsername(), USERNAME_KEY);
        deleteIfPresent(usernameKey);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", persistentRememberMeToken.getUsername());
        hashMap.put("token", persistentRememberMeToken.getTokenValue());
        hashMap.put("date", String.valueOf(persistentRememberMeToken.getDate().getTime()));
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, hashMap);
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(usernameKey, series);
        redisTemplate.expire(usernameKey, 1, TimeUnit.DAYS);
    }

    @Override
    public void updateToken(String s, String s1, Date date) {
        String key = generateKey(s, SERIES_KEY);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.opsForHash().put(key, "token", s1);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        String key = generateKey(s, SERIES_KEY);
        List<String> hashKeys = new ArrayList<>();
        hashKeys.add("username");
        hashKeys.add("token");
        hashKeys.add("date");
        List<String> hashValues = redisTemplate.opsForHash().multiGet(key, hashKeys);
        String username = hashValues.get(0);
        String tokenValue = hashValues.get(1);
        String date = hashValues.get(2);
        if (null == username || null == tokenValue || null == date) {
            return null;
        }
        Long timestamp = Long.valueOf(date);
        Date time = new Date(timestamp);
        return new PersistentRememberMeToken(username, s, tokenValue, time);
    }

    @Override
    public void removeUserTokens(String s) {
        String key = generateKey(s, USERNAME_KEY);
        deleteIfPresent(key);
    }

    private void deleteIfPresent(String key) {
        try {
            if (redisTemplate.hasKey(key)) {
                String series = generateKey(stringRedisTemplate.opsForValue().get(key), SERIES_KEY);
                if (series != null && redisTemplate.hasKey(series)) {
                    redisTemplate.delete(series);
                    redisTemplate.delete(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateKey(String series, String prefix) {
        return prefix + series;
    }

}
