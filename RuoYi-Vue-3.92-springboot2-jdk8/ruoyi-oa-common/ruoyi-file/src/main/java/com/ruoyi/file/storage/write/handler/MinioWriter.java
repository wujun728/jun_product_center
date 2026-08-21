package com.ruoyi.file.storage.write.handler;

import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.write.Writer;
import com.ruoyi.file.storage.write.domain.WriteFile;
import com.ruoyi.file.storage.util.FileStorageUtils;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class MinioWriter extends Writer {

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public void write(InputStream inputStream, WriteFile writeFile) {
        try {
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfig.getBucketName()).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfig.getBucketName()).build());
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(FileStorageUtils.getAliyunObjectNameByFileUrl(writeFile.getFileUrl()))
                            .stream(bufferedInputStream, writeFile.getFileSize(), -1)
                            .build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

}
