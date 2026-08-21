package com.ruoyi.seal.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.InputStream;


/**
 * 自定义报表文件存储
 */
@Data
@Component
@Configuration
@PropertySource(value = {"classpath:seal.properties"})
public class SealConfig {

    /**
     * 签名方式
     */
    @Value("${sign.type}")
    private String signType;

    /**
     * 证书
     */
    @Value("${cert.p12}")
    private String certp12;

    /**
     * 签名证书密码
     */
    @Value("${sign.password}")
    private String signPassword;

    public InputStream getCertStream() {
        return this.getClass().getResourceAsStream(getCertp12());
    }
}
