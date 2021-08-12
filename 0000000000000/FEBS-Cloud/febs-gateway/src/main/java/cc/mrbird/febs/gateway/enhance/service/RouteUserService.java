package cc.mrbird.febs.gateway.enhance.service;

import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.gateway.enhance.entity.RouteUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author MrBird
 */
public interface RouteUserService {

    /**
     * 创建路由用户
     *
     * @param user 路由用户
     * @return 路由用户
     */
    Mono<RouteUser> create(RouteUser user);

    /**
     * 更新路由用户
     *
     * @param routeUser 路由用户
     * @return 路由用户
     */
    Mono<RouteUser> update(RouteUser routeUser);

    /**
     * 删除路由用户
     *
     * @param ids 路由用户id
     * @return 被删除的路由用户
     */
    Flux<RouteUser> delete(String ids);

    /**
     * 根据用户名获取路由用户
     *
     * @param username 用户名
     * @return 路由用户
     */
    Mono<RouteUser> findByUsername(String username);

    /**
     * 查找路由用户分页数据
     *
     * @param request   request
     * @param routeUser routeUser
     * @return 路由用户分页数据
     */
    Flux<RouteUser> findPages(QueryRequest request, RouteUser routeUser);

    /**
     * 查找路由用户分页数据count
     *
     * @param routeUser routeUser
     * @return count
     */
    Mono<Long> findCount(RouteUser routeUser);
}
