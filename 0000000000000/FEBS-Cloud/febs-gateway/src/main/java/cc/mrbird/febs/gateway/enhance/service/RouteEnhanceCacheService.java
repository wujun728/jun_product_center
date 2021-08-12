package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.gateway.enhance.entity.BlackList;
import cc.mrbird.febs.gateway.enhance.entity.RateLimitRule;
import reactor.core.publisher.Flux;

import java.util.Set;

/**
 * @author MrBird
 */
public interface RouteEnhanceCacheService {

    /**
     * 缓存所有黑名单规则
     *
     * @param blackList 黑名单列表
     */
    void saveAllBlackList(Flux<BlackList> blackList);

    /**
     * 删除黑名单规则缓存
     *
     * @param blackList 黑名单
     */
    void removeBlackList(BlackList blackList);

    /**
     * 缓存所有限流规则
     *
     * @param rateLimitRules 限流规则列表
     */
    void saveAllRateLimitRules(Flux<RateLimitRule> rateLimitRules);

    /**
     * 缓存黑名单规则
     *
     * @param blackList 黑名单规则
     */
    void saveBlackList(BlackList blackList);

    /**
     * 从缓存中获取黑名单规则
     *
     * @param ip ip
     * @return 黑名单
     */
    Set<Object> getBlackList(String ip);

    /**
     * 从缓存中获取黑名单规则
     *
     * @return 黑名单
     */
    Set<Object> getBlackList();

    /**
     * 缓存限流规则
     *
     * @param rateLimitRule 限流规则
     */
    void saveRateLimitRule(RateLimitRule rateLimitRule);

    /**
     * 从缓存中获取限流规则
     *
     * @param uri    uri
     * @param method method
     * @return 限流规则
     */
    Object getRateLimitRule(String uri, String method);

    /**
     * 获取当前请求次数
     *
     * @param uri uri
     * @param ip  ip
     * @return 次数
     */
    int getCurrentRequestCount(String uri, String ip);

    /**
     * 从缓存中删除限流规则
     *
     * @param rateLimitRule 限流规则
     */
    void removeRateLimitRule(RateLimitRule rateLimitRule);

    /**
     * 设置请求次数
     *
     * @param uri  uri
     * @param ip   ip
     * @param time time
     */
    void setCurrentRequestCount(String uri, String ip, Long time);

    /**
     * 递增请求次数
     *
     * @param uri uri
     * @param ip  ip
     */
    void incrCurrentRequestCount(String uri, String ip);
}
