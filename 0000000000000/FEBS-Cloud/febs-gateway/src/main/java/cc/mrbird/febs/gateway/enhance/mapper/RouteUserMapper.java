package cc.mrbird.febs.gateway.enhance.mapper;

import cc.mrbird.febs.gateway.enhance.entity.RouteUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author MrBird
 */
@Repository
public interface RouteUserMapper extends ReactiveMongoRepository<RouteUser, String> {

    /**
     * 查询路由用户
     *
     * @param username 用户名
     * @return 路由用户
     */
    Mono<RouteUser> findByUsername(String username);

    /**
     * 删除路由用户
     *
     * @param ids 路由用户id
     * @return 被删除的路由用户
     */
    Flux<RouteUser> deleteByIdIn(Collection<String> ids);
}
