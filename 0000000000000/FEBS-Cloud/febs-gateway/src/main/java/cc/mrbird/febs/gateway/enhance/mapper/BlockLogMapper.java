package cc.mrbird.febs.gateway.enhance.mapper;

import cc.mrbird.febs.gateway.enhance.entity.BlockLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * @author MrBird
 */
public interface BlockLogMapper extends ReactiveMongoRepository<BlockLog, String> {

    /**
     * 删除拦截日志
     *
     * @param ids 日志id
     * @return 被删除的拦截日志
     */
    Flux<BlockLog> deleteByIdIn(Collection<String> ids);
}
