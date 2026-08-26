package com.ruoyi.sms.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 短信配置类
 *
 * @author wocurr.com
 */
@Data
@Component
@PropertySource(value = "classpath:application-sms.properties", encoding = "UTF-8")
public class SmsConfig {

    @Value("${sms.use-route}")
    private String useRoute;

    @Value("${sms.aliyun.sign-name}")
    private String aliyunSignName;

    @Value("${sms.aliyun.endpoint}")
    private String aliyunEndpoint;

    @Value("${sms.aliyun.accesskey-id}")
    private String aliyunAccesskeyId;

    @Value("${sms.aliyun.accesskey-secret}")
    private String aliyunAccesskeySecret;

    @Value("${sms.tencent.sign-name}")
    private String tencentSignName;

    @Value("${sms.tencent.sdk-appid}")
    private String tencentAppid;

    @Value("${sms.tencent.accesskey-id}")
    private String tencentAccesskeyId;

    @Value("${sms.tencent.accesskey-secret}")
    private String tencentAccesskeySecret;

}
