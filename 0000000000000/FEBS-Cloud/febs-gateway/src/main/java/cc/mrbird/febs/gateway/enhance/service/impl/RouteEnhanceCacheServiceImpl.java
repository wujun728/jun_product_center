package cc.mrbird.febs.gateway.enhance.service.impl;

import cc.mrbird.febs.common.redis.service.RedisService;
import cc.mrbird.febs.gateway.enhance.entity.BlackList;
import cc.mrbird.febs.gateway.enhance.entity.RateLimitRule;
import cc.mrbird.febs.gateway.enhance.service.RouteEnhanceCacheService;
import cc.mrbird.febs.gateway.enhance.utils.RouteEnhanceCacheUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Set;

/**
 * @author MrBird
 */
@Slf4j
@Service
public class RouteEnhanceCacheServiceImpl implements RouteEnhanceCacheService {

    private RedisService redisService;

    @Autowired(required = false)
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void saveAllBlackList(Flux<BlackList> blackList) {
        blackList.subscribe(b -> {
            String key = StringUtils.isNotBlank(b.getIp()) ?
                    RouteEnhanceCacheUtil.getBlackListCacheKey(b.getIp()) :
                    RouteEnhanceCacheUtil.getBlackListCacheKey();
            String value = JSONObject.toJSONString(b);
            redisService.sSet(key, value);
        });
        log.info("Cache blacklist into redis >>>");
    }

    @Override
    public void saveBlackList(BlackList blackList) {
        String key = StringUtils.isNotBlank(blackList.getIp()) ?
                RouteEnhanceCacheUtil.getBlackListCacheKey(blackList.getIp()) :
                RouteEnhanceCacheUtil.getBlackListCacheKey();
        redisService.sSet(key, JSONObject.toJSONString(blackList));
    }

    @Override
    public Set<Object> getBlackList(String ip) {
        String key = RouteEnhanceCacheUtil.getBlackListCacheKey(ip);
        return redisService.sGet(key);
    }

    @Override
    public Set<Object> getBlackList() {
        String key = RouteEnhanceCacheUtil.getBlackListCacheKey();
        return redisService.sGet(key);
    }

    @Override
    public void removeBlackList(BlackList blackList) {
        String key = StringUtils.isNotBlank(blackList.getIp()) ?
                RouteEnhanceCacheUtil.getBlackListCacheKey(blackList.getIp()) :
                RouteEnhanceCacheUtil.getBlackListCacheKey();
        redisService.setRemove(key, JSONObject.toJSONString(blackList));
    }

    @Override
    public void saveAllRateLimitRules(Flux<RateLimitRule> rateLimitRules) {
        rateLimitRules.subscribe(r -> {
            String key = RouteEnhanceCacheUtil.getRateLimitCacheKey(r.getRequestUri(), r.getRequestMethod());
            String value = JSONObject.toJSONString(r);
            redisService.set(key, value);
        });
        log.info("Cache rate limit rules into redis >>>");
    }

    @Override
    public void saveRateLimitRule(RateLimitRule rateLimitRule) {
        String key = RouteEnhanceCacheUtil.getRateLimitCacheKey(rateLimitRule.getRequestUri(), rateLimitRule.getRequestMethod());
        redisService.set(key, JSONObject.toJSONString(rateLimitRule));
    }


    @Override
    public Object getRateLimitRule(String uri, String method) {
        String key = RouteEnhanceCacheUtil.getRateLimitCacheKey(uri, method);
        return redisService.get(key);
    }

    @Override
    public int getCurrentRequestCount(String uri, String ip) {
        String key = RouteEnhanceCacheUtil.getRateLimitCountKey(uri, ip);
        return redisService.hasKey(key) ? (int) redisService.get(key) : 0;
    }

    @Override
    public void removeRateLimitRule(RateLimitRule rateLimitRule) {
        String key = RouteEnhanceCacheUtil.getRateLimitCacheKey(rateLimitRule.getRequestUri(), rateLimitRule.getRequestMethod());
        redisService.del(key);
    }

    @Override
    public void setCurrentRequestCount(String uri, String ip, Long time) {
        String key = RouteEnhanceCacheUtil.getRateLimitCountKey(uri, ip);
        redisService.set(key, 1, time);
    }

    @Override
    public void incrCurrentRequestCount(String uri, String ip) {
        String key = RouteEnhanceCacheUtil.getRateLimitCountKey(uri, ip);
        redisService.incr(key, 1L);
    }
}
