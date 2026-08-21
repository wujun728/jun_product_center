package com.ruoyi.file.storage.download.handler;

import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.download.Downloader;
import com.ruoyi.file.storage.download.domain.DownloadFile;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * minio下载器
 */
@Slf4j
@Component
public class MinioDownloader extends Downloader {

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public InputStream getInputStream(DownloadFile downloadFile) {
        InputStream inputStream = null;
        try {
            if (downloadFile.getRange() != null) {
                return minioClient.getObject(GetObjectArgs.builder()
                        .bucket(minioConfig.getBucketName())
                        .object(downloadFile.getFileUrl())
                        .offset(downloadFile.getRange().getStart())
                        .length((long) downloadFile.getRange().getLength())
                        .build());
            }
            inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(downloadFile.getFileUrl())
                    .build());
        } catch (MinioException e) {
            log.error(e.getMessage(), e);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage(), e);
        }
        return inputStream;
    }

}
