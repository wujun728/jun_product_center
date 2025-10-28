package io.github.wujun728.admin.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@Data
public class FileConfig {
    private String type;
    private String base;
    private String ossEndpoint;
    private String ossKey;
    private String ossSecret;
    private String ossBucketName;
    private String domain;
}
