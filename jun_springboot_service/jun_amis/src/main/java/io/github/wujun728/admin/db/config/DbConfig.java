package io.github.wujun728.admin.db.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("db")
@Data
public class DbConfig {
    @Value("${schema:db_qixing_amis}")
    private String schema;
    @Value("${manage-schema:information_schema}")
    private String manageSchema;
    @Value("${type:mysql}")
    private String type;
}
