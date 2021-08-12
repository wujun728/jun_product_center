package cc.mrbird.febs.server.test.feign.fallback;

import cc.mrbird.febs.common.core.annotation.Fallback;
import cc.mrbird.febs.server.test.feign.IRemoteUserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author MrBird
 */
@Slf4j
@Fallback
public class RemoteUserServiceFallback implements FallbackFactory<IRemoteUserService> {

    @Override
    public IRemoteUserService create(Throwable throwable) {
        return (queryRequest, user) -> {
            log.error("获取用户信息失败", throwable);
            return null;
        };
    }
}