package com.ruoyi.file.storage.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author wocurr.com
 */
@Data
@Component
@PropertySource(value = "classpath:application-file.properties")
public class FileConfig {
    @Value("${file.storage.type}")
    private String storageType;

    @Value("${file.local-storage-path}")
    private String localStoragePath;

    @Value("${file.bucket-name}")
    private String bucket;


}
