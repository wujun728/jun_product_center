package io.github.wujun728.admin.rbac.service;

import io.github.wujun728.admin.rbac.data.DynamicTask;

public interface DynamicTaskApi {
    String execute(DynamicTask task);
}
