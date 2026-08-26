package com.ruoyi.file.storage.config;

import com.ruoyi.file.storage.upload.handler.CustomMinioClient;
import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@PropertySource(value = "classpath:application-file.properties")
public class MinioConfig {

    @Value(value = "${minio.endpoint}")
    private String endpoint;

    @Value(value = "${minio.bucket-name}")
    private String bucketName;

    @Value(value = "${minio.access-key}")
    private String accessKey;

    @Value(value = "${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }

    @Bean
    public CustomMinioClient customMinioClient() {
        return new CustomMinioClient(MinioAsyncClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build());
    }

}
