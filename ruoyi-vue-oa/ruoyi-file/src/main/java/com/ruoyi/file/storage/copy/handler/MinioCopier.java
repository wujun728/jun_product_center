package com.ruoyi.file.storage.copy.handler;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.file.storage.config.MinioConfig;
import com.ruoyi.file.storage.copy.Copier;
import com.ruoyi.file.storage.copy.domain.CopyFile;
import com.ruoyi.file.storage.util.FileStorageUtils;
import io.minio.*;
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
public class MinioCopier extends Copier {

    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    @Override
    public String copy(InputStream inputStream, CopyFile copyFile) {
        String uuid = IdUtils.fastSimpleUUID();
        String fileUrl = FileStorageUtils.getUploadFileUrl(uuid, copyFile.getExtendName());
        try {
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfig.getBucketName()).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfig.getBucketName()).build());
            }
            // 如果指定复制已存在文件，则走如下逻辑
            if (copyFile.isServerCopy() && StringUtils.isNotBlank(copyFile.getFileUrl())) {
                return copy(copyFile.getFileUrl(), fileUrl);
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(fileUrl).stream(bufferedInputStream, copyFile.getFizeSize(), 1024 * 1024 * 5)
                            .build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件复制失败！");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return fileUrl;
    }

    /**
     * 直接复制已存在的文件
     *
     * @param sourceUrl
     * @param fileUrl
     * @return
     */
    private String copy(String sourceUrl, String fileUrl) {
        try {
            minioClient.copyObject(
                    CopyObjectArgs.builder()
                            .bucket(minioConfig.getBucketName())
                            .object(fileUrl)
                            .source(CopySource.builder().bucket(minioConfig.getBucketName()).object(sourceUrl).build())
                            .build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            log.error(e.getMessage(), e);
            throw new BaseException("文件复制失败！");
        }
        return fileUrl;
    }
}
