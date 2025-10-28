package io.github.wujun728.admin.common.service;

import io.github.wujun728.admin.common.data.DataListener;

import java.util.Map;

public interface DataListenerTask {
    void call(DataListener dataListener, Map<String, Object> context);
}
