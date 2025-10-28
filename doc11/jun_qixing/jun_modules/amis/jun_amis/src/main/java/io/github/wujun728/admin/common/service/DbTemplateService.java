package io.github.wujun728.admin.common.service;

import java.util.Map;

public interface DbTemplateService {
    String getValue(String templateCode, Map<String,Object> params);
}
