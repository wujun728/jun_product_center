package com.ruoyi.mq.config;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mq.enums.MqTypeEnum;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * <p> mq主配置 </p>
 *
 * @Author wocurr.com
 */
@Data
@Configuration
public class MqConfig {

    @Resource
    private Environment env;

    private static final String RABBIT_MQ_ENABLED_PREFIX = "spring.rabbitmq.enabled";

    /**
     * 获取mq类型
     *
     * @return
     */
    public String getAsyncType() {
        if (isExistInEnv(RABBIT_MQ_ENABLED_PREFIX)) {
            return MqTypeEnum.RABBITMQ.getCode();
        }
        return MqTypeEnum.RABBITMQ.getCode();
    }

    /**
     * 是否存在环境变量，并且属性为true
     *
     * @return
     */
    private boolean isExistInEnv(String key) {
        if (!env.containsProperty(key)) {
            return false;
        }
        String property = env.getProperty(key);
        if (StringUtils.isBlank(property)) {
            return false;
        }
        return Boolean.parseBoolean(property);
    }
}
