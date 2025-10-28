package io.github.wujun728.admin.rbac.service;

import io.github.wujun728.admin.rbac.data.Enterprise;
import io.github.wujun728.admin.rbac.data.User;

import java.util.List;

public interface UserService {
    List<Enterprise> getUserEnterpriseList(User user);

    User get(Long id);
}


