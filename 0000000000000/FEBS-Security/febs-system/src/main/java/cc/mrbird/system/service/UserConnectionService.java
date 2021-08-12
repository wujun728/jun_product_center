package cc.mrbird.system.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.UserConnection;

import java.util.List;

public interface UserConnectionService extends IService<UserConnection> {

    boolean isExist(String userId, String providerId);

    List<UserConnection> findByProviderUserId(String providerUserId);

    void delete(UserConnection userConnection);
}
