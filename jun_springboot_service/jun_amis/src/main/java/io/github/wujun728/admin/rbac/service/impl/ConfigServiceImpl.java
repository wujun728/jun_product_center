package io.github.wujun728.admin.rbac.service.impl;

import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.rbac.data.Config;
import io.github.wujun728.admin.rbac.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    @Resource
    private JdbcService jdbcService;
    @Override
    public String getValue(String code) {
        Config config = jdbcService.findOne(Config.class, "code", code);
        return config == null ? null : config.getValue();
    }
}
