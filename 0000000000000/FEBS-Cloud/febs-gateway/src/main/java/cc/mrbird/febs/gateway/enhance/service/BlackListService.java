package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.BlackList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
public interface BlackListService {

    /**
     * 查找所有黑名单列表
     *
     * @return 黑名单列表
     */
    Flux<BlackList> findAll();

    /**
     * 创建黑名单
     *
     * @param blackList 黑名单
     * @return 黑名单
     */
    Mono<BlackList> create(BlackList blackList);

    /**
     * 更新黑名单
     *
     * @param blackList 黑名单
     * @return 黑名单
     */
    Mono<BlackList> update(BlackList blackList);

    /**
     * 删除黑名单
     *
     * @param ids 黑名单id
     * @return 被删除的黑名单
     */
    Flux<BlackList> delete(String ids);

    /**
     * 黑名单分页数据
     *
     * @param request   request
     * @param blackList blackList
     * @return 黑名单分页数据
     */
    Flux<BlackList> findPages(QueryRequest request, BlackList blackList);

    /**
     * 黑名单分页count
     *
     * @param blackList blackList
     * @return count
     */
    Mono<Long> findCount(BlackList blackList);

    /**
     * 查找黑名单
     *
     * @param ip            ip
     * @param requestUri    requestUri
     * @param requestMethod requestMethod
     * @return 黑名单
     */
    Flux<BlackList> findByCondition(String ip, String requestUri, String requestMethod);

}
