package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.core.entity.constant.FebsConstant;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
public interface RouteEnhanceService {

    /**
     * 根据黑名单规则进行过滤
     *
     * @param exchange ServerWebExchange
     * @return Mono<Void>
     */
    Mono<Void> filterBlackList(ServerWebExchange exchange);

    /**
     * 根据限流规则进行过滤
     *
     * @param exchange ServerWebExchange
     * @return Mono<Void>
     */
    Mono<Void> filterRateLimit(ServerWebExchange exchange);

    /**
     * 异步存储请求日志
     *
     * @param exchange ServerWebExchange
     */
    @Async(FebsConstant.ASYNC_POOL)
    void saveRequestLogs(ServerWebExchange exchange);

    /**
     * 异步存储拦截日志
     *
     * @param exchange ServerWebExchange
     */
    @Async(FebsConstant.ASYNC_POOL)
    void saveBlockLogs(ServerWebExchange exchange);

    /**
     * 异步存储限流日志
     *
     * @param exchange ServerWebExchange
     */
    @Async(FebsConstant.ASYNC_POOL)
    void saveRateLimitLogs(ServerWebExchange exchange);
}
